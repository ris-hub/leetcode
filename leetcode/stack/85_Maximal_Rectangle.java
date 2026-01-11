package leetcode.stack;

/*
LeetCode 85 - Maximal Rectangle
Difficulty: Hard
Link: https://leetcode.com/problems/maximal-rectangle/

Approach:
- Treat each row of the matrix as the base of a histogram.
- For each row, build a histogram of heights and use the Largest Rectangle in Histogram algorithm (monotonic stack) to find the maximal rectangle area.
- Update the maximum area found for each row.

Time Complexity: O(m * n)
Space Complexity: O(n)

Tags: Stack, Monotonic Stack, Matrix, Histogram
Revisit: No
*/

class Solution {
    // Implement the required method here
    public int[] getNSR(int[] height){
        Stack<Integer> stack = new Stack<>();
        int n=height.length;
        int[] NSR = new int[n];
        for(int i=n-1;i>=0;i--){
            if(stack.isEmpty())
                NSR[i]=n;
            else{
                while(!stack.isEmpty() && height[stack.peek()]>=height[i]){
                    stack.pop();
                }
                if(stack.isEmpty())
                    NSR[i]=n;
                else{
                    NSR[i]=stack.peek();
                }                    
            }
            stack.push(i);
        }
        return NSR;
    }
    public int[] getNSL(int[] height){
        Stack<Integer> stack = new Stack<>();
        int n=height.length;
        int[] NSL = new int[n];
        for(int i=0;i<n;i++){
            if(stack.isEmpty())
                NSL[i]=-1;
            else{
                while(!stack.isEmpty() && height[stack.peek()]>=height[i]){
                    stack.pop();
                }
                if(stack.isEmpty())
                    NSL[i]=-1;
                else{
                    NSL[i]=stack.peek();
                }                    
            }
            stack.push(i);
        }
        return NSL;
    }
    public int findMaxArea(int[] height){
        //width=NSR-NSL-1
        //height=height[i]
        int[] NSR = getNSR(height);
        int[] NSL = getNSL(height);
        int[] width=new int[height.length];
        for(int i=0;i<height.length;i++){
            width[i]=NSR[i]-NSL[i]-1;
        }
        int max=0;
        for(int i=0;i<height.length;i++){
            max = Math.max(max, height[i]*width[i]);
        }
        return max;
    }
    public int maximalRectangle(char[][] matrix) {
        int m=matrix.length;
        int n=matrix[0].length;
        int[] height = new int[n];
        for(int i=0;i<n;i++){
            height[i] = matrix[0][i]=='1'?1:0;
        }
        int maxArea = findMaxArea(height);
        for(int row=1;row<m;row++){
            for(int col=0;col<n;col++){
                if(matrix[row][col]=='0')
                    height[col]=0;
                else
                    height[col] += 1;
            }
            maxArea = Math.max(maxArea, findMaxArea(height));
        }
        return maxArea;
    }
}
