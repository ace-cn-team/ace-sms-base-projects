package ace.sms.base.api.web.application.provider.impl.aliyun.enums;

import ace.fw.enums.BaseEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/2/25 18:34
 * @description 阿里云sms错误码
 * https://error-center.aliyun.com/status/product/Dysmsapi?spm=a2c4g.11186623.2.18.243456e0XJHYuX
 */
public enum AliyunSmsResponseEnum implements BaseEnum<String> {
    success("OK", "发送成功"),
    illegalPhoneNumber("isv.MOBILE_NUMBER_ILLEGAL", "短信服务非法手机号"),
    overclockMunute("VALVE:M_MC", "频繁发送，请稍后重试"),  //建议减少每分钟发送数量
    overclockHour("VALVE:H_MC", "频繁发送，请稍后重试"),    //重复过滤[建议减少每分钟发送数量]
    overclockDay("VALVE:D_MC", "频繁发送，请稍后重试"),  //重复过滤[建议减少每分钟发送数量]
    smsAccountException("isv.ACCOUNT_ABNORMAL", "短信服务异常"),   //建议联系平台确认账号
    smsAccountBalanceEmpty("isv.AMOUNT_NOT_ENOUGH", "短信服务暂时不可用"),    //账户余额不足
    smsPlatformError("isp.SYSTEM_ERROR", "远程系统错误"),  //系统错误	建议联系平台核查原因
    smsAccountSignIllegal("isv.SMS_SIGNATURE_ILLEGAL", "短信服务签名不合法"), //短信签名不合法	建议重新申请签名
    smsAccountTemplateIllegal("isv.SMS_TEMPLATE_ILLEGAL", "短信服务模板不合法"),  //短信模板不合法	建议重新申请模版
    smsAccountTemplateParamMissing("isv.TEMPLATE_MISSING_PARAMETERS", "短信服务模板缺少变量"),
    smsMobileCountOverLimt("isv.MOBILE_COUNT_OVER_LIMIT", "短信服务手机号码数量超过限制"),
    smsParamLengthLimt("isv.PARAM_LENGTH_LIMIT", "短信服务参数超出长度限制"),
    smsParamInvalidParam("isv.INVALID_PARAMETERS", "短信服务参数异常"),
    smsKeywordFilter("FILTER", "短信服务敏感关键字"),
    smsBizLimitCountrol("isv.BUSINESS_LIMIT_CONTROL", "短信服务繁忙，请稍后再试"),  //业务限流	建议联系平台核查原因
    smsOutOfService("isv.OUT_OF_SERVICE", "短息服务暂停"), //业务停机	建议联系平台核查原因
    smsRamPermissionDeny("isp.RAM_PERMISSION_DENY", "短信异常，请联系管理员"),  //RAM权限DENY
    smsInvalidJsonParam("isv.INVALID_JSON_PARAM", "短信服务参数异常"),    //JSON参数不合法，只接受字符串值
    smsUnkownException("未知异常", "短信服务未知异常");

    private String code;
    private String desc;

    AliyunSmsResponseEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    private static final Map<String, AliyunSmsResponseEnum> enum_mapper = new HashMap<String, AliyunSmsResponseEnum>(AliyunSmsResponseEnum.values().length) {{
        for (AliyunSmsResponseEnum itemEnum : AliyunSmsResponseEnum.values()) {
            put(itemEnum.code, itemEnum);
        }
    }};

    public static AliyunSmsResponseEnum getEnumByCode(String code) {
        if (code != null) {
            AliyunSmsResponseEnum bizEnum = enum_mapper.get(code);
            if (bizEnum == null) bizEnum = AliyunSmsResponseEnum.smsUnkownException;
            return bizEnum;
        }
        return null;
    }

    public boolean isSuccess() {
        return AliyunSmsResponseEnum.success.equals(this);
    }
}
