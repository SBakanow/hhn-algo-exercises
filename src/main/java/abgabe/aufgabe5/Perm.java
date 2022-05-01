package abgabe.aufgabe5;

class Perm extends Thread {

  public static void main(String[] args) {

    new Perm(20);
  }
  private int[] a; // a Arbeitsarray
  private int max; // maximaler Index
  private boolean mayread = false; // Kontrolle
  double counterFunc = 0;
  double counterPerm = 0;

  Perm(int n) { // Konstruktor
    a = new int[n]; // Indices: 0 .. n-1
    max = n - 1; // Maximaler Index
    for (int i = 0; i <= max; ) {
      a[i] = i++; // a fuellen
    }
    run();
  } // end Konstruktor

  public void run() {// die Arbeits-Methode
    perm(0); // a[0] bleibt fest; permutiere ab 1
    a = null; // Anzeige, dass fertig

  } // end run

  // ...
  private void perm(int i) { // permutiere ab Index i
    counterFunc++;
    if (i >= max) {
      //Hier wird permutation erstellt
      counterPerm++;
    } else {
      for (int j = i; j <= max; j++) { // jedes nach Vorne
        swap(i, j); // vertauschen
        perm(i + 1); // und Rekursion
      }
      int h = a[i]; // restauriere Array
      System.arraycopy(a, i + 1, a, i, max - i); // shift links
      a[max] = h;
    }
    System.out.println("Ausgabe counterPush: " + (counterFunc/counterPerm) + " Perm n: " + (max + 0));
  } // end perm

  private void swap(int i, int j) { // tausche a[i] <-> a[j]
    if (i != j) {
      int h = a[i];
      a[i] = a[j];
      a[j] = h;
    }
  } // end swap
  /*private synchronized void put (){ // uebergebe array
    mayread = true; // a wird gelesen
    notify (); // wecke anderen Thread
    try{ if (a!=null)
      while (mayread) wait (); // non busy waiting
    } catch (InterruptedException e){}
  } // end put*/

} // Perm
