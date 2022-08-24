package leetcode.dfs.medium;

import leetcode.data.TreeNode;

/**
 * Created by n!Ck
 * Date: 2022/8/24
 * Time: 09:18
 * Description:
 */

public class _654_MaximumBinaryTree {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 6, 0, 5};
        System.out.println(constructMaximumBinaryTree(nums));
    }

    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private static TreeNode dfs(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int index = getMaxIndex(nums, left, right);
        TreeNode node = new TreeNode(nums[index]);
        node.left = dfs(nums, left, index - 1);
        node.right = dfs(nums, index + 1, right);

        return node;
    }

    private static int getMaxIndex(int[] nums, int left, int right) {
        int max = left;
        for (int i = left + 1; i <= right; i++) {
            max = nums[i] > nums[max] ? i : max;
        }
        return max;
    }
}
