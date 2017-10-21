package facebook;

import java.util.*;

public class CombineEmailsRedo {
	public List<Account> merge(List<String[]> lists) {
		Map<String, List<Integer>> emailToLists = new HashMap<>();
		int n = lists.size();
		for (int i = 0; i < n; i++) {
			for (String email : lists.get(i)) {
				if (!emailToLists.containsKey(email))
					emailToLists.put(email, new ArrayList<Integer>());
				emailToLists.get(email).add(i);
			}
		}
		int[] fathers = new int[n];
		for (int i = 0; i < n; i++) {
			fathers[i] = i;
		}
		for (String email : emailToLists.keySet()) {
			List<Integer> arr = emailToLists.get(email);
			for (int i = 1; i < arr.size(); i++) {
				union(fathers, arr.get(i-1), arr.get(i));
			}
		}
		Map<Integer, Account> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			int id = find(fathers, i);
			if (!map.containsKey(id)) map.put(id, new Account());
			map.get(id).lists.add(i);
			for (String e : lists.get(i)) {
				map.get(id).emails.add(e);
			}
		}
		List<Account> res = new ArrayList<>();
		for (Account a : map.values()) {
			res.add(a);
		}
		return res;
	}
	private int find(int[] fathers, int i) {
		if (fathers[i] != i) {
			int f = find(fathers, fathers[i]);
			fathers[i] = f;
			return f;
		}
		return i;
	}

	private void union(int[] fathers, int i, int j) {
		int f1 = find(fathers, i);
		int f2 = find(fathers, j);
		if (f1 != f2) {
			fathers[f2] = f1;
			fathers[j] = f1;
		}
	}

	public static void main(String[] args) {
		HashMap<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		for(int i = 0; i < 4; ++i)
			map.put(i, new ArrayList<String>());
		map.get(0).add("a@a.com");
		map.get(0).add("b@b.com");
		map.get(1).add("b@b.com");
		map.get(1).add("c@c.com");
		map.get(2).add("c@c.com");
		map.get(3).add("e@e.com");
		List<String[]> input = new ArrayList<>();
		input.add(new String[]{"a@a.com","b@b.com"});
		input.add(new String[]{"b@b.com","c@c.com"});
		input.add(new String[]{"c@c.com"});
		input.add(new String[]{"e@e.com"});
		List<Account> res = new CombineEmailsRedo().merge(input);
		for (Account account : res) {
			System.out.print("(");
			for (int num : account.lists) {
				System.out.print(num + ", ");
			}
			System.out.print(") : (");
			for (String email : account.emails) {
				System.out.print(email + ", ");
			}
			System.out.println(")");
		}
	}

	class Account {
		List<Integer> lists;
		Set<String> emails;
		Account() {
			this.lists = new ArrayList<>();
			this.emails = new HashSet<>();
		}
	}
}
