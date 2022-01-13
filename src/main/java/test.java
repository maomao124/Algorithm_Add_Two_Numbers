/**
 * Project name(项目名称)：算法_Add_Two_Numbers
 * Package(包名): PACKAGE_NAME
 * Class(类名): test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/1/12
 * Time(创建时间)： 23:31
 * Version(版本): 1.0
 * Description(描述)： 两个链表表示的数相加
 * 首先每一位相加肯定会产生进位，我们用 carry 表示。进位最大会是 1 ，因为最大的情况是无非是 9 + 9 + 1 = 19 ，
 * 也就是两个最大的数相加，再加进位，这样最大是 19 ，不会产生进位 2 。下边是伪代码。
 * <p>
 * * 初始化一个节点的头，dummy head ，但是这个头不存储数字。并且将 curr 指向它。
 * * 初始化进位 carry 为 0 。
 * * 初始化 p 和 q 分别为给定的两个链表 l1 和 l2 的头，也就是个位。
 * * 循环，直到 l1 和 l2 全部到达 null 。
 * * 设置 x 为 p 节点的值，如果 p 已经到达了 null，设置 x 为 0 。
 * * 设置 y 为 q 节点的值，如果 q 已经到达了 null，设置 y 为 0 。
 * * 设置 sum = x + y + carry 。
 * * 更新 carry = sum / 10 。
 * * 创建一个值为 sum mod 10 的节点，并将 curr 的 next 指向它，同时 curr 指向变为当前的新节点。
 * * 向前移动 p 和 q 。
 * * 判断 carry 是否等于 1 ，如果等于 1 ，在链表末尾增加一个为 1 的节点。
 * * 返回 dummy head 的 next ，也就是个位数开始的地方。
 * <p>
 * 初始化的节点 dummy head 没有存储值，最后返回 dummy head 的 next 。这样的好处是不用单独对 head 进行判断改变值。
 * 也就是如果一开始的 head 就是代表个位数，那么开始初始化的时候并不知道它的值是多少，所以还需要在进入循环前单独对它进行值的更正，不能像现在一样只用一个循环简洁。
 */

class ListNode
{
    int val;
    ListNode next;

    ListNode(int x)
    {
        val = x;
    }
}


public class test
{
    public ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null)
        {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null)
            {
                p = p.next;
            }
            if (q != null)
            {
                q = q.next;
            }
        }
        if (carry > 0)
        {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    public ListNode reverseList(ListNode head)
    {
        if (head == null)
        {
            return null;
        }
        ListNode pre = null;
        ListNode next;
        while (head != null)
        {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public ListNode reverseListRecursion(ListNode head)
    {
        ListNode newHead;
        if (head == null || head.next == null)
        {
            return head;
        }
        newHead = reverseListRecursion(head.next); //head.next 作为剩余部分的头指针
        head.next.next = head; //head.next 代表新链表的尾，将它的 next 置为 head，就是将 head 加到最后了。
        head.next = null;
        return newHead;
    }

    public void display(ListNode l_next)
    {
        ListNode p = l_next;
        while (p != null)
        {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        System.out.println("两个链表表示的数相加:");
        test t = new test();
        ListNode l1, l2;
        l1 = new ListNode(0);
        l2 = new ListNode(0);
        ListNode l1_next1 = new ListNode(2);
        l1.next = l1_next1;
        ListNode l1_next2 = new ListNode(4);
        l1.next.next = l1_next2;
        ListNode l1_next3 = new ListNode(3);
        l1.next.next.next = l1_next3;
        t.display(l1.next);
        ListNode l2_next1 = new ListNode(5);
        l2.next = l2_next1;
        ListNode l2_next2 = new ListNode(6);
        l2.next.next = l2_next2;
        ListNode l2_next3 = new ListNode(4);
        l2.next.next.next = l2_next3;
        t.display(l2.next);

        //------------------------------------------------------
        long startTime = System.nanoTime();   //获取开始时间
        //------------------------------------------------------
        ListNode l = t.addTwoNumbers(l1, l2);
        t.display(l.next);
        //------------------------------------------------------
        long endTime = System.nanoTime(); //获取结束时间
        if ((endTime - startTime) < 1000000)
        {
            double final_runtime;
            final_runtime = (endTime - startTime);
            final_runtime = final_runtime / 1000;
            System.out.println("算法运行时间： " + final_runtime + "微秒");
        }
        else if ((endTime - startTime) >= 1000000 && (endTime - startTime) < 10000000000L)
        {
            double final_runtime;
            final_runtime = (endTime - startTime) / 1000;
            final_runtime = final_runtime / 1000;
            System.out.println("算法运行时间： " + final_runtime + "毫秒");
        }
        else
        {
            double final_runtime;
            final_runtime = (endTime - startTime) / 10000;
            final_runtime = final_runtime / 100000;
            System.out.println("算法运行时间： " + final_runtime + "秒");
        }
        Runtime r = Runtime.getRuntime();
        float memory;
        memory = r.totalMemory();
        memory = memory / 1024 / 1024;
        System.out.printf("JVM总内存：%.3fMB\n", memory);
        memory = r.freeMemory();
        memory = memory / 1024 / 1024;
        System.out.printf(" 空闲内存：%.3fMB\n", memory);
        memory = r.totalMemory() - r.freeMemory();
        memory = memory / 1024 / 1024;
        System.out.printf("已使用的内存：%.4fMB\n", memory);
        //------------------------------------------------------
    }
}
