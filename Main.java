import java.util.Arrays;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[][] p = new int[][] {
                // {0, 1, 2, 2}, {0, 1, 2, 2}, {0, 1, 1, 1}, {0, 1, 1, 1}, {1, 0, 0, 1}
                { 1, 2, 1 }, { 2, 2, 2 }, { 1, 2, 1 }
                // {2,2,2},{2,2,2},{2,2,2}
                // {0, 0, 1, 1}, {0, 0, 1, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0,0,0,0}
                // {0,1},{0,1}
        };
        int h = 2;
        alg1BF(p, h);
        // algBF2(p, h);
        // alg1DP(p,h);
    }

    public static int[] alg1BF(int[][] arr, int h) {
        int n = arr.length;
        int m = arr[0].length;
        int maxSize = 0;
        int[] result = new int[3];

        // Brute Force
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // if the current element is bigger than h
                if (arr[i][j] >= h) {

                    // initialize size equals to 1
                    int size = 1;

                    // make boolean true
                    boolean flag = true;

                    // we check if right and bottom has element or not
                    while (i + size < n && j + size < m) {

                        // if we do, we loop through the row first
                        for (int k = j; k <= j + size; k++) {

                            // if we found the number does not meet minimum trees, we set to false and break
                            if (arr[i + size][k] == 0) {
                                flag = false;
                                break;
                            }
                        }

                        // then we loop through the column again
                        for (int k = i; k <= i + size; k++) {

                            // if we found the number does not meet minimum tree, we set to false and break
                            // again
                            if (arr[k][j + size] == 0) {
                                flag = false;
                                break;
                            }
                        }

                        // if the number meet minimum tree, we increase size and loop again
                        if (flag) {
                            size++;
                        }
                    }

                    // After the loop, we compare size with maxSize to get the result
                    if (size > maxSize) {
                        maxSize = size;
                        result[0] = i;
                        result[1] = j;
                        result[2] = size;
                    }
                }
            }
        }

        System.out.println("start index " + result[0] + " " + result[1]);
        int finish1 = result[0] + result[2] - 1;
        int finish2 = result[1] + result[2] - 1;
        System.out.println("finish index " + finish1 + " " + finish2);

        return result;
    }

    public static int[] algBF2(int[][] arr, int h) {
        int n = arr.length;
        int m = arr[0].length;
        int maxSize = 0;
        int[] result = new int[4];

        // Iterate over all possible subarrays
        for (int size = 1; size <= Math.min(n, m); size++) {
            for (int i = 0; i <= n - size; i++) {
                for (int j = 0; j <= m - size; j++) {
                    // Check if all elements of subarray are 1
                    boolean isSquare = true;
                    for (int p = i; p < i + size; p++) {
                        for (int q = j; q < j + size; q++) {
                            if (arr[p][q] < h) {
                                isSquare = false;
                                break;
                            }
                        }
                        if (!isSquare) {
                            break;
                        }
                    }

                    // Update result if a larger square subarray is found
                    if (isSquare && size > maxSize) {
                        maxSize = size;
                        result[0] = i + 1;
                        result[1] = j + 1;
                        result[2] = size + i;
                        result[3] = size + j;
                    }
                }
            }
        }

        System.out.println(result[0]);
        return result;
    }

    public static int[] alg1DP(int[][] arr, int h) {
        int n = arr.length;
        int m = arr[0].length;
        int maxSize = 0;
        int[] result = new int[3];

        // Create a 2D array to store the largest square subarray ending at each
        // position
        int[][] dp = new int[n][m];

        // Initialize the first row and column of dp
        for (int i = 0; i < n; i++) {
            dp[i][0] = arr[i][0];
            if (dp[i][0] >= h) {
                maxSize = 1;
                result[0] = i;
                result[1] = 0;
                result[2] = 1;
            }
        }
        for (int j = 0; j < m; j++) {
            dp[0][j] = arr[0][j];
            if (dp[0][j] >= h) {
                maxSize = 1;
                result[0] = 0;
                result[1] = j;
                result[2] = 1;
            }
        }

        // Compute the largest square subarray ending at each position
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (arr[i][j] >= h) {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    if (dp[i][j] > maxSize) {
                        maxSize = dp[i][j];
                        result[0] = i - maxSize + 1;
                        result[1] = j - maxSize + 1;
                        result[2] = maxSize;
                    }
                }
            }
        }

        if (result[0] == -1) {
            result[0] = 0;
        }

        if (result[1] == -1) {
            result[1] = 0;
        }
        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);

        return result;
    }

}