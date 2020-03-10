package ace.sms.base.api.web.application.provider.impl.log;

import ace.fw.model.response.GenericResponseExt;
import ace.fw.util.GenericResponseExtUtils;
import ace.sms.base.api.web.application.provider.SmsProvider;
import ace.sms.base.api.web.application.provider.model.request.SmsSendRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/2/25 16:35
 * @description 日志短信提供者，用于测试
 */
@Slf4j
public class LogSmsProvider implements SmsProvider {

    @Override
    public GenericResponseExt<Boolean> send(SmsSendRequest request) {
        log.info("发送短信：" + request.toString());
        return GenericResponseExtUtils.buildSuccessWithData(true);
    }
}