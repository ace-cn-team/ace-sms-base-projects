package ace.sms.base.api.web.application.biz.util;

import ace.sms.define.base.model.bo.VerifyCodeId;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/3/9 14:09
 * @description
 */
public class SmsVerifyCodeUtils {
    public static String getCacheKey(VerifyCodeId verifyCodeId) {
        return String.format("ace:sms:%s:%s:%s",
                verifyCodeId.getAppId(),
                verifyCodeId.getBizId(),
                verifyCodeId.getMobile());
    }
}
