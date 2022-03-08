package nowcode.huawei;

import java.util.*;

/**
 * Created by n!Ck
 * Date: 2022/3/9
 * Time: 0:18
 * Description:
 */

public class _23 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            // 统计词频
            Map<Character, Integer> map = new HashMap<>();
            char[] cs = str.toCharArray();
            for (char c : cs) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            int min = map.values().stream().min(Comparator.comparingInt((v) -> v)).get();
            StringBuilder sb = new StringBuilder();
            for (char c : cs) {
                if (map.get(c) == min) {
                    continue;
                }
                sb.append(c);
            }
            System.out.println(sb.toString());
        }
    }
}
