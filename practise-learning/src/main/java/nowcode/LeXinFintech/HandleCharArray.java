package nowcode.LeXinFintech;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by n!Ck
 * Date: 2019-02-03
 * Time: 15:50
 * Description:
 * 现有字符串 char[] text ，实现函数 char[] drawChar(char[] text)，找出text中重复出现最多的字符，然后将该字符移到text的最前端，生成一个新的字符串。 假设重复出现最多的字符只有一个。请充分考虑内存和性能效率。 举例： “abcaba”，转换后成为“aaabcb”。
 */
public class HandleCharArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[] text = scanner.nextLine().toCharArray();
        Map<Character, Integer> chMap = new HashMap<>();
        char maxCh = '\0';
        int maxChCount = 1;
        for (char ch : text) {
            if (!chMap.containsKey(ch)) {
                chMap.put(ch, 1);
            } else {
                int count = chMap.get(ch) + 1;
                chMap.put(ch, count);
                if (count > maxChCount) {
                    maxChCount = count;
                    maxCh = ch;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (maxChCount != 0) {
            stringBuilder.append(maxCh);
            maxChCount--;
        }
        for (char ch : text) {
            if (ch != maxCh) {
                stringBuilder.append(ch);
            }
        }
        System.out.println(stringBuilder.toString());
    }
}
