package leetcode.dfs.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import leetcode.data.TreeNode;

/**
 * Created by n!Ck
 * Date: 2022/9/5
 * Time: 19:38
 * Description:
 */

public class _652_FindDuplicateSubtrees {

    private static List<TreeNode> res = new ArrayList<>();
    private static Map<String, Integer> childs = new HashMap<>();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.left.left = new TreeNode(4);
        root.right.right = new TreeNode(4);

        System.out.println(findDuplicateSubtrees(root));
    }

    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return res;
    }

    /**
     * 将一棵树序列化,使用 map 记录子树出现的次数
     * 当发现一颗子树"首次"重复出现后,添加到结果集
     */
    private static String dfs(TreeNode root) {
        if (root == null) {
            return "";
        }
        // 需要先得到左右两棵子树后才能序列化,采用后序遍历
        String left = dfs(root.left);
        String right = dfs(root.right);
        // 序列化格式 root = root.val + _ + left + _ + right
        String serial = root.val + "_" + left + "_" + right;
        if (childs.containsKey(serial) && childs.get(serial) == 1) {
            res.add(root);
        }
        childs.put(serial, childs.getOrDefault(serial, 0) + 1);
        return serial;
    }
}
