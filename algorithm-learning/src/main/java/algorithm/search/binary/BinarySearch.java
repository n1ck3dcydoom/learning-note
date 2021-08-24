package algorithm.search.binary;

import java.util.Objects;

/**
 * Created by n!Ck Date: 2018/10/7 Time: 14:29 Description: 二分查找要求待查找数组有序
 */
public class BinarySearch {
    public static Integer[] numbers = { 1, 3, 5, 9, 12, 23, 36, 46, 59, 62, 73, 81, 94, 100 };

    public static void main(String[] args) {
        boolean flag = binarySearch(numbers, 1);
        System.out.println(flag ? "yes" : "no");
    }

    public static boolean binarySearch(Integer[] numbers, Integer target) {
        int low = 0;
        int high = numbers.length - 1;
        int mid = (low + high) / 2;
        while (low <= high) {
            // 如果mid等于target，则返回查找成功
            if (Objects.equals(numbers[mid], target)) {
                return true;
            }
            // 如果mid小于target，则从大区间继续二分
            else if (numbers[mid] > target) {
                high = mid - 1;
                mid = (low + high) / 2;
            }
            // 如果mid大于target，则从小区间继续二分
            else {
                low = mid + 1;
                mid = (low + high) / 2;
            }
        }
        return false;
    }
}
