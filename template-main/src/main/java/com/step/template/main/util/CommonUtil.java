package com.step.template.main.util;


import com.step.template.main.vo.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class CommonUtil {

    public static List<TreeNode> listToTree(List<TreeNode> list) {
        //用递归找子
        List<TreeNode> treeList = new ArrayList<>();
        for (TreeNode tree : list) {
            if ("0".equals(tree.getParentId().toString())) {
                tree.setLevel(1);
                treeList.add(findChildren(tree, list));
            }
        }
        return treeList;
    }

    private static TreeNode findChildren(TreeNode tree, List<TreeNode> list) {
        for (TreeNode node : list) {
            if (node.getParentId().equals(tree.getId())) {
                if (tree.getChildren() == null) {
                    tree.setChildren(new ArrayList<>());
                }
                node.setLevel(tree.getLevel() + 1);
                tree.getChildren().add(findChildren(node, list));
            }
        }
        return tree;
    }
}
