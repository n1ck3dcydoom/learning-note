package daimasuixianglu._1_array;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by n!Ck
 * Date: 2022/1/12
 * Time: 0:15
 * Description:
 */

public class _12_FruitIntoBaskets {

    public static void main(String[] args) {
        int[] fruits = new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};
        System.out.println(totalFruit(fruits));
    }

    public static int totalFruit(int[] fruits) {
        // 翻译过来题意就是求,数组中仅包含两个数或一个数的最长连续子数组长度
        int n = fruits.length;
        int res = 0;
        // 双指针
        int l = 0, r = 0;
        Map<Integer, Integer> hash = new HashMap<>();
        while (r < n) {
            int f = fruits[r++];
            // 当前水果种数不超过2种,扩大右端点继续放入
            hash.size();
            hash.put(f, hash.getOrDefault(f, 0) + 1);
            // 如果水果种数超过了2种,收缩左端点移除水果
            for (; hash.size() > 2; l++) {
                f = fruits[l];
                int cnt = hash.get(f) - 1;
                if (cnt == 0) {
                    hash.remove(f);
                } else {
                    hash.put(f, cnt);
                }
            }
            // 更新窗口大小
            res = Math.max(res, r - l);
        }
        return res;
    }
}
