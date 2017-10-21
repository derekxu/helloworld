package facebook;

import java.util.*;

public class TaskSchedulerReorderRedo {

	// Can reorder, return least time.
    public int schedule1(char[] tasks, int k) {
        int[] map = new int[26];
        int nTasks = tasks.length;
        if (nTasks == 0) return 0;
        for (char task : tasks) {
            map[task-'A']++;
        }
        // Greedy, so needs to sort map
        Arrays.sort(map);
        int nSlot = k * (map[25]-1);
        for (int i = 24; i >= 0 && map[i] > 0; i--) {
            int n = Math.min(map[25]-1, map[i]);
            if (nSlot > n) {
                nSlot -= n;
            } else {
            	return nTasks;
            }
        }
        return nSlot + nTasks;
    }

    // Can reorder, return schedule
    public char[] schedule2(char[] tasks, int k) {
    	Elem[] map = new Elem[26];
    	for (char c = 'A'; c <= 'Z'; c++) {
    		map[c-'A'] = new Elem(c);
    	}
    	for (char task : tasks) {
    		map[task-'A'].count++;
    	}
    	Arrays.sort(map, new ElemComparator());
    	StringBuilder seq = new StringBuilder();
    	int nTask = tasks.length;
    	while (map[25].count > 0) {
    		// This has k+1 slots to allocate using greedy from most to least in map
    		for (int i = 0; i <= k && nTask > 0; i++) {
    			if (i < 26 && map[25-i].count > 0) {
    				seq.append(map[25-i].task);
    				map[25-i].count--;
    				nTask--;
    			} else {
    				seq.append('i');
    			}
    		}
    		Arrays.sort(map, new ElemComparator());
    	}
    	return seq.toString().toCharArray();
    }
 
    class ElemComparator implements Comparator<Elem> {
    	@Override
    	public int compare(Elem a, Elem b) {
    		return Integer.compare(a.count, b.count);
    	}
    }
    class Elem {
    	char task;
    	int count;
    	Elem(char task) {
    		this.task = task;
    		count = 0;
    	}
    }

	public static void main(String[] args) {
		char[] tasks = {'A','B','A','A','C','D'};
		int k = 2;
		TaskSchedulerReorderRedo instance = new TaskSchedulerReorderRedo();
		System.out.println(instance.schedule1(tasks, k));
		System.out.println(instance.schedule2(tasks, k));
	}

}
