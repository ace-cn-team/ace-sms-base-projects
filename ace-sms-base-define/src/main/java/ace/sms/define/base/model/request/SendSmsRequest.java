package ace.sms.define.base.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Map;

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
public class SendSmsRequest {
    @ApiModelProperty(value = "手机号码", required = true)
    @NotBlank(message = "请输入手机号码")
    private String mobile;
    @ApiModelProperty(value = "短信模板ID", required = true)
    @NotBlank(message = "请输入短信模板ID")
    private String templateId;
    @ApiModelProperty(value = "短信模板内容", required = true)
    @Size(min = 1, message = "请输入短信模板内容")
    private Map<String, String> templateContent;
}
