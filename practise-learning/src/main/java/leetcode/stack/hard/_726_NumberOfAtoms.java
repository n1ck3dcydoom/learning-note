package leetcode.stack.hard;

import java.util.*;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定一个化学式formula（作为字符串），返回每种原子的数量。
 * <p>
 * 原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。
 * <p>
 * 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。
 * <p>
 * 两个化学式连在一起是新的化学式。例如H2O2He3Mg4 也是化学式。
 * <p>
 * 一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。
 * <p>
 * 给定一个化学式formula ，返回所有原子的数量。格式为：第一个（按字典序）原子的名字，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：formula = "H2O"
 * 输出："H2O"
 * 解释：
 * 原子的数量是 {'H': 2, 'O': 1}。
 * 示例 2：
 * <p>
 * 输入：formula = "Mg(OH)2"
 * 输出："H2MgO2"
 * 解释：
 * 原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
 * 示例 3：
 * <p>
 * 输入：formula = "K4(ON(SO3)2)2"
 * 输出："K4N2O14S4"
 * 解释：
 * 原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
 * 示例 4：
 * <p>
 * 输入：formula = "Be32"
 * 输出："Be32"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= formula.length<= 1000
 * formula 由小写英文字母、数字 '(' 和 ')' 组成。
 * formula 是有效的化学式。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-atoms
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/7/5 22:57
 **/
public class _726_NumberOfAtoms {

    public static void main(String[] args) {
        String formula = "Mg(OH)2";
        System.out.println(countOfAtoms(formula));
    }

    private static int i = 0;

    public static String countOfAtoms(String formula) {
        // 括号序列问题一般通过递归或者栈解决

        // 使用栈保存每次遍历的中间结果
        // 使用hash表保存每层出现的字母和次数

        // 操作栈的规则
        // 每次遇到左括号 '(' 则压入一个空的hash表，表示开始下一层的遍历
        // 每次遇到字母，则栈顶的hash表压入 (当前字母, 字母后的数字)，如果字母后跟的另外一个字母，则压入1
        // 每次遇到右括号 ')' 则表示遍历完当前层，弹出栈顶hash表
        // 如果右括号 ')' 后面有数字，则当前hash表中每个字母出现的频率乘以右括号后面的数字
        // 然后把弹出的hash表中的字母和频率压入新的栈顶hash表中，重复的累加
        // 直到栈还剩最后一个hash表
        // 则依次输出最后一个hash表的字母和频率，然后按照字母字典序排序


        Deque<HashMap<String, Integer>> stack = new ArrayDeque<>();

        // 最开始先压入一个空hash表，因为化学式不以左括号开始
        stack.addLast(new HashMap<>());

        int n = formula.length();
        // 遍历化学式字符串
        while (i < n) {
            char c = formula.charAt(i);
            // 遇到左括号，放入新的hash表，进入下一层遍历
            if ('(' == c) {
                stack.addLast(new HashMap<>());
                i++;
            }
            // 遇到右括号，表示当前层遍历结束，弹出栈顶hash表和右括号后面的数字相乘，然后放入新的栈顶hash表
            else if (')' == c) {
                // 弹出栈顶
                HashMap<String, Integer> oldTopHash = stack.pollLast();
                // 新的栈顶
                HashMap<String, Integer> newTopHash = stack.peekLast();
                // i后面的数字
                i++;
                int multiply = getNum(formula);
                // 把弹出的hash表中的每个字母频率乘以后面的数字，然后重新压入新的栈顶hash表中
                for (Map.Entry<String, Integer> entry : oldTopHash.entrySet()) {
                    String oldChar = entry.getKey();
                    int oldCount = entry.getValue();
                    newTopHash.put(oldChar, newTopHash.getOrDefault(oldChar, 0) + oldCount * multiply);
                }
            }
            // 非括号的情况
            else {
                // 得到当前的原子
                String atom = getAtom(formula);
                int count = getNum(formula);
                // 当前栈顶hash表，加入当前原子和频率，如果已存在则累加
                HashMap<String, Integer> currentTopHash = stack.peekLast();
                currentTopHash.put(atom, currentTopHash.getOrDefault(atom, 0) + count);
            }
        }
        StringBuilder resBuilder = new StringBuilder();
        // 遍历最后一个栈顶元素，最开始遍历化学式的之前压入了一个空栈，保证每次遇到右括号弹出栈时，栈里面至少都有一个hash表
        HashMap<String, Integer> lastTopHash = stack.pollLast();
        // 使用排序map保证字典序
        TreeMap<String, Integer> treeMap = new TreeMap<>(lastTopHash);
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            String atom = entry.getKey();
            resBuilder.append(atom);
            int count = entry.getValue();
            if (count > 1) {
                resBuilder.append(count);
            }
        }
        return resBuilder.toString();
    }

    private static String getAtom(String formula) {
        StringBuilder sb = new StringBuilder();
        // 先放入大写字母，有些原子由大写字母+小写字母组成
        sb.append(formula.charAt(i++));
        // 如果后面紧跟小写字母，则加入当前原子，否则返回
        while (i < formula.length() && (Character.isLowerCase(formula.charAt(i)))) {
            sb.append(formula.charAt(i++));
        }
        return sb.toString();
    }

    private static int getNum(String formula) {
        // 如果到达末尾返回1
        if (i == formula.length()) {
            return 1;
        }
        // 如果不是一个数字
        char c = formula.charAt(i);
        if (!Character.isDigit(c)) {
            return 1;
        }
        // 遍历数字，字母后面可能有多个数字，例如Be32
        StringBuilder sb = new StringBuilder();
        while (i < formula.length() && Character.isDigit(formula.charAt(i))) {
            sb.append(formula.charAt(i++));
        }
        return Integer.parseInt(sb.toString());
    }
}