package leetcode.easy.tree;

import leetcode.data.TreeNode;

import java.util.HashSet;

/**
 * @author zhanglei
 * @version 1.0
 * @description TODO
 * @date 2020/6/17 15:54
 **/
public class _653_TwoSumIV {

    private HashSet<Integer> set = new HashSet<>();

    /**
     * dfs遍历整棵树，通过set集合存放节点值
     * <p>
     * 这种查询集合内是否存在两个节点值的某种运算结果是否等于k
     * 感觉都可以通过先遍历集合放入set，通过set去判断是否存在满足条件的节点值
     *
     * @param root 二叉树
     * @param k    查找值
     * @return true存在两个节点的和等于k  false不存在两个节点的和等于k
     */
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        // 查询节点集合里是否存在另一个节点加当前节点
        if (set.contains(k - root.val)) {
            return true;
        }
        // 否则当前节点值加入集合，遍历树
        set.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }
}