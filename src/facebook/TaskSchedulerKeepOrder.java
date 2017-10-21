package facebook;

import java.util.*;

/**
 * Keep order

给了一串task，不同的task可能属于不同type。这些task要放到CPU里运行，运行同一种type是要考虑一个冷却时间。。。
弄了半天，过了好几个例子才搞明白，就类似于一个OS。给你一个单线程的scheduler，和eg. 4种thread：1，2，3，4, 冷却时间: 3
在multithreading的时候同类型的thread要等上一个thread跑完冷却时间之后才能运行，求最后scheduler用了多少time slot。 
举个例子，thread: 1, 2, 1, 1, 3, 4; 冷却时间: 2 time slot，scheduler应该是这样的：1, 2, _, 1, _, _, 3, 4，最后返回8

NOTE: optimization.
 */
public class TaskSchedulerKeepOrder {

	public int schedule1(char[] tasks, int k) {
		int ts = 0;
		int[] lastCoolDown = new int[26];
		Arrays.fill(lastCoolDown, -1);
		for (char task : tasks) {
			if (ts <= lastCoolDown[task-'A']) {
				ts = lastCoolDown[task-'A']+1;
			}
			lastCoolDown[task-'A'] = ts+k;
			ts++;
		}
		return ts;
	}

	// Return sequence
	public char[] schedule2(char[] tasks, int k) {
		int ts = 0;
		int[] lastCoolDown = new int[26];
    	StringBuilder seq = new StringBuilder();
		Arrays.fill(lastCoolDown, -1);
		for (char task : tasks) {
			while (ts <= lastCoolDown[task-'A']) {
				seq.append('i');
				ts++;
			}
			seq.append(task);
			lastCoolDown[task-'A'] = ts+k;
			ts++;
		}
		return seq.toString().toCharArray();
	}
	
	// Reduce Space, e.g. LRU
	public char[] schedule3(char[] tasks, int k) {
		int ts = 0;
		Map<Character, Integer> lastCoolDown = new HashMap<>();
		Queue<Character> q = new LinkedList<>();
    	StringBuilder seq = new StringBuilder();
		for (char task : tasks) {
			while (lastCoolDown.containsKey(task) && ts <= lastCoolDown.get(task)) {
				seq.append('i');
				ts++;
			}
			// Case: A,B,C,D, k=2. head in q expires when ts >= 1;
			// Use k+1 or k
			if (q.size() == k+1) {
				lastCoolDown.remove(q.poll());
			}
			seq.append(task);
			lastCoolDown.put(task, ts+k);
			q.add(task);
			ts++;
		}
		return seq.toString().toCharArray();
	}

	public static void main(String[] args) {
		char[] tasks = {'A','B','A','A','C','D'};
		//char[] tasks = {'A','A','C','A'};
		int k = 2;
		TaskSchedulerKeepOrder instance = new TaskSchedulerKeepOrder();
		System.out.println(instance.schedule1(tasks, k));
		System.out.println(instance.schedule2(tasks, k));
		System.out.println(instance.schedule3(tasks, k));
	}
}
