package edu.princeton.cs.other;

import static java.lang.System.out;

/**
 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 必须原地修改，只允许使用额外常数空间。

 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 1,2,3 → 1,3,2
 3,2,1 → 1,2,3
 1,1,5 → 1,5,1
 1,5,3,4 -> 1,5,4,3


 * @author Mageek Chiu
 */
class NextPermutation {

    /**
     思路1：找到当前序列位置最靠后的一个升序的pair，把这个改成降序，就恰好比当前序列大
     所以 end 从最后开始往前遍历 对应  nextPermutationlastPair
     反例 1,3,2 这样搞出来是 2,3,1 实际答案是 2,1,3

     思路2应该是 从最后一个位置开始比较，看当前序列有没有办法调整至更大
     比如 1,3,2      2 没法，32也没法，132就可以调整为从1后面选一个大于1的最小的数 2 出来做第一位，剩下的数字升序排列13
     比如 1,5,6,8,2  2 没法，82也没法，682就可以调整为826   最终就是 15826
     比如 2,4,8,2,6  6 没法，26可以62

     所以就是从后往前找降序的源头（因为降序序列就是没法调的，比如上面的82，源头就是6）
     然后从降序序列里面找一个大于源头最小的数min作为源头，剩下的数升序排列，对应 nextPermutation



     * @param nums array
     */
    public static void nextPermutationlastPair(int[] nums) {

        int end = nums.length-1;
        int start = 0;
        boolean found = false;
        // 倒叙查找，所以找到一个pair就可以直接返回
        for (; end>0 ; end--){
            for (start = end-1;start>=0;start--){
                if (nums[start]<nums[end]){
                    found = true;
//                    out.println("found index :"+start+","+end+" number: "+nums[start]+","+nums[end]);
                    break;
                }
            }
            if (found) break;
        }

        // 不占额外空间交换变量的值，两种方法
        // 法一可能溢出
//        a = a + b;
//        b = a - b;
//        a = a - b;
        // 法二 用到了一个事实：任何数自己和自己异或（XOR）后等于0. 不会溢出
        // A XOR B XOR B = A，即对给定的数A，用同样的运算因子（B）作两次异或运算后仍得到A本身
//        a = a^b;
//        b = a^b;
//        a = a^b;
        if (found){
//            out.println("found index :"+start+","+end+" number: "+nums[start]+","+nums[end]);
            nums[start] = nums[start] ^ nums[end];
            nums[end] = nums[start] ^ nums[end];
            nums[start] = nums[start] ^ nums[end];
//            out.println("found index :"+start+","+end+" number: "+nums[start]+","+nums[end]);
        }else {//没有升序序列，是排列里面的最大，需要逆序获得最小
            int tmp ,l = 0,r = nums.length-1;
            while (l<r){// 逆序调整，适用于奇偶长度
                tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
                l++;r--;
            }
        }
    }

    public static void nextPermutation(int[] nums) {
        int r = nums.length - 1;
        int l = r-1;
        while ( l>=0 && nums[l]>=nums[r]){// 必须要等于号，因为 66 这种也属于没法的
            l--;
            r--;
        }
        if (l<0){// 没找到哦，全部逆序
            int tmp ,ll = 0,rr = nums.length-1;
            while (ll<rr){// 逆序调整，适用于奇偶长度
                tmp = nums[ll];
                nums[ll] = nums[rr];
                nums[rr] = tmp;
                ll++;rr--;
            }
            return;
        }
        // 到这里 l 就是源头
        int min = Integer.MAX_VALUE;
        int index = l;
        int newL = l;
        while (++index<nums.length){
            if (nums[index]>nums[l] && nums[index]<min){// 大于源头且最小
                newL = index;
            }
        }
        // 交换 min 与 源头
        nums[newL] = nums[newL]^nums[l];
        nums[l] = nums[newL]^nums[l];
        nums[newL] = nums[newL]^nums[l];

        // 插入排序，在 l+1 与 nums.lenght-1 中进行
        // 其实不用排序，因为 l 后面的递增，所以只需要简单的逆序就好
        for (int i = l+1; i < nums.length; i++) {
            for (int j = i; j > l+1 && nums[j]<nums[j-1]; j--) {
                nums[j] = nums[j]^nums[j-1];
                nums[j-1] = nums[j]^nums[j-1];
                nums[j] =  nums[j]^nums[j-1];
            }
        }
    }

    public static String getPermutation(int n, int k) {
        int[] start = new int[n];
        for (int i = 0;i<n;i++){
            start[i] = i+1;
        }
        while (--k>0){
            nextPermutation(start);
        }
        StringBuilder sb = new StringBuilder();
        for (int i : start) {
            sb.append(i);
        }
        return sb.toString();
    }

    // 感受：不熟悉的场景要抓住本质，可以通过特例进行归纳总结，特例要考虑一般的，也要考虑特殊的，比如
    // 本题 的 66 就是特殊的
    public static void main (String ...args){

//        out.println(2^3^3);// 2

//        int[] nums = {1,2,3};
//        nextPermutation(nums);// 1,3,2
//        for (int i = 0; i < nums.length; i++) {
//            out.print(nums[i]+",");
//        }

//        int[] nums = {1,1};
//        nextPermutation(nums);// 1,1
//        for (int i = 0; i < nums.length; i++) {
//            out.print(nums[i]+",");
//        }
//        out.println();
//        int[] nums1 = {4,3,2,1};
//        nextPermutation(nums1);// 1,2,3,4
//        for (int i = 0; i < nums1.length; i++) {
//            out.print(nums1[i]+",");
//        }
//        out.println();
//        int[] nums2 = {1,3,2};
//        nextPermutation(nums2);// 2,1,3
//        for (int i = 0; i < nums2.length; i++) {
//            out.print(nums2[i]+",");
//        }

        out.println(getPermutation(3,3));
        out.println(getPermutation(4,9));
    }
}
