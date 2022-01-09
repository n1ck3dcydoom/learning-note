package leetcode.array.easy;

/**
 * Created by n!Ck
 * Date: 2022/1/9
 * Time: 23:15
 * Description:
 */

public class _1629_SlowestKey {

    public static void main(String[] args) {
        int[] r1 = new int[]{6,20,29,42,57,70,76,82,91};
        System.out.println(slowestKey(r1, "ugmzgryso"));

        // int[] r2 = new int[]{12, 23, 36, 46, 62};
        // System.out.println(slowestKey(r2, "spuda"));
    }

    public static char slowestKey(int[] releaseTimes, String keysPressed) {

        int n = releaseTimes.length;
        // 前缀和
        int[] sum = new int[n];
        sum[0] = releaseTimes[0];
        for (int i = 1; i < n; i++) {
            sum[i] = releaseTimes[i] - releaseTimes[i - 1];
        }
        char c = keysPressed.charAt(0);
        int max = sum[0];
        for (int i = 1; i < n; i++) {
            char t = keysPressed.charAt(i);
            if (sum[i] > max) {
                c = t;
                max = sum[i];
            } else if (sum[i] == max) {
                c = c > t ? c : t;
            }
        }
        return c;
    }
}