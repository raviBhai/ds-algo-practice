package com.dsalgo.interviewbit.stacksAndQueues;

import java.util.*;

public class RelatedMovies {

    public static void printRelatedMovies(Movie movie) {
        Deque<Movie> queue = new LinkedList<>();
        queue.add(movie);
        Set<Movie> uniqueMovies = new HashSet<>();
        uniqueMovies.add(movie);
        while (!queue.isEmpty()) {
            Movie m2 = queue.remove();
            List<Movie> relatedMovies = m2.relatedMovies;
            if (relatedMovies != null && !relatedMovies.isEmpty()) {
                for (Movie movie1 : relatedMovies) {
                    if (!uniqueMovies.contains(movie1)) {
                        queue.add(movie1);
                        uniqueMovies.add(movie1);
                    }
                }
            }
        }
        System.out.println(uniqueMovies);
    }

    public static void main(String[] args) {
        Movie m1 = new Movie("1");
        Movie m2 = new Movie("2");
        Movie m3 = new Movie("3");
        Movie m4 = new Movie("4");
        Movie m5 = new Movie("5");
        Movie m6 = new Movie("6");
        Movie m7 = new Movie("7");
        Movie m8 = new Movie("8");
        Movie m9 = new Movie("9");
        Movie m10 = new Movie("10");

        m1.addRelatedMovie(m2);
        m1.addRelatedMovie(m3);
        //m1.addRelatedMovie(m4);

        m2.addRelatedMovie(m5);
        //m2.addRelatedMovie(m6);

        m3.addRelatedMovie(m7);

        //m4.addRelatedMovie(m8);
        //m4.addRelatedMovie(m9);

        m5.addRelatedMovie(m1);
        m7.addRelatedMovie(m1);

        printRelatedMovies(m1);

    }
}

class Movie {
    String name;
    List<Movie> relatedMovies;

    public Movie(String name) {
        this.name = name;
    }

    public void addRelatedMovie(Movie movie) {
        if (relatedMovies == null) {
            relatedMovies = new ArrayList<>();
        }
        relatedMovies.add(movie);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
