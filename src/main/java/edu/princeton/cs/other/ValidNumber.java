package edu.princeton.cs.other;

import static java.lang.System.out;

/**
 验证给定的字符串是否为数字。
 例如:
 "0" => true
 " 0.1 " => true
 "abc" => false
 "1 a" => false
 "2e10" => true
 说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。
 https://leetcode-cn.com/problems/valid-number/description/

 * @author Mageek Chiu
 */
class ValidNumber {

    public static boolean isNumber(String s){
        s = s.trim();
        if (s.startsWith("-")) s=s.substring(1);//不能仅仅放这里，-+还可以出现在指数部分
        if (s.startsWith("+")) s=s.substring(1);
        return isNumber(s,0,-1,-1,-1,-1);
    }

    /**
     *
     * @param s
     * @param start 开始检查点
     * @param dot .出现index
     * @param e  e 出现index
     * @param positive +
     * @param negetive -
     * @return
     */
    public static boolean isNumber(String s,int start,int dot,int e,int positive,int negetive) {
        int len = s.length();
        if (len==0) return false;
        if (len==1) return charIsNumber(s.charAt(0));
        int i = start;
        while (i<len){
            char thisChar = s.charAt(i);
            if (charIsNumber(thisChar)){//当前有效
                return isNumber(s,i+1,dot,e,positive,negetive);//递归查询下一个是否有效
            }else {//当前不是数字
                if (thisChar=='e'){// e 和 . 最多各自出现一次，若同时出现，则.必须在e前面，e不能出现在开头或结尾
                    if ((dot>=0 && e>dot) || i==len-1 || i==0 || e>=0 ) return false;//不满足顺序或则已经出现过了，所以返回false
                    e = i;
                }else if(thisChar=='.'){
                    if ((e>=0 && e>dot) || dot>=0) return false;//不满足顺序或则已经出现过了，所以返回false
                    dot = i;
                    if (i==len-1){// . 可以出现在结尾，但是前面必须是数
                        return charIsNumber(s.charAt(i-1));
                    }else if(i==0) {// . 可以出现在开头，但是后面必须是数
                        return charIsNumber(s.charAt(i+1)) && isNumber(s,i+1,dot,e,positive,negetive);
                    }
                }
                else if (thisChar==' '){// 空格只能连续出现在开头或者结尾,或者同时
//                    if (positive<0){//没出现过
//                        positive = i;
//                        negetive = i;
//                    }else {//已经出现过了
//                        // 比较麻烦，应该直接去掉首尾空白空白，这样有空白就返回false
//                    }
                    return false;
                }else if(thisChar=='+'){
                    if (positive>0) return false;
                    positive = i;
                    if (i<=0 || s.charAt(i-1)!='e' || i==len-1) return false;
                }else if( thisChar=='-'){
                    if (negetive>0) return false;
                    negetive = i;
                    if (i<=0 || s.charAt(i-1)!='e' || i==len-1) return false;
                }else {// 其他字母，直接返回false
                    return false;
                }
            }
            i++;
        }
        return true;
    }

    public static boolean charIsNumber(char c){
        return c>='0' && c<='9';
    }

    // 感受：快速总结有效数字的特点，并用代码表示出来
    public static void main (String ...args){
        out.println(isNumber("123"));//true
        out.println(isNumber("2e10"));//true
        out.println(isNumber("2.3e10"));//true
        out.println(isNumber("2.e10"));//true
        out.println(isNumber(" 210"));//true
        out.println(isNumber("1 "));//true
        out.println(isNumber("1  "));//true
        out.println(isNumber(".1"));//true
        out.println(isNumber("1."));//true
        out.println(isNumber("+.9"));//true
        out.println(isNumber(" 005047e+6"));//true
        out.println(isNumber("-1."));//true
        out.println(isNumber(" 1 "));//true
        out.println("-------------------------");
        out.println(isNumber("2e1.0.9"));//false
        out.println(isNumber("+++"));//false
        out.println(isNumber(" 2 10"));//false
        out.println(isNumber("10+"));//false
        out.println(isNumber("."));//false
        out.println(isNumber(" "));//false
        out.println(isNumber(" ."));//false
        out.println(isNumber(" 1 1"));//false
        out.println(isNumber(". "));//false
        out.println(isNumber("2e3.10"));//false
        out.println(isNumber("2ee10"));//false
        out.println(isNumber(".e1"));//false

    }
}
