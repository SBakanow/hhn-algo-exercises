package abgabe.aufgabe2;

public class MaxProd {

  public static double maxProd(double [] inputs) {
    double bestProd = 0.0;
    double bufferProd = 1.0;
    for (var input: inputs) {
      bufferProd *= input;
      if(bestProd < bufferProd) {
        bestProd = bufferProd;
      }
    }
    return bestProd;
  }

  public static void main(String[] args) {
    System.out.println(maxProd(new double[] {1.0, -2.0, -2.0, 1.5}));
  }
}
