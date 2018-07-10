package edu.princeton.cs.other;

import static java.lang.System.out;

/**
 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。

 示例：
 输入：1->2->4, 1->3->4
 输出：1->1->2->3->4->4
 * @author Mageek Chiu
 */
class TwoListMerge {


    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1==null && l2 ==null) return null;//特殊情况排除
        ListNode resultHead = new ListNode(0);// 作为哨兵节点
        ListNode resultTail = resultHead;
        while (l1 != null && l2 != null){
            if (l1.val<l2.val){
                resultTail.next = l1;
                l1 = l1.next;
            }else {
                resultTail.next = l2;
                l2 = l2.next;
            }
            resultTail = resultTail.next;
        }
        // 到这里就至少有一个null
        resultTail.next = l1!=null?l1:l2;
        return resultHead.next;
    }

    // 感受： 归并排序，然后就是各种特殊情况的考虑,涉及到链表加一个哨兵节点总是能简化问题
    public static void main (String ...args){
        ListNode a = new ListNode(2);
        ListNode a1 = new ListNode(4);
        ListNode a2 = new ListNode(6);
        ListNode a3 = new ListNode(12);
        a.next=a1;a1.next=a2;a2.next = a3;

        ListNode b = new ListNode(5);
        ListNode b1 = new ListNode(6);
        ListNode b2 = new ListNode(9);
        b.next=b1;b1.next=b2;

//        // 两个有序列表合并
//        ListNode res = mergeTwoLists(a,b);//
//        while (res!=null){//遍历打印
//            out.print(res.val+"->");
//            res = res.next;
//        }

        ListNode res = mergeTwoLists(null,null);//
        while (res!=null){//遍历打印
            out.print(res.val+"->");
            res = res.next;
        }

    }

//    将k个已排好序的链表合并为一个排序的链表。
//    第一次思考的时候，想得比较自然。打算仿照归并排序的思路来进行。
//
//    每次都在K个元素中选择一个最小的出来。每次选择最小的时间复杂度是O(K)，这样的事情一共做了O(N)次(假设一共有N个元素)。
//
//    另外注意需要考虑一些情况，比如 lists[i]如果在作为函数的输入，可能会在一开始就是NULL；处理到中途，
//      可能lists[i]对应的单链表从非空变化为了空。
// 每次从K个元素中找到最小的1个元素放入新链表的过程，其实就是每次找到最小的1个数的过程，这个过程可以用STL中的Algorithm库中的最小堆来实现。

//    这样每次从K个元素中找到最小的元素只需要O(1)的时间复杂度，然后调整新的堆也只需要O(lgK)的时间复杂度
//   将每个链表的表头元素取出来，建立一个小顶堆，因为k个链表中都排好序了，因此每次取堆顶的元素就是k个链表中的最小值，
//  可以将其合并到合并链表中，再将这个元素的指针指向的下一个元素也加入到堆中，再调整堆，取出堆顶，合并链表。。。。以此类推，
//   直到堆为空时，链表合并完毕。

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
        ListNode(int x,ListNode y) { val = x;next=y; }
    }


}
