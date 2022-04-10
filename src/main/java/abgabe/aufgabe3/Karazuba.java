package abgabe.aufgabe3;

import java.util.Arrays;

public class Karazuba {

  public static void main(String[] args) {
    int[] a = {2, 1, 3, 2};
    int[] b = {3, 2, 4, 3};
    System.out.println(Arrays.toString(prod(a, b)));
  }

  public static int[] prod(int[] a, int[] b) {
    int n = a.length, nh = n / 2;
    int[] r = new int[2 * n];
    if (n == 1) {
      r[0] = a[0] * b[0];
    } else {
      int[] al = new int[nh], ar = new int[nh],
          bl = new int[nh], br = new int[nh],
          alr = new int[nh], blr = new int[nh];
      for (int i = 0; i < nh; i++) {
        alr[i] = al[i] = a[i];
        blr[i] = bl[i] = b[i];
        alr[i] += ar[i] = a[i + nh];
        blr[i] += br[i] = b[i + nh];
      }

      int[] A = prod(al, bl);
      int[] B = prod(ar, br);
      int[] C = prod(alr, blr);

      for(int i=0; i<n; i++) {
        r[i] += A[i];
        r[i + nh] += C[i] - A[i] - B[i];
        r[i + n] = B[i];
      }
    }
    return r;
  }
}
