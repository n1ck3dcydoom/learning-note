package leetcode.simulate.easy;

import java.util.ArrayList;
import java.util.List;

public class _1078_OccurrencesAfterBigram {

    public static void main(String[] args) {
        String s = "we will we will rock you";
        String[] r = findOcurrences(s, "we", "will");
        for (String str : r) {
            System.out.print(str + " ");
        }
    }


    public static String[] findOcurrences(String text, String first, String second) {
        String[] split = text.split(" ");
        int n = split.length;

        List<String> res = new ArrayList<>();
        for (int i = 0; i + 1 < n; i++) {
            if (split[i].equals(first) && split[i + 1].equals(second)) {
                if (i + 2 < n) {
                    res.add(split[i + 2]);
                }
            }
        }
        return res.toArray(new String[0]);
    }
}
