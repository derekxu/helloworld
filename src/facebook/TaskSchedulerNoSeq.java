package facebook;

import java.util.*;

/**
Leetcode 621
 */
public class TaskSchedulerNoSeq {

	public static void main(String[] args) {
		char[] tasks = "AAABBB".toCharArray();
		int n = 2;
		System.out.printf("Time: %d\n", new TaskSchedulerNoSeq().leastInterval1(tasks, n));
		List<String> res = new TaskSchedulerNoSeq().leastInterva2(tasks, n);
		for (String str : res) {
			System.out.printf("%s,", str);
		}
		System.out.println("");
	}

    public int leastInterval1(char[] tasks, int n) {
        int[] map = new int[26];
        for (char t : tasks) {
            map[t-'A']++;
        }
        Arrays.sort(map);
        int nSlots = (map[25]-1) * n;
        int max = map[25]-1;
        for (int i = 24; i >= 0 && map[i] > 0 && nSlots > 0; i--) {
            nSlots -= Math.min(max, map[i]);
            if (nSlots <= 0) return tasks.length;
        }
        return tasks.length + nSlots;
    }
 
    public List<String> leastInterva2(char[] tasks, int n) {
        int[] map = new int[26];
        for (char t : tasks) {
            map[t-'A']++;
        }
        int nTasks = tasks.length;
        Arrays.sort(map);
        List<String> res = new ArrayList<>();
        while (nTasks > 0) {
        	// For loop n, test cases
            for (int i = 0; i <= n; i++) {
                if (i <= 25 && map[25-i] > 0) {
                	res.add(String.valueOf((char)('A'+i)));
                    map[25-i]--;
                    nTasks--;
                    if (nTasks == 0) return res;
                } else {
                	res.add("idle");
                }
            }
            Arrays.sort(map);
        }
        return res;
    }
}
