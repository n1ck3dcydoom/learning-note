package daimasuixianglu._7_backtrace;

import java.util.*;

/**
 * Created by n!Ck
 * Date: 2022/2/17
 * Time: 23:41
 * Description:
 */

public class _13_ReconstructItinerary {

    private static List<String> path = new ArrayList<>();
    private static Map<String, TreeMap<String, Integer>> map = new HashMap<>();

    public static void main(String[] args) {
        // [["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]
        List<List<String>> tickets = Arrays.asList(Arrays.asList("JFK", "KUL"),
                Arrays.asList("JFK", "NRT"), Arrays.asList("NRT", "JFK"));

        findItinerary(tickets);
        int a = 1;
    }

    public static List<String> findItinerary(List<List<String>> tickets) {
        // 处理机场到机场之间的映射表，且按照字典序排序
        // 外层map记录了 起点k所有的目的地v
        // 内层map记录了 所有目的地k能够访问的次数v，且v按照字典升序排序
        for (List<String> ticket : tickets) {
            TreeMap<String, Integer> treeMap = map.getOrDefault(ticket.get(0), new TreeMap<>());
            treeMap.put(ticket.get(1), treeMap.getOrDefault(ticket.get(1), 0) + 1);
            map.put(ticket.get(0), treeMap);
        }

        // 找到了一条满足条件的路径
        path.add("JFK");
        if (dfs(tickets, tickets.size(), "JFK")) {
            return path;
        }
        return Collections.emptyList();
    }

    private static boolean dfs(List<List<String>> tickets, int count, String index) {
        // 如果所有机票都已经使用了
        if (count == 0) {
            return true;
        }
        // 遍历从start开始的所有能够到达的目的地
        TreeMap<String, Integer> destination = map.get(index);
        // 剪枝，避免进入死胡同
        if (count > 0 && destination == null) {
            return false;
        }
        for (Map.Entry<String, Integer> entry : destination.entrySet()) {
            // 如果可以访问的次数等于0，则跳过该目的地
            if (entry.getValue() > 0) {
                String k = entry.getKey();
                int v = entry.getValue();
                // 做出选择
                entry.setValue(v - 1);
                path.add(k);
                // 递归搜索
                if (dfs(tickets, count - 1, k)) {
                    return true;
                }
                // 撤销选择
                entry.setValue(v);
                path.remove(path.size() - 1);
            }
        }
        return false;
    }
}
