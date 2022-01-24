package daimasuixianglu._3_hash;

import java.util.HashSet;
import java.util.Set;

public class _7_HappyNumber {

    public static void main(String[] args) {
        // System.out.println(isHappy(19));
        System.out.println(isHappy(2));
    }

    public static boolean isHappy(int n) {
        // 使用hash表记录已经出现过的组合
        // 如果有重复则表示不是快乐数，否则一直找到1为止
        Set<Integer> hash = new HashSet<>();
        while (n != 1) {
            int sum = 0;
            while (n != 0) {
                int t = n % 10;
                sum += t * t;
                n /= 10;
            }
            if (hash.contains(sum)) {
                return false;
            }
            hash.add(sum);
            n = sum;
        }
        return true;
    }
}
