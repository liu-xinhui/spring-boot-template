package com.step.template.main.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.step.template.main.base.BaseLogEntity;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@ApiModel("部门Entity")
@Getter
@Setter
@Accessors(chain = true)
@TableName("dept")
public class Dept extends BaseLogEntity {

    @TableLogic
    private Integer deleted;

    private String name;

    private Integer parentId;

}
