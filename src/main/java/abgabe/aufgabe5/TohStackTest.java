package abgabe.aufgabe5;

public class TohStackTest {
  public static void main(String args[]) {
    int counter = 0;
    TohStack s = new TohStack(); // leerer Stack
    for (int i = 0; i < 10; i++) {
      s.push(i, 1, 2, 3); // Startproblem
      counter = 0;
      while (!s.isEmpty()) { // Iteration statt Rekursion
        int[] a = s.pop();
        if (a[3] == 0) {
       /* System.out.println(
            "bewege Scheibe Nr. " + (a[0]) +
                " von Pfeiler " + a[1] + " nach " + a[2]);*/
        } else if (a[0] > 0) {
          counter += 3;
          s.push(a[0] - 1, a[3], a[2], a[1]); // zuletzt
          s.push(a[0], a[1], a[2], 0); // danach
          s.push(a[0] - 1, a[1], a[3], a[2]); // zuerst
        }
      }// while
    }
  }// main
} // TohStackTest
