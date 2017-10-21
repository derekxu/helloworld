package facebook;

import java.util.*;

/**
给了一串task，不同的task可能属于不同type。这些task要放到CPU里运行，运行同一种type是要考虑一个冷却时间。。。
弄了半天，过了好几个例子才搞明白，就类似于一个OS。给你一个单线程的scheduler，和eg. 4种thread：1，2，3，4, 冷却时间: 3
在multithreading的时候同类型的thread要等上一个thread跑完冷却时间之后才能运行，求最后scheduler用了多少time slot。 
举个例子，thread: 1, 2, 1, 1, 3, 4; 冷却时间: 2 time slot，scheduler应该是这样的：1, 2, _, 1, _, _, 3, 4，最后返回8

NOTE: optimization.
 */
public class TaskSchedulerSequence {

	public static void main(String[] args) {
		List<String> tasks = Arrays.asList("1","2","1","1","2","3");
		//List<String> tasks = Arrays.asList("1","2","3","1","2","3");
		//List<String> tasks = Arrays.asList("1","1","1","1","2","3");
		int len = 2;
		List<String> res = new TaskSchedulerSequence().scheduleTask(tasks, len);
		for (String str : res) {
			System.out.printf(str + ",");
		}
		System.out.println("");
		System.out.println(new TaskSchedulerSequence().scheduleTask2(tasks, len));
		System.out.println(new TaskSchedulerSequence().scheduleTask3(tasks, len));
	}
	public List<String> scheduleTask(List<String> tasks, int len) {
		Map<String, Integer> map = new HashMap<>();
		List<String> res = new ArrayList<>();
		int i = 0;
		while (i < tasks.size()) {
			String task = tasks.get(i);
			if (map.containsKey(task) && map.get(task) >= res.size()) {
				for (int j = res.size(); j <= map.get(task); j++) {
					res.add("idle");
				}
			} else {
				map.put(task, res.size() +len);
				i++;
				res.add(task);
			}
		}
		return res;
	}

	public int scheduleTask2(List<String> tasks, int len) {
		Map<String, Integer> map = new HashMap<>();
		int ts = 0;
		for (String task : tasks) {
			// Move the ts to the ts that the task can be scheduled.
			// The other cases are the same.
			if (map.containsKey(task) && map.get(task) >= ts) {
				ts = map.get(task) + 1;
			}
			map.put(task, ts+len);
			ts++;
		}
		return ts;
	}

	public int scheduleTask3(List<String> tasks, int len) {
		Map<String, Integer> map = new HashMap<>();
		int ts = 0;
		Queue<String> q = new LinkedList<>();
		for (String task : tasks) {
			if (map.containsKey(task) && map.get(task) >= ts) {
				ts = map.get(task) + 1;
			}
			// Remove before put new into map and queue
			// len+1 b/c ts+len+1 can reschedule same task
			if (q.size() == len+1) {
				map.remove(q.poll());
			}
			map.put(task, ts+len);
			ts++;
			q.add(task);
		}
		return ts;
	}
}
