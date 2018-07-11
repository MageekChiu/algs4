package edu.princeton.cs.other;

import java.util.*;

import static java.lang.System.out;

/**
 给定一个没有重复数字的序列，返回其所有可能的全排列。

 示例:
 输入: [1,2,3]
 输出:
 [
 [1,2,3],
 [1,3,2],
 [2,1,3],
 [2,3,1],
 [3,1,2],
 [3,2,1]
 ]


 给定一个可包含重复数字的序列，返回所有不重复的全排列。

 示例:

 输入: [1,1,2]
 输出:
 [
 [1,1,2],
 [1,2,1],
 [2,1,1]
 ]

 * @author Mageek Chiu
 */
class Permutation {

    public static List<List<Integer>> permute(int[] nums) {
        return permute(nums,nums.length);
    }

    /**
     * @param nums  数组
     * @param length 元素个数
     * @return
     */
    public static List<List<Integer>> permute(int[] nums,int length) {
        List<List<Integer>> result = new LinkedList<>();// 不同的排列 不用管顺序

        if (length <= 0) {
            return result;// 违规输入
        }
        if (length == 1){// 只有一个元素
            result.add(new ArrayList<Integer>(){{add(nums[0]);}});//一个排列内部的数字是要管顺序的
            return result;
        }

        List<List<Integer>> subResult = permute(nums,length-1);
        int newValue = nums[length-1];// 在子结果的基础之上增加的新元素
        for (List<Integer> integerList : subResult) {
            for (int i = 0;i<length;i++){
                List<Integer> newList = new ArrayList<>(integerList);// 一个排列内部的数字是要管顺序的
                newList.add(i,newValue);
                result.add(newList);
            }
        }
        return result;
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();// 不同的排列 不用管顺序
        result.addAll(permuteUnique(nums,nums.length));
        return result;
    }

    public static Set<List<Integer>> permuteUnique(int[] nums,int length) {
        Set<List<Integer>> result = new HashSet<>();// 不同的排列 不用管顺序,set会自动帮忙过滤相同的list

        if (length <= 0) {
            return result;// 违规输入
        }
        if (length == 1){// 只有一个元素
            result.add(new ArrayList<Integer>(){{add(nums[0]);}});//一个排列内部的数字是要管顺序的
            return result;
        }

        Set<List<Integer>> subResult = permuteUnique(nums,length-1);
        int newValue = nums[length-1];// 在子结果的基础之上增加的新元素
        for (List<Integer> integerList : subResult) {
            for (int i = 0;i<length;i++){
                List<Integer> newList = new ArrayList<>(integerList);// 一个排列内部的数字是要管顺序的
                newList.add(i,newValue);
                result.add(newList);
            }
        }
        return result;
    }


    // 感受：递归思想的应用
    public static void main (String ...args){
//        int[] nums1 = {1,2,3};
//        out.print(permute(nums1));

//        int[] nums2 = {1,1,2};
//        out.print(permuteUnique(nums2));

        // 验证 set 能对 list 去重，值一样就视为重复
        Set<List<Integer>> result = new HashSet<>();
        result.add(new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(1);
        }});
        result.add(new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(1);
//            add(1);
        }});
        out.println(result.size());

    }
}
