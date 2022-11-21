package nowcode.top101;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by n!Ck
 * Date: 2022/11/19
 * Time: 12:45
 * Description:
 */

public class _54_三数之和 {

    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        // 检查是否满足三元组条件
        if (num.length < 3) {
            return res;
        }
        // num 排序
        Arrays.sort(num);
        // 固定一个元素,使用双指针搜索后面两个元素
        // 三元组 i 最多搜索到 n-3 的位置
        int n = num.length;
        for (int i = 0; i <= n - 3; i++) {
            // 跳过重复的元素
            if (i > 0 && num[i] == num[i - 1]) {
                continue;
            }
            int l = i + 1;
            int r = n - 1;
            // 三元组 l 和 r 不能相等,否则只有两个元素了
            while (l < r) {
                int sum = num[i] + num[l] + num[r];
                if (sum == 0) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(num[i]);
                    list.add(num[l]);
                    list.add(num[r]);
                    res.add(list);
                    // 对 l 和 r 去重,跳过 -4 1 2 2 2 3 3 3 当中重复的 [-4,2,2] 和 [-4 1 3]
                    while (l + 1 < r && num[l] == num[l + 1]) {
                        l++;
                    }
                    while (l < r - 1 && num[r] == num[r - 1]) {
                        r--;
                    }
                    // 找到一组三元组之后同时收缩两端指针
                    l++;
                    r--;
                } else if (sum > 0) {
                    // 三元组比 0 大,收缩右指针减小三元组的和
                    r--;
                } else if (sum < 0) {
                    // 三元组比 0 小,收缩左指针增大三元组的和
                    l++;
                }
            }
        }
        return res;
    }
}
