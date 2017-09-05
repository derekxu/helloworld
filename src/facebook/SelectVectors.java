package facebook;

import java.util.*;

/**
给n个d维的vector和一个first selected的vector, 选出k个vector,每次选择下一个时要求：选离所有selected vector最远的vector, 
计算距离的函数已给出D(v1,v2)并假设调用此函数时间复杂度为O(1), 某vector与所有selected vectors的距离定义为这个vector与其nearest selected neighbor的距离。

在面试官的提示下最终找到了复杂度为O(nk)的最优解，对每个unselected的vector存下其与selected vectosr的距离,每次遍历unselected vectors找出距离最远的vector为下一个selected vector, 用这个vector与每个unselected vector的距离去更新距离（若小于原距离，表示nearest selected neighbor更换了）
 */
public class SelectVectors {

	public static void main(String[] args) {
		int n = 5;
		Vec[] vecs = new Vec[n];
		for (int i = 0; i < n; i++) {
			vecs[i] = new Vec(i+2);
		}
		Vec selected = new Vec(0);
		int k = 3;
		List<Vec> res = new SelectVectors().selectKVectors(vecs, selected, k);
		for (Vec vec : res) {
			System.out.printf("%d,", vec.val);
		}
		System.out.println("");
	}

	public List<Vec> selectKVectors(Vec[] vecs, Vec selected, int k) {
		int n = vecs.length;
		List<Vec> res = new ArrayList<>();
		if (n + 1 <= k)  {
			res.add(selected);
			for (Vec vec : vecs) {
				res.add(vec);
			}
			return res;
		}
		Integer[] dists = new Integer[n];
		Arrays.fill(dists, Integer.MAX_VALUE);
		Vec cur = selected;
		while (res.size() < k-1) {
			int maxDist = Integer.MIN_VALUE, maxId = -1;
			for (int i = 0; i < n; i++) {
				if (dists[i] != null) {
					dists[i] = Math.min(dists[i], getDist(cur, vecs[i]));
					if (dists[i] > maxDist) {
						maxId = i;
						maxDist = dists[i];
					}
				}
			}
			res.add(cur);
			cur = null;
			if (maxId >= 0) {
				cur = vecs[maxId];
				dists[maxId] = null;
			}
		}
		if (cur != null && res.size() < k) {
			res.add(cur);
		}
		return res;
	}

	int getDist(Vec v1, Vec v2) {
		return Math.abs(v1.val - v2.val);
	}

	static class Vec {
		int val;
		Vec (int val) {
			this.val = val;
		}
	}
}
