package leetcode.easy;

import leetcode.data.TreeNode;

/**
 * @author zhanglei
 * @version 1.0
 * @description 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 *      3
 *     / \
 *    9  20
 *   / \
 *  15  7
 * 返回它的最大深度 3 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/4/27 23:28
 **/
public class _104_MaximumDepthOfBinaryTree {

    // ****************笨方法********************
    private static int currentDeep = 0;
    private static int temp = 0;

    public static void main(String[] args) {
        TreeNode t = new TreeNode(3);
        t.left = new TreeNode(9);
        t.right = new TreeNode(20);
        t.left.left = null;
        t.left.right = null;
        t.right.left = new TreeNode(15);
        t.right.right = new TreeNode(7);
        System.out.println(maxDepth(t));
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        traverseByFramework(root);
        return currentDeep;
    }

    public static void traverseByFramework(TreeNode t) {
        // 前序遍历访问节点
        if (t == null) {
            return;
        }
        temp++;
        currentDeep = currentDeep > temp ? currentDeep : temp;
        traverseByFramework(t.left);
        // 中序遍历访问节点
        traverseByFramework(t.right);
        temp--;
        // 后序遍历访问节点
    }

    /**
     * 求一颗二叉树的深度，对于给定某一个根节点，其深度为左右子树中更深的度+1
     * 即 H(n) = Max( H(n左子树) , H(n右子树)
     * <p>
     * 对于叶子节点，其深度为1
     * null节点，其深度为0
     *
     * @param root 二叉树的根节点
     * @return 二叉树的深度
     */
    private int getDepth(TreeNode root) {
        // 遍历到了null节点，其深度为0
        if (root == null) {
            return 0;
        }
        // 找左子树的深度
        int left = getDepth(root.left);
        // 找右子树的深度
        int right = getDepth(root.right);
        // 返回左子树和右子树深度的最大值加1，即为当前root节点的深度
        return Math.max(left, right) + 1;
    }


    // ****************DFS遍历*******************

    /**
     * 求二叉树的最大深度
     * <p>
     * 所有的递归都需要考虑递归的结束条件
     * 1. 当root节点的左右子树都是null时，return 1
     * 2. 当root节点有一个子树为null时，return 另外一个子树节点的深度
     * 3. 当root节点的两个子树都不为null时，return 左右子树深度更大的值+1 （这里的1指的是root节点本身的深度）
     * <p>
     * 递归有个很重要的两点：
     * 1. 明确递归函数的作用
     * 2. 确定递归返回的条件
     * <p>
     * 这个递归函数的作用：计算当前节点的最大深度
     * 入参：树的某个节点
     * 出参：树的某个节点的深度
     * <p>
     * 什么时候递归返回：
     * 当访问到null节点时（即当前节点的左右子树都为null）时
     * null节点的深度肯定为0，此时返回（找到了叶子节点）
     * <p>
     * 如果当前节点不是null节点，则需要递归访问该节点的左右子树
     * 该节点的深度 = 其左右子树的更大深度值 + 1
     * maxDepth(root) = Math.max(maxDepth(root.left), maxDepth(root.right)) + 1
     *
     * @param root 二叉树
     * @return 最大深度
     */
    public int maxDepth0(TreeNode root) {
        // 如果root节点为null，return 0
        if (root == null) {
            return 0;
        }
        // 如果当前不是null节点，则需要访问其左右子树，才能决定当前节点的深度
        // 访问左子树
        int left = maxDepth0(root.left);
        // 访问右子树
        int right = maxDepth0(root.right);
        // 得到当前节点的深度
        int currentDepth = Math.max(left, right) + 1;
        // TODO 典型的二叉树后序遍历
        return currentDepth;
    }
}