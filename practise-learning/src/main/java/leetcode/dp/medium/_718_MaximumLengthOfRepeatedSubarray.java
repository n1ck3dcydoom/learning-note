package leetcode.dp.medium;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给两个整数数组 A 和B，返回两个数组中公共的、长度最长的子数组的长度。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出：3
 * 解释：
 * 长度最长的公共子数组是 [3, 2, 1] 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/2/19 21:44
 **/
public class _718_MaximumLengthOfRepeatedSubarray {

    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 3, 2, 1};
        int[] B = new int[]{3, 2, 1, 4, 7};
        System.out.println(findLength3(A, B));
    }

    public int findLength(int[] A, int[] B) {
        // 朴素dp思路

        // 从i开始思考
        // 假设有这么一个dpi表示数组A和数组B分别从 “某个” 位置开始的最长公共子数组长度
        // 那么dp数组里面如何体现：数组A  数组B  从具体 “某个” 位置开始呢？
        // 这里就是dp数组的设计思路，为了能够保存两个数组AB的起始位置
        // 需要两个变量i、j分别记录A和B的起点能够找到最长公共子数组
        // 即需要定义二维数组dp[i][j]表示数组A从i开始，数组B从j开始能够满足题意的最长公共子数组长度

        // 问题来了，通常dp都是由i-1推导出i的，即当前项的结果由前一项或者若干项决定
        // 如果dp[i][j]表示从AB “开始” 的最长长度，从ij才表示“开始”的长度，前面i-1、j-1一定是不等于的
        // 那么说明dp[i-1][j-1]肯定和当前最长长度无关，既然前面的项跟当前项都无关，说明dp数组定义得有问题
        // 既然开头定义失败了，那么转化为数组A以i “结尾”、数组B以j “结尾” 的最长公共子数组长度
        // 当i和j能够构成重复子串，需要考虑i-1和j-1能否构成重复子串，如果可以则i-1和j-1的dp值加上i和j的1就等于i和j的dp值
        // 为何只考虑i-1和j-1，而不是i-1和j-2呢，因为需要连续每次往前两个数组只能同时倒退1步，否则就不是连续子串了

        int n = A.length;
        int m = B.length;
        // 第一步，定义dp数组
        // 定义dp[i][j]表示数组A以i结尾，数组B以j结尾的最长公共子数组长度
        int[][] dp = new int[n][m];

        // 第二步，初始值
        // 考虑边界值的情况，既然i和j是由i-1和j-1推导出来的
        // 如果i=j=0，即数组AB的第一个相等，他们不可能从-1推到出来的
        // 那么这里的i和j等于0就是base case了
        // 如果i=j，那么dp[i][j]=1，否则等于0
        if (A[0] == B[0]) {
            dp[0][0] = 1;
        } else {
            dp[0][0] = 0;
        }
        // TODO 经验之谈，通常二维dp里面 dp[i][0]和dp[0][j]
        // TODO 即第一行和第一列通常都是base case，而非仅仅一个dp[0][0]
        // 把A当作纵轴，B当作横轴
        // 初始化第一列
        for (int i = 0; i < n; i++) {
            if (A[i] == B[0]) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = 0;
            }
        }
        // 初始化第一行
        for (int i = 0; i < m; i++) {
            if (A[0] == B[i]) {
                dp[0][i] = 1;
            } else {
                dp[0][i] = 0;
            }
        }

        // 第三步，选择，即递推表达式
        // 通过第一步的分析，以i结尾和j结尾的A[i]B[j]如果不能够组成公共子数组
        // 那么dp[i][j]=0，因为公共子数组的结尾必须要相同（毕竟公共，不同就不能够叫成公共了）
        // 如果A[i] == B[j]，则以他们结尾的公共子数组长度至少为1，他们本身
        // 考虑其前面连续的1项i-1和j-1，如果A[i-1]B[j-1]也能够组成公共子数组
        // 那么以i和j结尾的最长长度，可以通过以i-1和j-1的最长长度加上其本身1得到
        // 即 dp[i][j] = dp[i-1][j-1] + 1  (A[i] == B[j])
        //    dp[i][j] = 0                 (A[i] != B[j])

        // 遍历A数组和遍历B数组的先后顺序不影响
        // 第一行和第一列作为base case已经初始化了，所以从1开始遍历

        // 需要注意的是，通常的dp都是最后一项为结果
        // 这里的最长公共子数组不一定是两个数组AB的最后一项构成
        // 所以需要定义一个临时变量保存中间找的每一个公共子数组的最大长度
        int res = 0;

        // 如果把B当作横轴，A当作纵轴
        // 需要先遍历每行，每行里面再遍历每列
        // 即要求先遍历A数组，在里面遍历B数组
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (A[i] == B[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return res;
    }

    public static int findLength1(int[] A, int[] B) {
        // 上述普通dp可以看作是一个填表过程
        //   B 3 2 1 4 7
        // A 1 0 0 1 0 0
        //   2 0 1 0 0 0
        //   3 1 0 0 0 0
        //   2 0 2 0 0 0
        //   1 0 0 3 0 0

        // 从每次遍历A数组的时候可以发现，其实遍历当前行的时候，之和上一行的数据相关
        // 参考 洛谷P1048 可以通过滚动数组优化空间复杂度到O(2)
        int n = A.length;
        int m = B.length;
        int[][] dp = new int[2][m];

        // 初始化第1行
        for (int i = 0; i < m; i++) {
            if (A[0] == B[i]) {
                dp[0][i] = 1;
            } else {
                dp[0][i] = 0;
            }
        }

        // 选择遍历
        int res = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i] == B[j]) {
                    if (j == 0) {
                        dp[i % 2][j] = 1;
                    } else {
                        dp[i % 2][j] = dp[(i + 1) % 2][j - 1] + 1;
                    }
                    res = Math.max(res, dp[i % 2][j]);
                } else {
                    dp[i % 2][j] = 0;
                }
            }
        }
        return res;
    }

    public static int findLength2(int[] A, int[] B) {
        // 从每次遍历A数组的时候可以发现，其实遍历当前行的时候，之和上一行的数据相关
        // 参考 洛谷P1048 可以通过一维数组直接优化到O(1)
        int n = A.length;
        int m = B.length;
        int[] dp = new int[m];

        // 初始化第1行
        for (int i = 0; i < m; i++) {
            if (A[0] == B[i]) {
                dp[i] = 1;
            } else {
                dp[i] = 0;
            }
        }
        // 开始遍历
        int res = 0;
        for (int i = 1; i < n; i++) {
            // 顺序遍历会覆盖上一次的记录，导致dp[j]=dp[j-1]+1中的dp[j-1]不再是上一行的数据
            // 这里需要倒叙遍历B数组
            for (int j = m - 1; j >= 0; j--) {
                if (A[i] == B[j]) {
                    if (j == 0) {
                        dp[j] = 1;
                    } else {
                        dp[j] = dp[j - 1] + 1;
                    }
                    res = Math.max(res, dp[j]);
                } else {
                    dp[j] = 0;
                }
            }
        }
        return res;
    }

    public static int findLength3(int[] A, int[] B) {
        // 滑动窗口
        // 第一次：         1 2 3 2 1
        //         3 2 1 4 7       3 2 1 4 7
        // 第二次：       1 2 3 2 1
        //         3 2 1 4 7
        // 第三次：     1 2 3 2 1
        //         3 2 1 4 7
        // 第四次：   1 2 3 2 1
        //         3 2 1 4 7
        // 第五次： 1 2 3 2 1
        //         3 2 1 4 7
        // 第六次： 1 2 3 2 1
        //           3 2 1 4 7
        // 第七次： 1 2 3 2 1
        //             3 2 1 4 7
        // 第八次： 1 2 3 2 1
        //               3 2 1 4 7
        // 第九次： 1 2 3 2 1
        //                 3 2 1 4 7

        int n = A.length;
        int m = B.length;

        // 优先固定长的数组，从短的数组开始滑动，从短的尾部对齐长的头部 → 短的头部对齐长的尾部结束
        if (n > m) {
            // 固定A数组，滑动B数组
            for (int i = m - 1; i >= 0; i++) {
                // 计算重叠部分在A中的起始位置

            }
        } else {

        }
        return -1;
    }
}