package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.graphstream.algorithm.Algorithm;

import org.graphstream.algorithm.Dijkstra.Element;
import org.graphstream.algorithm.Kruskal;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.IdAlreadyInUseException;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.omg.Messaging.SyncScopeHelper;

import algorithms.BFS;
import algorithms.BreadthFirstSearch;
import algorithms.Dijkstra;
import algorithms.Dijkstra2;
import algorithms.Fleury;
import algorithms.Hierholz;
import algorithms.HierholzerTest;
import algorithms.Kruaskal;
import algorithms.KruskalSlow;
import algorithms.Prim;
import exceptions.SourceAndTargetEqualsException;
import file_handling.FileControl;
import file_handling.ReadFile;
import file_handling.GraphGenerator;
import file_handling.Reader;
import file_handling.WriteFile;
import tests.TestEulerianPath;

/**
 * 
 * @author Helge Johannsen, Timo Peters
 *
 */

public class Main {
	public static void main(String args[]) throws IOException, SourceAndTargetEqualsException {
		Graph euler = GraphGenerator.generateEuler(5, "euler");
		euler.display();

//		 FileControl fc = new FileControl();
//		//fc.readFile("./src/graphDataInput/" , "graph01");
//		// fc.writeFile("./src/graphDataOutput/");
//		Graph gen = fc.readFile("./src/graphDataOutput/" , "HausVomNikolaus");
//		// Graph gen = fc.genGraph(12,16, "euler1", true, false);
//		  gen.display();
		// fc.writeFile("./src/graphDataOutput/", gen);
		// Graph gen = fc.readFile("./src/graphDataOutput/", "generated2");
		// Dijkstra d1= new Dijkstra();
		// d1.calc(gen, gen.getNode("VZE"), gen.getNode("SIP"));
		// BreadthFirstSearch.getSteps(gen, "v3", "vs");
		// Dijkstra2 d2 = new Dijkstra2(gen, "v3", "v5");
		// d2.init();
		// d2.calc();
		// d2.showWay();
		// int kantengewicht = 0;
		// KruskalSlow.calcSpanningTree(gen);
		// Prim.prim(gen, gen.getNode(0));
		// String css = "edge .notintree {size:1px;fill-color:gray;} " +
		// "edge .intree {size:3px;fill-color:black;}";
		//
		// gen.addAttribute("ui.stylesheet", css);
		// Kruskal kruskal = new Kruskal("ui.class", "intree", "notintree");
		//
		// kruskal.init(gen);
		// kruskal.compute();

		// for(Edge e : gen.getEachEdge()){
		// //System.out.println(e.getAttribute("ui.class").toString());
		// if(e.getAttribute("ui.class").equals("intree")){
		// kantengewicht += (int) e.getAttribute("weight");
		// }
		// }
		// Algorithm alg = new Kruskal();

		// gen.display();
		// fc.writeFile("./src/graphDataOutput/", gen);
		// Graph genGraph = gra.generate();
		// graphicController.initGraph(gen);

		// fc.writeFile("./src/graphDataOutput/", gen);
		// System.out.println(kantengewicht);

		// System.out.println(KruskalSlow.calcSpanningTree(gen));
		// genGraph.display();
		// Prim.prim(gen, gen.getNode(0));
		// Kruaskal k1 = new Kruaskal(gen);
		// k1.calc();
		// System.out.println(genGraph.getEdge(0).getAttribute("weight").toString());

		// Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null,
		// "weight");
		// dijkstra.init(gen);
		// dijkstra.setSource(gen.getNode("DWK"));
		// dijkstra.init(gen);
		// dijkstra.compute();

		// System.out.println(dijkstra.getPath(gen.getNode("EHP")));

		// Hierholzer.getPath(gen);
//		ArrayList<Node> kantenfolge = new ArrayList();
//		kantenfolge.add(gen.getNode("A"));
//		kantenfolge.add(gen.getNode("C"));
//		kantenfolge.add(gen.getNode("B"));
//		kantenfolge.add(gen.getNode("A"));
//		
//		kantenfolge.add(gen.getNode("B"));
//		kantenfolge.add(gen.getNode("E"));
//		kantenfolge.add(gen.getNode("D"));
//		kantenfolge.add(gen.getNode("B"));
//		
//		kantenfolge.add(gen.getNode("F"));
//		kantenfolge.add(gen.getNode("G"));
//		kantenfolge.add(gen.getNode("B"));
//		kantenfolge.add(gen.getNode("I"));
//		kantenfolge.add(gen.getNode("H"));
//		kantenfolge.add(gen.getNode("B"));
//		kantenfolge.add(gen.getNode("F"));
//	
		
//		for (Iterator iterator = gen.getEdgeIterator(); iterator.hasNext();) {
//			Edge edgeToAdd = (Edge) iterator.next();
//			edgeToAdd.setAttribute("visited", false);
//			}
//		
//		
//		Node src = null;
//		for (Iterator iterator = kantenfolge.iterator(); iterator.hasNext();) {
//			Node trg = (Node) iterator.next();
//			if(src != null && trg != null){
//				System.out.println(src + " " + trg);
//				Node srcNode = gen.getNode(src.toString());
//				Edge e1 = srcNode.getEdgeBetween(trg);
//				if(e1 != null){
//				e1.setAttribute("visited", true);
//				}
//			}
//			src = trg;
//		}
//
//			
//		for (Iterator iterator = gen.getEdgeIterator(); iterator.hasNext();) {
//			Edge edgeToAdd = (Edge) iterator.next();
//			if((boolean) edgeToAdd.getAttribute("visited") == false){
//				
//			}
//			
//			}
	
		
//		Hierholz.getPath(gen);
//		System.out.println(TestEulerianPath.testCircle(gen, kantenfolge));
	}
}
