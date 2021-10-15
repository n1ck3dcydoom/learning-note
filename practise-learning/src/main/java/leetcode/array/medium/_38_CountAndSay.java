package leetcode.array.medium;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/10/18 13:38
 **/
public class _38_CountAndSay {

    public static void main(String[] args) {
        System.out.println(countAndSay(5));
    }

    public static String countAndSay(int n) {
        String s = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            int count = 0;
            char last = s.charAt(0);
            // 遍历上一层生成的字符串
            for (int j = 0; j < s.length(); j++) {
                // 相等则计数
                if (last == s.charAt(j)) {
                    count++;
                } else {
                    // 不相等则拼接字符串，重置计数
                    sb.append(count).append(last);
                    last = s.charAt(j);
                    count = 1;
                }
            }
            // s遍历完之后还剩最后一个字符没有计数
            sb.append(count).append(last);
            s = sb.toString();
        }
        return s;
    }
}