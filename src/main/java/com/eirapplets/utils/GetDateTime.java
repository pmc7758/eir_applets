package com.eirapplets.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author pangjian
 * @ClassName stampToTime
 * @Description 数据类型转化类
 * @date 2021/4/13 10:50
 */

public class GetDateTime {

    public static String getDateTime() {

        String time;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long timestamp = System.currentTimeMillis();
        Date date = new Date(timestamp);
        time = simpleDateFormat.format(date);
        return time;

    }


    public static String getDate(){

        String date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long timestamp = System.currentTimeMillis();
        Date datenow = new Date(timestamp);
        date = simpleDateFormat.format(datenow);
        return date;

    }

}
