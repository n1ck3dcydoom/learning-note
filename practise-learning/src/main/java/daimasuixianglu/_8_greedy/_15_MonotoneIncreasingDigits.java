package daimasuixianglu._8_greedy;

/**
 * Created by n!Ck
 * Date: 2022/2/21
 * Time: 22:15
 * Description:
 */

public class _15_MonotoneIncreasingDigits {

    public static void main(String[] args) {
        System.out.println(monotoneIncreasingDigits1(1234));
    }

    public static int monotoneIncreasingDigits(int n) {
        // 从前往后遍历，遇到开始下降的数nums[i]，则将nums[i]=nums[i-1]，且nums[i+1]开始的所有数全部赋值为9
        // 1232，第一个开始下降的数在3，则将nums[i]=3=nums[i]-1 -> 1222，且将nums[i+1]往后的所有数赋值为9 -> 1229
        // 12321 -> 12221 -> 12299
        // 如果第一个下降的位置前面还有和它相等的数，则需要往前寻找第一个
        // 13332 -> 12332 -> 12999

        if (n < 10) {
            return n;
        }
        String s = String.valueOf(n);
        char[] cs = s.toCharArray();
        int i = 0;
        for (; i < cs.length - 1; i++) {
            // 找到首次下降的位置i
            if (cs[i] > cs[i + 1]) {
                // 往前找到第一个cs[i]
                int j = i;
                for (; j >= 1; j--) {
                    if (cs[j] != cs[j - 1]) {
                        break;
                    }
                }
                i = j;
                break;
            }
        }
        // 如果i已经到达末尾，则不再需要后面的操作
        if (i == cs.length - 1) {
            return Integer.parseInt(new String(cs));
        }
        // 首次下降位置-1，不会出现0-1向前面借位的情况，因为0是最小的
        cs[i]--;
        // i+1开始全部赋值为9
        for (int j = i + 1; j < cs.length; j++) {
            cs[j] = '9';
        }
        return Integer.parseInt(new String(cs));
    }

    public static int monotoneIncreasingDigits1(int n) {
        // 正向遍历在找到了第一个下降位置之后，还需要倒回去找第一个出现的位置
        // 所以采用逆向遍历，再找到“最后”第一个下降位置（即正向遍历的第一个下降位置）之后，继续向前寻找最后一个等于下降位置的数
        if (n < 10) {
            return n;
        }
        String s = String.valueOf(n);
        char[] cs = s.toCharArray();
        // 如果没有找到下降位置，index的初始化为数组大小，第二个for循环将不会进入
        int index = cs.length;
        for (int i = cs.length - 2; i >= 0; i--) {
            if (cs[i] > cs[i + 1]) {
                // 找到下降位置后i后，cs[i]--，标记移动到i+1，因为从下降位置后面1位开始的所有数才变为9
                cs[i]--;
                index = i + 1;
            }
        }
        for (int j = index; j < cs.length; j++) {
            cs[j] = '9';
        }
        return Integer.parseInt(new String(cs));
    }
}
