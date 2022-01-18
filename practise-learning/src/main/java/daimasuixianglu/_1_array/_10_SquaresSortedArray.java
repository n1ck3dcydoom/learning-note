package daimasuixianglu._2_array;

public class _10_SquaresSortedArray {

    public static void main(String[] args) {
        int[] nums = new int[] { -4, -1, 0, 3, 10 };
        int[] res = sortedSquares(nums);
        for (int n : res) {
            System.out.println(n + " ");
        }
    }

    public static int[] sortedSquares(int[] nums) {
        // 双指针,从两端往里找,更大的完全平方数肯定来自于两端的较大者
        int n = nums.length;
        int[] res = new int[n];
        int pos = n - 1;
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int ll = nums[l] * nums[l];
            int rr = nums[r] * nums[r];
            if (ll >= rr) {
                res[pos--] = ll;
                l++;
            } else {
                res[pos--] = rr;
                r--;
            }
        }
        return res;
    }
}
