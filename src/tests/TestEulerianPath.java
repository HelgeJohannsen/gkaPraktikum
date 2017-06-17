package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.junit.Test;

import algorithms.BreadthFirstSearch;
import algorithms.Hierholz;
import file_handling.FileControl;

public class TestEulerianPath {
	// kleine gespeicherte Graphen laden
	FileControl fc = new FileControl();
	Graph eulerKreisPositiv1 = fc.readFile("./src/graphDataOutput/" , "eulerKreisPositiv1");
	Graph eulerKreisPositiv2 = fc.readFile("./src/graphDataOutput/" , "eulerKreisPositiv2");
	
	Graph HausVomNikolaus = fc.readFile("./src/graphDataOutput/" , "HausVomNikolaus");
	
	Graph eulerKreisNegativ1 = fc.readFile("./src/graphDataOutput/" , "eulerKreisNegativ1");
	Graph eulerKreisNegativ2 = fc.readFile("./src/graphDataOutput/" , "eulerKreisNegativ2");
	
	@Test
	public void testHierholzer() {
	// positive Tests	
		assertEquals(testCircle(eulerKreisPositiv1), true);
	//	assertEquals(testCircle(eulerKreisPositiv2), true);
	//	assertEquals(testCircle(HausVomNikolaus), true);
	// negative Tests	
		assertEquals(testCircle(eulerKreisNegativ1), false);
		assertEquals(testCircle(eulerKreisNegativ2), false);
	}
	
	@Test	
	public void testFleury() {
	// positive Tests	
		assertEquals(testCircle(eulerKreisPositiv1), true);
	//	assertEquals(testCircle(eulerKreisPositiv2), true);
	//	assertEquals(testCircle(HausVomNikolaus), true);
	// negative Tests	
		assertEquals(testCircle(eulerKreisNegativ1), false);
		assertEquals(testCircle(eulerKreisNegativ2), false);
	}
	
	
	
	public static boolean testCircle(Graph graph) {
		 ArrayList<Node> kantenfolge = Hierholz.getPath(graph);
		 ArrayList<Edge> edgesToVisit = new ArrayList();
		for (Iterator iterator = graph.getEdgeIterator(); iterator.hasNext();) {
			Edge edgeToAdd = (Edge) iterator.next();
			edgesToVisit.add(edgeToAdd);
		}

		Node src = null;
		for (Iterator iterator = kantenfolge.iterator(); iterator.hasNext();) {
			Node trg = (Node) iterator.next();
			if (src != null && trg != null) {
				Node srcNode = graph.getNode(src.toString());
				Edge e1 = srcNode.getEdgeBetween(trg);
				if (e1 != null) {
					edgesToVisit.remove(e1);
				}else{
					return false;
				}
			}
			src = trg;
		}		
		return (edgesToVisit.size() == 0);
	
	}
}
