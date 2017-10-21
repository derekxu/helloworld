package facebook;

import java.util.*;

/**
Given a graph each edge with a cost to pass, find the least cost to come from
the start node the the end node.
 */
public class ShortestPathWeightedEdge {

	// Diji approach
	public String shortestPath(Integer[][] matrix, int start, int end) {
		if (matrix.length == 0 || matrix[0].length == 0) return "";
		int m = matrix.length, n = matrix[0].length;
		PriorityQueue<Elem> q = new PriorityQueue<>(m, new Comparator<Elem>(){
			@Override
			public int compare(Elem a, Elem b) {
				return a.dist - b.dist;
			}
		});
		Elem[] nodes = new Elem[m];
		for (int i = 0; i < m; i++) {
			nodes[i] = new Elem(i);
			q.add(nodes[i]);
		}
		nodes[start].dist = 0;
		nodes[start].path = "" + start;
		Elem node = nodes[start];
		Elem endNode = nodes[end];
		while (!q.isEmpty()) {
			node = q.poll();
			if (node.id == endNode.id) {
				return node.path;
			}
			for (int j = 0; j < n; j++) {
				Integer d = matrix[node.id][j];
				if (d != null && nodes[j].dist > d + node.dist) {
					nodes[j].dist = d + node.dist;
					nodes[j].path = node.path + "," + j;
					q.remove(nodes[j]);
					q.add(nodes[j]);
				}
			}
		}
		return "";
	}

	public String shortestPath2(Integer[][] matrix, int start, int end) {
		if (matrix.length == 0 || matrix[0].length == 0) return "";
		int m = matrix.length, n = matrix[0].length;
		Elem[] nodes = new Elem[m];
		Elem[] sorted = new Elem[m];
		for (int i = 0; i < m; i++) {
			nodes[i] = new Elem(i);
			sorted[i] = nodes[i];
		}
		nodes[start].dist = 0;
		nodes[start].path = "" + start;
		int idx = 0;
		while (true) {
			Arrays.sort(sorted, new Comparator<Elem>(){
				@Override
				public int compare(Elem a, Elem b) {
					return Integer.compare(a.dist, b.dist);
				}
			});
			int id = sorted[idx].id;
			if (id == end) {
				return sorted[idx].path;
			}
			for (int j = 0; j < n; j++) {
				Integer d = matrix[id][j];
				if (d != null && nodes[j].dist > d + sorted[id].dist) {
					nodes[j].dist = d + sorted[id].dist;
					nodes[j].path = sorted[id].path + "," + j;
				}
			}
			idx++;
		}
	}

	public String shortestPath3(Integer[][] matrix, int start, int end) {
		if (matrix.length == 0 || matrix[0].length == 0) return "";
		int m = matrix.length, n = matrix[0].length;
		Elem[] nodes = new Elem[m];
		Elem[] sorted = new Elem[m];
		for (int i = 0; i < m; i++) {
			nodes[i] = new Elem(i);
			sorted[i] = nodes[i];
		}
		nodes[start].dist = 0;
		nodes[start].path = "" + start;
		int idx = 0;
		while (true) {
			quickSelect(sorted, idx);
			int id = sorted[idx].id;
			if (id == end) {
				return sorted[idx].path;
			}
			for (int j = 0; j < n; j++) {
				Integer d = matrix[id][j];
				if (d != null && nodes[j].dist > d + sorted[id].dist) {
					nodes[j].dist = d + sorted[id].dist;
					nodes[j].path = sorted[id].path + "," + j;
				}
			}
			idx++;
		}
	}

	private int quickSelect(Elem[] arr, int idx) {
		int l = idx, r = arr.length-1;
		while (l <= r) {
			int k = partition(arr, l, r);
			if (k == idx) {
				return k;
			}
			if (k > idx) {
				r = k - 1;
			} else {
				l = k + 1;
			}
		}
		return -1;
	}

	private int partition(Elem[] arr, int idx, int r) {
		int l = idx + 1;
		while (l <= r) {
			if (arr[l].dist >= arr[idx].dist) {
				swap(arr, l, r);
				r--;
			} else {
				l++;
			}
		}
		l--;
		swap(arr, idx, l);
		return l;
	}

	private void swap(Elem[] arr, int i, int j) {
		
	}

	public static void main(String[] args) {
		Integer[][] matrix = {
				{null, 1, 2, null},
				{1, null, null, 6},
				{2, null, null, 5},
				{null, 6, 5, null},
		};
		int start = 0;
		int end = matrix.length-1;
		System.out.println(new ShortestPathWeightedEdge().shortestPath(matrix, start, end));
		System.out.println(new ShortestPathWeightedEdge().shortestPath2(matrix, start, end));
		System.out.println(new ShortestPathWeightedEdge().shortestPath3(matrix, start, end));
	}

	class Elem {
		int id;
		int dist;
		String path;
		Elem(int id) {
			this.id = id;
			dist = Integer.MAX_VALUE;
			path = "";
		}
	}
}
