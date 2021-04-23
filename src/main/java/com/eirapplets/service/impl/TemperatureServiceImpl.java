package com.eirapplets.service.impl;

import com.eirapplets.jwt.JwtToken;
import com.eirapplets.mapper.TemperatureMapper;
import com.eirapplets.pojo.VO.TemperatureVO;
import com.eirapplets.pojo.VO.UserVO;
import com.eirapplets.service.TemperatureService;
import com.eirapplets.utils.GetDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author pangjian
 * @ClassName TemperatureServiceImpl
 * @Description TODO
 * @date 2021/4/20 22:41
 */
@Service
public class TemperatureServiceImpl implements TemperatureService {

    @Autowired
    TemperatureMapper temperatureMapper;


    /**
     * @Description: 查询当前日期所有用户温度信息业务
     * @Param page: 前端页数
     * @Param row: 前端页面展示的行数
     * @return java.util.List<com.eirapplets.pojo.VO.TemperatureVO>
     * @date 2021/4/20 23:09
    */
    @Override
    public List<TemperatureVO> queryAllTemperatureByTime(Integer page, Integer row,String date) {

        date = date == null ? GetDateTime.getDate() : date;

        int start = (page - 1) * row;

        return temperatureMapper.queryAllTemperatureByTime(start,row,date);

    }


    /**
     * @Description:查询体温信息的总记录条数业务
     * @return java.lang.Integer
     * @date 2021/4/20 23:24
    */
    @Override
    public Integer findTotals(String date) {
        return temperatureMapper.findTotals(date);
    }


    /**
     * @Description: 根据学号查询此人全部记录的业务
     * @Param username: 学号
     * @return java.util.List<com.eirapplets.pojo.VO.TemperatureVO>
     * @date 2021/4/21 10:29
    */
    @Override
    public List<TemperatureVO> queryTemperatureByUsername(String username) {

        List<TemperatureVO> temperatureDAOlist = temperatureMapper.queryTemperatureByUsername(username);

        if(temperatureDAOlist.isEmpty()){
            throw new RuntimeException("您输出的学号不存在！");
        }else {
            return temperatureDAOlist;
        }
    }


    /**
     * @Description:查询自己的全部体温记录信息业务
     * @return java.util.List<com.eirapplets.pojo.VO.TemperatureVO>
     * @date 2021/4/21 10:33
    */
    @Override
    public List<TemperatureVO> queryTemperatureByMe(HttpServletRequest httpServletRequest) {

        String token = httpServletRequest.getHeader("token");
        JwtToken jwtToken = new JwtToken(token);
        String username = (String) jwtToken.getPrincipal();

        List<TemperatureVO> temperatureVOList = temperatureMapper.queryTemperatureByUsername(username);

        if (temperatureVOList.isEmpty()){

            throw new RuntimeException("您还未添加过任何记录");

        }else {
            return temperatureVOList;
        }
    }


    @Override
    public List<UserVO> getAllNoReportByDate(String date) {

        date = date == null ? GetDateTime.getDate():date;
        List<UserVO> userVOList = temperatureMapper.getAllNoReportByDate(date);
        if(userVOList.isEmpty()){
            throw new RuntimeException("今天还未有人上报！");
        }else {
            return userVOList;
        }
    }




}
