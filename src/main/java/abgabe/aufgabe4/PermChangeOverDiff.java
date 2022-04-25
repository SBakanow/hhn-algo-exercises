package abgabe.aufgabe4;

import java.util.Arrays;
import java.util.Scanner;

public class PermChangeOverDiff {
  public static void main(String[] arg) {
    System.out.print("Perm Change Over Diff, please input n: ");
    int n = 0;
    int counter = 0;
    try {
      n = new Scanner(System.in).nextInt();
    } catch (Exception e) {
      System.exit(1);
    }
    Perm p = new Perm(n); // Liefert Permutationen von 0 .. N-1 mit 0 fix
    int[] c;
    while ((c = p.getNext()) != null) { // Naechste Permutation
     System.out.println(Arrays.toString(c));
     counter++;
    }
    System.out.println(counter);
  }
}
