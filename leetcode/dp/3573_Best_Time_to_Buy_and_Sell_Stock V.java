package leetcode.dp;

/*
LeetCode 3573 - Best Time to Buy and Sell Stock V
Difficulty: Hard
Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-v/

// Recursive Implementation
Approach:
- Use recursion to explore all possible actions at each day: buy, sell, short-sell, or skip.
- The state variable tracks the current action: 0 (ready for new transaction), 1 (must sell), 2 (must buy after short-sell).
- For each day, either take an action (buy/sell/short-sell) or skip to the next day.
- The recursion continues until all days are processed or the transaction limit k is reached.
- The maximum profit is the best result from all possible choices.

Time Complexity: O(3^n), where n is the number of days, as each day can branch into up to 3 choices (buy, sell, skip).
Space Complexity: O(n), due to the recursion stack.

Tags: DP, Recursion
Revisit: No
*/

class Solution {
    // Recursive function to calculate max profit
    // idx: current day, state: 0 (ready), 1 (must sell), 2 (must buy after short-sell), k: transactions left
    public long solve(int idx, int state, int k, int n, int []prices){
        // Base case: end of days
        if(idx>=n){
            // If state==0, all transactions are complete; otherwise, incomplete transaction is invalid
            return state==0?0:Integer.MIN_VALUE;
        }

        // Option 1: skip current day
        long dontTake = solve(idx+1, state, k, n, prices);
        long take=0;
        if(k>0){
            if(state==1){ // Must sell
                take = prices[idx]+solve(idx+1, 0, k-1, n, prices); // Sell and decrease k
            }
            else if(state==2){ // Must buy after short-sell
                take = -prices[idx]+solve(idx+1, 0, k-1, n, prices); // Buy and decrease k
            }
            else{
                // Option to buy (then must sell) or short-sell (then must buy)
                take = Math.max(
                    -prices[idx]+solve(idx+1, 1, k, n, prices), // Buy, next state must sell
                    prices[idx]+solve(idx+1, 2, k, n, prices)   // Short-sell, next state must buy
                );
            }
        }
        return Math.max(take, dontTake);
    }
    public long maximumProfit(int[] prices, int k) {
        int n=prices.length;
        return solve(0, 0, k, n, prices);
    }
}

// Recursive + Memoization Implementation
/*
Approach:
- Use recursion with memoization (top-down DP) to avoid redundant calculations.
- Store results of subproblems in a 3D DP array: dp[index][transactions_left][state].
- At each step, decide to buy, sell, short-sell, or skip, and cache the result for each state.
- This reduces the time complexity by ensuring each state is computed only once.

Time Complexity: O(n * k * 3), where n is the number of days, k is the transaction limit, and 3 is for the state (0: ready, 1: must sell, 2: must buy after short-sell).
Space Complexity: O(n * k * 3) for the memoization table, plus O(n) recursion stack.
*/

class Solution {
    // Implement the required method here
    public long solve(int idx, int state, int k, int n, int []prices, long [][][]t){
        if(idx>=n){
            //if state==0 that means either we've  completed past transactions / ready to start new transactions
            //but if state!=0 and idx==n then this transaction isn't possible
            return state==0?0:Integer.MIN_VALUE;
        }

        if(t[idx][k][state] != -1)
            return t[idx][k][state];
        
        long dontTake = solve(idx+1, state, k, n, prices, t);
        long take=0;
        if(k>0){
            if(state==1){//sell
                take = prices[idx]+solve(idx+1, 0, k-1, n, prices, t);
            }
            else if(state==2){//short sell- yo've selled now yo've to (buy)
                take = -prices[idx]+solve(idx+1, 0, k-1, n, prices, t);
            }
            else{
                // buy for state - 1
                //sell for state - 2
                // fresh transaction
                take = Math.max(-prices[idx]+solve(idx+1, 1, k, n, prices, t), //since you've bought now then mark for sell
                                prices[idx]+solve(idx+1, 2, k, n, prices, t));  //since you've sold now then mark for buy
            }
        }
        return t[idx][k][state] = Math.max(take, dontTake);
    }
    public long maximumProfit(int[] prices, int k) {
        int n=prices.length;
        long [][][]t = new long[n+1][k+1][3];
        for(long [][]arr: t)
            for(long []a: arr)
                Arrays.fill(a, -1);
        return solve(0, 0, k, n, prices, t);
    }
}


//Bottom-Up DP Implementation

