package edu.princeton.cs.other;

import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdRandom;

import static java.lang.System.out;
/**
 *
 * @author Mageek Chiu
 */
class Template {

    public static Comparable numberK(Comparable[] nums, int k, int left, int right) {
        int storeIndex = Quick.partition(nums,left,right);
        Comparable pivotValue = nums[storeIndex];
        if (storeIndex > k){// 在左边

        }else if (storeIndex < k){// 在右边

        }else {// storeIndex == k  就是它了
            return pivotValue;
        }


        return 0;
    }




    public static void main (String ...args){
        Integer[] nums = {11,1,2,3,12,4,5,15,6,13,7,8,14,9,10} ; int k = 5 ; int len = nums.length;
        Comparable res = numberK(nums,k,0,len);//第k大的数  11
         out.println(res);
    }
}
