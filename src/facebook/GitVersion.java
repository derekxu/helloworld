package facebook;

import java.util.*;

/**
Git: 给你两个commit，求出他们最近的一个共同的ancestor commit。
举个例子，commit4和commit3'最近的一个ancestor是commit2
 */
public class GitVersion {

	public static void main(String[] args) {
		int nCommon = 3;
		int n1 = 3;
		int n2 = 11;
		List<Commit> arr1 = new ArrayList<>();
		List<Commit> arr2 = new ArrayList<>();
		Commit last = null;
		for (int i = 0; i < nCommon; i++) {
			arr1.add(new Commit(i, last));
			arr2.add(arr1.get(arr1.size() - 1));
			last = arr1.get(arr1.size()-1);
		}
		for (int i = 0; i < n1; i++) {
			arr1.add(new Commit(i, last));
			last = arr1.get(arr1.size()-1);
		}
		last = arr2.get(arr2.size()-1);
		for (int i = 0; i < n2; i++) {
			arr2.add(new Commit(i, last));
			last = arr2.get(arr2.size()-1);
		}
		System.out.println(new GitVersion().findLCA(arr1.get(arr1.size()-1), arr2.get(arr2.size()-1)).id);
		System.out.println(new GitVersion().findLCA2(arr1.get(arr1.size()-1), arr2.get(arr2.size()-1)).id);
		System.out.println(new GitVersion().findAncestor(arr1.get(arr1.size()-1), arr2.get(arr2.size()-1)).id);
	}

	public Commit findLCA2(Commit c1, Commit c2) {
		Set<Integer> s1 = new HashSet<>();
		Set<Integer> s2 = new HashSet<>();
		while (c1 != null && c2 != null) {
			if (s1.contains(c2.id)) return c2;
			if (s2.contains(c1.id)) return c1;
			s1.add(c1.id);
			s2.add(c2.id);
			c1 = c1.parent;
			c2 = c2.parent;
		}
		while (c1 != null) {
			if (s2.contains(c1.id)) return c1;
			c1 = c1.parent;
		}
		while (c2 != null) {
			if (s1.contains(c2.id)) return c2;
			c2 = c2.parent;
		}
		return null;
	}

	public Commit findLCA(Commit c1, Commit c2) {
		return helper(c1, c2, new HashSet<Integer>(), new HashSet<Integer>());
	}
	private Commit helper(Commit c1, Commit c2, Set<Integer> set1, Set<Integer> set2) {
		if (c1 == null || c2 == null) return null;
		if (c1.id == c2.id) return c1;
		if (set1.contains(c2.id)) return c2;
		if (set2.contains(c1.id)) return c1;
		set1.add(c1.id);
		set2.add(c2.id);
		if (c1.parent != null) {
			c1 = c1.parent;
		}
		if (c2.parent != null) {
			c2 = c2.parent;
		}
		return helper(c1, c2, set1, set2);
	}

	// Min approach.
	public Commit findAncestor(Commit c1, Commit c2) {
		if(c1 == null || c2 == null || c1.parent == null || c2.parent == null) {
			return null;
		}
		if(c1.parent.id == c2.id) {
			return c2.parent;
		}
		if(c2.parent.id == c1.id) {
			return c1.parent;
		}
		Commit ancestor1 = findAncestor(c1.parent, c2);
		Commit ancestor2 = findAncestor(c2.parent, c1);
		if(ancestor1 == null && ancestor2 == null) {
			return null;
		} else if(ancestor1 != null) {
			return ancestor1;
		} else {
			return ancestor2;
		}
	}
	static class Commit {
		int id;
		Commit parent;
		Commit(int id, Commit parent) {
			this.id = id;
			this.parent = parent;
		}
	}

}
