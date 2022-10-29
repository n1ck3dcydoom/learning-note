package leetcode.array.easy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by n!Ck
 * Date: 2022/10/29
 * Time: 16:46
 * Description:
 */

public class _1773_CountItemsMatchingRule {

    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        // int res = 0;
        // for (List<String> item : items) {
        //     if ("type".equals(ruleKey)) {
        //         if (ruleValue.equals(item.get(0))) {
        //             res++;
        //         }
        //     } else if ("color".equals(ruleKey)) {
        //         if (ruleValue.equals(item.get(1))) {
        //             res++;
        //         }
        //     } else if ("name".equals(ruleKey)) {
        //         if (ruleValue.equals(item.get(2))) {
        //             res++;
        //         }
        //     }
        // }
        Map<String, Integer> hash = new HashMap<>();
        hash.put("type", 0);
        hash.put("color", 1);
        hash.put("name", 2);
        int res = 0;
        for (List<String> item : items) {
            if (item.get(hash.get(ruleKey)).equals(ruleValue)) {
                res++;
            }
        }
        return res;
    }
}
