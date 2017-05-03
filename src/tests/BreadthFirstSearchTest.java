package tests;

import static org.junit.Assert.*;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.junit.Before;
import org.junit.Test;

import algorithms.BreadthFirstSearch;

public class BreadthFirstSearchTest {
	Graph bfsGraph = new MultiGraph("bfsGraph");

	@Before
	public void setUp() {
		bfsGraph.addNode("A");
		bfsGraph.addNode("B");
		bfsGraph.addNode("C");
		bfsGraph.addNode("D");
		bfsGraph.addNode("E");
		bfsGraph.addNode("F");
		bfsGraph.addNode("G");
		bfsGraph.addNode("H");

		bfsGraph.addEdge("AB", "A", "B");
		bfsGraph.addEdge("AC", "A", "C");
		bfsGraph.addEdge("BD", "B", "D");
		bfsGraph.addEdge("CE", "C", "E");
		bfsGraph.addEdge("CF", "C", "F");
		bfsGraph.addEdge("EG", "E", "G");
	}

	@Test
	public void testBFS() {
		assertEquals(3, BreadthFirstSearch.getSteps("A", "G", bfsGraph));
		assertEquals(-1, BreadthFirstSearch.getSteps("A", "H", bfsGraph));
	}

}
