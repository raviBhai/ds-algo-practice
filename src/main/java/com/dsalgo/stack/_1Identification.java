package com.dsalgo.stack;

public class _1Identification {
    /**
     *
     * In the given question, array is given as an input.
     * To get the output by brute force, you figure out that 2 nested loops are required.
     * Outer loop "i" can run from 0 to n
     * inner loop "j" is conditional on "i" and ca run from -
     *  1. 0 to i
     *  2. i to 0
     *  3. i to n
     *  4. n to i
     *
     *  for (int i = 0; i < n; i++) {
     *
     *      for (int j = 0; j < i; j++) {
     *
     *      }
     *
     *  }
     *
     *
     *  for (int i = 0; i < n; i++) {
     *
     *      for (int j = i; j >=0 ; j--) {
     *
     *      }
     *
     *  }


     *  for (int i = 0; i < n; i++) {
     *
     *      for (int j = i; j < n; j++) {
     *
     *      }
     *
     *  }


     *  for (int i = 0; i < n; i++) {
     *
     *      for (int j = n; j >= i; j--) {
     *
     *      }
     *
     *  }

     *
     *
     *  If you identify any of the above 4 patterns, try to use STACK
     *
     */
}
