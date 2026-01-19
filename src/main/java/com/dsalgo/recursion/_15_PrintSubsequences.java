package com.dsalgo.recursion;


import java.util.ArrayList;
import java.util.List;

public class _15_PrintSubsequences {

}

/*

Given input array - {3,1,2}
Print all the subsequences-
{3}
{3,1}
{3,1,2}
{1}
{1,2}
{2}
{}

Algorithm :

We can use input/output method.
Input - array
output - empty

 */
class _PrintAllSubsequences_Input_Output {

    private static void solve(List<Integer> input, int index, List<Integer> output) {

        if (index == input.size()) {
            System.out.println(output);
            return;
        } else {

            List<Integer> output_1 = new ArrayList<>(output);

            List<Integer> output_2 = new ArrayList<>(output);
            output_2.add(input.get(index));

            // remove first index from the input list
            // input.remove(0);
            // In _6AllSubsets, we modified the input by removing the first element.
            // here, we cannot remove the first element of the input list, because if we modify the input list, it gets modified for all the branches in the recursion tree.
            // List is pass by reference, hence all the branches of the recursion tree get the modified list.
            // In _6AllSubsets, we modified the string, as string is pass by value, all the branches did not get the modified input string
            // hence, we use index variable in this problem

            solve(input, index+1, output_1);
            solve(input, index+1, output_2);
        }

    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(1);
        list.add(2);
        solve(list, 0, new ArrayList<>());
    }

}



/*

Generate all sub-sequences, however, do not use input/output method.
Use a similar approach - pick / non-pick

Algorithm :

1. In the input/output method, we create 2 outputs - output_1 and output_2 by copying from output.
2. output_1 is same as output, and, output_2 is (output + element at index in input)
3. Then using both output_1 and output_2, we do recursion
4. Rather than creating 2 outputs, we will try to use only one output.
5. We create output_2 from output by doing  (output + element at index in input) and then we recurse for output_2
6. Then we want to recurse for output_1. However, we do not create output_1.
7. Rather, we remove last element from output_2, it is now similar to output_1 and then we recurse

Printing all sub-sequences is printing all paths, hence we can use recursion


 */

class _PrintAllSubsequences_Pick_NonPick {

    private static void solve(List<Integer> input, int index, List<Integer> output) {
        if (index == input.size()) {
            System.out.println(output);
            return;
        } else {
            output.add(input.get(index));
            solve(input, index + 1, output);

            output.remove(output.size() - 1);
            solve(input, index+1, output);
        }
    }

    /**
     * <p>Here is an image related to this class:</p>
     *  <img src="images/_PrintAllSubsequences_Pick_NonPick.png" alt="My Image Description" width="300"/>
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(1);
        list.add(2);
        solve(list, 0, new ArrayList<>());
    }

}

/*

From all the sub-sequences, if we wish to print the sub-sequences whose sum is equal to K,
it means we want to print few paths of all the paths.
Make code changes in the base case.
Allow the base case to be reached. It means we have processed all the indexes.
Then check if the current sum is equal to K.

void solve(index, k, sum, output) {

    if (base condition) {
        if (k == sum) {
            sysout(output)
        }
        return
    }
    else {
        output.add(arr[index]);
        sum = sum + arr[index]
        solve(index+1, k, sum, output)

        output.remove(output.size() - 1);
        sum = sum - arr[index]
        solve(index+1, k, sum, output)

    }

}

 */
class _PrintAllSubsequencesWithSumAsK_Pick_NonPick_ {

    private static void solve(List<Integer> input, int index, int k, int sum, List<Integer> output) {
        if (index == input.size()) {
            if (sum == k) {
                System.out.println(output);
            }
            return;
        } else {
            output.add(input.get(index));
            sum = sum + input.get(index);
            solve(input, index + 1, k, sum, output);

            output.remove(output.size() - 1);
            sum = sum - input.get(index);
            solve(input, index+1, k, sum, output);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(1);
        int k = 2;
        int sum = 0;
        solve(list, 0, k, sum, new ArrayList<>());
    }
}

/*

Now, let's say there were a total of 5 paths, out of which, there were 3 paths which had sum = k.
But we want to print only one of these 3 paths.

Make code changes in the base case.
When sum = k, return true, else return false.
From recursive calls, collect the boolean result.
If boolean is true, it means this recursive call gave the desired output.
Hence, return true after this recursive call and do not make any further recursive call.
Do this for both the recursive calls.

boolean solve(index, k, sum, output) {

    if (base condition) {
        if (k == sum) {
            return true
        } else {
            return false
        }
    }
    else {
        output.add(arr[index]);
        sum = sum + arr[index]
        boolean left = solve(index+1, k, sum, output)
        if (left) {
            return true
        }

        output.remove(output.size() - 1);
        sum = sum - arr[index]
        boolean right = solve(index+1, k, sum, output)
        if (right) {
            return true
        }

        return false;
    }

}

 */
class _PrintAllSubsequencesWithSumAsK_PrintOnlyOnePath_Pick_NonPick_ {

    private static boolean solve(List<Integer> input, int index, int k, int sum, List<Integer> output) {
        if (index == input.size()) {
            if (sum == k) {
                System.out.println(output);
                return true;
            }
            return false;
        } else {
            output.add(input.get(index));
            sum = sum + input.get(index);
            boolean res1 = solve(input, index + 1, k, sum, output);
            if (res1) {
                return true;
            }

            output.remove(output.size() - 1);
            sum = sum - input.get(index);
            boolean res2 = solve(input, index+1, k, sum, output);
            if (res2) {
                return true;
            }

            return false;
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(1);
        int k = 2;
        int sum = 0;
        solve(list, 0, k, sum, new ArrayList<>());
    }
}

/*

Now, rather than printing any of the path which sums to K, we want to count how many paths have the sum K.
Make changes in the base case.
When base case is reached and the condition is satisfied that sum=k, it is one of the path.
Hence, return 1, else return 0.

Collect the left and right recursive calls result in int.
Sum these up and return the sum.
Here, we do not need to maintain the output list as we do not want to print te output path.

int solve(index, k, sum) {

    if (base condition) {
        if (k == sum) {
            return 1
        } else {
            return 0
        }
    }
    else {
        sum = sum + arr[index]
        int left = solve(index+1, k, sum)

        sum = sum - arr[index]
        int right = solve(index+1, k, sum)

        return left + right;
    }

}

 */

class _CountAllSubsequencesWithSumAsK_Pick_NonPick_ {

    private static int solve(List<Integer> input, int index, int k, int sum) {
        if (index == input.size()) {
            if (sum == k) {
                return 1;
            }
            return 0;
        } else {
            sum = sum + input.get(index);
            int res1 = solve(input, index + 1, k, sum);

            sum = sum - input.get(index);
            int res2 = solve(input, index+1, k, sum);

            return res1 + res2;
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(1);
        int k = 2;
        int sum = 0;
        System.out.println(solve(list, 0, k, sum));
    }
}