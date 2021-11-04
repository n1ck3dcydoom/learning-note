package leetcode.array.easy;

public class _434_NumberOfSegmentsInString {

    public static void main(String[] args) {
        System.out.println(countSegments("Hello, this is a test"));
        System.out.println(countSegments("   Hello this is a test"));
        System.out.println(countSegments("   Hello this is a test   "));
        System.out.println(countSegments("cccccccccc     "));
        System.out.println(countSegments("ssss dfsf     fsfsfsfs"));
    }

    public static int countSegments(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (' ' != s.charAt(i)) {
                while (i < s.length() && ' ' != s.charAt(i)) {
                    i++;
                }
                count++;
            }
        }
        return count;
    }
}
