package algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class Hierholz {
	public static ArrayList<Node> getPath(Graph graph) {
		ArrayList path = new ArrayList();
		ArrayList<Node> unterkreis = new ArrayList();
		ArrayList<Edge> notVisitedEdges = new ArrayList();

		// Alle Kanten zu einer Liste zusammenfügen
		for (Iterator iterator = graph.getEdgeIterator(); iterator.hasNext();) {
			Edge edgeToAdd = (Edge) iterator.next();
			if (edgeToAdd != null) {
				notVisitedEdges.add(edgeToAdd);
			}
		}


		// Schritt I auswählen des Startknoten
		Node startNode = graph.getNode(0);
		Node srcNode = startNode;
		unterkreis.add(srcNode);
		Node trgNode = null;
		Boolean first = true;
		
		do{
			for (Iterator iter = srcNode.getNeighborNodeIterator(); iter.hasNext();){
				trgNode = (Node) iter.next();
				Edge actEdge = srcNode.getEdgeBetween(trgNode);
				if(notVisitedEdges.contains(actEdge)){
					unterkreis.add(trgNode);
					srcNode = trgNode;
		// Schritt II vernachlässige besuchte Kanten			
					notVisitedEdges.remove(actEdge);
					break;
				}
			}
		}while(srcNode != startNode);
		
		for (Iterator iterator = unterkreis.iterator(); iterator.hasNext();) {
			Node node = (Node) iterator.next();
		}
		return unterkreis;

	}
}
