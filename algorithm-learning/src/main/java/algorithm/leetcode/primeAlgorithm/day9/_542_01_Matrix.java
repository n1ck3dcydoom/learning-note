package algorithm.leetcode.primeAlgorithm.day9;

import java.util.Deque;
import java.util.ArrayDeque;

public class _542_01_Matrix {

    public static void main(String[] args) {
        int[][] mat = new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 1, 1, 1 } };
        int[][] res = updateMatrix(mat);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                System.out.print(res[i][j] + " ");
            }
        }
    }

    public static int[][] updateMatrix(int[][] mat) {
        // 图的bfs和树的bfs区别
        // 树只有一个起点，而且树是有方向的（从根节点向下遍历），即根节点root，从根节点root开始每次按照层次遍历即可
        // 图有多个起点，而且无向图是没有方向的（从某个起开始向四周扩散遍历）

        // 同时图的多源bfs需要记录某个节点是否已经被访问过了，而树的单源bfs不需要记录是否访问，按照树节点的发展方向每层便利即可保证所有节点都只遍历一次
        int l = mat.length;
        int w = mat[0].length;
        boolean[][] visited = new boolean[l][w];
        // 记录每个点的距离
        int[][] dis = new int[l][w];

        // 为了计算从1到最近0的距离，等同于计算从0到最近1的距离
        // 多源bfs，假象一个超级节点，这个节点连接所有的0节点，但是边权值为0，这样多源0节点就变成了单源超级0节点
        // 超级0节点到每个0节点的距离为0
        // 问题转化为从超级0节点到每个1节点的最短曼哈顿距离

        // 使用队列保存bfs每次遍历的中间结果
        Deque<int[]> queue = new ArrayDeque<>();
        // 超级0节点加入队列（即每个多源0节点加入队列）
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < w; j++) {
                if (mat[i][j] == 0) {
                    queue.addLast(new int[] { i, j });
                    visited[i][j] = true;
                }
            }
        }
        // 遍历队列
        while (!queue.isEmpty()) {
            // 得到当前层次需要遍历的节点个数
            int size = queue.size();
            // 遍历当前层的每个节点
            for (int i = 0; i < size; i++) {
                // 节点出队
                int[] node = queue.pollFirst();
                int x = node[0];
                int y = node[1];
                // 检查上方节点
                if (inSide(visited, mat, x - 1, y)) {
                    // 更新距离
                    dis[x - 1][y] = dis[x][y] + 1;
                    // 上方节点入队
                    queue.addLast(new int[] { x - 1, y });
                    // 更新访问记录
                    visited[x - 1][y] = true;
                }
                // 检查下方
                if (inSide(visited, mat, x + 1, y)) {
                    // 更新距离
                    dis[x + 1][y] = dis[x][y] + 1;
                    // 下方节点入队
                    queue.addLast(new int[] { x + 1, y });
                    // 更新访问记录
                    visited[x + 1][y] = true;
                }
                // 检查左方
                if (inSide(visited, mat, x, y - 1)) {
                    // 更新距离
                    dis[x][y - 1] = dis[x][y] + 1;
                    // 左方节点入队
                    queue.addLast(new int[] { x, y - 1 });
                    // 更新访问记录
                    visited[x][y - 1] = true;
                }
                // 检查右方
                if (inSide(visited, mat, x, y + 1)) {
                    // 更新距离
                    dis[x][y + 1] = dis[x][y] + 1;
                    // 上方节点入队
                    queue.addLast(new int[] { x, y + 1 });
                    // 更新访问记录
                    visited[x][y + 1] = true;
                }
            }
        }
        return dis;
    }

    private static boolean inSide(boolean[][] visited, int[][] mat, int x, int y) {
        return (0 <= x && x < mat.length) && (0 <= y && y < mat[0].length) && (!visited[x][y]);
    }
}
