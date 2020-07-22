package ace.sms.base.api.web.application.biz.smsverifycode;

import ace.fw.exception.BusinessException;
import ace.fw.model.response.GenericResponseExt;
import ace.fw.util.AceRandomStringUtils;
import ace.fw.util.GenericResponseExtUtils;
import ace.sms.base.api.web.application.biz.util.SmsVerifyCodeUtils;
import ace.sms.base.api.web.application.propreties.SmsVerifyCodeConfigProperties;
import ace.sms.base.api.web.application.provider.SmsProvider;
import ace.sms.base.api.web.application.provider.model.request.SmsSendRequest;
import ace.sms.define.base.enums.SmsTemplateEnum;
import ace.sms.define.base.enums.SmsVerifyCodeTypeEnum;
import ace.sms.define.base.model.bo.VerifyCodeId;
import ace.sms.define.base.model.request.SendVerifyCodeRequest;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/2/25 16:44
 * @description
 */
@Component
@Slf4j
public class SmsVerifyCodeSendBiz {
    @Autowired
    private SmsProvider smsProvider;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private SmsVerifyCodeConfigProperties smsVerifyCodeConfigProperties;

    public GenericResponseExt<String> execute(@Valid SendVerifyCodeRequest request) {
        //检查发送频率
        this.checkLimitSendInterval(request);
        //检查每天发送次数
        this.checkLimitCountInDay(request);

        String verifyCode = this.newVerifyCode(request.getSmsVerifyCodeTypeEnum(), request.getVerifyCodeLength());
        // 添加验证码到cache
        this.addCacheVerifyCode(request, verifyCode);
        // 发送短信
        GenericResponseExt<Boolean> smsSendResponse = this.send(request, verifyCode);

        smsSendResponse.check();
        //添加发送间隔锁
        this.addSendIntervalLock(request);
        //添加每天发送次数记录
        this.addSendCountInDay(request);
        return GenericResponseExtUtils.buildSuccessWithData(verifyCode);
    }

    private GenericResponseExt<Boolean> send(SendVerifyCodeRequest request, String verifyCode) {
        Map<String, String> templateContent = new HashMap<>();
        templateContent.put("verifyCode", verifyCode);
        GenericResponseExt<Boolean> smsSendResponse = smsProvider.send(SmsSendRequest
                .builder()
                .mobile(request.getVerifyCodeId().getMobile())
                .templateId(SmsTemplateEnum.VERIFY_CODE.getCode())
                .templateContent(templateContent)
                .build());
        return smsSendResponse;
    }

    private void addCacheVerifyCode(SendVerifyCodeRequest request, String verifyCode) {
        String cacheKey = this.getCacheKey(request.getVerifyCodeId());
        RBucket<String> verifyCodeRBucket = redissonClient.getBucket(cacheKey);
        verifyCodeRBucket.set(verifyCode, smsVerifyCodeConfigProperties.getVerifyCodeExpireInSeconds(), TimeUnit.SECONDS);
    }

    private String getCacheKeyForLimitCountInDay(SendVerifyCodeRequest request) {
        String cacheKey = this.getCacheKey(request.getVerifyCodeId());
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return cacheKey + ":countinday:" + format.format(now);
    }

    private void addSendCountInDay(SendVerifyCodeRequest request) {
        String cacheKey = this.getCacheKeyForLimitCountInDay(request);
        RAtomicLong sendedCountRAtomicLong = redissonClient.getAtomicLong(cacheKey);
        sendedCountRAtomicLong.incrementAndGet();
    }

    private void checkLimitCountInDay(SendVerifyCodeRequest request) {
        String cacheKey = this.getCacheKeyForLimitCountInDay(request);
        RAtomicLong sendedCountRAtomicLong = redissonClient.getAtomicLong(cacheKey);
        long sendedCount = sendedCountRAtomicLong.get();
        if (sendedCount >= smsVerifyCodeConfigProperties.getLimitPreMobileMaxCountOfDay()) {
            String msg = String.format("每天只能发送%s条短信验证码", smsVerifyCodeConfigProperties.getLimitPreMobileMaxCountOfDay());
            throw new BusinessException(msg);
        }
    }

    private String getCacheKeySendInterval(SendVerifyCodeRequest request) {
        String cacheKey = this.getCacheKey(request.getVerifyCodeId());
        return cacheKey + ":intervallock";
    }

    private void addSendIntervalLock(SendVerifyCodeRequest request) {
        String cacheKey = this.getCacheKeySendInterval(request);
        RBucket<Boolean> rIntervalLock = redissonClient.getBucket(cacheKey);
        rIntervalLock.trySet(true, smsVerifyCodeConfigProperties.getLimitSendIntervalInSeconds(), TimeUnit.SECONDS);
    }

    private void checkLimitSendInterval(SendVerifyCodeRequest request) {
        String cacheKey = this.getCacheKeySendInterval(request);
        RBucket<Boolean> rIntervalLock = redissonClient.getBucket(cacheKey);
        Boolean rIntervalLockValue = rIntervalLock.get();
        if (rIntervalLockValue != null) {
            throw new BusinessException(String.format("验证码发送太频繁，请至少间隔%s秒", smsVerifyCodeConfigProperties.getLimitSendIntervalInSeconds()));
        }


    }

    private String getCacheKey(VerifyCodeId verifyCodeId) {
        String cacheKey = SmsVerifyCodeUtils.getCacheKey(verifyCodeId);
        return cacheKey;
    }

    private String newVerifyCode(SmsVerifyCodeTypeEnum smsVerifyCodeTypeEnum, int count) {
        if (SmsVerifyCodeTypeEnum.NUMBER_AND_CHARACTER.equals(smsVerifyCodeTypeEnum)) {
            return AceRandomStringUtils.randomAlphanumeric(count);
        } else if (SmsVerifyCodeTypeEnum.CHARACTER.equals(smsVerifyCodeTypeEnum)) {
            return AceRandomStringUtils.randomAlphabetic(count);
        } else {
            return AceRandomStringUtils.randomNumeric(count);
        }
    }
}
