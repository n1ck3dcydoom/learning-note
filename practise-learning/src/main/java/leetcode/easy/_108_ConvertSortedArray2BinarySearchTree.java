package leetcode.easy;

import leetcode.data.TreeNode;

/**
 * @author zhanglei
 * @version 1.0
 * @description 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 示例:
 * <p>
 * 给定有序数组: [-10,-3,0,5,9],
 * <p>
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 *      0
 *     / \
 *   -3   9
 *   /   /
 * -10  5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/5/7 21:34
 **/
public class _108_ConvertSortedArray2BinarySearchTree {


    public static TreeNode sortedArrayToBST(int[] nums) {
        return inorderTraverseTree(nums, 0, nums.length);
    }

    /**
     * 二叉搜索树的中序遍历就是一个升序序列
     * 还原一颗二叉树至少需要两个遍历序列
     * 每次取数组的中位数作为根节点，二分数组递归 -> 相当于是先序遍历
     *
     * @param nums  升序序列
     * @param left  数组起点
     * @param right 数组终点
     * @return 还原后的根节点
     */
    private static TreeNode inorderTraverseTree(int[] nums, int left, int right) {
        // 二分数组后左右指针重合，表示没有左右节点了
        if (left == right) {
            return null;
        }
        // 二分数组防止整型溢出
        int mid = left + (right - left) / 2;
        // 把数组的中位数当做根节点，先序遍历和中序遍历还原一颗二叉树
        TreeNode root = new TreeNode(nums[mid]);
        // 递归数组左侧得到左子树
        // TODO inorderTraverseTree(nums, left, mid - 1)为什么会溢出
        root.left = inorderTraverseTree(nums, left, mid);
        // 递归数组右侧得到右子树
        root.right = inorderTraverseTree(nums, mid + 1, right);
        return root;
    }
}