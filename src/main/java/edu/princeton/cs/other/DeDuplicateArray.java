package edu.princeton.cs.other;

import edu.princeton.cs.other.DeleteKNode.ListNode;
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

 ------------------------------------------------------------------------------------------------------------
 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 示例 1:
 给定 nums = [1,1,1,2,2,3],
 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 你不需要考虑数组中超出新长度后面的元素。
 示例 2:
 给定 nums = [0,0,1,1,1,1,2,3,3],
 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
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

 ------------------------------------------------------------------------------------------------------------

 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 示例 1:
 输入: 1->2->3->3->4->4->5
 输出: 1->2->5
 示例 2:
 输入: 1->1->1->2->3
 输出: 2->3

 ------------------------------------------------------------------------------------------------------------
 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 示例 1:
 输入: 1->1->2
 输出: 1->2
 示例 2:
 输入: 1->1->2->3->3
 输出: 1->2->3

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
        while(i<A.length&&j<A.length){//直接把不重复的数拿出来即可
            if (A[i]!=A[j]){
                A[++i] = A[j];
            }
            j++;
        }
        return i+1;
    }

    /**
     * 在上一题的基础之上加一个判断即可
     * @param A
     * @return
     */
    public static int removeDuplicates2(int[] A) {
        int i=0;
        int j=1;
        if(A.length==0||A.length==1) return A.length;
        int duplicate = 0;
        while(i<A.length&&j<A.length){
            if(A[i]==A[j]){
                duplicate++;
                if (duplicate==1){
                    A[++i]=A[j];
                }
                j++;
            }else{
                duplicate = 0;
                A[++i]=A[j++];
            }
        }
        return i+1;
    }

    /**

     给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
     示例 1:
     输入: 1->2->3->3->4->4->5
     输出: 1->2->5
     示例 2:
     输入: 1->1->1->2->3
     输出: 2->3

     * 新建一个哨兵节点，值为-1.
     * 如果当前节点有重复，就删除节点指直到不重复的，将当前节点的前一个节点的指针指向不重复的，保持链表不断。
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if(head==null||head.next==null)
            return head;
        ListNode p=head;
        ListNode sentinel=new ListNode(-1);
        sentinel.next=head;
        ListNode pre=sentinel;
        while(p!=null&&p.next!=null){
            if(p.val!=p.next.val){
                pre=p;
            }else{
                while(p.next!=null&&p.val==p.next.val)
                    p=p.next;
                pre.next=p.next;
            }
            p=p.next;
        }
        return sentinel.next;

    }

    public static ListNode deleteDuplicates1(ListNode head) {
        if(head == null || head.next == null)  return head;

        ListNode p = head;
        ListNode q = p.next;
        while(p!=null && q != null){
            while(q != null && p.val == q.val){
                //当p和下一个q相等时，往后移动q
                q = q.next;
            }
            p.next = q;
            p = q;
            if(p !=null){
                q = p.next;
            }

        }
        return head;
    }

    /**
     给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），
     可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
     示例 1:
     输入: [1,3,4,2,2]
     输出: 2
     示例 2:
     输入: [3,1,3,4,2]
     输出: 3
     说明：
        不能更改原数组（假设数组是只读的）。
        只能使用额外的 O(1) 的空间。
        时间复杂度小于 O(n2) 。
        数组中只有一个重复的数字，但它可能不止重复出现一次。

     找出1到n中重复的数字 - CSDN博客: https://blog.csdn.net/Apie_CZX/article/details/49278447

     方法1：
     另外开创一个数组hash[]，大小为n + 1，初始值都为0。因为原数组范围都在1到n内，所以我们可以直接通过对相应的位置上的hash[nums[i]]++来标志。
     通过判断当前的hash[nums[i]]是否为0就可以知道其是否是重复的数字。
     时间复杂度O(n)，空间复杂度O(n)，不用移动原数组，不用改动原数组。

     方法2：
     排序：排序也是比较容易想到的方法，其实排序可以针对范围不止在1到n的数字。
     （这在另一方面就说明了排序的算法拓展了题目中的条件，所以是没有充分利用题目的条件，当然也就很难成为最优）
     时间复杂度O(nlogn)，空间复杂度O(1)，需要移动原数组，不用改动原数组。

     方法3：
     类基数排序：如果当前的数字不等于当前的位置，就把当前数字换到其对应的位置上去，然后依次类推，直到找到重复的元素为止。
     相应的方法可以看：http://blog.csdn.net/morewindows/article/details/8204460
     我这里举个栗子：比如对于nums为数组 [2, 4, 8, 5, 7, 6, 1, 9, 3, 2] 的情况。
     nums[0]的位置是2，把2和它本来的位置上的数字nums[1]调换得到：[4, 2, 8, 5, 7, 6, 1, 9, 3, 2]
     nums[0]的位置是4，把4和它本来的位置上的数字nums[3]调换得到：[5, 2, 8, 4, 7, 6, 1, 9, 3, 2]
     nums[0]的位置是5，把5和它本来的位置上的数字nums[4]调换得到：[7, 2, 8, 4, 5, 6, 1, 9, 3, 2]
     nums[0]的位置是7，把7和它本来的位置上的数字nums[6]调换得到：[1, 2, 8, 4, 5, 6, 7, 9, 3, 2]
     nums[0]的位置是1，刚好！那么就不用调换，前进。
     nums[1]的位置是2，刚好！那么就不用调换，前进。
     nums[2]的位置是8，把8和它本来的位置上的数字nums[7]调换得到：[1, 2, 9, 4, 5, 6, 7, 8, 3, 2]
     nums[2]的位置是9，把9和它本来的位置上的数字nums[8]调换得到：[1, 2, 3, 4, 5, 6, 7, 8, 9, 2]
     nums[2]的位置是3，刚好！那么就不用调换，前进。
     nums[3]的位置是4，刚好！那么就不用调换，前进。
     nums[4]的位置是5，刚好！那么就不用调换，前进。
     nums[5]的位置是6，刚好！那么就不用调换，前进。
     nums[6]的位置是7，刚好！那么就不用调换，前进。
     nums[7]的位置是8，刚好！那么就不用调换，前进。
     nums[8]的位置是9，刚好！那么就不用调换，前进。
     nums[9]的位置是2，而2的位置nums[1]上已经是2了，所以就找到了重复元素2。
     时间复杂度O(n)，空间复杂度O(1)，需要移动原数组，不用改动原数组。

     方法4：
     标记法：我们可以看到，其实在nums[n + 1]中，因为数字都是在1到n之内，所以nums[i]就像一个个指针，指着另外的位置上的数字。
     有一个巧妙的办法是，我们遍历一遍数字，把nums[i]指向的数（即nums[nums[i]]）做一个+n的操作，
     那么如果遇到一个nums[nums[i]]的值已经大于n了，说明这个数已经被其他数字指到过了，也就是找到了重复值。
     在执行的过程中，我们还要先判断一下nums[i]是否大于n（因为可能先前被别人指过所以+n了），用一个值来保存其原来的值。
     具体的思路参考：http://blog.csdn.net/morewindows/article/details/8212446
     时间复杂度O(n)，空间复杂度O(1)，不用移动原数组，需要改动原数组。

     方法5：（举一反三，适合教程）
     二分搜索法：二分搜索其实是个很神奇的东西，用的好可以举一反三用在各种地方，其实，
     关键就是你把left和right当做是哪里的值，一般人都只是把这两个当做是数组中的min和max（在本题1和n），
     或者是数组中的头尾（本题中的nums[0]和nums[n]）。所以，适用不了。
     其实，换个思路，我们只要把left和right当做是ans的下上界限就能别有洞天。接着开展二分搜索中的搜索过程：mid取中值，
     那么nums中的数就被分成了[left - mid - right]两端了。
     然后我们遍历一遍nums，统计所有<=mid的值count，如果left + count > mid + 1，说明[ left - mid ]段的数字中存在重复（ans为其区间的值），
     所以令right = mid。
     反之就是[ mid - right ]的数字，所以令left = mid + 1；
     知道其结束条件即可。
     时间复杂度O(nlogn)，空间复杂度O(1)，不用移动原数组，不用改动原数组。

     方法6：（最佳）

     链表找环法：首先，我们来复习一下链表找环：参考：http://blog.csdn.net/xudacheng06/article/details/7706245
     有一个链表，要确定其中是否有环，以及环的入口：
     寻找环的入口点：
     设置两个步长的指针：fast和slow，初始值都设置为头指针。其中fast每次2步，slow每次一步，发现fast和slow重合，确定了单向链表有环路。
     接下来，让fast回到链表的头部，重新走，每次步长1，那么当fast和slow再次相遇的时候，就是环路的入口了。
     证明：在fast和slow第一次相遇的时候，假定slow走了n步，环路的入口是在p步，那么
     slow走的路径： p+c ＝ n； c为fast和slow相交点 距离环路入口的距离
     fast走的路径： p+c+k*L = 2*n； L为环路的周长，k是整数
     显然，如果从p+c点开始，slow再走n步的话，还可以回到p+c这个点。
     同时，fast从头开始走，步长为1，经过n步，也会达到p+c这点。
     显然，在这个过程中fast和slow只有前p步骤走的路径不同。所以当p1和p2再次重合的时候，必然是在链表的环路入口点上。

     方法7：（适用于只有一个重复元素且只重复一次的情况）
     数学平方和法：如果题目中重复元素只重复一次的话，或者改一下题目，对于nums[n]，有一个数字没有出现，还有一个数字重复。我们还可以用数学的方法来解决。
     不妨设消失的数字为a，重复的数字为b。我们的思路是找到两个二元方程，就可以解出a和b。
     如果按照原本的数字排列，有其总和为1+2+...+n = n*(1+n)/2。
     有其平方和为1^2+2^2+...+n^2 = n*(n+1)*(2*n+1)/6。
     现在的总和为1+2+...+n-a+b，
     现在的平方和为1^2+2^2+...+n^2-a^2+b^2。
     所以可以计算出a和b。
     时间复杂度O(n)，空间复杂度O(1)，不用移动原数组，不用改动原数组。
    */
    public static int findDuplicate(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i){
            int next = nums[i] - 1;
            if (nums[i] > n)
                next -= n;
            if (nums[next] > n)
                return next + 1;
            else
                nums[next] += n;
        }
        return -1;
    }

    // 感受：
    public static void main (String ...args){
//        int[] nums = {0,0,1,1,1,2,2,3,3,4};//
//        int[] nums = {1,1,1,2,2,3};//
//        out.println(removeDuplicates(nums));
//        out.println(removeDuplicates2(nums));
//        for (int num : nums) {
//            out.print(num+",");
//        }

//        DeleteKNode.ListNode a = new DeleteKNode.ListNode(1);
//        DeleteKNode.ListNode b = new DeleteKNode.ListNode(2);
//        DeleteKNode.ListNode c = new DeleteKNode.ListNode(2);
//        DeleteKNode.ListNode d = new DeleteKNode.ListNode(4);
//        DeleteKNode.ListNode e = new DeleteKNode.ListNode(6);
//        DeleteKNode.ListNode f = new DeleteKNode.ListNode(12);
//        a.next=b;b.next=c;c.next=d;d.next=e;e.next=f;f.next=null;
//
//        DeleteKNode.ListNode listNode = deleteDuplicates(a);
//        while (listNode!=null){
//            System.out.println(listNode.val);
//            listNode = listNode.next;
//        }

        System.out.println(findDuplicate(new int[]{1,2,3,4,5,5}));//5
        System.out.println(findDuplicate(new int[]{1,3,4,2,2}));//2

    }
}
