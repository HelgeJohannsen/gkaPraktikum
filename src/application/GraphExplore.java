package application;

import java.util.Iterator;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class GraphExplore {
    public static void main(String args[]) {
        new GraphExplore();
    }

    public GraphExplore() {
        Graph graph = new SingleGraph("tutorial 1");

        graph.addAttribute("ui.stylesheet", styleSheet);
        graph.setAutoCreate(true);
        graph.setStrict(false);
        graph.display();

        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CA", "C", "A");
        graph.addEdge("AD", "A", "D");
        graph.addEdge("DE", "D", "E");
        graph.addEdge("DF", "D", "F");
        graph.addEdge("EF", "E", "F");
        Edge edge = graph.getEdge("AB");
        graph.getEdge("AB").addAttribute("ui.label", edge.getId());
        edge.setAttribute("ui.class", "marked");
        
        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }

        explore(graph.getNode("A"));
    }

    public void explore(Node string) {
        Iterator<? extends Node> k = string.getBreadthFirstIterator();
        
        while (k.hasNext()) {
            Node next = k.next();
            next.setAttribute("ui.class", "marked");
            sleep();
        }
    }

    protected void sleep() {
        try { Thread.sleep(1000); } catch (Exception e) {}
    }

    protected String styleSheet =
        "node {" +
        "	fill-color: black;" +
        "}" +
        "node.marked {" +
        "	fill-color: red;" +
        "}";
}