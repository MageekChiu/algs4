package edu.princeton.cs.other;

import java.util.Arrays;
import java.util.Stack;

import static java.lang.System.out;

public class EvalRPN {

//    根据逆波兰表示法，求表达式的值。
//    有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
//    说明：
//    整数除法只保留整数部分。
//    给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
    // 核心就是遇上运算符就两次出栈，计算结果在入栈
    public static int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        int result = 0;
        int a,b;
        for (String token : tokens) {
            try {
                switch (token){
                    case "+":
                        a = Integer.parseInt(stack.pop());
                        b = Integer.parseInt(stack.pop());
                        result = (b+a);
                        stack.push(String.valueOf(result));
                        break;
                    case "-":
                        a = Integer.parseInt(stack.pop());
                        b = Integer.parseInt(stack.pop());
                        result = (b-a);
                        stack.push(String.valueOf(result));
                        break;
                    case "/":
                        a = Integer.parseInt(stack.pop());
                        b = Integer.parseInt(stack.pop());
                        result = (b/a);
                        stack.push(String.valueOf(result));
                        break;
                    case "*":
                        a = Integer.parseInt(stack.pop());
                        b = Integer.parseInt(stack.pop());
                        result = (b*a);
                        stack.push(String.valueOf(result));
                        break;
                    default:
                        stack.push(token);
                }
            }catch (Exception e){
                return 0;
            }
        }
        return Integer.parseInt(stack.pop());
    }

    /**
     只要总汽油量要大于总的消耗量，那么肯定是有解的，可以从头遍历起，什么时候汽油量小于消耗量了，就假设从下一个点重新开始。
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0;
        int j = -1;
        for (int i = 0, sum = 0; i < gas.length; ++i) {
            sum += gas[i] - cost[i];
            total += gas[i] - cost[i];
            if (sum < 0) {
                j = i;//不断更新，最后一次 sum<0 的下标j,
                sum = 0;//清空sum重新从i+1开始判断。
            }
        }//只要总汽油量要大于总的消耗量，那么肯定是有解的，不用构成环来判断。
        return total >= 0 ? j + 1 : -1;
    }

    /**
     老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
     你需要按照以下要求，帮助老师给这些孩子分发糖果：
         - 每个孩子至少分配到 1 个糖果。
         - 相邻的孩子中，评分高的孩子必须获得更多的糖果。
     那么这样下来，老师至少需要准备多少颗糖果呢？
     示例 1:
     输入: [1,0,2]
     输出: 5
     解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
     示例 2:
     输入: [1,2,2]
     输出: 4
     解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
     第三个孩子只得到 1 颗糖果，这已满足上述两个条件。

     思路：Cur只要不比邻居高，那么就只分一个，比其中一个邻居A高就比A多一个，比两个邻居A,B都高就分max（a,b）+1；

     需要注意，不仅要从左往右遍历，还有从右往左补充一下,重新屡一下

     candies列表是每个小朋友得到的糖果数。我们首先从左往右遍历，给candies列表赋初值，如果当前小朋友的分数比前1位小朋友的高，
     则分给当前小朋友的糖果数=前1位小朋友的糖果数+1，否则当前小朋友的糖果数=1（满足每个孩子至少有1颗糖）。
     然后，再从右到左遍历，需要再比较当前小朋友和后1位小朋友的分数，因此，但此时，由于我们已经给candies列表赋过初值，
     即如果满足当前小朋友的分数比后一位小朋友高时，当前小朋友的糖果数也比后一位小朋友的糖果数高时，
     则说明原始candies列表满足条件，否则需要修改当前小朋友的糖果为后一位小朋友的糖果数+1。
     https://blog.csdn.net/qq_35559420/article/details/79917980
     */
    public static int candy(int[] ratings) {
        if (ratings.length<2) return 1;
        int[] number = new int[ratings.length];
        Arrays.fill(number,1);//每个人首先分一个
        for (int i = 0; i < ratings.length; i++) {
            if (i==0){
                if (ratings[i]>ratings[i+1]) number[i] = number[i+1]+1;
            }else if(i==ratings.length-1){
                if (ratings[i]>ratings[i-1]) number[i] = number[i-1]+1;
            }else {
                if (ratings[i]>ratings[i-1] && ratings[i]>ratings[i+1] ){
                    number[i] = Math.max(number[i-1],number[i+1])+1;
                }else if (ratings[i]>ratings[i-1] && ratings[i]<=ratings[i+1] ){
                    number[i] = number[i-1]+1;
                }else if(ratings[i]<=ratings[i-1] && ratings[i]>ratings[i+1] ) {
                    number[i] = number[i+1]+1;
                }
            }
        }
        for (int i = ratings.length-1;i>=0; i--) {
            if (i != ratings.length-1) {
                if (ratings[i]>ratings[i+1]) {
                    number[i] = Math.max(number[i],number[i+1]+1);
                }
            }
        }

        int res = 0;
        for (int i : number)  res+=i;
        return res;
    }



    // 感受：
    public static void main (String ...args){
//        out.println(evalRPN(new String[]{"2", "1", "+", "3", "*"}));//9
//        out.println(evalRPN(new String[]{"4", "13", "5", "/", "+"}));//6
//        out.println(evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));//22

//        out.println(canCompleteCircuit(new int[]{1,2,3,4,5},new int[]{3,4,5,1,2}));//3
//        out.println(canCompleteCircuit(new int[]{2,3,4},new int[]{3,4,3}));//-1

        out.println(candy(new int[]{1,0,2}));//5
        out.println(candy(new int[]{1,2,2}));//4
        out.println(candy(new int[]{0}));//1
        out.println(candy(new int[]{1,2,87,87,87,2,1}));//13


    }

}
