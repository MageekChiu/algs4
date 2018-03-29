package edu.princeton.cs.other;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Mageek Chiu
 * @date 2018/3/29 0029:12:21
 */
class Solution1 {

    private static final String carSplit =";";
    private static final String timeSplit =",";
    private static final String regress = "(\\d{1,2},\\d{1,2};)*\\d{1,2},\\d{1,2}$";

    public static int countCars(int[][] carArray) {
        int ans = 0;
        int[] hourCarNum = {0,0,0,0,0,0,0,0,0,0,0,0};//每小时有的车的数量
        //按小时遍历，也可以车数据在外面，小时在内部
        for (int hour = 0;hour<12;hour++){
            for(int i=0;i<carArray.length;i++){
//                System.out.println(carArray[i][0]+","+carArray[i][1]);
                if(carArray[i][0]<=hour&&carArray[i][1]>hour){
                    hourCarNum[hour] += 1;
                }
            }
//            System.out.println( hourCarNum[hour]+"=============="+hour);
        }
        int max = -1;
        for (int i=0;i<12;i++){
            if(hourCarNum[i]>max){
                max=hourCarNum[i];
            }
        }
        ans = max;
        return ans; // 返回计算结果
    }

    //输入字符串转数组
    public int[][] convertToArray(String str) {
        String[] strArray = str.split(carSplit);
        int row = strArray.length;
        int col = 2;
        // 字符转数组判断
        int[][] carArray = new int[row][col];
        int start,end;
        for (int i = 0; i < row; i++) {
            start = Integer.parseInt(strArray[i].split(timeSplit)[0]);
            end = Integer.parseInt(strArray[i].split(timeSplit)[1]);
            if(start>end){
                continue;
            }
            carArray[i][0] = start;
            carArray[i][1] = end;
        }
        return carArray;
    }

    public static void main (String ...args){

        String inString = "6,9;4,6;3,6;6,8";
        Pattern pat = Pattern.compile(regress);
        if(inString == null || inString.trim().equals("")||!pat.matcher(inString).matches()){
            System.out.println("输入错误!");
            return;
        }
        Solution1 sol = new Solution1();
        int countCars = countCars(sol.convertToArray(inString));
        System.out.println(countCars);
    }
}
