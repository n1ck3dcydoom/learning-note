package leetcode.array.medium;

import java.util.HashMap;
import java.util.Map;

public class _869_ReorderedPowerOf2 {

    public static void main(String[] args) {
        System.out.println(reorderedPowerOf2(1));
        System.out.println(reorderedPowerOf2(10));
        System.out.println(reorderedPowerOf2(16));
        System.out.println(reorderedPowerOf2(24));
        System.out.println(reorderedPowerOf2(46));
    }

    public static boolean reorderedPowerOf2(int n) {
        // 暴力枚举Integer范围内的所有2的幂
        // 然后对比2的幂的数字长度和给定n的长度是否相等，且出现的数字和次数必须相等

        String ns = String.valueOf(n);
        HashMap<Integer, Integer> nmap = new HashMap<>();
        for (int i = 0; i < ns.length(); i++) {
            int num = ns.charAt(i) - '0';
            nmap.put(num, nmap.getOrDefault(num, 0) + 1);
        }

        int pow = 0;
        String pows = "";
        // 枚举位数小于等于给定n位数的所有2的幂
        for (int i = 0; pow < Integer.MAX_VALUE && pows.length() <= ns.length(); i++) {
            pow = (int) Math.pow(2, i);
            pows = String.valueOf(pow);
            // 位数相等后判断每个数字出现的次数是否相等
            if (pows.length() == ns.length()) {
                HashMap<Integer, Integer> powMap = new HashMap<>();
                for (int j = 0; j < pows.length(); j++) {
                    int num = pows.charAt(j) - '0';
                    powMap.put(num, powMap.getOrDefault(num, 0) + 1);
                }
                // 比较两个map的每个值
                boolean flag = true;
                for (Map.Entry<Integer, Integer> entry : nmap.entrySet()) {
                    if (!powMap.containsKey(entry.getKey())
                            || powMap.get(entry.getKey()) != entry.getValue()) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }
}