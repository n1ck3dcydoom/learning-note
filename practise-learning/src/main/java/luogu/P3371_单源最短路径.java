package luogu;

import java.util.Scanner;

/**
 * @author zhanglei
 * @version 1.0
 * @description 题目背景
 * <p>
 * 题目描述
 * 如题，给出一个有向图，请输出从某一点出发到所有点的最短路径长度。
 * <p>
 * 输入格式
 * 第一行包含三个整数 n,m,s，分别表示点的个数、有向边的个数、出发点的编号。
 * <p>
 * 接下来 m 行每行包含三个整数 u,v,w，表示一条 u→v 的，长度为 w 的边。
 * <p>
 * 输出格式
 * 输出一行 n 个整数，第 i 个表示 s 到第 i 个点的最短路径，若不能到达则输出 2^{31}-1
 * <p>
 * 输入输出样例
 * 4 6 1
 * 1 2 2
 * 2 3 2
 * 2 4 1
 * 1 3 5
 * 3 4 3
 * 1 4 4
 * <p>
 * 输出 #1
 * 0 2 4 3
 * @date 2020/12/21 16:20
 **/
public class P3371_单源最短路径 {

    /**
     * http://www.coding-daddy.com/algorithms/Dijkstra.html#_3-java%E5%AE%9E%E7%8E%B0
     *
     * @param args
     */
    public static void main(String[] args) {
        // 顶点个数
        int n = 4;
        // 有向边个数
        int m = 6;
        // 起点顶点编号
        int s = 1;
        // 使用邻接矩阵存储图
        int[][] graph = null;
        try (Scanner scanner = new Scanner(System.in)) {
            //            n = scanner.nextInt();
            //            m = scanner.nextInt();
            //            s = scanner.nextInt();
            graph = new int[n][n];
            // 初始化所有图为正无穷，且当i=j的时候，节点路径为0
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        graph[i][j] = 0;
                    } else {
                        graph[i][j] = Integer.MAX_VALUE;
                    }
                }
            }
            // 根据输入设置邻接矩阵
            //            for (int i = 0; i < m; i++) {
            //                graph[scanner.nextInt() - 1][scanner.nextInt() - 1] = scanner.nextInt();
            //            }
            graph = new int[][]{{0, 2, 5, 4}, {9999, 0, 2, 1}, {9999, 9999, 0, 3}, {9999, 9999, 9999, 0}};
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (graph != null) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(graph[i][j] + " ");
                    if (j == 3) {
                        System.out.print("\n");
                    }
                }
            }
        }
        int[] dis = dijkstra(graph, s);
        for (int i = 0; i < dis.length; i++) {
            System.out.print(dis[i] + " ");
        }
    }


    /**
     * 使用二维矩阵存储带权有向图
     */
    private static int[] dijkstra(int[][] graph, int start) {
        int n = graph.length;
        // dis集合存储起点s到每个顶点的最短路径
        int[] dis = new int[n];
        for (int i = 0; i < n; i++) {
            dis[i] = 99999;
        }
        dis[start - 1] = 0;
        // selected集合存储已经确定了的顶点，已经选择的为1，没有选择的为0
        int[] selected = new int[n];
        selected[start - 1] = 1;
        // 需要保存前驱节点
        int[] pre = new int[n];
        pre[start - 1] = 0;

        // 进行n-1次遍历（因为起点已经选择了），完成dis集合的赋值
        int pos = start - 1;
        for (int i = 0; i < n - 1; i++) {
            // 找出当前dis集合中可达的且最近的顶点值，并且放入selected集合，更新dis集合
            int tempMin = Integer.MAX_VALUE;
            int k = pos;
            for (int j = 0; j < n; j++) {
                // 当顶点没有被选择
                if (selected[j] != 1) {
                    if (graph[k][j] < tempMin) {
                        tempMin = graph[k][j];
                        pos = j; // 此时pos指针已经指向当前遍历后所找到的最短路径的顶点位置
                    }
                }
            }

            // 把当前找到的最短路径更新dis集合，经过当前pos点，到达其他点的最短路径
            for (int p = 0; p < n; p++) {
                if (selected[p] != 1) {
                    // 起点到pos点的距离 + pos点到p点的距离 < 起点到p点的距离
                    if (graph[start - 1][pos] + graph[pos][p] <= graph[start - 1][p]) {
                        dis[p] = graph[start - 1][pos] + graph[pos][p];
                    }
                }
            } // 当前最小路径加入selected集合
            selected[pos] = 1;
        }
        return dis;
    }
}

