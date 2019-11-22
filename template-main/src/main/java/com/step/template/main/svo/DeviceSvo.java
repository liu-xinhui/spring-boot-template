package com.step.template.main.svo;

import com.step.template.main.base.BaseSvo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("查询条件")
@Getter
@Setter
public class DeviceSvo extends BaseSvo {

    @ApiModelProperty("搜索-编号")
    private String no;

    @ApiModelProperty("搜索-名称")
    private String name;

    @ApiModelProperty("搜索-型号")
    private String model;

    @ApiModelProperty("搜索-安装地址")
    private String address;

    @ApiModelProperty("搜索-使用单位")
    private String customer;
}