package facebook;

import java.util.*;

public class ShortestPathWeightedRedo {
	public String getShortestPath(Integer[][] matrix, int start, int end) {
		if (start == end) return String.valueOf(start);
		PriorityQueue<GraphNode> q = new PriorityQueue<>(10, new Comparator<GraphNode>() {
			@Override
			public int compare(GraphNode a, GraphNode b) {
				return a.dist - b.dist;
			}
		});
		int n = matrix.length;
		GraphNode[] nodes = new GraphNode[n];
		for (int i = 0; i < n; i++) {
			if (i == start) {
				nodes[i] = new GraphNode(i, 0);
				nodes[i].path = String.valueOf(i)+",";
			} else {
				nodes[i] = new GraphNode(i);
			}
			q.add(nodes[i]);
		}
		Set<Integer> used = new HashSet<>();
		while (!q.isEmpty()) {
			GraphNode node = q.poll();
			used.add(node.id);
			if (node.id == end) {
				return node.path;
			}
			for (int i = 0; i < n; i++) {
				if (used.contains(i) || matrix[node.id][i] == null)
					continue;
				int dist = node.dist + matrix[node.id][i];
				if (dist < nodes[i].dist) {
					nodes[i].dist = dist;
					nodes[i].path = node.path + String.valueOf(i) + ",";
					q.remove(nodes[i]);
					q.add(nodes[i]);
				}
			}
		}
		return null;
	}

	class GraphNode {
		int id;
		int dist;
		String path;
		GraphNode(int id) {
			this.id = id;
			dist = Integer.MAX_VALUE;
			path = "";
		}
		GraphNode(int id, int dist) {
			this.id = id;
			this.dist = Integer.MAX_VALUE;
			path = "";
		}
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
		System.out.println(new ShortestPathWeightedRedo().getShortestPath(matrix, start, end));
	}

}
