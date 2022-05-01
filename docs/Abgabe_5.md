# Abgabe 5

## Autoren

Sergej Bakanow, Denis Troccolo, Anselm Koch, Robin Schüle, Marvin Simon

## Aufgabe 1

### Aufgabe 1.1

Anzahl der Aufrufe wird durch die Funktion 𝑓 dargestellt.

Wenn f(1), gibt es nur ein Element im Array und die Abbruchbedingung in der perm-Methode
wird sofort erreicht. Deshalb gibt es nur einen Aufruf.

    𝑓(1) = 1

Für jede weitere Zahl gibt es n-Mal so viele Aufrufe wie bei der Zahl davor, da in der for-Schleife der perm-Methode
n-Mal ein rekursiver Aufruf erfolgt. Da der initiale Aufruf von perm
auch gewertet werden muss, muss dazu noch 1 addiert werden.

Daraus ergibt sich:

    𝑓(𝑛) = 𝑛 ∗ 𝑓(𝑛 − 1) + 1

### Aufgabe 1.2

Die Anzahl der Aufrufe von perm lässt sich durch die Funktion **𝑓(𝑛)** aus Aufgabe 1.1
ermitteln. Die Anzahl der Permutationen lässt sich durch **𝑛!** ermitteln. Daraus folgend, kann
man die Anzahl von perm-Aufrufen pro Permutation so ermitteln:

    𝑓(𝑛) / 𝑛!

Da wir den Wert für ein gegen Unendlich gehendes n ermitteln wollen brauchen wir den
Grenzwert.

    lim 𝑛→∞ 𝑓(𝑛) / 𝑛! ≈ 1,71828 ≈ 𝑒 − 1

Der Grenzwert nähert sich 1,71828 an, was ungefähr e-1 entspricht.

## Aufgabe 2

### Aufgabe 2.1

