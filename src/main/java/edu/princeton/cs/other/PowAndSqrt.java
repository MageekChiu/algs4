package edu.princeton.cs.other;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.out;

/**
 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 示例 1:
 输入: 2.00000, 10
 输出: 1024.00000
 示例 2:
 输入: 2.10000, 3
 输出: 9.26100
 示例 3:
 输入: 2.00000, -2
 输出: 0.25000
 解释: 2-2 = 1/22 = 1/4 = 0.25
 说明:
 -100.0 < x < 100.0
 n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。

 --------------------- ---------------------

 实现 int sqrt(int x) 函数。
 计算并返回 x 的平方根，其中 x 是非负整数。
 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 示例 1:
 输入: 4
 输出: 2
 示例 2:
 输入: 8
 输出: 2
 说明: 8 的平方根是 2.82842...,
 由于返回类型是整数，小数部分将被舍去。

 * @author Mageek Chiu
 */
class PowAndSqrt {

    // 这样直接循环在N很大的时候容易超时，O(n)
    public static double myPow0(double x, int n) {
        if (n>0){
            double result = x;
            while (--n>0){
                result *= x;
            }
            return result;
        }else if (n<0){
            double result = 1/x;
            while (++n<0){
                result /= x;
            }
            return result;
        }else {
            return 1;
        }
    }

    // O(logn) 最坏也是 O(n)  2147483647 与 2147483648（2^31次方） 2到30次方时就会剩一大串出去mod
    public static double myPow1(double x, int n) {
        if (n>0){
            double result = x;
            double i = 1,mod = 0;// 用double，int溢出就为0,i代表当前的次方
            while (2*i<=n){// 这样的话最后还是会剩一大串
                result *= result;
                i *= 2;
                mod = n-i;
            }
            while (mod-->0 ) result *= x;
            return result;
        }else if (n<0){
            double result = 1/x;
            double i = -1,mod = 0;
            while (2*i>=n){
                result *= result;
                i *= 2;
                mod = i-n;
            }
            while (mod-->0) result *= 1/x;
            return result;
        }else {
            return 1;
        }
    }
    public static double myPow(double x, int n) {
        double r = 1;
        if (n== Integer.MIN_VALUE){// minvalue不能转abs，需要单独处理
            for (int i = n; i < 0; i++) {
                if (i % 2 == 0) {
                    x = x * x;
                    i = i / 2;
                }
                r = r * x;
            }
            return 1/r;
        }
        int len = Math.abs(n);//可以求abs  正负统一处理
        for (int i = len; i > 0; i--) {
            if (i % 2 == 0) {
                x = x * x;
                i = i / 2;
            }
            r = r * x;
        }
        if (n < 0) {
            r = 1 / r;
        }
        return r;
    }

    // 返回平方根，注意去掉了小数部分，所以<=实际平方根
    public static int mySqrt(int x) {
        if (x==0) return 0;
        if (x<4) return 1;
        for (long i=x/2;i>=1;){
            if (i*i<=x && (i+1)*(i+1)>x){
                return (int) i;
            }
            if (i*i/4>x) i /=2;//加快减小速度
            else i--;
        }
        return 1;
    }

    // 是否是完全平方数
    public static boolean isPerfectSquare(int x) {
        if (x==0 || x==1) return true;
        if (x<4) return false ;
        for (long i=x/2;i>=1;){
            if (i*i==x){
                return true;
            }
            if (i*i/4>x) i /=2;//加快减小速度
            else i--;
        }
        return false;
    }

    // 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c。
//    https://leetcode-cn.com/problems/sum-of-square-numbers/description/
    public static boolean judgeSquareSum(int c) {
        if (isPerfectSquare(c)) return true;//是完全平方数，那么a就是0

        int l = 1, r = mySqrt(c)+1;
        while (l<=r){
            long result = r*r+l*l;
            if (result==c) return true;
            else if (result<c) l++;
            else  r--;
        }
        return false;
    }

    /**
     给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
     示例 1:
     输入: n = 12
     输出: 3
     解释: 12 = 4 + 4 + 4.
     示例 2:
     输入: n = 13
     输出: 2
     解释: 13 = 4 + 9.

     思路：直观感觉首先取最大的，然后递归求剩下的，那么就是数量最少的
     贪心算法 numSquaresGreedy

     但是这样不对，比如你看 n = 18，先取 4 再取 1 1 ，其实应该是两个3
     所以必须全部搜索完整个解空间树，但是要注意剪枝

     */
    public static int numSquaresGreedy(int n) {
        Deque<Integer> stack = new ArrayDeque<>();
        AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
        numSquaresGreedy(n,stack,min);
        return min.get();
    }

