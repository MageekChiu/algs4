package edu.princeton.cs.other;

import java.util.*;

import static java.lang.System.out;

/**
 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 示例:
 输入："23"
 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

 说明:
 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。



 * @author Mageek Chiu
 */
class PhoneMap {

    public static Map<String,Set<String>> phoneMap = new HashMap<>();
    static {
        phoneMap.put("2",new HashSet<String>() {{add("a");add("b");add("c");}});
        phoneMap.put("3",new HashSet<String>() {{add("d");add("e");add("f");}});
        phoneMap.put("4",new HashSet<String>() {{add("g");add("h");add("i");}});
        phoneMap.put("5",new HashSet<String>() {{add("j");add("k");add("l");}});
        phoneMap.put("6",new HashSet<String>() {{add("m");add("n");add("o");}});
        phoneMap.put("7",new HashSet<String>() {{add("p");add("q");add("r");add("s");}});
        phoneMap.put("8",new HashSet<String>() {{add("t");add("u");add("v");}});
        phoneMap.put("9",new HashSet<String>() {{add("w");add("x");add("y");add("z");}});
    }

    public static List<String> letterCombinations(String digits) {
        return letterCombinations(digits,0);
    }

    public static List<String> letterCombinations(String digits,int depth) {
        if (depth>=digits.length()) return new LinkedList<>();

        List<String> result = new LinkedList<>();
        List<String> resultNext = letterCombinations(digits,depth+1);
        phoneMap.get(digits.substring(depth,depth+1)).forEach((v)->{
            if (resultNext.size()<1) {
                result.add(v);
            }else{
                for (String s : resultNext) {
                    result.add(v+s);
                }
            }

        });

        return result;
    }

    // 感受：排列组合要善于运用递归
    public static void main (String ...args){
        String s = "23";
        List<String> res = letterCombinations(s);
        out.println(res);
    }
}
