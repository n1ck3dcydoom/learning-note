package algorithm.leetcode.primeAlgorithm.day2;

public class _189_RotateArray {

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        // rotate0(nums, 3);
        rotate1(nums, 3);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    public static void rotate0(int[] nums, int k) {
        // 超时了
        int n = nums.length;
        // 直接旋转数组
        while (k > 0) {
            int head = nums[n - 1];
            for (int i = n - 1; i >= 1; i--) {
                nums[i] = nums[i - 1];
            }
            nums[0] = head;
            k--;
        }
    }

    public static void rotate1(int[] nums, int k) {
        int n = nums.length;
        // 使用额外数组保存旋转后的位置
        int[] newNums = new int[n];

        for (int i = 0; i < n; i++) {
            newNums[(i + k) % n] = nums[i];
        }
        for (int i = 0; i < n; i++) {
            nums[i] = newNums[i];
        }
    }
}
