package edu.princeton.cs.other;

import java.util.*;

import static java.lang.System.out;

/**
 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

 示例:

 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 输出:
 [
 ["ate","eat","tea"],
 ["nat","tan"],
 ["bat"]
 ]
 说明：

 所有输入均为小写字母。
 不考虑答案输出的顺序。
 * @author Mageek Chiu
 */
class GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            StringBuilder sb = new StringBuilder();
            for (char aChar : chars) {
                sb.append(aChar);
            }
            String key = sb.toString();
            if (!map.containsKey(key)){
                map.put(key, new LinkedList<>());
            }
            map.get(key).add(str);

        }

        return new ArrayList<>(map.values());
    }


    // 感受：
    public static void main (String ...args){
        String[] nums = {"eat", "tea", "tan", "ate", "nat", "bat"};
        out.println(groupAnagrams(nums));
    }
}
