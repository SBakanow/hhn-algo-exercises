package abgabe.aufgabe2;

public class MaxProd {

  /*
    Input: A[] = { 6, -3, -10, 0, 2 }
    Output: 180  // The subarray is {6, -3, -10}

    Input: A[] = {-1, -3, -10, 0, 60 }
    Output: 60  // The subarray is {60}

    Input: A[] = { -2, -3, 0, -2, -40 }
    Output: 80  // The subarray is {-2, -40}
   */
  public static double maxProd(double[] inputs) {
    double bestProd = 0.0;
    double bufferProd = 1.0;
    for (var input : inputs) {
      bufferProd *= input;
      if (bestProd < bufferProd) {
        bestProd = bufferProd;
      }
    }
    return bestProd;
  }

  public static void main(String[] args) {
    System.out.println(maxProd(new double[] {-1, -3, -10, 0, 60}));
  }
}
