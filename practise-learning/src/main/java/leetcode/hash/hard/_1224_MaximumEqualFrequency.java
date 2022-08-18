package leetcode.hash.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by n!Ck
 * Date: 2022/8/18
 * Time: 09:12
 * Description:
 */

public class _1224_MaximumEqualFrequency {

    public static void main(String[] args) {
        // System.out.println(maxEqualFreq(new int[]{2, 2, 1, 1, 5, 3, 3, 5})); // 7
        // System.out.println(maxEqualFreq(new int[]{1, 1, 2, 2, 3, 3, 4, 4, 5})); // 9
        // System.out.println(maxEqualFreq(new int[]{1, 1, 1, 1, 1, 1, 1, 1})); // 8
        System.out.println(maxEqualFreq(new int[]{1, 2, 3, 1, 2, 3, 4, 4, 4, 4, 1, 2, 3, 5, 6})); // 13
    }

    public static int maxEqualFreq(int[] nums) {
        // 统计每个数字出现的次数
        Map<Integer, Integer> count = new HashMap<>();
        // 统计某个频次出现的数字的个数
        // 例如 1,2,2,3,3,4
        // map = [1,2] 出现 1 次的数字有 1 和 4 共 2 个
        //     = [2,2] 出现 2 次的数字有 2 和 3 共 2 个
        Map<Integer, Integer> freq = new HashMap<>();
        // 记录出现的最大频次数
        // 例如 1,1,2,2,3,3,4,4,4
        // maxFreq = 3
        int maxFreq = 0;
        // 结果
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            // 判断当前数字是否已经出现过?
            // 如果已经出现过,则要调整 freq
            if (count.getOrDefault(nums[i], 0) > 0) {
                // 如果某个数字已经出现过了,则那个数字之前出现的频率就要 -1
                // 例如 1,2,2,3,3,4,3
                // 当遍历到 [1,2,2,3,3,4] 时,freq = [1,2][2,2] 出现 1 次的有 1 和 4,出现 2 次的有 2 和 3
                // 当遍历到 [1,2,2,3,3,4,3] 时,其中 3 出现的次数已经由 2 次变为 3 次,所以之前 3 的频次 2 需要 -1,因为 3 的频次已经升级到 3 次了
                // 即 freq = [1,2][2,1][3,1] 出现 1 次的有 1 和 4,出现 2 次的有 2,出现 3 次的有 3
                freq.put(count.get(nums[i]), freq.get(count.get(nums[i])) - 1);
                // 这里不用 getOrDefault() 因为肯定已经出现了
            }
            // 更新 count 当前数字频次+1
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
            // 更新 freq 当前数字的频次
            freq.put(count.get(nums[i]), freq.getOrDefault(count.get(nums[i]), 0) + 1);
            // 更新某个数字出现的最大频次
            maxFreq = Math.max(maxFreq, count.get(nums[i]));

            // 判断当前前缀是否满足条件

            // 1. 当前前缀出现的所有数字的最大频率都只有 1
            // 例如 1,2,3,4,5,6,7
            // 此时随便删掉一个数都满足题意
            if (maxFreq == 1) {
                res = i + 1;
            }
            // 2. 当前前缀只出现过 1 种数字
            // 例如 1,1,1,1,1,1,1,1
            // 此时随便删掉一个数都满足题意
            else if (freq.get(maxFreq) == 1 && count.size() == 1) {
                res = i + 1;
            }
            // 3. 有 1 个数出现次数为 1,其他数出现次数都是 maxFreq,删掉出现次数为 1 的这个数即可
            else if (freq.get(maxFreq) * maxFreq + 1 == i + 1 && freq.get(1) == 1) {
                // freq.get(maxFreq) 出现 maxFreq 次数的数字个数 * maxFreq 的频率 + 只出现过 1 次的数字
                // = 当前已经遍历过的数字个数(i 表示下表,遍历的数字个数为 i+1)
                res = i + 1;
            }
            // 4. 有 n-1 个数出现了 maxFreq -1 次,有 1 个数出现了 maxFreq 次,删掉 1 个出现了 maxFreq 次的数
            // 例如 1,1,1,2,2,2,3,3,3,4,4,4,4  maxFreq=4,有 4 出现了 4 次, maxFreq-1=3 有 1,2,3 分别出现了 3 次,
            else if (freq.get(maxFreq - 1) * (maxFreq - 1) + maxFreq == i + 1) {
                res = i + 1;
            }
        }
        return res;
    }
}
