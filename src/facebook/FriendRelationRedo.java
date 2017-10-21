package facebook;

import java.util.*;

public class FriendRelationRedo {
	public List<List<Integer>> getGroups(int n, List<int[]> friends) {
		if (n == 0) return new ArrayList<>();
		List<Set<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adj.add(new HashSet<>());
		}
		for (int[] p : friends) {
			adj.get(p[0]).add(p[1]);
			adj.get(p[1]).add(p[0]);
		}
		int[] pToG = new int[n];
		Queue<Integer> q = new LinkedList<>();
		int id = 0;
		while (id < n && adj.get(id).isEmpty()) {
			id++;
		}
		q.add(id);
		pToG[id] = 1;
		while (!q.isEmpty()) {
			int p = q.poll();
			for (int nei : adj.get(p)) {
				if (pToG[nei] == 0) {
					pToG[nei] = -pToG[p];
					q.add(nei);
				} else if(pToG[nei] == pToG[p]) {
					throw new RuntimeException("Invalid Relation.");
				}
			}
		}
		List<List<Integer>> res = new ArrayList<>();
		res.add(new ArrayList<>());
		res.add(new ArrayList<>());
		for (int i = 0; i < n; i++) {
			if (pToG[i] == 1) {
				res.get(0).add(i);
			} else {
				res.get(1).add(i);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		List<int[]> friends = new ArrayList<>();
		friends.add(new int[]{1,2});
		friends.add(new int[]{2,3});
		friends.add(new int[]{3,4});
		friends.add(new int[]{4,1});
		List<List<Integer>> res = new FriendRelationRedo().getGroups(5, friends);
		System.out.printf("Group1: ");
		for (int p : res.get(0)) {
			System.out.printf(p + ",");
		}
		System.out.println("");
		System.out.printf("Group2: ");
		for (int p : res.get(1)) {
			System.out.printf(p + ",");
		}
		System.out.println("");
	}
}
