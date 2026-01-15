package leetcode.arrays;

/*
LeetCode 2943 - Maximize Area of Square Hole in Grid
Difficulty: Medium
Link: https://leetcode.com/problems/maximize-area-of-square-hole-in-grid/

Approach:
- Sort the horizontal and vertical bars to find the largest group of consecutive bars removed in each direction.
- For both hBars and vBars, count the maximum number of consecutive bars.
- The largest possible square hole will have a side length of (min(max consecutive hBars, max consecutive vBars) + 1).
- Return the area as (side length)^2.

Time Complexity: O(H log H + V log V), where H and V are the lengths of hBars and vBars respectively (for sorting).
Space Complexity: O(1) (ignoring input and sort space).

Tags: Arrays, Sorting, Greedy
Revisit: No
*/

class Solution {
    // Implement the required method here
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        Arrays.sort(hBars);
        Arrays.sort(vBars);
        int consecH=1, consecV=1, c=1;
        for(int i=1;i<hBars.length;i++){
            if(hBars[i] == hBars[i-1]+1)
                c++;
            else{
                consecH = Math.max(consecH, c);
                c=1;
            }
        }
        consecH = Math.max(consecH, c);
        c=1;
        for(int i=1;i<vBars.length;i++){
            if(vBars[i] == vBars[i-1]+1)
                c++;
            else{
                consecV = Math.max(consecV, c);
                c=1;
            }
        }
        consecV = Math.max(consecV, c);
        int res=Math.min(consecH, consecV)+1;        
        return res*res;
    }
}
