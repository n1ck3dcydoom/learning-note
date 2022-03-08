package nowcode.huawei;

import java.util.Scanner;

/**
 * Created by n!Ck
 * Date: 2022/3/8
 * Time: 23:50
 * Description:
 */

public class _20 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String pwd = scanner.nextLine();
            if (checkLen(pwd) && checkChar(pwd) && checkRepeat(pwd)) {
                System.out.println("OK");
            } else {
                System.out.println("NG");
            }
        }
    }

    private static boolean checkLen(String pwd) {
        return pwd.length() > 8;
    }

    private static boolean checkChar(String pwd) {
        int num = 0, lowerCase = 0, upperCase = 0, other = 0;
        char[] cs = pwd.toCharArray();
        for (char c : cs) {
            if ('0' <= c && c <= '9') {
                num = 1;
            } else if ('a' <= c && c <= 'z') {
                lowerCase = 1;
            } else if ('A' <= c && c <= 'Z') {
                upperCase = 1;
            } else {
                other = 1;
            }
        }
        return num + lowerCase + upperCase + other >= 3;
    }

    private static boolean checkRepeat(String pwd) {
        for (int i = 0; i < pwd.length() - 2; i++) {
            String sub = pwd.substring(i, i + 3);
            if (pwd.substring(i + 1).contains(sub)) {
                return false;
            }
        }
        return true;
    }
}
