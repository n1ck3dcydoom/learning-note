package daimasuixianglu._1_array;

/**
 * Created by n!Ck
 * Date: 2022/1/6
 * Time: 23:14
 * Description: 二分查找
 */

public class _1_BinarySearch {

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
    }

    private static int binarySearch(int[] nums, int target) {
        // 二分查找
        int n = nums.length;
        // 这里决定了搜索区间是[l,r]，因为r的取值是n-1，即数组的最后一个元素
        // [l,r]右闭区间才能覆盖到最后一个元素

        // 如果r=n，那么搜索区间就变成了[l,r)，因为r=n的索引并不存在，所以右端点是开区间
        int l = 0;
        int r = n - 1;

        // 上面的搜索区间[l,r]决定了这里是<还是<=
        // 当l==r时，搜索区间[l,r]还有一个数l，是有意义的，所以仍然需要对[l,r]做二分搜索
        // 搜索区间[l,r)则会漏掉最后一个数l
        // 所以当搜索区间是[l,r]时，二分查找的退出条件是l>r，这样结束时搜索区间是[r+1,r]已经没有元素了
        while (l <= r) {
            // 防止越界的基本操作
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 这里的l和r究竟要不要做+1和-1操作
            // 因为当前的nums[mid]一定不等于t，相当于已经访问过mid的位置了
            // 如果l=mid或者r=mid，那么后面可能会再次搜索到mid的位置
            // 所以需要l=mid+1，r=mid-1
            else if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            }
        }
        return -1;
    }
}
