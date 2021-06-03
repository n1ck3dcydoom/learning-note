package leetcode.hash.medium;

import java.util.*;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * "tree"
 * <p>
 * 输出:
 * "eert"
 * <p>
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * 示例 2:
 * <p>
 * 输入:
 * "cccaaa"
 * <p>
 * 输出:
 * "cccaaa"
 * <p>
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * 示例 3:
 * <p>
 * 输入:
 * "Aabb"
 * <p>
 * 输出:
 * "bbAa"
 * <p>
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-characters-by-frequency
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/6/3 23:25
 **/
public class _451_SortCharactersByFrequency {

    public static void main(String[] args) {
        String s = "tree";
        System.out.println(frequencySort1(s));
    }

    public static String frequencySort(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int n = s.length();
        HashMap<Character, Integer> hash = new HashMap<>();

        for (int i = 0; i < n; i++) {
            hash.put(s.charAt(i), hash.getOrDefault(s.charAt(i), 0) + 1);
        }

        // 排序
        List<Character> list = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : hash.entrySet()) {
            list.add(entry.getKey());
        }
        list.sort((c1, c2) -> hash.getOrDefault(c2, 0) - hash.getOrDefault(c1, 0));
        // 输出
        StringBuilder stringBuilder = new StringBuilder();
        for (Character c : list) {
            for (int i = 0; i < hash.getOrDefault(c, 0); i++) {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    public static String frequencySort1(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int n = s.length();
        // ASCII一共就128个字符
        int[] hash = new int[128];

        for (int i = 0; i < n; i++) {
            hash[s.charAt(i)]++;
        }

        List<Character> list = new ArrayList<>();
        for (int i = 0; i < 128; i++) {
            // 如果hash里面存的对应的ascii字符次数不为0
            // 则放入对应字符到list中，直接把int转成char即可
            if (hash[i] != 0) {
                list.add((char) i);
            }
        }
        // 按照出现次数排序
        list.sort((c1, c2) -> hash[c2] - hash[c1]);
        // 输出
        StringBuilder stringBuilder = new StringBuilder();
        for (Character c : list) {
            for (int i = 0; i < hash[c]; i++) {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    public static String frequencySort2(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int n = s.length();
        // ASCII一共就128个字符
        int[] hash = new int[128];
        for (int i = 0; i < n; i++) {
            hash[s.charAt(i)]++;
        }

        // 使用大顶堆存储字符出现的次数
        // 构造一个大顶堆 (优先队列，且出栈顺序降序)
        // 入堆的是字符，优先级是字符的出现次数
        PriorityQueue<Character> heap = new PriorityQueue<>((c1, c2) -> hash[c2] - hash[c1]);
        // 字符插入大顶堆
        for (int i = 0; i < 128; i++) {
            if (hash[i] != 0) {
                heap.offer((char) i);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        // 大顶堆，堆顶元素依次出堆
        while (!heap.isEmpty()) {
            char c = heap.poll();
            while (hash[c] > 0) {
                stringBuilder.append(c);
                hash[c]--;
            }
        }
        return stringBuilder.toString();
    }
}