package ace.sms.base.api.web.application.provider.impl.taobao.autoconfigure;

import ace.sms.base.api.web.application.provider.SmsProvider;
import ace.sms.base.api.web.application.provider.impl.taobao.TaobaoSmsProvider;
import ace.sms.base.api.web.application.provider.impl.taobao.constant.TaoBaoSmsConstants;
import ace.sms.base.api.web.application.provider.impl.taobao.properties.TaobaoClientSmsConfigProperties;
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
        name = TaoBaoSmsConstants.CONFIG_KEY_ENABLED,
        havingValue = "true",
        matchIfMissing = false
)
@EnableConfigurationProperties(TaobaoClientSmsConfigProperties.class)
@Configuration
public class TaobaoSmsAutoconfigure {
    @Bean
    @ConditionalOnMissingBean
    public TaobaoClient taobaoClient(TaobaoClientSmsConfigProperties properties) {
        TaobaoClient taobaoClient = new com.taobao.api.DefaultTaobaoClient(
                properties.getServerUrl(),
                properties.getAppKey(),
                properties.getAppSecret(),
                properties.getFormat(),
                properties.getConnectTimeout(),
                properties.getReadTimeout()
        );
        return taobaoClient;
    }

    @Bean
    @ConditionalOnMissingBean
    public SmsProvider taobaoSmsProvider(TaobaoClient taobaoClient, TaobaoClientSmsConfigProperties taobaoClientSmsConfigProperties) {
        TaobaoSmsProvider taobaoSmsProvider = new TaobaoSmsProvider(taobaoClient, taobaoClientSmsConfigProperties);
        return taobaoSmsProvider;
    }
}
