package daimasuixianglu._4_string;

public class _6_ImplementStrStr {

    public static void main(String[] args) {
        System.out.println(strStr1("mississippi", "issip"));
    }

    public static int strStr(String haystack, String needle) {
        // needle为空串返回0
        if ("".equals(needle)) {
            return 0;
        }
        int m = haystack.length();
        int n = needle.length();
        if (n > m) {
            return -1;
        }
        // 双指针匹配
        int p = 0, q = 0;

        char[] ch = haystack.toCharArray();
        char[] cn = needle.toCharArray();
        while (p < m && q < n) {
            if (ch[p] != cn[q]) {
                p++;
            } else {
                int temp = p;
                // 尝试进行匹配
                while (p < m && q < n && ch[p] == cn[q]) {
                    p++;
                    q++;
                }
                // q走到了needle的末尾，找到了
                if (q == n) {
                    return p - n;
                } else {
                    // 没找到重置pq指针
                    q = 0;
                    p = temp + 1;
                }
            }
        }
        return -1;
    }

    public static int strStr1(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }
        int m = haystack.length();
        int n = needle.length();
        if (n > m) {
            return -1;
        }
        char[] ch = haystack.toCharArray();
        char[] cn = needle.toCharArray();

        // 主串最多遍历到m-n的位置
        for (int i = 0; i <= m - n; i++) {
            // 尝试匹配
            if (ch[i] == cn[0]) {
                // 扫描模式串
                int j = 0;
                for (; j < n; j++) {
                    // 不匹配则直接退出
                    if (ch[i + j] != cn[j]) {
                        break;
                    }
                }
                // 模式串扫描结束时，判断j有没有到达模式串末尾
                if (j == n) {
                    return i;
                }
            }
        }
        return -1;
    }
}
