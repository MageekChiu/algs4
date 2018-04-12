package edu.princeton.cs.other;

import static java.lang.System.out;
/**
 * 最长公共子序列定义：两个或多个已知数列的子序列集合中最长的就是最长公共子序列。
 * 比如数列A = “abcdef”和B = “adefcb”，那么两个数列的公共子序列集合有{”a","ab","abc","adef",等等}，其中最长的就是adef，这就是最长公共子序列。
 * 注意：最长公共子序列的公共子序列里的元素可以不相邻，但是公共子字符串必须是连接在一起的，比如A和B的公共子字符串是“def”。
 * 用动态规划法来求解最长公共子序列，因为最长公共子序列具有最有子结构性质，可以分成子问题来递归求最优解，最后组合子问题求解出问题。用c[i][j]记录X[i]与Y[j]
 *  的LCS 的长度，求解问题c[i,j]，可以分成c[i-1][j-1]、c[i-1][j]、c[i][j-1]子问题来求解，依次递堆到最小子问题，动态规划的递归式描述为
 *
 * @author Mageek Chiu
 */
class LCS {

    private int lcs(char[] X, char[] Y, int m, int n) {
        int[][] lcs = new int[m+1][n+1];
        for (int i=0; i<=m; i++){
            for (int j=0; j<=n; j++){
                if (i == 0 || j == 0)
                    lcs[i][j] = 0;
                else if (X[i-1] == Y[j-1])
                    lcs[i][j] = lcs[i-1][j-1] + 1;
                else
                    lcs[i][j] = max(lcs[i-1][j], lcs[i][j-1]);
            }
        }
        return lcs[m][n];
    }

    private int max(int a, int b){
        return (a > b) ? a : b;
    }

    public static void main(String[] args)
    {
        LCS lcs = new LCS();
//        String s1 = "12345"; String s2 = "34567";// 345
//        String s1 = "1234567890"; String s2 = "34523156789";//  34 56789
        String s1 = "1234567890"; String s2 = "34523156890";//  34 56890
        char[] X=s1.toCharArray();
        char[] Y=s2.toCharArray();
        int m = X.length;
        int n = Y.length;
        System.out.println("Length of LCS is " + lcs.lcs( X, Y, m, n ) );
    }

}
