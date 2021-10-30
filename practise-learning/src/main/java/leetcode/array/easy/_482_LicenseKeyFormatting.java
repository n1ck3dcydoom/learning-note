package leetcode.array.easy;

public class _482_LicenseKeyFormatting {

    public static void main(String[] args) {
                System.out.println(licenseKeyFormatting("5F3Z-2e-9-w", 4));
                System.out.println(licenseKeyFormatting("2-5g-3-J", 2));
                System.out.println(licenseKeyFormatting("2-4A0r7-4k", 3));
        System.out.println(licenseKeyFormatting("a-a-a-a-", 1));
    }

    public static String licenseKeyFormatting(String s, int k) {
        // 格式化后的第一个分组字符个数至少包含1个且小于等于k
        // 其他分组的字符个数必须等于k
        // 情况1，能够被k整除
        // 情况2，不能够被k整除，则减去余数c后恰好能够整除，第一个分组的长度为c，剩下s-c个能够被k整除

        // 第一步、提取字符
        StringBuilder raw = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if ('-' != s.charAt(i)) {
                raw.append(s.charAt(i));
            }
        }

        // 第二步、计算分组个数size
        // 如果len%k==0，则有count/k个分组
        // 否则有len/k + 1个分组
        int len = raw.length();
        int size = len % k == 0 ? len / k : len / k + 1;

        // 第三步、添加分隔符'-'
        // 共size个分组，需要添加size-1个分隔符'-'
        int index = len - 1;
        for (int i = 1; i <= size - 1; i++) {
            // 倒着往前面插入分隔符
            if (i == 1) {
                index = index - k + 1;
            } else {
                index = index - k;
            }
            raw.insert(index, "-");
        }
        return raw.toString().toUpperCase();
    }
}