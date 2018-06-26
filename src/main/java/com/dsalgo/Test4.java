package com.dsalgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test4 {
    public static void main(String[] args) {
        String keywords = "city pool food";
        int[] hotelIds = {1, 2, 1, 1, 2};
        String[] reviews = {
                "city is good. food is food!",              //1
                "city is good. food is food! pool also",    //2
                "pool.",                                    //1
                "city and food.",                           //1
                "pool, city and food."                      //2
        };
        System.out.println(Arrays.toString(sort_hotels(keywords, hotelIds, reviews)));
    }

    static int[] sort_hotels(String keywords, int[] hotel_ids, String[] reviews) {
        /*
         * Write your code here.
         */
        Map<Integer, List<String>> hotelIdsReviewsMap = new HashMap<>();
        for (int i = 0; i < hotel_ids.length; i++) {
            if (hotelIdsReviewsMap.get(hotel_ids[i]) == null) {
                List<String> reviewList = new ArrayList<>();
                reviewList.add(reviews[i]);
                hotelIdsReviewsMap.put(hotel_ids[i], reviewList);
            } else {
                List<String> reviewList = hotelIdsReviewsMap.get(hotel_ids[i]);
                reviewList.add(reviews[i]);
                hotelIdsReviewsMap.put(hotel_ids[i], reviewList);
            }
        }


        Map<Integer, Integer> hotelReviewScore = new HashMap<>();
        String[] keywordArr = keywords.split(" ");
        for (Map.Entry<Integer, List<String>> entry : hotelIdsReviewsMap.entrySet()) {
            int hotelId = entry.getKey();
            List<String> hotelReviews = entry.getValue();
            int reviewScore = 0;

            //do some opr to calculate reivew score
            for (String review : hotelReviews) {
                String[] reviewArr = review.split(" ");
                for (int i = 0 ; i < reviewArr.length; i++) {
                    reviewArr[i] = reviewArr[i].replaceAll("\\.", "");
                    reviewArr[i] = reviewArr[i].replaceAll(",", "");
                    reviewArr[i] = reviewArr[i].replaceAll("!", "");
                }

                for (int j = 0 ; j < keywordArr.length; j++) {
                    for (int k = 0; k < reviewArr.length; k++) {
                        if (reviewArr[k].equalsIgnoreCase(keywordArr[j])) {
                            reviewScore++;
                        }
                    }
                }
            }

            hotelReviewScore.put(hotelId, reviewScore);
        }

        Set<Map.Entry<Integer, Integer>> set = hotelReviewScore.entrySet();
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(set);

        Collections.sort( list, new Comparator<Map.Entry<Integer,Integer>>() {
            public int compare( Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2 )
            {
                if (o1.getValue().compareTo(o2.getValue()) == 0) {
                    return (o1.getKey()).compareTo( o2.getKey() );
                } else if (o1.getValue().compareTo(o2.getValue()) == -1) {
                    return 1;
                } else if (o1.getValue().compareTo(o2.getValue()) == 1) {
                    return -1;
                }
                return 0;
            }
        } );

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i).getKey();
        }
        return res;
    }
}
