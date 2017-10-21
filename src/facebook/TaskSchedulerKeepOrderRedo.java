package facebook;

import java.util.*;

public class TaskSchedulerKeepOrderRedo {
	public int schedule1(char[] tasks, int k) {
		int[] map = new int[26];
		Arrays.fill(map, -1);
		int ts = 0;
		for (char t : tasks) {
			if (ts <= map[t-'A']) {
				ts = map[t-'A']+1;
			}
			map[t-'A'] = ts+k;
			ts++;
		}
		return ts;
	}
	public int schedule3(char[] tasks, int k) {
		Map<Character, Integer> map = new HashMap<>();
		Queue<Character> q = new LinkedList<>();
		int ts = 0;
		for (char t : tasks) {
			int cool = map.getOrDefault(t, -1);
			if (ts <= cool) {
				ts = cool+1;
			}
			if (q.size() == k) {
				map.remove(q.poll());
			}
			map.put(t, ts+k);
			q.add(t);
			ts++;
		}
		return ts;
	}

	public static void main(String[] args) {
		char[] tasks = {'A','B','A','A','C','D'};
		//char[] tasks = {'A','A','C','A'};
		int k = 2;
		TaskSchedulerKeepOrderRedo instance = new TaskSchedulerKeepOrderRedo();
		System.out.println(instance.schedule1(tasks, k));
		//System.out.println(instance.schedule2(tasks, k));
		System.out.println(instance.schedule3(tasks, k));
	}

}
