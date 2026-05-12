package com.dsalgo.neetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class _2_CarFleet {
    public static int carFleet(int target, int[] position, int[] speed) {

        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < position.length; i++) {
            Position p = new Position(position[i], speed[i]);
            positions.add(p);
        }

        Collections.sort(positions, ((p1, p2) -> p2.position - p1.position));

        float[] time = new float[speed.length];

        for (int i = 0; i < positions.size(); i++) {
            time[i] = (float)(target - positions.get(i).position) / positions.get(i).speed;
        }



        List<Integer> result = new ArrayList<>();
        Stack<Float> stack = new Stack<>();
        int fleetCount = 0;

        for (int i = 0; i <= time.length-1; i++) {
            if (stack.empty()) {
                //result.add(-1);
                fleetCount++;
            } else if (!stack.isEmpty() && stack.peek() >= time[i]) {
                //result.add(stack.peek());
            } else if (!stack.isEmpty() && stack.peek() < time[i]) {
                //pop from stack till stack is not empty and stack top is less than current element
                while (!stack.isEmpty() && stack.peek() < time[i]) {
                    // the popped element will never be used in the answer.
                    // it is fine to pop the element because the current element arr[i] is greater than the popped element and can be used to other elements which are not yet processed.
                    stack.pop();
                }

                //stack can be empty after doing pop()
                if (stack.isEmpty()) {
                    fleetCount++;
                } else {
                    //result.add(stack.peek());
                }
            }

            // after processing every element and before moving to the previous element in the array to process, push the current element on stack
            stack.push(time[i]);
        }

        return fleetCount;

    }

    public static void main(String[] args) {
        int target=10;
        int[] position={6,8};
        int[] speed={3,2};

        carFleet(target, position, speed);
    }
}


class Position {
    int position;
    int speed;

    Position(int position, int speed) {
        this.position = position;
        this.speed = speed;
    }
}