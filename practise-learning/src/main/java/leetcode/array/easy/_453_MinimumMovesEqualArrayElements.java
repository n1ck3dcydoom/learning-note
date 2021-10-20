package leetcode.array.easy;

public class _453_MinimumMovesEqualArrayElements {

    public int minMoves(int[] nums) {
        // 每次让n-1个数加1，等价于让1个数减1
        // 最后每个数都相等，只能都等于最小的数
        int min = Integer.MAX_VALUE;
        for (int i : nums) {
            min = Math.min(i, min);
        }
        int res = 0;
        for (int i : nums) {
            res += i - min;
        }
        return res;
    }
}