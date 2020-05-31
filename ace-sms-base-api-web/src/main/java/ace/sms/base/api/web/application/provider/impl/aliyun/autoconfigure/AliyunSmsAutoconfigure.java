package ace.sms.base.api.web.application.provider.impl.aliyun.autoconfigure;

import ace.sms.base.api.web.application.provider.SmsProvider;
import ace.sms.base.api.web.application.provider.impl.aliyun.AliyunSmsProvider;
import ace.sms.base.api.web.application.provider.impl.aliyun.constant.AliyunSmsConstants;
import ace.sms.base.api.web.application.provider.impl.aliyun.properties.AliyunSmsConfigProperties;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
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
        name = AliyunSmsConstants.CONFIG_KEY_ENABLED,
        havingValue = "true",
        matchIfMissing = false
)
@EnableConfigurationProperties(AliyunSmsConfigProperties.class)
@Configuration
public class AliyunSmsAutoconfigure {
    @Bean
    @ConditionalOnMissingBean
    public IClientProfile iClientProfile(AliyunSmsConfigProperties properties) {
        IClientProfile profile = DefaultProfile.getProfile(properties.getRegionId(), properties.getAppKey(), properties.getAppSecret());
        return profile;
    }

    @Bean
    @ConditionalOnMissingBean
    public IAcsClient iAcsClient(IClientProfile iClientProfile) {
        IAcsClient iAcsClient = new DefaultAcsClient(iClientProfile);
        return iAcsClient;
    }

    @Bean
    @ConditionalOnMissingBean
    public SmsProvider aliyunSmsProvider(IAcsClient iAcsClient, AliyunSmsConfigProperties configProperties) {
        AliyunSmsProvider aliyunSmsProvider = new AliyunSmsProvider(iAcsClient, configProperties);
        return aliyunSmsProvider;
    }
}
