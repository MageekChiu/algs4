package edu.princeton.cs.other;

/**
 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 你的算法时间复杂度必须是 O(log n) 级别。
 如果数组中不存在目标值，返回 [-1, -1]。
 示例 1:
 输入: nums = [5,7,7,8,8,10], target = 8
 输出: [3,4]
 示例 2:
 输入: nums = [5,7,7,8,8,10], target = 6
 输出: [-1,-1]


 * @author Mageek Chiu
 */
class SearchRange {
    public static int[] searchRange(int[] nums, int target) {
        return searchRange(nums,0,nums.length,target);
    }

    /**
     范围 [start,end)
     * @param nums
     * @param start
     * @param end
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums,int start,int end, int target){
        int l = start,r = end;
        if (nums.length<1 || start>end) return new int[]{-1,-1};
        if (start==end) return new int[]{target==nums[l]?l:-1,target==nums[r]?r:-1};

        int ans = ArrayRotated.binarySearch(nums,start,end,target);// 找到的第一个
        if (ans==-1) return new int[]{-1,-1};

        if ((ans > 0 && nums[ans]>nums[ans-1]) || ans==start){// 是开头
            l = ans;
        }else {// 左边还有该数
            l = searchRange(nums,start,ans,target)[0];
        }
        if (ans < nums.length-1 && nums[ans]<nums[ans+1] || ans == end-1){// 是结尾
            r = ans;
        }else{// 右边还有该数
            r = searchRange(nums,ans+1,end,target)[1];
        }
        return new int[]{l,r};
    }

//    大家对于二分查找并不陌生，一般意义上的二分查找，往往返回给我们的是目标元素在排序数组中出现的一个随机的位置，
// 但是在很多时候，我们却是需要目标元素的第一个和最后一个位置，才有意义。
// 举个例子来说，我们要求得目标元素在排序数组中出现的次数，假如利用一般的方法，逐个比较目标元素和数组元素，时间复杂度O（n），
// 不能够令我们满意，既然数组是排序的我们很容易想到二分查找，在这里我们能不能使用二分查找的算法呢，
// 答案是肯定的。只要我们能够利用二分查找找到目标元素出现的第一个和最后一个位置，就能够求得它出现的次数。
// 我们如何来求得目标元素出现的第一个和最后一个位置呢，其实很简单，我们只需要对于二分查找的退出条件，
// 做一个简单的设定就能得到我们理想的结果哦！
//    下面我们来看一下代码:
//
//    int GetFirstK(int *a, int _left, int _right, int dest)
//    {
//        if(_left > _right || a == NULL)
//        {
//            return 0;
//        }
//        int temp = 0;
//        int left = _left;
//        int right = _right;
//        while(left < right)
//        {
//            temp = (left + right) >> 1;
//            if(a[temp] < dest)
//            {
//                left = temp + 1;
//            }
//            else
//            {
//                right = temp;
//            }
//        }
//        return left;
//    }
//    在这里跟一般的二分查找的代码相比，仅仅是判断语句上做了一点细微的变化，序列是递增排列的，
// 当中间值小于目标元素的时候，目标元素在序列的右边：left = temp + 1；
//    其余的情况目标值在序列的左边：right = temp；我们要查找的第一个目标元素的位置，
// 一般的情况就是目标元素存在多个情况，由上述的两个判断条件，我们可以知道，如果查找到了目标元素，
// 并且该目标元素不是第一个的时候，此时left
//    去最后一次出现的位置，道理也是类似的：
//
//    int GetUpK(int *a, int _left, int _right, int dest)
//    {
//        if(_left > _right || a == NULL)
//        {
//            return 0;
//        }
//        int temp = 0;
//        int left = _left;
//        int right = _right;
//        while(left < right)
//        {
//            temp = (left + right + 1) >> 1; //保证取到中间靠后的位置
//            if(a[temp] > dest)
//            {
//                right = temp - 1;
//            }
//            else
//            {
//                left = temp;
//            }
//        }
//        return right;
//    }
//
//    大家可以看出，跟我们取第一个元素时候的判断条件恰好相反，而两种情况处理的方式我们可以归结为以下两句话：
//            1、当我们要找到目标元素出现的第一个位置时候：当中间值大于等于目标元素的时候，我们要保留当前中间值的位置，并且在左边继续查找。
//    这句话用条件语句表述就是：
//            if（a【mid】 >= dest)
//    right = mid;
//
//2、当我们要找目标元素出现的最后一个位置的时候：当中间值小于等于目标元素的时候，我们要保留中间值的位置，并且在右边继续查找。
//            if(a[mid] <= dest)
//    left = mid;

    /**
     给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     你可以假设数组中无重复元素。
     示例 1:
     输入: [1,3,5,6], 5
     输出: 2
     示例 2:
     输入: [1,3,5,6], 2
     输出: 1
     示例 3:
     输入: [1,3,5,6], 7
     输出: 4
     示例 4:
     输入: [1,3,5,6], 0
     输出: 0
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        int l = 0,r = nums.length-1,middle;
        while (l<=r){
            middle = l+(r-l)/2;
            if (target==nums[middle]){
                return middle;
            }else if (target>nums[middle]){
                l = middle+1;
            }else {
                r = middle-1;
            }
        }
//        return r;// r 就是待插入数字target在新数组中的左边第一个位置
        return l;// l 就是待插入数字target在新数组中位置
    }

    /**
     给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
     请注意，它是排序后的第k小元素，而不是第k个元素。

     示例:
     matrix = [
        [ 1,  5,  9],
        [10, 11, 13],
        [12, 13, 15]
     ],
     k = 8,
     返回 13。
     说明:
     你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n2

     思路1：首先 n 路归并排序 小到大，然后在找第k个，这样需要遍历所有元素


     思路2：一个不熟悉的场景，对其特性与性质进行总结：
     第i行（0开始）对角线处的元素[i,i]至少 >= （i+1）^2 个元素
     左上方一定比当前元素小，
     右下方一定比当前元素大，所以[i,i]  至少 <= (n-i)^2 个元素
     升序排列第k个元素，亦即至多 < n-k 个元素（因为可能重复）

     https://www.cnblogs.com/grandyang/p/5727892.html

     思路3：一种利用堆的方法，我们使用一个最大堆，然后遍历数组每一个元素，将其加入堆，根据最大堆的性质，
     大的元素会排到最前面，然后我们看当前堆中的元素个数是否大于k，大于的话就将首元素去掉，循环结束后我们返回堆中的首元素即为所求:

     思路4：也可以用二分查找法来做，我们由于是有序矩阵，那么左上角的数字一定是最小的，而右下角的数字一定是最大的，
     所以这个是我们搜索的范围，然后我们算出中间数字mid，由于矩阵中不同行之间的元素并不是严格有序的，
     所以我们要在每一行都查找一下mid，我们使用upper_bound，这个函数是查找第一个大于目标数的元素，
     如果目标数在比该行的尾元素大，则upper_bound返回该行元素的个数，如果目标数比该行首元素小，则upper_bound返回0,
     我们遍历完所有的行可以找出中间数是第几小的数，然后k比较，进行二分查找，left和right最终会相等，并且会变成数组中第k小的数字。
     举个例子来说吧，比如数组为:

     [  1    2
        12 100  ]
     k = 3
     那么刚开始left = 1, right = 100, mid = 50, 遍历完 cnt = 3，此时right更新为50
     此时left = 1, right = 50, mid = 25, 遍历完之后 cnt = 3, 此时right更新为25
     此时left = 1, right = 25, mid = 13, 遍历完之后 cnt = 3, 此时right更新为13
     此时left = 1, right = 13, mid = 7, 遍历完之后 cnt = 2, 此时left更新为8
     此时left = 8, right = 13, mid = 10, 遍历完之后 cnt = 2, 此时left更新为11
     此时left = 11, right = 12, mid = 11, 遍历完之后 cnt = 2, 此时left更新为12
     循环结束，left和right均为12，任意返回一个即可。 复杂度O(nlgn*lgX)


     上面的解法还可以进一步优化到O(nlgX)，其中X为最大值和最小值的差值，我们并不用对每一行都做二分搜索法，
     我们注意到每列也是有序的，我们可以利用这个性质，从数组的左下角开始查找，
     如果比目标值小，我们就向右移一位，而且我们知道当前列的当前位置的上面所有的数字都小于目标值，
     那么cnt += i+1，反之则向上移一位，这样我们也能算出cnt的值。其余部分跟上面的方法相同，

     */
    // O(nlgn*lgX)
//    public static int kthSmallest(int[][] matrix, int k) {
//        int n = matrix.length;
//        int left = matrix[0][0], right = matrix[n-1][n-1];
//        while (left < right) {
//            int mid = left + (right - left) / 2, cnt = 0;
//            for (int i = 0; i < matrix.size(); ++i) {
//                cnt += upper_bound(matrix[i][0], matrix[i][n-1], mid) - matrix[i][0];
//            }
//            if (cnt < k) left = mid + 1;
//            else right = mid;
//        }
//        return left;
//    }
//
//    public static int upper_bound(int start,int end,int mid){
//
//    }


