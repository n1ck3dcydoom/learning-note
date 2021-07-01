package leetcode.dp.easy;

import java.util.*;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
 * <p>
 * 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
 * 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
 * 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
 * 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3
 * <p>
 * 输出：3
 * <p>
 * 解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0->2->0->4， 0->2->1->4， 0->2->3->4。
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 3, relation = [[0,2],[2,1]], k = 2
 * <p>
 * 输出：0
 * <p>
 * 解释：信息不能从小 A 处经过 2 轮传递到编号 2
 * <p>
 * 限制：
 * <p>
 * 2 <= n <= 10
 * 1 <= k <= 5
 * 1 <= relation.length <= 90, 且 relation[i].length == 2
 * 0 <= relation[i][0],relation[i][1] < n 且 relation[i][0] != relation[i][1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/chuan-di-xin-xi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/7/1 22:38
 **/
public class LCP_07_传递信息 {

    public static void main(String[] args) {
        int[][] relation = new int[][]{{0, 2}, {2, 1}, {3, 4}, {2, 3}, {1, 4}, {2, 0}, {0, 4}};
        System.out.println(numWays1(5, relation, 3));
    }

    public static int numWays(int n, int[][] relation, int k) {
        // bfs
        // 预先处理所有节点，使用map保存某个节点的所有可达的邻居节点
        HashMap<Integer, HashSet<Integer>> preMap = new HashMap<>();
        for (int[] nodeArray : relation) {
            // 当前节点
            int currentNode = nodeArray[0];
            // 当前节点可达的邻居节点
            int neighborNode = nodeArray[1];
            HashSet<Integer> set = preMap.getOrDefault(currentNode, new HashSet<>());
            set.add(neighborNode);
            preMap.put(currentNode, set);
        }

        // 使用队列完成bfs
        Deque<Integer> deque = new ArrayDeque<>();
        // 头节点首先加入队列
        deque.addLast(0);

        // bfs遍历图，直到队列为空，或者层次达到第k层
        while (!deque.isEmpty() && k > 0) {
            // 当前层有多少节点需要遍历
            int size = deque.size();
            // 遍历当前层所有可达的邻居节点
            while (size > 0) {
                int currentNode = deque.pollFirst();
                HashSet<Integer> currentSet = preMap.get(currentNode);
                // 如果当前节点没有可达的邻居节点，跳过
                if (currentSet != null && currentSet.size() > 0) {
                    // 当前节点的所有可达邻居节点加入队列（即下一层的节点加入队列）
                    for (int next : currentSet) {
                        deque.addLast(next);
                    }
                }
                size--;
            }
            k--;
        }

        int res = 0;
        // 如果没走完k层就遍历了所有节点，就没有满足题意的走法
        if (k > 0) {
            return res;
        } else {
            // 走完k层后就退出bfs，此时队列里面保存的就是走了k步后所有能到达的节点
            // 队列里面出现多少次n-1，就有多少种走法
            while (!deque.isEmpty()) {
                if (deque.pollFirst() == n - 1) {
                    res++;
                }
            }
        }
        return res;
    }

    public static int numWays1(int n, int[][] relation, int k) {
        // dfs
        // 预先处理所有节点，使用map保存某个节点的所有可达的邻居节点
        HashMap<Integer, HashSet<Integer>> preMap = new HashMap<>();
        for (int[] nodeArray : relation) {
            // 当前节点
            int currentNode = nodeArray[0];
            // 当前节点可达的邻居节点
            int neighborNode = nodeArray[1];
            HashSet<Integer> set = preMap.getOrDefault(currentNode, new HashSet<>());
            set.add(neighborNode);
            preMap.put(currentNode, set);
        }

        List<Integer> resCounter = new ArrayList<>();
        resCounter.add(0);
        dfs(resCounter, 0, 0, n, k, preMap.get(0), preMap);
        return resCounter.get(0);
    }

    /**
     * @param resCounter  结果集：有多少种不同的走法
     * @param step        路径：当前走了多少步
     * @param current     路径：当前节点
     * @param n           当走了k步后，判断当前的节点是否是n-1，如果是结果集+1，否则返回上一次dfs
     * @param k           目标步数
     * @param neighborSet 选择：当前节点的所有邻居节点
     * @param preMap      选择：预处理map
     */
    private static void dfs(List<Integer> resCounter, int step, int current, int n, int k,
                            HashSet<Integer> neighborSet, HashMap<Integer, HashSet<Integer>> preMap) {
        if (step == k) {
            int counter = resCounter.get(0);
            // 如果当前节点等于n-1，结果集+1
            if (current == n - 1) {
                resCounter.remove(0);
                resCounter.add(counter + 1);
            }
            return;
        }
        if (neighborSet == null || neighborSet.size() == 0) {
            return;
        }
        // 遍历当前节点的所有邻居节点，继续dfs
        for (int next : neighborSet) {
            dfs(resCounter, step + 1, next, n, k, preMap.get(next), preMap);
        }
    }

    public static int numWays2(int n, int[][] relation, int k) {
        // 动态规划

        // 设dp[i][j]表示经过i轮到达编号为j的节点
        int[][] dp = new int[k + 1][n];

        // 初始值，起点在编号为0的节点，有1种方法经过0次到达编号为0的节点
        dp[0][0] = 1;

        // 递推表达式
        // 对于dp[i][j]  表示已经走了i步到达编号为j的节点的走法

        // 对于传递关系[src, des]
        // 有两种选择：
        // 第一，i-1步到达某个节点k，且节点k能走一步到达节点j
        // 第二，i-1步到达某个节点k，且节点k不能走一步到达节点j、

        // 对于不能从k到达j的情况，不用考虑，因为dp[i-1][k]无法转移得到dp[i][j]
        // 对于能够从k到达j的情况，dp[i][j]等于所有能够在走了i-1步时，从k到达j的情况之和

        // 开始打表
        // 遍历所有轮次
        for (int i = 1; i <= k; i++) {
            // 遍历所有节点
            for (int[] node : relation) {
                // 起点
                int src = node[0];
                // 终点
                int des = node[1];
                dp[i][des] += dp[i - 1][src];
            }
        }
        return dp[k][n - 1];
    }
}