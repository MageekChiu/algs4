package edu.princeton.cs.other;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mageek Chiu
 * @date 2018/3/29 0029:12:21
 */
class Solution {

    /**
     * 字典排序
     * @param n
     * @return
     */
    public static List<Integer> lexicalOrder(int n) {

        List<Integer> list = new ArrayList<>(n);
        int curr = 1;
        for (int i = 1; i <= n; i++) {
            list.add(curr);
            if (curr * 10 <= n) {
                curr *= 10;
            } else if (curr % 10 != 9 && curr + 1 <= n) {
                curr++;
            } else {
                while ((curr / 10) % 10 == 9) {
                    curr /= 10;
                }
                curr = curr / 10 + 1;
            }
        }
        return list;
    }

    /**
     * hash，得到<元素，频次>键值对
     因为频次小于n，建散列表，即新建大小为n+1的数组，数组下标为频次，数组内容为有相同频次的键值list，对散列表按下标由大到小遍历，找出k个键值
     * @param nums
     * @param k
     * @return
     */
    public static List<Integer> topKFrequent(int[] nums, int k) {
        int len = nums.length;
        List<Integer> list = new ArrayList<>(len);
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0;i<len;i++){
            Integer a= map.get(nums[i]);
//            System.out.println(a);
            if (a==null){
                map.put(nums[i],1);
            }else {
                map.put(nums[i],a+1);
            }
        }
//        System.out.println("==============");
        int[] a = new int[len+1];
        map.forEach((key,val)->{// 元素与频次
            a[val] = key;
            System.out.println(key+","+val);
        });
        System.out.println("==============");
        for (int i = len;i>=0;i--){
            System.out.println(a[i]);
            if(a[i]!=0&&list.size()<k){
                list.add(a[i]);
            }
        }
        System.out.println("==============");
        return list;

    }

    public static void main (String ...args){
        int input = 13;
//        lexicalOrder(input).forEach(System.out::println);

//        int[] aa = {1,1,1,2,2,3};
//        topKFrequent(aa,2).forEach(System.out::println);
        int[] bb = {1,2};
        topKFrequent(bb,2).forEach(System.out::println);


    }
}
