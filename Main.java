import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // Input: Matrix dimensions
        int n = 5;
        int m = 5;
        int[][] arr = new int[n][m];

        // Matrix elements
        arr[0] = new int[] { 1, 2, 3, 4, 5 };
        arr[1] = new int[] { 6, 7, 8, 9, 10 };
        arr[2] = new int[] { 11, 12, 13, 14, 15 };
        arr[3] = new int[] { 16, 17, 18, 19, 20 };
        arr[4] = new int[] { 21, 22, 23, 24, 25 };

        // Ring number (sno) and rotation number (rno)
        int sno = 2; // Second ring (outermost ring is ring 1)
        int rno = 2; // Rotate by 2 steps

        // Call the ring rotation function
        ringrotate(arr, sno, rno);

        // Display the updated matrix
        display(arr);
    }

    public static void ringrotate(int[][] arr, int sno, int rno) {
        // Convert the 2D ring into 1D array
        int[] la = fill1Dfrom2D(arr, sno);

        // Rotate the 1D array
        rotate1D(la, rno);

        // Fill the 2D array back from 1D array
        fill2Dfrom1D(arr, la, sno);
    }

    public static int[] fill1Dfrom2D(int[][] arr, int sno) {
        int rmin = sno - 1;
        int cmin = sno - 1;
        int rmax = arr.length - sno;
        int cmax = arr[0].length - sno;

        // Total size of the ring
        int sz = 2 * (rmax - rmin + cmax - cmin);

        int[] la = new int[sz];
        int idx = 0;

        // Traverse the ring and fill the 1D array
        for (int row = rmin; row <= rmax; row++) {
            la[idx] = arr[row][cmin];
            idx++;
        }
        cmin++;

        for (int col = cmin; col <= cmax; col++) {
            la[idx] = arr[rmax][col];
            idx++;
        }
        rmax--;

        for (int row = rmax; row >= rmin; row--) {
            la[idx] = arr[row][cmax];
            idx++;
        }
        cmax--;

        for (int col = cmax; col >= cmin; col--) {
            la[idx] = arr[rmin][col];
            idx++;
        }
        rmin++;

        return la;
    }

    public static void rotate1D(int[] la, int rno) {
        rno = rno % la.length;

        if (rno < 0) {
            rno += la.length;
        }

        reverse(la, 0, la.length - 1);
        reverse(la, 0, rno - 1);
        reverse(la, rno, la.length - 1);
    }

    public static void reverse(int[] la, int left, int right) {
        while (left < right) {
            int temp = la[left];
            la[left] = la[right];
            la[right] = temp;
            left++;
            right--;
        }
    }

    public static void fill2Dfrom1D(int[][] arr, int[] la, int sno) {
        int rmin = sno - 1;
        int cmin = sno - 1;
        int rmax = arr.length - sno;
        int cmax = arr[0].length - sno;

        int idx = 0;

        // Fill the 2D array back from the 1D array
        for (int row = rmin; row <= rmax; row++) {
            arr[row][cmin] = la[idx];
            idx++;
        }
        cmin++;

        for (int col = cmin; col <= cmax; col++) {
            arr[rmax][col] = la[idx];
            idx++;
        }
        rmax--;

        for (int row = rmax; row >= rmin; row--) {
            arr[row][cmax] = la[idx];
            idx++;
        }
        cmax--;

        for (int col = cmax; col >= cmin; col--) {
            arr[rmin][col] = la[idx];
            idx++;
        }
        rmin++;
    }

    public static void display(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
