package edu.princeton.cs.other;

import static java.lang.System.out;

/**
 给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。

 示例 1:
 输入: [[1,1],[2,2],[3,3]]
 输出: 3
 解释:
 ^
 |
 |        o
 |     o
 |  o
 +------------->
 0  1  2  3  4
 示例 2:

 输入: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 输出: 4
 解释:
 ^
 |
 |  o
 |     o        o
 |        o
 |  o        o
 +------------------->
 0  1  2  3  4  5  6
 * @author Mageek Chiu
 */
class MaxPoints {
    // 思路1：两两求出斜率，然后遍历每一个斜率上的点，求最大值
    // 选定一个点，分别计算其他点和它构成的直线的斜率，斜率相同的点肯定在同一条直线上。
    //   注意：
    //        1.在计算机里使用double表示斜率，是不严谨的也是不正确的，double有精度误差，double是有限的，斜率是无限的;表示斜率最靠谱的方式是用最简分数，即分子分母都无法再约分了。分子分母同时除以他们的最大公约数gcd即可得到最简分数。
    //        2.注意重合点
    //        3.注意斜率无穷大的，tan=(y1−y2)/(x1−x2)$,所以用一个pair存储分子分母就好了。
    public static int maxPoints(Point[] points) {

        return  0;
    }


    // 感受：
    public static void main (String ...args){
        out.println(maxPoints(new Point[]{new Point(1,1),new Point(3,2),new Point(5,3),new Point(4,1),new Point(2,3),new Point(1,4)}));//4
    }
}

  class Point {
      int x;
      int y;
      Point() { x = 0; y = 0; }
      Point(int a, int b) { x = a; y = b; }
  }
