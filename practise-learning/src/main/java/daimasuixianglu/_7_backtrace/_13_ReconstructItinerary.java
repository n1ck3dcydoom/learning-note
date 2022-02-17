package daimasuixianglu._7_backtrace;

import java.util.*;

/**
 * Created by n!Ck
 * Date: 2022/2/17
 * Time: 23:41
 * Description:
 */

public class _13_ReconstructItinerary {

    private List<String> res = new ArrayList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        // 处理机场到机场之间的映射表，且按照字典序排序
        // 外层map记录了 机场k所有的航班
        // 内层map记录了 起点k和终点v，且内层map的key都相同，且v按照字典升序排序
        Map<String, TreeMap<String, String>> map = new HashMap<>();
        return res;
    }

    private void dfs(List<List<String>> tickets) {

    }
}
