package abgabe.aufgabe2;

public final class MaxProd {

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
    System.out.println(indexStart + " " + indexEnd);
    return bisMax;
  }

  public static void main(String[] args) {
    System.out.println(maxProd(new double[] {1.0, -2.0, -2.0, 1.5}));
  }
}
