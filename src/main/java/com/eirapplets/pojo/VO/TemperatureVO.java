package com.eirapplets.pojo.VO;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * @author pangjian
 * @ClassName TemperatureDTO
 * @Description TODO
 * @date 2021/4/20 22:29
 */
@ApiModel(value = "体温信息展示类",description = "用来前端展示")
@Data
public class TemperatureVO {


    private String name;

    private String datetime;

    private String temperature;

    private String place;

}
