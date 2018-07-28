package edu.princeton.cs.other;

import java.util.Stack;

import static java.lang.System.out;

public class EvalRPN {

//    根据逆波兰表示法，求表达式的值。
//    有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
//    说明：
//    整数除法只保留整数部分。
//    给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
    // 核心就是遇上运算符就两次出栈，计算结果在入栈
    public static int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        int result = 0;
        int a,b;
        for (String token : tokens) {
            try {
                switch (token){
                    case "+":
                        a = Integer.parseInt(stack.pop());
                        b = Integer.parseInt(stack.pop());
                        result = (b+a);
                        stack.push(String.valueOf(result));
                        break;
                    case "-":
                        a = Integer.parseInt(stack.pop());
                        b = Integer.parseInt(stack.pop());
                        result = (b-a);
                        stack.push(String.valueOf(result));
                        break;
                    case "/":
                        a = Integer.parseInt(stack.pop());
                        b = Integer.parseInt(stack.pop());
                        result = (b/a);
                        stack.push(String.valueOf(result));
                        break;
                    case "*":
                        a = Integer.parseInt(stack.pop());
                        b = Integer.parseInt(stack.pop());
                        result = (b*a);
                        stack.push(String.valueOf(result));
                        break;
                    default:
                        stack.push(token);
                }
            }catch (Exception e){
                return 0;
            }
        }
        return Integer.parseInt(stack.pop());
    }
    // 感受：
    public static void main (String ...args){
        out.println(evalRPN(new String[]{"2", "1", "+", "3", "*"}));//9
        out.println(evalRPN(new String[]{"4", "13", "5", "/", "+"}));//6
        out.println(evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));//22
    }

}
