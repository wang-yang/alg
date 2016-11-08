// Divide Conquer 
public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists.size() == 0) {
            return null;
        }
        return mergeHelper(lists, 0, lists.size() - 1);
    }
    private ListNode mergeHelper(List<ListNode> lists, int start, int end) {
        if (start == end) {
            return lists.get(start);
        }
        int mid = start + (end - start) / 2; 
        //divide
        ListNode left = mergeHelper(lists, start, mid); //分治到最底端只剩一个元素, 然后开始向上合并
        ListNode right = mergeHelper(lists, mid + 1, end);
        //conquer
        return mergeTwoLists(left, right);
    }
    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }
        if (list1 != null) {
            tail.next = list1;
        } else {
            tail.next = list2;
        }
        return dummy.next;
    }
}

// Merge lists two by two
public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {  
        if (lists == null || lists.size() == 0) {
            return null;
        }
        while (lists.size() > 1) { //每轮迭两两合并array相邻的list, 放到一个新的array中, 然后最后用新array替换老的array
            List<ListNode> new_lists = new ArrayList<ListNode>();
            for (int i = 0; i + 1 < lists.size(); i += 2) {
                ListNode merged_list = merge(lists.get(i), lists.get(i + 1));
                new_lists.add(merged_list);
            }
            if (lists.size() % 2 == 1) { //注意array长度是奇数的情况
                new_lists.add(lists.get(lists.size() - 1));
            }
            lists = new_lists;
        }
        return lists.get(0);
    }
    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }
        if (list1 != null) {
            tail.next = list1;
        } else {
            tail.next = list2;
        }
        return dummy.next;
    }
}

// Priority Queue (Heap) 
public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {  
        if (lists == null || lists.size() == 0) {
            return null;
        }
        Queue<ListNode> heap = new PriorityQueue<ListNode>(lists.size(), ListNodeComparator); //PQ用法, 初始化指定大小, 以及元素的comparator
        for (int i = 0; i < lists.size(); i++) { //先把k路链表的头放入min-heap中
            if (lists.get(i) != null) {
                heap.add(lists.get(i));
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!heap.isEmpty()) {
            ListNode head = heap.poll(); //PQ出堆, 然后放入下一个元素
            tail.next = head; //把出堆元素连接到结果list中
            tail = tail.next;
            if (head.next != null) {//如果还有元素没有遍历, 继续放入heap中
                heap.add(head.next);
            }
        }
        return dummy.next;
    }
    private Comparator<ListNode> ListNodeComparator = new Comparator<ListNode>() { //Min heap
        public int compare(ListNode left, ListNode right) {
            return left.val - right.val;
        }
    };
}


Merge k sorted linked lists and return it as one sorted list.
Analyze and describe its complexity.
Example
Given lists:
[
  2->4->null,
  null,
  -1->null
],
return -1->2->4->null.
