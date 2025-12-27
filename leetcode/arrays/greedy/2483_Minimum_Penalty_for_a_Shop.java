package leetcode.arrays.greedy;

/*
LeetCode 2483 - Minimum Penalty for a Shop
Difficulty: Medium
Link: https://leetcode.com/problems/minimum-penalty-for-a-shop/

Approach:
- Count the total number of 'Y' (customers who will come) as the initial penalty if the shop closes at hour 0.
- Iterate through each hour, updating the penalty:
- If the current hour is 'N', add 1 to the penalty (missed a customer who would not come).
- If the current hour is 'Y', subtract 1 from the penalty (served a customer who would come).
- Track the minimum penalty and the earliest hour it occurs.
- Return the earliest hour with the minimum penalty.

Time Complexity: O(n), where n is the length of the input string.
Space Complexity: O(1)

Tags: Greedy, String, Prefix Sum
Revisit: Yes
*/

class Solution {
    public int bestClosingTime(String customers) {
        int prefix=0, n=customers.length();
        for(char c: customers.toCharArray()){
            if(c=='Y')
                prefix++;
        }
        int penalty = prefix, curr=0, ans=0;
        for(int i=0;i<n;i++){
            if(customers.charAt(i)=='N')
                prefix++;
            else
                prefix--;
            if(prefix < penalty){
                penalty=curr+prefix;
                ans=i+1;
            }
        }
        return ans;
    }
}