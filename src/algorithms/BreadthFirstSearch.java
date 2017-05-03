package algorithms;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import exceptions.SourceAndTargetEqualsException;

public class BreadthFirstSearch {

	public static int getSteps(String source, String target, Graph graph){
		Node s = graph.getNode(source);
		Node t = graph.getNode(target);
		System.out.println("Source: " + s + " Target:" + t);
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.add(s);
		s.addAttribute("step", 0);
		Iterator<Node> bfs;
		int counter = 0;

		while (!queue.isEmpty()) {
			Node parentNode = queue.remove();
			bfs = parentNode.getNeighborNodeIterator();
			while (bfs.hasNext()) {
				Node next = bfs.next();
				if (next.getAttribute("step") == null) {
					counter = parentNode.getAttribute("step");
					next.setAttribute("step", counter + 1);
					// System.out.print(next.toString() + " " +
					// next.getAttribute("step"));
					queue.add(next);
					if (next.equals(t)) {
						System.out.println("Distance " + ((counter + 1)));
						return counter + 1;
					}

				}
			}
		}
		return -1;

		// Boolean ende = true;
		//
		// while (ende) {
		// Node oldNode = queue2.remove();
		//// System.out.println("Zielknoten:" + oldNode.getAttribute("step"));
		// bfs = oldNode.getNeighborNodeIterator();
		// HashSet<Node> s1 = new HashSet();
		// HashSet<Node> s2 = new HashSet();
		// while (bfs.hasNext()) {
		//
		// Node newNode = bfs.next();
		// int newWeight = newNode.getAttribute("step");
		// int oldWeight = oldNode.getAttribute("step");
		//
		//// System.out.println("old:" + oldWeight + "new:"+newWeight);
		// if ((newWeight < oldWeight) &&!s1.contains(newNode)) {
		// //System.out.println(newNode.toString()+":::" + newWeight);
		// queue2.add(newNode);
		// s1.add(newNode);
		// s2.add(newNode);
		// }
		//
		// if(oldNode.equals(s)){
		// ende = false;
		// }
		//// for (Iterator iterator = s2.iterator(); iterator.hasNext();) {
		//// Node node = (Node) iterator.next();
		//// System.out.print(node);
		//// }
		// }
		//
		// }
	}
}
