package leetcode.string.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by n!Ck
 * Date: 2022/9/8
 * Time: 18:44
 * Description:
 */

public class _1592_RearrangeSpacesBetweenWords {

    public static void main(String[] args) {
        System.out.println(reorderSpaces(" hello"));
    }

    public static String reorderSpaces(String text) {
        int space = 0;
        char[] cs = text.toCharArray();
        for (char c : cs) {
            if (c == ' ') {
                space++;
            }
        }
        // 正则表达式切割单词
        String[] words = text.split("\\s+");
        List<String> cleanWords = new ArrayList<>();
        for (String w : words) {
            if (!"".equals(w)) {
                cleanWords.add(w);
            }
        }
        int word = cleanWords.size();

        int interval = word == 1 ? 0 : (int) Math.floor((double) space / (word - 1));
        int mod = word == 1 ? space : space % (word - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < interval; i++) {
            sb.append(" ");
        }
        String spaces = sb.toString();
        String join = String.join(spaces, cleanWords);
        if (mod > 0) {
            sb = new StringBuilder(join);
            while (mod-- > 0) {
                sb.append(" ");
            }
            return sb.toString();
        }
        return join;
    }
}
