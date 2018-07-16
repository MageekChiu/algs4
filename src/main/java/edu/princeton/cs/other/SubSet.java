package edu.princeton.cs.other;

import java.util.*;

/**

 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 说明：解集不能包含重复的子集。
 示例:
 输入: nums = [1,2,3]
 输出:
 [
 [3],
 [1],
 [2],
 [1,2,3],
 [1,3],
 [2,3],
 [1,2],
 []
 ]
-----------------------------------------------------------------------------

 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

 说明：解集不能包含重复的子集。

 示例:

 输入: [1,2,2]
 输出:
 [
 [2],
 [1],
 [1,2,2],
 [2,2],
 [1,2],
 []
 ]



 * @author Mageek Chiu
 */
class SubSet {

    public static List<List<Integer>> subsets(int[] nums) {
        return subsets0(nums,nums.length);
    }

    public static List<List<Integer>> subsets0(int[] nums,int number) {
        List<List<Integer>> resultList = new LinkedList<>();
        if (number==0){
            resultList.add(new LinkedList<>());// 加入空集,因为空集是空集的子集
        }
        else {
            List<List<Integer>> subList = subsets0(nums,number-1);
            for (List<Integer> integers : subList) {
                resultList.add(integers);//无最后一个元素
                List<Integer> tmp = new LinkedList<>();
                tmp.addAll(integers);
                tmp.add(nums[number-1]);
                resultList.add(tmp);//有最后一个元素
            }
        }
        return resultList;

    }


    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);// 先排序，便于去重
        return subsetsWithDup0(nums,nums.length);
    }

    public static List<List<Integer>> subsetsWithDup0(int[] nums,int number) {
        List<List<Integer>> resultList = new LinkedList<>();
        Set<List<Integer>> resultSet = new HashSet<>();
        if (number==0){
            resultSet.add(new LinkedList<>());// 加入空集,因为空集是空集的子集
        }
        else {
            List<List<Integer>> subList = subsetsWithDup0(nums,number-1);
            for (List<Integer> integers : subList) {
                resultSet.add(integers);//无最后一个元素
                List<Integer> tmp = new LinkedList<>();
                tmp.addAll(integers);
                tmp.add(nums[number-1]);
                resultSet.add(tmp);//有最后一个元素
            }
        }
        resultList.addAll(resultSet);
        return resultList;

    }


    // 感受：
    public static void main(String[] args) {
//        int[] nums = new int[]{1,2,3};
//        int[] nums = new int[]{1};
//        int[] nums = new int[]{};
//        System.out.println(subsets(nums));

//        int[] nums = new int[]{1,2,2};
        int[] nums = new int[]{4,4,4,1,4};
        System.out.println(subsetsWithDup(nums));

    }
}
