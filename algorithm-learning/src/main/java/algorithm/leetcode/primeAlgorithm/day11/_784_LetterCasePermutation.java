package algorithm.leetcode.primeAlgorithm.day11;

import java.util.ArrayList;
import java.util.List;

public class _784_LetterCasePermutation {

    public static void main(String[] args) {
        String s1 = "a1b2";
        String s2 = "3z4";
        String s3 = "12345";
        List<String> l1 = letterCasePermutation(s1);
        for (String str : l1) {
            System.out.print(str + " ");
            System.out.println("\n");
        }
        List<String> l2 = letterCasePermutation(s2);
        for (String str : l2) {
            System.out.print(str + " ");
            System.out.println("\n");
        }
        List<String> l3 = letterCasePermutation(s3);
        for (String str : l3) {
            System.out.print(str + " ");
            System.out.println("\n");
        }
    }

    public static List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        bfs(res, new ArrayList<>(), s, 0);
        return res;
    }

    private static void bfs(List<String> res, List<Character> path, String s, int index) {
        if (path.size() == s.length()) {
            StringBuilder sb = new StringBuilder();
            for (char c : path) {
                sb.append(c);
            }
            res.add(sb.toString());
        }
        for (int i = index; i < s.length(); i++) {
            char c = s.charAt(i);
            // 如果是数字则直接加入当前路径中
            if (Character.isDigit(c)) {
                path.add(c);
                if (path.size() == s.length()) {
                    StringBuilder sb = new StringBuilder();
                    for (char ch : path) {
                        sb.append(ch);
                    }
                    res.add(sb.toString());
                }
                continue;
            }
            // 将当前字母保持原样加入路径进行递归
            // char nc = Character.isLowerCase(c) ? Character.toUpperCase(c) :
            // Character.toLowerCase(c);
            path.add(c);
            // 继续递归
            bfs(res, path, s, i + 1);
            // 撤销上一个选择(字母选择)
            if (Character.isDigit(path.get(path.size() - 1))) {
                while (Character.isDigit(path.get(path.size() - 1))) {
                    path.remove(path.size() - 1);
                }
            }
            path.remove(path.size() - 1);

            // 将当前字母变换大小写后加入路径进行递归
            char nc = Character.isLowerCase(c) ? Character.toUpperCase(c) : Character.toLowerCase(c);
            path.add(nc);
            // 继续递归
            bfs(res, path, s, i + 1);
            // 撤销上一个选择(字母选择)
            if (Character.isDigit(path.get(path.size() - 1))) {
                while (Character.isDigit(path.get(path.size() - 1))) {
                    path.remove(path.size() - 1);
                }
            }
            path.remove(path.size() - 1);
        }
    }
}
