package leetcode;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/9/26 23:02
 **/
public class _371_SumOfTwoIntegers {
    public int getSum(int a, int b) {
        // 基本位运算操作
        // 得到最后一位 a&1

        // 不允许使用+和-
        // 只能考虑位运算了
        //   3 = 0000 0011
        //   5 = 0000 0101
        // + 8 = 0000 1000

        // 其中二进制运算
        // 0+0 = 0
        // 0+1 = 1
        // 1+1 = (1)0 (产生进位1，实际上末尾还是0)
        // 可以总结出，异或就是个 "没有进位" 的加法

        // 什么情况下能够得到进位呢
        //   3 = 0000 0011
        //   5 = 0000 0101
        // &   = 0000 0001
        // 可以看到 3&5 在最后一位得到了进位1，但是其位置不对，正确的位置应该往前一位
        // 所以把 a&b 的结果左移1位，可以得到每一位相加后的正确进位位置

        // 这样把 (a&b) << 1 和 a^b 的结果相加，直到不再产生进位

        // 计算每次进位结果
        int carry = (a & b) << 1;
        // 计算无进位加法结果
        int sum = a ^ b;
        while (carry != 0) {
            // 保存旧的sum值，为了计算进位
            int tempSum = sum;
            // 将无进位加法结果和进位结果相加(仍然采用异或计算)
            sum = carry ^ sum;
            // 这里需要和以前旧的sum值计算新的进位结果
            carry = carry & tempSum;
        }
        return sum;
    }

    public int getSum1(int a, int b) {
        // 使用carry保存进位信息
        // 分情况讨论

        // 1、0+0
        // 结果为0，不产进位

        // 2、0+1
        // 结果为1，不产生进位

        // 3、1+1
        // 结果为(1)0，产生进位1，其末位为0

        int res = 0;
        // 当前位的结果
        // 1、当前位两个0，其计算结果依赖前一位进位carry，产生的进位为0
        // 若carry=1，则res=1，新的carry=0
        // 若carry=0，则res=0，新的carry=0
        // res=res|carry   carry=0

        // 2、当前位一个0一个1，其计算结果依赖前一位进位carry，产生的进位也依赖前一位
        // 若carry=1，则res=0，新的carry=1
        // 若carry=0，则res=1，新的carry=0
        // res=res|(~carry)   carry=carry
        // (~carry)会改变所有位的结果
        // 这里需要的是仅改变carry位的结果
        // 1^carry，和1进行异或运算
        // 其他位置是0则异或后仍然是0
        // 其他位置是1则异或后是0
        // carry每次有且只有1个1，所以1^carry能够对这个1进行取反操作!!

        // 3、当前位两个1，其计算结果依赖前一位进位carry，产生的进位为1
        // 若carry=1，则res=1，新的carry=1
        // 若carry=0，则res=0，新的carry=1
        // res=res|carry      carry=1

        // int为32位，每次操作一位，从低到高依次操作
        int carry = 0;
        for (int i = 0; i < 32; i++) {
            // 得到当前的a和b的最后一位
            int a1 = (a >> i) & 1;
            int b1 = (b >> i) & 1;

            if (a1 == 0 && b1 == 0) {
                res = res | (carry << i);
                carry = 0;
            } else if (a1 == 1 && b1 == 1) {
                res = res | (carry << i);
                carry = 1;
            } else {
                res = res | ((1 ^ carry) << i);
                // res = res | ((~carry) << i);
                //                carry = carry;
            }
        }
        return res;
    }
}