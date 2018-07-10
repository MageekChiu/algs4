package edu.princeton.cs.other;

import static java.lang.System.out;

/**
 删除链表的倒数第k个节点

 * @author Mageek Chiu
 */
class DeleteKNode {

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode mark = new ListNode(0); mark.next = head;// 设置一个哨兵节点，避免删除头结点的特殊判断。
        ListNode p1 = mark , p2 = mark;// p1 先走，p2 后走 n 步
        while (p1.next!=null){
            p1 = p1.next;
            if ((--n)<0){
                p2 = p2.next;
            }
        }
        // 此时 p1 是尾节点， p2 就是待删除节点的前缀
        p2.next = p2.next.next;
        return mark.next;
    }

    // 感受：双指针，哨兵节点两个点
    public static void main (String ...args){
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        a.next=b;b.next=c;c.next=d;d.next=e;e.next=null;

//        ListNode res = removeNthFromEnd(a,3);//
//        while (res!=null){
//            out.print(res.val+"->");res = res.next;
//        }

//        ListNode res = removeNthFromEnd(a,1);//
//        while (res!=null){
//            out.print(res.val+"->");res = res.next;
//        }

        ListNode res = removeNthFromEnd(e,1);//
        while (res!=null){
            out.print(res.val+"->");res = res.next;
        }

    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
}
