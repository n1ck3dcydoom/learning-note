package leetcode.simulate.medium;

import java.util.HashMap;

public class _423_ReconstructOriginalDigitsFromEnglish {

    public static void main(String[] args) {
        //        System.out.println(originalDigits("owoztneoer"));
        //        System.out.println(originalDigits("fviefruo"));
        System.out.println(originalDigits("ehigt"));
    }

    public static String originalDigits(String s) {
        // 统计所有出现的字符次数
        HashMap<Character, Integer> hash = new HashMap<>();
        for (char c : s.toCharArray()) {
            hash.put(c, hash.getOrDefault(c, 0) + 1);
        }
        // e	0 1 3 5 7 8 9
        // f	4 5
        // h	3 8
        // i	5 6 8 9
        // n	1 7 9
        // o	0 1 2 4
        // r	0 3 4
        // s	6 7
        // t	2 3 8
        // v	5 7

        // z	0
        // w	2
        // u	4
        // x	6
        // g	8

        // 0 2 4 6 8可以通过特征字符统计次数
        int[] res = new int[10];
        res[0] = hash.getOrDefault('z', 0);
        res[2] = hash.getOrDefault('w', 0);
        res[4] = hash.getOrDefault('u', 0);
        res[6] = hash.getOrDefault('x', 0);
        res[8] = hash.getOrDefault('g', 0);

        // 3可以通过h减去8的次数得到
        res[3] = hash.getOrDefault('h', 0) - res[8];
        // 5可以通过f减去4的次数得到
        res[5] = hash.getOrDefault('f', 0) - res[4];
        // 7可以通过s减去6的次数得到
        res[7] = hash.getOrDefault('s', 0) - res[6];
        // 1可以通过o减去0，2，4的次数得到
        res[1] = hash.getOrDefault('o', 0) - res[0] - res[2] - res[4];
        // 9可以通过i减去5，6，8的次数得到
        // 如果使用n计算，n在9里面出现过两次，要特殊处理
        res[9] = hash.getOrDefault('i', 0) - res[5] - res[6] - res[8];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            if (res[i] > 0) {
                for (int j = 0; j < res[i]; j++) {
                    sb.append(i);
                }
            }
        }
        return sb.toString();
    }

    //    public static String originalDigits(String s) {
    //        // zero  z
    //        // two   w
    //        // four  u
    //        // six   x
    //        // eight g
    //
    //        // one   o
    //        // three thre - h
    //        // five  five - f
    //
    //        // seven sevn - v
    //        // nine  nine - i
    //
    //        // 0 2 4 6 8 1 3 5 7 9
    //        // String[] dic = new String[]{"zero", "two", "four", "six", "eight", "one", "three", "five", "seven", "nine"};
    //        // char[] dic = new char[]{'z', 'w', 'u', 'x', 'g', 'o', 'h', 'f', 'v', 'i'};
    //
    //
    //        HashMap<Character, Integer> hash = new HashMap<>();
    //        // 统计所有出现的字符次数
    //        for (char c : s.toCharArray()) {
    //            hash.put(c, hash.getOrDefault(c, 0) + 1);
    //        }
    //        int[] res = new int[10];
    //        // 遍历字典，构造字符
    //        if (hash.containsKey('z') && hash.get('z') > 0) {
    //            int count = hash.get('z');
    //            res[0] = count;
    //            hash.remove('z');
    //            hash.put('e', hash.get('e') - count);
    //            hash.put('r', hash.get('r') - count);
    //            hash.put('o', hash.get('o') - count);
    //        }
    //        if (hash.containsKey('w') && hash.get('w') > 0) {
    //            int count = hash.get('w');
    //            res[2] = count;
    //            hash.remove('w');
    //            hash.put('t', hash.get('t') - count);
    //            hash.put('o', hash.get('o') - count);
    //        }
    //        if (hash.containsKey('u') && hash.get('u') > 0) {
    //            int count = hash.get('u');
    //            res[4] = count;
    //            hash.remove('u');
    //            hash.put('f', hash.get('f') - count);
    //            hash.put('o', hash.get('o') - count);
    //            hash.put('r', hash.get('r') - count);
    //        }
    //        if (hash.containsKey('x') && hash.get('x') > 0) {
    //            int count = hash.get('x');
    //            res[6] = count;
    //            hash.remove('x');
    //            hash.put('s', hash.get('s') - count);
    //            hash.put('i', hash.get('i') - count);
    //        }
    //        if (hash.containsKey('g') && hash.get('g') > 0) {
    //            int count = hash.get('g');
    //            res[8] = count;
    //            hash.remove('g');
    //            hash.put('e', hash.get('e') - count);
    //            hash.put('i', hash.get('i') - count);
    //            hash.put('h', hash.get('h') - count);
    //            hash.put('t', hash.get('t') - count);
    //        }
    //        if (hash.containsKey('o') && hash.get('o') > 0) {
    //            int count = hash.get('o');
    //            res[1] = count;
    //            hash.remove('o');
    //            hash.put('n', hash.get('n') - count);
    //            hash.put('e', hash.get('e') - count);
    //        }
    //        if (hash.containsKey('h') && hash.get('h') > 0) {
    //            int count = hash.get('h');
    //            res[3] = count;
    //            hash.remove('h');
    //            hash.put('t', hash.get('t') - count);
    //            hash.put('r', hash.get('r') - count);
    //            hash.put('e', hash.get('e') - count * 2);
    //        }
    //        if (hash.containsKey('f') && hash.get('f') > 0) {
    //            int count = hash.get('f');
    //            res[5] = count;
    //            hash.remove('f');
    //            hash.put('i', hash.get('i') - count);
    //            hash.put('v', hash.get('v') - count);
    //            hash.put('e', hash.get('e') - count);
    //        }
    //        if (hash.containsKey('v') && hash.get('v') > 0) {
    //            int count = hash.get('v');
    //            res[7] = count;
    //            hash.remove('v');
    //            hash.put('s', hash.get('s') - count);
    //            hash.put('e', hash.get('e') - count * 2);
    //            hash.put('n', hash.get('n') - count);
    //        }
    //        if (hash.containsKey('i') && hash.get('i') > 0) {
    //            int count = hash.get('i');
    //            res[9] = count;
    //            hash.remove('i');
    //            hash.put('n', hash.get('n') - count * 2);
    //            hash.put('e', hash.get('e') - count);
    //        }
    //        StringBuilder sb = new StringBuilder();
    //        for (int i = 0; i < 10; i++) {
    //            if (res[i] > 0) {
    //                for (int j = 0; j < res[i]; j++) {
    //                    sb.append(i);
    //                }
    //            }
    //        }
    //
    //        return sb.toString();
    //    }
}
