package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/18
 * Time: 14:19
 * Description:
 */

public class _47_寻找第K大个数 {

    public int findKth(int[] a, int n, int K) {
        // 先来一趟快速排序
        int index = quick(a, 0, n - 1);
        // 如果当前快排返回的基准位置正好是第 K 大的元素，直接返回
        // 第 K 大的数在下标的体现就是第 n - K 个元素
        while (index != n - K) {
            if (index > n - K) {
                // 在左侧继续搜索
                index = quick(a, 0, index - 1);
            } else {
                // 在右侧继续搜索
                index = quick(a, index + 1, n - 1);
            }
        }
        return a[index];
    }

    private static int quick(int[] arr, int l, int r) {
        if (l > r) {
            return -1;
        }
        // 待排序数组的第一个元素当做哨兵
        int tmp = arr[l];
        int i = l, j = r;
        // 在 [l, r] 从左往右找第一个比哨兵大的元素，从右往左找第一个比哨兵小的元素，然后交换，直到 i == j
        while (i < j) {
            // 实际查找时，先从右往左查找第一个比哨兵小的元素
            while (i < j && tmp <= arr[j]) {
                j--;
            }
            // 从左往右查找第一个比哨兵大的元素
            while (i < j && tmp >= arr[i]) {
                i++;
            }
            // 交换 i 和 j
            swap(arr, i, j);
        }
        if (i == j) {
            // 相遇之后和哨兵交换
            swap(arr, l, i);
        }
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
