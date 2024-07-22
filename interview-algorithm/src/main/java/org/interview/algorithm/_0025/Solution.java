package org.interview.algorithm._0025;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode b = head;
        for (int i = 0; i < k - 1; i++) {
            // 不足 K 个不反转
            if (b.next == null) {
                return head;
            }
            b = b.next;
        }
        ListNode next = b.next;
        b.next = null;
        ListNode newHead = reverse(head);
        head.next = reverseKGroup(next, k);
        return newHead;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = null;
        ListNode newHead = reverse(next);
        next.next = head;
        return newHead;
    }
}
