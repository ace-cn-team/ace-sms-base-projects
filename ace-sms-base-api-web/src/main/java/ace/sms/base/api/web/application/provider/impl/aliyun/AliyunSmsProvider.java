package ace.sms.base.api.web.application.provider.impl.aliyun;

import ace.fw.enums.SystemCodeEnum;
import ace.fw.exception.BusinessException;
import ace.fw.json.JsonUtils;
import ace.fw.model.response.GenericResponseExt;
import ace.fw.util.RegPatternUtils;
import ace.sms.base.api.web.application.provider.SmsProvider;
import ace.sms.base.api.web.application.provider.impl.aliyun.enums.AliyunSmsResponseEnum;
import ace.sms.base.api.web.application.provider.impl.aliyun.model.response.AliSmsResponse;
import ace.sms.base.api.web.application.provider.impl.aliyun.properties.AliyunSmsConfigProperties;
import ace.sms.base.api.web.application.provider.model.request.SmsSendRequest;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/2/25 18:21
 * @description
 */
@Slf4j
@AllArgsConstructor
public class AliyunSmsProvider implements SmsProvider {

    private IAcsClient iAcsClient;
    private AliyunSmsConfigProperties configProperties;


    @Override
    public GenericResponseExt<Boolean> send(SmsSendRequest smsSendRequest) {
        GenericResponseExt<Boolean> result = new GenericResponseExt<>();
        try {
            if (RegPatternUtils.isMobile(smsSendRequest.getMobile())) {
                throw new BusinessException("接收人手机号码格式不正确");
            }
            CommonRequest request = new CommonRequest();
            request.setSysAction(configProperties.getSmsAction());
            request.setSysProtocol(configProperties.getProtocolType());
            request.setSysVersion(configProperties.getSmsClientSdkVersion());
            request.setSysDomain(configProperties.getSmsServiceDomain());
            request.putQueryParameter("PhoneNumbers", smsSendRequest.getMobile());
            request.putQueryParameter("SignName", configProperties.getSign());
            request.putQueryParameter("TemplateCode", smsSendRequest.getTemplateId());
            if (MapUtils.isNotEmpty(smsSendRequest.getTemplateContent())) {
                String paramJsonString = JsonUtils.toJson(smsSendRequest.getTemplateContent());
                request.putQueryParameter("TemplateParam", paramJsonString);
            }
            CommonResponse commonResponse = iAcsClient.getCommonResponse(request);
            AliSmsResponse aliSmsResponse = JsonUtils.toObject(commonResponse.getData(), AliSmsResponse.class);
            AliyunSmsResponseEnum responseBiz = AliyunSmsResponseEnum.getEnumByCode(aliSmsResponse.getCode());
            if (AliyunSmsResponseEnum.success.equals(responseBiz) == false) {
                log.error("sms短信服务发送失败：手机号={}，smsResponse={}", smsSendRequest.getMobile(), aliSmsResponse);
                throw new BusinessException(SystemCodeEnum.BusinessException.getCode(), aliSmsResponse.getMessage());
            }

            result.setCode(SystemCodeEnum.Success.getCode());
            result.setMessage(aliSmsResponse.getMessage());
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
