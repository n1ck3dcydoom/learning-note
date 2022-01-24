package daimasuixianglu._3_hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class _3_GroupAnagrams {

    public static void main(String[] args) {
        String[] strs = new String[] { "eat", "tea", "tan", "ate", "nat", "bat" };
        groupAnagrams(strs);
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> hash = new HashMap<>();
        for (String str : strs) {
            // 单词词频一样,按照字典序排序后也必然一样
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            List<String> list = hash.getOrDefault(key, new ArrayList<>());
            list.add(str);
            hash.put(key, list);
        }
        return hash.values().stream().collect(Collectors.toList());
    }
}
