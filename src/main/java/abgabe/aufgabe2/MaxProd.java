package abgabe.aufgabe2;

/**
 * Lösung Übung 2 der Vorlesung Algorithmen und Datenstrukturen.
 * Das Programm MaxProd nimmt eine beliebige Folge von reellen Zahlen entgegen, bestimmt und gibt
 * eine aufeinanderfolgende Teilfolge darin aus, welche das maximal Produkt liefert. Das maximale
 * Produkt wird am Ende auch mit ausgegeben.
 *
 * @author Denis Troccolo, Anselm Koch, Robin Schüle, Marvin Simon, Sergej Bakanow
 */
public final class MaxProd {

  public static void main(String[] args) {
    double[] values = new double[args.length];
    for (int i = 0; i < args.length; i++) {
      values[i] = Double.parseDouble(args[i]);
    }
    System.out.println("Max product: " + maxProd(values));
    System.out.println("Laufzeit O(n)");
  }

  /**
   * Die eigentliche Methode zur Bestimmung des maximalen Produkts, sowie der dazugehörigen
   * Teilfolge.
   *
   * @param inputs Array aus Double Zahlen.
   * @return Liefert das maximale Produkt der Zahlenfolge.
   */
  public static double maxProd(final double[] inputs) {
    double maxProduct = inputs[0];
    double minProduct = inputs[0];
    double result = inputs[0];
    int indexEnd = 0;

    // Schleife zur Bestimmung des maximalen Produkts und des Index der letzten Zahl der Teilfolge.
    for (int i = 1; i < inputs.length; i++) {
      double temp = Math.max(Math.max(inputs[i], inputs[i] * maxProduct), inputs[i] * minProduct);
      minProduct = Math.min(Math.min(inputs[i], inputs[i] * maxProduct), inputs[i] * minProduct);
      maxProduct = temp;
      if (result < maxProduct) {
        result = maxProduct;
        indexEnd = i;
      }
    }

    // Schleife zur Bestimmung des Index der ersten Zahl der Teilfolge
    int indexStart = indexEnd;
    for (double i = 1.0; i < result; --indexStart) {
      i *= inputs[indexStart];
      if (i >= result) {
        break;
      }
    }

    // Erzeugung des Strings zur Ausgabe der Teilfolge, sowie des maximalen Produkts.
    StringBuilder subarray = new StringBuilder();
    subarray.append("Teilfolge: [");
    for (int i = indexStart; i <= indexEnd; ++i) {
      subarray.append(inputs[i]).append(", ");
    }
    String arrayString = subarray.substring(0, subarray.length() - 2);
    System.out.print(arrayString + "]\n");

    return result;
  }
}
