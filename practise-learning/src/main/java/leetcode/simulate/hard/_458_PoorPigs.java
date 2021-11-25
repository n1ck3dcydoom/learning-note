package leetcode.simulate.hard;

public class _458_PoorPigs {

    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        // 1只猪经过1轮测试后，只有两个状态，死或生
        // x只猪经过1论测试后，每只猪2个状态，x只猪总计能够组成2^x个状态
        //
        // 1只猪进经过n轮测试后，有n+1个状态
        // 第1轮死掉，或者第2轮死掉，或者第3轮死掉，，，或者第n轮死掉，或者n轮后仍然存活
        // x只猪经过n轮测试后，有(n+1)^x个状态
        //
        // 其中每个状态决定某个桶是否有毒
        // 所以x只猪经过n轮测试，能够检测(n+1)^x个桶

        // 令b = buckets  n = minutesToTest / mintusToDie
        // 可得(n+1)^x >= b
        // 两边对n+1取对数可得 x >= log(n+1)^b
        // 右边做以e为底的换底操作可得 x >= log(e)^b / log(e)^(n+1)
        // x为正整数，求x的最小值，即右边的向上取整


        // 最多测试轮数
        int n = minutesToTest / minutesToDie + 1;
        return (int) Math.ceil(Math.log(buckets) / Math.log(n + 1));
    }
}
