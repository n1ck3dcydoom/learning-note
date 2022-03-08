package nowcode.huawei;

import java.util.Scanner;

/**
 * Created by n!Ck
 * Date: 2022/3/8
 * Time: 23:11
 * Description:
 */

public class _17 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] arr = str.split(";");

        int x = 0;
        int y = 0;
        for (String s : arr) {
            if (s.startsWith("A") || s.startsWith("S") || s.startsWith("D") || s.startsWith("W")) {
                if (s.length() >= 2 && s.length() <= 3) {
                    int step;
                    try {
                        step = Integer.parseInt(s.substring(1));
                    } catch (Exception e) {
                        // do nothing
                        continue;
                    }
                    if (s.startsWith("A")) {
                        x -= step;
                    } else if (s.startsWith("S")) {
                        y -= step;
                    } else if (s.startsWith("D")) {
                        x += step;
                    } else if (s.startsWith("W")) {
                        y += step;
                    }
                }
            }
        }
        System.out.println(x + "," + y);
    }
}
