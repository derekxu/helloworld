package facebook;

import java.util.*;
/**
	sparse vector dot product。
	follow up: what if the number of nonzero elements of one vector is 10^10 and the other is 10^2, how can you make it faster?
 */
public class SparseDotProduct {

	public static void main(String[] args) {
		List<Tuple> l1 = new ArrayList<>();
		l1.add(new Tuple(1,2));
		l1.add(new Tuple(5,2));
		l1.add(new Tuple(22,3));
		List<Tuple> l2 = new ArrayList<>();
		l2.add(new Tuple(1,2));
		l2.add(new Tuple(11,2));
		l2.add(new Tuple(22,1));
		System.out.println(new SparseDotProduct().dotProduct(l1, l2));
		System.out.println(new SparseDotProduct().dotProductFollowup(l1, l2));
	}
	public int dotProduct(List<Tuple> l1, List<Tuple> l2) {
		int i = 0, j = 0;
		int res = 0;
		while (i < l1.size() && j < l2.size()) {
			Tuple t1 = l1.get(i);
			Tuple t2 = l2.get(j);
			if (t1.id == t2.id) {
				res += t1.val * t2.val;
				i++;
				j++;
			} else if (t1.id < t2.id) {
				i++;
			} else {
				j++;
			}
		}
		return res;
	}
	public int dotProductFollowup(List<Tuple> l1, List<Tuple> l2) {
		if (l1.size() > l2.size()) return dotProductFollowup(l2, l1);
		int res = 0;
		for (int i = 0; i < l1.size(); i++) {
			Tuple t = l1.get(i);
			int l = 0, r = l2.size() - 1;
			while (l <= r) {
				if (r - l < 2) {
					if (l2.get(l).id == t.id) {
						res += t.val * l2.get(l).val;
					} else if (l2.get(l).id == t.id) {
						res += t.val * l2.get(r).val;
					}
					break;
				}
				int m = l + (r - l)/2;
				if (l2.get(m).id == t.id) {
					res += t.val * l2.get(m).val;
					break;
				}
				if (l2.get(m).id < t.id) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
		}
		return res;
	}
	static class Tuple {
		int id;
		int val;
		Tuple(int id, int val) {
			this.id = id;
			this.val = val;
		}
	}
}
