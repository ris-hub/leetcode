package leetcode.greedy;

/*
LeetCode 121 - Best Time to Buy and Sell Stock
Difficulty: Easy
Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

Approach:
- Iterate through the price array while keeping track of the minimum price seen so far.
- For each price, calculate the potential profit if sold at that price (current price - min price so far).
- Update the maximum profit if the current profit is higher.
- Update the minimum price if the current price is lower than the min price so far.
- This greedy approach ensures we always buy at the lowest price before the current day and sell at the current day for the highest profit.

Time Complexity: O(n), where n is the number of days (length of prices array). We traverse the array once.
Space Complexity: O(1), as we use only a constant amount of extra space.

Tags: Array, Greedy
Revisit: Yes
*/

class Solution {
    // Implement the required method here
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int res=0, min=prices[0];
        for(int i=1;i<n;i++){
            res=Math.max(res, prices[i]-min);
            min=Math.min(min, prices[i]);
        }
        return res;
    }
}
