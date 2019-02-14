package com.dsalgo.others;

public class PetrolPump {

    public static void main(String[] args) {
        PetrolPump pp = new PetrolPump();
        System.out.println(pp.getMin());
    }

    int getMin() {

        //int[] gas = {1,2,3,4,5,1,8};
        int[] gas = {6,3,7};
        //int[] gasConsumed = {3,4,5,1,2,8,1};
        int[] gasConsumed = {4,6,3};
        int[] deficit = new int[gas.length];//can you do without this ?
        int sum = 0 ;
        int sumTillHere = 0;
        int potentialStart = -1;

        for(int i = 0; i < gas.length; i++){
            deficit[i] = gas[i] - gasConsumed[i];
            sum += deficit[i];

            if(potentialStart == -1 && deficit[i] >=0) {
                potentialStart = i;
            }


                sumTillHere += deficit[i];
                if(sumTillHere <  0) {
                    potentialStart = -1;
                }


        }

        if(sum < 0) {
            return -1;
        } else {
            return potentialStart;
        }


    }
}
