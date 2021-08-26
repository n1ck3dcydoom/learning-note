package algorithm.leetcode.primeAlgorithm.day3;

public class _283_MoveZeroes {

    public static void main(String[] args) {
        int[] nums = new int[] { 0, 0, 0 };
        moveZeroes0(nums);
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }

    /**
     * 对0进行冒泡
     */
    public static void moveZeroes0(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                for (int j = i; j < n - 1; j++) {
                    int temp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                }
                // 冒完一次后，最后一个位置已经确定是0了，n=n-1
                n--;
            }
            if (nums[i] == 0) {
                i--;
            }
        }
    }

    public static void moveZeroe1(int[] nums) {
        // 双指针
        // 左指针指向处理好的序列尾部，右指针右移找到非0的数，然后交换做右指针
        if (nums == null || nums.length == 0) {
            return;
        }
        int n = nums.length;
        int l = 0, r = 0;
        while (r < n) {
            // 右指针不断右移找非0的数，直到到达数组末尾
            if (nums[r] != 0) {
                int temp = nums[r];
                nums[r] = nums[l];
                nums[l] = temp;
                // 交换后，左指针后移，此时左指针前面的数都是处理好的序列
                l++;
            }
            // 右指针不断右移
            r++;
        }
    }
}
