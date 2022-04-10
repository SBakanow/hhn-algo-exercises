# Abgabe 1

## Authors

Sergej Bakanow, Denis Troccolo, Anselm Koch, Robin Schüle, Marvin Simon

## Aufgabe 1

### Aufgabe 1.1

(2 + 3x + | x^2 + 2x^3) × (3 + 4x + | 2x^2 + 3x^3)

A: a_links x b_links
B: a_rechts x b_rechts
C: ((a_links + a_rechts) x (b_links + b_rechts)) - A - B

### Aufgabe 1.2

Al(x) = 2 + 3x
Ar(x) = 1 + 2x
A(x) = Al(x) x Ar(x) x x^2

Bl(x) = 3 + 4x
Br(x) = 2 + 3x
B(x) = Bl(x) x Br(x) x x^2

Teil 1:
Al(x) * Bl(x)
All(x) = 2
Alr(x) = 3x
Bll(x) = 3
Blr(x) = 4x

ALGesamt(x) = All(x) x Bll(x)
ARGesamt(x) = Alr(x) x Blr(x)
ALGesamt(x) + ARGesamt(x) + ((Al(x)) x (Bl(x)) - ALGesamt(x) - ARGesamt(x))
A = (6 + 17x + 12x^2)

Teil 2:
Arl(x) = 1
Arr(x) = 2x

Brl(x) = 2
Brr(x) = 3x
BLGesamt(x) = Arl(x) x Brl(x) = 2
BRGesamt(x) = Arr(x) x Brr(x) = 6x^2

BLGesamt(x) + BRGesamt(x) + ((Ar(x)) x (Br(x)) - BLGesamt(x) - BRGesamt(x))
B = 2 + 6x^2 + 7x

Teil 3:

(Al(x) + Ar(x)) x (Bl(x) + Br(x)) - A - B
(3 + 5x) x (5 + 7x) - (6 + 17x + 12x^2) - (2 + 6x^2 + 7x)
= (15 + 46x + 35x^2) - (6 + 17x + 12x^2) - (2 + 6x^2 + 7x)
C = 7 + 22x + 17x^2

A + Cx^2 + Bx^4
= 6 + 17x + 12x^2 + 7x^2 + 22x^3 + 17x^4 + 2x^4 + 6x^6 + 7x^5
= 6 + 17x + 19x^2 + 22x^3 + 19x^4 + 7x^5 + 6x^6

### Aufgabe 1.3

9 Koeffizienten Multiplikationen werden benötigt.
Jede Teilaufgabe vollzieht 3 Multiplikationen.

## Aufgabe 2

### Aufgabe 2.1

= 2 * T(n/4) + √n
= log4(2) + √n
= n^1/2 + n^1/2
= n^1/2 == n^1/2

θ(√n * log(n))

### Aufagbe 2.2

4 * T(n/2) + nlog(n)
= log2(4) + nlog(n)
= n^2 + nlog(n)
n^2 > nlog(n)
θ(n^2)

### Aufgabe 2.3

3 * T(n/3) + nlog(n)
= log3(3) + nlog(n)
= 1 + nlog(n)
n == nlog(n)
θ(n)

### Aufgabe 2.4

2 * T(n − 1) + n
= a > 1

θ(n*2^n)

### Aufgabe 2.5

T(n − 1) + 2
= a == 1

θ(n)

### Aufgabe 2.6

T(n − 1) + n^2 + 1
= a == 1

θ(n^3)
