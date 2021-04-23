package com.eirapplets.pojo.PO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author pangjian
 * @ClassName Temperature
 * @Description TODO
 * @date 2021/4/13 10:34
 */
@ApiModel(value = "温度实体类")
@Data
public class Temperature {

    @ApiModelProperty(value = "主键，数据库自动生成",name = "id",required = false,example = "1",hidden = true)
    private int id;

    @ApiModelProperty(value = "信息的上传时间，由服务器判断",name = "上传时间",required = false,example = "1999/12/21")
    private String datetime;

    @ApiModelProperty(value = "绑定用户id，服务器判断",name = "userid",required = false,example = "1")
    private int userid;

    @ApiModelProperty(value = "疫情上传温度实体类的温度属性",name = "温度",required = true,example = "36")
    private String temperature;

    @ApiModelProperty(value = "疫情上传温度实体类的地点属性",name = "地点",required = true,example = "桂林市灵田镇花江校区")
    private String place;

}
