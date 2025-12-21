package leetcode.strings;

/*
LeetCode 955 - Delete Columns to Make Sorted II
Difficulty: Medium
Link: https://leetcode.com/problems/delete-columns-to-make-sorted-ii/

Approach:
- Iterate through each column of the string array.
- For each column, check if the current column breaks the lexicographical order for any pair of adjacent rows that are not already sorted.
- If so, increment the deletion count and skip updating the sorted status for this column.
- Otherwise, update the alreadySorted array to mark pairs that are now sorted due to this column.
- Continue until all columns are processed and return the total number of deletions needed.

Time Complexity: O(n*m), where n is the number of rows and m is the number of columns.
Space Complexity: O(n), for the alreadySorted array.

Tags: Array, Greedy, String
Revisit: No
*/

class Solution {
    // Implement the required method here
    public int minDeletionSize(String[] strs) {
        int rows = strs.length;          // number of rows
        int cols = strs[0].length();     // number of columns

        int deletion = 0;
        boolean[] alreadySorted = new boolean[rows];

        for (int col = 0; col < cols; col++) {
            boolean deleted = false;

            // check if this column breaks lexicographical order
            for (int row = 0; row < rows - 1; row++) {
                if (!alreadySorted[row] &&
                    strs[row].charAt(col) > strs[row + 1].charAt(col)) {
                    deletion++;
                    deleted = true;
                    break;
                }
            }

            if (deleted) {
                continue;
            }

            // update alreadySorted status
            for (int i = 0; i < rows - 1; i++) {
                alreadySorted[i] = alreadySorted[i] ||
                        (strs[i].charAt(col) < strs[i + 1].charAt(col));
            }
        }

        return deletion;
    }
}
