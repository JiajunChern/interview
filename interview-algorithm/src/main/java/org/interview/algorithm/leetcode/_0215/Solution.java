package org.interview.algorithm.leetcode._0215;

public class Solution {

    public int findKthLargest(int[] nums, int k) {
        int i = 0, j = nums.length - 1;
        quickSort(nums, k - 1, i, j);
        return nums[k - 1];
    }

    private void quickSort(int[] nums, int k, int i, int j) {

        if (i >= j) {
            return;
        }
        int m = partition(nums, k, i, j);
        if (m == k) {
            return;
        }
        quickSort(nums, k, i, m - 1);
        quickSort(nums, k, m + 1, j);
    }

    private int partition(int[] nums, int k, int low, int high) {
        int i = low, j = high + 1;
        while (true) {
            while (i != high && nums[++i] > nums[low]) ;
            while (j != low && nums[--j] < nums[low]) ;
            if (i >= j) {
                break;
            }
            swap(nums, i, j);
        }
        swap(nums, j, low);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
