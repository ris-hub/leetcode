package leetcode.arrays.greedy;

/*
LeetCode 2402 - Meeting Rooms III
Difficulty: Hard
Link: https://leetcode.com/problems/meeting-rooms-iii/

Approach:
- Sort the meetings by their start time.
- Track for each room: the number of times it is used and the time it becomes available.
- For each meeting, try to assign it to the first available room (with the smallest index) that is free at the meeting's start time.
- If no room is available, assign the meeting to the room that becomes available the earliest, and delay the meeting to start when that room is free.
- After all meetings, return the room used the most times (smallest index in case of a tie).

T.C : O(mlogm +m*n) , where Let n = number of rooms, m =  number of meetings
S.C : O(n)

Tags: Greedy, Arrays, Simulation, Sorting
Revisit: No
*/
class Solution {
   public int mostBooked(int n, int[][] meetings) {
        // sort by starting time of meetings
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        // Each room is used 0 times in the beginning
        int[] roomsUsedCount = new int[n];
        // Each room will be last available at
        long[] lastAvailableAt = new long[n];

        for (int[] meet : meetings) {
            int start = meet[0];
            int end = meet[1];
            boolean found = false;

            long earlyEndRoomTime = Long.MAX_VALUE;
            int earlyEndRoom = 0;

            // Find the first available meeting room
            for (int room = 0; room < n; room++) {
                if (lastAvailableAt[room] <= start) {
                    found = true;
                    lastAvailableAt[room] = end;
                    roomsUsedCount[room]++;
                    break;
                }

                if (lastAvailableAt[room] < earlyEndRoomTime) {
                    earlyEndRoom = room;
                    earlyEndRoomTime = lastAvailableAt[room];
                }
            }

            if (!found) {
                lastAvailableAt[earlyEndRoom] += (end - start);
                roomsUsedCount[earlyEndRoom]++;
            }
        }

        int resultRoom = -1;
        int maxUse = 0;
        for (int room = 0; room < n; room++) {
            if (roomsUsedCount[room] > maxUse) {
                maxUse = roomsUsedCount[room];
                resultRoom = room;
            }
        }

        return resultRoom;
    }
}

// Approach II with Priority Queue:
// - Sort the meetings by their start time.
// - Track for each room: the number of times it is used and the time it becomes available.
// - For each meeting, try to assign it to the first available room (with the smallest index) that is free at the meeting's start time.
// - If no room is available, assign the meeting to the room that becomes available the earliest, and delay the meeting to start when that room is free.
// - After all meetings, return the room used the most times (smallest index in case of a tie).

// T.C : O(mlogm + mlogn) , where Let n = number of rooms, m =  number of meetings
// S.C : O(n)

class Solution {
    public int mostBooked(int n, int[][] meetings) {
        // sort by starting time of meetings
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        //room used count
        int []roomsUsedCount = new int[n];
        
        //making a min-heap with end time 
        PriorityQueue<long[]> usedRooms = new PriorityQueue<>((x,y)->(x[0]!=y[0]?Long.compare(x[0], y[0]):Long.compare(x[1], y[1])));

        //min-heap to store the room with smallest index on top
        PriorityQueue<Integer> availableRooms = new PriorityQueue<>();

        for(int room=0;room<n;room++)
            availableRooms.add(room);

        for(int []m: meetings){
            int start = m[0];
            int end =  m[1];

            //checking the current usedRoom if going to empty currently for start
            while(!usedRooms.isEmpty()){
                long []p = usedRooms.peek();
                if(p[0]<=start){
                    availableRooms.add((int)p[1]);
                    usedRooms.poll();
                }
                else
                    break;
            }

            if(!availableRooms.isEmpty()){
                int room=availableRooms.poll();
                usedRooms.add(new long[]{end, room});
                roomsUsedCount[room]++;
            } else{ // we don't have any rooms available - so pick the room which is going to finish the earliest     
                if(!usedRooms.isEmpty()){
                    long []p = usedRooms.poll();
                    usedRooms.add(new long[]{p[0]+(end-start), p[1]});
                    roomsUsedCount[(int)p[1]]++;
                }    
            }
        }

        int resultRoom = -1;
        int maxUse = 0;
        for (int room = 0; room < n; room++) {
            if (roomsUsedCount[room] > maxUse) {
                maxUse = roomsUsedCount[room];
                resultRoom = room;
            }
        }

        return resultRoom;
    }
}