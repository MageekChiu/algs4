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

//    /**
//     * 递归法生成，用set去排除重复
//     * @param n
//     * @return
//     */
//    public static List<String> generateParenthesis(int n) {
//        if (n==1) return new LinkedList<String>(){{
//            add("()");
//        }};
//        Set<String> tmp = new HashSet<>();
//        List<String> subResults = generateParenthesis(n-1);//递归调用获得子结果，无重复
//
//        for (String subResult : subResults) {// subResult 是一个括号组合的字符串
//            char[] brackets = new char[subResult.length()+2];
//            char[] subResultArray = subResult.toCharArray();
//            for(int i = 0;i<=subResult.length();i++){
//                for (int j = i+1;j<=subResult.length()+1;j++){
//                    brackets[i] = '(';
//                    brackets[j] = ')';
//                    if ((i==0&&j==1)){
//                        System.arraycopy(subResultArray,0,brackets,2,subResult.length());
//                    }else if(j==subResult.length()+1&&i==j-1){
//                        System.arraycopy(subResultArray,0,brackets,0,subResult.length());
//                    }else if(i==0 && j>1){
//                        System.arraycopy(subResultArray,0,brackets,1,j-i-1);
//                        System.arraycopy(subResultArray,j-i,brackets,,);
//                    }else if(j==subResult.length()+1 && i<j-1){
//                        System.arraycopy(subResultArray,0,brackets,0,subResult.length());
//                        System.arraycopy(subResultArray,0,brackets,0,subResult.length());
//                    }else if(i>0 && j<subResult.length()+1 && j==i+1){
//                        System.arraycopy(subResultArray,0,brackets,0,subResult.length());
//                        System.arraycopy(subResultArray,0,brackets,0,subResult.length());
//                    }else{
//                        System.arraycopy(subResultArray,0,brackets,0,subResult.length());
//                        System.arraycopy(subResultArray,0,brackets,0,subResult.length());
//                        System.arraycopy(subResultArray,0,brackets,0,subResult.length());
//                    }
////                    if ((i==0&&j==1)||(j==subResult.length()+1&&i==j-1)){// 首尾，只需插一段
////
////                    }else if((i==0 && j>1)||
////                            (j==subResult.length()+1 && i<j-1) ||
////                            (i>0 && j<subResult.length()+1 && j==i+1)){// 插两段
////
////                    }else{// 插3段
////
////                    }
//                    tmp.add(new String(brackets));// set 防止重复
//                }
//            }
//        }
//        return new LinkedList<>(tmp);// set 直接转 list
//    }

    /**
     *
 对于括号的组合，要考虑其有效性。比如说，)(， 它虽然也是由一个左括号和一个右括号组成，但它就不是一个有效的括号组合。
 那么，怎样的组合是有效的呢？对于一个左括号，在它右边一定要有一个右括号与之配对， 这样的才能是有效的。
 所以，对于一个输出，比如说(()())， 从左边起，取到任意的某个位置得到的串，左括号数量一定是大于或等于右括号的数量，
 只有在这种情况下，这组输出才是有效的。我们分别记左，右括号的数量为left和right， 如下分析可看出，(()())是个有效的括号组合。

 这样一来，在程序中，只要还有左括号，我们就加入输出串，然后递归调用。 当退出递归时，如果剩余的右括号数量比剩余的左括号数量多，
 我们就将右括号加入输出串。 直到最后剩余的左括号和右括号都为0时，即可打印一个输出串。


    /**
     * 生成n对括号的全部有效组合
     * @param num 有几对括号
     * @return 装有所有括号组合的列表容器
     */
    public static List<String> generateParenthesis(int num) {
        char[] buffer = new char[num * 2];
        List<String> list = new ArrayList<>();
        make(list, num, num, buffer, 0);
        return list;
    }
    /**
     * 通过递归生成n对括号的全部有效组合
     * @param list 装括号组合的容器
     * @param leftRem 左括号剩余数量
     * @param rightRem 右括号剩余数量
     * @param buffer 放括号的字符数组
     * @param count 当前应该插入括号的位置
     */
    private static void make(List<String> list, int leftRem, int rightRem, char[] buffer, int count) {
        if(leftRem < 0 || rightRem < leftRem) {   // 无效输入
            return ;
        }
        if(leftRem == 0 && rightRem == 0) { // 木有括号了
            String s = String.copyValueOf(buffer);
            list.add(s);
        }else {
            if(leftRem > 0) {    // 还有左括号可用则加入左括号
                buffer[count] = '(';
                make(list, leftRem - 1, rightRem, buffer, count + 1);
            }
            if(rightRem > leftRem) { // 右括号比左括号更多就可以加入右括号，右括号剩的必须必左括号多才合法
                buffer[count] = ')';
                make(list, leftRem, rightRem - 1, buffer, count + 1);
            }
        }
    }



    // 感受：一些基本概念的原理，这里就是括号的概念问题，然后这种N有关的一般看来要么递归要么动态规划
    // 没有重复子问题就用递归就好，有重复的要用动态规划
    public static void main (String ...args){

        // ()   ()(),()(),(())   ()()(),(())(),(())(),(()())
        List<String> res = generateParenthesis(3);
        out.println(res);
    }
}
