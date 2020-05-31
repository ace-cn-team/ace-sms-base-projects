package ace.sms.define.base.constant;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/2/4 20:15
 * @description
 */
public interface SmsConstants {
    /**
     * base api layer openfeign微服务配置名称
     */
    String BASE_FEIGN_CLIENT_NAME = "${ace.ms.service.api.ace-sms-base-api.name:ace-sms-base-api-web}";
    /**
     * base api layer openfeign微服务配置包路径
     */
    String BASE_FEIGN_CLIENT_SERVICE_PACKAGE = "ace.sms.base.api.service";
    /**
     * base api layer openfeign微服务自动配置
     */
    String CONFIG_KEY_BASE_API_CLIENT_ENABLE = "ace.ms.service.api.ace-sms-base-api.enable";
    /**
     * logic api layer openfeign微服务配置名称
     */
    String LOGIC_FEIGN_CLIENT_NAME = "${ace.ms.service.api.ace-sms-logic-api.name:ace-sms-logic-api-web}";


    String SMS_VERIFY_CODE_TYPE_REMARK = "0-纯数字,1-纯字母,2-数字与字母";
}
