package ace.sms.base.api.web.application.provider.impl.aliyun.properties;

import ace.sms.base.api.web.application.provider.impl.aliyun.constant.AliyunSmsConstants;
import com.aliyuncs.http.ProtocolType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/2/25 18:34
 * @description
 */
@Data
@ConfigurationProperties(prefix = AliyunSmsConstants.CONFIG_KEY_PREFIX)
public class AliyunSmsConfigProperties {

    private String regionId = "cn-hangzhou";
    private String appKey;
    private String appSecret;
    private String format;
    private Integer connectTimeout;
    private Integer readTimeout;
    /**
     * 短信签名
     */
    private String sign;

    private String smsAction = "SendSms";
    private String smsClientSdkVersion = "2017-05-25";
    private String smsServiceDomain = "dysmsapi.aliyuncs.com";

    private ProtocolType protocolType = ProtocolType.HTTPS;
}
