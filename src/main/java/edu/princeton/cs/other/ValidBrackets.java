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
 * @author Mageek Chiu
 */
class ValidBrackets {

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
                    exceptChar = stack.poll();
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
        if (stack.size()<1)
            return true;
        else
            return false;
    }

    // 感受：基本概念的逻辑，对应到栈的应用,注意特殊情况
    public static void main (String ...args){
        String input = "()[]{}";
        out.println(isValid(input));

        input = "{[]}";
        out.println(isValid(input));

        input = "([)]";
        out.println(isValid(input));

        input = "(]";
        out.println(isValid(input));

        input = "]";
        out.println(isValid(input));

        input = "{";
        out.println(isValid(input));


    }
}
