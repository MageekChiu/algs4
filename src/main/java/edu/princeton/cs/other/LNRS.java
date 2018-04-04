package edu.princeton.cs.other;

import static java.lang.System.out;

/**
 *   给定一个字符串，找出不含有重复字符的 最长子串 的长度。

     示例：

     给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。

     给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。

     给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列 而不是子串。

 * @author Mageek Chiu
 */
class LNRS {

    /**
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s.length()==0) return 0;//特殊情况
        int res = 1;int i,j;
        for (i = 0;i<s.length();i++){
            for (j = i+1 ; j<s.length();j++){
                if (s.substring(i,j).indexOf(s.charAt(j)) < 0){// 第 j 位 不属于 [i,j)
                    res = res > j-i+1 ? res : j-i+1;
                }else{// 重复了
                    res = res > j-i ? res : j-i ;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 另一种思路
     如果确定子串s[i,j]（假设表示字符串的第i个字符到第j-1个字符表示的子串），那么只需要比较s[j]是否与子串s[i,j]重复即可
     若重复：记录此时滑动窗口大小，并与最大滑动窗口比较，赋值。然后滑动窗口大小重定义为1，头位置不变，并右移一个单位。
     若不重复：滑动窗口头不变，结尾+1，整个窗口加大1个单位。继续比较下一个。
     *
     * @param args
     */

    public static void main (String ...args){
        out.println(lengthOfLongestSubstring("pwwkew"));// 3
        out.println(lengthOfLongestSubstring("bbbbb"));// 1
        out.println(lengthOfLongestSubstring("abcabcbb"));//3
        out.println(lengthOfLongestSubstring("abceeddd"));//4
    }
}
