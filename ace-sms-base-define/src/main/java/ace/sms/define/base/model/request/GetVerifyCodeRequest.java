package ace.sms.define.base.model.request;

import ace.sms.define.base.constraint.SmsVerifyCodeIdConstraint;
import ace.sms.define.base.model.bo.VerifyCodeId;
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
 * @create 2020/1/19 14:53
 * @description
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetVerifyCodeRequest {
    @Valid
    @SmsVerifyCodeIdConstraint
    @ApiModelProperty(value = SmsVerifyCodeIdConstraint.FIELD_NAME, required = true)
    private VerifyCodeId verifyCodeId;
}
