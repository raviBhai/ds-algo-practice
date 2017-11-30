package com.dsalgo.hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RansomNote {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();

        if (m < n) {
            System.out.println("No");
            System.exit(0);
        }
        String magazine[] = new String[m];
        for(int magazine_i=0; magazine_i < m; magazine_i++){
            magazine[magazine_i] = in.next();
        }
        String ransom[] = new String[n];
        for(int ransom_i=0; ransom_i < n; ransom_i++){
            ransom[ransom_i] = in.next();
        }
        System.out.println(canReplicateRansomNote(magazine, ransom));
    }

    public static String canReplicateRansomNote(String[] magazine, String[] ransom) {
        Map<String, Integer> magazineMap = new HashMap<String, Integer>();

        for (String s : magazine) {
            if (magazineMap.get(s) != null){
                magazineMap.put(s, magazineMap.get(s) + 1);
            } else {
                magazineMap.put(s, 1);
            }
        }

        for (String s : ransom) {
            if (magazineMap.get(s) == null || magazineMap.get(s) == 0) {
                System.out.println("No");
                System.exit(0);
            }
            magazineMap.put(s, magazineMap.get(s) - 1);
        }

        return "Yes";
    }
}
