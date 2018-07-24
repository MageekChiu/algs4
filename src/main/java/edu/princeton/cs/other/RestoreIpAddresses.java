package edu.princeton.cs.other;

import java.util.LinkedList;
import java.util.List;

import static java.lang.System.out;

/**
 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 示例:
 输入: "25525511135"
 输出: ["255.255.11.135", "255.255.111.35"]

 输入: "12332"
 输出: ["1.2.3.32", "1.2.33.2", "1.23.3.2", "12.3.3.2"]

 思路，四段，每一段最长4位，最短1位，且每一段∈[0,255]
 这种求全部解的，而且还不能暴力破解（3个点要插入串中，3个for循环，n^3）的就要用回溯法
 回溯法比暴力for的优势在于可以剪枝，有些肯定无解的循环能够省略，加快速度



 * @author Mageek Chiu
 */
class RestoreIpAddresses {

    public static List<String> restoreIpAddresses(String s) {
//        return restoreIpAddresses(s,3);// 3,2,1 共三个点，表示当前待填的这个点填下去之前后面还有n段,也就是还有n-1个点
        return restoreIpAddresses(s,4);// 4,3,2,1 共4段，表示当前需要的段数，这样才好终止
    }

    public static List<String> restoreIpAddresses(String s,int n) {
        int len = s.length();
        List<String> strings = new LinkedList<>();
        if(len<n) return strings;//长度不够段数
        if (n==1){//最后一个段。递归终止条件
            if (s.startsWith("0") && (!s.endsWith("0") || s.lastIndexOf("0")!=0)) {
                return strings;
            }//以0开头，但是不以0结尾或者0有多个，不合法 如 01 或则 00
            int thisInt = Integer.parseInt(s);
            if (thisInt>255 || len>3){
                return strings;
            }
            strings.add(s);
            return strings;
        }
        for (int i = 1;i<4 && i<=len-(n-1);i++){//每段字符串长度1-3，后面至少要剩n-1个字符才能凑够n-1段，取不取等找个例子试试就知道了
            String thisSegment = s.substring(0,i);
            if (thisSegment.startsWith("0") && (!thisSegment.endsWith("0") || thisSegment.lastIndexOf("0")!=0)) continue;//以0开头，但是又不只一个0就不合法
            int thisInt = Integer.parseInt(thisSegment);
            if (thisInt>255 || (len-i)>(n-1)*3) continue;//本字符串大于255或者剩下的串太长
            List<String> subStrings = restoreIpAddresses(s.substring(i),n-1);
            for (String subString : subStrings) {
                strings.add(thisSegment+"."+subString);
            }
        }
        return strings;
    }

    // 感受：
    public static void main (String ...args){
//        out.println(restoreIpAddresses("25525511135"));//["255.255.11.135", "255.255.111.35"]
//        out.println(restoreIpAddresses("12332"));//["1.2.3.32", "1.2.33.2", "1.23.3.2", "12.3.3.2"]
//        out.println(restoreIpAddresses("122"));// []
        out.println(restoreIpAddresses("010010"));//["0.10.0.10","0.100.1.0"]
        out.println(restoreIpAddresses("172162541"));//["17.216.25.41","17.216.254.1","172.16.25.41","172.16.254.1","172.162.5.41","172.162.54.1"]
    }
}
