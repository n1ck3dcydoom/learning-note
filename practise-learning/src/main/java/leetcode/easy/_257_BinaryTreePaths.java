package leetcode.easy;

import leetcode.data.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanglei
 * @version 1.0
 * @description 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * <p>
 * 1
 * /   \
 * 2     3
 * \
 * 5
 * <p>
 * 输出: ["1->2->5", "1->3"]
 * <p>
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/5/26 20:57
 **/
public class _257_BinaryTreePaths {

    private static List<List<String>> result0 = new ArrayList<>();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);

        List<String> pathList = binaryTreePaths(root);
        System.out.println(pathList.size());
    }

    public static List<String> binaryTreePaths(TreeNode root) {
        getPath(root, new ArrayList<>());
        List<String> result = new ArrayList<>(result0.size());
        for (List<String> temp : result0) {
            String[] array = new String[temp.size()];
            for (int i = 0; i < temp.size(); i++) {
                array[i] = temp.get(i);
            }
            result.add(String.join("->", array));
        }
        return result;
    }

    private static List<String> getPath(TreeNode root, List<String> list) {
        if (root == null) {
            return null;
        }
        list.add(String.valueOf(root.val));
        getPath(root.left, list);
        getPath(root.right, list);
        if (root.left == null && root.right == null) {
            result0.add(new ArrayList<>(list));
        }
        list.remove(list.size() - 1);
        return list;
    }
}