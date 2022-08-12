package leetcode.hash.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by n!Ck
 * Date: 2022/8/12
 * Time: 09:01
 * Description:
 */

public class _1282_GroupThePeopleGivenTheGroupSizeTheyBelongTo {

    public static void main(String[] args) {
        System.out.println(groupThePeople(new int[]{1, 1, 1, 3, 3, 1, 3}));
    }

    public static List<List<Integer>> groupThePeople(int[] groupSizes) {
        // 把数组值一样的元素放到一个集合里面
        // Map<Integer, List<Integer>> hash = new HashMap<>();
        // List<List<Integer>> res = new ArrayList<>();
        // for (int i = 0; i < groupSizes.length; i++) {
        //     if (hash.containsKey(groupSizes[i])) {
        //         if (hash.get(groupSizes[i]).size() == groupSizes[i]) {
        //             res.add(hash.get(groupSizes[i]));
        //             hash.remove(groupSizes[i]);
        //             List<Integer> list = new ArrayList<>();
        //             list.add(i);
        //             hash.put(groupSizes[i], list);
        //         } else {
        //             List<Integer> list = hash.get(groupSizes[i]);
        //             list.add(i);
        //             hash.put(groupSizes[i], list);
        //         }
        //     } else {
        //         List<Integer> list = new ArrayList<>();
        //         list.add(i);
        //         hash.put(groupSizes[i], list);
        //     }
        // }
        // for (Map.Entry<Integer, List<Integer>> entry : hash.entrySet()) {
        //     res.add(entry.getValue());
        // }
        // return res;

        List<Integer>[] tmp = new ArrayList[500];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < groupSizes.length; i++) {
            tmp[groupSizes[i]].add(i);
            if (tmp[groupSizes[i]].size() == groupSizes[i]) {
                List<Integer> t = new ArrayList<>();
                t.addAll(tmp[groupSizes[i]]);
                res.add(t);
                // 清空缓存数组
                tmp[groupSizes[i]].clear();
            }
        }
        return res;
    }
}
