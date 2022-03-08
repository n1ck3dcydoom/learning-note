package nowcode.huawei;

import java.util.Scanner;

/**
 * Created by n!Ck
 * Date: 2022/3/9
 * Time: 0:11
 * Description:
 */

public class _22 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int N = scanner.nextInt();
            if (N == 0) {
                break;
            }
            int res = 0;
            while (N / 3 > 0) {
                int t = N / 3;
                res += t;
                N -= 3 * t;
                N += t;
            }
            // 还剩两个可以借一个再喝一瓶
            if (N == 2) {
                res++;
            }
            System.out.println(res);
        }
    }
}
