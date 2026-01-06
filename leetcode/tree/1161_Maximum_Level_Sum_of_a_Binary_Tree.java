package leetcode.tree;

/*
LeetCode 1161 - Maximum Level Sum of a Binary Tree
Difficulty: Medium
Link: https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/

Approach:
- Use Breadth-First Search (BFS) to traverse the tree level by lev
el.
- For each level, calculate the sum of all node values at that level.
- Track the maximum sum and the corresponding level number.
- Return the smallest level with the maximum sum.

Time Complexity: O(n), where n is the number of nodes in the tree (each node is visited once).
Space Complexity: O(w), where w is the maximum width of the tree (maximum number of nodes at any level, for the queue).

Tags: Tree, BFS, Queue
Revisit: No
*/

class Solution {
    // Implement the required method here
    public int maxLevelSum(TreeNode root) {
        if(root==null)
            return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        long res=Integer.MIN_VALUE;
        int i=1, ans=0;
        while(!q.isEmpty()){
            int n =q.size();
            //System.out.println(n);
            long sum=0;
            for(int j=0;j<n;j++){
                TreeNode curr = q.remove();
                //System.out.print(curr.val + "->");
                sum+= curr.val;
                if(curr.left!=null)
                    q.add(curr.left);
                if(curr.right!=null)
                    q.add(curr.right);
            }
            //System.out.println();
            //System.out.println(sum);
            if(sum>res){
                res=sum;
                ans=i;
            }
            i++;
        }
        return ans;
    }
}
