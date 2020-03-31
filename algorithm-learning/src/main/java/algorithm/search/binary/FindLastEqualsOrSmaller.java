package algorithm.search.binary;

/**
 * Created by n!Ck
 * Date: 2019-02-14
 * Time: 15:27
 * Description:
 * 查询最后一个等于或者小于key的元素
 * 也就是查询线性表里有多少个等于或者小于key的元素
 */
public class FindLastEqualsOrSmaller {
    public static void main(String[] args) {
        int[] array = new int[]{0, 1, 1, 1, 3, 5, 5, 5, 7, 9};
        // 查找元素6，应当返回7
        System.out.println(findLastEqualsOrSmaller(array, 6));
        // 查找元素2，应当返回3
        System.out.println(findLastEqualsOrSmaller(array, 2));
        // 查找元素1，应当返回3
        System.out.println(findLastEqualsOrSmaller(array, 1));
    }

    private static int findLastEqualsOrSmaller(int[] array, int key) {
        int low = 0;
        int high = array.length - 1;
        int mid;

        while (low <= high) {
            // 注意 low + high 可能会溢出
            mid = low + (high - low) / 2;

            if (array[mid] > key) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }
}
