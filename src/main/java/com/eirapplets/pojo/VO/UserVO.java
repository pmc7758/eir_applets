package com.eirapplets.pojo.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * @author pangjian
 * @ClassName UserVO
 * @Description TODO
 * @date 2021/4/22 11:24
 */
@ApiModel(value = "用户展示类")
@Data
public class UserVO {

    private String name;
    private String username;
}
