// package daimasuixianglu._9_dp;
//
// public class _8_PartitionEqualSubsetSum {
//
//     public boolean canPartition(int[] nums) {
//         // 将集合nums划分为两个和相等的子集合，且每个数只能用一次
//         // 转换为从n个数里面取若干个数，使得这若干个数的和等于sum/2
//         // 转换为01背包问题，即从n个物品里面，刚好装满容量为sum/2的背包，dp数组的类型是boolean
//
//         int n = nums.length;
//         int sum = 0;
//         for (int num : nums) {
//             sum += num;
//         }
//         if (sum % 2 == 1) {
//             return false;
//         }
//
//         // 第一步、定义dp数组
//         // 定义dp[i][j]表示使用前i个物品能否刚好装入容量为j的背包
//         // 物品就是集合nums里面的数，背包容量就是sum/2
//         boolean[][] dp = new boolean[n + 1][(sum / 2) + 1];
//
//         // 第二步、初始状态
//         dp[0][0] = 0;
//         // 第一行，没有物品装入容量为j的背包
//         for (int j = 1; j <= (sum / 2); j++) {
//             dp[0][j] = false;
//         }
//         // 第一列，物品0~i装入容量为0的背包
//         // 只要不装入物品，刚好能够放到容量为0的背包
//         for (int i = 1; i <= n; i++) {
//             dp[i][0] = true;
//         }
//
//         // 第三步、状态转移
//         // 根据01背包问题有：
//         // dp[i][j]=max(dp[i-1][j], dp[i-1][j-w[i]]+v[i])
//         // 如果不选择物品i
//         // 如果前i-1个物品能刚好满足容量j的背包，则前i个物品也可以
//         // 如果前i-1个物品不能够刚好满足容量j的背包，则前i个物品也不能（反正都不选择物品i）
//         // 即dp[i][j]=dp[i-1][j]
//         // 如果选择物品i
//         // 如果前i-1个物品刚好能够满足容量为j-w[i]的背包，那么装下第i个物品，也能够满足容量为j的背包
//         // 如果前i-1个物品不能够刚好装满容量为j-w[i]的背包，那么装下第i个物品，也不能够满足容量为j的背包
//         // 即dp[i][j]=dp[i-1][j-w[i]]
//
//         // 先遍历物品
//         for (int i = 1; i <= n; i++) {
//             // 再遍历背包
//             for (int j = 1; j <= (sum / 2); j++) {
//                 // 如果物品i不能放入当前背包
//                 if (j - nums[i - 1] < 0) {
//                     dp[i][j] = dp[i - 1][j];
//                 } else {
//                     // 如果能够装下，则考虑或的关系
//                     // 不装能满足，或者装了能满足，满足一个都可以
//                     dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
//                 }
//             }
//         }
//         return dp[n][sum / 2];
//     }
//
//     public boolean canPartition1(int[] nums) {
//         // 压缩空间到1维
//         int n = nums.length;
//         int sum = 0;
//         for (int num : nums) {
//             sum += num;
//         }
//         if (sum % 2 == 1) {
//             return false;
//         }
//         boolean[] dp = new boolean[(sum / 2) + 1];
//         dp[0] = true;
//         // 遍历物品
//         for (int i = 1; i <= n; i++) {
//             // 遍历背包，必须逆序！
//             // 因为dp[i]依赖于上一层dp[i-1]的结果
//             // 如果正序会修改当前dp[i]，导致后面dp[j]读取了当前层的dp[i]的结果，而非上一层dp[i-1]的结果
//             // 如果逆序修改当前dp[i]，读取的前面dp[k]仍然是上一层dp[i-1]的结果，符合状态转移方程的推导
//             for (int j = sum / 2; j >= 1; j--) {
//                 // 装不下
//                 if (j < nums[i - 1]) {
//                     dp[j] = dp[j];
//                 } else {
//                     dp[j] = dp[j] || dp[j - nums[i - 1]];
//                 }
//             }
//         }
//         return dp[sum / 2];
//     }
// }
