package daimasuixianglu._3_hash;

public class _10_4Sum {
    
    public static void main(String[] args) {
        int[] n1 = new int[] { -1,1,1,1,-1 };
        int[] n2 = new int[] { 0,-1,-1,0,1 };
        int[] n3 = new int[] { -1,-1,1,-1,-1};
        int[] n4 = new int[] { 0,1,0,-1,-1 };

        System.out.println(fourSumCount(n1, n2, n3, n4));
    }

    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // hash统计a+b的和和出现次数
        Map<Integer, Integer> hash = new HashMap<>();
        for (int n1 : nums1) {
            for (int n2 : nums2) {
                int s1 = n1 + n2;
                hash.put(s1, hash.getOrDefault(s1, 0) + 1);
            }
        }
        // 遍历c和的，检查hash里面是否存在0-(c+d)
        int res = 0;
        for (int n3 : nums3) {
            for (int n4 : nums4) {
                int s2 = n3 + n4;
                if (hash.containsKey(0 - s2)) {
                    res += hash.get(0 - s2);
                }
            }
        }
        return res;
    }
}
