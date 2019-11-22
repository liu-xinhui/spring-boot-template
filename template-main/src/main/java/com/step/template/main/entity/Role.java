package com.step.template.main.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.step.template.main.base.BaseLogEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel("系统角色")
@Getter
@Setter
@TableName("role")
public class Role extends BaseLogEntity {
    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("备注")
    private String note;

    @ApiModelProperty("权限列表")
    private transient List<Permission> Permissions;
}
