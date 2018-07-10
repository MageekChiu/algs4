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

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
        ListNode(int x,ListNode y) { val = x;next=y; }
    }


}
