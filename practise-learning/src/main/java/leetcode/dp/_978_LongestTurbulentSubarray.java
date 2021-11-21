package leetcode.dp;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
 * <p>
 * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 * <p>
 * 返回 A 的最大湍流子数组的长度。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[9,4,2,10,7,8,8,1,9]
 * 输出：5
 * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
 * 示例 2：
 * <p>
 * 输入：[4,8,12,16]
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：[100]
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 40000
 * 0 <= A[i] <= 10^9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-turbulent-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/12/15 20:22
 **/
public class _978_LongestTurbulentSubarray {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 1, 0, 1, 0, 1, 1, 0, 0};
        System.out.println(maxTurbulenceSize0(arr));
    }

    /**
     * 定义dp[i]为 以第i个元素结尾的最长湍流数组的长度
     * 选择arr[i]的时候，需要和arr[i-1]判断是否相等
     * 如果不等，则可以选择arr[i-1]和arr[i]构成长度为2的湍流数组
     * 如果相等，则只能选择arr[i]本身构成长度为1的湍流数组
     * <p>
     * 若 arr[i-2] > arr[i-1] && arr[i-1] < arr[i] 即下上，则选择arr[i]加入当前数组，dp[i]=dp[i-1]+1
     * 否则 dp[i]=1，由于arr[i]和arr[i-1]不连续了，所以选择arr[i]自己当作新的湍流数组长度为1
     * <p>
     * 若 arr[i-2] < arr[i-1] && arr[i-1] > arr[i] 即上下，则选择arr[i]加入当前数组，dp[i]=dp[i-1]+1
     * 否则 dp[i]=1，由于arr[i]和arr[i-1]不连续了，所以选择arr[i]自己当作新的湍流数组长度为1
     */
    public static int maxTurbulenceSize(int[] arr) {
        if (arr == null) {
            return 0;
        }
        int n = arr.length;
        if (n <= 1) {
            return n;
        }
        // 定义dp[i]为 以第i个元素结尾的最长湍流数组的长度
        int[] dp = new int[n];
        // 设定初始值
        dp[0] = 1; //第一个元素一定能构成一个湍流数组，元素只有它自己本身，长度为1
        // 根据分析，选择第i个元素是否能够构成新的湍流数组，依赖i-2和i-1和i三个元素，所以dp[1]也是初始值
        dp[1] = arr[0] == arr[1] ? 1 : 2; // 如果第1个和第2个元素相等，则dp[1]只能选择自己，否则一定能够成湍流数组，长度为2
        // 最终dp[i]的最大值就是题解
        int max = Math.max(dp[0], dp[1]);
        // 从第三个元素开始遍历arr构造dp数组
        for (int i = 2; i < n; i++) {
            // 首先需要判断dp[i]的初始状态，如果arr[i]和arr[i-1]不相等，那么从i-1选择i一定能够成一个长度为2的湍流数组
            // 所以dp[i]的初始长度就是2
            dp[i] = arr[i - 1] == arr[i] ? 1 : 2;
            if (arr[i - 2] > arr[i - 1] && arr[i] > arr[i - 1]) {
                // 下上型湍流数组，选择arr[i]加入dp[i]
                dp[i] = dp[i - 1] + 1;
            } else if (arr[i - 2] < arr[i - 1] && arr[i] < arr[i - 1]) {
                // 上下型湍流数组，选择arr[i]加入dp[i]
                dp[i] = dp[i - 1] + 1;
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static int maxTurbulenceSize0(int[] arr) {
        if (arr == null) {
            return 0;
        }
        int n = arr.length;
        if (n <= 1) {
            return n;
        }
        if (n == 2) {
            return arr[0] == arr[1] ? 1 : 2;
        }
        // 标志位，判断前两个元素的上升或者下降情况
        // flag = 1 表示前两个元素是上升
        // flag = -1 表示前两个元素是下降
        // flag = 0 表示前两个元素相等
        // 用标志位的-1和1更替长度表示最长湍流数组的长度
        int flag = Integer.compare(arr[1], arr[0]);
        // 当前和前一个元素的上升或下降情况
        int current = 0;
        // 当前湍流数组的起点 (滑动窗口的左端，i是滑动窗口的右端)
        int pos = 0;
        // 湍流数组的最大长度
        int max = 0;
        for (int i = 2; i < n; i++) {
            current = Integer.compare(arr[i], arr[i - 1]);
            // 如果i和i-1相等，窗口左端调整到和右端重合
            if (current == 0) {
                pos = i;
            }
            // i-2和i-1的符号，和i-1和i的符号不异号
            else if (Integer.compare(arr[i - 1], arr[i - 2]) * current >= 0) {
                // 因为current一定不等于0，即使前面i-2和i-2相等，子湍流数组的长度也一定有2
                // 移动左端口到右端口前一个位置
                pos = i - 1;
            }
            // 如果异号，则左端口不动，计算当前子湍流数组的长度
            max = Math.max(max, i - pos + 1);
        }
        //
        //
        //            if (flag == 0 && current == 0) {
        //                pos = i;
        //            } else if (flag * current >= 1) {
        //                pos = i - 1;
        //            }
        //            max = Math.max(max, i - pos + 1);
        //            // 更新flag
        //            flag = current;
        return max;
    }
}