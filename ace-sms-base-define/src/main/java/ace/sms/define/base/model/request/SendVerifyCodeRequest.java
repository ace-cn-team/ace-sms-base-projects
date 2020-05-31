package ace.sms.define.base.model.request;

import ace.fw.util.AceEnumUtils;
import ace.sms.define.base.constant.SmsConstants;
import ace.sms.define.base.enums.SmsVerifyCodeTypeEnum;
import ace.sms.define.base.model.VerifyCodeId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;

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
public class SendVerifyCodeRequest {
    @ApiModelProperty(value = "验证码Id", required = true)
    @NotNull
    @Valid
    private VerifyCodeId verifyCodeId;

    @ApiModelProperty(value = "业务名称", required = true)
    @NotBlank
    @Length(min = 1, max = 36)
    private String bizName;

    @ApiModelProperty(value = "验证码长度，默认：4", required = false)
    @Min(value = 4)
    @Max(value = 8)
    private Integer verifyCodeCount = 4;

    @ApiModelProperty(value = "验证码类型,默认:0," + SmsConstants.SMS_VERIFY_CODE_TYPE_REMARK, required = true)
    private Integer smsVerifyCodeType = SmsVerifyCodeTypeEnum.CHARACTER.getCode();

    public SmsVerifyCodeTypeEnum getSmsVerifyCodeTypeEnum() {
        return AceEnumUtils.getEnum(SmsVerifyCodeTypeEnum.class, this.smsVerifyCodeType, SmsVerifyCodeTypeEnum.NUMBER);
    }
}
