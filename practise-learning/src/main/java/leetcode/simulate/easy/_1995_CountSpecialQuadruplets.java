package leetcode.simulate.easy;

import java.util.HashMap;
import java.util.Map;

public class _1995_CountSpecialQuadruplets {

    public int countQuadruplets(int[] nums) {
        // 排序后暴力遍历
        // 不能排序
        // Arrays.sort(nums);
        int count = 0;
        int n = nums.length;
        for (int a = 0; a < n; a++) {
            for (int b = a + 1; b < n; b++) {
                for (int c = b + 1; c < n; c++) {
                    for (int d = c + 1; d < n; d++) {
                        if (nums[a] + nums[b] + nums[c] == nums[d]) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    public int countQuadruplets1(int[] nums) {
        // 枚举abc之后，d的取值范围在c < d < n当中，使用hash表统计区间nums[c+1] ~ nums[n-1]的个数
        int count = 0;
        int n = nums.length;
        // 从n-2开始倒序枚举c，这样d的取值范围每次新增一个nums[c+1]
        // c>=2，保证前面一定存在ab
        Map<Integer, Integer> hash = new HashMap<>();
        for (int c = n - 2; c >= 2; c--) {
            // 将d放入hash
            hash.put(nums[c + 1], hash.getOrDefault(nums[c + 1], 0) + 1);
            // 枚举a
            for (int a = 0; a < c - 1; a++) {
                // 枚举b
                for (int b = a + 1; b < c; b++) {
                    count += hash.getOrDefault(nums[a] + nums[b] + nums[c], 0);
                }
            }
        }
        return count;
    }

    public int countQuadruplets2(int[] nums) {
        int n = nums.length;
        // 考虑一维背包问题的dp建模
        // 定义dp[i][j]表示从前i个物品中，恰好装满容量为j的背包，不同的方案数

        // 多维背包问题
        // 这里多了个限制条件为使用k个物品，所以放到dp数组的后一维当中
        // 定义dp[i][j][k]表示从前i个物品当中，装满容量为j的背包，使用k个物品的不同方案数

        // 题意给出的1 <= nums[i] <= 100
        int[][][] dp = new int[n + 1][100 + 1][n + 1];

        // 初始状态
        // 从前0个物品，装入容量为0的背包，使用0个物品的方案有1中
        dp[0][0][0] = 1;

        // 状态转移
        // 考虑第i个物品要不要放入背包当中
        // 如果前面已经使用了k个物品个数，那么第i个物品也放不进去
        // 如果前面剩余的背包容量装不下第i个物品，那么第i个物品也放不进去
        // 不放入第i个物品，有dp[i][j][k]=dp[i-1][j][k]
        // 放入第i个物品，有dp[i][j][k]=dp[i-1][j-nums[i]][k-1]

        // 根据题意求nums[a]+nums[b]+nums[c]==nums[d]，即从前i个物品中取3个物品装入容量为nums[d]的背包
        // 即dp[i][nums[i]][3]的所有结果之和，3 <= i < n，由于限制了a<b<c，只能从第3个物品开始枚举

        // 枚举物品，下标从1开始到0，dp数组的定义n+1预留了0的位置
        for (int i = 3; i <= n; i++) {
            // 物品容量
            int v = nums[i - 1];
            // 枚举背包
            for (int j = 0; j <= 101; j++) {
                // 枚举物品个数，k限制了最大为3
                for (int k = 0; k < 4; k++) {
                    // 第i个物品不装入背包
                    dp[i][j][k] = dp[i - 1][j][k];
                    // 第i个物品能够装入背包，前面i-1个物品至少需要j-v的容量，前面最多只能用k-1个物品
                    if (j - v >= 0 && k - 1 >= 0) {
                        dp[i][j][k] += dp[i - 1][j - v][k - 1];
                    }
                }
            }
        }
        // 统计结果dp[i][nums[i]][3]
        int count = 0;
        for (int i = 3; i <= n; i++) {
            count += dp[i][nums[i]][3];
        }
        return count;
    }
}
