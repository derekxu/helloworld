package facebook;

import java.util.*;

/**
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=216431&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%255B3046%255D%255Bvalue%255D%3D2%26searchoption%255B3046%255D%255Btype%255D%3Dradio&page=1
 1.input friends relation {{1，2}， {2，3}， {3，4}} 把用户存在两个group里， 每个group里大家都不互相认识。所以exp应该g1{1，3} g2{2，4}。
 */
public class FriendRelation {

	public static void main(String[] args) {
		List<List<Integer>> friends = new ArrayList<>();
		friends.add(Arrays.asList(1,2));
		friends.add(Arrays.asList(2,3));
		friends.add(Arrays.asList(3,4));
		friends.add(Arrays.asList(4,1));
		List<List<Integer>> res = new FriendRelation().friendTwoParties(friends);
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

	public List<List<Integer>> friendTwoParties(List<List<Integer>> friends) {
		Map<Integer, Set<Integer>> relation = new HashMap<>();
		for (List<Integer> pair : friends) {
			int p0 = pair.get(0);
			int p1 = pair.get(1);
			if (!relation.containsKey(p0)) {
				relation.put(p0, new HashSet<Integer>());
			}
			if (!relation.containsKey(p1)) {
				relation.put(p1, new HashSet<Integer>());
			}
			relation.get(p0).add(p1);
			relation.get(p1).add(p0);
		}
		List<List<Integer>> res = new ArrayList<>();
		Set<Integer> visited = new HashSet<>();
		Group[] groups = new Group[2];
		groups[0] = new Group();
		groups[1] = new Group();
		for (int person : relation.keySet()) {
			if (visited.contains(person)) continue;
			Queue<Integer> q = new LinkedList<>();
			q.add(person);
			groups[0].group.add(person);
			visited.add(person);
			while (!q.isEmpty()) {
				int p = q.poll();
				int gid = -1;
				if (groups[0].group.contains(p)) {
					gid = 0;
				} else if (groups[1].group.contains(p)) {
					gid = 1;
				}
				if (gid == -1) throw new RuntimeException();
				for (int fri : relation.get(p)) {
					if (groups[gid].group.contains(fri)) throw new RuntimeException(); 
					if (visited.contains(fri)) continue;
					visited.add(fri);
					groups[(gid+1)%2].group.add(fri);
					q.add(fri);
				}
			}
		}
		res.add(new ArrayList<Integer>(groups[0].group));
		res.add(new ArrayList<Integer>(groups[1].group));
		return res;
	}

	static class Group {
		Set<Integer> group;
		Group() {
			group = new HashSet<>();
		}
	}
}
