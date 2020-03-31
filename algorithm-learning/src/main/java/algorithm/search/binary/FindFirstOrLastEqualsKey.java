package algorithm.search.binary;

/**
 * Created by n!Ck
 * Date: 2019-02-14
 * Time: 14:55
 * Description:
 * 带有限制条件的二分查找（二分查找的变种）
 * 查询第一个与key相等的元素
 */
public class FindFirstOrLastEqualsKey {
    private static final String FIRST = "first";
    private static final String LAST = "last";

    public static void main(String[] args) {
        int[] array = new int[]{0, 1, 1, 1, 3, 5, 5, 5, 7, 9};
        // 查询第一个元素1，应返回1
        System.out.println(findFirstOrLastEqualsKey(array, 1, FIRST));
        // 查询最后一个元素5，应返回7
        System.out.println(findFirstOrLastEqualsKey(array, 5, LAST));
    }

    private static int findFirstOrLastEqualsKey(int[] array, int key, String pos) {
        int low = 0;
        int high = array.length - 1;
        int mid;

        if (FIRST.equals(pos)) {
            while (low <= high) {
                // 注意 low + high 可能会溢出
                mid = low + (high - low) / 2;
                // 如果是查找第一个出现的位置，这里必须让high减小到low
                if (array[mid] >= key) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            if (low < array.length && array[low] == key) {
                return low;
            }
        }
        if (LAST.equals(pos)) {
            while (low <= high) {
                // 注意 low + high 可能会溢出
                mid = low + (high - low) / 2;
                // 如果是查找最后一个出现的位置，这里必须让low增大到high
                if (array[mid] <= key) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            if (high >= 0 && array[high] == key) {
                return high;
            }
        }
        return -1;
    }
}
