package algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import org.graphstream.graph.implementations.SingleGraph;

import file_handling.FileControl;

public class DijkstraExample {
//        B---9--E
//       /|      |
//      / |      |
//     /  |      |
//    14  2      6
//   /    |      |
//  /     |      |
// A---9--C--11--F
//  \     |     /
//   \    |    /
//    7  10   15
//     \  |  /
//      \ | /
//       \|/
//        D      G
 	public static Graph exampleGraph() {
 		FileControl fc = new FileControl();
 		fc.readFile("./src/graphDataOutput/" , "generated1");
 		Graph g = fc.getGraph();
//		for (Node n : g)
//			n.addAttribute("label", n.getId());
//		for (Edge e : g.getEachEdge())
//			e.addAttribute("label", "" + (int) e.getNumber("weight"));
		return g;
	}

	public static void main(String[] args) {
		Graph g = exampleGraph();
		g.display(false);

		// Edge lengths are stored in an attribute called "length"
		// The length of a path is the sum of the lengths of its edges
		Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "weight");

		// Compute the shortest paths in g from A to all nodes
		dijkstra.init(g);
		dijkstra.setSource(g.getNode("VZE"));
		dijkstra.compute();

		// Print the lengths of all the shortest paths
		for (Node node : g)
			System.out.printf("%s->%s:%10.2f%n", dijkstra.getSource(), node,
					dijkstra.getPathLength(node));

//		// Color in blue all the nodes on the shortest path form A to B
//		for (Node node : dijkstra.getPathNodes(g.getNode("DHL")))
//			node.addAttribute("ui.style", "fill-color: blue;");
//
//		// Color in red all the edges in the shortest path tree
//		for (Edge edge : dijkstra.getTreeEdges())
//			edge.addAttribute("ui.style", "fill-color: red;");

		// Print the shortest path from A to B
		System.out.println(dijkstra.getPath(g.getNode("SQT")));

		// Build a list containing the nodes in the shortest path from A to B
		// Note that nodes are added at the beginning of the list
		// because the iterator traverses them in reverse order, from B to A
//		List<Node> list1 = new ArrayList<Node>();
//		for (Node node : dijkstra.getPathNodes(g.getNode("DHL")))
//			list1.add(0, node);

		// A shorter but less efficient way to do the same thing
//		List<Node> list2 = dijkstra.getPath(g.getNode("DHL")).getNodePath();

		// cleanup to save memory if solutions are no longer needed
		dijkstra.clear();

		// Now compute the shortest path from A to all the other nodes
		// but taking the number of nodes in the path as its length
//		dijkstra = new Dijkstra(Dijkstra.Element.NODE, null, null);
//		dijkstra.init(g);
//		dijkstra.setSource(g.getNode("JCM"));
//		dijkstra.compute();

		// Print the lengths of the new shortest paths
//		for (Node node : g)
//			System.out.printf("%s->%s:%10.2f%n", dijkstra.getSource(), node,
//					dijkstra.getPathLength(node));

		// Print all the shortest paths between A and F
//		Iterator<Path> pathIterator = dijkstra.getAllPathsIterator(g
//				.getNode("UVJ"));
//		while (pathIterator.hasNext())
//			System.out.println(pathIterator.next());

	}
}