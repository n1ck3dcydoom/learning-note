package leetcode.array.easy;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 未知 整数数组 arr 由 n 个非负整数组成。
 * <p>
 * 经编码后变为长度为 n - 1 的另一个整数数组 encoded ，其中 encoded[i] = arr[i] XOR arr[i + 1] 。例如，arr = [1,0,2,1] 经编码后得到 encoded = [1,2,3] 。
 * <p>
 * 给你编码后的数组 encoded 和原数组 arr 的第一个元素 first（arr[0]）。
 * <p>
 * 请解码返回原数组 arr 。可以证明答案存在并且是唯一的。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：encoded = [1,2,3], first = 1
 * 输出：[1,0,2,1]
 * 解释：若 arr = [1,0,2,1] ，那么 first = 1 且 encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
 * 示例 2：
 * <p>
 * 输入：encoded = [6,2,7,3], first = 4
 * 输出：[4,2,0,7,4]
 * <p>
 * 提示：
 * <p>
 * 2 <= n <= 104
 * encoded.length == n - 1
 * 0 <= encoded[i] <= 105
 * 0 <= first <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-xored-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/5/6 22:59
 **/
public class _1720_DecodeXORedArray {
    public int[] decode(int[] encoded, int first) {
        // 已知密文encode数组和明文第一个元素decode[0]

        // 明文长度比密文长度多1
        int n = encoded.length;
        int[] decode = new int[n + 1];
        decode[0] = first;

        // 遍历明文
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                decode[i] = first ^ encoded[i - 1];
            } else {
                decode[i] = encoded[i - 1] ^ decode[i - 1];
            }
        }
        return decode;
    }
}