package leetcode.dp;

/*
LeetCode 712 - Minimum ASCII Delete Sum for Two Strings
Difficulty: Medium
Link: https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/


Approach:
- Use recursion with memoization (top-down DP) to find the minimum ASCII delete sum to make two strings equal.
- Define a recursive function solve(i, j) that returns the minimum cost to make s1[i:] and s2[j:] equal.
- If characters match, move both pointers forward.
- If not, try deleting from s1 or s2 and take the minimum cost, adding the ASCII value of the deleted character.
- Use a DP table to cache results and avoid recomputation.


Time Complexity: O(m * n), where m and n are the lengths of s1 and s2.
Space Complexity: O(m * n), for the DP table.

Tags: Dynamic Programming, String, Memoization
Revisit: No
*/

class Solution {
    // Implement the required method here
    public int solve(int i, int j, String s1, String s2, int [][]dp){
        if(i==s1.length() && j==s2.length())
            return 0;

        if(dp[i][j] != -1)
            return dp[i][j];
        
        if(i>=s1.length())
            return dp[i][j] = (int)s2.charAt(j) + solve(i, j+1, s1, s2, dp);
        
        if(j>=s2.length())
            return dp[i][j] = (int)s1.charAt(i) + solve(i+1, j, s1, s2, dp);
        
        if(s1.charAt(i) == s2.charAt(j))
            return dp[i][j] = solve(i+1, j+1, s1, s2, dp);
        
        int delete_s1 = (int)s1.charAt(i) + solve(i+1, j, s1, s2, dp);
        int delete_s2 = (int)s2.charAt(j) + solve(i, j+1, s1, s2, dp);
        return dp[i][j] = Math.min(delete_s1, delete_s2);
    }
    public int minimumDeleteSum(String s1, String s2) {
        int [][]dp = new int[s1.length()+1][s2.length()+1];
        for(int []a: dp)
            Arrays.fill(a, -1);
        return solve(0, 0, s1, s2, dp);
    }
}