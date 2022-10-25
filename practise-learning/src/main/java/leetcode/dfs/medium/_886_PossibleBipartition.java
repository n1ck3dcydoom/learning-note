package leetcode.dfs.medium;

/**
 * Created by n!Ck
 * Date: 2022/10/21
 * Time: 09:51
 * Description:
 */

public class _886_PossibleBipartition {

    public static void main(String[] args) {
        System.out.println(possibleBipartition(4, new int[][]{{1, 2}, {1, 3}, {2, 4}}));
    }

    public static boolean possibleBipartition(int n, int[][] dislikes) {
        // 第一眼想到使用 dfs 搜索每个人
        // 将第 i 个人尝试放到 A 组,如果 A 组没有他不喜欢的人,则可以放入
        // 否则放到 B 组,如果 B 组没有他不喜欢的人,则可以放入
        // 如果 AB 两组都放不下他,则回溯

        // 每个人维护一个数组,这个数组记录了他不喜欢的人
        // dislikes 数组里面每个人的下标从 1 开始,所以 hate 数组与之对应从 1 开始计数
        int[][] hate = new int[n + 1][n + 1];
        for (int[] dis : dislikes) {
            hate[dis[0]][dis[1]] = 1;
            hate[dis[1]][dis[0]] = 1;
        }
        // dfs 搜索每个人,放到 AB 两组抽象为对每个人染色
        // 一共只有两种颜色 1 和 2,每个人只能染一种颜色; 一旦某个人染了色,那么他不喜欢的人只能染另一种颜色
        int[] colored = new int[n + 1];
        int color = 1;
        for (int i = 1; i <= n; i++) {
            // 如果当前这个人还没有被染色,则 dfs 尝试为他染色
            // 如果 dfs 返回染色失败,则说明不存在
            if (colored[i] == 0 && !dfs(colored, i, color, hate)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 规定颜色只有 1 和 2,每次取反的时候用 3-color 即可
     *
     * @param colored 所有人的染色数组
     * @param i       当前需要染色的人
     * @param color   当前可以使用的颜色
     * @param hate    当前人不喜欢的人
     * @return 能否成功染色
     */
    private static boolean dfs(int[] colored, int i, int color, int[][] hate) {
        // 对当前人染色
        colored[i] = color;
        // dfs 搜索当前所有不喜欢的人,尝试对他们进行另一种颜色的染色
        for (int j = 1; j <= hate[i].length - 1; j++) {
            if (hate[i][j] == 1) {
                // 如果不喜欢的人还没有染色,用相反颜色尝试对他染色
                // 如果对不喜欢的人染另一种颜色失败,则说明不存在可能的解
                if (colored[j] == 0 && !dfs(colored, j, 3 - color, hate)) {
                    return false;
                }
                // 如果不喜欢的人已经染了色,那么判断两个人的颜色是否互斥
                if (colored[j] != 0 && colored[i] == colored[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
