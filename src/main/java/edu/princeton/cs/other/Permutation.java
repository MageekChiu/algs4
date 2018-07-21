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

    /**
     给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     示例:
     输入: n = 4, k = 2
     输出:
     [
         [2,4],
         [3,4],
         [2,3],
         [1,2],
         [1,3],
         [1,4],
     ]
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new LinkedList<>();
        if (n>=2*k){// k 小于 n 的一半
            result = combine2KLessThanN(n,k);
            return result;
        }
        if (n==k){
            result = combine2KLessThanN(n,k);
            return  result;
        }
        List<List<Integer>> tmp = combine2KLessThanN(n,n-k);
        for (List<Integer> list : tmp) {
            List<Integer> list1 = new LinkedList<>();
            for (int i=1;i<=n;i++){
                if (!list.contains(i)){
                    list1.add(i);
                }
            }
            result.add(list1);
        }
        return result;

    }

    public static List<List<Integer>> combine2KLessThanN(int n, int k) {
        List<List<Integer>> result = new LinkedList<>();
        Set<List<Integer>> resultSet = new HashSet<>();
        if (k==0) {// c n 0 = 1
            return result;
        }
        if (k==1){
            for (int i = 1;i<=n;i++){
                int tmp = i;
                resultSet.add(new ArrayList<Integer>(){{
                    add(tmp);
                }});
            }
        }
        if (k>1){
            List<List<Integer>> sub = combine(n,k-1);
            for (List<Integer> list : sub) {
                for (int i = 1;i<=n;i++){
                    List<Integer> list1 = new ArrayList<>();
                    list1.addAll(list);
                    if (!list.contains(i)){//从剩下的当中选
                        list1.add(i);
                        Collections.sort(list1);//排序去重
                        resultSet.add(list1);
                    }
                }
            }
        }
        result.addAll(resultSet);
        return result;
    }


        // 感受：递归思想的应用
    public static void main (String ...args){
//        int[] nums1 = {1,2,3};
//        out.print(permute(nums1));

//        int[] nums2 = {1,1,2};
//        out.print(permuteUnique(nums2));

//        // 验证 set 能对 list 去重，值一样就视为重复
//        Set<List<Integer>> result = new HashSet<>();
//        result.add(new ArrayList<Integer>(){{
//            add(1);
//            add(2);
//            add(1);
//        }});
//        result.add(new ArrayList<Integer>(){{
//            add(1);
//            add(2);
//            add(1);
////            add(1);
//        }});
//        out.println(result.size());


//        out.println(combine(4,2));
//        out.println(combine(4,1));
//        out.println(combine(4,3));
        out.println(combine(20,16));
//        out.println(combine(3,1));
//        out.println(combine(1,1));
    }
}
