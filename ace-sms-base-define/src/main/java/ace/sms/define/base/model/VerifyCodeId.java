package ace.sms.define.base.model;

import ace.fw.util.RegPatternUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
public class VerifyCodeId {
    /**
     * 应用ID
     */
    @ApiModelProperty(value = "appId", required = true)
    @NotBlank
    @Length(min = 1, max = 36)
    private String appId;
    /**
     * 业务ID
     */
    @ApiModelProperty(value = "业务ID,用于区分是什么业务的验证码", required = true)
    @NotBlank
    @Length(min = 1, max = 36)
    private String bizId;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码", required = true)
    @NotBlank
    @Pattern(regexp = RegPatternUtils.REG_MOBILE, message = "请输入正确的手机号码")
    private String mobile;
}
