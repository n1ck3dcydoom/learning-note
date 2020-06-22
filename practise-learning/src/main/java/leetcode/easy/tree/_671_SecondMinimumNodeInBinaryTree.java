package leetcode.easy.tree;

import leetcode.data.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhanglei
 * @version 1.0
 * @description TODO
 * @date 2020/6/22 19:32
 **/
public class _671_SecondMinimumNodeInBinaryTree {

    private List<Integer> list = new ArrayList<>();

    /**
     * 暴力遍历
     *
     * @param root 树
     * @return 第二小值
     */
    public int findSecondMinimumValue(TreeNode root) {
        dfs(root);
        if (list.isEmpty() || list.size() == 1) {
            return -1;
        }
        list = list.stream().sorted().collect(Collectors.toList());
        return list.get(1);
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        if (!list.contains(root.val)) {
            list.add(root.val);
        }
        dfs(root.left);
        dfs(root.right);
    }
}