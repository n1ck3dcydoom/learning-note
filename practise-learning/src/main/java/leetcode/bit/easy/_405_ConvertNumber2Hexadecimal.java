package leetcode.bit.easy;

public class _405_ConvertNumber2Hexadecimal {

    private static final char[] hexmap = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static void main(String[] args) {
        System.out.println(toHex(26));
        System.out.println(toHex(-1));
        System.out.println(toHex(0));
        System.out.println(toHex(16));
    }

    public static String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        int[] bit = new int[32];
        for (int i = 0; i < 32; i++) {
            // &1 得到最后一位
            bit[31 - i] = num & 1;
            // num右移
            num = num >> 1;
        }
        // 每4位统计一次
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int hex = 0;
            // 4位二进制数转换为16进制
            hex += bit[i * 4 + 3] * 1;
            hex += bit[i * 4 + 2] * 2;
            hex += bit[i * 4 + 1] * 4;
            hex += bit[i * 4 + 0] * 8;
            sb.append(hexmap[hex]);
        }
        String raw = sb.toString();
        for (int i = 0; i < 8; i++) {
            if (raw.charAt(i) == '0') {
                continue;
            }
            return raw.substring(i);
        }
        return "0";
    }
}