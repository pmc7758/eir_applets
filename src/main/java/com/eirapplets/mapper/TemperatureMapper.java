package com.eirapplets.mapper;

import com.eirapplets.pojo.VO.TemperatureVO;
import com.eirapplets.pojo.VO.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author pangjian
 * @Interface TemperatureMapper
 * @Description TODO
 * @date 2021/4/20 22:42
 */
@Mapper
public interface TemperatureMapper {

    List<TemperatureVO> queryAllTemperatureByTime(Integer start,Integer row,String date);


    Integer findTotals(String date);

    List<TemperatureVO> queryTemperatureByUsername(String username);

    List<UserVO> getAllNoReportByDate(String date);


}
