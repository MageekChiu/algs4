package edu.princeton.cs.other;

import static java.lang.System.out;

/**
 * 给定两个非空链表来代表两个非负整数，位数按照逆序方式存储，它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 示例：
    输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
    输出：7 -> 0 -> 8
    原因：342 + 465 = 807

 就像你在纸上计算两个数字的和那样，我们首先从最低有效位也就是列表 l1l1 和 l2l2 的表头开始相加。
 由于每位数字都应当处于 0 \ldots 90…9 的范围内，我们计算两个数字的和时可能会出现“溢出”。
 例如，5 + 7 = 125+7=12。在这种情况下，我们会将当前位的数值设置为 22，并将进位 carry = 1carry=1 带入下一次迭代。
 进位 carrycarry 必定是 00 或 11，这是因为两个数字相加（考虑到进位）可能出现的最大和为 9 + 9 + 1 = 199+9+1=19。

 伪代码如下：

 将当前节点初始化为返回列表的哑节点。
 将进位 carrycarry 初始化为 00。
 将 pp 和 qq 分别初始化为列表 l1l1 和 l2l2 的头部。
 遍历列表 l1l1 和 l2l2 直至到达它们的尾端。
     将 xx 设为节点 pp 的值。如果 pp 已经到达 l1l1 的末尾，则将其值设置为 00。
     将 yy 设为节点 qq 的值。如果 qq 已经到达 l2l2 的末尾，则将其值设置为 00。
     设定 sum = x + y + carrysum=x+y+carry。
     更新进位的值，carry = sum / 10carry=sum/10。
     创建一个数值为 (sum \bmod 10)(summod10) 的新节点，并将其设置为当前节点的下一个节点，然后将当前节点前进到下一个节点。
     同时，将 pp 和 qq 前进到下一个节点。
 检查 carry = 1 是否成立，如果成立，则向返回列表追加一个含有数字 11 的新节点。
 返回哑节点的下一个节点。

 请注意，我们使用哑节点来简化代码。如果没有哑节点，则必须编写额外的条件语句来初始化表头的值。
 请特别注意以下情况：
 当一个列表比另一个列表长时。
 当一个列表为空时，即出现空列表。
 求和运算最后可能出现额外的进位，这一点很容易被遗忘

 复杂度分析

 时间复杂度：O(\max(m, n))O(max(m,n))，假设 mm 和 nn 分别表示 l1l1 和 l2l2 的长度，
 上面的算法最多重复 \max(m, n)max(m,n) 次。

 空间复杂度：O(\max(m, n))O(max(m,n))， 新列表的长度最多为 \max(m,n) + 1max(m,n)+1。

 拓展

 如果链表中的数字不是按逆序存储的呢？例如：
     (3→4→2)+(4→6→5)=8→0→7

 可以翻转列表转化为原问题

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

        // 两个列表相加
        ListNode res = addTwoNumbers(a,b);// 342 + 465 = 807,所以输出 7,0,8
        while (res!=null){//遍历打印
            out.print(res.val+"->");
            res = res.next;
        }

        out.println();

        // 翻转列表
        ListNode resV = revertList(a);
        while (resV!=null){//遍历打印
            out.print(resV.val+"->");
            resV = resV.next;
        }

    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
        ListNode(int x,ListNode y) { val = x;next=y; }
    }

    // 简便的做法，复杂度为O(n)
    public ListNode addTwoNumber(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }


    /**
     * 单向列表翻转
     * @param l1
     * @return
     */
    public static ListNode revertList(ListNode l1){

        if (l1==null) return l1;
        ListNode pre = l1;
        ListNode cur = l1.next;
        ListNode tmp;

        // l1,l2,l3,l4,l5,l6
        while (cur != null){
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        l1.next = null;// 翻转后列表的尾部是空的
        return pre;
    }

}