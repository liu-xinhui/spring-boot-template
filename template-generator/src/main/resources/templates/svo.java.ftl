package ${svoPackage};

import ${superSvoClass};
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("查询条件")
@Getter
@Setter
public class ${entity}Svo extends BaseSvo{

    //todo 此字段仅供参考，请更改
    @ApiModelProperty("搜索-字段名称")
    private String demo;
}