package nowcode.ByteDance;

import java.util.Scanner;

/**
 * Created by n!Ck
 * Date: 2019-03-16
 * Time: 11:29
 * Description:
 */
public class _3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 测试样例个数
        int N = Integer.parseInt(scanner.nextLine());
        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            // 参赛人数
            int n = Integer.parseInt(scanner.nextLine());
            String[] scoresLine = scanner.nextLine().split(" ");
            int[] scores = new int[n];
            int[] gifts = new int[n];
            for (int j = 0; j < n; j++) {
                scores[j] = Integer.parseInt(scoresLine[j]);
                gifts[j] = 1;
            }
            if (n == 1) {
                result[i] = 1;
                continue;
            }
            if (n == 2) {
                result[i] = scores[0] == scores[1] ? 2 : 3;
                continue;
            }

            // 对第一个判断
            if (scores[0] > scores[n - 1] && scores[0] > scores[1]) {
                gifts[0] = Math.max(gifts[1], gifts[n - 1]) + 1;
            } else if (scores[0] > scores[n - 1] && scores[0] <= scores[1]) {
                gifts[0] = gifts[n - 1] + 1;
            } else if (scores[0] > scores[1] && scores[0] <= scores[n - 1]) {
                gifts[0] = gifts[1] + 1;
            }

            // 对最后一个判断
            if (scores[n - 1] > scores[n - 2] && scores[n - 1] > scores[0]) {
                gifts[n - 1] = Math.max(gifts[n - 2], gifts[0]) + 1;
            } else if (scores[n - 1] > scores[n - 2] && scores[n - 1] <= scores[0]) {
                gifts[n - 1] = gifts[n - 2] + 1;
            } else if (scores[n - 1] > scores[0] && scores[n - 1] <= scores[n - 2]) {
                gifts[n - 1] = gifts[0] + 1;
            }

            for (int k = 1; k < n - 1; k++) {
                if (scores[k] > scores[k + 1] && scores[k] > scores[k - 1]) {
                    gifts[k] = Math.max(gifts[k + 1], gifts[k - 1]) + 1;
                } else if (scores[k] > scores[k + 1] && scores[k] <= scores[k - 1]) {
                    gifts[k] = gifts[k + 1] + 1;
                } else if (scores[k] > scores[k - 1] && scores[k] <= scores[k + 1]) {
                    gifts[k] = gifts[k - 1] + 1;
                }
            }
            for (int m = 0; m < n; m++) {
                result[i] += gifts[m];
            }
        }
        for (int n : result) {
            System.out.println(n);
        }
    }
}
