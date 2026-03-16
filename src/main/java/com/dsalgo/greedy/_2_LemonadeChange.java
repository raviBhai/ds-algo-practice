package com.dsalgo.greedy;

/*

You have a lemonade stand and each lemonade has a price of Rs.5
Initially you do not have any money. You just have lemonades.

Customers can come to your stand with currency notes which has values as Rs.5, Rs.10 or Rs.20

If someone comes with Rs.5 , you can sell them the lemonade.

If someone comes with Rs.10 , you can sell them the lemonade but need to return Rs.5 back

If someone comes with Rs.20 , you can sell them the lemonade but need to return Rs.15 back
Rs.15 can be returned as (Rs.10 + Rs.5) or (Rs.5 + Rs.5 + Rs.5)

Given will be an array which denotes the customers coming up to your stand.
Each entry in the array denotes the currency amount the customer carries.

You need to tell whether you can sell lemonades to all the customers.

Examples -
Input_1 - { 5, 5, 5, 10, 20 }

Let's track number of 5,10,20 Rs currency notes with us after we sell the lemonades.
numberOfNotesWithRs_5 - 0
numberOfNotesWithRs_10 - 0
numberOfNotesWithRs_20 - 0



customer_0 - has Rs.5
you can sell the lemonade, and keep Rs.5
numberOfNotesWithRs_5 - 1
numberOfNotesWithRs_10 - 0
numberOfNotesWithRs_20 - 0

customer_1 - has Rs.5
you can sell the lemonade, and keep Rs.5
numberOfNotesWithRs_5 - 2
numberOfNotesWithRs_10 - 0
numberOfNotesWithRs_20 - 0

customer_2 - has Rs.5
you can sell the lemonade, and keep Rs.5
numberOfNotesWithRs_5 - 3
numberOfNotesWithRs_10 - 0
numberOfNotesWithRs_20 - 0

customer_3 - has Rs.10
you can sell the lemonade, keep Rs.10 and need to return Rs.5
numberOfNotesWithRs_5 - 2
numberOfNotesWithRs_10 - 1
numberOfNotesWithRs_20 - 0

customer_4 - has Rs.20
you can sell the lemonade, keep Rs.20 and need to return Rs.15
Return- 10 + 5
numberOfNotesWithRs_5 - 1
numberOfNotesWithRs_10 - 0
numberOfNotesWithRs_20 - 1

We can return true as we are able to sell the lemonades to all the customers.

What if one more customer comes with Rs.20.
If we take Rs.20, we need to return Rs.15, and we do not have the currency notes that can make us return Rs.15
Hence, we will not be able to sell to this customer
 */
public class _2_LemonadeChange {

    private static boolean solve(int[] customers) {
        int numberOfNotesWithRs_5 = 0;
        int numberOfNotesWithRs_10 = 0;
        int numberOfNotesWithRs_20 = 0;

        for (int i = 0; i < customers.length; i++) {
            if (customers[i] == 5) {
                numberOfNotesWithRs_5++;
            } else if (customers[i] == 10) {
                if (numberOfNotesWithRs_5 >= 1) {
                    // take 10 and return 5
                    numberOfNotesWithRs_10++;
                    numberOfNotesWithRs_5--;
                } else {
                    return false;
                }
            } else if (customers[i] == 20) {
                // take 20 and return 15 (10+5)
                if (numberOfNotesWithRs_5 >= 1 && numberOfNotesWithRs_10 >= 1) {
                    numberOfNotesWithRs_20++;
                    numberOfNotesWithRs_10--;
                    numberOfNotesWithRs_5--;
                } else if (numberOfNotesWithRs_5 >= 3) { // take 20 and return 15 (5+5+5)
                    numberOfNotesWithRs_20++;
                    numberOfNotesWithRs_5 = -3;
                } else {
                    return false;
                }
            }
        }

        // able to iterate over all the customers, which means able to sell to all the customers, hence return true
        return true;
    }

    public static void main(String[] args) {
        int[] input1 = {5, 5, 5, 10, 20};
        System.out.println(solve(input1));

        int[] input2 = {5, 5, 5, 10, 20, 20};
        System.out.println(solve(input2));
    }

}
