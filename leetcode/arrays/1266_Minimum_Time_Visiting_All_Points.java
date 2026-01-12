
package leetcode.arrays;

/*
LeetCode 1266 - Minimum Time Visiting All Points
Difficulty: Easy
Link: https://leetcode.com/problems/minimum-time-visiting-all-points/

Approach:
- Iterate through each pair of consecutive points.
- For each pair, the minimum time to move from one point to the next is the maximum of the absolute differences in x and y coordinates (since you can move diagonally, horizontally, or vertically in one second).
- Sum up these minimum times for all pairs.

Time Complexity: O(n), where n is the number of points.
Space Complexity: O(1)

Tags: Array, Simulation, Math
Revisit: No
*/

class Solution {
    // Implement the required method here
    public int minTimeToVisitAllPoints(int[][] points) {
        int x1=points[0][0], y1=points[0][1];
        int res=0;
        for(int []p: points){
            int x2 = p[0];
            int y2 = p[1];

            if(x1==y1 && x2==y2)
                continue;
            
            res += Math.max(Math.abs(x2-x1), Math.abs(y2-y1));
            x1=x2;
            y1=y2;
        }
        return res;
    }
}
