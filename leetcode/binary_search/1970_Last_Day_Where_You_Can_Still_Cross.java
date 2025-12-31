package leetcode.binary_search;

/*
LeetCode 1970 - Last Day Where You Can Still Cross
Difficulty: Hard
Link: https://leetcode.com/problems/last-day-where-you-can-still-cross/

Approach:
- Use binary search to find the latest day you can cross from the top to the bottom of the grid.
- For each day (mid in binary search), mark the flooded cells and use DFS to check if there is a path from any cell in the top row to the bottom row.
- If a path exists, try a later day; otherwise, try an earlier day.

Time Complexity: O((row * col) * log(days)), where days = cells.length. For each binary search step, DFS may visit all cells.
Space Complexity: O(row * col) for the grid and visited arrays.

Tags: Binary Search, DFS, Matrix
Revisit: No
*/

class Solution {
    // Implement the required method here
    public boolean dfs(int i, int j, int [][]grid, boolean [][]visited){
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j]==1 || visited[i][j]==true)
            return false;

        if(i==grid.length-1)
            return true;
        
        visited[i][j]=true;
        boolean up=false, down=false, left=false, right=false;
        if(i>0)
            up = dfs(i-1, j, grid, visited);
        if(i<grid.length-1)
            down = dfs(i+1, j, grid, visited);
        if(j<grid[0].length-1)
            right = dfs(i, j+1, grid, visited);
        if(j>0)
            left = dfs(i, j-1, grid, visited);
        
        return up || left || right || down;
    }
    public int latestDayToCross(int row, int col, int[][] cells) {
        int res=0, l=0, h=cells.length-1;
        while(l<=h){
            int mid=l+(h-l)/2;
            int [][]grid = new int[row][col];
            boolean [][]visited = new boolean[row][col];
            for(int x=0;x<=mid;x++)
                grid[cells[x][0]-1][cells[x][1]-1]=1;
            boolean canCross=false;
            for(int k=0;k<col;k++){
                if(dfs(0, k, grid, visited)){
                    canCross=true;
                    break;
                }                    
            }
            if(canCross){
                res=Math.max(res, mid+1);
                l=mid+1;                    
            }else
                h=mid-1;            
        }
        return res;
    }
}
