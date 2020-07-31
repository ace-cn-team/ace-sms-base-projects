package ace.sms.base.api.web.application.biz.smsverifycode;

import ace.fw.model.response.GenericResponseExt;
import ace.fw.util.GenericResponseExtUtils;
import ace.sms.base.api.web.application.biz.util.SmsVerifyCodeUtils;
import ace.sms.base.api.web.application.property.SmsVerifyCodeProperties;
import ace.sms.define.base.model.request.CheckRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/3/9 14:19
 * @description
 */
@Component
@Slf4j
public class SmsVerifyCodeCheckBiz {
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private SmsVerifyCodeProperties smsVerifyCodeProperties;

    public GenericResponseExt<Boolean> execute(@Valid CheckRequest request) {
        // 是否启用并且是系统验证码
        if (this.isSystemVerifyCodePass(request)) {
            return GenericResponseExtUtils.buildSuccessWithData(true);
        }
        String cacheKey = SmsVerifyCodeUtils.getCacheKey(request.getVerifyCodeId());
        RBucket<String> verifyCodeRBucket = redissonClient.getBucket(cacheKey);
        String verifyCode = verifyCodeRBucket.get();
        boolean isEqual = request.getVerifyCode().equalsIgnoreCase(verifyCode);
        return GenericResponseExtUtils.buildSuccessWithData(isEqual);
    }

    /**
     * 是否启用并且是系统验证码
     *
     * @return
     */
    private boolean isSystemVerifyCodePass(CheckRequest request) {
        boolean isSystemVerifyCodePass =
                StringUtils.isNoneBlank(smsVerifyCodeProperties.getSystemVerifyCode()) &&
                        StringUtils.equalsIgnoreCase(smsVerifyCodeProperties.getSystemVerifyCode(), request.getVerifyCode());
        return isSystemVerifyCodePass;
    }
}
