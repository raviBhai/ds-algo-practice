package com.dsalgo;

import java.util.*;

public class MeetingRooms {

    public int mostBooked(int n, int[][] meetings) {
        List<Meeting> meetingList = new ArrayList<>();
        for (int i = 0; i < meetings.length; i++) {
            Meeting m = new Meeting(meetings[i][0], meetings[i][1]);
            meetingList.add(m);
        }

        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Room r = new Room(i);
            rooms.add(r);
        }

        Collections.sort(meetingList, new Comparator<Meeting>() {
            public int compare(Meeting m1, Meeting m2) {
                return m1.start - m2.start;
            }
        });

        PriorityQueue<Room> busyRooms = new PriorityQueue<Room>(
                new Comparator<Room>() {
                    public int compare(Room r1, Room r2) {
                        if (r1.busyTill == r2.busyTill) {
                            return r1.index - r2.index;
                        } else {
                            return r1.busyTill - r2.busyTill;
                        }
                    }
                }
        );

        PriorityQueue<Room> availableRooms = new PriorityQueue<>(
                new Comparator<Room>() {
                    public int compare(Room r1, Room r2) {
                        return r1.index - r2.index;
                    }
                }
        );

        for (Room r : rooms) {
            availableRooms.offer(r);
        }

        for (Meeting m : meetingList) {

            while (!busyRooms.isEmpty()) {
                Room br = busyRooms.poll();
                if (br.busyTill <= m.start) {
                    availableRooms.offer(br);
                } else if (availableRooms.isEmpty()) {
                    availableRooms.offer(br);
                } else {
                    busyRooms.offer(br);
                    break;
                }
            }
            Room ar = availableRooms.poll();
            ar.busyTill = Math.max(m.end - m.start + ar.busyTill, m.end);
            ar.count++;
            busyRooms.offer(ar);
        }

        PriorityQueue<Room> maxCountRooms = new PriorityQueue<>(
                new Comparator<Room>() {
                    public int compare(Room r1, Room r2) {
                        return r1.index - r2.index;
                    }
                }
        );



        int maxCount = Integer.MIN_VALUE;
        List<Room> maxCountRoomsList = null;
        for (Room r : rooms) {
            if (maxCount < r.count) {
                maxCount = r.count;
                maxCountRoomsList = new ArrayList<>();
                maxCountRoomsList.add(r);
            } else if (maxCount == r.count) {
                maxCountRoomsList.add(r);
            }
        }

        for (Room r : maxCountRoomsList) {
            maxCountRooms.offer(r);
        }

        return maxCountRooms.poll().index;

    }

    public static void main(String[] args) {
        MeetingRooms meetingRooms = new MeetingRooms();
        int[][] meetings = {
                {1,27},
                {29,49},
                {47,49},
                {41,43},
                {15,36},
                {11,15}
        };
        System.out.println(meetingRooms.mostBooked(3, meetings));
    }


}


class Meeting {
    int start;
    int end;

    Meeting (int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Room {
    int busyTill;
    int count;
    int index;

    Room(int index) {
        this.index = index;
        this.busyTill = busyTill;
    }
}