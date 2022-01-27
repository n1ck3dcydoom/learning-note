package daimasuixianglu._4_string;

public class _3_ReplaceSpace {

    public static void main(String[] args){
        System.out.println(replaceSpace("We are happy."));
    }

    public static String replaceSpace(String s) {
        // 统计空格个数
        int count = 0;
        char[] cs = s.toCharArray();
        for (char c : cs) {
            count = ' ' == c ? count + 1 : count;
        }
        // 扩充数组长度，使用最少的额外空间
        int len = cs.length;
        char[] ncs = new char[len + 2 * count];
        int nlen = ncs.length;
        // 双指针倒序遍历
        int p = len - 1, q = nlen - 1;
        while (p>=0) {
            if (' ' != cs[p]) {
                ncs[q--] = cs[p--];
            } else {
                ncs[q--] = '0';
                ncs[q--] = '2';
                ncs[q--] = '%';
                p--;
            }
        }
        return new String(ncs);
    }
}
