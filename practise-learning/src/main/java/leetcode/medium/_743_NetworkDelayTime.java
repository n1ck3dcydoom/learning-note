package leetcode.medium;

/**
 * @author zhanglei
 * @version 1.0
 * @description 有N个网络节点，标记为1到N。
 * <p>
 * 给定一个列表times，表示信号经过有向边的传递时间。times[i] = (u, v, w)，其中u是源节点，v是目标节点， w是一个信号从源节点传递到目标节点的时间。
 * <p>
 * 现在，我们从某个节点K发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回-1。
 * <p>
 * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
 * 输出：2
 * <p>
 * 注意:
 * <p>
 * N的范围在[1, 100]之间。
 * K的范围在[1, N]之间。
 * times的长度在[1, 6000]之间。
 * 所有的边times[i] = (u, v, w)都有1 <= u, v <= N且0 <= w <= 100。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/network-delay-time
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/12/24 23:20
 **/
public class _743_NetworkDelayTime {

    public static void main(String[] args) {
        int[][] times = new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int N = 4;
        int K = 2;
        System.out.println(networkDelayTime(times, N, K));
    }

    public static int networkDelayTime(int[][] times, int N, int K) {
        // 初始化邻接矩阵
        int max = 9999;
        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = max;
            }
        }
        // 遍历times矩阵
        for (int i = 0; i < times.length; i++) {
            graph[times[i][0] - 1][times[i][1] - 1] = times[i][2];
        }
        // dis集合存储起点K到每个顶点的最短路径，初始值为顶点K的权值，顶点K到自身的最短路径为0
        int[] dis = graph[K - 1];
        dis[K - 1] = 0;
        // selected集合存储已经选择了最短路径的顶点，已选择为1，未选择为0，顶点K的初始值为1
        int[] selected = new int[N];
        selected[K - 1] = 1;
        // 需要一个指针保存每次从未选择顶点的集合中找到距离已选择顶点集合最近的顶点的位置
        int pos = 0;
        // 进行N-1次遍历（起点已经被选择了），完成dis集合的赋值
        for (int i = 0; i < N - 1; i++) {
            // 找出未选择顶点的集合中距离dis集合最近的定点之，并且放入selected集合，更新dis集合
            int tempMin = Integer.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                // 只在未选择顶点的集合中挑选
                if (selected[j] != 1) {
                    // 找距离dis集合最近的顶点
                    if (dis[j] < tempMin) {
                        tempMin = dis[j];
                        pos = j;
                    }
                }
            }
            // 找到距离dis集合最近的顶点后，更新selected集合
            selected[pos] = 1;
            // 对dis进行松弛
            for (int j = 0; j < N; j++) {
                // 只在还没有被选中的顶点集合中进行松弛
                if (selected[j] != 1) {
                    if (dis[j] > graph[pos][j] + dis[pos]) {
                        dis[j] = graph[pos][j] + dis[pos];
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < N; i++) {
            res = Math.max(dis[i], res);
        }
        if (res == max) {
            return -1;
        }
        return res;
    }
}
