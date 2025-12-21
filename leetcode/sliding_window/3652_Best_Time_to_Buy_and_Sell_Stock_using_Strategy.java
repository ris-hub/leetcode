package leetcode.sliding_window;

/*
LeetCode 3652 - Best Time to Buy and Sell Stock using Strategy
Difficulty: Medium
Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-using-strategy/

Approach:
- Calculate the original profit for each day using the given strategy.
- Use a sliding window of size k to find the maximum gain by modifying the strategy for a window of days.
- For each window, compute the difference between the modified profit and the original profit.
- Track the maximum gain and add it to the actual profit to get the answer.
- The solution uses the sliding window technique for efficient computation.

Time Complexity: O(n), where n is the number of days (length of prices array). Each day is processed once.
Space Complexity: O(n), for storing the profit array.

Tags: Array, Sliding Window, Greedy
Revisit: No
*/

class Solution {
    // Implement the required method here
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;

        long actualProfit = 0;
        long[] profit = new long[n]; // original profit of each day

        for (int idx = 0; idx < n; idx++) {
            profit[idx] = (long) strategy[idx] * prices[idx];
            actualProfit += profit[idx];
        }

        long originalWindowProfit = 0;
        long modifiedWindowProfit = 0;
        long maxGain = 0; // modifiedWindowProfit - originalWindowProfit

        int i = 0, j = 0;

        // Khandani sliding window technique
        while (j < n) {

            originalWindowProfit += profit[j];

            // Second half of the window contributes to modifiedWindowProfit
            if (j - i + 1 > k / 2) {
                modifiedWindowProfit += prices[j];
            }

            if (j - i + 1 > k) {
                originalWindowProfit -= profit[i];
                modifiedWindowProfit -= prices[i + k / 2];
                i++;
            }

            // Evaluate window of size k
            if (j - i + 1 == k) {
                maxGain = Math.max(maxGain, modifiedWindowProfit - originalWindowProfit);
            }

            j++;
        }

        return actualProfit + maxGain;
    }
}
