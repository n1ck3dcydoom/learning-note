package leetcode.easy.tree;

import leetcode.data.TreeNode;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description 给定一个二叉树，它的每个结点都存放着一个整数值。
 *
 * 找出路径和等于给定数值的路径总数。
 *
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 *
 * 示例：
 *
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * 返回 3。和等于 8 的路径有:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/5/28 21:27
 **/
public class _437_PathSumIII {

    public int pathSum(TreeNode root, int sum) {
        // 前缀和数组，一般需要知道目标数组的长度，这里是树节点不超过1000个，所以前缀和数组长度为1001即可
        int[] preArray = new int[1001];
        preArray[0] = 0;
        return trav(root, preArray, 1, sum);
    }

    private int trav(TreeNode root, int[] preArray, int layer, int sum) {
        if (root == null) {
            return 0;
        }
        // 路径和为n的个数
        int count = 0;
        // 记录当前节点的权值
        preArray[layer] = root.val;
        // 判断是否有满足前缀和数组的路径
        int temp = 0;
        for (int i = layer; i >= 0; i--) {
            temp += preArray[i];
            if (temp == sum) {
                count++;
            }
        }
        int left = trav(root.left, preArray, layer + 1, sum);
        int right = trav(root.right, preArray, layer + 1, sum);
        return count + left + right;
    }
}