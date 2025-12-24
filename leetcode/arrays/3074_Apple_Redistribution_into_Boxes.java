package leetcode.arrays;

/*
LeetCode 3074 - Apple Redistribution into Boxes
Difficulty: Medium
Link: https://leetcode.com/problems/apple-redistribution-into-boxes/

Approach:
- Calculate the total number of apples.
- Sort the capacity array in ascending order.
- Starting from the largest box, keep filling boxes with apples until all apples are distributed.
- The answer is the minimum number of boxes needed to store all apples (i.e., the smallest number of largest boxes whose total capacity is at least the total number of apples).

Time Complexity: O(n log n), where n is the number of boxes (due to sorting).
Space Complexity: O(1), ignoring the space for sorting if in-place.

Tags: Array, Greedy, Sorting
Revisit: No
*/

class Solution {
    // Implement the required method here
    public int minimumBoxes(int[] apple, int[] capacity) {
        Arrays.sort(capacity);
        int sum=Arrays.stream(apple).sum();
        int res=0, rem=0;
        for(int j=capacity.length-1;j>=0;j--){
            sum -= capacity[j];
            if(sum<=0)
                return capacity.length-j;
        }
        return -1;
    }
}
