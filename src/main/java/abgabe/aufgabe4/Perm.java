package abgabe.aufgabe4;

class Perm extends Thread {
  private int[] a; // a Arbeitsarray
  private final int max; // maximaler Index
  private boolean mayread = false; // Kontrolle
  int k = 0;

  boolean dontPut = true;

  Perm(int n) { // Konstruktor
    a = new int[n]; // Indices: 0 .. n-1
    max = n - 1; // Maximaler Index
    for (int i = 0; i <= max; i++) {
      a[i] = i + 1; // a fuellen
    }
    this.start(); // run-Methode beginnt zu laufen
  } // end Konstruktor

  public void run() {// die Arbeits-Methode
    perm(0);
    a = null; // Anzeige, dass fertig
    put(); // ausliefern
  } // end run

  private void perm(int i) { // permutiere ab Index i
    if (i == max) {
      if (dontPut) {
        put(); // Liefert fertige permutation
      }
    } else {
      for (int j = i; j < a.length; j++) {
        swap(i, j);

        if (validPerm(i) || i != 1) {
          if (k != i) {
            perm(i + 1);
          }
        }
      dontPut = true;
      }
    }
    var h = a[i];
    System.arraycopy(a, i + 1, a, i, max - i);
    a[max] = h;
  } // end perm

  private void swap(int i, int j) { // tausche a[i] <-> a[j]
    if (i != j) {
      int h = a[i];
      a[i] = a[j];
      a[j] = h;
    }
  } // end swap

  private boolean validPerm(int i) {
    var checkValue = Math.abs(a[0] - a[1]);
    this.k = -1;
    if (checkValue <= 1) {
      return false;
    }
    int x = Math.abs(a[0] - a[1]);
    for (int k = 1; k < a.length - 1; k++) {
      if (x > Math.abs(a[k] - a[k + 1]) && (k % 2) == 1) {
        x = Math.abs(a[k] - a[k + 1]);
      } else if (x < Math.abs(a[k] - a[k + 1]) && (k % 2) == 0) {
        x = Math.abs(a[k] - a[k + 1]);
      } else {
        this.k = k + 1;
        dontPut = false;
        break;
      }
    }
    return true;
  }

  synchronized int[] getNext() { // liefert naechste Permutation
    mayread = false; // a darf geaendert werden
    notifyAll(); // wecke anderen Thread
    try {
      while (!mayread) {
        wait(); // non busy waiting
      }
    } catch (InterruptedException e) {
    }
    return a; // liefere Permutationsarray
  } // end getNext

  private synchronized void put() { // uebergebe array
    mayread = true; // a wird gelesen
    notifyAll(); // wecke anderen Thread
    try {
      if (a != null) {
        while (mayread) {
          wait(); // non busy waiting
        }
      }
    } catch (InterruptedException e) {
    }
  } // end put
}