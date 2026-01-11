package leetcode.stack;

/*
LeetCode 84 - Largest Rectangle in Histogram
Difficulty: Hard
Link: https://leetcode.com/problems/largest-rectangle-in-histogram/

Approach:
- Use two monotonic stacks to find the index of the previous and next smaller bar for each bar in the histogram.
- For each bar, calculate the area as height * (right boundary - left boundary - 1).
- Track the maximum area found.

Time Complexity: O(n)
Space Complexity: O(n)

Tags: Stack, Monotonic Stack, Array
Revisit: No
*/

class Solution {
    // Implement the required method here
    public int largestRectangleArea(int[] heights) {
        int n=heights.length, res=0;
        Stack<Integer> st = new Stack<>();
        int []left = new int[n];
        for(int i=0;i<n;i++){
            while(!st.isEmpty() && heights[st.peek()]>=heights[i]){
                st.pop();
            }
            if(!st.isEmpty())
                left[i]=st.peek();
            else
                left[i]=-1;
            st.push(i);
        } 
        st.clear();
        int []right = new int[n];
        for(int i=n-1;i>=0;i--){
            while(!st.isEmpty() && heights[st.peek()]>=heights[i]){
                st.pop();
            }
            if(!st.isEmpty())
                right[i]=st.peek();
            else
                right[i]=n;
            st.push(i);
        } 
        for(int i=0;i<n;i++){
            res = Math.max(res, heights[i] * (right[i]-left[i]-1));
        }
        return res;
    }
}
