package leetcode.hash.easy;

import java.util.*;

public class _1436_DestinationCity {

    public static void main(String[] args) {
        List<List<String>> paths1 = new ArrayList<>();
        paths1.add(Arrays.asList("B", "C"));
        paths1.add(Arrays.asList("D", "B"));
        paths1.add(Arrays.asList("C", "A"));
        System.out.println(destCity(paths1));

        List<List<String>> paths2 = new ArrayList<>();
        paths2.add(Arrays.asList("S", "Q"));
        System.out.println(destCity(paths2));
    }

    public static String destCity(List<List<String>> paths) {
        // 遍历所有路线，使用hash保存每个起点和终点城市
        HashMap<String, Boolean> hash = new HashMap<>();
        for (List<String> path : paths) {
            // 如果是起点放入value=true
            // 终点如果不在则放入value=false
            hash.put(path.get(0), true);
            if (!hash.containsKey(path.get(1))) {
                hash.put(path.get(1), false);
            }
        }
        // 找到value=false的key
        for (Map.Entry<String, Boolean> entry : hash.entrySet()) {
            if (!entry.getValue()) {
                return entry.getKey();
            }
        }
        return "";
    }
}