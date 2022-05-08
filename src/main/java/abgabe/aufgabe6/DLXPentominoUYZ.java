package abgabe.aufgabe6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Diese Klasse ließt über Kommandozeilen eine Ganzzahl n größer gleich 0 ein und
 * bestimmt mit dem DLX Algorithms wie viele Kombinationen aus U, Y und Z Pentominos
 * ein 5xn großes Feld füllen können.
 *
 * @author Anselm Koch
 * @author Robin Schüle
 * @author Denis Troccolo
 * @author Sergej Bakanow
 * @author Marvin Simon
 */
public class DLXPentominoUYZ {
  private static final int m = 5;

  public static void main(String[] args) {
    int n = getInput(args);
    System.out.printf("n | a(n)%n---------%n");
    List<int[]> positions = calculatePositions(n);
    DLXNode header = buildMatrix(positions, n * m);
    int cnt = search(0, header);
    System.out.println(String.format("%d | %d", n, cnt));
  }

  /**
   * Berechnet alle Positionen die jeder vorgegebene Pentomino auf einem nxm großen
   * Spielfeld einnehmen kann.
   * Positionen werden als Array von durchnummerierten Feldern des Spielbrettes angegeben,
   * welche vom jeweiligen Pentomino verdeckt werden.
   * Nummerierung der Felder des Spielfelds:
   * +----+----+----+----+----+
   * |  0 |  1 |  2 |  3 |  4 |
   * +----+----+----+----+----+
   * |  5 |  6 |  7 |  8 |  9 | n=3
   * +----+----+----+----+----+
   * | 10 | 11 | 12 | 13 | 14 |
   * +----+----+----+----+----+
   * m=5
   *
   * @param n Anzahl der Zeilen des Spielfelds
   * @return Liste mit allen Positionen die ein jeder Pentomino abdecken kann
   */
  public static List<int[]> calculatePositions(int n) {
    List<int[]> positions = new ArrayList<>();

    for (Pentomino p : Pentomino.values()) {
      for (int x = 0; x <= m - p.width; x++) {
        for (int y = 0; y <= n - p.height; y++) {
          // Versatz der Grundposition. +1 pro x, +m pro y
          int offset = x + y * m;
          int[] position = new int[p.data.length];
          // Grundposition des Pentominos verschieben, durch addition des Versatzes
          Arrays.setAll(position, i -> p.data[i] + offset);
          positions.add(position);
        }
      }
    }
    return positions;
  }

  /**
   * Erstellt aus den Positionen der Pentominos eine Datenstruktur aus DLXNode
   * auf denen der DLX Algorithmus ausgeführt werden kann.
   *
   * @param positions   Positionen der Pentominos
   * @param matrixWidth Breite der zu erstellenden Matrix. In dem Fall: nxm
   * @return Header der Datenstruktur
   */
  public static DLXNode buildMatrix(List<int[]> positions, int matrixWidth) {
    // Erstelle einen Header aus verlinkten DLXNodes. +1 da extra Header Element
    DLXNode[] header = createHorizontalLinkedNodes(matrixWidth + 1);
    // Für jede Position
    for (int[] position : positions) {
      // Erstelle eine Zeile aus verlinkten DLXNodes
      DLXNode[] row = createHorizontalLinkedNodes(position.length);
      for (int i = 0; i < position.length; i++) {
        // Verlinke jedes Element in der Zeile mit seinem entsprechenden Header
        insertNodeVertical(header[position[i]], row[i]);
      }
    }
    // Letztes Element in der Header Zeile ist Header Element
    return header[matrixWidth];
  }

  /**
   * Erstellt ein Array aus DLXNodes die horizontal zyklisch verlinkt sind.
   *
   * @param n Anzahl der Elemente
   * @return Horizontal verlinkte DLXNodes
   */
  private static DLXNode[] createHorizontalLinkedNodes(int n) {
    DLXNode[] row = new DLXNode[n];
    Arrays.setAll(row, i -> new DLXNode());
    for (int i = 0; i < row.length; i++) {
      row[i].R = row[(i + 1) % n];
      row[i].R.L = row[i];
    }
    return row;
  }

  /**
   * Fügt ein DLXNode über dem Header ein, indem es die Verknüpfungen anpasst.
   *
   * @param header  Header der Spalte
   * @param newNode Element das eingefügt werden soll
   */
  private static void insertNodeVertical(DLXNode header, DLXNode newNode) {
    newNode.C = header;
    newNode.D = header;
    newNode.U = header.U;
    header.U.D = newNode;
    header.U = newNode;
  }

  private static int getInput(String[] args) {
    int n;
    if (args.length > 0) {
      try {
        n = Integer.parseInt(args[0]);
        if (n >= 0) {
          return n;
        } else {
          System.out.println("Parameter kleiner als 0.");
        }
      } catch (NumberFormatException e) {
        System.out.println("Parameter ist keine Zahl.");
      }
    }
    while (true) {
      System.out.println("Geben sie n ein: ");
      Scanner in = new Scanner(System.in);
      try {
        n = in.nextInt();
      } catch (InputMismatchException e) {
        System.out.println("Eingabe ist keine Zahl.");
        continue;
      }
      if (n < 0) {
        System.out.println("Eingabe muss größer gleich 0 sein.");
        continue;
      }
      return n;
    }
  }

