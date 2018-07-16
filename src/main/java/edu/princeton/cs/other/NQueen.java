package edu.princeton.cs.other;

import java.util.Date;

/**
 n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 示例:

 输入: 4
 输出: [
 [".Q..",  // 解法 1
 "...Q",
 "Q...",
 "..Q."],

 ["..Q.",  // 解法 2
 "Q...",
 "...Q",
 ".Q.."]
 ]
 解释: 4 皇后问题存在两个不同的解法。

 https://www.cnblogs.com/newflydd/p/5091646.html

 * @author Mageek Chiu
 */
class NQueen {
    private static final short N=8;        //使用常量来定义，方便之后解N皇后问题
    private static int count=0;            //结果计数器

    public static void main(String[] args) {
        Date begin =new Date();
        //初始化棋盘，全部置0
        short chess[][]=new short[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                chess[i][j]=0;
            }
        }

        putQueenAtRow(chess,0);
        Date end =new Date();
        System.out.println("解决 " +N+ " 皇后问题，用时：" +String.valueOf(end.getTime()-begin.getTime())+ "毫秒，计算结果："+count);
    }

    private static void putQueenAtRow(short[][] chess, int row) {
        /**
         * 递归终止判断：如果row==N，则说明已经成功摆放了8个皇后
         * 输出结果，终止递归
         */
        if(row==N){
            count++;
            System.out.println("第 "+ count +" 种解：");
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    System.out.print(chess[i][j]+" ");
                }
                System.out.println();
            }
            return;
        }

        short[][] chessTemp=chess.clone();

        /**
         * 向这一行的每一个位置尝试排放皇后
         * 然后检测状态，如果安全则继续执行递归函数摆放下一行皇后
         */
        for(int i=0;i<N;i++){
            //摆放这一行的皇后，之前要清掉所有这一行摆放的记录，防止污染棋盘
            for(int j=0;j<N;j++)
                chessTemp[row][j]=0;
            chessTemp[row][i]=1;

            if( isSafety( chessTemp,row,i ) ){
                putQueenAtRow(chessTemp,row+1);
            }
        }
    }

    private static boolean isSafety(short[][] chess,int row,int col) {
        //判断中上、左上、右上是否安全
        int step=1;
        while(row-step>=0){
            if(chess[row-step][col]==1)                //中上
                return false;
            if(col-step>=0 && chess[row-step][col-step]==1)        //左上
                return false;
            if(col+step<N && chess[row-step][col+step]==1)        //右上
                return false;

            step++;
        }
        return true;
    }
}
