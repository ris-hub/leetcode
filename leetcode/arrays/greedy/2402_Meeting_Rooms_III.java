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