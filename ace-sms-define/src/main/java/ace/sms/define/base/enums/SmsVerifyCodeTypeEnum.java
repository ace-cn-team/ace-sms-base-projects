package ace.sms.define.base.enums;

import ace.fw.enums.BaseEnum;
import lombok.Getter;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/3/2 17:24
 * @description
 */
public enum SmsVerifyCodeTypeEnum implements BaseEnum<Integer> {
    NUMBER(0, "纯数字"),
    CHARACTER(1, "纯字母"),
    NUMBER_AND_CHARACTER(2, "数字与字母"),
    ;
    @Getter
    private Integer code;
    @Getter
    private String desc;

    SmsVerifyCodeTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
