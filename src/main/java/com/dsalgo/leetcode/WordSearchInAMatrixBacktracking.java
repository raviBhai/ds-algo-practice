package com.dsalgo.leetcode;

import java.util.ArrayList;
import java.util.List;

public class WordSearchInAMatrixBacktracking {

    public static final Integer[] x_moves = {1, 0, -1, 0};
    public static final Integer[] y_moves = {0, 1, 0, -1};

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (isFound(word, board)) {
                result.add(word);
            }
        }
        return result;
    }

    private boolean isFound(String word, char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (word.charAt(0) == board[i][j]) {
                    boolean[][] visited = new boolean[board.length][board[0].length];
                    visited[i][j] = true;
                    if (isFound(word, board, visited, i, j, 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isFound(String word, char[][] board, boolean[][] visited,
                            int from_x, int from_y, int index) {
        if (index == word.length()) {
            return true;
        } else {
            for (int i = 0; i < x_moves.length; i++) {
                int next_x = from_x + x_moves[i];
                int next_y = from_y + y_moves[i];
                if (isMatch(next_x, next_y, board, word, index, visited)) {
                    visited[next_x][next_y] = true;
                    if (isFound(word, board, visited, next_x, next_y, index + 1)) {
                        return true;
                    } else {
                        //backtrack
                        visited[next_x][next_y] = false;
                    }
                }
            }
            return false;
        }
    }

    private boolean isMatch(int r, int c, char[][] board, String word, int index, boolean[][] visited) {
        if (r < 0 || r >= board.length) {
            return false;
        }

        if (c < 0 || c >= board[0].length) {
            return false;
        }

        if (!visited[r][c] && board[r][c] == word.charAt(index)) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        WordSearchInAMatrixBacktracking problem = new WordSearchInAMatrixBacktracking();
        char[][] board =
                {
                        {'o','a','a','n'},
                        {'e','t','a','e'},
                        {'i','h','k','r'},
                        {'i','f','l','v'}
                };
        String [] words = {"oath","pea","eat","rain"};
        System.out.println(problem.findWords(board, words));

        char[][] board2 =
                {
                        {'a','b','c','e'},
                        {'x','x','c','d'},
                        {'x','x','b','a'}
                };
        String [] words2 = {"abc","abcd"};
        System.out.println(problem.findWords(board2, words2));

        char[][] board3 =
                {
                        {'a','b','c'},
                        {'a','e','d'},
                        {'a','f','g'}
                };
        String [] words3 = {"eaafgdcba"};
        System.out.println(problem.findWords(board3, words3));

        char[][] board4 =
                {
                        {'a','a'},
                        {'a','a'}

                };
        String [] words4 = {"aaaaa"};
        System.out.println(problem.findWords(board4, words4));
    }
}
