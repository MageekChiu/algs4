package edu.princeton.cs.other;

import java.util.Arrays;
import static java.lang.System.out;
import java.util.List;

/**
 * 给定两个非空链表来代表两个非负整数，位数按照逆序方式存储，它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 示例：
    输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
    输出：7 -> 0 -> 8
    原因：342 + 465 = 807
 *
 *
 * 思路：
 *
 * @author Mageek Chiu
 */
class TwoListAdd {


    public static ListNode addTwoNumbers( ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode tail = res;
        int carry = 0;// 进位
        while (l1!=null && l2!=null){
            int result = l1.val + l2.val;// 本位相加
            result += carry;//加上进位
            int mod = result % 10;// 取余数就是本位该存的数
            ListNode thisRes = new ListNode(mod);
            tail.next = thisRes; tail = thisRes;
            carry = (result - mod)/10;// 得到进位
            l1=l1.next; l2=l2.next;
        }
        // 如果 某一个 较长
        ListNode noNull = l1==null ? (l2) : l1;
        while (noNull!=null){
            int result = noNull.val + carry;
            int mod = result % 10;// 取余数就是本位该存的数
            ListNode thisRes = new ListNode(mod);
            tail.next = thisRes; tail = thisRes;
            carry = (result - mod)/10;// 得到进位
            noNull=noNull.next;
        }
        if(carry!=0) tail.next = new ListNode(carry);
        return res.next;// 去掉第一个0
    }


    public static void main (String ...args){
        ListNode a = new ListNode(2);ListNode a1 = new ListNode(4);ListNode a2 = new ListNode(3);a.next=a1;a1.next=a2;
        ListNode b = new ListNode(5);ListNode b1 = new ListNode(6);ListNode b2 = new ListNode(4);b.next=b1;b1.next=b2;
        ListNode res = addTwoNumbers(a,b);// 342 + 465 = 807,所以输出 7,0,8
        while (res!=null){//遍历打印
            out.print(res.val+"->");
            res = res.next;
        }
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

}
