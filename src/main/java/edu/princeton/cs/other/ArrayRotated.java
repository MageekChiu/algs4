package edu.princeton.cs.other;

/**

 1. 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 你可以假设数组中不存在重复的元素。
 你的算法时间复杂度必须是 O(log n) 级别。
 示例 1:
 输入: nums = [4,5,6,7,0,1,2], target = 0
 输出: 4
 示例 2:
 输入: nums = [4,5,6,7,0,1,2], target = 3
 输出: -1

 思路：可以先用二分法把转折点找到，再在这两个有序子数组中分别进行二分查找，结果就是两个子结果中的有效结果

 转折点怎么找？ 旋转数组实际上是两个递增数组的组成，
 且第一个数组中的任意一个值都大于第二个数组的最大值，还是用二分查找的思想。


 2. 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 示例 1:
 输入: nums = [2,5,6,0,0,1,2], target = 0
 输出: true
 示例 2:
 输入: nums = [2,5,6,0,0,1,2], target = 3
 输出: false
 进阶:
 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？

 * @author Mageek Chiu
 */
class ArrayRotated {

    // 无重复元素
    public static int search(int[] nums, int target) {
        if(nums.length<=0) return -1;
        if(nums.length==1) return nums[0]==target?0:-1;

        int begin=0,end=nums.length-1;
        while(begin<=end){
            int mid=(end+begin)/2;
            if(nums[mid]==target)// 找到直接返回
                return mid;
            if(nums[mid]>=nums[begin]){//此时begin和mid肯定处在同一个递增数组上,mid，begin可能是同一个数，所以要等号
                //那么就直接运用原始的二分查找
                if(nums[begin]<=target&&target<nums[mid])
                    end=mid-1;
                else
                    begin=mid+1;
            }else{//此时mid处于第二个递增数组 begin处于第一个递增数组 自然的mid和end肯定处于第二个递增数组上
                //还是直接运用原始的二分查找思想
                if(nums[mid]<target&&target<=nums[end])
                    begin=mid+1;
                else
                    end=mid-1;
            }
        }
        return -1;
    }

    // 1 3 1 1 1   3
    // 有重复元素,在无重复的基础之上考虑 begin 和 mid 值相等的情况即可
    public static boolean searchRepeat(int[] nums, int target) {
//        return search(nums, target) > 0;// 这样是不行的，重复元素会影响

        if(nums.length<=0) return false;
        if(nums.length==1) return nums[0] == target;

        int begin=0,end=nums.length-1;
        while(begin<=end){
            int mid=(end+begin)/2;
            if(nums[mid]==target)// 找到直接返回
                return true;
            if(nums[mid]>nums[begin]){//此时begin和mid肯定处在同一个递增数组
                //那么就直接运用原始的二分查找
                if(nums[begin]<=target&&target<nums[mid])
                    end=mid-1;
                else
                    begin=mid+1;
            }else if(nums[mid] < nums[begin]){
                //还是直接运用原始的二分查找思想
                if(nums[mid]<target&&target<=nums[end])
                    begin=mid+1;
                else
                    end=mid-1;
            }else {
                begin++;
            }
        }
        return false;
    }

    // 4,5,6,7,0,1,2
    // 4,5,6,0,1,2,3
    // 4,5,6,7,8,1,2
    // 7,9,2,3,4,5,6
    public static int searchPivot(int[] nums,int l,int r) {
        if (l<0 || r > nums.length || l>= r) return -1;

        int pivot = (l+r)/2;
        if (nums[pivot-1] > nums[pivot]){// 找到了,pivot 就是原始起点
            return pivot;
        }else{
            int ll = searchPivot(nums,l,pivot-1);
            int rr = searchPivot(nums,pivot+1,r);
            if (ll>0){
                return l+ll;
            }
            if (rr>0){
                return pivot+rr;
            }
        }
//        out.println(pivot);
        return pivot;
    }

    /**
     * 指定范围内的二分查找 [start,end)，所以一般就调用 binarySearch(num,0,num.length,6)
     * 这种涉及边界的一开始就要定义好如果不包含 end 那么 r 就是从 end -1 开始的
     */
    public static int binarySearch(int[] nums,int start,int end,int target){
        if (start < 0 || end > nums.length) return -1;// 排除特殊
        int l = start,r = end-1,middle;
        while (l<=r){
            middle = (l+r)/2;
            if (target==nums[middle]){
                return middle;
            }else if (target>nums[middle]){
                l = middle+1;
            }else {
                r = middle-1;
            }
        }
        return -1;
    }

    // 感受：遇上新东西关键就是要理解他的概念，然后梳理其性质，
    // 然后根据性质来求解
    public static void main (String ...args){
//        int[] num = {0,1,2,4,5,6,7,8};
//        out.println(binarySearch(num,0,num.length,6));
//        out.println(binarySearch(num,0,num.length,4));
//        out.println(binarySearch(num,2,num.length-2,4));
//        out.println(binarySearch(num,1,num.length+3,4));

//        int[] nums = {4,5,6,7,0,1,2};
//        int res = searchPivot(nums,0,nums.length);
//        out.println(res);

//        int[] nums = {4,5,6,7,0,1,2};
//        int res = search(nums,2);//-1
//        System.out.println(res);

//        int[] nums = {3,1};
//        int res = search(nums,1);//1
//        System.out.println(res);

//        int[] nums = {7,1,2,4,6};
//        int res = search(nums,7);//1
//        System.out.println(res);

//        int[] nums = {2,5,6,0,0,1,2};
//        System.out.println(search(nums,0));
//        System.out.println(search(nums,3));

//        int[] nums = {1};
//        System.out.println(searchRepeat(nums,1));

//        int[] nums = {1,3,1,1,1};
//        System.out.println(searchRepeat(nums,3));
//
//        int[] nums = {3,1};
//        System.out.println(searchRepeat(nums,1));

//        int[] nums = {1,1,3,1};
//        System.out.println(searchRepeat(nums,3));
    }
}
