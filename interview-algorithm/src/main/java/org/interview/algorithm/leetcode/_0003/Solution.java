package org.interview.algorithm.leetcode._0003;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left = -1, right = 0;
        int length = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (right < s.length()) {
            char c = s.charAt(right);
            left = Math.max(left, map.getOrDefault(c, -1));
            map.put(c, right);
            length = Math.max(length, right - left);
            right++;
        }
        return length;
    }

    public static void main(String[] args) {
        String s = "pwwkew";
        Solution solution = new Solution();
        int length = solution.lengthOfLongestSubstring(s);
        System.out.println(length);
    }
}
