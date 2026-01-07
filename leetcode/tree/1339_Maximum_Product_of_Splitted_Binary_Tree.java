package leetcode.tree;

/*
LeetCode 1339 - Maximum Product of Splitted Binary Tree
Difficulty: Medium
Link: https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/

Approach:
- First, calculate the total sum of all nodes in the tree using a post-order traversal (getTotalSum).
- Then, traverse the tree again (solve) to compute the sum of every possible subtree. For each subtree, calculate the product of its sum and the sum of the rest of the tree (totalSum - subTreeSum).
- Track the maximum product found during traversal.
- Return the maximum product modulo 10^9 + 7.

Time Complexity: O(N) — Each node is visited twice (once for total sum, once for product calculation).
Space Complexity: O(H) — Where H is the height of the tree (due to recursion stack).

Tags:
Tree, DFS, Recursion, Binary Tree
Revisit: No
*/

class Solution {
    // Implement the required method here
    private long totalSum = 0;
    private long maxProduct = 0;
    private static final int MOD = 1_000_000_007;

    private long getTotalSum(TreeNode root) {
        if (root == null) 
            return 0;
        return root.val + getTotalSum(root.left) + getTotalSum(root.right);
    }

    private long solve(TreeNode root) {
        if (root == null) 
            return 0;

        long leftSum  = solve(root.left);
        long rightSum = solve(root.right);
        long subTreeSum = root.val + leftSum + rightSum;

        long product = subTreeSum * (totalSum - subTreeSum);
        maxProduct = Math.max(maxProduct, product);

        return subTreeSum;
    }

    public int maxProduct(TreeNode root) {
        totalSum = getTotalSum(root); 
        solve(root);                    
        return (int)(maxProduct % MOD);
    }
}
