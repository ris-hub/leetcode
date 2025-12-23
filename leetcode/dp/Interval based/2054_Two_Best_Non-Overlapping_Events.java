package leetcode.dp.Interval based;

/*
LeetCode 2054 - Two Best Non-Overlapping Events
Difficulty: Medium
Link: https://leetcode.com/problems/two-best-non-overlapping-events/

Approach:
- Use dynamic programming with memoization to maximize the sum of values from at most two non-overlapping events.
- Sort events by start time.
- For each event, either take it (and use binary search to find the next non-overlapping event) or skip it.
- DP state: current event index and count of events taken (up to 2).
- Binary search efficiently finds the next event whose start time is after the current event's end time.

Time Complexity: O(n log n)
Space Complexity: O(n)

Tags: Dynamic Programming, Binary Search, Intervals
Revisit: No
*/

class Solution {
    private int n;
    private int[][] dp = new int[100001][3];
    
    // Binary search for the next event start time greater than the current event's end time
    private int binarySearch(int[][] events, int endTime) {
        int left = 0, right = n - 1, result = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (events[mid][0] > endTime) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    private int solve(int[][] events, int i, int count) {
        if (count == 2 || i >= n) {
            return 0;
        }

        if (dp[i][count] != -1) {
            return dp[i][count];
        }

        int nextValidEventIndex = binarySearch(events, events[i][1]);
        int take = events[i][2] + solve(events, nextValidEventIndex, count + 1);
        int notTake = solve(events, i + 1, count);

        dp[i][count] = Math.max(take, notTake);
        return dp[i][count];
    }

    public int maxTwoEvents(int[][] events) {
        n = events.length;
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0])); // Sort events by start time
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return solve(events, 0, 0);
    }
}