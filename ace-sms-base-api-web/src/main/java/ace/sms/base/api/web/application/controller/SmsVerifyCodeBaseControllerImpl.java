package ace.sms.base.api.web.application.controller;

import ace.fw.model.response.GenericResponseExt;
import ace.fw.util.GenericResponseExtUtils;
import ace.sms.base.api.controller.SmsVerifyCodeBaseController;
import ace.sms.base.api.web.application.biz.smsverifycode.SmsVerifyCodeCheckEqualBiz;
import ace.sms.base.api.web.application.biz.smsverifycode.SmsVerifyCodeGetBiz;
import ace.sms.base.api.web.application.biz.smsverifycode.SmsVerifyCodeRemoveBiz;
import ace.sms.base.api.web.application.biz.smsverifycode.SmsVerifyCodeSendBiz;
import ace.sms.define.base.model.VerifyCodeId;
import ace.sms.define.base.model.request.CheckEqualRequest;
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
    private SmsVerifyCodeCheckEqualBiz smsVerifyCodeCheckEqualBiz;
    @Autowired
    private SmsVerifyCodeRemoveBiz smsVerifyCodeRemoveBiz;

    @Override
    public GenericResponseExt<Boolean> send(@Valid SendVerifyCodeRequest request) {
        smsVerifyCodeSendBiz.execute(request);
        return GenericResponseExtUtils.buildSuccessWithData(true);
    }
    
    @Override
    public GenericResponseExt<String> get(@Valid GetVerifyCodeRequest request) {
        return smsVerifyCodeGetBiz.execute(request);
    }

    @Override
    public GenericResponseExt<Boolean> checkEqual(CheckEqualRequest request) {
        return smsVerifyCodeCheckEqualBiz.execute(request);
    }

    @Override
    public GenericResponseExt<Boolean> remove(VerifyCodeId request) {
        return smsVerifyCodeRemoveBiz.execute(request);
    }
}
