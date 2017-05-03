package tests;

import static org.junit.Assert.*;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.junit.Before;
import org.junit.Test;

import file_handling.FileControl;

public class FileHandlingTest {

	FileControl controller = new FileControl();

	// Ungerichteter Graph
	Graph undirectedGraph = new MultiGraph("undirectedGraph");

	// Gerichteter Graph
	Graph directedGraph = new MultiGraph("directedGraph");

	// Ungerichteter Graph mit gewichteten Kanten
	Graph undirectedWeightGraph = new MultiGraph("undirectedWeightGraph");

	// Gerichteter Graph mit gewichteten Kanten
	Graph directedWeightGraph = new MultiGraph("directedWeightGraph");

	@Before
	public void setUp() {

		undirectedGraph.addAttribute("hasWeight", false);
		// Knoten ungerichteter Graph
		undirectedGraph.addNode("A");
		undirectedGraph.addNode("B");
		undirectedGraph.addNode("C");
		undirectedGraph.addNode("D");
		// Kanten ungerichteter Graph
		undirectedGraph.addEdge("AC", "A", "C");
		undirectedGraph.addEdge("AD", "A", "D");
		undirectedGraph.addEdge("BD", "B", "D");
		undirectedGraph.addEdge("CD", "C", "D");

		directedGraph.addAttribute("hasWeight", false);
		// Knoten gerichteter Graph
		directedGraph.addNode("A");
		directedGraph.addNode("B");
		directedGraph.addNode("C");
		directedGraph.addNode("D");
		// Kanten gerichteter Graph
		directedGraph.addEdge("AC", "A", "C", true);
		directedGraph.addEdge("AD", "A", "D", true);
		directedGraph.addEdge("BD", "B", "D", true);
		directedGraph.addEdge("CD", "C", "D", true);

		undirectedWeightGraph.addAttribute("hasWeight", true);
		// Knoten ungerichteter Graph mit gewichteten Kanten
		undirectedWeightGraph.addNode("A");
		undirectedWeightGraph.addNode("B");
		undirectedWeightGraph.addNode("C");
		undirectedWeightGraph.addNode("D");
		// Kanten ungerichteter Graph mit gewichteten Kanten
		undirectedWeightGraph.addEdge("AC", "A", "C");
		undirectedWeightGraph.addEdge("AD", "A", "D");
		undirectedWeightGraph.addEdge("BD", "B", "D");
		undirectedWeightGraph.addEdge("CD", "C", "D");
		// Gewichte hinzufügen
		undirectedWeightGraph.getEdge("AC").addAttribute("weight", 2);
		undirectedWeightGraph.getEdge("AD").addAttribute("weight", 3);
		undirectedWeightGraph.getEdge("BD").addAttribute("weight", 1);
		undirectedWeightGraph.getEdge("CD").addAttribute("weight", 5);

		directedWeightGraph.addAttribute("hasWeight", true);
		// Knoten gerichteter Graph mit gewichteten Kanten
		directedWeightGraph.addNode("A");
		directedWeightGraph.addNode("B");
		directedWeightGraph.addNode("C");
		directedWeightGraph.addNode("D");
		// Kanten gerichteter Graph mit gewichteten Kanten
		directedWeightGraph.addEdge("AC", "A", "C", true);
		directedWeightGraph.addEdge("AD", "A", "D", true);
		directedWeightGraph.addEdge("BD", "B", "D", true);
		directedWeightGraph.addEdge("CD", "C", "D", true);
		// Gewichte hinzufügen
		directedWeightGraph.getEdge("AC").addAttribute("weight", 2);
		directedWeightGraph.getEdge("AD").addAttribute("weight", 3);
		directedWeightGraph.getEdge("BD").addAttribute("weight", 1);
		directedWeightGraph.getEdge("CD").addAttribute("weight", 5);

	}

