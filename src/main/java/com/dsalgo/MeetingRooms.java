package com.dsalgo;

import java.util.*;

public class MeetingRooms {

    public int mostBooked(int n, int[][] meetings) {

        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Room r = new Room(i);
            rooms.add(r);
        }

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


        List<Meeting> meetingList = new ArrayList<>();
        for (int i = 0; i < meetings.length; i++) {
            Meeting m = new Meeting(meetings[i][0], meetings[i][1]);
            meetingList.add(m);
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


        Collections.sort(rooms, new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                if (o1.count == o2.count) {
                    return o1.index - o2.index;
                }
                return o1.count - o2.count;
            }
        });

        return rooms.get(0).index;

       /* PriorityQueue<Room> maxCountRooms = new PriorityQueue<>(
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

        return maxCountRooms.poll().index;*/

    }

    public static void main(String[] args) {
        MeetingRooms meetingRooms = new MeetingRooms();
        int[][] meetings = {
                {0,10},
                {1,5},
                {2,7},
                {3,4}

        };
        System.out.println(meetingRooms.mostBooked(2, meetings));
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