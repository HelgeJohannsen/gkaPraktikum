package algorithms;

import java.util.ArrayList;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

public class KruskalSlow {
	
	public static ArrayList<Edge> sortQueue(Graph graph){
		ArrayList<Edge> queue = new ArrayList<Edge>();
		int maxWeight = 0;
		// Suche das maximale Kantengewicht
		for (Edge edge : graph.getEachEdge()){
			if((int)edge.getAttribute("weight") >maxWeight ){
				maxWeight = (int)edge.getAttribute("weight");
			}
		}
		
		// wir iterieren �ber das maximale Kantengewicht um die quueue so zu sortieren
		for(int j=0; j<=maxWeight; j++) {
		for (Edge edge : graph.getEachEdge()) {
				if((int) edge.getAttribute("weight") == j) {
					queue.add(edge);
				}
				
			}
		}
		return queue;		
	}
	

	
	public static int calcSpanningTree(Graph graph){
		long start = System.currentTimeMillis();
		ArrayList<Edge> queue = sortQueue(graph);
		int minSpannTree = 0;
		Graph spannTree = new MultiGraph("toCheckIfCircle");
		for(Node node : graph){
			spannTree.addNode(node.toString());
		}
		
		while(!queue.isEmpty()){
			Edge edgeWeight = queue.remove(0);
			String src = edgeWeight.getSourceNode().toString();
			String trg = edgeWeight.getTargetNode().toString();
			if(!BFS.findWay(spannTree, src, trg)){
				spannTree.addEdge((src+trg), src, trg);
				minSpannTree+= (int)edgeWeight.getAttribute("weight");
			}
		}
		
		System.out.println((System.currentTimeMillis() - start)/1000);
		return minSpannTree;
	}
}
