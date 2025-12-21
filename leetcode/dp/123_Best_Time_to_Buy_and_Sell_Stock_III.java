package leetcode.dp;

/*
LeetCode 123 - Best Time to Buy and Sell Stock III
Difficulty: Hard
Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/

// Recursive Implementation

Approach:
- Use recursion to explore all possible transactions (buy/sell/skip) with at most 2 transactions allowed.
- At each index, decide to buy, sell, or skip based on the current state (buying or selling) and remaining transactions.
- The recursion continues until the end of the array or the transaction limit is reached.
- The maximum profit is the best result from all possible choices.
- This approach gives TLE for large inputs due to overlapping subproblems.

Time Complexity: O(2^n), as each day can branch into two choices (buy/sell or skip), leading to exponential growth.
Space Complexity: O(n), due to the recursion stack.

Tags: DP, Recursion, Memoization
Revisit: No
*/

class Solution {
    public int solve(int idx, boolean buy, int trx, int n, int []prices){
        //base case: no transactions left or transaction limit reached
        if(idx>=n || trx==0)
            return 0;
        
        int maxProfit=Integer.MIN_VALUE;
        if(buy){// buying
            maxProfit = Math.max(-prices[idx] + solve(idx+1, !buy, trx, n, prices), //buy
                            solve(idx+1, buy, trx, n, prices)); //skip and buy later
        }else{// selling
            maxProfit = Math.max(prices[idx] + solve(idx+1, !buy, trx-1, n, prices), //sell
                            solve(idx+1, buy, trx, n, prices)); // skip and sell later
        }
        return maxProfit;
    }
    public int maxProfit(int[] prices) {
        int n=prices.length;
        return solve(0, true, 2, n, prices);
    }
}

// Recursive + Memoization Implementation
/*
Approach:
- Use recursion with memoization (top-down DP) to avoid redundant calculations.
- Store results of subproblems in a 3D DP array: dp[index][transactions_left][buy_state].
- At each step, decide to buy, sell, or skip, and cache the result for each state.
- This reduces the time complexity by ensuring each state is computed only once.

Time Complexity: O(n * 2 * 3) = O(n), where n is the number of days, 2 is for buy/sell state, and 3 is for the max 2 transactions (0, 1, 2).
Space Complexity: O(n * 2 * 3) for the memoization table, plus O(n) recursion stack.
*/

class Solution {
    public int solve(int idx, int buy, int trx, int n, int []prices, int [][][]t){
        //base case: no transactions left or transaction limit reached
        if(idx>=n || trx==0)
            return 0;

        // Return computed result
        if(t[idx][trx][buy] != -1)
            return t[idx][trx][buy];
        
        int maxProfit=Integer.MIN_VALUE;
        if(buy==1){// buying
            maxProfit = Math.max(-prices[idx] + solve(idx+1, 0, trx, n, prices, t), //buy
                            solve(idx+1, buy, trx, n, prices, t)); //skip and buy later
        }else{// selling
            maxProfit = Math.max(prices[idx] + solve(idx+1, 1, trx-1, n, prices, t), //sell
                            solve(idx+1, buy, trx, n, prices, t)); // skip and sell later
        }
        return t[idx][trx][buy] = maxProfit;
    }
    public int maxProfit(int[] prices) {
        int n=prices.length;
        int [][][]t = new int[n+1][3][2];
        // Fill DP with -1 (uncomputed states)
        for(int [][]arr: t)
            for(int []a: arr)
                Arrays.fill(a, -1);
        return solve(0, 1, 2, n, prices, t);
    }
}

// Bottom-Up DP/ Tabulation Implementation
/*
Approach:
- Use a 3D DP array to iteratively compute the maximum profit for each state (day, transactions left, buy/sell state).
- Start from the last day and fill the DP table backwards (tabulation), considering all possible actions (buy, sell, skip) for each state.
- For each day, for each transaction count (1 to 2), and for both buy/sell states, update the DP table based on the best action.
- The answer is found at dp[0][2][1], representing the start with 2 transactions left and the ability to buy.

Time Complexity: O(n * 2 * 3) = O(n), where n is the number of days, 2 is for buy/sell state, and 3 is for the max 2 transactions (0, 1, 2).
Space Complexity: O(n * 2 * 3) for the DP table.
*/

class Solution{
    public int maxProfit(int[] prices) {
        int n=prices.length;
        int [][][]dp = new int[n+1][3][2]; //where n is the number of days, 2 is for buy/sell state, and 3 is for the max 2 transactions (0, 1, 2).

        for(int i=n-1;i>=0;i--){
            for(int trx=1;trx<3;trx++){
                for(int buy=0;buy<2;buy++){
                    if(buy==1){
                        dp[i][trx][1] = Math.max(-prices[i] + dp[i+1][trx][0], dp[i+1][trx][1]);
                    }
                    else{
                        dp[i][trx][0] = Math.max(prices[i] + dp[i+1][trx-1][1], dp[i+1][trx][0]);
                    }
                }
            }           
        }
        return dp[0][2][1];
    }
}

