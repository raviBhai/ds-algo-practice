package com.dsalgo.binarysearch;

/**
 * Given input array and an element k
 * Array is of books where each entry represents number of pages in a book.
 * Element k is the number of students.
 *
 * Distribute the books among the k students such that the maximum number of pages read by any student is minimum.
 * Conditions to distribute -
 * 1. every student should get at least one book
 * 2. all the pages in one book has to be read by one student, a book cannot be divided between more than one student
 * 3. books should be given to students in continuous location.
 *      for eg, input array - 10,20,30,40, and k=2 (students s1 and s2), s1 cannot have 10+30 and s2 cannot have 20+40.
 *      s1 can have - 10 OR 10,20 OR 10,20,30, that is, continuous locations
 *
 * Example - Array - 10,20,30,40 and k=2
 * Students - s1, s2
 * If s1 gets book with 10 pages and s2 gets remaining (20+30+40), then s1 will read 10 and s2 will read 90 pages
 * If s1 gets book with 10,20 pages and s2 gets remaining (30+40), then s1 will read 30 and s2 will read 70 pages
 * If s1 gets book with 10,20,30 pages and s2 gets remaining (40), then s1 will read 60 and s2 will read 40 pages
 * These are the only ways to distribute the books among the students by satisfying the conditions.
 * In these 3 cases, get student who read max pages - s2 can read 90 or 70 and s1 can read 60
 * Get the minimum of these maximum pages, which is s1 reading 60 pages, and return as output.
 *
 * Solution -
 * Base condition - if number of books are less than the number of students, return -1, as it does not satisfy the
 * condition that every student should get at least one book.
 *
 * Take a number line with start and end. A number on this line denotes the max pages a student can read.
 * Take mid = (start + end) / 2, this mid denotes the max pages a student can read.
 * Check if it is possible to distribute the books among k students such that max pages read by any student is less than or equal to mid
 * If no, it means max number of pages that can be read should be increased. Hence, do start = mid+1
 * If yes, it means, we have a potential answer, but we still need to check if reduce the max number of pages and still able to distribute the books among the students. Hence, do end = mid-1
 *
 * This check is done in the isValid function in the below code
 *
 * In the isValid function, initialize the student count with 1.
 * Then start from the beginning of the book array and do the sum of the pages.
 * The sum being done is the number of pages student 1 can read.
 * When sum becomes greater than max pages, it means we need student 2.
 * The moment we know student 2 is required, the current element that was added to the sum actually initializes the sum for student 2
 * Whenever the student count becomes greater than k, we know that max is not a valid option.
 *
 */
public class _19_AllocateMinimumNumberOfPAges {

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40};
        System.out.println(solve(arr, 2));
    }

    private static int solve(int[] arr, int k) {
        if (arr.length < k) {
            return -1;
        }

        int start = maxFromArray(arr);
        int end = sumOfArr(arr);
        int mid = 0, result = -1;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (isValid(arr, mid, k)) {
                result = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return result;
    }

    private static boolean isValid(int[] arr, int max, int k) {
        int students = 1;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum > max) {
                students++;
                sum = arr[i];
            }
            if (students > k) {
                return false;
            }
        }
        return true;
    }

    private static int sumOfArr(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return sum;
    }

    private static int maxFromArray(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            if (max < i) {
                max = i;
            }
        }
        return max;
    }
}
