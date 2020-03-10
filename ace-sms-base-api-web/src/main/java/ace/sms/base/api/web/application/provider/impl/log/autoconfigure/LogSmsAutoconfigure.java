package ace.sms.base.api.web.application.provider.impl.log.autoconfigure;

import ace.sms.base.api.web.application.provider.SmsProvider;
import ace.sms.base.api.web.application.provider.impl.log.LogSmsProvider;
import ace.sms.base.api.web.application.provider.impl.log.constant.LogSmsConstants;
import com.taobao.api.TaobaoClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/2/27 9:51
 * @description
 */
@ConditionalOnProperty(
        name = LogSmsConstants.CONFIG_KEY_ENABLED,
        havingValue = "true",
        matchIfMissing = true
)
@Configuration
public class LogSmsAutoconfigure {

    @Bean
    @ConditionalOnMissingBean
    public SmsProvider smsProvider() {
        return new LogSmsProvider();
    }

}
