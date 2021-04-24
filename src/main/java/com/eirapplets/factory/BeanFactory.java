package com.eirapplets.factory;

import com.eirapplets.service.SendSmsService;
import org.springframework.stereotype.Component;

/**
 * @author pangjian
 * @ClassName BeanFactory
 * @Description TODO
 * @date 2021/4/23 22:28
 */
@Component
public class BeanFactory {


    public static SendSmsService getObject(){

        try {
            SendSmsService sendSmsService = (SendSmsService)Class.forName("com.eirapplets.service.impl.SendSmsServiceImpl").newInstance();
            return sendSmsService;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
