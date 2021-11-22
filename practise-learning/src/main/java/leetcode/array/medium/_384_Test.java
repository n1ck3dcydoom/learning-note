package leetcode.array.medium;

public class _384_Test {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        _384_ShuffleArray shuffle = new _384_ShuffleArray(array);

        int[] s;
        int[] r;

        // 第一次洗牌
        s = shuffle.shuffle();
        for (int n : s) {
            System.out.print(n + " ");
        }
        System.out.println("\n");

        // 重置
        r = shuffle.reset();
        for (int n : r) {
            System.out.print(n + " ");
        }
        System.out.println("\n");

        // 第二次洗牌
        s = shuffle.shuffle();
        for (int n : s) {
            System.out.print(n + " ");
        }
        System.out.println("\n");
    }
}
