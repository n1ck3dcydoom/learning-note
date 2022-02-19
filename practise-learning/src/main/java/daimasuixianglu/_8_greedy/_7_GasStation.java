package daimasuixianglu._8_greedy;

public class _7_GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // 每到达一个加油站，就加一次油
        // 使用totalGas记录从i开始到第k个加油站总共能获得的汽油量
        // 使用totalCost记录从i开始到第k个加油站总共需要消耗的汽油量
        // 暴力搜索从每个加油作为起点开始，能否走完一圈，且中间不断油
        int n = gas.length;
        // 从第0个加油站开始，搜索每个加油站
        for (int i = 0; i < n; i++) {
            // 从i出发前往i+1后的剩余油量
            int lastGas = gas[i] - cost[i];
            // 如果剩余油量小于0，则说明无法从i前往i+1，遍历下一个起点
            if (lastGas < 0) {
                continue;
            }
            // 计算环路的下一个位置，不包括i
            int next = (i + 1) % n;
            // 如果能够走完一圈，next回到i
            // 如果next不等于i，则说明没有走完一圈，继续尝试前往下一个加油站
            while (next != i && lastGas > 0) {
                lastGas += gas[next] - cost[next];
                // 更新下一个油站的索引
                next = (next + 1) % n;
            }
            // 当某一轮的next等于i之后，此时还未前往i
            // 因为while循环先计算前往i的需要油量，再更新索引next
            // 而更新next之后就跳出while判断了，还并未前往i
            if (lastGas >= 0 && next == i) {
                return i;
            }
        }
        return -1;
    }
}
