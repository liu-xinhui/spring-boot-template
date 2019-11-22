package com.step.template.main.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.step.template.main.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("系统权限")
@Getter
@Setter
@TableName("permission")
public class Permission extends BaseEntity {

    @ApiModelProperty("编号")
    private String code;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("父级ID")
    private Integer parentId;
}
