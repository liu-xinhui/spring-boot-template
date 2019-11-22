package com.step.template.main.vo;

import com.step.template.main.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVo extends User {

    @ApiModelProperty("公司名字")
    private String deviceName;
}
