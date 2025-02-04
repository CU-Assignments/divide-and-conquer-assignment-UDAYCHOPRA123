class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        return divideAndConquer(matrix, target, 0, 0, matrix.length - 1, matrix[0].length - 1);
    }

    private boolean divideAndConquer(int[][] matrix, int target, int rowStart, int colStart, int rowEnd, int colEnd) {
        
        if (rowStart > rowEnd || colStart > colEnd) {
            return false;
        }

        int midRow = rowStart + (rowEnd - rowStart) / 2;
        int midCol = colStart + (colEnd - colStart) / 2;
        int midValue = matrix[midRow][midCol];

        if (midValue == target) {
            return true;
        }

        
        if (target < midValue) {
            return divideAndConquer(matrix, target, rowStart, colStart, midRow - 1, colEnd) || 
                   divideAndConquer(matrix, target, rowStart, colStart, rowEnd, midCol - 1);  
        } 
        else {
            return divideAndConquer(matrix, target, midRow + 1, colStart, rowEnd, colEnd) ||
                   divideAndConquer(matrix, target, rowStart, midCol + 1, midRow, colEnd);  
        }
    }
}
