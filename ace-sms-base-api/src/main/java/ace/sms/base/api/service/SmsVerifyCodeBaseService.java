package ace.sms.base.api.service;

import ace.fw.model.response.GenericResponseExt;
import ace.sms.define.base.constant.SmsConstants;
import ace.sms.define.base.model.VerifyCodeId;
import ace.sms.define.base.model.request.CheckRequest;
import ace.sms.define.base.model.request.GetVerifyCodeRequest;
import ace.sms.define.base.model.request.SendVerifyCodeRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/1/19 12:50
 * @description 短信验证码服务
 */
@FeignClient(
        name = SmsConstants.BASE_FEIGN_CLIENT_NAME,
        contextId = "smsVerifyCodeBaseService",
        path = "/" + SmsVerifyCodeBaseService.MODULE_RESTFUL_NAME
)
@Validated
public interface SmsVerifyCodeBaseService {
    String MODULE_RESTFUL_NAME = "sms-verify-code-base";


    /**
     * 发送验证码
     */
    @ApiOperation(value = "发送验证码")
    @RequestMapping(path = "/send", method = RequestMethod.POST)
    GenericResponseExt<String> send(@Valid @RequestBody SendVerifyCodeRequest request);


    /**
     * 获取验证码
     *
     * @param request
     * @return 返回验证码
     */
    @ApiOperation(value = "获取验证码")
    @ApiResponse(code = 200, message = "成功data字段代表验证码,否则为null")
    @RequestMapping(path = "/get", method = RequestMethod.POST)
    GenericResponseExt<String> get(@Valid @RequestBody GetVerifyCodeRequest request);

    /**
     * 验证验证码是否相等
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "验证码是否相等")
    @RequestMapping(path = "/check", method = RequestMethod.POST)
    GenericResponseExt<Boolean> check(@Valid @RequestBody CheckRequest request);

    /**
     * 删除验证码
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "删除验证码")
    @RequestMapping(path = "/remove", method = RequestMethod.POST)
    GenericResponseExt<Boolean> remove(@Valid @RequestBody VerifyCodeId request);
}
