
package leetcode.dp;

/*
LeetCode 1458 - Max Dot Product of Two Subsequences
Difficulty: Hard
Link: https://leetcode.com/problems/max-dot-product-of-two-subsequences/

Approach:
- Use dynamic programming with memoization to find the maximum dot product of non-empty subsequences from nums1 and nums2.
- The DP state is (i, j): the best dot product starting from nums1[i] and nums2[j].
- At each step, you can:
    1. Take nums1[i] * nums2[j] and move both pointers (i+1, j+1).
    2. Skip nums1[i] (move i+1).
    3. Skip nums2[j] (move j+1).
    4. Just take the current pair (nums1[i] * nums2[j]) as a new start.
- Use a large negative value as the base case to ensure at least one pair is chosen.

Time Complexity: O(n*m), where n = nums1.length, m = nums2.length
Space Complexity: O(n*m), for the DP table

Tags: Dynamic Programming
Revisit: No
*/

import java.util.Arrays;

class Solution {
    private int [][]dp;
    public int solve(int i, int j, int[] nums1, int[] nums2){
        if(i==nums1.length || j==nums2.length)
            return (int)-1e9;
        if(dp[i][j] != (int)-1e9)
            return dp[i][j];
        int val = nums1[i]*nums2[j];
        int take = nums1[i]*nums2[j] + solve(i+1, j+1, nums1, nums2);
        int skip1 = solve(i+1, j, nums1, nums2);
        int skip2 = solve(i, j+1, nums1, nums2);
        return dp[i][j] = Math.max(val, Math.max(take, Math.max(skip1, skip2)));
    }
    public int maxDotProduct(int[] nums1, int[] nums2) {
        dp = new int[nums1.length+1][nums2.length+1];
        for(int []arr: dp)
            Arrays.fill(arr, (int)-1e9);
        return solve(0, 0, nums1, nums2);
    }
}