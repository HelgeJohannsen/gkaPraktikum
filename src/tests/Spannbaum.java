package tests;

import org.graphstream.graph.Graph;
import org.junit.Assert;
import org.junit.Test;

import algorithms.KruskalSlow;
import algorithms.Prim;
import file_handling.FileControl;

public class Spannbaum {

	@Test
	public void testSpannBaum(){
		FileControl fc = new FileControl();
		fc.readFile("./src/graphDataOutput/" , "generated1");
		Graph generated1 = fc.getGraph();
		fc.readFile("./src/graphDataOutput/" , "generated2");
		Graph generated2 = fc.getGraph();
		fc.readFile("./src/graphDataOutput/" , "generated3");
		Graph generated3 = fc.getGraph();
		fc.readFile("./src/graphDataOutput/" , "graph03");
		Graph graph03 = fc.getGraph();
		fc.readFile("./src/graphDataOutput/" , "graph05");
		Graph graph05 = fc.getGraph();
		//Test Prim
		Assert.assertTrue(Prim.prim(generated1, generated1.getNode(0)) == 490);
		Assert.assertTrue(Prim.prim(generated2, generated2.getNode(0)) == 9283);
		Assert.assertTrue(Prim.prim(generated3, generated3.getNode(0)) == 18289);
		Assert.assertTrue(Prim.prim(graph03, graph03.getNode(0)) == 1898);
		Assert.assertTrue(Prim.prim(graph05, graph05.getNode(0)) == 10);
		//test Kruskal2
		Assert.assertTrue(KruskalSlow.calcSpanningTree(generated1) == 490);
		Assert.assertTrue(KruskalSlow.calcSpanningTree(generated2) == 9283);
		Assert.assertTrue(KruskalSlow.calcSpanningTree(generated3) == 18289);
		Assert.assertTrue(KruskalSlow.calcSpanningTree(graph03) == 1898);
		Assert.assertTrue(KruskalSlow.calcSpanningTree(graph05) == 10);
	}
	
}
