package daimasuixianglu._6_tree;

public class _38_ConvertSortedArrayBinarySearchTree {

    public TreeNode sortedArrayToBST(int[] nums) {
        // 递归函数的入参和返回值
        // 入参：输入一个数组
        // 出参：返回数组中构建好的根节点
        return build(nums, 0, nums.length);
    }

    /**
     * 使用索引访问数组
     */
    private TreeNode build(int[] nums, int from, int to) {
        // 坚持左闭右开原则
        // [from, to)为空区间
        if (from == to) {
            return null;
        }

        int len = to - from;
        // 偶数取右侧
        // int pos = len / 2 + from;
        // 偶数取左侧
        int pos = len % 2 == 0 ? len / 2 - 1 + from : len / 2 + from;
        // 构造根节点
        TreeNode root = new TreeNode(nums[pos]);

        // 切割数组
        // 左子树
        int leftStart = from, leftEnd = pos;
        // 右子树，排除掉pos元素
        int rightStart = pos + 1, rightEnd = to;

        // 构造左子树
        root.left = build(nums, leftStart, leftEnd);
        // 构造右子树
        root.right = build(nums, rightStart, rightEnd);

        return root;
    }
}