    // O(nlgX)
    public static int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0], right = matrix[n-1][n-1];
        while (left < right) {
            int mid = left + (right - left) / 2;
            int cnt = search_less_equal(matrix, mid);
            if (cnt < k) left = mid + 1;
            else right = mid;
        }
        return left;
    }
    public static int search_less_equal(int[][] matrix, int target) {
        int n = matrix.length, i = n - 1, j = 0, res = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= target) {
                res += i + 1;
                ++j;
            } else {
                --i;
            }
        }
        return res;
    }

    // 感受：
    public static void main (String ...args){
//        int[] nums = {5,7,7,8,8,10};
//        int[] res = searchRange(nums,8);// [3,4]
//        System.out.println(res[0]+","+res[1]);

//        int[] nums1 = {5,7,7,8,8,10};
//        int[] res1 = searchRange(nums1,6);//[-1,-1]
//        out.println(res1[0]+","+res1[1]);

//        int[] nums1 = {5,7,7,8,8};
//        int[] res1 = searchRange(nums1,8);//[3,4]
//        System.out.println(res1[0]+","+res1[1]);

//        int[] nums = {0,0,5,7,7,8,8};
//        int[] res = searchRange(nums,0);//[0,1]
//        System.out.println(res[0]+","+res[1]);

//        int[] nums = {2,2};
//        int[] res = searchRange(nums,2);//[0,1]
//        System.out.println(res[0]+","+res[1]);

//        int[] nums = {1,3,5,6};
//        System.out.println(searchInsert(nums,5));//2
//
//        int[] nums1 = {1,3,5,6};
//        System.out.println(searchInsert(nums1,2));//1
//
//        int[] nums2 = {2,3,5,6};
//        System.out.println(searchInsert(nums2,1));//0
//
//        int[] nums3 = {2,3,5,6};
//        System.out.println(searchInsert(nums3,7));//4
    }
}
