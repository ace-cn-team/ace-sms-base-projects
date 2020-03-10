package ace.sms.define.logic.model.request;

import ace.image.verify.code.define.base.model.request.CheckRequest;
import ace.sms.define.base.model.request.SendVerifyCodeRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/1/19 14:44
 * @description
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendVerifyCodeWIthImageVerifyCodeRequest {
    @ApiModelProperty(value = "发送短信验证码参数", required = true)
    @NotNull
    @Valid
    private SendVerifyCodeRequest sendVerifyCodeParams;
    @ApiModelProperty(value = "图形验证码参数", required = true)
    @NotNull
    @Valid
    private CheckRequest imageVerifyCodeParams;
}
