package algorithms;
// git Test Branch dev 1
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import application.graphicController;

//Helge Timo Test push
public class Dijkstra2 {
	private Graph graph;
	private Node source;
	private Node target;
	private LinkedList<Node> queue = new LinkedList<Node>();
	Iterator<Node> neighborIterator;
	Boolean directedOnOff;

	public Dijkstra2(Graph graph, String source, String target) {
		this.graph = graph;
		this.source = graph.getNode(source);
		this.target = graph.getNode(target);
		this.directedOnOff = graph.getAttribute("directed");

	}

	public void init() {
		for (Node n1 : graph.getEachNode()) {
			n1.addAttribute("distance", Integer.MAX_VALUE);
			n1.addAttribute("parent", "null");
			n1.addAttribute("ok", false);
		}
		graph.getNode(source.toString()).addAttribute("distance", 0);
		graph.getNode(source.toString()).addAttribute("parent", source.toString());
		graph.getNode(source.toString()).addAttribute("ok", true);
		queue.add(source);
		graphicController.colorNode(source, "blue");
	}

	public void print() {
		for (Node n1 : graph.getEachNode()) {
			System.out.print(n1.toString() + " " + n1.getAttribute("distance").toString() + " "
					+ n1.getAttribute("parent") + " " + n1.getAttribute("ok").toString() + "\n");
		}
	}

	public void showWay() {
		Node node = target;
		for (Edge ed : graph.getEachEdge()) {
			graphicController.colorEdge(ed, "black");
		}

		try {
			while (node != source) {
				graphicController.colorNode(node, "red");
				Edge edge = graph.getEdge((node.getAttribute("parent") + node.toString()));
				if (edge == null && !graph.isStrict()) {
					edge = graph.getEdge(node.toString() + node.getAttribute("parent"));
				}
				node = graph.getNode(node.getAttribute("parent"));
				graphicController.colorEdge(edge, "red");
			}
		} catch (NullPointerException e) {
			System.out.print("NullPointerException caught");
			System.out.println("there is no way between source and target");
		}

	}

	private Node nodeFromQ() {
		int lowest = Integer.MAX_VALUE;
		LinkedList<Node> queue2 = (LinkedList<Node>) queue.clone();
		Node lowestNode = null;
		int index = 0, counter = 0;

		while (!queue2.isEmpty()) {
			Node node = (Node) queue2.remove();
			int i1 = (int) node.getNumber("distance");
			if (i1 < lowest) {
				lowest = i1;
				lowestNode = node;
				index = counter;
				lowestNode = node;
			}
			counter += 1;
		}
		return queue.remove(index);

	}

	public void calc() {
		while (!queue.isEmpty()) {
			Node sourceNode = nodeFromQ();
			neighborIterator = sourceNode.getNeighborNodeIterator();
			while (neighborIterator.hasNext()) {
				Node node = neighborIterator.next();
				Boolean ok = node.getAttribute("ok");
				Edge edge = graph.getEdge(sourceNode.toString() + node.toString());
				if (edge == null && !graph.isStrict()) {
					edge = graph.getEdge(node.toString() + sourceNode.toString());
				}

				if (edge != null && !ok) {

					 graphicController.colorEdge(edge, "red");
					 graphicController.colorNode(node, "red");
					int sEdgeDistance = (edge.getAttribute("weight"));
					int distanceSource = (int) sourceNode.getNumber("distance");
					int distanceNode = (int) node.getNumber("distance");
					int edgeDistance = (sEdgeDistance);

					if ((distanceSource + edgeDistance) < distanceNode) {
						node.addAttribute("distance", (distanceSource + edgeDistance));
						node.addAttribute("parent", sourceNode.toString());
					}
					queue.add(node);
				}
				if (!ok) {
					graphicController.colorNode(node, "green");
				}
			}
			sourceNode.setAttribute("ok", true);
			graphicController.colorNode(sourceNode, "green");
		}

	}
}
