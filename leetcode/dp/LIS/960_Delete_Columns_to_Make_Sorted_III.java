package leetcode.dp.LIS;

/*
LeetCode 960 - Delete Columns to Make Sorted III
Difficulty: Hard
Link: https://leetcode.com/problems/delete-columns-to-make-sorted-iii/

Approach:
- Treat each column as an element and find the Longest Increasing Subsequence (LIS) of columns such that for every pair of columns in the subsequence, the characters in all rows are non-decreasing from left to right.
- For each column i, check all previous columns j < i. If for all rows, strs[k][j] <= strs[k][i], then column i can follow column j in the subsequence.
- Use dynamic programming (dp[i]) to store the length of the LIS ending at column i.
- The answer is the total number of columns minus the length of the LIS.

Time Complexity: O(n * m^2), where n = number of rows, m = number of columns
Space Complexity: O(m)

Tags: Dynamic Programming, LIS, String
Revisit: No
*/

class Solution {
    public int minDeletionSize(String[] strs) {
        int rows = strs.length;
        int cols = strs[0].length();

        int[] dp = new int[cols];
        // dp[i] = LIS ending at column i

        int LIS = 1;

        for (int i = 0; i < cols; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {

                boolean valid = true;
                for (int k = 0; k < rows; k++) {
                    if (strs[k].charAt(j) > strs[k].charAt(i)) {
                        valid = false;
                        break;
                    }
                }

                if (valid) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            LIS = Math.max(LIS, dp[i]);
        }

        return cols - LIS;
    }
}