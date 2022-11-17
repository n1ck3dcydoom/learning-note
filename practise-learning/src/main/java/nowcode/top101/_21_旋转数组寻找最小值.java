package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 16:57
 * Description:
 */

public class _21_旋转数组寻找最小值 {

    public int minNumberInRotateArray(int[] array) {
        // 原数组有序,旋转后两部分仍然是有序的
        // 二段性,mid 和 右端点 r
        // 如果 mid > r ,右端点是右半部分的最大值,所以旋转点一定在 mid 右侧,l = mid+1
        // 如果 mid < r ,旋转点可能是 mid 也可能在 mid 左侧,所以 r = mid 不能-1,否则就会漏掉了 mid
        // 如果 mid == r,无法判断旋转点的位置,缩小 r 边界, r = r-1

        int l = 0, r = array.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (array[mid] == array[r]) {
                r--;
            } else if (array[mid] > array[r]) {
                l = mid + 1;
            } else if (array[mid] < array[r]) {
                // mid 可能是最小值，也可能不是最小值
                r = mid;
            }
        }
        // 返回循环中不变的端点，因为跳出循环的时候是由改变的端点导致的
        return array[r];
    }
}
