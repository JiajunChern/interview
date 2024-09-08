package org.interview.algorithm.leetcode._0015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        // 没有小数无法成立
        if (nums.length == 0 || nums[0] > 0) {
            return result;
        }
        for (int i = 0; i < nums.length - 2; ) {
            int left = i + 1;
            int right = nums.length - 1;
            int target = -nums[i];
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[left]);
                    list.add(nums[right]);
                    list.add(nums[i]);
                    result.add(list);
                    while (left < right && nums[left + 1] == nums[left]) {
                        left++;
                    }
                    left++;
                } else if (nums[left] + nums[right] < target) {
                    while (left < right && nums[left + 1] == nums[left]) {
                        left++;
                    }
                    left++;
                } else {
                    while (left < right && nums[right - 1] == nums[right]) {
                        right--;
                    }
                    right--;
                }
            }
            while (i < nums.length - 2 && nums[i + 1] == nums[i]) {
                i++;
            }
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        solution.threeSum(nums);
    }

}
