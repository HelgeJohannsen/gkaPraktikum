package algorithms;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import util.DijkstraEntry;

public class Dijkstra {
	
	public static void calc(Graph graph, Node src, Node trg){
		// Die Table Entrys werden in einer HashMap gespeichert um mit dem String zugriff auf den passenden DijkstraEntry zu haben
		HashMap<String , DijkstraEntry> table = new HashMap();
		// Liste der Route von src zu target
		List way = new List();
		ArrayList<DijkstraEntry> queue = new ArrayList();
		// die DijkstraEntry werdem erstellt
		for(Node n : graph.getEachNode()){
			DijkstraEntry de = new DijkstraEntry(n.toString());
			table.put(n.toString(), de);
		}
		// initialisierung des Startknoten 
		queue.add(table.get(src.toString()));
		table.get(src.toString()).setDistance(0);
		
		// 
		while(!queue.isEmpty()){
			// der Knoten mit der niedrigsten distanz wird der Queue entnommen 
			// der Comparator des TableEntry wird hier genutzt 
			Collections.sort(queue);

			
			Node srcNode = graph.getNode(queue.remove(0).getName());
						
			Iterator it = srcNode.getNeighborNodeIterator();
			while(it.hasNext()){
				table.get(srcNode.toString()).setOk(true);
				Node neighbor = (Node) it.next();
				// Distanz wird berechnet von bisheriger Distanz plus neuer Kante
				int newDistance = table.get(srcNode.toString()).getDistance() + (int) srcNode.getEdgeBetween(neighbor).getNumber("weight");
				// wenn diese niedriger ist wird es die neue distanz und dieser wird als previous Node gesetzt
				if(newDistance < table.get(neighbor.toString()).getDistance()){
				table.get(neighbor.toString()).setDistance(newDistance);
				table.get(neighbor.toString()).setPrevNode(srcNode.toString());
				}
				// wenn der Knoten noch nicht angeschaut wurde kommt er in die Warteschlange
				if(table.get(neighbor.toString()).isOk() == false){
				queue.add(table.get(neighbor.toString()));
				}
			}

		}
		
		
		Iterator it = table.entrySet().iterator();
		for(DijkstraEntry de : table.values()){
			System.out.println(de.toString());
		}
		
		String prevNode = trg.toString();
		while(!prevNode.equals(src.toString())){
			way.add(prevNode);
			System.out.println(prevNode);
			prevNode = table.get(prevNode).getPrevNode();


		}
	}	
	
	
	public static int distanceToStart(Graph graph, Node src, Node trg){
		// Die Table Entrys werden in einer HashMap gespeichert um mit dem String zugriff auf den passenden DijkstraEntry zu haben
		HashMap<String , DijkstraEntry> table = new HashMap();
		// Liste der Route von src zu target

		ArrayList<DijkstraEntry> queue = new ArrayList();
		// die DijkstraEntry werdem erstellt
		for(Node n : graph.getEachNode()){
			DijkstraEntry de = new DijkstraEntry(n.toString());
			table.put(n.toString(), de);
		}
		// initialisierung des Startknoten 
		queue.add(table.get(src.toString()));
		table.get(src.toString()).setDistance(0);
		
		// 
		while(!queue.isEmpty()){
			// der Knoten mit der niedrigsten distanz wird der Queue entnommen 
			// der Comparator des TableEntry wird hier genutzt 
			Collections.sort(queue);

			
			Node srcNode = graph.getNode(queue.remove(0).getName());
						
			Iterator it = srcNode.getNeighborNodeIterator();
			while(it.hasNext()){
				table.get(srcNode.toString()).setOk(true);
				Node neighbor = (Node) it.next();
				// Distanz wird berechnet von bisheriger Distanz plus neuer Kante
				int newDistance = table.get(srcNode.toString()).getDistance() + (int) srcNode.getEdgeBetween(neighbor).getNumber("weight");
				// wenn diese niedriger ist wird es die neue distanz und dieser wird als previous Node gesetzt
				if(newDistance < table.get(neighbor.toString()).getDistance()){
				table.get(neighbor.toString()).setDistance(newDistance);
				table.get(neighbor.toString()).setPrevNode(srcNode.toString());
				}
				// wenn der Knoten noch nicht angeschaut wurde kommt er in die Warteschlange
				if(table.get(neighbor.toString()).isOk() == false){
				queue.add(table.get(neighbor.toString()));
				}
			}

		}
		
		
		return table.get(trg.toString()).getDistance();

		
	}	
	public static List path(Graph graph, Node src, Node trg){
		// Die Table Entrys werden in einer HashMap gespeichert um mit dem String zugriff auf den passenden DijkstraEntry zu haben
		HashMap<String , DijkstraEntry> table = new HashMap();
		// Liste der Route von src zu target
		List way = new List();
		ArrayList<DijkstraEntry> queue = new ArrayList();
		// die DijkstraEntry werdem erstellt
		for(Node n : graph.getEachNode()){
			DijkstraEntry de = new DijkstraEntry(n.toString());
			table.put(n.toString(), de);
		}
		// initialisierung des Startknoten 
		queue.add(table.get(src.toString()));
		table.get(src.toString()).setDistance(0);
		
		// 
		while(!queue.isEmpty()){
			// der Knoten mit der niedrigsten distanz wird der Queue entnommen 
			// der Comparator des TableEntry wird hier genutzt 
			Collections.sort(queue);

			
			Node srcNode = graph.getNode(queue.remove(0).getName());
						
			Iterator it = srcNode.getNeighborNodeIterator();
			while(it.hasNext()){
				table.get(srcNode.toString()).setOk(true);
				Node neighbor = (Node) it.next();
				// Distanz wird berechnet von bisheriger Distanz plus neuer Kante
				int newDistance = table.get(srcNode.toString()).getDistance() + (int) srcNode.getEdgeBetween(neighbor).getNumber("weight");
				// wenn diese niedriger ist wird es die neue distanz und dieser wird als previous Node gesetzt
				if(newDistance < table.get(neighbor.toString()).getDistance()){
				table.get(neighbor.toString()).setDistance(newDistance);
				table.get(neighbor.toString()).setPrevNode(srcNode.toString());
				}
				// wenn der Knoten noch nicht angeschaut wurde kommt er in die Warteschlange
				if(table.get(neighbor.toString()).isOk() == false){
				queue.add(table.get(neighbor.toString()));
				}
			}

		}
		
		
		Iterator it = table.entrySet().iterator();
		for(DijkstraEntry de : table.values()){
			System.out.println(de.toString());
		}
		
		return way;

		
	}	
}
