package leetcode.pointoffer;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 统计一个数字在排序数组中出现的次数。
 * <p>
 *
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 * 示例2:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 *
 * <p>
 * 限制：
 * <p>
 * 0 <= 数组长度 <= 50000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/7/16 10:40
 **/
public class PointOffer_53 {

    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        System.out.println(search1(nums, 11));
    }


    /**
     * 朴素遍历
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * <p>
     * 未利用数组排序的特点
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > target) {
                break;
            }
            if (nums[i] == target) {
                res++;
            }
        }
        return res;
    }

    /**
     * 根据排序数组的特性，例用两次二分查找到第一次出现和最后一次出现的索引
     */
    public static int search1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int first = 0;
        int last = 0;

        int l = 0, r = n;
        // 寻找左边界，搜索区间是[l, r) 因为r不可能等于n，结束时的搜索区间[l, l)，搜索区间为空
        while (l < r) {
            int mid = l + (r - l) / 2;

            // 由于搜索区间是左闭右开[l, r)，在完成对mid的搜索后，应该要去掉mid
            // 即[mid+1, r) 或者 [l, mid)
            if (nums[mid] == target) {
                // 即使查找到target，也不要立即退出查找，继续缩小右边界，因为要查找到区间的左边界
                r = mid;
            } else if (nums[mid] > target) {
                r = mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            }
        }
        // 这里注意，由于初始化r=n，退出搜索的条件是l=r，所以会出现l=n的情况，在外面需要打个补丁特殊处理下
        // 如果l=0，表示第一个大于等于target的数就是nums[0]，要特殊处理nums中不存在target的情况
        if ((l == 0 && nums[0] != target) || l == n) {
            // 如果nums[0]不等于target，表示搜索左边界就不存在了，即nums中不存在target
            // l==n表示搜索时超出了右边界
            return 0;
        }
        // 记录第一个target出现的索引下标
        else {
            first = l;
        }

        l = 0;
        r = n;
        // 寻找右边界
        while (l < r) {
            int mid = l + (r - l) / 2;

            // 由于搜索区间是左闭右开[l, r)，在完成对mid的搜索后，应该要去掉mid
            // 即[mid+1, r) 或者 [l, mid)
            if (nums[mid] == target) {
                // 缩小左边界
                l = mid + 1;
            } else if (nums[mid] < target) {
                // 缩小左边界
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid;
            }
        }
        // 此时返回的最后一个小于等于target的数应该是l-1
        if ((l == n && nums[l - 1] != target) || l == 0) {
            return 0;
        } else {
            last = l - 1;
        }
        return last - first + 1;
    }
}