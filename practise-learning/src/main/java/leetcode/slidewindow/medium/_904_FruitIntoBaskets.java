package leetcode.slidewindow.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by n!Ck
 * Date: 2022/10/20
 * Time: 23:47
 * Description:
 */

public class _904_FruitIntoBaskets {

    public static void main(String[] args) {
        System.out.println(totalFruit(new int[]{1, 2, 1}));
        System.out.println(totalFruit(new int[]{0, 1, 2, 2}));
        System.out.println(totalFruit(new int[]{1, 2, 3, 2, 2}));
        System.out.println(totalFruit(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}));
    }

    public static int totalFruit(int[] fruits) {
        // 1. 只有 2 个篮子,每个篮子只能装 1 种水果,但是可以放任意多个
        // 2. 一旦选定从某棵树上摘水果,后面就必须依次摘取,直到出现第三种水果
        // 简单抽象来说,就是求一个数组里面,仅仅包含两种或两种一下的子序列的最大长度

        // 滑动窗口遍历整个序列
        // 使用 map 记录当前窗口出现的水果种类和数量
        // 当种类小于等于 2 时,右端点右移,此时 map 加上加入的水果种类和数量
        // 当出现第 3 种水果时,左端点右移,此时 map 减去移除的水果种类和数量,直到种类再次小于等于 2 种

        int res = 0;
        int l = 0, r = 0;
        int n = fruits.length;
        Map<Integer, Integer> map = new HashMap<>();
        while (l < n && r < n) {
            // 右端点右移添加的新水果
            int addFruit = fruits[r];
            // 判断能否放入当前种类的水果,果篮没满(种树小于 2),果篮满了但是之前已经放过相同种类的水果
            if (map.size() < 2 || map.containsKey(addFruit)) {
                map.put(addFruit, map.getOrDefault(addFruit, 0) + 1);
                r++;
            } else {
                // 放不进去了,就需要收缩左端点
                // 左端点右移移除的水果
                int removeFruit = fruits[l];
                int left = map.get(removeFruit) - 1;
                if (left == 0) {
                    map.remove(removeFruit);
                } else {
                    map.put(removeFruit, left);
                }
                l++;
            }
            res = Math.max(res, r - l);
        }
        return res;
    }
}
