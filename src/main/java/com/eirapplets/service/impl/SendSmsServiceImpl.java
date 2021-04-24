package com.eirapplets.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.eirapplets.mapper.UserMapper;
import com.eirapplets.service.SendSmsService;
import com.eirapplets.utils.GetDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author pangjian
 * @ClassName SendSmsServiceImpl
 * @Description 当天没有体温上报的通过短信去提醒学生
 * @date 2021/4/13 18:55
 */
@Service
public class SendSmsServiceImpl implements SendSmsService {

    final static String ACCESSKEYSECRET= "KONIz61HUdEIXu03eAkPIrmd3FUORE";

    final static String ACCESSKEYID = "LTAI5tHqeQvuVZo4BhXLJFxm";

    @Autowired
    private UserMapper userMapper;


    @Override
    public void sendSms(String phoneNumbers) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-qingdao", ACCESSKEYID, ACCESSKEYSECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        // 不能更改
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");


        // 自定义参数:短信模板，短信签名，手机号
        request.putQueryParameter("phoneNumbers",phoneNumbers);
        request.putQueryParameter("signName","疫情信息上报提醒");
        request.putQueryParameter("TemplateCode","SMS_215122910");

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void remind(){

        String date = GetDateTime.getDate();

        List<String> list = userMapper.queryPhoneNumbers(date);

        for (String phone:
            list ) {
            sendSms(phone);
        }

    }

    /**
     * @Description:如果温度超过37度则上报报老师
     * @return void
     * @date 2021/4/23 12:51
    */
    @Override
    public void report() {
        sendSms("17607750063");
    }
}
