package com.step.template.main.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class TreeNode {
    private Object id;
    private Object parentId;

    @ApiModelProperty("节点名称")
    private String label;

    @ApiModelProperty("值，和ID一样")
    private Object value;

    @ApiModelProperty("等级")
    private Integer level;

    @ApiModelProperty("其他信息")
    private Object other;

    private List<TreeNode> children;

    public TreeNode(Object id, Object parentId, String label) {
        this(id, parentId, label, null);
    }

    public TreeNode(Object id, Object parentId, String label, Object other) {
        this.id = id;
        this.value = id;
        this.parentId = parentId;
        this.label = label;
        this.other = other;
    }
}
