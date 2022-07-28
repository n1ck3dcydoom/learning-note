package leetcode.hash.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by n!Ck
 * Date: 2022/7/28
 * Time: 19:30
 * Description:
 */

public class _1331_RankTransformOfAnArray {

    public static void main(String[] args) {
        int[] arr = new int[]{37, 12, 28, 9, 100, 56, 80, 5, 12};

        int[] res = arrayRankTransform(arr);
        for (int i : res) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
    }

    public static int[] arrayRankTransform(int[] arr) {
        int n = arr.length;
        // 复制一个新数组,排序
        int[] copy = Arrays.copyOf(arr, n);
        Arrays.sort(copy);
        // hash 保存排序后的元素和下标的对应关系
        Map<Integer, Integer> hash = new HashMap<>();
        int index = 1;
        for (int num : copy) {
            if (!hash.containsKey(num)) {
                hash.put(num, index++);
            }
        }
        for (int i = 0; i < n; i++) {
            arr[i] = hash.get(arr[i]);
        }
        return arr;
    }
}
