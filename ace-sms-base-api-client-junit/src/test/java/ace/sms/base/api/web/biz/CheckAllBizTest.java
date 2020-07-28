package ace.sms.base.api.web.biz;

import ace.sms.base.api.SmsBaseApi;
import ace.sms.base.api.SmsVerifyCodeBaseApi;
import ace.sms.base.api.client.application.SmsClientJUnitBaseApplication;

import ace.sms.define.base.enums.SmsVerifyCodeTypeEnum;
import ace.sms.define.base.model.bo.VerifyCodeId;
import ace.sms.define.base.model.request.*;
import com.fasterxml.uuid.Generators;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/6/28 14:32
 * @description
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmsClientJUnitBaseApplication.class)
public class CheckAllBizTest {


    @Autowired
    private SmsBaseApi smsBaseApi;
    @Autowired
    private SmsVerifyCodeBaseApi smsVerifyCodeBaseApi;

    private final static String TEST_MOBILE = "15099975787";


    /**
     * 验证全部公开的逻辑接口
     */
    @Test
    public void testBiz() {


        Map<String, String> templateContent = new HashMap<>();
        templateContent.put("${verifyCode}", "123456");
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .mobile(TEST_MOBILE)
                .templateId("sendTemplateId")
                .templateContent(templateContent)
                .build();

        smsBaseApi.send(sendSmsRequest)
                .check()
        ;
        VerifyCodeId verifyCodeId = VerifyCodeId.builder()
                .appId(Generators.timeBasedGenerator().generate().toString().toLowerCase())
                .bizType(Generators.timeBasedGenerator().generate().toString().toLowerCase())
                .mobile(TEST_MOBILE)
                .build();

        String verifyCode = smsVerifyCodeBaseApi.send(SendVerifyCodeRequest.builder()
                .verifyCodeLength(4)
                .smsVerifyCodeType(SmsVerifyCodeTypeEnum.NUMBER.getCode())
                .verifyCodeId(verifyCodeId)
                .build())
                .check();

        Boolean checkResult = smsVerifyCodeBaseApi.check(CheckRequest.builder().verifyCodeId(verifyCodeId)
                .verifyCode(verifyCode).build())
                .check();

        Assert.isTrue(checkResult);

        String getVerifyCode = smsVerifyCodeBaseApi.get(GetVerifyCodeRequest.builder()
                .verifyCodeId(verifyCodeId)
                .build())
                .check();

        Assert.isTrue(StringUtils.equalsIgnoreCase(verifyCode, getVerifyCode));

        Boolean removeResult = smsVerifyCodeBaseApi.remove(RemoveRequest.builder()
                .verifyCodeId(verifyCodeId)
                .build())
                .check();

        Assert.isTrue(removeResult);

    }
}
