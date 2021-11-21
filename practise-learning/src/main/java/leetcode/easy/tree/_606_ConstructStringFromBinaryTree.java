package leetcode.easy.tree;

import leetcode.data.TreeNode;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description 你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。
 *
 * 空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
 *
 * 示例 1:
 *
 * 输入: 二叉树: [1,2,3,4]
 *        1
 *      /   \
 *     2     3
 *    /
 *   4
 *
 * 输出: "1(2(4))(3)"
 *
 * 解释: 原本将是“1(2(4)())(3())”，
 * 在你省略所有不必要的空括号对之后，
 * 它将是“1(2(4))(3)”。
 * 示例 2:
 *
 * 输入: 二叉树: [1,2,3,null,4]
 *        1
 *      /   \
 *     2     3
 *      \
 *       4
 *
 * 输出: "1(2()(4))(3)"
 *
 * 解释: 和第一个示例相似，
 * 除了我们不能省略第一个对括号来中断输入和输出之间的一对一映射关系。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-string-from-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/6/16 0:48
 **/
public class _606_ConstructStringFromBinaryTree {

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        TreeNode t = new TreeNode(1);
        t.left = new TreeNode(2);
        t.right = new TreeNode(3);
        t.left.right = new TreeNode(4);
        System.out.println(tree2str(t));
    }

    public static String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }
        dfs(t);
        return sb.toString().substring(1, sb.length() - 1);
    }

    private static void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        sb.append("(").append(root.val);
        // 如果当前节点没有左孩子，但是有右孩子需要添加空括号
        if (root.left == null && root.right != null) {
            sb.append("()");
        }
        // 访问左孩子
        dfs(root.left);
        // 访问右孩子
        dfs(root.right);
        // 添加右括号
        sb.append(")");
    }
}