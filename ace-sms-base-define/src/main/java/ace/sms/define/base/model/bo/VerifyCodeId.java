package ace.sms.define.base.model.bo;

import ace.common.base.define.model.constraint.AppIdConstraint;
import ace.common.base.define.model.constraint.BizTypeConstraint;
import ace.common.base.define.model.constraint.MobileConstraint;
import ace.fw.util.RegPatternUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
    @AppIdConstraint
    @ApiModelProperty(value = AppIdConstraint.FIELD_NAME, required = true)
    private String appID;
    /**
     * 业务类型
     */
    @BizTypeConstraint
    @ApiModelProperty(value = BizTypeConstraint.FIELD_NAME, required = true)
    private String bizType;
    /**
     * 手机号码
     */
    @MobileConstraint
    @ApiModelProperty(value = MobileConstraint.FIELD_NAME, required = true)
    private String mobile;
}
