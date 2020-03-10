package ace.sms.base.api.web.application.provider.impl.taobao.properties;

import ace.sms.base.api.web.application.provider.impl.taobao.constant.TaoBaoSmsConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/2/25 18:34
 * @description
 */
@Data
@ConfigurationProperties(prefix = TaoBaoSmsConstants.CONFIG_KEY_PREFIX)
public class TaobaoClientSmsConfigProperties {
    private String serverUrl;
    private String appKey;
    private String appSecret;
    private String format;
    private Integer connectTimeout;
    private Integer readTimeout;
    private String sign;
}
