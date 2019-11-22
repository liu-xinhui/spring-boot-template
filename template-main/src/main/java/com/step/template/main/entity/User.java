package com.step.template.main.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.step.template.main.base.BaseLogEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel("系统用户")
@Getter
@Setter
@TableName("user")
public class User extends BaseLogEntity {
    @ApiModelProperty("删除标志")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty("登录名,备用字段,目前随机自动生成")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("包含的角色")
    private transient List<Role> roles;
}
