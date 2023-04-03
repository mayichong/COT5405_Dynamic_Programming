import java.util.Scanner;
import java.lang.Math;

public class DynamicProgramming {
    public static void main(String[] args) {

        // String argument = args[0];

        int tempInt = 1;

        switch (tempInt) {
            case 1, 2, 3, 4, 5: {
                Scanner s = new Scanner(System.in);
                int m = s.nextInt();
                int n = s.nextInt();
                int h = s.nextInt();
                int[][] p = new int[m][n];

                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        p[i][j] = s.nextInt();
                    }
                }
                s.close();
                alg2(m, n, h, p);
                break;
            }

            case 6, 7: {
                Scanner s = new Scanner(System.in);
                int m = s.nextInt();
                int n = s.nextInt();
                int h = s.nextInt();
                int k = s.nextInt();
                int[][] p = new int[m][n];

                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        p[i][j] = s.nextInt();
                    }
                }
                s.close();

                alg7(m, n, h, k, p);
                break;
            }
        }

    }

    public static void alg1(int m, int n, int h, int[][] arr) {
        // define max for matrix
        int max = 0;

        // initialize coordinates
        int coord1 = 0;
        int coord2 = 0;
        int coord3 = 0;
        int coord4 = 0;

        int maxSquare = 0;
        // Find minimum between n and m
        if (n > m) {
            maxSquare = m;
        } else {
            maxSquare = n;
        }

        // initialize size as 1, increase as we go through the loop
        for (int size = 1; size <= maxSquare; size++) {

            // make sure that the maximum loop does not exceed the maximum size
            for (int i = 0; i <= n - size; i++) {
                for (int j = 0; j <= m - size; j++) {

                    // Check if all elements of subarray meet the requirement
                    boolean isSquare = true;

                    for (int a = i; a < i + size; a++) {
                        for (int b = j; b < j + size; b++) {

                            // Check if current index is less than h
                            if (arr[a][b] < h) {

                                // Set spuare to false and break for loop
                                isSquare = false;
                                break;
                            }
                        }

                        // After for loop is done, break if isSquare is false
                        if (!isSquare) {
                            break;
                        }
                    }

                    // Update result if a larger square subarray is found
                    if (isSquare == true) {
                        if (size > max) {
                            // set size of new max
                            max = size;

                            // coordnate1,2,3,4 as top left coord and bottom right coord
                            coord1 = i;
                            coord2 = j;
                            coord3 = i + size;
                            coord4 = j + size;
                        }
                    }
                }
            }
        }

        System.out.println(coord1 + " " + coord2 + " " + coord3 + " " +
                coord4);
    }

    public static void alg2(int m, int n, int h, int[][] original) {
        // define max to be 0
        int max = 0;
        int coord1 = 0;
        int coord2 = 0;
        int coord3 = 0;
        int coord4 = 0;
        // Iterate original matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // If the current element is greater than or equal to h
                if (original[j][i] >= h) {

                    // Initialize size, right and top
                    int size = 1;
                    int left = size + j;
                    int top = size + i;

                    // We check if right and bottom have element or not
                    while (n >= size + left && m >= size + top) {
                        // Initialize a boolean statement to true
                        boolean isSquare = true;

                        // loop through elements in the matrix added size
                        for (int a = j; a < j + size; a++) {
                            for (int b = i; b < i + size; b++) {

                                // check if index element is smaller than h
                                if (original[a][b] < h) {

                                    // make isSquare false since one of the element is smaller than h in the matrix
                                    isSquare = false;
                                    break;
                                }
                            }

                            // terminate for loop early if isSquare is always false
                            if (isSquare == false) {
                                break;
                            }
                        }

                        // set max to the current size and record the latest info
                        if (isSquare == true) {
                            if (size > max) {
                                // set size of new max
                                max = size;

                                // coordnate1,2,3,4 as top left coord and bottom right coord
                                coord1 = i;
                                coord2 = j;
                                coord3 = i + size;
                                coord4 = j + size;
                            }
                        }
                        // If all indexes are greater than h, we increase size and loop again to find
                        // the maximum
                        size++;
                    }

                }
            }
        }

        System.out.println(coord1 + " " + coord2 + " " + coord3 + " " +
                coord4);
    }

    public static void alg3(int m, int n, int h, int[][] p) {
        int maxSize = 0;
        int dp[][] = new int[m][n];
        int maxRow = 0;
        int maxCol = 0;

        // Interate through the entire 2D array
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // If the current element is greater than or equal to h
                if (p[i][j] >= h) {
                    // if current element is not located at the leftmost or topmost layer
                    if (i >= 1 && j >= 1) {
                        // check the 2x2 square where dp[i][j] is the bottom right plot
                        int tempMin = Math.min(dp[i - 1][j], dp[i - 1][j - 1]);
                        dp[i][j] = Math.min(tempMin, dp[i][j - 1]) + 1;

                    } else {
                        dp[i][j] = 1;
                    }

                    // if dp[i][j] is greater than maxSize
                    if (dp[i][j] > maxSize) {
                        maxRow = i;
                        maxCol = j;
                        maxSize = dp[i][j];
                    }
                }
            }
        }
        System.out.println(
                (maxRow - maxSize + 2) + " " + (maxCol - maxSize + 2) + " " + (maxRow + 1) + " " + (maxCol + 1));
    }

    public static void alg4(int m, int n, int h, int[][] p) {

    }

    public static void alg5(int m, int n, int h, int[][] p) {
        int maxSize = 0;
        int dp[][] = new int[m][n];
        int maxRow = 0;
        int maxCol = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (p[i][j] >= h) {
                    if (i >= 1 && j >= 1) {
                        if (p[i - 1][j - 1] < h) {
                            dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                        } else {
                            int tempMin = Math.min(dp[i - 1][j], dp[i - 1][j - 1]);
                            dp[i][j] = Math.min(tempMin, dp[i][j - 1]) + 1;
                        }
                    } else {
                        dp[i][j] = 1;
                    }
                    if (dp[i][j] > maxSize) {
                        maxRow = i;
                        maxCol = j;
                        maxSize = dp[i][j];
                    }
                }
            }
        }
        System.out.println(
                (maxRow - maxSize + 2) + " " + (maxCol - maxSize + 2) + " " + (maxRow + 1) +
                        " " + (maxCol + 1));

    }

    public static void alg6(int m, int n, int h, int k, int[][] p) {
        int maxSize = 0;
        int[] result = new int[4];

        // Iterate through the entire 2d array as the top left plot
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Iterate through the entire 2d array as the bottom right plot
                for (int newI = i; newI < m; newI++) {
                    for (int newJ = j; newJ < n; newJ++) {

                        // if the coordinates form a square
                        if (newI - i == newJ - j) {

                            // count the amount of plot with weight under h
                            int count = 0;
                            for (int x = i; x <= newI; x++) {
                                for (int y = j; y <= newJ; y++) {
                                    if (p[x][y] < h) {
                                        count++;
                                    }
                                }
                            }

                            // if square is bigger and within k limit
                            if (count <= k && (newI - i + 1) * (newJ - j + 1) > maxSize) {
                                maxSize = (newI - i + 1) * (newJ - j + 1);
                                result[0] = i + 1;
                                result[1] = j + 1;
                                result[2] = newI + 1;
                                result[3] = newJ + 1;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(result[0] + " " + result[1] + " " + result[2] + " " +
                result[3]);

    }

    public static void alg7(int m, int n, int h, int k, int[][] p) {

        int[][] prefixSum = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Create the binary matrix and calculate the prefix sum simultaneously
                prefixSum[i][j] = p[i][j] >= h ? 1 : 0;
                if (i > 0) {
                    prefixSum[i][j] += prefixSum[i - 1][j];
                }
                if (j > 0) {
                    prefixSum[i][j] += prefixSum[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    prefixSum[i][j] -= prefixSum[i - 1][j - 1];
                }
            }
        }

        // Step 3: Initialize variables to keep track of the maximum square size and
        // coordinates
        int maxSquareSize = 0;
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;

        // Step 4: Iterate over all possible square sizes and squares
        for (int size = 1; size <= Math.min(m, n); size++) {
            for (int i = 0; i <= m - size; i++) {
                for (int j = 0; j <= n - size; j++) {
                    int total = prefixSum[i + size - 1][j + size - 1];
                    if (i > 0) {
                        total -= prefixSum[i - 1][j + size - 1];
                    }
                    if (j > 0) {
                        total -= prefixSum[i + size - 1][j - 1];
                    }
                    if (i > 0 && j > 0) {
                        total += prefixSum[i - 1][j - 1];
                    }

                    // Check if the number of plots that do not satisfy the tree condition is <= k
                    if ((size * size - total) <= k) {
                        if (size > maxSquareSize) {
                            maxSquareSize = size;
                            x1 = i;
                            y1 = j;
                            x2 = i + size - 1;
                            y2 = j + size - 1;
                        }
                    }
                }
            }
        }

        // Step 5: Output the bounding indices of the optimal solution region
        System.out.println((x1 + 1) + " " + (y1 + 1) + " " + (x2 + 1) + " " + (y2 + 1));
    }

}
