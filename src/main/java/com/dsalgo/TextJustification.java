package com.dsalgo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        //a  computer.  Art is
        //enough to explain to
        //Science  is  what we

        int start = 0, end = 0;
        int lenWithSpace = 0;
        int lenWithoutSpace = 0;
        List<String> result = new ArrayList<>();
        boolean last = false;
        while (end < words.length) {
            lenWithSpace += words[end].length() + 1;
            lenWithoutSpace += words[end].length();
            last = false;
            if (lenWithSpace - 1 > maxWidth) {
                lenWithoutSpace -= words[end].length();
                end = end - 1;
                result.add(getLine(start, end, words, maxWidth, lenWithoutSpace));
                start = end + 1;
                last = true;
                lenWithSpace = 0;
                lenWithoutSpace = 0;
            } else if (lenWithSpace - 1 == maxWidth) {
                //end = end;
                result.add(getLine(start, end, words, maxWidth, lenWithoutSpace));
                start = end + 1;
                last = true;
                lenWithSpace = 0;
                lenWithoutSpace = 0;
            }
            end++;
        }
        if (!last && end == words.length) {
            result.add(getLine(start, end - 1, words, maxWidth, lenWithoutSpace));
        }
        return result;
    }

    private String getLine(int start, int end, String[] words, int maxWidth, int lenWithoutSpace) {
        boolean isLastLine = end == words.length - 1;
        String output = "";
        if (start == end) {
            output += words[start];
            //pad end
            output += getSpaces(maxWidth - output.length());
        } else if (isLastLine) {
            for (int i = start; i <= end; i++) {
                output = output + words[i];
                if (i < end) {
                    output += " ";
                }
            }
            //pad end
            output += getSpaces(maxWidth - output.length());
        } else {
            int slots = end - start;
            int availableSpaces = maxWidth - lenWithoutSpace;
            int spaces = 0;
            Stack<Integer> stack = new Stack<>();

            while (availableSpaces > 0) {
                spaces = availableSpaces / slots;
                stack.push(spaces);
                availableSpaces -= spaces;
                slots -= 1;
            }

            for (int i = start; i <= end; i++) {
                output = output + words[i];
                if (i < end) {
                    output += getSpaces(stack.pop());
                }
            }
        }
        return output;
    }

    private String getSpaces(int count) {
        String str = "";
        while (count > 0) {
            str += " ";
            count--;
        }
        return str;
    }

    public static void main(String[] args) {
        TextJustification tf = new TextJustification();
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        System.out.println(tf.fullJustify(words, 16));
    }
}
