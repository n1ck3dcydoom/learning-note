package leetcode.sort.medium;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/4/12 21:19
 **/
public class _215_KthLargestElementInAnArray {

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1,5,6,4};
        System.out.println(findKthLargest(nums, 2));
    }

    public static int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return nums[partition(nums, 0, nums.length - 1, n - k)];
    }

    public static int partition(int[] nums, int i, int j, int target) {
        int pos = quickSort(nums, i, j);
        int n = nums.length;

        // 如果一趟快排后返回的下标等于n-k了，直接返回这个下标
        if (target == pos) {
            return pos;
        }
        // 左边递归快排
        if (pos > target) {
            return partition(nums, i, pos - 1, target);
        }
        // 右边递归快排
        else {
            return partition(nums, pos + 1, j, target);
        }
    }

    private static int quickSort(int[] nums, int i, int j) {
        if (i > j) {
            return nums[i];
        }

        int left = i;
        int right = j;

        // 左边第一个元素为哨兵
        int base = nums[left];

        // “选择性” 快速排序

        // 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
        // 第k大的数，从位置上来说，一定是出现在完全排序后的第n-k个位置上

        // 每次快排完成后，能够确定一个数的位置，如果与哨兵交换后的位置正好是第n-k个位置
        // 那么直接返回这个位置的元素即可，也不用关心剩下的数是否有序

        // 如果交换后的数位置不等于n-k，根据快排的原理，比nums[n-k]小的都在左边，大的都在右边
        // 此时只需要判断交换后的位置下标比n-k小，那么只需要递归左边即可，比n-k大，只需要递归右边即可，可以减少一半的计算量
        while (left < right) {
            // 从右往左开始找第一个比哨兵小的数
            while (nums[right] >= base && left < right) {
                right--;
            }
            // 从左往右开始找第一个比哨兵大的数
            while (nums[left] <= base && left < right) {
                left++;
            }
            // 如果left和right指针还没有相遇，交换位置继续寻找直到相遇位置
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
        // left和right相遇后，交换哨兵和相遇位置的数
        int temp = nums[left];
        nums[left] = base;
        nums[i] = temp;

        return left;
    }
}
