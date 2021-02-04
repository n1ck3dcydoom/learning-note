package leetcode.dp.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zhanglei
 * @version 1.0
 * @description leetcode
 * 给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,6,5,1,8]
 * 输出：18
 * 解释：选出数字 3, 6, 1 和 8，它们的和是 18（可被 3 整除的最大和）。
 * 示例 2：
 * <p>
 * 输入：nums = [4]
 * 输出：0
 * 解释：4 不能被 3 整除，所以无法选出数字，返回 0。
 * 示例 3：
 * <p>
 * 输入：nums = [1,2,3,4,4]
 * 输出：12
 * 解释：选出数字 1, 3, 4 以及 4，它们的和是 12（可被 3 整除的最大和）。
 *  
 * 提示：
 * <p>
 * 1 <= nums.length <= 4 * 10^4
 * 1 <= nums[i] <= 10^4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/greatest-sum-divisible-by-three
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/2/3 17:09
 **/
public class _1262_GreatestSumDivisibleByThree {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 6, 5, 1, 8};
        System.out.println(maxSumDivThree(nums));
    }

    public static int maxSumDivThree(int[] nums) {
        // 应该用什么思路去思考一道题？
        // 第一个想法就是，既然是求能被3整除的最大和，那么我一来就把数组求和，然后根据某些条件减去一些数后，就得到最大和
        // 那么问题来了，如果和刚好被3整除，这就是答案
        // 如果和不能被3整除呢？应该怎么继续走下去，思路应该如何发散？
        // 思考：一个数如果不能够被3整除，那么它除以3有什么情况？
        // 1、余0，即能够被3整除
        // 2、余1
        // 3、余2
        // 继续思考：如果数组的和除以3余1，应该通过怎样的筛选得到去掉某些数后的最大和呢？
        // 什么样的情况下会出现除以3余1的现象现象呢，4/3=1..1  (4+3)/3=2...1  (4+3+1)/3=2..2
        // 8/3=2...2  如果8减去一个本身除以3余1的数呢，(8-1)/3=2...1  结果余1了
        // 如果7再减去一个本身除以3余1的数呢，(7-4)/3=1 结果余0整除了

        // 总结：如果数组和除以3余1，我们可以通过数组和减去一个本身除以3余1的数，那么剩下的数的和就能够被3整除
        //      同理，减去两个除以3余2的数，相当于余数少了4，相当于减去了一个除以3余1的数，同理也能够得到剩下的和能够被3整除

        // 同理可得，如果数组和除以3余2，可以减去一个本身除以3余2的数，也可以减去两个除以3余1的数，这样余数也少了2，剩下的数的和能够被3整除

        // 为了保证数组的和最大，在减去对应的数的时候，应该选择最小的数或者最小的和次小的两个数

        int n = nums.length;
        int sum = 0;
        // 余1的数
        List<Integer> mod1 = new ArrayList<>();
        // 余2的数
        List<Integer> mod2 = new ArrayList<>();
        for (int num : nums) {
            if (num % 3 == 1) {
                mod1.add(num);
            } else if (num % 3 == 2) {
                mod2.add(num);
            }
            sum += num;
        }
        // 对mod1和mod2排序
        Collections.sort(mod1);
        Collections.sort(mod2);

        // 从最大和中依次剔除一些数，让sum能够被3整除，或者sum=0

        int temp = 0;
        // 如果sum已经能够被3整除了，直接返回sum
        if (sum % 3 == 0) {
            return sum;
        }
        // 如果sum除以3余1，则剔除一个余1的数，或者剔除两个余2的数
        else if (sum % 3 == 1) {
            // 余1数的集合不为空
            if (!mod1.isEmpty()) {
                temp = Math.max(temp, sum - mod1.get(0));
            }
            // 余2的数集合个数大于等于2
            if (mod2.size() >= 2) {
                temp = Math.max(temp, sum - mod2.get(0) - mod2.get(1));
            }
        }
        // 如果sum除以3余2，则剔除一个余2的数，或者剔除两个余1的数
        else {
            if (mod1.size() >= 2) {
                temp = Math.max(temp, sum - mod1.get(0) - mod1.get(1));
            }
            if (!mod2.isEmpty()) {
                temp = Math.max(temp, sum - mod2.get(0));
            }
        }
        return temp;
    }
}