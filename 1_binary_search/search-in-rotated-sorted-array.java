public class Solution {
    public int search(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int start = 0;
        int end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] < A[end]) { //说明mid到end之间有序, 关键点是确定target在不在已知有序的地方
                if (A[mid] <= target && target <= A[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            } else { //说明start 到 mid之间有序
                if (A[start] <= target && target <= A[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            }
        }
        if (A[start] == target) {
            return start;
        }
        if (A[end] == target) {
            return end;
        }
        return -1;
    }
}

/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
You are given a target value to search. If found in the array return its index, otherwise return -1.
You may assume no duplicate exists in the array.
Example:
For [4, 5, 1, 2, 3] and target=1, return 2.
For [4, 5, 1, 2, 3] and target=0, return -1.
mid左右必有一个是有序的

如果 A[start] < A[mid], 则说明mid左侧是有序的, eg 4 5 6 7 [8] 0 1 2 3
如果 A[start] > A[mid], 则说明mid右侧是有序的, eg 7 8 0 1 [2] 3 4 5 6
知道哪边是有序的, 则可以看看target在不在有序的那边
根据target在不在有序的那边, 确定后续搜索的范围(即如何更新start, end)
*/
