package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/20
 * Time: 19:14
 * Description:
 */

public class _85_验证IP地址 {

    public static void main(String[] args) {
        // System.out.println(solve("172.16.254.1"));
        System.out.println(solve("2001:0db8:85a3:0:0:8A2E:0370:7334:"));
        // System.out.println(solve("256.256.256.256"));

    }

    public static String solve(String IP) {
        if (ipv4(IP)) {
            return "IPv4";
        } else if (ipv6(IP)) {
            return "IPv6";
        }
        return "Neither";
    }

    private static boolean ipv4(String IP) {
        String[] ips = IP.split("\\.");
        if (ips.length == 1 || ips.length != 4) {
            return false;
        }
        for (String ip : ips) {
            // 排除空串 ""
            if (ip.length() == 0) {
                return false;
            }
            // 排除前导 0
            if (ip.charAt(0) == '0' && ip.length() > 1) {
                return false;
            }
            // 检查是否有其他字符出现
            for (int i = 0; i < ip.length(); i++) {
                if (ip.charAt(i) < '0' || ip.charAt(i) > '9') {
                    return false;
                }
            }
            // 检查是否在 0~255 之间
            int num = Integer.parseInt(ip);
            if (num < 0 || num > 255) {
                return false;
            }
        }
        return true;
    }

    private static boolean ipv6(String IP) {
        String[] ips = IP.split(":", -1);
        if (ips.length == 1 || ips.length != 8) {
            return false;
        }
        for (String ip : ips) {
            // 检查长度不能超过 4，检查空串
            if (ip.length() == 0 || ip.length() > 4) {
                return false;
            }
            // 检查每位是否有数字，大小写字母其他的字符出现
            for (int i = 0; i < ip.length(); i++) {
                boolean check = ((ip.charAt(i) >= '0' && ip.charAt(i) <= '9')
                        || (ip.charAt(i) >= 'a' && ip.charAt(i) <= 'f')
                        || (ip.charAt(i) >= 'A' && ip.charAt(i) <= 'F'));
                if (!check) {
                    return false;
                }
            }
        }
        return true;
    }
}
