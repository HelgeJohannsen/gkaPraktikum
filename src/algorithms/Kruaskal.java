package algorithms;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;


import application.graphicController;
import util.EdgeEntry;

public class Kruaskal {
	public Graph graphSpannbaum = new MultiGraph("SpannBaum") ;
	public  Graph inputGraph;
	LinkedList<EdgeEntry> sortedEdges = new LinkedList<EdgeEntry>();
	int lengthAll=0;
	public Kruaskal(Graph inputGraph) {
		this.inputGraph = inputGraph;
		graphSpannbaum.setStrict(false);
		graphicController.initGraph(graphSpannbaum);
		for(Node node : inputGraph.getEachNode()){
			graphSpannbaum.addNode(node.toString());

		}
		
		graphicController.initGraph(graphSpannbaum);
	}


	public void sortEdges(){
		for(Edge edge : inputGraph.getEachEdge()){	
		EdgeEntry edgeE = new EdgeEntry(edge.toString(), edge.getAttribute("weight"), edge.getId());
		sortedEdges.add(edgeE);
		}
		Collections.sort(sortedEdges);
		for (Iterator iterator = sortedEdges.iterator(); iterator.hasNext();) {
			EdgeEntry edgeEntry = (EdgeEntry) iterator.next();
		}
		
	}
	
	public void addEdge(String id, int weight){
		Edge edge = inputGraph.getEdge(id);
		String source = edge.getSourceNode().toString();
		String target = edge.getTargetNode().toString();	
		String s = source.toString() + target.toString();
		graphSpannbaum.addEdge(s, source, target);
		Edge newEdge = graphSpannbaum.getEdge(s);
		newEdge.addAttribute("weight",weight );
		lengthAll += weight;
		graphicController.colorNode(edge.getTargetNode(), "red");
		graphicController.colorNode(edge.getSourceNode(), "red");
		graphicController.colorEdge(edge , "green");	
		graphicController.labelEdge(edge);
		
	}
	
	
	
	public  int calc(){
		long start = System.currentTimeMillis();
		sortEdges();
		EdgeEntry edgeEntry = sortedEdges.removeFirst();
		String id = edgeEntry.getId();
		int weight = (int) edgeEntry.getWeight();

		addEdge(id, weight);
			while(!sortedEdges.isEmpty()){
			EdgeEntry edgeE= sortedEdges.removeFirst();
			Edge edge = inputGraph.getEdge(edgeE.getId());
			String src = edge.getSourceNode().toString();
			String trg = edge.getTargetNode().toString();
			Boolean isCircle = BFS.findWay(graphSpannbaum, src, trg);
			System.out.println( "  id: " + edgeE.getId() +"    Source: " +src + "target: " + trg + isCircle);
			if(!isCircle){
				addEdge(edgeE.getId(),edgeE.getWeight());
			}
		}
		
		System.out.println(lengthAll + " " + (System.currentTimeMillis() - start));
		return lengthAll;
		
	}
    protected static void sleep() {
        try { Thread.sleep(1000); } catch (Exception e) {}
    }
	
	
}
