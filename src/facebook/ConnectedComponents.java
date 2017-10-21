package facebook;

import java.util.*;

import facebook.GraphBiparty.GraphNode;

/**
找一个无向图中的所有联通分量，要求输出每个联通分量的点集.
Connected Components of undirected graph.
 */
public class ConnectedComponents {

	public static void main(String[] args) {
		int n = 3;
		List<GraphNode> nodes = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			nodes.add(new GraphNode(i));
		}
		nodes.get(0).subs.add(nodes.get(1));
		List<List<GraphNode>> res = new ConnectedComponents().connectedComponents(nodes);
		for (List<GraphNode> sc : res) {
			System.out.printf("sc: ");
			for (GraphNode node : sc) {
				System.out.printf("%d,", node.id);
			}
			System.out.println("");
		}
	}

	public List<List<GraphNode>> connectedComponents(List<GraphNode> nodes) {
		List<List<GraphNode>> res = new ArrayList<>();
		for (GraphNode node : nodes) {
			if (!node.visited) {
				List<GraphNode> arr = bfs(node);
				if (!arr.isEmpty()) {
					res.add(arr);
				}
			}
		}
		return res;
	}
	private List<GraphNode> bfs(GraphNode node) {
		if (node.visited) return new ArrayList<GraphNode>();
		Queue<GraphNode> q = new LinkedList<>();
		List<GraphNode> res = new ArrayList<>();
		res.add(node);
		q.add(node);
		node.visited = true;
		while (!q.isEmpty()) {
			GraphNode cur = q.poll();
			for (GraphNode nei : cur.subs) {
				if (!nei.visited) {
					q.add(nei);
					res.add(nei);
					nei.visited = true;
				}
			}
		}
		return res;
	}
}
