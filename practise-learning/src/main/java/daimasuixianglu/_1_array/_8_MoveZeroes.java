package daimasuixianglu._1_array;

public class _8_MoveZeroes {

    public static void main(String[] args) {
        int[] nums = new int[] { 0, 1, 0, 3, 12 };
        moveZeroes(nums);
    }

    public static void moveZeroes(int[] nums) {
        // // 快慢指针,逆序遍历
        // int n = nums.length;
        // int s = n - 1, f = n - 1;
        // for (; f >= 0; f--) {
        // // 快指针找到了0,把他和慢指针交换
        // if (nums[f] == 0) {
        // int temp = nums[s];
        // nums[s] = nums[f];
        // nums[s--] = temp;
        // // nums[s--] = nums[f];
        // }
        // }

        // 快慢指针
        int n = nums.length;
        int s = 0;
        for (int f = 0; f < n; f++) {
            // 快指针往后面找0
            // 慢指针永远指向第一个0
            if (nums[f] != 0) {
                int temp = nums[s];
                nums[s++] = nums[f];
                nums[f] = temp;
                // nums[s++] = nums[f];
            }
        }
        System.out.print(1);
    }
}
