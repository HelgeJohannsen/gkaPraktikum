package algorithms;

import java.util.ArrayList;
import java.util.Iterator;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class HierholzerTest {
	public static ArrayList<Node> getPath(Graph graph) {
		ArrayList path = new ArrayList();
		ArrayList<Edge> eulerPath = new ArrayList();
		ArrayList<Edge> notVisitedEdges = new ArrayList();

		// Alle Kanten zu einer Liste zusammenfügen
		for (Iterator iterator = graph.getEdgeIterator(); iterator.hasNext();) {
			Edge edgeToAdd = (Edge) iterator.next();

			edgeToAdd.setAttribute("visited", false);

			if (edgeToAdd != null) {
				notVisitedEdges.add(edgeToAdd);
			}
		}

		// Vorbedingungen Graph muss ungerichtet sein und darf nur Knoten mit
		// geraden Knotengrad aufweisen
		if (graph.getEdge(0) == null) {

		}

		if (notVisitedEdges.size() % 2 == 0)
			;

		// beginn des Algorithmus
		Edge edge = notVisitedEdges.get(2);
		String src = edge.getSourceNode().toString();
		System.out.println(src);
		Node start = graph.getNode(src);
		Node akt = null;
		Boolean first = true;
		while (akt != start){
			if(first){
				akt = start;
				first=false;
			}
	
			for (Iterator iter = akt.getNeighborNodeIterator(); iter.hasNext();) {
				Node neighbor = (Node) iter.next();
				Edge aktEdge = akt.getEdgeBetween(neighbor);
				Boolean visit = aktEdge.getAttribute("visited");
				if ((Boolean) aktEdge.getAttribute("visited") == false) {
					System.out.println(aktEdge);
					aktEdge.setAttribute("visited", true);
					eulerPath.add(aktEdge);
					
					if(akt != aktEdge.getTargetNode()){
						akt = aktEdge.getTargetNode();
					}else{
						akt = aktEdge.getSourceNode();
					}
					
					
					System.out.println(akt);
					break;
					
				}
			}
		}
		// akt.getNeighborNodeIterator()
		return path;

	}
}
