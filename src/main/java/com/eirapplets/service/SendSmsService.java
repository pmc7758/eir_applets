package com.eirapplets.service;

/**
 * @author pangjian
 * @Interface SendMessageService
 * @Description TODO
 * @date 2021/4/13 18:53
 */

public interface SendSmsService {

    public void sendSms(String phoneNumbers);

    public void remind();

    public void report();

}
