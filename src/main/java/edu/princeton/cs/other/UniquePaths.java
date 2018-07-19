package edu.princeton.cs.other;

import static java.lang.System.out;

/**
 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 问总共有多少条不同的路径？
 例如，上图是一个7 x 3 的网格。有多少可能的路径？
 说明：m 和 n 的值均不超过 100。
 示例 1:
 输入: m = 3, n = 2
 输出: 3
 解释:
 从左上角开始，总共有 3 条路径可以到达右下角。
 1. 向右 -> 向右 -> 向下
 2. 向右 -> 向下 -> 向右
 3. 向下 -> 向右 -> 向右
 示例 2:
 输入: m = 7, n = 3
 输出: 28
 可以转化为求组合数问题，
 也可以转化为递归问题 每一点（x,y），可以由（x-1，y）向右走或是（x,y-1）向下走来到达，因此在（x,y）这一点可到达的方法有ans[x-1][y]+ans[x][y-1]种

 ---------------------------------------------------------------------------------------
 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？

 网格中的障碍物和空位置分别用 1 和 0 来表示。
 说明：m 和 n 的值均不超过 100。
 示例 1:
 输入:
 [
 [0,0,0],
 [0,1,0],
 [0,0,0]
 ]
 输出: 2
 解释:
 3x3 网格的正中间有一个障碍物。
 从左上角到右下角一共有 2 条不同的路径：
 1. 向右 -> 向右 -> 向下 -> 向下
 2. 向下 -> 向下 -> 向右 -> 向右

 这个问题就只能用DP了，如果障碍点只有1,2个依然可以通过排除法转换为组合数问题，但是这里障碍物数量未知

----------------------------------------------------------

 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 说明：每次只能向下或者向右移动一步。
 示例:
 输入:
 [
 [1,3,1],
 [1,5,1],
 [4,2,1]
 ]
 输出: 7
 解释: 因为路径 1→3→1→1→1 的总和最小。

 思路，标准的DP问题

 * @author Mageek Chiu
 */
class UniquePaths {

    public static int uniquePaths(int m, int n) {
        // 本质 就是 C (m+n-2) (n-1);
        // if(m==1||n==1) return 1;
        // int k = 0;
        // int topSum = 1;
        // int downSum = 1;
        // int tmp = m<n?m:n;// 取小的减少运算，这样算组合数容易溢出，还是按dp来解吧
        // while(k<n-1){
        //     topSum *= (m+n-2-k);
        //     downSum *= (tmp-1-k);
        //     k++;
        // }
        // return (int)(topSum)/downSum;

//        return cfunc(m+n-2,n-1);//递归容易溢出
//        return combination(m+n-2,n-1);//DP比较耗费空间

        if (m == 0 || n == 0) {
            return 0;
        }
        int[][] path = new int[m][n];
        for (int i = 0; i < m; i++)
            path[i][0] = 1;
        for (int i = 0; i < n; i++)
            path[0][i] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                path[i][j] = path[i-1][j] + path[i][j-1];
            }
        }
        return path[m-1][n-1];
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length<1)
            return 0;
        //入口有障碍的话，直接返回
        if (obstacleGrid[0][0] == 1)
            return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] path = new int[m][n];
        //入口点走法只有1种。
        path[0][0] = 1;
        for (int i = 1; i < m; i++) {
            //左边界，如果上一点可行并且当前点没有障碍物，那么该点可走
            if (path[i-1][0] != 0 && obstacleGrid[i][0] != 1)
                path[i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            //上边界与左边界情况同理
            if (path[0][i-1] != 0 && obstacleGrid[0][i] != 1)
                path[0][i] = 1;
        }
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                //动态规划，当前点无障碍物则与上面处理方法一致
                if (obstacleGrid[i][j] != 1)
                    path[i][j] = path[i-1][j]+path[i][j-1];
        return path[m-1][n-1];
    }

    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        if (m<1) return 0;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                if (i==0 && j>0) dp[i][j] =dp[i][j-1]+ grid[i][j];// 行起始条件
                if (j==0 && i>0) dp[i][j] =dp[i-1][j]+ grid[i][j];// 列起始条件
                if (i>0 && j>0) dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }

    public static int[][] C = null;
    public static int rows = 200,cols = 200;

    /**
     * 计算组合数，
     * @param m
     * @param n
     * @return
     */
    public static int combination(int m,int n){
        return  0;
    }

    /**
     * 计算排列数
     * @param m
     * @param n
     * @return
     */
    public static int permutation(int m,int n){
        return 0;
    }

    // 从 n个里面选k个 的组合数，递归会导致重复计算
    public static int cfunc(int n,int k){
        if(k==0) return 1;
        if(k==1) return n;
        if(n>k/2) return cfunc(n,n-k);// 取小的减少运算
        if(n==0) return 0;
        return cfunc(n-1,k-1)+cfunc(n-1,k);

    }

    // 感受：
    public static void main (String ...args){
//        out.println(uniquePaths(7,3));//28
//        out.println(uniquePaths(3,2));//3

        out.println(minPathSum(new int[][]{
                {1,3,1},
                {1,5,1},
                {4,2,1}
        }));
    }
}
