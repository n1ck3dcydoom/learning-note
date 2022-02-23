package daimasuixianglu._9_dp;

public class _10_TargetSum {

    public int findTargetSumWays(int[] nums, int target) {
        // 每个数可以+或者-，那么按照+和-进行分类，有left（全是+）-right（全是-）=target
        // 化简后有left-right=target，又有left+right=sum，即right=sum-left
        // 代入后又left-(sum-left)=target，化简后又2*left=target+sum
        // 即left=(target-sum)/2
        // 转化为01背包问题，即从nums中选取若干个数，恰好装满容量为(target+sum)/2的背包

        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 如果(target+sum)/2不能整除，则返回0
        if ((target + sum) % 2 != 0) {
            return 0;
        }
        // 如果target比sum还大或者比-sum还小，也返回0
        if (Math.abs(target) > sum) {
            return 0;
        }
        // 背包容量
        int v = (target + sum) / 2;

        // 第一步、定义dp数组
        // 定义dp[i][j]表示前i个物品，恰好装入容量为j的背包的不同种数
        int[][] dp = new int[n + 1][v + 1];

        // 第二步、初始状态
        // 没有物品“恰好”装满容量为0的背包的方法种数有1种
        dp[0][0] = 1;

        // 第三步、状态转移
        // 这里dp数组的定义与常规的01背包问题有所不同
        // 常规01背包问题的dp表示装满后能够得到的最大价值
        // 而这里的dp数组表示的是装满容量为j的背包的不同种数
        // 如果不选则物品i，前i-1个物品已经装满了容量为j的背包，那么有dp[i][j]=dp[i-1][j]
        // 如果选择物品i，前i-1个物品有已经装满了容量为j-w[i]的背包，那么有dp[i-1][j-w[i]]种不同的方法
        // 综上所述 { dp[i-1][j] ，不装入物品i j < nums[i]
        // dp[i][j]={ dp[i-1][j] + dp[i-1][j-w[i]] 装入物品i加上不装入物品i j >= nums[i]

        // 遍历物品
        for (int i = 1; i <= n; i++) {
            // 遍历背包，从容量为0开始遍历
            for (int j = 0; j <= v; j++) {
                // 不装
                if (j < nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                }
                // 装入
                else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][v];
    }
}
