package leetcode.easy;

import leetcode.data.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanglei
 * @version 1.0
 * @description TODO
 * @date 2020/6/16 23:56
 **/
public class _637_AverageOfLevelsInBinaryTree {

    public static void main(String[] args) {
        // [2147483647,2147483647,2147483647]
        TreeNode root = new TreeNode(2147483647);
        root.left = new TreeNode(2147483647);
        root.right = new TreeNode(2147483647);
//        root.right.left = new TreeNode(15);
//        root.right.right = new TreeNode(7);

        averageOfLevels(root).forEach(System.out::println);

    }

    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        List<List<Long>> list = new ArrayList<>();
        dfs(root, 1, list);
        for (List<Long> currentList : list) {
            long temp = 0;
            for (Long i : currentList) {
                temp += i;
            }
            res.add((double) temp / currentList.size());
        }
        return res;
    }

    private static void dfs(TreeNode root, int level, List<List<Long>> list) {
        if (root == null) {
            return;
        }
        if (list.size() < level) {
            List<Long> currentLevel = new ArrayList<>();
            list.add(currentLevel);
        }
        // 将当前节点放入list中
        list.get(level - 1).add((long) root.val);
        dfs(root.left, level + 1, list);
        dfs(root.right, level + 1, list);
    }
}