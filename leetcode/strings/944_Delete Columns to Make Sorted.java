package leetcode.strings;

/*
LeetCode 944 - Delete Columns to Make Sorted
Difficulty: Easy
Link: https://leetcode.com/problems/delete-columns-to-make-sorted/  

Approach:
- Iterate through each column of the string array.
- For each column, check if the characters from top to bottom are sorted (non-decreasing).
- If any character in a column is less than the character above it, mark the column for deletion.
- Count and return the number of such columns.

Time Complexity: O(n*m)
Space Complexity: O(1)

Tags:
Revisit: No
*/

class Solution {
    // Implement the required method here
        public int minDeletionSize(String[] strs) {
        int n=strs[0].length(), m=strs.length, res=0;
        for(int i=0;i<n;i++){
            for(int j=1;j<m;j++){
                if(strs[j].charAt(i)<strs[j-1].charAt(i)){
                    res++;
                    break;
                }
            }
        }
        return res;
    }
}
