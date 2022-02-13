package daimasuixianglu._6_tree;

import java.util.Arrays;

public class _27_MaximumBinaryTree {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 6, 0, 5};

        TreeNode node = constructMaximumBinaryTree1(nums);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        // 需要一个备忘录记录索引
        // Map<Integer, Integer> hash = new HashMap<>();
        // for (int i = 0; i < nums.length; i++) {
        // hash.put(nums[i], i);
        // }
        if (nums == null || nums.length == 0) {
            return null;
        }
        // 找最大值出现的位置
        int max = -1;
        int pos = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                pos = i;
                max = nums[i];
            }
        }
        // 创建根节点
        TreeNode root = new TreeNode(max);
        // 切割数组
        int[] left = Arrays.copyOfRange(nums, 0, pos);
        int[] right = Arrays.copyOfRange(nums, pos + 1, nums.length);
        // 递归构建左右孩子
        root.left = constructMaximumBinaryTree(left);
        root.right = constructMaximumBinaryTree(right);

        return root;
    }

    public static TreeNode constructMaximumBinaryTree1(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private static TreeNode dfs(int[] nums, int from, int to) {
        if (from > to) {
            return null;
        }
        // 优化数组的创建，不再通过copy新数组，而是通过from和to索引直接操作原数组
        int max = -1;
        int pos = -1;
        for (int i = from; i <= to; i++) {
            pos = nums[i] > max ? i : pos;
            max = Math.max(nums[i], max);
        }
        // 创建根节点
        TreeNode root = new TreeNode(max);
        // 创建左右孩子
        root.left = dfs(nums, from, pos - 1);
        root.right = dfs(nums, pos + 1, to);

        return root;
    }
}
