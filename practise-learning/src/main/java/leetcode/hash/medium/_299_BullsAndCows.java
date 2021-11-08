package leetcode.hash.medium;

import java.util.HashMap;
import java.util.Map;

public class _299_BullsAndCows {

    public static void main(String[] args) {
        //        System.out.println(getHint("5410", "7401"));
        System.out.println(getHint("1807", "7810"));
        System.out.println(getHint("1123", "0111"));
        System.out.println(getHint("0", "1"));
        System.out.println(getHint("1", "1"));
    }

    public static String getHint(String secret, String guess) {
        int n = secret.length();
        // hash计数
        HashMap<Character, Integer> shash = new HashMap<>(n);
        HashMap<Character, Integer> ghash = new HashMap<>(n);
        int bulls = 0;
        int cows = 0;
        // 遍历密码串
        for (int i = 0; i < n; i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else {
                shash.put(secret.charAt(i), shash.getOrDefault(secret.charAt(i), 0) + 1);
                ghash.put(guess.charAt(i), ghash.getOrDefault(guess.charAt(i), 0) + 1);
            }
        }
        // 遍历密码串的hash结果
        for (Map.Entry<Character, Integer> entry : shash.entrySet()) {
            if (ghash.containsKey(entry.getKey())) {
                cows += Math.min(ghash.get(entry.getKey()), entry.getValue());
            }
        }
        return bulls + "A" + cows + "B";
    }
}
