package leetcode.array.medium;

public class _165_CompareVersionNumbers {

    public static void main(String[] args) {
        System.out.println(compareVersion("1.01", "1.001"));
        System.out.println(compareVersion("1.0", "1.0.0"));
        System.out.println(compareVersion("0.1", "1.1"));
        System.out.println(compareVersion("1.0.1", "1"));
        System.out.println(compareVersion("7.5.2.4", "7.5.3"));
    }

    public static int compareVersion(String version1, String version2) {
        if (version1.equals(version2)) {
            return 0;
        }

        String[] s1 = version1.split("\\.");
        int n1 = s1.length;
        String[] s2 = version2.split("\\.");
        int n2 = s2.length;

        int n = Math.min(n1, n2);
        int i = 0;
        while (i < n) {
            int p = Integer.parseInt(s1[i]);
            int q = Integer.parseInt(s2[i]);
            if (p > q) {
                return 1;
            } else if (p < q) {
                return -1;
            }
            i++;
        }
        // 检查长的版本号剩余的修订位是否全部是0
        if (n1 > n2) {
            for (int j = i; j < n1; j++) {
                if (Integer.parseInt(s1[j]) > 0) {
                    return 1;
                }
            }
        } else {
            for (int j = i; j < n2; j++) {
                if (Integer.parseInt(s2[j]) > 0) {
                    return -1;
                }
            }
        }
        return 0;
    }
}