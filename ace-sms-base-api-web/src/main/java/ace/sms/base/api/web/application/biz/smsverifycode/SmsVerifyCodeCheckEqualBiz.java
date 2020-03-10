package ace.sms.base.api.web.application.biz.smsverifycode;

import ace.fw.model.response.GenericResponseExt;
import ace.fw.util.GenericResponseExtUtils;
import ace.sms.base.api.web.application.biz.util.SmsVerifyCodeUtils;
import ace.sms.define.base.model.request.CheckEqualRequest;
import ace.sms.define.base.model.request.GetVerifyCodeRequest;
import lombok.extern.slf4j.Slf4j;
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
public class SmsVerifyCodeCheckEqualBiz {
    @Autowired
    private RedissonClient redissonClient;

    public GenericResponseExt<Boolean> execute(@Valid CheckEqualRequest request) {
        String cacheKey = SmsVerifyCodeUtils.getCacheKey(request.getVerifyCodeId());
        RBucket<String> verifyCodeRBucket = redissonClient.getBucket(cacheKey);
        String verifyCode = verifyCodeRBucket.get();
        boolean isEqual = request.getVerifyCode().equalsIgnoreCase(verifyCode);
        return GenericResponseExtUtils.buildSuccessWithData(isEqual);
    }
}
