package luogu;

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

    public static void main(String[] args) {
        // 顶点个数
        int n = 6;
        // 起点顶点编号
        int s = 0;
        // 使用邻接矩阵存储图
        int max = 9999;
        int[][] graph = new int[][]{{0, 1, 12, max, max, max}, {max, 0, 7, 3, max, max}, {max, max, 0, max, 5, max}, {max, max, 5, 0, 10, 13}, {max, max, max, max, 0, 4}, {max, max, max, max, max, 0}};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(graph[i][j] + "\t");
                if (j == 5) {
                    System.out.print("\n");
                }
            }
        }
        int[] dis = dijkstra(graph, s);
        for (int di : dis) {
            System.out.print(di + " ");
        }
    }

    private static int[] dijkstra(int[][] graph, int start) {
        int n = graph.length;
        // dis集合存储起点s到每个顶点的最短路径，初始值为顶点s的权值，顶点s到自身的最短路径为0
        int[] dis = graph[start];
        dis[start] = 0;
        // selected集合存储已经确定了的顶点，已经选择的为1，没有选择的为0，顶点s初始值为1
        int[] selected = new int[n];
        selected[start] = 1;
        // 需要一个指针保存每次找到的最近节点的位置
        int pos = 0;
        // 进行n-1次遍历（因为起点已经选择了），完成dis集合的赋值
        for (int i = 0; i < n - 1; i++) {
            // 找出当前dis集合中可达的且最近的顶点值，并且放入selected集合，更新dis集合
            int tempMin = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                // 只在还没有被选中的顶点集合中找
                if (selected[j] != 1) {
                    // 只找距离顶点s最近的顶点
                    if (dis[j] < tempMin) {
                        tempMin = dis[j];
                        pos = j;
                    }
                }
            }
            // 找到当前距离顶点s的最近顶点后，把这个顶点选出来，selecte集合置为1
            selected[pos] = 1;
            // 对dis集合进行松弛
            for (int j = 0; j < n; j++) {
                // 只在还没有被选中的顶点集合中进行松弛
                if (selected[j] != 1) {
                    if (dis[j] > graph[pos][j] + dis[pos]) {
                        dis[j] = graph[pos][j] + dis[pos];
                    }
                }
            }

        }
        return dis;
    }
}
