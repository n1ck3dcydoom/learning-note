package leetcode.array.easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class _500_KeyboardRow {

    public static void main(String[] args) {
        String[] s1 = new String[]{"Hello", "Alaska", "Dad", "Peace"};
        String[] r1 = findWords(s1);
        for (String str : r1) {
            System.out.print(str + " ");
        }
        System.out.println("\n");

        String[] s2 = new String[]{"okm"};
        String[] r2 = findWords(s2);
        for (String str : r2) {
            System.out.print(str + " ");
        }
        System.out.println("\n");

        String[] s3 = new String[]{"adsdf", "sfd"};
        String[] r3 = findWords(s3);
        for (String str : r3) {
            System.out.print(str + " ");
        }
        System.out.println("\n");
    }

    public static String[] findWords(String[] words) {
        HashSet<Character> set1 = new HashSet<>();
        String raw1 = "qwertyuiop";
        for (int i = 0; i < raw1.length(); i++) {
            set1.add(raw1.charAt(i));
        }
        HashSet<Character> set2 = new HashSet<>();
        String raw2 = "asdfghjkl";
        for (int i = 0; i < raw2.length(); i++) {
            set2.add(raw2.charAt(i));
        }
        HashSet<Character> set3 = new HashSet<>();
        String raw3 = "zxcvbnm";
        for (int i = 0; i < raw3.length(); i++) {
            set3.add(raw3.charAt(i));
        }

        List<String> res = new ArrayList<>();
        for (String word : words) {
            String wordt = word.toLowerCase();
            boolean f1 = false;
            boolean f2 = false;
            boolean f3 = false;
            for (int i = 0; i < wordt.length(); i++) {
                if (set1.contains(wordt.charAt(i))) {
                    f1 = true;
                }
                if (set2.contains(wordt.charAt(i))) {
                    f2 = true;
                }
                if (set3.contains(wordt.charAt(i))) {
                    f3 = true;
                }
                if ((f1 && f2) || (f1 && f3) || (f2 && f3)) {
                    break;
                }
            }
            if ((f1 && !f2 && !f3) || (!f1 && f2 && !f3) || (!f1 && !f2 && f3)) {
                res.add(word);
            }
        }
        return res.toArray(new String[0]);
    }
}