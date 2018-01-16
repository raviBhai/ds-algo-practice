package com.dsalgo.onlinetests;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CountHoursForTT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int noOfPlayers = scanner.nextInt();

        Map<Integer, Integer> playersAvailableForEachHour = initializeForTheDay(9, 18);
        int singlePlayingHours = 0;
        int doublePlayingHours = 0;
        for (int i = 0; i < noOfPlayers; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();

            for (int hour = start; hour < end; hour++) {
                Integer playingHours = playersAvailableForEachHour.get(hour) + 1;
                playersAvailableForEachHour.put(hour, playingHours);
            }
        }

        for (Map.Entry<Integer, Integer> entry : playersAvailableForEachHour.entrySet()) {
            int playingHours = entry.getValue();
            if (playingHours > 3) {
                doublePlayingHours++;
            } else if (playingHours == 2 || playingHours == 3) {
                singlePlayingHours++;
            }
        }

        System.out.print(singlePlayingHours);
        System.out.print(" ");
        System.out.print(doublePlayingHours);
    }

    private static Map<Integer, Integer> initializeForTheDay(int start, int end) {
        Map<Integer, Integer> playersAvailableForEachHour = new HashMap<>();
        for (int i = start; i < end; i++) {
            playersAvailableForEachHour.put(i, 0);
        }
        return playersAvailableForEachHour;
    }
}
