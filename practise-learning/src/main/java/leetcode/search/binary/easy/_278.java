package leetcode.search.binary.easy;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 * <p>
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 * <p>
 * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：n = 5, bad = 4
 * 输出：4
 * 解释：
 * 调用 isBadVersion(3) -> false
 * 调用 isBadVersion(5) -> true
 * 调用 isBadVersion(4) -> true
 * 所以，4 是第一个错误的版本。
 * 示例 2：
 * <p>
 * 输入：n = 1, bad = 1
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= bad <= n <= 2^31 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-bad-version
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/8/4 15:30
 **/
public class _278 {
    public int firstBadVersion(int n) {
        // 二分查找，找到第一个满足题意的左边界

        // 搜索区间是[l,r]
        int l = 1, r = n;
        // 结束时r=l-1，搜索区间是[l,l-1]，搜索区间为空
        while (l <= r) {
            int mid = l + (r - l) / 2;
            // 如果不是一个错误版本，那么错误版本一定在mid的右边，收缩左边界
            if (!isBadVersion(mid)) {
                // mid已经搜索过，需要去掉
                l = mid + 1;
            }
            // 如果是一个错误版本，那么第一个错误版本一定出现在mid的左边，收缩右边界
            else if (isBadVersion(mid)) {
                // mid已经搜索过，需要去掉
                r = mid - 1;
            }
        }
        return l;
    }

    // The isBadVersion API is defined in the parent class VersionControl.
    // mock return : )
    boolean isBadVersion(int version) {
        return version % 2 == 0;
    }

}