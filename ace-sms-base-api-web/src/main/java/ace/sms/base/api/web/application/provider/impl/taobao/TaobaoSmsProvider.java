package ace.sms.base.api.web.application.provider.impl.taobao;

import ace.fw.enums.SystemCodeEnum;
import ace.fw.exception.BusinessException;
import ace.fw.json.JsonUtils;
import ace.fw.model.response.GenericResponseExt;
import ace.fw.util.RegPatternUtils;
import ace.sms.base.api.web.application.provider.SmsProvider;
import ace.sms.base.api.web.application.provider.impl.taobao.properties.TaobaoClientSmsConfigProperties;
import ace.sms.base.api.web.application.provider.model.request.SmsSendRequest;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/2/25 18:21
 * @description
 */
@Slf4j
@AllArgsConstructor
public class TaobaoSmsProvider implements SmsProvider {

    private TaobaoClient taobaoClient;
    private TaobaoClientSmsConfigProperties configProperties;


    @Override
    public GenericResponseExt<Boolean> send(SmsSendRequest request) {
        GenericResponseExt<Boolean> result = new GenericResponseExt<>();
        try {
            if (RegPatternUtils.isMobile(request.getMobile())) {
                throw new BusinessException("接收人手机号码格式不正确");
            }

            AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
            req.setExtend("");
            req.setSmsType("normal");
            req.setSmsFreeSignName(configProperties.getSign());
            req.setSmsParamString(JsonUtils.toJson(request.getTemplateContent()));
            req.setRecNum(request.getMobile());
            req.setSmsTemplateCode(request.getTemplateId());
            AlibabaAliqinFcSmsNumSendResponse rsp = taobaoClient.execute(req);
            if (rsp.isSuccess() == false) {
                String logMsg = String.format("发送短信失败，%s,%s", JsonUtils.toJson(request), JsonUtils.toJson(rsp));
                log.error(logMsg);
                throw new BusinessException(rsp.getErrorCode(), "短信发送失败");
            }
            result.setCode(SystemCodeEnum.Success.getCode());
            result.setMessage(rsp.getMsg());
            result.setData(true);
        } catch (BusinessException ex) {
            result.setCode(ex.getCode());
            result.setMessage(ex.getMessage());
            result.setData(false);
        } catch (Exception ex) {
            log.error("发送短信息出现异常", ex);
            result.setMessage(SystemCodeEnum.BusinessException.getDesc())
                    .setCode(SystemCodeEnum.BusinessException.getCode())
                    .setData(false);
        }
        return result;
    }
}
