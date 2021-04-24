package com.eirapplets.thread;

import com.eirapplets.factory.BeanFactory;
import com.eirapplets.service.SendSmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author pangjian
 * @ClassName SendSmsThread
 * @Description 电话通知老师子线程
 * @date 2021/4/23 13:04
 */
@Component
@Slf4j
public class SendSmsThread implements Runnable{



    @Override
    public void run() {

        BeanFactory.getObject().report();

        log.info("电话通知");
    }

}
