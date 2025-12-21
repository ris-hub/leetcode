package leetcode.greedy;

/*
LeetCode 122 - Best Time to Buy and Sell Stock II
Difficulty: Easy
Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/



Approach:
- Iterate through the price array and sum up all increases where prices[i+1] > prices[i].
- This simulates buying at every local minimum and selling at every local maximum, which is optimal when unlimited transactions are allowed.
- The greedy approach ensures we capture all profitable opportunities.

Time Complexity: O(n), where n is the number of days (length of prices array). We traverse the array once.
Space Complexity: O(1), as we use only a constant amount of extra space.

Tags:
Revisit: No
*/

class Solution {
    // Implement the required method here
    public int maxProfit(int[] prices) {
        int n=prices.length;
        int res=0;
        for(int i=0;i<n-1;i++){
            if(prices[i+1]>prices[i]){
                res += prices[i+1]-prices[i];
            }
        }
        return res;
    }
}