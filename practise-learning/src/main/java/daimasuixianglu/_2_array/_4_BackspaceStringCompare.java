package daimasuixianglu._2_array;

class Solution {

    public static void main(String[] args) {
        String s1 = "ab#c";
        String s2 = "ad#c";
        System.out.println(backspaceCompare(s1, s2));

        String s3 = "ab##";
        String s4 = "c#d#";
        System.out.println(backspaceCompare(s3, s4));

        String s5 = "a##c";
        String s6 = "#a#c";
        System.out.println(backspaceCompare(s5, s6));

        String s7 = "a#c";
        String s8 = "b";
        System.out.println(backspaceCompare(s7, s8));
    }

    public static boolean backspaceCompare(String s, String t) {
        // 双指针
        char[] ss = s.toCharArray();
        char[] ts = t.toCharArray();
        int l1 = delete(ss);
        int l2 = delete(ts);
        if (l1 != l2) {
            return false;
        }
        for (int i = 0; i < l1; i++) {
            if (ss[i] != ts[i]) {
                return false;
            }
        }
        return true;
    }

    private static int delete(char[] s) {
        int n = s.length;
        int l = 0;
        for (int r = 0; r < n; r++) {
            if (s[r] != '#') {
                s[l++] = s[r];
            } else {
                l = l - 1 < 0 ? 0 : l - 1;
            }
        }
        return l;
    }
}