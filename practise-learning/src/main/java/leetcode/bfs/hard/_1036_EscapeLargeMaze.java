package leetcode.bfs.hard;

import java.util.*;

public class _1036_EscapeLargeMaze {

    // 一维坐标转换最大值
    private static final int LEN = 1000001;
    // 网格最大长度为1000000
    private static final int MAX = 1000000;

    // 上下左右四个方向
    private static final int[][] ways = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        // block数量有限，在有限个障碍物block下能形成最大的包围圈，网格数量为n
        // 分情况讨论
        // 1. s和t都在包围圈以内，肯定可以相遇 -> s搜索范围小于包围圈时遇到了t
        // 2. s在包围圈，t不在，肯定不能相遇 -> s搜索范围等于包围圈，但是没有遇到t，t搜索范围大于包围圈
        // 3. s不在包围圈，t在，肯定不能相遇 -> s搜索范围大于包围圈，t搜索范围等于包围圈，但是没有遇到s
        // 4. s和t都不在包围圈内，肯定可以相遇 -> s和t的搜索范围都大于包围圈，在中途相遇
        // 或者两个都搜索完成后没有相遇(说明距离更远，超过了包围圈的最大范围)

        // 所有障碍物利用网格两边构成等腰直角三角形能够得到最大的保卫面积(n-1)*n/2
        // 长为n，宽为n-1的矩形面积的一半
        int n = blocked.length;
        // 没有障碍物或者只有1个障碍物是无法形成包围圈的
        if (n <= 1) {
            return true;
        }
        // 包围圈的最大范围
        int maxBlock = n * (n - 1) / 2;
        // 二维数组降维到一位数组
        // 公式 int[x][y] -> x * length + y
        // 使用hash表保存所有障碍物加快搜索
        Set<Integer> blockSet = new HashSet<>();
        for (int[] block : blocked) {
            // 网格最大长度为1000000，
            blockSet.add(block[0] * LEN + block[1]);
        }

        // 从s开始bfs搜索
        int fromSource = bfs(blockSet, source, target, maxBlock);
        // 如果s能够找到t
        if (fromSource == 1) {
            return true;
        }
        // 如果s在包围圈内部，而且在内部没有找到t，那么永远也找不到t了
        else if (fromSource == -1) {
            return false;
        }
        // 返回0则说明s能够搜索的范围已经超过包围圈的最大面积了，只需要再检查从t出发能不能找到s
        int fromTarget = bfs(blockSet, target, source, maxBlock);
        // 这里t和s肯定不同时在包围圈内，不然上面fromSource就返回1，s就能够找到t了
        // 此时s不在包围圈，那么t还剩两种情况，在包围圈内，返回-1，不在包围圈内，返回0
        // 只要fromTarget不返回-1，则说明t也在包围圈外，那么一定可以找到s
        return fromTarget != -1;
    }

    private int bfs(Set<Integer> blocked, int[] s, int[] t, int maxBlock) {
        // 标记数组，保存是否被访问过
        Set<Integer> visited = new HashSet<>();
        // s加入标记数组
        visited.add(s[0] * LEN + s[1]);

        // 使用队列记录bfs搜索的下一层节点
        Deque<int[]> queue = new ArrayDeque<>();
        // 起点s入队
        queue.addLast(s);

        // 开始bfs
        while (!queue.isEmpty()) {
            int[] temp = queue.pollFirst();
            // 搜索四个方向
            for (int[] way : ways) {
                int x = way[0] + temp[0];
                int y = way[1] + temp[1];
                // 转换为一维坐标
                int flat = x * LEN + y;
                // 在网格范围内
                boolean inBoard = (x >= 0 && x < MAX) && (y >= 0 && y < MAX);
                // 不是障碍物
                boolean nonBlock = !blocked.contains(flat);
                // 没有被访问过
                boolean nonVisited = !visited.contains(flat);
                // 检查当前点是否能够被访问
                if (inBoard && nonBlock && nonVisited) {
                    // 如果找到了t，直接返回1表示找到了t
                    if (x == t[0] && y == t[1]) {
                        return 1;
                    }
                    // 标记为已访问
                    visited.add(flat);
                    // 检查已经搜索的范围有没有超过包围圈
                    if (visited.size() > maxBlock) {
                        // 返回0表示搜索范围已经大于包围圈
                        return 0;
                    }
                    // 当前节点入队，等待继续搜索当前节点的四个方向
                    queue.add(new int[] { x, y });
                }
            }
        }
        // 搜索完整个包围圈的范围都没能找到t，或者搜索面积小于包围圈，说明t在包围圈里面，返回-1
        return -1;
    }
}
