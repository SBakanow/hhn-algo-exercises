package abgabe.aufgabe5;

class TohStack { // der Stack fuer TOH
  ArrNode head; // oberster Knoten
  public int counterPush = 0;
  TohStack (){ // Konstruktor
    head = null; // leerer Stack
  }
  public boolean isEmpty (){ // Test ob leer
    return head == null;
  }
  public void push(int a, int b, int c, int d){
    int [] arr = { a, b, c, d }; // 4 ints in Array
    head = new ArrNode (arr, head); // neuer Knoten auf Stack
    counterPush++;
  }
  public int [] pop (){ // Entfernt obersten Knoten
    counterPush--;
    ArrNode h = head; // das alte head
    head = head.getNext(); // head auf Nachfolger
    return h.getContent (); // liefert int Array zurueck
  }
} // TohStack
