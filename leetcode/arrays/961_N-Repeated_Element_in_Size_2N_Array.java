package leetcode.arrays;

/*
LeetCode 961 - N-Repeated Element in Size 2N Array
Difficulty: Easy
Link: https://leetcode.com/problems/n-repeated-element-in-size-2n-array/

Approach:
- Use a frequency array to count occurrences of each element in the input array.
- The element that appears exactly N times (where N = nums.length / 2) is the answer.
- Iterate through the frequency array to find the element with frequency N.

Time Complexity: O(n), where n is the length of nums (one pass to count, one pass to find the answer).
Space Complexity: O(m), where m is the range of numbers in nums (for the frequency array).

Tags: Array, Hash Table
Revisit: No
*/

class Solution {
    // Implement the required method here
    public int repeatedNTimes(int[] nums) {
        int n=nums.length/2;
        int max=Arrays.stream(nums).max().getAsInt();
        int []freq = new int[max+1];
        for(int ele: nums)
            freq[ele]++;

        //System.out.println(Arrays.toString(freq));
        int i=0;
        for(int f: freq){
            if(f==n)
                return i;
            i++;
        }
        return -1;
    }
}