	@Test
	public void testWrite() {
		// Write Files
		controller.writeFile("./src/graphDataOutput/", undirectedGraph);
		controller.writeFile("./src/graphDataOutput/", directedGraph);
		controller.writeFile("./src/graphDataOutput/", undirectedWeightGraph);
		controller.writeFile("./src/graphDataOutput/", directedWeightGraph);
		assertTrue(true);
	}

	@Test
	public void testRead() {
		assertTrue(true);
	}

	@Test
	public void testCompareTestWrite() {
		// Write Files
		controller.writeFile("./src/graphDataOutput/", undirectedGraph);
		controller.writeFile("./src/graphDataOutput/", directedGraph);
		controller.writeFile("./src/graphDataOutput/", undirectedWeightGraph);
		controller.writeFile("./src/graphDataOutput/", directedWeightGraph);
		
		//Read Files
		Graph readGraph1 = controller.readFile("./src/graphDataOutput/", "undirectedGraph");
		Graph readGraph2 = controller.readFile("./src/graphDataOutput/", "directedGraph");
		Graph readGraph3 = controller.readFile("./src/graphDataOutput/", "undirectedWeightGraph");
		Graph readGraph4 = controller.readFile("./src/graphDataOutput/", "directedWeightGraph");
		
		// Test gerichtet
		assertFalse(readGraph1.getEdge("AC").isDirected());
		assertTrue(readGraph2.getEdge("AC").isDirected());
		assertFalse(readGraph3.getEdge("AC").isDirected());
		assertTrue(readGraph4.getEdge("AC").isDirected());
		
		// Test gewichtet
		assertFalse(readGraph1.getAttribute("hasWeight"));
		assertFalse(readGraph2.getAttribute("hasWeight"));
		assertTrue(readGraph3.getAttribute("hasWeight"));
		assertTrue(readGraph4.getAttribute("hasWeight"));
		
		//Test Knoten und Kanten
		assertTrue(readGraph1.getNode("A").hasEdgeBetween("C"));
		assertTrue(readGraph1.getNode("A").hasEdgeBetween("D"));
		assertTrue(readGraph1.getNode("B").hasEdgeBetween("D"));
		assertTrue(readGraph1.getNode("C").hasEdgeBetween("D"));
		
		assertTrue(readGraph2.getNode("A").hasEdgeBetween("C"));
		assertTrue(readGraph2.getNode("A").hasEdgeBetween("D"));
		assertTrue(readGraph2.getNode("B").hasEdgeBetween("D"));
		assertTrue(readGraph2.getNode("C").hasEdgeBetween("D"));
		
		assertTrue(readGraph3.getNode("A").hasEdgeBetween("C"));
		assertTrue(readGraph3.getNode("A").hasEdgeBetween("D"));
		assertTrue(readGraph3.getNode("B").hasEdgeBetween("D"));
		assertTrue(readGraph3.getNode("C").hasEdgeBetween("D"));
		
		assertTrue(readGraph4.getNode("A").hasEdgeBetween("C"));
		assertTrue(readGraph4.getNode("A").hasEdgeBetween("D"));
		assertTrue(readGraph4.getNode("B").hasEdgeBetween("D"));
		assertTrue(readGraph4.getNode("C").hasEdgeBetween("D"));
		
		// Tets Gewichtungen
		assertEquals((int) readGraph3.getEdge("AC").getAttribute("weight"), 2);
		assertEquals((int) readGraph3.getEdge("AD").getAttribute("weight"), 3);
		assertEquals((int) readGraph3.getEdge("BD").getAttribute("weight"), 1);
		assertEquals((int) readGraph3.getEdge("CD").getAttribute("weight"), 5);
		
		assertEquals((int) readGraph4.getEdge("AC").getAttribute("weight"), 2);
		assertEquals((int) readGraph4.getEdge("AD").getAttribute("weight"), 3);
		assertEquals((int) readGraph4.getEdge("BD").getAttribute("weight"), 1);
		assertEquals((int) readGraph4.getEdge("CD").getAttribute("weight"), 5);
		
	}

}
