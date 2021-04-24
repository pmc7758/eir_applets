package com.eirapplets.test;

import com.eirapplets.utils.MD5Utils;
import org.junit.jupiter.api.Test;

/**
 * @author pangjian
 * @ClassName MD5
 * @Description TODO
 * @date 2021/4/23 18:29
 */

public class MD5 {

    @Test
    public void test01(){

        //e10adc3949ba59abbe56e057f20f883e
        System.out.println(MD5Utils.code("123456"));

    }

}
