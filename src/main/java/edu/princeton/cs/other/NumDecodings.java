package edu.princeton.cs.other;

import static java.lang.System.out;

/**
 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26
 给定一个只包含数字的非空字符串，请计算解码方法的总数。

 示例 1:
 输入: "12"
 输出: 2
 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 示例 2:
 输入: "226"
 输出: 3
 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。

 这个和那个恢复IP几乎一样,采用回溯法，但是居然超时了

 动态规划其实也好解

 'A'到'Z' 26个字母最多2位最少1位且不为0，因此存在多种解码的可能性。
 建立一维数组DP[i]表示到当前第i位字符时所能解码的方式，
 因此对于某一位i，若其不为‘0’，则DP[i] += DP[i-1],若其能与i-1位构成的数字在1到26之间，则DP[i]+=DP[i-2]。因此动态规划表达式为：

 　　　　①S[i]!=0：DP[i] = DP[i-1] + (S[i-1]=='1'||S[i-1]=='2'&&S[i]<='6')?DP[i-2]:0；

 　　　　②S[i]=0：DP[i] = (S[i-1]=='1'||S[i-1]=='2')?DP[i-1]:0；

 要注意的一点是在进行二位数判断时直接用字符进行判断即可，Integer.valueOf()反而会增加代码量如对前一位是‘0’的处理等等；

 * @author Mageek Chiu
 */
class NumDecodings {

//    public static int numDecodings(String s) {
//        List<String> list = numDecodingsAns(s);
//        return list==null ? 0 : list.size();
//    }
//
//    public static List<String> numDecodingsAns(String s) {
//        return numDecodingsAns(s,s.length());//最多就这么多段，一段一个字符
//    }
//
//    public static List<String> numDecodingsAns(String s,int n) {
//        int len = s.length();
//        List<String> strings = new LinkedList<>();
//        if (n==1){//最后一个段。递归终止条件
//            if ("".equals(s)) return strings;//为空，但是合法
//            int thisInt = Integer.parseInt(s);
//            if (thisInt>26 || thisInt<1)  return null;//非法，返回null
//            strings.add(s); return strings;//正确且合法
//        }
//        int failTime = 0;
//        for (int i = 1;i<3 && i<=len;i++){//每段字符串长度1-2
//            String thisSegment = s.substring(0,i);
//            if (thisSegment.startsWith("0")) continue;//不能以0开头，所以 30就只能30 而不能 3.0 这个不算fail
//            int thisInt = Integer.parseInt(thisSegment);
//            if (thisInt>26  || thisInt<1){failTime++; continue;}//本字符串大于26或小于1，这个算fail
//            String nextString = s.substring(i);
//            if (nextString.startsWith("0")){ failTime++;continue;}//不能以0开头，这个也算fail
//
//            List<String> subStrings = numDecodingsAns(nextString,n-1);
//            if (null==subStrings) return null;//不合法
//            for (String subString : subStrings) {
//                strings.add(thisSegment+"."+subString);
//            }
//            if (subStrings.size()<1) strings.add(thisSegment);//是空的就要补上当前段
//        }
//        if (failTime>=2) return null;
//        return strings;
//    }

    public static int numDecodingsAns(String s) {
        int length = s.length();
        //对首位0或空字符串进行处理
        if(length==0||s.charAt(0)=='0'){
            return 0;
        }
        int[] result = new int[length+1];
        result[0] = 1;
        result[1] = 1;
        for(int i=1;i<length;i++){
            //遇到0时的处理方式
            result[i+1]=s.charAt(i)=='0'?0:result[i];
            //判断数字是否在范围内
            if(s.charAt(i-1)=='1'||s.charAt(i-1)=='2'&&s.charAt(i)<='6'){
                result[i+1]+=result[i-1];
            }
        }
        return result[length];
    }



    


    // 感受： 尤其注意边界条件，这样才能逐渐完善程序
    // 动态规划和回溯法要分清适用场合，一般能用DP就用DP,回溯法太耗时间
    public static void main (String ...args){
        out.println(numDecodingsAns("12"));//[1.2, 12]
        out.println(numDecodingsAns("226"));//[2.2.6, 2.26, 22.6]
        out.println(numDecodingsAns("123456"));//[1.2.3.4.5.6, 1.23.4.5.6, 12.3.4.5.6]
        out.println(numDecodingsAns("10"));//[10]
        out.println(numDecodingsAns("01"));//[]
        out.println(numDecodingsAns("230"));//[]
        out.println(numDecodingsAns("4757562545844617494555774581341211511296816786586787755257741178599337186486723247528324612117156948"));//
    }
}
