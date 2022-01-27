package daimasuixianglu._4_string;

public class _4_ReverseWordsInString {

    public static void main(String[] args) {
        System.out.println(reverseWords("the sky is blue"));
    }

    public static String reverseWords(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        // 去掉开头空格
        int l = 0;
        while (l < n && cs[l] == ' ') {
            l++;
        }
        int r = n - 1;
        while (r >= 0 && cs[r] == ' ') {
            r--;
        }
        StringBuilder sb = new StringBuilder();
        while (l <= r) {
            int index = r;
            while (index >= l && cs[index] != ' ') {
                index--;
            }
            // 记录index+1和r之间的单词
            for (int i = index + 1; i <= r; i++) {
                sb.append(cs[i]);
            }
            // 记录空格
            if (l < index) {
                sb.append(' ');
            }
            // 去掉单词中间多余的空格
            while (index >= l && cs[index] == ' ') {
                index--;
            }
            // 更新r的位置
            r = index;
        }
        return sb.toString();
    }
}