  /**
   * search tries to find and count all complete coverings of the DLX matrix.
   * Is a recursive, depth-first, backtracking algorithm that finds
   * all solutions to the exact cover problem encoded in the DLX matrix.
   * each time all columns are covered, static long cnt is increased
   *
   * @param k: number of level
   */
  public static int search(int k, DLXNode h) {   // finds & counts solutions
    int cnt = 0;
    if (h.R == h) {
      return 1;            // if empty: count & done
    }
    DLXNode c = h.R;                   // choose next column c
    cover(c);                          // remove c from columns
    for (DLXNode r = c.D; r != c; r = r.D) {  // forall rows with 1 in c
      for (DLXNode j = r.R; j != r; j = j.R) // forall 1-elements in row
      {
        cover(j.C);                    // remove column
      }
      cnt += search(k + 1, h);            // recursion
      for (DLXNode j = r.L; j != r; j = j.L) // forall 1-elements in row
      {
        uncover(j.C);                  // backtrack: un-remove
      }
    }
    uncover(c);                        // un-remove c to columns
    return cnt;
  }

  /**
   * cover "covers" a column c of the DLX matrix
   * column c will no longer be found in the column list
   * rows i with 1 element in column c will no longer be found
   * in other column lists than c
   * so column c and rows i are invisible after execution of cover
   *
   * @param c: header element of column that has to be covered
   */
  public static void cover(DLXNode c) {   // remove column c
    c.R.L = c.L;                         // remove header
    c.L.R = c.R;                         // .. from row list
    for (DLXNode i = c.D; i != c; i = i.D)      // forall rows with 1
    {
      for (DLXNode j = i.R; i != j; j = j.R) {   // forall elem in row
        j.D.U = j.U;                     // remove row element
        j.U.D = j.D;                     // .. from column list
      }
    }
  }

  /**
   * uncover "uncovers" a column c of the DLX matrix
   * all operations of cover are undone
   * so column c and rows i are visible again after execution of uncover
   *
   * @param c: header element of column that has to be uncovered
   */
  public static void uncover(DLXNode c) { //undo remove col c
    for (DLXNode i = c.U; i != c; i = i.U)      // forall rows with 1
    {
      for (DLXNode j = i.L; i != j; j = j.L) {   // forall elem in row
        j.D.U = j;                       // un-remove row elem
        j.U.D = j;                       // .. to column list
      }
    }
    c.R.L = c;                           // un-remove header
    c.L.R = c;                           // .. to row list
  }

  /**
   * Enum mit allen Pentomino die für die Aufgabe relevant sind.
   * Drehungen und Spiegelungen jedes Pentomino sind ein eigener Eintrag.
   */
  private enum Pentomino {
    U(3, 2, new int[] {0, 2, m, m + 1, m + 2}),
    U_FLIP_90(2, 3, new int[] {0, 1, m, 2 * m, 2 * m + 1}),
    U_FLIP_180(3, 2, new int[] {0, 1, 2, m, m + 2}),
    U_FLIP_270(2, 3, new int[] {0, 1, m + 1, 2 * m, 2 * m + 1}),
    Z(3, 3, new int[] {0, 1, m + 1, 2 * m + 1, 2 * m + 2}),
    Z_90(3, 3, new int[] {0, m, m + 1, m + 2, 2 * m + 2}),
    Z_FLIP(3, 3, new int[] {1, 2, m + 1, 2 * m, 2 * m + 1}),
    Z_FLIP_90(3, 3, new int[] {2, m, m + 1, m + 2, 2 * m}),
    Y(2, 4, new int[] {1, m + 1, 2 * m, 2 * m + 1, 3 * m + 1}),
    Y_90(4, 2, new int[] {0, 1, 2, 3, m + 2}),
    Y_180(2, 4, new int[] {0, m, m + 1, 2 * m, 3 * m}),
    Y_270(4, 2, new int[] {1, m, m + 1, m + 2, m + 3}),
    Y_FLIP(2, 4, new int[] {0, m, 2 * m, 2 * m + 1, 3 * m}),
    Y_FLIP_90(4, 2, new int[] {2, m, m + 1, m + 2, m + 3}),
    Y_FLIP_180(2, 4, new int[] {1, m, m + 1, 2 * m + 1, 3 * m + 1}),
    Y_FLIP_270(4, 2, new int[] {0, 1, 2, 3, m + 1});

    private final int width;
    private final int height;
    private final int[] data;

    Pentomino(int width, int height, int[] data) {
      this.width = width;
      this.height = height;
      this.data = data;
    }
  }

  /**
   * Class DLXNode
   * represents a matrix element of the cover matrix with value 1
   * links go to up down left right neigbors, and column header
   * can also be used as colm header or root of column headers
   * matrix is sparsely coded
   * try to do all operations very efficiently
   * see:
   * http://en.wikipedia.org/wiki/Dancing_Links
   * http://arxiv.org/abs/cs/0011047
   *
   * @author Alois Heinz
   */
  static class DLXNode {  // represents 1 element or header
    DLXNode C;           // reference to column-header
    DLXNode L, R, U, D;  // left, right, up, down references

    DLXNode() {
      C = L = R = U = D = this;
    } // supports circular lists
  }
}
