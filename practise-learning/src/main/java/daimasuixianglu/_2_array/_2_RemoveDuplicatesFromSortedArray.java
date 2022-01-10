package daimasuixianglu._2_array;

public class _2_RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        // int[] nums = new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
        // System.out.println(removeDuplicates(nums));

        int[] nums = new int[] { 1 };
        System.out.print(removeDuplicates(nums));
    }

    public static int removeDuplicates(int[] nums) {
        // 快慢指针原地删除重复元素
        // 慢指针永远指向更新后数组的尾部
        int s = 0, f = 0;
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        for (; f < n; f++) {
            // 快指针找到了和慢指针不相等的数,同步更新慢指针
            if (nums[f] != nums[s]) {
                nums[++s] = nums[f];
            }
            // 快指针和慢指针的数相等,只移动快指针继续往后找,直到和慢指针不相等
        }
        return s + 1;
    }
}
