package com.dsalgo.arrays;

/*

Given input array has +ve and -ve numbers.

Part-1 : [STARTS]
Both +ve and -ve numbers are equal.
Rearrange the array such that the +ve and -ve numbers are alternate but their order within themselves is not changed.

input - 3, 1, -2, -5, 2, -4
output - 2, -2, 1, -5, 2, -4

Order of +ve and -ve numbers is not changed.

Algorithm :

Solution-1 :
Take 2 new arrays, one to hold +ve and another to hold -ve numbers.
Iterate over the input array and populate the 2 new arrays.

Then iterate of the 2 new arrays and update back the input array such that +ve and -ve are alternate.

To populate as alternate :
We know that first number is +ve, then -ve, then +ve
We can say that +ve are on even index and -ve on odd index.


However, this solution takes 2 iterations over the array.


Optimized approach -
Do this in one iteration.
We know that +ve number will go to even place and -ve to odd place.
We can't avoid taking extra space here.
Create new array and have 2 pointers - positive and negative
Initialize positive with 0 and negative with 1.
Then iterate over the array.
If the element is positive, then update the new array[positive] with current element.
Then move positive to its next potential position which will be after negative, that is, positive+2

Do same with negative index.

Part-1 : [ENDS]

 */

import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;
import java.util.List;

public class _11_RearrangeArrayElementsBySign {

    private static int[] solve_extraIteration_part_1(int[] array) {
        int[] positive = new int[array.length / 2];
        int[] negative = new int[array.length / 2];

        int positiveIndex = 0, negativeIndex = 0;
        for (int i : array) {
            if (i > 0) {
                positive[positiveIndex++] = i;
            } else if (i < 0) {
                negative[negativeIndex++] = i;
            }
        }

        // update input array with alternate +ve and -ve numbers
        for (int i = 0; i < positive.length; i++) {
            array[2 * i] = positive[i];         // even index
            array[2 * i + 1] = negative[i];     // odd index
        }

        return array;
    }

    private static int[] solve_withoutExtraIteration_part_1(int[] array) {
        int[] newArray = new int[array.length];

        int positiveIndex = 0, negativeIndex = 1;
        for (int i : array) {
            if (i > 0) {
                newArray[positiveIndex] = i;
                positiveIndex += 2;
            } else if (i < 0) {
                newArray[negativeIndex] = i;
                negativeIndex += 2;
            }
        }

        return newArray;
    }

    private static void print(int[] array) {
        for (int j : array) {
            System.out.print(j + " ");
        }
    }

    public static void main(String[] args) {
        int[] array = {3, 1, -2, -5, 2, -4};
        array = solve_extraIteration_part_1(array);
        print(array);

        System.out.println();

        int[] array2 = {3, 1, -2, -5, 2, -4};
        array2 = solve_withoutExtraIteration_part_1(array2);
        print(array2);
    }
}

/*
As an extension to the above problem, the question may state that the +ve and -ve numbers in the input array are not equal.
It will mention that initially arrange the numbers alternatively, and then, add remaining to the last of the array.

input - -1, 2, 3, 4, -3, 1
output - 2, -1, 3, -3, 4, 1

To solve this, we cannot use the optimized version mentioned above.
We will fallback to the brute force where we created 2 separate arrays for +ve and -ve numbers.

In this case, either of +ve or -ve array will be larger in size. So the remaining elements will be added to the last from this larger size array
 */

class Part_2 {

    private static int[] solve_extraIteration_part_2(int[] array) {
        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();

        int positiveIndex = 0, negativeIndex = 0;
        for (int i : array) {
            if (i > 0) {
                positive.add(i);
            } else if (i < 0) {
                negative.add(i);
            }
        }


        // update input array with alternate +ve and -ve numbers

        if (positive.size() == negative.size()) {
            for (int i = 0; i < positive.size(); i++) {
                array[2 * i] = positive.get(i);         // even index
                array[2 * i + 1] = negative.get(i);     // odd index
            }
        } else if (positive.size() > negative.size()) {
            for (int i = 0; i < negative.size(); i++) {
                array[2 * i] = positive.get(i);         // even index
                array[2 * i + 1] = negative.get(i);     // odd index
            }

            // add extra positive elements to the end
            int index = 0;
            for (int i = negative.size(); i < positive.size(); i++) {
                array[2 * negative.size() + index] = positive.get(i);
                index++;
            }

        } else if (positive.size() < negative.size()) {
            for (int i = 0; i < positive.size(); i++) {
                array[2 * i] = positive.get(i);         // even index
                array[2 * i + 1] = negative.get(i);     // odd index
            }

            // add extra negative elements to the end
            int index = 0;
            for (int i = positive.size(); i < negative.size(); i++) {
                array[2 * positive.size() + index] = negative.get(i);
                index++;
            }
        }

        return array;
    }

    private static void print(int[] array) {
        for (int j : array) {
            System.out.print(j + " ");
        }
    }

    public static void main(String[] args) {
        int[] array1 = {-1, 2, 3, 4, -3, 1};
        array1 = solve_extraIteration_part_2(array1);
        print(array1);

        System.out.println();

        int[] array2 = {1, -2, -3, -4, 3, -1};
        array2 = solve_extraIteration_part_2(array2);
        print(array2);

    }
}