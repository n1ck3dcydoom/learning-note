package leetcode.heap.hard;

import java.util.PriorityQueue;

/**
 * Created by n!Ck
 * Date: 2022/7/21
 * Time: 20:57
 * Description:
 */

public class _871_MinimumNumberOfRefuelingStops {

    public static void main(String[] args) {
        System.out.println(minRefuelStops(1, 1, new int[][]{}));
        System.out.println(minRefuelStops(100, 1, new int[][]{{10, 100}}));
        System.out.println(minRefuelStops(100, 10, new int[][]{{10, 60}, {20, 30}, {30, 30}, {60, 40}}));
    }

    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        // 要加油次数最少,采取贪心算法
        // 每次能够经过加油站,并不立即加油,而是把这部分有保存下来
        // 当车子没油时,从之前保存的油里面,选取最多的油一次性加完

        // 当车子没油可加,没有到达目的地,返回 -1
        // 如果能够到达目的地,返回最小加油次数

        // 问题:如何保存每次经过的加油站,使得后面需要加油的时候能够获取最大油量
        // 使用大顶堆保存每次加油站的油量,加油时移除堆顶元素

        // 最少加油次数
        int res = 0;
        // 使用大顶堆保存每次经过的加油站油量
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((v1, v2) -> v2 - v1);

        // 剩余油量
        int remaining = startFuel;
        // 已走长度
        int len = 0;
        // 当前已经路过哪几个加油站
        int pos = 0;
        // 只要车子还有油,而且没有到达目的地,就一直走
        while (remaining > 0) {
            // 一口气消耗完当前所有油量
            len += remaining;
            remaining = 0;
            if (len >= target) {
                break;
            }
            // 消耗 remaining 能够经过哪些加油站
            for (int i = pos; i < stations.length; i++) {
                if (stations[i][0] <= len) {
                    maxHeap.offer(stations[i][1]);
                    pos = i + 1;
                } else {
                    break;
                }
            }
            // 选取堆顶元素加油
            if (maxHeap.size() > 0) {
                remaining += maxHeap.poll();
                res++;
            }
        }
        return len >= target ? res : -1;
    }
}