package ace.sms.base.api.web.application.controller;

import ace.fw.model.response.GenericResponseExt;
import ace.sms.base.api.web.controller.SmsBaseController;
import ace.sms.base.api.web.application.biz.sms.SmsSendBiz;
import ace.sms.define.base.model.request.SendSmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/1/19 11:37
 * @description
 */
@RestController
public class SmsBaseControllerImpl implements SmsBaseController {

    @Autowired
    private SmsSendBiz smsSendBiz;

    @Override
    public GenericResponseExt<Boolean> send(@Valid SendSmsRequest request) {
        return smsSendBiz.execute(request);
    }
}
