package ace.sms.base.api.web.application.config;

import ace.sms.base.api.web.application.property.SmsVerifyCodeProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/1/14 15:57
 * @description
 */
@EnableConfigurationProperties({SmsVerifyCodeProperties.class})
@Configuration
public class SmsBaseApiWebApplicationConfig {
}
