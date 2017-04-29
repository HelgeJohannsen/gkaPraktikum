package algorithms;

import java.util.ArrayList;
import java.util.Iterator;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import application.graphicController;

public class Prim {
	public static Node smallestDist(ArrayList<Node> queue) {
		Node smallest = queue.get(0);

		for (Iterator iterator = queue.iterator(); iterator.hasNext();) {
			Node node = (Node) iterator.next();
			if ((int) node.getAttribute("distance") < (int) smallest.getAttribute("distance")
					&& (Boolean) node.getAttribute("visited") != true) {
				smallest = (Node) node;
			}

		}
		queue.remove(smallest);
		return smallest;

	}

	public static int prim(Graph graph, Node srcNode) {
		long start = System.currentTimeMillis();
		ArrayList<Node> resultGraph = new ArrayList<Node>();
		ArrayList<Node> queue = new ArrayList<Node>();
		int lengthAll=0;
		for (Node node : graph.getEachNode()) {
			node.addAttribute("distance", Integer.MAX_VALUE);
			node.addAttribute("visited", false);
			queue.add(node);
		}
		srcNode.addAttribute("distance", 0);

		while (!queue.isEmpty()) {

			Node next = smallestDist(queue);
			next.addAttribute("visited", true);
			resultGraph.add(next);
			Iterator<Node> neighbor = next.getNeighborNodeIterator();
			while (neighbor.hasNext()) {
				Node neighborNode = neighbor.next();
				Edge edgeB = next.getEdgeBetween(neighborNode);
				if ((Boolean) neighborNode.getAttribute("visited") != true) {
					int dist = (int) edgeB.getAttribute("weight");
					if ((int) neighborNode.getAttribute("distance") > dist) {
						neighborNode.setAttribute("distance", dist);
						neighborNode.setAttribute("Parent", next.toString());
					}
				}

			}
		}

		for(Node e : resultGraph) {
			Node ed = graph.getNode(e.getAttribute("Parent"));
			Edge edge = e.getEdgeBetween(ed);
			
			if (edge != null) {
				lengthAll += (int)edge.getAttribute("weight");
				graphicController.colorEdge(edge, "red");
			}
		}
		System.out.println("LaufZeit: " + ((System.currentTimeMillis() - start)/1000));
		System.out.println("Gesamtkantengewicht Prim:" + lengthAll);
		return lengthAll;
		
	}
}
