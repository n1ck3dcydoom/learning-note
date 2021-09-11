package algorithm.leetcode.primeDataStructure.day6;

public class _387_FirstUniqueCharacterInString {

    public static void main(String[] args) {
        String s = "loveleetcode";
        System.out.println(firstUniqChar1(s));
    }

    public static int firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        // 使用数组保存26个字母出现的索引
        // index数组全部初始化为-1
        // 遍历到某个字符ch时，如果index[ch] == -1，将index[ch]置为其索引
        // 再次遍历到字符ch时，如果index[ch] > -1，则将index[ch]置为-2
        int[] index = new int[26];
        for (int i = 0; i < 26; i++) {
            index[i] = -1;
        }
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (index[chars[i] - 'a'] == -1) {
                index[chars[i] - 'a'] = i;
            } else if (index[chars[i] - 'a'] > -1) {
                index[chars[i] - 'a'] = -2;
            }
        }
        for (int i = 0; i < chars.length; i++) {
            if (index[chars[i] - 'a'] > -1) {
                return i;
            }
        }
        return -1;
    }

    public static int firstUniqChar1(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        // 使用数组保存26个字母出现的索引
        // 思路没错，想复杂了
        // 优化下
        int[] index = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            index[chars[i] - 'a']++;
        }
        for (int i = 0; i < chars.length; i++) {
            if (index[chars[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
