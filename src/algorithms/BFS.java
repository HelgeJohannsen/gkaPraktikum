package algorithms;

import java.util.Iterator;
import java.util.LinkedList;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class BFS {
	public static Boolean findWay(Graph graph, String sour, String targ) {
		Node source = graph.getNode(sour);
		Node target = graph.getNode(targ);
		LinkedList<Node> queue = new LinkedList<Node>();
		for (Node n : graph.getEachNode()) {
			n.addAttribute("visited", false);
		}
		queue.add(source);

		while (!queue.isEmpty()) {
			Node node = queue.remove();
			Iterator<Node> n1 = node.getNeighborNodeIterator();
			if (node.equals(target)) {
				return true;
			}
			while (n1.hasNext()) {
				Node node1 = n1.next();
				if ((Boolean) node1.getAttribute("visited") == false) {
					node1.addAttribute("visited", true);
					queue.add(node1);
				}
			}
		}
		return false;

	}
}
