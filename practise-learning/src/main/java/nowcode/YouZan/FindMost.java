package nowcode.YouZan;

import java.util.*;

/**
 * Created by n!Ck
 * Date: 2019-02-06
 * Time: 14:39
 * Description:
 * 给定一个数组A[n], 定义数组的众数 ( Majority Element) 为数组中出现次数超过 n/2 次的元素
 * 假设数组A[n]非空且一定存在众数, 请设计算法找到该众数并输出.
 *
 * 解题思路：
 * 统计数组内每个数字出现的频率（Map），然后排序后找到频率最高的数，查看这个数是否为众数
 */
public class FindMost {
    public static void main(String[] args) {
        String line;
        try (Scanner scanner = new Scanner(System.in)) {
            line = scanner.nextLine();
        }

        line = line.substring(1, line.length() - 1);
        String[] array = line.split(",");
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (String str : array) {
            Integer number = Integer.valueOf(str);
            if (!frequencyMap.containsKey(number)) {
                frequencyMap.put(number, 1);
            } else {
                frequencyMap.put(number, frequencyMap.get(number) + 1);
            }
        }

        // 对hashMap按照value排序
        List<Map.Entry<Integer, Integer>> enfolds = new ArrayList<>(frequencyMap.entrySet());
        enfolds.sort((value1, value2) -> value2.getValue() - value1.getValue());

        System.out.println(enfolds.get(0).getKey());
    }
}
