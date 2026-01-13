package leetcode.binary_search;

/*
LeetCode 3453 - Separate Squares I
Difficulty: Hard
Link: https://leetcode.com/problems/separate-squares-i/

Approach:
- Use binary search to find the horizontal line (y-coordinate) that divides the total area of squares into two equal halves.
- For each candidate y (midY), calculate the area of squares below this line.
- If the area below is more than half, move the search down; otherwise, move up.
- Continue until the difference between high and low is less than a small epsilon (1e-5).

Time Complexity: O(N * log(L)), where N is the number of squares and L is the range of y-coordinates.
Space Complexity: O(1)

Tags: binary search, geometry, area calculation
Revisit: No
*/

class Solution {
    // Implement the required method here
    public boolean check(int[][] squares, double midY, double total){
        double bottomArea = 0.0;
        for (int[] square : squares) {
            double y = square[1];
            double l = square[2];

            double bottomY = y;
            double topY = y + l;

            if (midY >= topY) {
                // full square below
                bottomArea += l * l;
            } else if (midY > bottomY) {
                // partial square below
                bottomArea += (midY - bottomY) * l;
            }
        }
        return bottomArea >= total / 2.0; //Is bottom area more than above ?
    }
    public double separateSquares(int[][] squares) {
        double low = Double.MAX_VALUE;
        double high = -Double.MAX_VALUE;
        double total = 0.0;

        for (int[] square : squares) {
            double y = square[1];
            double l = square[2];

            total += l * l;
            low = Math.min(low, y);
            high = Math.max(high, y + l);
        }

        double resultY = 0.0;

        while (high - low > 1e-5) {
            double midY = low + (high - low) / 2.0;
            resultY = midY;

            if (check(squares, midY, total)) {
                // bottom area is more than half, move down
                high = midY;
            } else {
                low = midY;
            }
        }
        return resultY;
    }
}
