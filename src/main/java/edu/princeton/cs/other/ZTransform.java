package edu.princeton.cs.other;

import static java.lang.System.out;

/**
 *
 将字符串 "PAYPALISHIRING" 以Z字形排列成给定的行数：

 P   A   H   N
 A P L S I I G
 Y   I   R
 之后从左往右，逐行读取字符："PAHNAPLSIIGYIR"

 实现一个将字符串进行指定行数变换的函数:

 string convert(string s, int numRows);
 示例 1:

 输入: s = "PAYPALISHIRING", numRows = 3
 输出: "PAHNAPLSIIGYIR"
 示例 2:

 输入: s = "PAYPALISHIRING", numRows = 4
 输出: "PINALSIGYAHRPI"
 解释:

 P     I    N
 A   L S  I G
 Y A   H R
 P     I
 * @author Mageek Chiu
 */
class ZTransform {

    public static String convert(String s, int numRows) {
        int len = s.length();
        int number = numRows+(numRows-2);//一个周期的字符个数
        int numCols = (int) Math.ceil(1.0*len/number)*(numRows-1);//numRows-1列为一个周期
        if (number<1) {// 特殊情况
            return s;
        }
//        System.out.println(s+","+len+","+number+","+numRows+","+numCols);
        char[][] result = new char[numRows][numCols];
        StringBuilder res = new StringBuilder();
        int i = 0,j = 0, mark = 0;
        while (j<numCols){
            int mod = j % (numRows-1);
            if (mod == 0 ){// 首列需要填满
                while (i<numRows && mark<s.length()){
                    result[i++][j] = s.charAt(mark++);
                }
                i = 0;
            }else{// 其余列只填一个
                if (mark<s.length())
                    result[numRows-1-mod][j] = s.charAt(mark++);
                else
                    break;
            }
            j++;
        }
        for (i=0;i<numRows;i++){
            for (j=0;j<numCols;j++){
                if (result[i][j]!=0)
                    res.append(result[i][j]);
            }
        }

        return res.toString();
    }

    // 感受：
    public static void main (String ...args){
//        String s = "PAYPALISHIRING";
//        String res = convert(s,3);//
//        out.println(res);//PAHNAPLSIIGYIR
//
//        String s = "PAYPALISHIRING";
//        String res = convert(s,4);//
//        out.println(res);//PINALSIGYAHRPI

        String s = "A";
        String res = convert(s,1);//
        out.println(res);//A
    }
}
