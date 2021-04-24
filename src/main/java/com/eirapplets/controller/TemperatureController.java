package com.eirapplets.controller;

import com.eirapplets.pojo.PO.Temperature;
import com.eirapplets.pojo.VO.TemperatureVO;
import com.eirapplets.pojo.VO.UserVO;
import com.eirapplets.service.TemperatureService;
import com.eirapplets.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pangjian
 * @ClassName TemperatureController
 * @Description TODO
 * @date 2021/4/13 10:37
 */
@Api(tags = "温度信息控制器")
@RestController
public class TemperatureController {

    @Autowired
    private UserService userService;

    @Autowired
    private TemperatureService temperatureService;


    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Description:用户添加体温信息控制器
     * @date 2021/3/15 22:01
     */
    @ApiOperation(value = "用户上传体温信息方法", notes = "处理get类型疫情体温信息请求方法")
    @GetMapping("user/login/addtemperature")
    public Map<String, Object> addTemperature(
            @ApiParam(value = "体温信息对象", name = "体温信息对象") Temperature temperature,
            HttpServletRequest httpServletRequest) {

        Map<String, Object> map = new HashMap<>();
        try {
            userService.userAddTemperature(temperature, httpServletRequest);
            map.put("msg", "温度信息上传成功");
            map.put("state", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", e.getMessage());
        }
        return map;

    }


    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Description: 查询当前日期所有学生体温信息分页控制器
     * @Param page: 前端当前页面
     * @Param row: 前端页面展示的行数
     * @date 2021/4/20 23:29
     */
    @ApiOperation(value = "老师根据日期查询所有学生体温信息方法", notes = "老师根据日期分页查询所有学生体温信息方法")
    @GetMapping("user/login/queryalltemperature")
    @RequiresRoles("老师")
    public Map<String, Object> queryAllTemperature(
            @ApiParam(name = "页码", value = "分页的页码", required = false) Integer page,
            @ApiParam(name = "行数", value = "一页展示的记录条数", required = false) Integer row,
            @ApiParam(name = "日期", value = "日期作为查询条件，默认为当前日期", required = false) String date) {


        page = page == null ? 1 : page;
        row = row == null ? 1 : row;

        Map<String, Object> map = new HashMap<>();

        List<TemperatureVO> temperatureVOList = temperatureService.queryAllTemperatureByTime(page, row, date);


        Integer totals = temperatureService.findTotals(date);

        Integer totalPage = totals % row == 0 ? totals / row : totals / row + 1;

        map.put("temperatureList", temperatureVOList);
        map.put("totalsPage", totalPage);
        map.put("currentPage", page);


        return map;

    }


    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Description: 根据学号查询一个学生的全部温度记录
     * @Param username: 前端的学号
     * @date 2021/4/20 23:44
     */
    @ApiOperation(value = "根据学号查询一个学生的全部温度记录")
    @GetMapping("user/login/querytemperaturebyusername")
    @RequiresRoles("老师")
    public Map<String, Object> queryTemperatureByUsername(
            @ApiParam(name = "学号", value = "用户输入的学号") String username) {

        Map<String, Object> map = new HashMap<>();
        List<TemperatureVO> temperatureVOList = null;

        try {
            temperatureVOList = temperatureService.queryTemperatureByUsername(username);
            map.put("temperatureVO", temperatureVOList);
            map.put("state", true);
        } catch (Exception e) {

            e.printStackTrace();
            map.put("msg", e.getMessage());
            map.put("state", false);

        }

        return map;

    }

    /**
     * @Description: 查询自己的体温信息控制器，不需要参数，自动判断登录用户查询自己
     * @Param httpServletRequest: 请求，用来取请求头的token
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @date 2021/4/22 10:27
    */
    @ApiOperation(value = "查询自己的体温信息")
    @GetMapping("user/login/querytemperaturebyme")
    public Map<String, Object> queryTemperatureByMe(HttpServletRequest httpServletRequest) {

        Map<String, Object> map = new HashMap<>();

        try {

            List<TemperatureVO> temperatureVOList = temperatureService.queryTemperatureByMe(httpServletRequest);
            map.put("msg", "查询成功");
            map.put("state", "true");
            map.put("list", temperatureVOList);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", e.getMessage());
            map.put("state", false);
        }
        return map;

    }

    /**
     * @Description: 获取指定日期未上报的学生，默认日期是当天
     * @Param date: 日期
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @date 2021/4/21 23:52
    */
    @ApiOperation(value = "获取指定日期未上报的学生，默认日期是当天")
    @GetMapping("user/login/getallnoreportbydate")
    @RequiresRoles("老师")
    public Map<String,Object> getAllNoReportByDate(
            @ApiParam(name = "日期",value = "查询需要的日期条件") String date){
        Map<String,Object> map = new HashMap<>();

        try{
            List<UserVO> userList = temperatureService.getAllNoReportByDate(date);
            map.put("state",true);
            map.put("msg","查询成功");
            map.put("noreportstudent",userList);
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        return map;
    }



}