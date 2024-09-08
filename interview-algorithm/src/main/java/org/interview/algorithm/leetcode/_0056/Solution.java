package org.interview.algorithm.leetcode._0056;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0 || intervals.length == 1) {
            return intervals;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> ans = new ArrayList<>();
        ans.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int j = ans.size() - 1;
            if (ans.get(j)[1] <= intervals[i][0]) {
                // 合并吃掉
                ans.get(j)[1] = Math.max(ans.get(j)[1], intervals[i][1]);
            } else {
                // 新的区间添加
                ans.add(intervals[i]);
            }
        }
        return ans.toArray(new int[ans.size()][2]);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        solution.merge(intervals);
    }
}
