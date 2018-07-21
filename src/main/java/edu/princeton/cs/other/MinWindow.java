package edu.princeton.cs.other;

import java.util.HashSet;
import java.util.Set;

import static java.lang.System.out;

/**
 给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串。
 示例：
 输入: S = "ADOBECODEBANC", T = "ABC"
 输出: "BANC"
 说明：
 如果 S 中不存这样的子串，则返回空字符串 ""。
 如果 S 中存在这样的子串，我们保证它是唯一的答案。

 思路： 子串 str 的开头和结尾必然都是T中的字母，这样才能最小
 还要注意排除首尾和中间重复了的元素


 * @author Mageek Chiu
 */
class MinWindow {

    public static String minWindow(String s, String t) {
        int max = s.length();
        t = stringDeDuplicate(t);
        int min = t.length();
        int i=0,j=0,mini=-1,minj=max;
        boolean found = false;
        for ( i = 0;i<=max-min;i++){
            if (t.contains(s.substring(i,i+1))){//以t中的元素开头 才有必要继续往下看
                for ( j = i+min;j<=max;j++){
                    if(isIn(s.substring(i,j),t)){// i,j包括，那么也没必要看 i,j+1了，因为要找最小
                        if ((j-i)<(minj-mini)) {
                            minj = j;mini = i;
                            found = true;
                        }
                        break;//之所以只break一层，外层还要继续就是为了排除首尾和中间重复了的元素
                    }
                }
            }
        }
        return found?s.substring(mini,minj):"";
    }

    public static String stringDeDuplicate(String t){
        StringBuilder sb = new StringBuilder();
        Set<Character> set = new HashSet<>();
        for (char c : t.toCharArray()) {
            set.add(c);
        }

        for (Character character : set) {
            sb.append(character);
        }
        return sb.toString();
    }

    public static boolean isIn(String s,String t){
        for (int i=0;i<t.length();i++){
            if (!s.contains(t.substring(i,i+1)))return false;
        }
        return  true;
    }

    // 感受：
    public static void main (String ...args){
        out.println(minWindow("ADOBECODEBANC","ABC"));//BANC
        out.println(minWindow("1233445567","345"));//3445
        out.println(minWindow("1233445567","3458"));//
        out.println(minWindow("bbaa","aba"));//ba
    }
}
