package ace.sms.base.api.web.application.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 陈志杭
 * @contact 279397942@qq.com
 * @date 2018/5/17
 * @description
 */
@Data
@ConfigurationProperties(prefix = "ace.sms.verify-code")
public class SmsVerifyCodeProperties {

    /**
     * 验证码过期时间
     */
    private Integer verifyCodeExpireInSeconds = 5 * 60;
    /**
     * 每个手机，每天最多获取几条验证码
     */
    private Integer limitPreMobileMaxCountOfDay = 5;
    /**
     * 每隔几秒可以发送验证码
     */
    private Integer limitSendIntervalInSeconds = 60;
    /**
     * 系统万能注册验证码
     */
    private String systemVerifyCode;
}
