package com.dsalgo.interviewbit.strings;

import java.util.ArrayList;

public class Atoi {
    public static void main(String[] args) {
        Atoi atoi = new Atoi();
        System.out.println(atoi.atoi("- 123"));
    }

    public int atoi(final String A) {
        int output = 0;
        long tempOutput = 0;
        ArrayList<Character> tempList = new ArrayList<>();
        boolean isIntegerEncountered = false;
        boolean isPlusEncountered = false;
        boolean isMinusEncountered = false;
        boolean isSignEncountered = false;
        char ch;
        int temp;
        //2147483647


        for (int i = 0; i < A.length(); i++) {
            ch = A.charAt(i);
            if (isSignEncountered && ch == ' ') {
                break;
            } else if (isIntegerEncountered) {
                temp = ch - '0';
                if (temp >= 0 && temp <= 9) {
                    tempList.add(ch);
                } else {
                    break;
                }
            } else {
                if (ch == ' ') {
                    continue;
                } else {
                    if (ch == '+' && !isPlusEncountered && !isSignEncountered) {
                        isPlusEncountered = true;
                        isSignEncountered = true;
                        continue;
                    } else if (ch == '-' && !isMinusEncountered && !isSignEncountered) {
                        isMinusEncountered = true;
                        isSignEncountered = true;
                        continue;
                    }
                    temp = ch - '0';
                    if (temp >= 0 && temp <= 9) {
                        tempList.add(ch);
                        isIntegerEncountered = true;
                        continue;
                    } else {
                        break;
                    }
                }
            }
        }

        if (tempList.isEmpty()) {
            return 0;
        }
        //System.out.println(tempList);


        for (Character character : tempList) {
            temp = character - '0';
            if (tempOutput < 0) {
                tempOutput = tempOutput * -1;
            }
            tempOutput = tempOutput * 10 + temp;
            if (isMinusEncountered) {
                tempOutput = tempOutput * -1;
            }
            if (tempOutput > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else if (tempOutput < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }

        output = (int) tempOutput;
        return output;
    }
}
