package leetcode.doublepointer.medium;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * 示例 2：
 * <p>
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：[0]
 * 示例 4：
 * <p>
 * 输入：nums = [1]
 * 输出：[1]
 *  
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-colors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/5/17 20:00
 **/
public class _75_SortColors {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        sortColors(nums);
    }

    public static void sortColors(int[] nums) {
        // 遍历两遍，第一遍把所有0放到前面，第二次把所有1放到0的后面，剩下的就全部是2了

        // 保存上一次交换后的位置
        int index = 0;

        // 第一次遍历，找到所有的0，并放到数组最前面
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                // 和index交换位置，并且index后移
                swap(nums, i, index++);
            }
        }
        // 第二次遍历，找到所有的1，放到0的后面，剩下的全部就是2了
        for (int i = index; i < nums.length; i++) {
            if (nums[i] == 1) {
                swap(nums, i, index++);
            }
        }
    }

    private static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

}