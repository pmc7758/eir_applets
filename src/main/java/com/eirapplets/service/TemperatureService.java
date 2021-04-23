package com.eirapplets.service;

import com.eirapplets.pojo.VO.TemperatureVO;
import com.eirapplets.pojo.VO.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author pangjian
 * @Interface TemperatureService
 * @Description TODO
 * @date 2021/4/20 22:41
 */

public interface TemperatureService {

    List<TemperatureVO> queryAllTemperatureByTime(Integer page,Integer row,String date);


    Integer findTotals(String date);

    List<TemperatureVO> queryTemperatureByUsername(String username);

    List<TemperatureVO> queryTemperatureByMe(HttpServletRequest httpServletRequest);

    List<UserVO> getAllNoReportByDate(String date);

}
