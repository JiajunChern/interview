package org.interview.algorithm.leetcode._0200;

import java.util.Deque;
import java.util.LinkedList;

class Solution {
    int[][] offset = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int mx, my;

    public void bfs(char[][] grid, int i, int j) {
        Deque<int[]> deque = new LinkedList<>();
        int[] xy = {i, j};
        deque.add(xy);
        while (!deque.isEmpty()) {
            int num = deque.size();
            for (int k = 0; k < num; k++) {
                xy = deque.removeFirst();
                for (int[] off : offset) {
                    int x = xy[0] + off[0];
                    int y = xy[1] + off[1];
                    if (x < 0 || x >= mx || y < 0 || y >= my) {
                        continue;
                    }
                    if (grid[x][y] == '1') {
                        int[] nxy = {x, y};
                        deque.add(nxy);
                        grid[x][y] = '0';
                    }
                }
            }
        }
    }


    public int numIslands(char[][] grid) {
        int ret = 0;
        mx = grid.length;
        my = grid[0].length;
        for (int i = 0; i < mx; i++) {
            for (int j = 0; j < my; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    bfs(grid, i, j);
                    ret++;
                }

            }
        }
        return ret;
    }


    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        Solution solution = new Solution();
        solution.numIslands(grid);
    }

}
