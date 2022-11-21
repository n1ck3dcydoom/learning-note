package nowcode.top101;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by n!Ck
 * Date: 2022/11/20
 * Time: 14:38
 * Description:
 */

public class _74_字符串转化为IP地址 {

    public static void main(String[] args) {
        // System.out.println(restoreIpAddresses("25525522135"));
        // System.out.println(restoreIpAddresses("1111"));
        System.out.println(restoreIpAddresses("000256"));
    }

    public static ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> res = new ArrayList<>();
        dfs(s, new ArrayList<>(), 0, res);
        return res;
    }

    private static void dfs(String s, List<String> path, int index, ArrayList<String> res) {
        if (path.size() == 4) {
            String ip = path.get(0) + "." + path.get(1) + "." + path.get(2) + "." + path.get(3);
            res.add(ip);
            return;
        }
        // ip 地址每段长度为 1~3,每次搜索 1~3 个字符
        for (int i = index; i < index + 3 && i < s.length(); i++) {
            // 截取当前可能的 ip,如果是最后一段 ip,则全部截取
            String ips = path.size() == 3 ? s.substring(index) : s.substring(index, i + 1);
            if (ips.length() == 0) {
                break;
            }
            int ipi = Integer.parseInt(ips);
            // 判断当前 ip 是否合法,不能包含前导 0
            if (0 <= ipi && ipi <= 255) {
                if (ips.length() > 1 && ips.charAt(0) == '0') {
                    continue;
                }
                // 当前选择加入路径
                path.add(ips);
                // 递归搜索下一层
                dfs(s, path, i + 1, res);
                // 撤销选择
                path.remove(path.size() - 1);
            } else {
                // 非法 ip 直接返回上一层搜索
                return;
            }
            if (path.size() == 3) {
                break;
            }
        }
    }
}
