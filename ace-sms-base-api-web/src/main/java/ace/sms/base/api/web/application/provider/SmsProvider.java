package ace.sms.base.api.web.application.provider;

import ace.fw.model.response.GenericResponseExt;
import ace.sms.base.api.web.application.provider.model.request.SmsSendRequest;


/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/2/25 16:35
 * @description 短信服务提供者
 */
public interface SmsProvider {
    GenericResponseExt<Boolean> send(SmsSendRequest request);
}
