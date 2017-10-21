package facebook;

import java.util.*;


/**
No keep sequence

Leetcode 621 + return list of string


Keep sequence

给了一串task，不同的task可能属于不同type。这些task要放到CPU里运行，运行同一种type是要考虑一个冷却时间。。。
弄了半天，过了好几个例子才搞明白，就类似于一个OS。给你一个单线程的scheduler，和eg. 4种thread：1，2，3，4, 冷却时间: 3
在multithreading的时候同类型的thread要等上一个thread跑完冷却时间之后才能运行，求最后scheduler用了多少time slot。 
举个例子，thread: 1, 2, 1, 1, 3, 4; 冷却时间: 2 time slot，scheduler应该是这样的：1, 2, _, 1, _, _, 3, 4，最后返回8

NOTE: optimization.
 */
public class TaskSchedulerWithCoolDown {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] tasks = {1, 2, 1, 1, 3, 4};
		int cooldown = 2;
		// Keep order
		int minTime = taskSchedule1(tasks, cooldown);
		System.out.println(minTime);
		
		String schedule = taskSchedule2(tasks, cooldown);
		System.out.println(schedule);
		System.out.println(taskSchedule5(tasks, cooldown));
		
		// Can reoder
		System.out.println(taskSchedule3(tasks, cooldown));
		System.out.println(taskSchedule4(tasks, cooldown));
	}
	
	// keep order, output number
	public static int taskSchedule1(int[] tasks, int k) {
		if(tasks == null || tasks.length == 0) {
			return 0;
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int slots = 0;
		for(int task : tasks) {
			if(map.containsKey(task) && map.get(task) > slots) {
				slots = map.get(task);
			}
			map.put(task, slots + k + 1);
			slots++;
		}
		return slots;
	}
	
	// Keep order, output scheduler
	public static String taskSchedule2(int[] tasks, int k) {
		if(tasks == null || tasks.length == 0) {
			return "";
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int slots = 0;
		StringBuilder sb = new StringBuilder();
		for(int task : tasks) {
			if(map.containsKey(task) && map.get(task) > slots) {
				int waitTime = map.get(task) - slots;
				for(int i = 0; i < waitTime; ++i) {
					sb.append("_");
				}
			}
			map.put(task, slots + k + 1);
			sb.append(task);
			slots++;
		}
		return sb.toString();
	}
	
	// reorder, output time
	public static int taskSchedule3(int[] tasks, int k) {
		if(tasks == null || tasks.length == 0) {
			return 0;
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int mostFreq = 0;
		for(int task : tasks) {
			if(!map.containsKey(task)) {
				map.put(task, 1);
			} else {
				map.put(task, map.get(task) + 1);
			}
			mostFreq = Math.max(mostFreq, map.get(task));
		}
		int mostCommon = 0;
		for(int task : map.keySet()) {
			if(map.get(task) == mostFreq) {
				mostCommon++;
			}
		}
		return Math.max(tasks.length, (mostFreq - 1) * (k + 1) + mostCommon);
	}
	
	// reorder, output schedule
	public static String taskSchedule4(int[] tasks, int k) {
		if(tasks == null || tasks.length == 0) {
			return "";
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int mostFreq = 0;
		for(int task : tasks) {
			if(!map.containsKey(task)) {
				map.put(task, 1);
			} else {
				map.put(task, map.get(task) + 1);
			}
			mostFreq = Math.max(mostFreq, map.get(task));
		}
		List<Integer> mostTasks = new ArrayList<Integer>();
		List<Integer> unvisited = new ArrayList<Integer>();
		for(int task : map.keySet()) {
			if(map.get(task) == mostFreq) {
				mostTasks.add(task);
			} else {
				unvisited.add(task);
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < mostFreq - 1; ++i) {
			for(int task : mostTasks) {
				sb.append(task);
			}
			for(int j = 0; j < Math.max(0, k + 1 - mostTasks.size()); ++j) {
				sb.append("_");
			}
		}
		for(int task : mostTasks) {
			sb.append(task);
		}
		int i = 0, j = 0;
		for( ; i < sb.length(); ++i) {
			if(j >= unvisited.size()) {
				break;
			}
			if(sb.charAt(i) == '_') {
				sb.setCharAt(i, (char)(unvisited.get(j++) + '0'));
			}
		}
		while(j < unvisited.size()) {
			sb.append(unvisited.get(j++));
		}
		return sb.toString();
	}
	
	// reduce space, Keep order
	public static String taskSchedule5(int[] tasks, int k) {
		if(tasks == null || tasks.length == 0) {
			return "";
		}
		LinkedList<Integer> q = new LinkedList<Integer>();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int slots = 0;
		StringBuilder sb = new StringBuilder();
		for(int task : tasks) {
			if(map.containsKey(task) && map.get(task) > slots) {
				int waitTime = map.get(task) - slots;
				for(int i = 0; i < waitTime; ++i) {
					sb.append("_");
				}
				slots = map.get(task);
			}
			if(q.size() == k + 1) {
				map.remove(q.poll());
			}
			map.put(task, slots + k + 1);
			sb.append(task);
			q.offer(task);
			slots++;
		}
		return sb.toString();
	}

}
