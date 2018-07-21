package edu.princeton.cs.other;

import static java.lang.System.out;

/**
 给定一个二维网格和一个单词，找出该单词是否存在于网格中。

 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

 示例:

 board =
 [
 ['A','B','C','E'],
 ['S','F','C','S'],
 ['A','D','E','E']
 ]

 给定 word = "ABCCED", 返回 true.
 给定 word = "SEE", 返回 true.
 给定 word = "ABCB", 返回 false.


 dp[i][j] 的值表示 到[i][j]的当前最长字符串的子串长度

 实际上不应该是dp  而是回溯法

 * @author Mageek Chiu
 */
class SearchWordInMatrix {

    private static boolean[][] mark ;//全部是false
    private static int m;
    private static int n ;

    public static boolean exist(char[][] board, String word) {
        m = board.length;
        if (m<1) return false;
        n = board[0].length;
        mark = new boolean[m][n];//全部是false
        for (int i = mark.length - 1; i >= 0; i--) {
            for (int i1 = mark[i].length - 1; i1 >= 0; i1--) {
                mark[i][i1] = false;
            }
        }

        boolean result;
        int start = 0;
        for (int i = 0; i < m ; i++) {
            for (int j = 0; j < n ; j++) {
                if (board[i][j]==word.charAt(start)){//可以作为搜索起点
                    mark[i][j] = true;//访问标记了
                    result = exist(board,i,j,word,start+1);//查找下一个字符
                    if (result) return true;//结果为真就可以直接返回了
                    mark[i][j] = false;//为假就取消标记
                }
            }
        }
        return false;
    }

    private static boolean exist(char[][] board, int i, int j, String word, int start) {
        if (start==word.length()) return true;//找完了
        boolean result;

        if (j>0 && !mark[i][j-1] && board[i][j-1]==word.charAt(start)){//左边在范围内，未被访问，且是下一个字符
            mark[i][j-1] = true;//访问标记了
            result = exist(board,i,j-1,word,start+1);//查找下一个字符
            if (result) return true;//结果为真就可以直接返回了
            mark[i][j-1] = false;//结果为真就标记了，为假就不标记
        }
        if (i>0 && !mark[i-1][j] && board[i-1][j]==word.charAt(start)){//上边在范围内，未被访问，且是下一个字符
            mark[i-1][j] = true;//访问标记了
            result = exist(board,i-1,j,word,start+1);//查找下一个字符
            if (result) return true;//结果为真就可以直接返回了
            mark[i-1][j] = false;//结果为真就标记了，为假就不标记
        }
        if (j<n-1 && !mark[i][j+1] && board[i][j+1]==word.charAt(start)){//右边在范围内，未被访问，且是下一个字符
            mark[i][j+1]= true;//访问标记了
            result = exist(board,i,j+1,word,start+1);//查找下一个字符
            if (result) return true;//结果为真就可以直接返回了
            mark[i][j+1] = false;//结果为真就标记了，为假就不标记
        }
        if (i<m-1 && !mark[i+1][j] && board[i+1][j]==word.charAt(start)){//下边在范围内，未被访问，且是下一个字符
            mark[i+1][j] = true;//访问标记了
            result = exist(board,i+1,j,word,start+1);//查找下一个字符
            if (result) return true;//结果为真就可以直接返回了
            mark[i+1][j] = false;//结果为真就标记了，为假就不标记
        }
        return false;
    }

        // 感受：新鲜概念的理解与使用
    // dp关键是起始条件和递推公式两点
    // 深度优先要注意回溯条件
    public static void main (String ...args){

//        char[][] input1 = {
//                { 82, 87, 65, 77 },
//                { 77, 86, 87, 84 },
//                { 53, 54, 55, 56 }
//        };
//        out.println((input1[0]));//RWAM
//        out.println((input1[1]));//MVWT
//        out.println((input1[2]));//5678
//        out.println(exist(input1,"AW76"));//true
//        out.println(exist(input1,"AW787"));// false
//        out.println(exist(input1,"MTW765M"));// true

//        char[][] input = {
//                {'C','A','A'},
//                {'A','A','A'},
//                {'B','C','D'}};
//        out.println(exist(input,"AAB"));// true

        char[][] input1 = {{'a','b'}};
        out.println(exist(input1,"ba"));// true





    }
}
