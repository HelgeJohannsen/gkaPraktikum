package algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class Fleury {

	public static List<Node> getPath(Graph eulerKreisPositiv2) {
		List<Node> eulerPath = new ArrayList<>();
		// Zuf�lligen Startknoten finden
		List<Node> nodeList = new ArrayList<>(eulerKreisPositiv2.getNodeSet());
		int randomIndex = (int) (Math.random() * nodeList.size() - 1);
		Node start = nodeList.get(randomIndex);
		eulerPath.add(start);

		Node v = start;
		Iterator<Edge> neighbors;
		// Solange Kanten vorhanden sind
		while (eulerKreisPositiv2.getEdgeSet().size() > 0) {
			// Iteriere �ber alle Kanten 
			neighbors = v.getEdgeIterator();
			while (neighbors.hasNext()) {
				Edge nextEdge = neighbors.next();
				Node s = nextEdge.getSourceNode();
				Node t = nextEdge.getTargetNode();
				// Ist "s--t" eine Br�cke ?
				if (t.getDegree() != 1) {
					if (!isBridge(eulerKreisPositiv2, nextEdge)) {
						eulerKreisPositiv2.removeEdge(nextEdge);
						eulerPath.add(t);
						v = t;
						break;
					}
					// Die letzte Kante wurde entfernt -> return
				} else {
					eulerPath.add(t);
					return eulerPath;
				}
			}
		}

		return eulerPath;
	}

	private static boolean isBridge(Graph graph, Edge edge) {
		Node s = edge.getSourceNode();
		Node t = edge.getTargetNode();

		graph.removeEdge(edge);
		if (BreadthFirstSearch.getSteps(s.toString(), t.toString(), graph) != -1) {
			graph.addEdge(s.toString() + t.toString(), s, t);
			return true;
		} else {
			graph.addEdge(s.toString() + t.toString(), s, t);
			return false;
		}

	}

}
