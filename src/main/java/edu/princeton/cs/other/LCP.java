package edu.princeton.cs.other;

import com.sun.xml.internal.ws.util.Pool;

import static java.lang.System.in;
import static java.lang.System.out;

/**
 编写一个函数来查找字符串数组中的最长公共前缀。

 如果不存在公共前缀，返回空字符串 ""。

 示例 1:

 输入: ["flower","flow","flight"]
 输出: "fl"
 示例 2:

 输入: ["dog","racecar","car"]
 输出: ""
 解释: 输入不存在公共前缀。
 说明:

 所有输入只包含小写字母 a-z 。
 * @author Mageek Chiu
 */
class LCP {

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length<1) return "";
        int maxLen = strs[0].length();
        int i;
        for (i = 0;i<maxLen;i++){
            char cur = strs[0].charAt(i);
            boolean stop = false;
            for (String str : strs) {
                if (i>=str.length() || str.charAt(i)!=cur){
                    stop = true;
                    break;
                }
            }
            if (stop) break;
        }
        return strs[0].substring(0,i);
    }

    // 感受：
    public static void main (String ...args){
        String[] nums = {"flower","flow","flight"};
        out.println(longestCommonPrefix(nums));

        String[] nums1 = {"dog","racecar","car"};
        out.println(longestCommonPrefix(nums1));
    }
}
