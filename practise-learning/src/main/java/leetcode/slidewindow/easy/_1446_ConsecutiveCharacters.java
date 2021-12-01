package leetcode.slidewindow.easy;

public class _1446_ConsecutiveCharacters {

    public static void main(String[] args) {
        //        System.out.println(maxPower("leetcode"));
        //        System.out.println(maxPower("abbcccddddeeeeedcba"));
        //        System.out.println(maxPower("triplepillooooow"));
        //        System.out.println(maxPower("hooraaaaaaaaaaay"));
        //        System.out.println(maxPower("tourist"));

        System.out.println(maxPower("cc"));
    }

    public static int maxPower(String s) {
        // 滑动窗口
        // 每次需要移动左端点的时候计算当前窗口的长度
        int l = 0, r = 0;
        int len = 1;
        int n = s.length();
        while (r < n - 1) {
            // 如果r和r+1相等，则移动r=r+1
            if (s.charAt(r) == s.charAt(r + 1)) {
                r = r + 1;
            }
            // 如果r和r+1不相等，则移动l=r+1，r=r+1，并统计窗口长度
            else {
                // 统计长度
                int tlen = r - l + 1;
                len = Math.max(len, tlen);
                // l和r同时移动
                l = r + 1;
                r = r + 1;
            }
        }
        return Math.max(r - l + 1, len);
    }
}
