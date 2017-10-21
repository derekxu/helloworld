package facebook;

import java.util.*;

/**
有一些账号，账号里面有一个或多个email， 如果两个账号有共同的email，则认为这两个账号是同一个人，找出哪些账号是同一个人
Union Find, same as CombineEmails
List<String[]> lists represent input is the easiest.
 */
public class DupAccountEmails {

	public static void main(String[] args) {
		int n = 3;
		Account[] accounts = new Account[n];
		for (int i = 0; i < n; i++) {
			accounts[i] = new Account(i);
		}
		accounts[0].emails.add("1");
		accounts[0].emails.add("2");
		accounts[0].emails.add("3");
		accounts[1].emails.add("5");
		accounts[2].emails.add("2");
		List<Account> res = new DupAccountEmails().removeDuplicates(accounts);
		for (Account account : res) {
			System.out.printf("Account: %d,", account.id);
		}
	}

	public List<Account> removeDuplicates(Account[] accounts) {
		int n = accounts.length;
		Account[] fathers = new Account[n];
		for (int i = 0; i < n; i++) {
			fathers[i] = accounts[i];
		}
		for (int i = 0; i < n - 1; i++) {
			if (!fathers[i].equals(accounts[i])) continue;
			for (int j = i + 1; j < n; j++) {
				unionByEmails(accounts, fathers, i, j);
			}
		}
		Set<Integer> accountSet = new HashSet<>();
		for (Account account : accounts) {
			accountSet.add(find(accounts, fathers, account.id).id);
		}
		List<Account> res = new ArrayList<>();
		for (int id : accountSet) {
			res.add(accounts[id]);
		}
		return res;
	}
	private Account find(Account[] accounts, Account[] fathers, int i) {
		if (!fathers[i].equals(accounts[i])) {
			fathers[i] = find(accounts, fathers, fathers[i].id);
		}
		return fathers[i];
	}

	private void unionByEmails(Account[] accounts, Account[] fathers, int i, int j) {
		Account f1 = find(accounts, fathers, i);
		Account f2 = find(accounts, fathers, j);
		if (!f1.equals(f2)) {
			for (String email : f1.emails) {
				if (f2.emails.contains(email)) {
					union(accounts, fathers, i, j);
					return;
				}
			}
		}
	}

	private void union(Account[] accounts, Account[] fathers, int i, int j) {
		Account f1 = find(accounts, fathers, i);
		Account f2 = find(accounts, fathers, j);
		if (!f1.equals(f2)) {
			f1.emails.addAll(f2.emails);
			fathers[f2.id] = f1;
			f1.emails.addAll(accounts[j].emails);
			fathers[j] = f1;
		}
	}
	
	static class Account {
		int id;
		Set<String> emails;
		Account(int id) {
			this.id = id;
			emails = new HashSet<>();
		}
		Account(int id, Set<String> emails) {
			this.id = id;
			this.emails = emails;
		}
		boolean equals(Account account) {
			return id == account.id;
		}
	}
}
