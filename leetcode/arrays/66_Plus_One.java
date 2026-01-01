package leetcode.arrays;

/*
LeetCode 66 - Plus One
Difficulty: Easy
Link: https://leetcode.com/problems/plus-one/

Approach:
- Start from the last digit and move backwards.
- If the digit is less than 9, increment it and return the array.
- If the digit is 9, set it to 0 and continue to the next digit.
- If all digits are 9, create a new array with an extra digit set to 1 at the front.

Time Complexity: O(n), where n is the number of digits.
Space Complexity: O(n), in the worst case when all digits are 9 and a new array is needed.

Tags: Array, Math
Revisit: No
*/

class Solution {
    // Implement the required method here
    public int[] plusOne(int[] digits) {
        int n=digits.length, i=n-1;
        while(i>=0){
            if(digits[i]!=9){
                digits[i] += 1;
                return digits;
            }
            digits[i]=0;
            i--;
        }
        int []ans = new int[n+1];            
        ans[0]=1;        
        return ans;
    }
}

