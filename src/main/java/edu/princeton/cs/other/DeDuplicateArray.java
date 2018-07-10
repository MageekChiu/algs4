package edu.princeton.cs.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

/**
 数组去重
 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。

 示例 1:

 给定数组 nums = [1,1,2],

 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。

 你不需要考虑数组中超出新长度后面的元素。
 示例 2:

 给定 nums = [0,0,1,1,1,2,2,3,3,4],

 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。

 你不需要考虑数组中超出新长度后面的元素。
 说明:

 为什么返回数值是整数，但输出的答案是数组呢?

 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。

 你可以想象内部操作如下:

 // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 int len = removeDuplicates(nums);

 // 在函数里修改输入数组对于调用者是可见的。
 // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 for (int i = 0; i < len; i++) {
    print(nums[i]);
 }
 * @author Mageek Chiu
 */
class DeDuplicateArray {

    /**
     思路：一个数组是有序的，所以算法实现起来相对比较简单，因为只需比较数组相邻的两个数字即可，存在两种情况
     1：如果数组里面不存在元素或者只存在一个元素，那么就不需要进行比较，直接返回数组的长度即可；
     2：数组长度大于一的话那么就需要比较数组的相邻的两个元素，如果相等 的话那么后一个元素的指针往后移一位，
     然后前一个元素的指针接着往后移一位，将当前后一个元素指针所指的数字赋给前一个元素指针所指的位置，
     然后后一元素指针继续加一。如果相邻俩个元素不等的话，则直接前一元素指针加一与后一元素重合，然后后一元素指针继续加一。
     */
    public static int removeDuplicates(int[] A) {
        int i=0;
        int j=1;
        if(A.length==0||A.length==1){
            return A.length;
        }
        while(i<A.length&&j<A.length){
            if(A[i]==A[j]){
                j++;
            }else{
                A[++i]=A[j++];
            }

        }
        return i+1;
    }

    // 感受：
    public static void main (String ...args){
        int[] nums = {0,0,1,1,1,2,2,3,3,4};//
        out.println(removeDuplicates(nums));
    }
}
