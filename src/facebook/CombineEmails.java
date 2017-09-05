package facebook;

import java.util.*;

public class CombineEmails {

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
		List<Account> res = new CombineEmails().merge(map);
		for (Account account : res) {
			System.out.print("(");
			for (int num : account.numbers) {
				System.out.print(num + ", ");
			}
			System.out.print(") : (");
			for (String email : account.emails) {
				System.out.print(email + ", ");
			}
			System.out.println(")");
		}
	}

	// Continuous integers on account number
	public List<Account> merge(Map<Integer, List<String>> numAndEmails) {
		Map<String, List<Integer>> map = new HashMap<>();
		int n = numAndEmails.size();
		for (int id : numAndEmails.keySet()) {
			for (String email : numAndEmails.get(id)) {
				if (!map.containsKey(email)) {
					map.put(email, new ArrayList<Integer>());
				}
				map.get(email).add(id);
			}
		}
		int[] fathers = new int[n];
		for (int i = 0; i < n; i++) {
			fathers[i] = i;
		}
		for (String email : map.keySet()) {
			List<Integer> nums = map.get(email);
			for (int i = 1; i < nums.size(); i++) {
				union(fathers, nums.get(i-1), nums.get(i));
			}
		}
		List<Account> res = new ArrayList<>();
		Map<Integer, Account> fatherToAccount = new HashMap<>();
		for (int i = 0; i < n; i++) {
			int f = find(fathers, i);
			if (!fatherToAccount.containsKey(f)) {
				fatherToAccount.put(f, new Account(new ArrayList<Integer>(), new HashSet<String>()));
				res.add(fatherToAccount.get(f));
			}
			fatherToAccount.get(f).numbers.add(i);
			fatherToAccount.get(f).emails.addAll(numAndEmails.get(i));
		}
		return res;
	}

	private int find(int[] fathers, int i) {
		if (fathers[i] != i) {
			fathers[i] = find(fathers, fathers[i]);
		}
		return fathers[i];
	}

	private void union(int[] fathers, int i, int j) {
		int f1 = find(fathers, i);
		int f2 = find(fathers, j);
		if (f1 != f2) {
			fathers[f1] = f2;
			fathers[i] = f2;
		}
	}


	class Account {
		List<Integer> numbers;
		Set<String> emails;
		Account(List<Integer> numbers, Set<String> emails) {
			this.numbers = numbers;
			this.emails = emails;
		}
	}
}