    public static void numSquaresGreedy(int n,Deque<Integer> stack,AtomicInteger min) {
        int sqrt = mySqrt(n);//真正平方根取floor后的值
        for (int i = sqrt;i>0;i--){//从最大的开始尝试,取1会产生很多无用空间，浪费搜索时间，所以单独拎出来,其实不必，注意剪枝即可
            stack.push(i);//入栈
            if (isPerfectSquare(n)) {//本身就是平方数
                if (stack.size()<min.get()){
                    min.set(stack.size());
                }
                stack.pop();//弹出继续求解
            }else {
                if (stack.size()+1<min.get()){//可以继续往下分解
                    numSquaresGreedy(n-i*i,stack,min);//肯定有解，因为任何数都可以由1相加而得
                    stack.pop();
                }else {//已经比已有最小值大了，没必要继续分解，直接弹出当前值，亦即剪枝了
                    stack.pop();
                }
            }
        }
    }

    public static int numSquaresBFS(int n) {
        Queue<Integer> queue = new LinkedList<>();//遍历节点队列
        AtomicInteger min = new AtomicInteger(0);
        queue.add(n);
        numSquaresBFS(queue,min);
        return min.get()+1;
    }

    public static void numSquaresBFS(Queue<Integer> queue,AtomicInteger min) {
        Queue<Integer> queue1 = new LinkedList<>();//存这一层的所有子节点，便于统计层数
        int n = queue.peek();//peek不删除
        if (isPerfectSquare(n)) return;
        while (!queue.isEmpty()){
            Integer head = queue.poll();//出队，
            if (isPerfectSquare(n-head*head)) return;//宽度优先，最先找到的肯定是路径最短的
            else {//否则把该节点的子节点全部入队
                List<Integer> tmp = getSubNodeList(n,head);
//                queue.addAll(tmp);
                queue1.addAll(tmp);
            }
            if (queue.isEmpty()){
                min.incrementAndGet();
                queue.addAll(queue1);
                queue1.clear();
            }
        }
    }

    public static List<Integer> getSubNodeList(int n,int m){
        List<Integer> queue = new LinkedList<>();
        int toDo = n==m ? n : n-m*m;//要排除根节点的特殊性
        int sqrt = mySqrt(toDo);//真正平方根取floor后的值
        for (int i = sqrt;i>0;i--){
            queue.add(i);
        }
//        queue.add(-1);//层级标志
        return queue;
    }

    public static int numSquares(int n) {
        return  numSquaresBFS(n);
//        return  numSquaresGreedy(n);
    }


    // 感受：这种求平方或者根的都要通过乘以2除以2来加快速度，直接遍历太慢了
    // 对于有序的数据一定要记得 l，r双指针
    public static void main (String ...args){
//        out.println(myPow(2,10));
//        out.println(myPow(2,2));
//        out.println(myPow(2,-2));
//        out.println(myPow(2,-3));
//        out.println(myPow(2,0));
//        out.println(myPow(2.1,3));
//        out.println(myPow(34.00515,-3));
//        out.println(myPow(0.0001,2147483647));
//        out.println(myPow(2,-2147483648));
//        out.println(myPow(1,-2147483648));
//        out.println(myPow(-4.48392,6));

//        out.println(mySqrt(8));
//        out.println(mySqrt(4));
//        out.println(mySqrt(101));
//        out.println(mySqrt(2000010001));

//        out.println(isPerfectSquare(81));
//        out.println(isPerfectSquare(16));
//        out.println(isPerfectSquare(9));
//        out.println(isPerfectSquare(1));
//        out.println(isPerfectSquare(13));
//        out.println(isPerfectSquare(3));
//        out.println(isPerfectSquare(5));
//        out.println(isPerfectSquare(2043434346));


//        out.println(judgeSquareSum(2043434346));
//        out.println(judgeSquareSum(5));
//        out.println(judgeSquareSum(4));
//        out.println(judgeSquareSum(100));
//        out.println(judgeSquareSum(3));
//        out.println(judgeSquareSum(13));

//        out.println(numSquares(81));//1
        out.println(numSquares(12));//3
//        out.println(numSquares(13));//2
//        out.println(numSquares(16));//1
//        out.println(numSquares(2));//2
//        out.println(numSquares(3));//3
//        out.println(numSquares(18));//2
//        out.println(numSquares(59));//3
//        out.println(numSquares(101));//2



    }
}
