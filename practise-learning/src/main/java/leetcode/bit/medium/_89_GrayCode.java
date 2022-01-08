package leetcode.bit.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by n!Ck
 * Date: 2022/1/8
 * Time: 20:31
 * Description:
 */

public class _89_GrayCode {

    public static void main(String[] args) {
        // grayCode(2);
        grayCode(3);
    }

    public static List<Integer> grayCode(int n) {
        // // n=2时的格雷码
        // // 00 01 11 10
        // // n=3时的格雷码
        // // 之前的n=2的数前面补一位0得到
        // // 000 001 011 010
        // // 在每个数前面补一位1得到
        // // 100 101 111 110
        // // 直接拼接后有[000 001 011 010][100 101 111 110]，发现010和100不止一位不相同
        // // 逆序后拼接有[000 001 011 010][110 111 101 100]，满足题意
        // // 综上所述，有n为格雷码等于n-1位格雷码前面首位补1后逆序拼接得到
        // List<Integer> res=new ArrayList<>();
        // // 1位格雷码有两个数0和1
        // res.add(0);
        // res.add(1);
        // for(int i=2;i<=n;i++)
        // {
        //     // 构造第n位格雷码时，将前n-1位格雷码逆序后首位补1
        //     for(int j=res.size()-1;j>=0;j--)
        //     {
        //
        //     }
        // }

        // 算了，我直接背公式
        // g(i)=i xor i >> 1
        List<Integer> res = new ArrayList<>();
        // n位格雷码含有2^n个数，即1<<n个数
        for (int i = 0; i < 1 << n; i++) {
            res.add(i ^ (i >> 1));
        }
        return res;
    }
}
