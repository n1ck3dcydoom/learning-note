package leetcode.bfs.medium;

public class _547_NumberOfProvinces {

    public static void main(String[] args) {
        int[][] i1 = new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int[][] i2 = new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};

        System.out.println(findCircleNum(i1));
        System.out.println(findCircleNum(i2));
    }

    public static int findCircleNum(int[][] isConnected) {
        // 抽象过来就是个岛屿问题
        // 最终返回岛屿数量
        return -1;
    }
}
