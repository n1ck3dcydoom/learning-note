package algorithm.search.binary;

import algorithm.sort.QuickSort0;

/**
 * Created by n!Ck
 * Date: 2019-02-14
 * Time: 14:37
 * Description:
 * 普通的二分查找，即没有任何其他限制条件，单纯的查找元素
 */
public class Normal {
    public static void main(String[] args) {
        // 二分查找要求线性表必须有序，所以先使用快排排序
        int[] array = new int[]{9, 2, 3, 5, 7, 1, 0, 6, 4, 8};
        QuickSort0.quickSort(array, 0, array.length - 1);
        // 查找元素2，应返回2
        System.out.println(binarySearch(array, 2));
        // 查找元素10，应返回-1
        System.out.println(binarySearch(array, 10));
    }

    /**
     * 这里二分查找如果查找成功，返回的是元素在线性表中的位置下标，而不是返回的元素本身的值
     * 如果查找失败则返回 -1
     *
     * @param array 待查找线性表
     * @param key   待查找元素
     * @return int 元素位置下标，-1表示未查找到
     */
    private static int binarySearch(int[] array, int key) {
        int low = 0;
        int high = array.length - 1;

        // 这里必须是 <= 如果换成 < 那么可能会造成元素的遗漏
        // 如果线性表只有一个元素的话 < 就不会查找到这个元素
        while (low <= high) {
            // 注意 low + high 可能会溢出
            int mid = low + (high - low) / 2;
            if (array[mid] == key) {
                return mid;
            } else if (array[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
