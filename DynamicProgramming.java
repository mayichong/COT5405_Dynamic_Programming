import java.util.Scanner;
import java.lang.Math;

public class DynamicProgramming {
    public static void main(String[] args) {
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
        // alg1(m, n, h, p);
        // alg2(m, n, h, p);
        alg3(m, n, h, p);
    }

    public static void alg1(int m, int n, int h, int[][] p) {
        int maxSize = 0;
        int[] result = new int[4];

        // Interate through the entire 2D array
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // If the current element is greater than or equal to h
                if (p[j][i] >= h) {
                    // Initialize size equals to 1
                    int size = 1;

                    // We check if right and bottom have element or not
                    while (i + size <= n && j + size <= m) {
                        // Make boolean true
                        boolean flag = true;

                        // if each element in the square has a minimum of h
                        for (int k = j; k < j + size; k++) {
                            for (int l = i; l < i + size; l++) {
                                if (p[k][l] < h) {
                                    flag = false;
                                    break;
                                }
                            }
                            if (!flag) {
                                break;
                            }
                        }

                        // If the number meets the minimum tree, we increase size and loop again
                        if (flag && size > maxSize) {
                            maxSize = size;
                            result[0] = i + 1;
                            result[1] = j + 1;
                            result[2] = i + size;
                            result[3] = j + size;

                        }
                        size++;
                    }

                }
            }
        }
        System.out.println(result[0] + " " + result[1] + " " + result[2] + " " +
                result[3]);
    }

    public static void alg2(int m, int n, int h, int[][] arr) {
        int maxSize = 0;
        int[] result = new int[4];

        // Iterate over all possible subarrays
        for (int size = 1; size <= Math.min(n, m); size++) {
            for (int i = 0; i <= n - size; i++) {
                for (int j = 0; j <= m - size; j++) {
                    // Check if all elements of subarray meet the requirement
                    boolean isSquare = true;
                    for (int p = i; p < i + size; p++) {
                        for (int q = j; q < j + size; q++) {
                            if (arr[q][p] < h) {
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
                        result[2] = i + size;
                        result[3] = j + size;
                    }
                }
            }
        }

        System.out.println(result[0] + " " + result[1] + " " + result[2] + " " +
                result[3]);
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
                    // if current element is not located at the outer layer
                    if (i >= 1 && j >= 1) {
                        int tempMin = Math.min(dp[i - 1][j], dp[i - 1][j - 1]);
                        dp[i][j] = Math.min(tempMin, dp[i][j - 1]) + 1;
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
                (maxRow - maxSize + 2) + " " + (maxCol - maxSize + 2) + " " + (maxRow + 1) + " " + (maxCol + 1));
    }
}