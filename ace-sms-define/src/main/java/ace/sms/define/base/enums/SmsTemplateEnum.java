package ace.sms.define.base.enums;

import ace.fw.enums.BaseEnum;
import lombok.Getter;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/2/28 18:49
 * @description
 */

public enum SmsTemplateEnum implements BaseEnum<String> {
    DEFAULT("default", "默认"),

    VERIFY_CODE("verify_code", "验证码");

    @Getter
    private String code;
    @Getter
    private String desc;

    SmsTemplateEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
