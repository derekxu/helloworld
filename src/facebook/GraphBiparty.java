package facebook;

import java.util.*;

public class GraphBiparty {

	public static void main(String[] args) {
		GraphNode n1 = new GraphNode();
		GraphNode n2 = new GraphNode();
		GraphNode n3 = new GraphNode();
		n1.subs.add(n2);
		n2.subs.add(n3);
		//n3.subs.add(n1);
		boolean res = new GraphBiparty().isBiparty2(n1);
		if (res) {
			System.out.println("is biparty");
		} else {
			System.out.println("NOT");
		}
	}

	public boolean isBiparty(GraphNode node) {
		if (node == null) return true;
		Set<GraphNode> set1 = new HashSet<>();
		set1.add(node);
		Set<GraphNode> set2 = new HashSet<>();
		Queue<GraphNode> q = new LinkedList<>();
		q.add(node);
		while (!q.isEmpty()) {
			node = q.poll();
			if (!set1.contains(node) && !set2.contains(node))
				return false;
			if (set1.contains(node) && !edgesAllBiparty(node, set1, set2, q)) {
				return false;
			} else if (set2.contains(node) && !edgesAllBiparty(node, set2, set1, q)) {
				return false;
			}
		}
		return true;
	}

	private boolean edgesAllBiparty(GraphNode node, Set<GraphNode> set1, Set<GraphNode> set2, Queue<GraphNode> q) {
		if (!set1.contains(node)) return false;
		for (GraphNode nei : node.subs) {
			if (set1.contains(nei)) {
				return false;
			} else if (!set2.contains(nei)) {
				set2.add(nei);
				q.add(nei);
			}
		}
		return true;
	}

	public boolean isBiparty2(GraphNode node) {
		if (node == null) return true;
		Queue<GraphNode> q = new LinkedList<>();
		q.add(node);
		node.color = 1;
		while (!q.isEmpty()) {
			node = q.poll();
			for (GraphNode nei : node.subs) {
				if (nei.color == 0) {
					nei.color = -node.color;
					q.add(nei);
				} else if (nei.color == node.color) {
					return false;
				}
			}
		}
		return true;
	}

	static class GraphNode {
		int id;
		int color = 0;
		boolean visited;
		List<GraphNode> subs;
		GraphNode () {
			this.id = -1;
			visited = false;
			this.subs = new ArrayList<>();
		}
		GraphNode (int id) {
			this.id = id;
			visited = false;
			this.subs = new ArrayList<>();
		}
		GraphNode (int id, List<GraphNode> subs) {
			this.id = id;
			visited = false;
			this.subs = subs;
		}
	}
}
