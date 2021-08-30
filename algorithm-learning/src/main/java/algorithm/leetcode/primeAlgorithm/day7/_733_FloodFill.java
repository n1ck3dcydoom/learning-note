package algorithm.leetcode.primeAlgorithm.day7;

import java.util.ArrayDeque;
import java.util.Deque;

public class _733_FloodFill {

    public static void main(String[] args) {
        int[][] image = new int[][] { { 0, 0, 0, }, { 0, 1, 1 } };

        int[][] res = floodFill0(image, 1, 1, 1);

        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static int[][] floodFill0(int[][] image, int sr, int sc, int newColor) {
        // bfs

        // 初始颜色
        int oldColor = image[sr][sc];
        // 如果初始像素的颜色已经等于新的颜色，那么所有跟初始像素相连且颜色相同的像素点，其颜色也都已经等于新的颜色了，不用再遍历处理
        if (oldColor == newColor) {
            return image;
        }
        // 矩阵长宽
        int l = image.length;
        int w = image[0].length;
        // 使用队列保存bfs遍历节点
        Deque<int[]> queue = new ArrayDeque<>();
        // 放入初始节点
        queue.addLast(new int[] { sr, sc });
        // 使用set保存遍历后的节点
        boolean[][] visited = new boolean[l][w];

        // 遍历队列
        while (!queue.isEmpty()) {
            // 当前层所有节点
            int size = queue.size();
            // 遍历当前层的每一个节点
            for (int i = 0; i < size; i++) {
                // 弹出队头节点
                int[] e = queue.pollFirst();
                if (visited[e[0]][e[1]]) {
                    continue;
                }
                // 修改当前节点颜色
                image[e[0]][e[1]] = newColor;
                // 标记已访问
                visited[e[0]][e[1]] = true;
                // 检查上方
                if (inSide0(image, e[0] - 1, e[1]) && !visited[e[0] - 1][e[1]] && image[e[0] - 1][e[1]] == oldColor) {
                    queue.addLast(new int[] { e[0] - 1, e[1] });
                }
                // 检查下方
                if (inSide0(image, e[0] + 1, e[1]) && !visited[e[0] + 1][e[1]] && image[e[0] + 1][e[1]] == oldColor) {
                    queue.addLast(new int[] { e[0] + 1, e[1] });
                }
                // 检查左方
                if (inSide0(image, e[0], e[1] - 1) && !visited[e[0]][e[1] - 1] && image[e[0]][e[1] - 1] == oldColor) {
                    queue.addLast(new int[] { e[0], e[1] - 1 });
                }
                // 检查右方
                if (inSide0(image, e[0], e[1] + 1) && !visited[e[0]][e[1] + 1] && image[e[0]][e[1] + 1] == oldColor) {
                    queue.addLast(new int[] { e[0], e[1] + 1 });
                }
            }
        }
        return image;
    }

    private static boolean inSide0(int[][] image, int x, int y) {
        return (0 <= x && x < image.length) && (0 <= y && y < image[0].length);
    }

    public static int[][] floodFill1(int[][] image, int sr, int sc, int newColor) {
        // 如果初始像素的颜色已经等于新的颜色，那么所有跟初始像素相连且颜色相同的像素点，其颜色也都已经等于新的颜色了，不用再遍历处理
        if (image[sr][sc] == newColor) {
            return image;
        }
        dfs(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    private static void dfs(int[][] image, int sr, int sc, int oldColor, int newColor) {
        if (!inSide(image, sr, sc, oldColor, newColor)) {
            return;
        }
        // 修改当前像素点颜色
        image[sr][sc] = newColor;
        // 检查上方
        dfs(image, sr - 1, sc, oldColor, newColor);
        // 检查下方
        dfs(image, sr + 1, sc, oldColor, newColor);
        // 检查左方
        dfs(image, sr, sc - 1, oldColor, newColor);
        // 检查右方
        dfs(image, sr, sc + 1, oldColor, newColor);
    }

    private static boolean inSide(int[][] image, int x, int y, int oldColor, int newColor) {
        // 在边界内，且修改前颜色等于初始颜色oldColor，且不等于修改后颜色newColor
        return (0 <= x && x < image.length) && (0 <= y && y < image[0].length) && image[x][y] == oldColor
                && image[x][y] != newColor;
    }

}
