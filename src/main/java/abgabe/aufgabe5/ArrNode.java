package abgabe.aufgabe5;

class ArrNode { // Knoten fuer TOH-Stack
  private int [] content; // Inhalt: Array von ints
  private ArrNode next; // Referenz auf Nachfolgeknoten
  ArrNode (int [] a, ArrNode n){ // Konstruktor
    content = a; // Inhalt
    next = n; // und Nachfolger eintragen
  }
  public int [] getContent (){ // int-Array herausgeben
    return content;
  }
  public ArrNode getNext(){ // Nachfolgeknoten liefern
    return next;
  }
} // ArrNode
