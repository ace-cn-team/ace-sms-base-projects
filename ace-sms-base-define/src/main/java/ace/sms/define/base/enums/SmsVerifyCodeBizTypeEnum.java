package ace.sms.define.base.enums;

import ace.fw.enums.BaseEnum;
import lombok.Getter;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/3/2 17:24
 * @description 短信验证码业务类型
 */
public enum SmsVerifyCodeBizTypeEnum implements BaseEnum<String> {
    GLOBAL("common", "全局短信验证码业务类型"),
    ;
    @Getter
    private String code;
    @Getter
    private String desc;

    SmsVerifyCodeBizTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
