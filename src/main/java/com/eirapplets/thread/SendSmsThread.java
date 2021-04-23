package com.eirapplets.thread;

import com.eirapplets.service.SendSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author pangjian
 * @ClassName SendSmsThread
 * @Description TODO
 * @date 2021/4/23 13:04
 */
@Component
public class SendSmsThread implements Runnable{

    @Autowired
    private SendSmsService sendSmsService;

    @Override
    public void run() {
        sendSmsService.Report();
    }

}
