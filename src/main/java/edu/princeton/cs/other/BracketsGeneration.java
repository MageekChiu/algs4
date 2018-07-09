package edu.princeton.cs.other;

import java.util.*;

import static java.lang.System.out;

/**
 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。

 例如，

 n = 1
 ()

 n = 2

 ()()
 (())

 给出 n = 3，生成结果为：

 [
 "((()))",
 "(()())",
 "(())()",
 "()(())",
 "()()()"
 ]




 * @author Mageek Chiu
 */
class BracketsGeneration {

    public static List<String> generateParenthesis(int n) {
        List<String> result = new LinkedList<>();

        Set<String> tmp = new HashSet<>();


        return result;
    }

    // 感受：
    public static void main (String ...args){
        int n = 3;
        List<String> res = generateParenthesis(n);
        out.println(res);
    }
}
