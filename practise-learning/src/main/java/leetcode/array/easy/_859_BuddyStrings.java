package leetcode.array.easy;

public class _859_BuddyStrings {


    public static void main(String[] args) {
        //        System.out.println(buddyStrings("ab", "ba"));
        //        System.out.println(buddyStrings("ab", "ab"));
        //        System.out.println(buddyStrings("aa", "aa"));
        //        System.out.println(buddyStrings("aaaabcaaa", "aaaacbaaa"));
        System.out.println(buddyStrings("abac", "abad"));
    }

    public static boolean buddyStrings(String s, String g) {
        int m = s.length();
        int n = g.length();
        // 如果长度不相等，返回false
        if (m != n) {
            return false;
        }
        // 如果两个串相等
        // 有重复字符返回true，否则返回false
        if (s.equals(g)) {
            int[] arr = new int[26];
            for (char c : s.toCharArray()) {
                arr[c - 'a']++;
                // 有重复字符就可以交换，返回true
                if (arr[c - 'a'] > 1) {
                    return true;
                }
            }
            return false;
        }
        // 如果两个串不相等
        // 检查 两处 不相等的地方能否通过交换得到
        int first = -1;
        int second = -1;
        int count = 2;
        for (int i = 0; i < m; i++) {
            if (count < 0) {
                break;
            }
            if (s.charAt(i) != g.charAt(i)) {
                if (count == 2) {
                    first = i;
                }
                if (count == 1) {
                    second = i;
                }
                count--;
            }
        }
        // 不止两处不相同，交换一次无法复原，返回false
        if (count != 0) {
            return false;
        }
        // 检查交换后是否相等
        //        char[] ss = s.toCharArray();
        //        char temp = ss[first];
        //        ss[first] = ss[second];
        //        ss[second] = temp;
        //        String ns = new String(ss);
        //        return ns.equals(g);

        // 不必重新构建字符串，只需要检查1st和2nd是否相等即可
        // 空间复杂度居然更高，调用了更多的方法???
        return s.charAt(first) == g.charAt(second) && s.charAt(second) == g.charAt(first);
    }
}
