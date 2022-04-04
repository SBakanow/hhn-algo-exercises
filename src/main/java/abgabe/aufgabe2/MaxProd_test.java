package abgabe.aufgabe2;

import java.util.Scanner;

public final class MaxProd_test {

  public static double maxProd(final double[] inputs) {
    double maxProduct = inputs[0];
    double minProduct = inputs[0];
    double result = inputs[0];
    int indexEnd = 0;

    for (int i = 1; i < inputs.length; i++) {
      double temp =
          Math.max(Math.max(inputs[i], inputs[i] * maxProduct), inputs[i] * minProduct);
      minProduct =
          Math.min(Math.min(inputs[i], inputs[i] * maxProduct), inputs[i] * minProduct);
      maxProduct = temp;
      if (result < maxProduct) {
        result = maxProduct;
        indexEnd = i;
      }
    }

    int indexStart = indexEnd;
    for (double i = 1.0; i < result; --indexStart) {
      i *= inputs[indexStart];
      if (i >= result) {
        break;
      }
    }

    StringBuilder subarray = new StringBuilder();
    subarray.append("[");
    for (int i = indexStart; i <= indexEnd; ++i) {
      subarray.append(inputs[i]).append(", ");
    }
    String arrayString = subarray.substring(0, subarray.length() - 2);
    System.out.print(arrayString + "]\n");
    return result;
  }

  public static void main(String[] args) {
    long startTime = System.nanoTime();
    Scanner input = new Scanner(System.in);
    if (input.hasNextLine()) {
      String line = input.nextLine();
      String[] array = line.split("\\s+");
      double[] values = new double[array.length];
      for (int i = 0; i < array.length; i++) {
        values[i] = Double.parseDouble(array[i]);
      }
      System.out.println("Max product: " + maxProd(values));
    }
    System.out.println("Execution Time: " + (System.nanoTime() - startTime) / 1000000 + "ms");
  }
}
