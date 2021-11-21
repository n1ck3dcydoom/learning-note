package leetcode.easy.array;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2020/7/7 17:50
 **/
public class _189_RotateArray {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        rotate1(nums, 3);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    /**
     * 使用额外数组拷贝
     *
     * @param nums
     * @param k
     */
    public static void rotate1(int[] nums, int k) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int offset = i + k < nums.length ? i + k : i + k - nums.length;
            res[offset] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = res[i];
        }
    }

    /**
     * 环状拷贝
     * 有多少个元素，就有多少次置换
     *
     * @param nums
     * @param k
     */
    //    public static void rotate2(int[] nums, int k) {
    //        int n = nums.length;
    //        int count = 0;
    //        // n个元素需要交换n次
    //        for (int i = 0; count < n; i++) {
    //            // 从当前位置开始交换
    //            int start = i;
    //            // 保存当前位置的元素
    //            int temp = nums[start];
    //            // 开始交换
    //            do {
    //                // 获取当前位置的偏移量
    //                int offset = (start + k) % n;
    //                // 保存偏移位置的元素
    //                int temp0 = nums[offset];
    //                // 交换当前位置和便宜位置的元素
    //                nums[offset] = temp;
    //            }
    //        }


    //        // 置换次数
    //        int count = 0;
    //        // 初始位置
    //        int start = 0;
    //        // 置换偏移量
    //        int offset = 0;
    //        // 当交换次数等于元素个数时完成置换
    //        while (count < nums.length) {
    //            // 从位置0开始置换
    //            int current = start;
    //            // 得出下次置换的偏移量
    //            offset = (current + k) % nums.length;
    //            // 保存置换出的元素
    //            int temp = nums[offset];
    //            // 置换元素
    //            nums[offset] = nums[current];
    //            nums[current] = temp;
    //            cu
    //            count++;
    //        }
    //    }
}