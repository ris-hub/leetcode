package leetcode.greedy;

import java.util.Arrays;

/*
LeetCode 3075 - Maximize Happiness of Selected Children
Difficulty: Medium
Link: https://leetcode.com/problems/maximize-happiness-of-selected-children/

Approach:
- Sort the happiness array in ascending order.
- Select the k largest values (from the end of the sorted array).
- For each selected child, subtract the number of children already selected (i) from their happiness value, but not below 0.
- Sum up these values for the answer.

Time Complexity: O(n log n), where n is the length of happiness (for sorting).
Space Complexity: O(1) (in-place sort, ignoring input size).

Tags: Greedy, Sorting, Array
Revisit: No
*/

class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        int i=0, n=happiness.length;
        long res=0;
        while(k-->0){
            int temp = Math.max(0, happiness[n-i-1]-i);
            res+=temp;
            i++;
        }
        return res;
    }
}

