package abgabe.aufgabe4;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Lösung Übung 4 der Vorlesung Algorithmen und Datenstrukturen.
 * Das Programm PermChangeOverDiff nimmt eine natürliche Zahl n entgegen und gibt alle Permutationen
 * in aufsteigender Reihenfolge aus, bei denen die absoluten Differenzen zweier aufeinanderfolgenden
 * Zahlen abwechselnd kleiner und größer werden.
 *
 * @author Denis Troccolo, Anselm Koch, Robin Schüle, Marvin Simon, Sergej Bakanow
 */
public class PermChangeOverDiff {
  public static void main(String[] arg) {
    System.out.print("Perm Change Over Diff, please input n: ");
    int n = 0;
    try {
      n = new Scanner(System.in).nextInt();
    } catch (Exception e) {
      System.exit(1);
    }
    long time = System.currentTimeMillis();
    new Perm(n);
    System.out.println(System.currentTimeMillis() - time + " ms");
  }


  static class Perm {
    private final int[] permutationArray;
    private final int maxIndex;
    int indexAfterFail = 0;
    int counter = 0;
    boolean noPrint = false;

    Perm(int n) {
      permutationArray = new int[n];
      maxIndex = n - 1;
      for (int i = 0; i <= maxIndex; i++) {
        permutationArray[i] = i + 1;
      }
      run();
    }

    private void run() {
      perm(0);
      System.out.println(counter);
    }

    private void perm(int i) {
      if (i == maxIndex) {
        if (!noPrint) {
          System.out.println(Arrays.toString(permutationArray));
          counter++;
        }
      } else {
        for (int j = i; j < permutationArray.length; j++) {
          swap(i, j);
          if ((validPerm() || i != 1) && indexAfterFail != i) {
            perm(i + 1);
          }
          noPrint = false;
        }
      }
      var helperVariable = permutationArray[i];
      System.arraycopy(permutationArray, i + 1, permutationArray, i, maxIndex - i);
      permutationArray[maxIndex] = helperVariable;
    }

    private void swap(int i, int j) {
      if (i != j) {
        int helperVariable = permutationArray[i];
        permutationArray[i] = permutationArray[j];
        permutationArray[j] = helperVariable;
      }
    }

    private boolean validPerm() {
      var checkValue = Math.abs(permutationArray[0] - permutationArray[1]);
      this.indexAfterFail = -1;
      if (checkValue <= 1) { //Check
        return false;
      }
      for (int i = 1; i < permutationArray.length - 1; i++) {
        var compareValue = Math.abs(permutationArray[i] - permutationArray[i + 1]);
        if (checkValue > compareValue && (i % 2) == 1) {
          checkValue = Math.abs(permutationArray[i] - permutationArray[i + 1]);
        } else if (checkValue < compareValue && (i % 2) == 0) {
          checkValue = Math.abs(permutationArray[i] - permutationArray[i + 1]);
        } else {
          this.indexAfterFail = i + 1;
          noPrint = true;
          break;
        }
      }
      return true;
    }
  }
}
