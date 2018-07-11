package edu.princeton.cs.other;

import java.util.Deque;
import java.util.LinkedList;

import static java.lang.System.out;

/**
 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

 有效字符串需满足：

 左括号必须用相同类型的右括号闭合。
 左括号必须以正确的顺序闭合。
 注意空字符串可被认为是有效字符串。

 示例 1:

 输入: "()"
 输出: true
 示例 2:

 输入: "()[]{}"
 输出: true
 示例 3:

 输入: "(]"
 输出: false
 示例 4:

 输入: "([)]"
 输出: false
 示例 5:

 输入: "{[]}"
 输出: true
 isValid


 * @author Mageek Chiu
 */
class ValidBrackets {

    /**
     * 括号是否有效, O(n) 复杂度
     */
    public static boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0;i<s.length();i++){
            char thisChar =s.charAt(i);
            Character exceptChar ;
            switch (thisChar){
                case '{':
                case '[':
                case '(':
                    stack.push(thisChar);
                    break;
                case '}':
                    exceptChar = stack.poll();// pop 不存在会报exception，poll返回null
                    if(exceptChar==null || exceptChar != '{')
                        return false;
                    break;
                case ']':
                    exceptChar = stack.poll();
                    if(exceptChar==null || exceptChar != '[')
                        return false;
                    break;
                case ')':
                    exceptChar = stack.poll();
                    if(exceptChar==null || exceptChar != '(')
                        return false;
                    break;
            }
        }
        return stack.size() < 1;// 为 0 才说明括号匹配完毕
    }

    /**
     给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
     示例 1:
     输入: "(()"
     输出: 2
     解释: 最长有效括号子串为 "()"

     示例 2:
     输入: ")()())"
     输出: 4
     解释: 最长有效括号子串为 "()()"

     longestValidParentheses  暴力解法直接定首尾 n^2 加上有效性判断 n 复杂度就是是 O(n^3) 了，应该有 更小阶的方法

     如果上动态规划就是n^2 ，有可能，试试
     计算 c[i,j]的分数，表示从 j-i 这一段的分数，有点难

     再想想， ) -1  (  1  ，小于0 的左边子串可以和右边子串分开看了，记录下其最长有效序列和右边最长有效序列即可，
     因为怎么左边和右边的随意组合都是是无效的,但是怎么在子串内部求最长有效序列呢

     还是想想动态规划, i<=j,表示[i,j]这一段的分数,ij相等表示i这个位置字符的值

                [i-1,j] + 1  i-1=(
     [i,j] =    [i,j-1] + 1  j-1=)
                ...

     就是找出 等于0，且 j-i 最大的 [i,j]

     (()            2
     ()(()          2
     )()())         4
     )()())()()()   6
     (()())()()()   12
     */
//    public static int longestValidParentheses(String s) {
//        if (s.length()<=1) return 0;
//        int sum = 0;
//        int result = 0;
//        for (int i=0;i<s.length();i++){
//            if (s.charAt(i)==')'){
//                sum += -1;
//                if (sum<0){// 左右可以分开看了,i可以排除
//                    return Math.max(longestValidParentheses(s.substring(0,i)),longestValidParentheses(s.substring(i+1)));
//                }
//                result += 1;
//            }else {// '('
//                sum += 1;
//            }
//        }
//        return result*2;
//    }

    /**
     动态规划，可解答，O(n^2) 时间复杂度，LeetCode 会超时
     * @param s
     * @return
     */
    public static int longestValidParenthesesDP(String s) {
        // 排除特殊情况
        if (s.length()<=1) return 0;

        // 转化为数组
        int[] middle = new int[s.length()];
        for (int i=0;i<s.length();i++){
            middle[i] = s.charAt(i)=='(' ? 1 : -1;
        }

        // DP 初始化
        int [][] c = new int[s.length()][s.length()];
        c[0][0] = middle[0];
        for (int i=1;i<s.length();i++){
            c[i][i] = middle[i];
            c[0][i] = middle[i]+c[0][i-1];
        }

        // 计算DP
        for (int i=0;i<s.length();i++){
            for (int j=i+1;j<s.length();j++){
                c[i][j] = c[i][j-1]+middle[j];
            }
        }

        // 找距离最远且符合条件的，注意条件要包含中间没有[i,j]中间没有负数
        int l=0,r=0,max=0;
        for (int i=0;i<s.length();i++){
            for (int j=i+1;j<s.length();j++){
                if (c[i][j]<0) break;//
                if( middle[i]==1 && middle[j]==-1 && c[i][j]==0 && (j-i)>max){
                    l = i;r = j;max = j-i+1;
                }
            }
        }

        // 打印DP矩阵 和 结果
//            for (int i=0;i<s.length();i++){
//                for (int j=0;j<s.length();j++){
//                    out.print(c[i][j]+"->");
//                }
//                out.println();
//            }
//            out.println("position:"+l+","+r);
//

        return max;
    }

    /**
     这道求最长有效括号比之前那道 Valid Parentheses 验证括号难度要大一些，这里我们还是借助栈来求解，
     需要定义个start变量来记录合法括号串的起始位置，我们遍历字符串，如果遇到左括号，则将当前下标压入栈，
     如果遇到右括号，如果当前栈为空，则将下一个坐标位置记录到start，如果栈不为空，则将栈顶元素取出，
     此时若栈为空，则更新结果和i - start + 1中的较大值，否则更新结果和i - 栈顶元素中的较大值
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {
        int res = 0, start = 0;
        Deque<Integer> m = new LinkedList<>();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') m.push(i);
            else if (s.charAt(i) == ')') {
                if (m.isEmpty()) start = i + 1;
                else {
                    m.pop();
                    res = m.isEmpty() ? Math.max(res, i - start + 1) : Math.max(res, i - m.peek());
                }
            }
        }
        return res;
    }

    // 感受：基本概念的逻辑，对应到栈的应用,注意特殊情况
    // 动态规划的应用
    public static void main (String ...args){
        String input = "()[]{}";
        out.println(isValid(input));

//        input = "{[]}";
//        out.println(isValid(input));

//        input = "([)]";
//        out.println(isValid(input));
//
//        input = "(]";
//        out.println(isValid(input));
//
//        input = "]";
//        out.println(isValid(input));
//
//        input = "{";
//        out.println(isValid(input));

        input = "(()";
        out.println(longestValidParentheses(input));// 2

        input = "()(()";
        out.println(longestValidParentheses(input));// 2

        input = ")()())";
        out.println(longestValidParentheses(input));// 4

        input = ")()())()()()";
        out.println(longestValidParentheses(input));// 6

        input = "(())()(()((";
        out.println(longestValidParentheses(input));// 6



    }
}
