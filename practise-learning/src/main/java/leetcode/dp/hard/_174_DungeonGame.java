package leetcode.dp.hard;

/**
 * @version 1.0
 * @description leetcode
 * 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
 * <p>
 * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 * <p>
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
 * <p>
 * 为了尽快到达公主，骑士决定每次只向右或向下移动一步。
 * <p>
 * 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。
 * <p>
 * 例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。
 * 骑士的健康点数没有上限。
 * <p>
 * 任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dungeon-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/3/10 21:52
 **/
public class _174_DungeonGame {

    public static void main(String[] args) {
        int[][] dungeon = new int[][]{{3,-20,-30}, {-3,4,0}};
        System.out.println(calculateMinimumHP1(dungeon));
    }

    public static int calculateMinimumHP(int[][] dungeon) {
        // 逆向二维dp

        // 第一眼看到这个，很自然的想到了定义dp[i][j]表示从(i,j)走到终点所需要的最小生命值

        // 正向打表：
        // 初始值如何确定，通过上面的dp的定义，如果正向打表的话，初始值dp[0][0]就是我们要求解的答案，这明显没法定义初始值

        // 反过来想，逆向打表
        // 如果从终点开始走，那么只需要保证初始生命扣除终点房间的血量后等于1，即是最小生命值
        // 所以dp[i][j]反而成了初始值，它只能由上面或者左边走过来，这样可以倒着推回dp[0][0]求得答案

        // 如果终点的血量大于0，那么起始生命值直接设置为1即可，相当于走到终点还能加血
        // 如果终点的血量小于0，那么起始生命值直接设置为 血量+ x = 1即可，即 x = 1-血量


        int n = dungeon.length;
        int m = dungeon[0].length;
        int[][] dp = new int[n][m];

        // 初始值
        // 正向dp我们需要初始化第一行和第一列，逆向dp则需要初始化最后一行和最后一列
        if (dungeon[n - 1][m - 1] >= 0) {
            dp[n - 1][m - 1] = 1;
        } else {
            dp[n - 1][m - 1] = 1 - dungeon[n - 1][m - 1];
        }
        // 初始化最后一行，最后一个已经初始化了，从倒数第二个开始遍历
        for (int i = m - 2; i >= 0; i--) {
            // 假设已经走到i,j，求得需要的最小生命值是dp[i][j]
            // 考虑左边的格子i,j-1
            // 如果dungeon[i][j-1] < 0，说明进入(i,j-1)需要更多的生命值，为了保证扣掉dungeon[i][j-1]之后，还能够进入(i,j)
            // 需要dp[i][j-1] - (|dungeon[i][j-1]| + dp[i][j]) = 1
            // 即 dp[i][j-1] = dp[i][j] - dungeon[i][j-1] + 1

            // 如果dungeon[i][j-1] >= 0，则需要考虑dp[i][j-1]加上dungeon[i][j-1]后走到(i,j)需要扣除dp[i][j]，还剩1
            // 即 如果dungeon[i][j-1] >= dp[i][j]    dp[i][j-1] = 1 即可
            // 如果   dungeon[i][j-1] < dp[i][j]     dp[i][j-1] = dp[i][j]-dungeon[i][j-1]

            if (dungeon[n - 1][i] < 0) {
                dp[n - 1][i] = dp[n - 1][i + 1] - dungeon[n - 1][i];
            } else if (dp[n - 1][i + 1] - dungeon[n - 1][i] > 0) {
                dp[n - 1][i] = dp[n - 1][i + 1] - dungeon[n - 1][i];
            } else {
                dp[n - 1][i] = 1;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (dungeon[i][m - 1] < 0) {
                dp[i][m - 1] = dp[i + 1][m - 1] - dungeon[i][m - 1];
            } else if (dp[i + 1][m - 1] - dungeon[i][m - 1] > 0) {
                dp[i][m - 1] = dp[i + 1][m - 1] - dungeon[i][m - 1];
            } else {
                dp[i][m - 1] = 1;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                int right;
                int left;
                if (dungeon[i][j] < 0) {
                    right = dp[i][j + 1] - dungeon[i][j];
                } else if (dp[i][j + 1] - dungeon[i][j] > 0) {
                    right = dp[i][j + 1] - dungeon[i][j];
                } else {
                    right = 1;
                }
                if (dungeon[i][j] < 0) {
                    left = dp[i + 1][j] - dungeon[i][j];
                } else if (dp[i + 1][j] - dungeon[i][j] > 0) {
                    left = dp[i + 1][j] - dungeon[i][j];
                } else {
                    left = 1;
                }
                dp[i][j] = Math.min(left, right);
            }
        }
        return dp[0][0];
    }

    public static int calculateMinimumHP1(int[][] dungeon) {

        int n = dungeon.length;
        int m = dungeon[0].length;
        int[][] dp = new int[n][m];

        // 初始值
        if (dungeon[n - 1][m - 1] >= 0) {
            dp[n - 1][m - 1] = 1;
        } else {
            dp[n - 1][m - 1] = 1 - dungeon[n - 1][m - 1];
        }

        // 初始化最后一行
        for (int i = m - 2; i >= 0; i--) {
            if (dungeon[n - 1][i] >= dp[n - 1][i + 1]) {
                dp[n - 1][i] = 1;
            } else {
                dp[n - 1][i] = dp[n - 1][i + 1] - dungeon[n - 1][i];
            }
        }
        // 初始化最后一列
        for (int i = n - 2; i >= 0; i--) {
            if (dungeon[i][m - 1] >= dp[i + 1][m - 1]) {
                dp[i][m - 1] = 1;
            } else {
                dp[i][m - 1] = dp[i + 1][m - 1] - dungeon[i][m - 1];
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                // 走右边
                int right;
                if (dungeon[i][j] >= dp[i][j + 1]) {
                    right = 1;
                } else {
                    right = dp[i][j + 1] - dungeon[i][j];
                }
                // 走下边
                int left;
                if (dungeon[i][j] >= dp[i + 1][j]) {
                    left = 1;
                } else {
                    left = dp[i + 1][j] - dungeon[i][j];
                }
                dp[i][j] = Math.min(left, right);
            }
        }
        return dp[0][0];
    }
}