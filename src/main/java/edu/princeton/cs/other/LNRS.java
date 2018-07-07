package edu.princeton.cs.other;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
     * 两个循环都是n,indexof 也是n，所以一共是O(n^3)
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
     *
     *
     public int lengthOfLongestSubstring(String s) {
         int n = s.length();
         int ans = 0;
         for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
         return ans;
     }

     public boolean allUnique(String s, int start, int end) {
         Set<Character> set = new HashSet<>();
         for (int i = start; i < end; i++) {
             Character ch = s.charAt(i);
             if (set.contains(ch)) return false;
             set.add(ch);
     }
     return true;
     }
     * 另一种思路。
     * 暴力法非常简单。但它太慢了。那么我们该如何优化它呢？

     在暴力法中，我们会反复检查一个子字符串是否含有有重复的字符，但这是没有必要的。
     如果从索引 i到 j - 1 之间的子字符串 s ​ij​  已经被检查为没有重复字符。
     我们只需要检查 s[j] 对应的字符是否已经存在于子字符串 s ​ij   ​​ 中。

     要检查一个字符是否已经在子字符串中，我们可以检查整个子字符串，这将产生一个复杂度为 O(n^2) 的算法，但我们可以做得更好。

     通过使用 HashSet 作为滑动窗口，我们可以用 O(1) 的时间来完成对字符是否在当前的子字符串中的检查。

     滑动窗口是数组/字符串问题中常用的抽象概念。 窗口通常是在数组/字符串中由开始和结束索引定义的一系列元素的集合，
     即 [i, j)（左闭，右开）。而滑动窗口是可以将两个边界向某一方向“滑动”的窗口。例如，我们将 [i, j) 向右滑动 1 个元素，
     则它将变为 [i+1, j+1)（左闭，右开）。

     回到我们的问题，我们使用 HashSet 将字符存储在当前窗口 [i, j)（最初 j = i）中。 然后我们向右侧滑动索引 j，
     如果它不在 HashSet 中，我们会继续滑动 j。直到 s[j] 已经存在于 HashSet 中。
     此时，我们找到的没有重复字符的最长子字符串将会以索引 i 开头。如果我们对所有的 i 这样做，就可以得到答案。
     *

     时间复杂度：O(2n) = O(n)，在最糟糕的情况下，每个字符将被 i 和 j 访问两次。

     空间复杂度：O(min(m, n))，与之前的方法相同。滑动窗口法需要 O(k)的空间，其中 k 表示 Set 的大小。
     而Set的大小取决于字符串 n 的大小以及字符集/字母 mm 的大小。

     */
    public int lengthOfLongestSubstring2n(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    /**
     * 上述的方法最多需要执行 2n 个步骤。事实上，它可以被进一步优化为仅需要 n 个步骤。
     * 我们可以定义字符到索引的映射，而不是使用集合来判断一个字符是否存在。
     * 当我们找到重复的字符时，我们可以立即跳过该窗口。
       也就是说，如果 s[j] 在 [i, j) 范围内有与 j' 重复的字符，我们不需要逐渐增加 i
       我们可以直接跳过 [i，j′] 范围内的所有元素，
       因为逐渐增加的i都会卡在j，序列长度不会比现在大,所以没必要了，并将 i 变为 j' + 1。
     */

    public int lengthOfLongestSubstring1n(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    // 感受： hash表一般可以用来减小一个量级，把n的查找变成1
    public static void main (String ...args){
        out.println(lengthOfLongestSubstring("pwwkew"));// 3
        out.println(lengthOfLongestSubstring("bbbbb"));// 1
        out.println(lengthOfLongestSubstring("abcabcbb"));//3
        out.println(lengthOfLongestSubstring("abceeddd"));//4
    }
}
