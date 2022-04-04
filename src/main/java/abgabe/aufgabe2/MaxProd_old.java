package abgabe.aufgabe2;

public final class MaxProd_old {

  /*
    Input: A[] = { 6, -3, -10, 0, 2 }
    Output: 180  // The subarray is {6, -3, -10}

    Input: A[] = {-1, -3, -10, 0, 60 }
    Output: 60  // The subarray is {60}

    Input: A[] = { -2, -3, 0, -2, -40 }
    Output: 80  // The subarray is {-2, -40}
   */
  public static double maxProd(final double[] inputs) {
    double scanMax = 1.0;
    double bisMax = 1.0;
    int indexEnd = 0;

    for (int i = 0; i < inputs.length; ++i) {
      scanMax *= inputs[i];

      if (bisMax < scanMax) {
        bisMax = scanMax;
        indexEnd = i;
      }
    }

    int indexStart = indexEnd;
    for (double i = 1.0; i < bisMax; --indexStart) {
      i *= inputs[indexStart];
      if (i >= bisMax) {
        break;
      }
    }

    for (int i = indexStart; i <= indexEnd; ++i) {
      System.out.println(inputs[i]);
    }
    return bisMax;
  }

  public static void main(String[] args) {
    long startTime = System.nanoTime();
    System.out.println("Max product: " + maxProd(new double[] {1.0, -2.0, -2.0, 1.5, -1.0, 3.0, 3.0}));
    System.out.println("Execution Time: " + (System.nanoTime() - startTime)/1000000 + "ms");
  }
}
