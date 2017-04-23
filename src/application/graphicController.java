package application;

import java.io.IOException;
import java.util.Iterator;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class graphicController {
	public static void initGraph(Graph graph) {
         graph.addAttribute("ui.stylesheet", styleSheet);
     //   graph.setAutoCreate(true);
		labelNodes(graph);
		labelEdges(graph);
	//	colorNodes(graph);
	//	colorEdges(graph);
	}

	public static void colorNode(Node node1, String color){
         node1.setAttribute("ui.class", color);
    
	}
	
	public static void colorEdge(Edge edge, String color){
        	edge.setAttribute("ui.class", color);       
	}
	
	public static void colorEdges(Graph graph){
     for (Edge edge : graph.getEachEdge()) {
         edge.addAttribute("ui.class","black");
     }
	}
	
	public static void colorNodes(Graph graph){
       for (Node node : graph) {
            node.addAttribute("ui.class", "blue;");

        }
	}
	
	public static  void labelNodes(Graph graph) {
        		for (Node node : graph.getEachNode()) {
			node.addAttribute("ui.label", node.toString());
		}
	}

	public static void labelEdges(Graph graph) {
		for (Edge edge : graph.getEachEdge()) {
			if(edge.getAttribute("weight") != null){
			edge.addAttribute("ui.label", edge.getNumber("weight"));
			}
		}
	}
	public static void labelEdge(Edge edge) {
			edge.addAttribute("ui.label", edge.getNumber("weight"));
	}

    protected static void sleep() {
        try { Thread.sleep(10); } catch (Exception e) {}
    }


		
    	    protected static String styleSheet =
            "node {" +
            "	fill-color: black;" +
            "}" +
            "node.red {" +
            "	fill-color: red;" +
            "}"+
            "node.blue {" +
            "	fill-color: blue;" +
            "}"+
            "node.green {" +
            "	fill-color: green;" +
            "}"+
    		
            "edge {" +
            "	fill-color: black;" +
            "}" +
            "edge.red {" +
            "	fill-color: red;" +
            "}" + 
            "edge.blue {" +
            "	fill-color: blue;" +
            "}" +
            "edge.green {" +
            "	fill-color: green;" +
            "}"
            ;
    
}
