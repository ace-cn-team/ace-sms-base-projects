package ace.sms.base.api.web.application.biz.sms;

import ace.fw.model.response.GenericResponseExt;
import ace.sms.base.api.web.application.provider.SmsProvider;
import ace.sms.base.api.web.application.provider.model.request.SmsSendRequest;
import ace.sms.define.base.enums.SmsTemplateEnum;
import ace.sms.define.base.model.request.SendSmsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/2/25 16:44
 * @description
 */
@Component
@Slf4j
public class SmsSendBiz {
    @Autowired
    private SmsProvider smsProvider;

    public GenericResponseExt<Boolean> execute(SendSmsRequest request) {

        return smsProvider.send(SmsSendRequest
                .builder()
                .mobile(request.getMobile())
                .templateId(SmsTemplateEnum.DEFAULT.getCode())
                .templateContent(request.getTemplateContent())
                .build());
    }
}
