package leetcode.graph;

import java.util.*;

/*
LeetCode 2092 - Find All People With Secret
Difficulty: Hard
Link: https://leetcode.com/problems/find-all-people-with-secret/

Test Case:
Example 1:

Input: n = 6, meetings = [[1,2,5],[2,3,8],[1,5,10]], firstPerson = 1
Output: [0,1,2,3,5]
Explanation:
At time 0, person 0 shares the secret with person 1.
At time 5, person 1 shares the secret with person 2.
At time 8, person 2 shares the secret with person 3.
At time 10, person 1 shares the secret with person 5.​​​​
Thus, people 0, 1, 2, 3, and 5 know the secret after all the meetings.

Approach:
- Group all meetings by their time in increasing order.
- Track which people know the secret using a boolean array, initially marking person 0 and firstPerson as knowing the secret.
- For each time, build a graph of people who meet at that time.
- Start BFS from people who already know the secret at that time, spreading the secret to others in the same time group.
- After processing all meetings, return the list of people who know the secret.

Time Complexity: ~O(M*(M+N)) where M = number of meetings and N = number of people
Space Complexity: O(M+N)

Tags:
Revisit: Yes
*/

class Solution {
    // Implement the required method here
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        // Group Meetings in increasing order of time
        Map<Integer, List<int[]>> timeMeetings = new TreeMap<>();
        for(int []m: meetings){
            int p1 = m[0];
            int p2 = m[1];
            int time = m[2];

            timeMeetings.computeIfAbsent(time, k->new ArrayList<>()).add(new int[]{p1, p2});
        }

        // Boolean Array to mark if a person knows the secret or not
        boolean[] knowsSecret = new boolean[n];
        knowsSecret[0] = true;
        knowsSecret[firstPerson] = true;

        // Process in increasing order of time
        for(int t: timeMeetings.keySet()){
            // For each person, save all the people whom he/she meets at time t
            Map<Integer, List<Integer>> meet = new HashMap<>();
            for(int []m: timeMeetings.get(t)){
                int x=m[0], y=m[1];
                meet.computeIfAbsent(x, k-> new ArrayList<>()).add(y);
                meet.computeIfAbsent(y, k-> new ArrayList<>()).add(x);
            }

            // Start traversal from those who already know the secret at time t
            // Set to avoid redundancy
            Set<Integer> set = new HashSet<>();
            for(int []m: timeMeetings.get(t)){
                int x=m[0], y=m[1];
                if(knowsSecret[x])
                    set.add(x);
                if(knowsSecret[y])
                    set.add(y);
            }

            // Do BFS
            Queue<Integer> q = new LinkedList<>(set);
            while (!q.isEmpty()) {
                int person = q.poll();
                for (int nextPerson : meet.getOrDefault(person, new ArrayList<>())) {
                    if (!knowsSecret[nextPerson]) {
                        knowsSecret[nextPerson] = true;
                        q.offer(nextPerson);
                    }
                }
            }
        }       

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (knowsSecret[i]) {
                res.add(i);
            }
        }
        return res;
    }
}
