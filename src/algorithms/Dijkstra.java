package algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import util.DijkstraEntry;

public class Dijkstra {
	private Graph graph;
	private ArrayList<DijkstraEntry> table = new ArrayList<DijkstraEntry>();
	private LinkedList<DijkstraEntry> queue = new LinkedList<DijkstraEntry>();
	Iterator<Node> neighborIterator;
	public Dijkstra(Graph graph) {
		this.graph = graph;
	}

	public void fillTable(String source) {
		initialize(graph.getNode(source));

	}

	private void initialize(Node source) {
		for (Node n : graph.getNodeSet()) {
			table.add(new DijkstraEntry(n.toString()));
		}

		for (DijkstraEntry de : table) {
			if (de.getName().equals(source.toString())) {
				de.setDistance(0);
				de.setPrevNode(source.toString());
			}
		//	queue.add(source);
		}
		
	}

	private void print() {
		for (Iterator iterator = table.iterator(); iterator.hasNext();) {
			DijkstraEntry dijkstraEntry = (DijkstraEntry) iterator.next();
			System.out.println(dijkstraEntry.toString());
		}
	}
	
	
	private DijkstraEntry smallestDistanceInQueue(){
		DijkstraEntry lowestDij = new DijkstraEntry("test");
		lowestDij.setDistance(Integer.MAX_VALUE);
		for (Iterator iterator = queue.iterator(); iterator.hasNext();) {
			DijkstraEntry dij = (DijkstraEntry) iterator.next();
			if(dij.getDistance() < lowestDij.getDistance() && !dij.isOk()){
				lowestDij = dij;
			}			
		}
				
		return lowestDij;		
	}

	public void calc() {
		System.out.println(smallestDistanceInQueue());
		while (!queue.isEmpty()) {
			Node n1 = graph.getNode(queue.remove().getName());
			System.out.println(n1);
			neighborIterator =  n1.getNeighborNodeIterator();
			while(neighborIterator.hasNext()){
				System.out.println(n1+ neighborIterator.next().toString());
				
			}
		}

		print();
	}

}
