package leetcode.bfs.hard;

import java.util.*;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 字典wordList 中从单词 beginWord和 endWord 的 转换序列 是一个按下述规格形成的序列：
 * <p>
 * 序列中第一个单词是 beginWord 。
 * 序列中最后一个单词是 endWord 。
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典wordList 中的单词。
 * 给你两个单词 beginWord和 endWord 和一个字典 wordList ，找到从beginWord 到endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 * 示例 2：
 * <p>
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：0
 * 解释：endWord "cog" 不在字典中，所以无法进行转换。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * beginWord != endWord
 * wordList 中的所有字符串 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/6/26 0:01
 **/
public class _127_WordLadder {

    public static void main(String[] args) {
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        //        wordList.add("cog");
        System.out.println(ladderLength("hit", "cog", wordList));
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 按照头节点进行bfs广度优先搜索，每次遍历一个单词，直到找到最后一个endWord

        // 使用队列保存bfs的搜索路径
        Queue<String> queue = new ArrayDeque<>();
        // 无向图需要记录每次访问过的节点，否则会有死循环
        HashSet<String> visited = new HashSet<>();
        // 放入头节点
        queue.add(beginWord);
        visited.add(beginWord);
        // 记录需要通过多少次转换得到endWord,其中startWord也算作一次
        int res = 1;
        // 所有节点个数
        int n = wordList.size();
        // 遍历队列
        while (!queue.isEmpty()) {
            // 当前层有多少个节点
            int count = queue.size();
            // 遍历当前层有哪些节点
            for (int i = 0; i < count; i++) {
                // 弹出队列头节点，遍历字典其他节点找到所有和当前头节点连通的节点
                String current = queue.poll();
                // 遍历字典中其他所有节点，找到与当前节点连通的节点
                for (String s : wordList) {
                    // 如果当前节点已经被访问过了，则跳过
                    if (visited.contains(s)) {
                        continue;
                    }
                    // 不连通，则跳过
                    if (!canConvert(current, s)) {
                        continue;
                    }
                    // 连通，且能转换成endWord，则bfs找到终点，返回步长+1 (重点也算一次)
                    if (s.equals(endWord)) {
                        return res + 1;
                    }
                    // 标记当前节点为已经访问过的节点，放入队列作为下一层遍历的节点
                    visited.add(s);
                    queue.add(s);
                }
            }
            // 每一层访问完后，步长加1
            res++;
        }
        return 0;
    }

    private static boolean canConvert(String baseString, String targetString) {
        if (baseString.length() != targetString.length()) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < baseString.length(); i++) {
            if (baseString.charAt(i) != targetString.charAt(i)) {
                count++;
                if (count > 1) {
                    return false;
                }
            }
        }
        // 有一位不同，则说明可以转换
        return 1 == count;
    }
}