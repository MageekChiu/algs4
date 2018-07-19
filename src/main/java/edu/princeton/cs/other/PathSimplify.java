package edu.princeton.cs.other;

import edu.princeton.cs.algs4.Stack;

import static java.lang.System.out;

/**
 给定一个文档 (Unix-style) 的完全路径，请进行路径简化。
 例如，
 path = "/home/", => "/home"
 path = "/a/./b/../../c/", => "/c"
 边界情况:
 你是否考虑了 路径 = "/../" 的情况？
 在这种情况下，你需返回 "/" 。
 此外，路径中也可能包含多个斜杠 '/' ，如 "/home//foo/" 。
 在这种情况下，你可忽略多余的斜杠，返回 "/home/foo" 。


 * @author Mageek Chiu
 */
class PathSimplify {

    public static String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        int len = path.length();
        String[] chars = path.split("/");
        for (String aChar : chars) {
            switch (aChar) {
                case "..":
                    if(!stack.isEmpty())stack.pop();
                    break;
                case "":
                case ".": //无操作
                    break;
                default:
                    stack.push(aChar);
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("/");// 起始的
        for (String s : stack) {
            sb.append(s).append("/");
        }
        String s =  sb.toString();
        if (stack.size()>0) return s.substring(0,s.length()-1);
        return s;
    }

    // 感受：
    public static void main (String ...args){
        out.println(simplifyPath("/../"));
        out.println(simplifyPath("/home//foo/"));
        out.println(simplifyPath("/a/./b/../../c/"));// /c
    }
}
