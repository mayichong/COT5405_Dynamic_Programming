import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math;

public class DynamicProgramming {
    public static void main(String[] args) {

        // String argument = args[0];

        int tempInt = 2;

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
                alg5a(m, n, h, p);
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

    public static void alg1(int m, int n, int h, int[][] p) {
        int maxSize = 0;
        int[] result = new int[4];

        // Iterate through the entire 2d array as top left plot
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Iterate through the entire 2d array as bottom right plot
                for (int newI = i; newI < m; newI++) {
                    for (int newJ = j; newJ < n; newJ++) {

                        // if the coordinates form a square
                        if ((newI - i) == (newJ - j)) {

                            // Check if all elements of subarray has weight >= h
                            boolean isValid = true;
                            for (int x = i; x <= newI; x++) {
                                for (int y = j; y <= newJ; y++) {
                                    if (p[x][y] < h) {
                                        isValid = false;
                                    }
                                }

                            }
                            // Update result if a larger square subarray is found
                            if (isValid && (newI - i + 1) > maxSize) {
                                maxSize = newI - i + 1;
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
        // Print the coordinates
        System.out
                .println(result[0] + " " + result[1] + " " + result[2] + " " + result[3]);

    }

    public static void alg2(int m, int n, int h, int[][] p) {
        int maxSize = 0;
        int[] result = new int[4];

        // Iterate through the entire 2d array as the top-most slot
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Check if the current slot has a weight >= h
                if (p[i][j] >= h) {

                    int currentSize = 1;
                    boolean isValid = true;

                    // expand valid square until the square is no longer valid
                    while (currentSize + i < m && currentSize + j < n && isValid) {

                        // check if the square could expand vertically
                        for (int k = i; k <= currentSize + i; k++) {
                            if (p[k][j + currentSize] < h) {
                                isValid = false;
                                break;
                            }
                        }

                        // check if the square could expand horizontally
                        for (int k = j; k <= currentSize + j; k++) {
                            if (p[i + currentSize][k] < h) {
                                isValid = false;
                                break;
                            }
                        }

                        // if it is a valid square, increase the size of square
                        if (isValid) {
                            currentSize++;
                        }
                    }

                    // if currentSize is bigger than maxSize, store the new maxSize
                    if (maxSize < currentSize) {
                        maxSize = currentSize;
                        result[0] = i + 1;
                        result[1] = j + 1;
                        result[2] = i + currentSize;
                        result[3] = j + currentSize;
                    }
                }
            }
        }

        System.out.println(result[0] + " " + result[1] + " " + result[2] + " " + result[3]);
    }

    public static void alg3(int m, int n, int h, int[][] p) {
        int maxSize = 0;
        int maxRow = 0;
        int maxCol = 0;
        int dp[][] = new int[m][n];

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

    public static void alg5a(int m, int n, int h, int[][] p) {
        int maxSize = 0;
        int maxRow = 0;
        int maxCol = 0;
        int dp[][] = new int[m][n];

        // Interate through the entire 2D array as the bottom-right plot
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // If the current element is greater than or equal to h
                if (p[i][j] >= h) {
                    // if current element is not located at the leftmost or topmost layer
                    if (i >= 1 && j >= 1) {

                        // check the 2x2 square where dp[i][j] is the bottom right plot and ignore the
                        // weight of p[i-1][j-1]
                        if (p[i - 1][j - 1] < h) {
                            dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                        } else {
                            int tempMin = Math.min(dp[i - 1][j], dp[i - 1][j - 1]);
                            dp[i][j] = Math.min(tempMin, dp[i][j - 1]) + 1;
                        }
                    } else {
                        dp[i][j] = 1;
                    }
                    // if dp[i][j] is greater than maxSize
                    if (dp[i][j] > maxSize) {
                        maxRow = i;
                        maxCol = j;
                        maxSize = dp[i][j];
                    }
                    // when the corner p[i][j] is < h
                } else {
                    dp[i][j] = 1;
                }
            }

        }
        System.out.println(
                (maxRow - maxSize + 2) + " " + (maxCol - maxSize + 2) + " " + (maxRow + 1) + " " + (maxCol + 1));

    }

    public static void alg5b(int m, int n, int h, int[][] p) {
        int maxSize = 0;
        int maxRow = 0;
        int maxCol = 0;
        int dp[][] = new int[m][n];

        // initialize the dp array
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }

        // Interate through the entire 2D array
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int currentSize = alg5bRecursion(i, j, m, n, h, p, dp);

                // if the current square is greater than maxSize
                if (currentSize > maxSize) {
                    maxSize = currentSize;
                    maxRow = i;
                    maxCol = j;
                }
            }
        }

        System.out.println(
                (maxRow - maxSize + 2) + " " + (maxCol - maxSize + 2) + " " + (maxRow + 1) +
                        " " + (maxCol + 1));

    }

    public static int alg5bRecursion(int i, int j, int m, int n, int h, int[][] p, int[][] dp) {
        // Base case

        // out of bound check
        if (i < 0 || m <= i || j < 0 || n <= j) {
            return 0;
        }

        // < h check
        if (p[i][j] < h) {
            return 0;
        }
        // if the current plot is visited, return its value
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        // Recursive calculations
        int left = alg5bRecursion(i, j - 1, m, n, h, p, dp);
        int top = alg5bRecursion(i - 1, j, m, n, h, p, dp);
        int topLeft = alg5bRecursion(i - 1, j - 1, m, n, h, p, dp);

        // current slot is the minimal value of the 3 adjacent slot + 1
        int temp = Math.min(top, topLeft);
        dp[i][j] = Math.min(temp, left) + 1;

        return dp[i][j];
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
        int maxSize = 0;
        int[] result = new int[4];

        // construct the prefix sum matrix
        int[][] prefixP = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // convert p[i][j] into binary representation of >= or < h
                if (p[i][j] >= h) {
                    prefixP[i][j] = 1;
                } else {
                    prefixP[i][j] = 0;
                }
                // prefix[i][j] should include the sum of current plot's row/column adjacent
                // plots
                if (i > 0 && j > 0) {
                    prefixP[i][j] -= prefixP[i - 1][j - 1];
                }
                if (i > 0) {
                    prefixP[i][j] += prefixP[i - 1][j];
                }
                if (j > 0) {
                    prefixP[i][j] += prefixP[i][j - 1];
                }
            }
        }

        // Iterate through all possibe square sizes
        for (int currentSize = 1; currentSize <= Math.min(m, n); currentSize++) {

            // Iterate all possible coordiantes within the size limitation
            for (int i = 0; i <= m - currentSize; i++) {
                for (int j = 0; j <= n - currentSize; j++) {

                    // calculate the number of plots that >= h
                    int sum = prefixP[i + currentSize - 1][j + currentSize - 1];
                    if (i > 0 && j > 0) {
                        sum += prefixP[i - 1][j - 1];
                    }
                    if (i > 0) {
                        sum -= prefixP[i - 1][j + currentSize - 1];
                    }
                    if (j > 0) {
                        sum -= prefixP[i + currentSize - 1][j - 1];
                    }
                    // is conditions are satisfied, update new maxSize and coordinates
                    if (currentSize > maxSize && (currentSize * currentSize - sum) <= k) {
                        maxSize = currentSize;
                        result[0] = i + 1;
                        result[1] = j + 1;
                        result[2] = i + currentSize;
                        result[3] = j + currentSize;

                    }
                }
            }
        }
        System.out.println(result[0] + " " + result[1] + " " + result[2] + " " + result[3]);
    }
}
