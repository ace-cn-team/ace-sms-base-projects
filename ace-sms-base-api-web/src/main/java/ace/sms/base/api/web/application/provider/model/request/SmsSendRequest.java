package ace.sms.base.api.web.application.provider.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/2/25 18:18
 * @description
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmsSendRequest {
    @ApiModelProperty(value = "手机号码", required = true)
    @NotBlank
    private String mobile;
    @ApiModelProperty(value = "短信模板ID", required = true)
    @NotBlank
    private String templateId;
    @ApiModelProperty(value = "短信模板内容", required = true)
    private Map<String, String> templateContent;
}

