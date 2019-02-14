package com.dsalgo.others;

import java.util.*;

public class DesignElevatorSystem {
}

interface Command {
    void execute();
}

class GoUpCommand implements Command {
    Elevator elevator;
    int destination;

    GoUpCommand(Elevator elevator, int destination) {
        this.elevator = elevator;
        this.destination = destination;
    }

    @Override
    public void execute() {
        elevator.addDestination(destination);
        elevator.move();
    }
}

class GoDownCommand implements Command {
    Elevator elevator;
    int destination;

    GoDownCommand(Elevator elevator, int destination) {
        this.elevator = elevator;
        this.destination = destination;
    }

    @Override
    public void execute() {
        elevator.addDestination(destination);
        elevator.move();
    }
}

class Elevator {
    State state;
    Direction direction;
    int currentState;

    PriorityQueue<Integer> up = new PriorityQueue<>();
    PriorityQueue<Integer> down = new PriorityQueue<>(Comparator.reverseOrder());

    void addDestination(int destination) {
        if (destination < currentState) {
            down.add(destination);
        } else if (destination > currentState) {
            up.add(destination);
        }
    }

    void move() {
        this.state = State.MOVING;
        if (direction == Direction.UP) {
            moveToAllUps();
            moveToAllDowns();
        } else if (direction == Direction.DOWN) {
            moveToAllDowns();
            moveToAllUps();
        }
    }

    private void moveToAllDowns() {
        while (!down.isEmpty()) {
            Integer first = down.remove();
            for (int i = currentState - 1; i >= first; i--) {
                System.out.println("Moving from " + currentState + " to " + i);
                this.currentState = i;
            }
        }
    }

    private void moveToAllUps() {
        while (!up.isEmpty()) {
            Integer first = up.remove();
            for (int i = currentState + 1; i <= first; i++) {
                System.out.println("Moving from " + currentState + " to " + i);
                this.currentState = i;
            }
        }
    }

    void moveTo(int destination) {
        addDestination(destination);
        if (direction == Direction.UP && !up.isEmpty()) {
            while (!up.isEmpty()) {
                Integer first = up.remove();
                for (int i = currentState + 1; i <= first; i++) {
                    System.out.println("Moving from " + currentState + " to " + i);
                    this.currentState = i;
                }
                if (first == destination) {
                    break;
                }
            }
        } else {
            while (!down.isEmpty()) {
                Integer first = down.remove();
                for (int i = currentState - 1; i >= first; i--) {
                    System.out.println("Moving from " + currentState + " to " + i);
                    this.currentState = i;
                }
                if (first == destination) {
                    break;
                }
            }
        }
    }
}

class ElevatorSystem {
    List<Elevator> elevators;

    void goUp(int from, int to) {
        Elevator elevator = getElevatorToGoUpFromLevel(from);
        elevator.moveTo(from);
        elevator.state = State.STOP;
        Command up = new GoUpCommand(elevator, to);
        up.execute();
    }

    void goDown(int from, int to) {
        Elevator elevator = getElevatorToGoDownFromLevel(from);
        elevator.moveTo(from);
        elevator.state = State.STOP;
        Command down = new GoDownCommand(elevator, to);
        down.execute();
    }

    Elevator getElevatorToGoUpFromLevel(int level) {
        return null;
    }

    Elevator getElevatorToGoDownFromLevel(int level) {
        return null;
    }

}

enum State {
    MOVING, STOP
}

enum Direction {
    UP, DOWN
}