package ace.sms.base.api.service;

import ace.fw.model.response.GenericResponseExt;
import ace.sms.define.base.constant.SmsConstants;
import ace.sms.define.base.model.request.SendSmsRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/1/19 11:37
 * @description
 */
@FeignClient(
        name = SmsConstants.BASE_FEIGN_CLIENT_NAME,
        contextId = "smsService",
        path = "/" + SmsBaseService.MODULE_RESTFUL_NAME
)
@Validated
public interface SmsBaseService {
    String MODULE_RESTFUL_NAME = "sms-base";
    @ApiOperation(value = "发送SMS")
    @RequestMapping(path = "/send", method = RequestMethod.POST)
    GenericResponseExt<Boolean> send(SendSmsRequest request);
}
