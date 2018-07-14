package edu.princeton.cs.other;

import java.util.*;

/**
 * 子集和问题：
 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 candidates 中的数字可以无限制重复被选取。

 说明：
     所有数字（包括 target）都是正整数。
     解集不能包含重复的组合。
     示例 1:
     输入: candidates = [2,3,6,7], target = 7,
     所求解集为:
     [
         [7],
         [2,2,3]
     ]
     示例 2:
     输入: candidates = [2,3,5], target = 8,
     所求解集为:
     [
         [2,2,2,2],
         [2,3,3],
         [3,5]
     ]
-----------------------------------------------------------------------------

 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 candidates 中的每个数字在每个组合中只能使用一次。
 说明：
     所有数字（包括目标数）都是正整数。
     解集不能包含重复的组合。
     示例 1:
     输入: candidates = [10,1,2,7,6,1,5], target = 8,
     所求解集为:
     [
         [1, 7],
         [1, 2, 5],
         [2, 6],
         [1, 1, 6]
     ]
     示例 2:
     输入: candidates = [2,5,2,1,2], target = 5,
     所求解集为:
     [
         [1,2,2],
         [5]
     ]
 --------------------------------------------------------------------------------

 第一个问题可以用 https://www.cnblogs.com/leavescy/archive/2016/09/23/5900943.html
 第二个问题可以用 https://algoriddles.wordpress.com/2012/04/04/kill-backtrack-sum-of-subsets-problem/



 * @author Mageek Chiu
 */
class SubSetSum {


    private static int[] a;
    private static int[] visited;
    private static int desiredSum;

    public static List<List<Integer>>  combinationSum2(int[] candidates, int target) {
//        // 法1
//        a = candidates;desiredSum = target;
//        visited = new int[a.length];
//        Arrays.sort(a);// 排序的目的是便于剪枝，在某个点找不着结果，那后面的都比target大，自然也就没有结果了
//        int totalSum = 0;
//        for(int item : a) totalSum += item;
//        return sumOfSubSet1(0, 0, totalSum);

        // 法2
        if(candidates == null || candidates.length < 1 || target < 1 ) return null;// 排除特殊
        Arrays.sort(candidates);  // 排序便于剪枝，在某个点找不着结果，那后面的都比target大，自然也就没有结果了
        List<Integer> list = new LinkedList<>();  // 临时结果，亦即当前已经选择的元素列表
        return sumOfSubSet3(candidates,list, target, 0);
    }

    public static List<List<Integer>> sumOfSubSet3(int[] candidates, List<Integer> list, int target, int index){
        List<List<Integer>> resultList = new LinkedList<>();
        Set<List<Integer>> resultSet = new HashSet<>();//  set 是为了去重，因为有重复元素
        for(int i = index; i < candidates.length; i++){
            if(candidates[i] == target){  // 等于就加入结果集，因为这个target是老的target减去之前的结果集，所以这里这样判断
                List<Integer> result = new LinkedList<>();
                result.addAll(list);
                result.add(candidates[i]);
                resultSet.add(result);
            }
            else if(candidates[i] < target){  // 小于就继续递归
                List<Integer> result = new LinkedList<>();
                result.addAll(list);
                result.add(candidates[i]);
                resultSet.addAll(sumOfSubSet3(candidates, result, target - candidates[i],i+1));  // 从下一个元素开始判断
            }
            else{  // 大于，则后面的数字都大于，就可以剪枝了
                break;
            }
        }
        resultList.addAll(resultSet);// 结果以 list的形式返回
        return resultList;
    }

    // 解空间树的层数是数组元素+1
    public static List<List<Integer>> sumOfSubSet1(int index, int curSum, int remainSum){
        Set<List<Integer>> resultSet = new HashSet<>();// set 是为了去重，因为有重复元素
        List<List<Integer>> resultList = new LinkedList<>();
        visited[index] = 1;
        // 入口处验证是否是全局解，如果是，直接返回
        // 如果需要全部解而不是一个解就不能直接返回而是加入结果集
        // 实际编程中也需要查看是否是无效解，如果是，也是直接返回
        if(a[index] + curSum == desiredSum){
            List<Integer> result = new LinkedList<>();
            for(int i = 0; i <= index; i++){
                if(visited[i] == 1){
                    result.add(a[i]);
                }
            }
            resultSet.add(result);
        }
        // 到这说明index及其之前没有解
        // index 元素是可用的  搜索左子树
        else if(index + 1 < a.length && curSum + a[index] + a[index + 1] <= desiredSum){
            resultSet.addAll(sumOfSubSet1(index + 1, curSum + a[index], remainSum - a[index]));
        }
        // 此时，curSum不包括 index,remainSum 是包括 index的
        // 因为排序，所以 值 有 index <= index+1
        // 后两个条件的意思是？？？
        // index 元素不可用，搜索右子树
        if(index + 1 < a.length && curSum + a[index + 1] <= desiredSum && curSum + remainSum - a[index] >= desiredSum){
            visited[index] = 0;
            resultSet.addAll(sumOfSubSet1(index + 1, curSum, remainSum - a[index]));
        }
        resultList.addAll(resultSet);
        return resultList;
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        if(candidates == null || candidates.length < 1 || target < 1 ) return null;// 排除特殊
        Arrays.sort(candidates);  // 排序就能去重
        List<Integer> list = new LinkedList<>();  // 临时结果
        return sumOfSubSet2(candidates,list, target, 0);
    }

    public static List<List<Integer>> sumOfSubSet2(int[] candidates, List<Integer> list, int target, int index){
        List<List<Integer>> resultList = new LinkedList<>();// 因为无重复元素，所以不需要用set去重
        for(int i = index; i < candidates.length; i++){
            if(candidates[i] == target){  // 等于就加入结果集，因为这个target是老的target减去之前的结果集，所以这里这样判断
                List<Integer> result = new LinkedList<>();
                result.addAll(list);
                result.add(candidates[i]);
                resultList.add(result);
            }
            else if(candidates[i] < target){  // 小于就继续递归
                List<Integer> result = new LinkedList<>();
                result.addAll(list);
                result.add(candidates[i]);
                resultList.addAll(sumOfSubSet2(candidates, result, target - candidates[i], i));  // i值不变是因为i可以使用多次
            }
            else{  // 大于，则后面的数字都大于，因此不可能出现在结果集中
                break;
            }
        }
        return resultList;
    }


    // 感受：这种求解问题适合回溯法，一般先排序利于剪枝
    public static void main(String[] args)
    {

////        a = new int[]{2,3,6,7};desiredSum = 7;//[[2, 2, 3], [7]]
//        a = new int[]{2,3,5};desiredSum = 8;//[[2, 2, 2, 2], [2, 3, 3], [3, 5]]
////        a = new int[]{2,3,5,9};desiredSum = 14;//[[2, 2, 2, 2, 2, 2, 2], [2, 2, 2, 2, 3, 3], [2, 2, 2, 3, 5], [2, 2, 5, 5], [2, 3, 3, 3, 3], [2, 3, 9], [3, 3, 3, 5], [5, 9]]
//        System.out.println(combinationSum(a,desiredSum));


        a = new int[]{1, 2, 3, 2, 7, 4, 5, 6};desiredSum = 10;
//        a = new int[]{10,1,2,7,6,1,2,5};desiredSum = 8;
//        a = new int[]{2,5,2,1,2};desiredSum = 5;
        System.out.println(combinationSum2(a,desiredSum));


    }
}
