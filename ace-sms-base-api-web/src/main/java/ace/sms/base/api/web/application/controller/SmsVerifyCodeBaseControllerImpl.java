package ace.sms.base.api.web.application.controller;

import ace.fw.model.response.GenericResponseExt;
import ace.sms.base.api.web.controller.SmsVerifyCodeBaseController;
import ace.sms.base.api.web.application.biz.smsverifycode.SmsVerifyCodeCheckBiz;
import ace.sms.base.api.web.application.biz.smsverifycode.SmsVerifyCodeGetBiz;
import ace.sms.base.api.web.application.biz.smsverifycode.SmsVerifyCodeRemoveBiz;
import ace.sms.base.api.web.application.biz.smsverifycode.SmsVerifyCodeSendBiz;
import ace.sms.define.base.model.request.RemoveRequest;
import ace.sms.define.base.model.request.CheckRequest;
import ace.sms.define.base.model.request.GetVerifyCodeRequest;
import ace.sms.define.base.model.request.SendVerifyCodeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/1/19 11:37
 * @description
 */
@RestController
@Validated
public class SmsVerifyCodeBaseControllerImpl implements SmsVerifyCodeBaseController {

    @Autowired
    private SmsVerifyCodeSendBiz smsVerifyCodeSendBiz;
    @Autowired
    private SmsVerifyCodeGetBiz smsVerifyCodeGetBiz;
    @Autowired
    private SmsVerifyCodeCheckBiz smsVerifyCodeCheckBiz;
    @Autowired
    private SmsVerifyCodeRemoveBiz smsVerifyCodeRemoveBiz;

    @Override
    public GenericResponseExt<String> send(@Valid SendVerifyCodeRequest request) {
        return smsVerifyCodeSendBiz.execute(request);
    }

    @Override
    public GenericResponseExt<String> get(@Valid GetVerifyCodeRequest request) {
        return smsVerifyCodeGetBiz.execute(request);
    }

    @Override
    public GenericResponseExt<Boolean> check(CheckRequest request) {
        return smsVerifyCodeCheckBiz.execute(request);
    }

    @Override
    public GenericResponseExt<Boolean> remove(RemoveRequest request) {
        return smsVerifyCodeRemoveBiz.execute(request);
    }
}