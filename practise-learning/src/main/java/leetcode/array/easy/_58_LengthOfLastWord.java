package leetcode.array.easy;

public class _58_LengthOfLastWord {

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("Hello World"));
        System.out.println(lengthOfLastWord("   fly me   to   the moon  "));
        System.out.println(lengthOfLastWord("luffy is still joyboy"));
    }

    public static int lengthOfLastWord(String s) {
        // 直接倒序遍历
        int res = 0;
        boolean flag = false;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ' && flag) {
                break;
            } else {
                if (s.charAt(i) != ' ') {
                    flag = true;
                    res++;
                }
            }
        }
        return res;
    }
}