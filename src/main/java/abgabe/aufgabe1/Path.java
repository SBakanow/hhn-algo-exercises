package abgabe.aufgabe1;

import java.math.BigInteger;
import java.util.HashMap;

public class Path {
  static int[] pathsY = {-4, -2, 0, 1};

  static BigInteger calculatePathNumbers(int steps) {
    return recursiveCall2(steps, 0, new HashMap<>());
  }

  static BigInteger recursiveCall2(int steps, long currentYValue, HashMap<Long, BigInteger> map) {
    if (steps == 0) {
      return currentYValue == 0 ? BigInteger.valueOf(1) : BigInteger.valueOf(0);
    }
    BigInteger solutions = BigInteger.valueOf(0);
    for (int yDisplacement : pathsY) {
      long nextValue = currentYValue + yDisplacement;
      if (nextValue >= 0) {
        BigInteger nextSolution = map.get(generateKey(steps - 1, nextValue));
        if (nextSolution == null) {
          nextSolution = recursiveCall2(steps - 1, nextValue, map);
          map.put(generateKey(steps - 1, nextValue), nextSolution);
        }
        solutions = solutions.add(nextSolution);
      }
    }
    return solutions;
  }


  static long generateKey(int step, long currentYDisplacement) {
    return ((long) step << 10 | currentYDisplacement);
  }


  /*static void recursiveCall(long valueY, int steps) {
    if (steps == 0 && valueY == 0) {
      numberOfPaths++;
    } else if (valueY >= 0 && steps > 0) {
      for (int pathY : pathsY) {
        recursiveCall(valueY + pathY, steps - 1);
      }
    }
  }*/

  public static void main(String[] args) {
    for (int i = 0; i < 400; i++) {
      System.out.println("Step " + i + ": " + calculatePathNumbers(i));
    }
  }
}
