package file_handling;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.omg.Messaging.SyncScopeHelper;

import application.graphicController;

public class GraphGenerator {
	final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	final String alphabet2 = "0123456789";
	final int alphabetLength2 = alphabet2.length();
	final int alphabetLength = alphabet.length();
	int anzahlKnoten = 20;
	int anzahlKanten = 40;
	Boolean connected = true;
	Boolean isStrict;
	Boolean eulerCicle;
	Set<String> nodeSet = new HashSet<String>();
	Set<String> edgeSet = new HashSet<String>();
	String graphName;
	Graph graph;
	Iterator it;
	LinkedList<String> listeKnoten = new LinkedList<String>();

	public GraphGenerator(int anzahlKnoten, int anzahlKanten, String graphName, Boolean connected, Boolean isStrict) {
		super();
		this.anzahlKnoten = anzahlKnoten;
		this.anzahlKanten = anzahlKanten;
		this.graphName = graphName;
		this.connected = connected;
		this.isStrict = isStrict;
		// this.eulerCicle = eulerCircle;
		this.graph = new MultiGraph(graphName);
	}

	Random r = new Random();

	void createNodes() {
		while (nodeSet.size() < anzahlKnoten) {
			char n = 0, m = 0, l = 0, k = 0, j = 0;
			for (int y = 0; y < 4; y++) {
				n = alphabet.charAt(r.nextInt(alphabetLength));
				m = alphabet.charAt(r.nextInt(alphabetLength));
				l = alphabet.charAt(r.nextInt(alphabetLength));
				k = alphabet.charAt(r.nextInt(alphabetLength));
				j = alphabet.charAt(r.nextInt(alphabetLength));

			}
			String nodeS = Character.toString(n) + Character.toString(m) + Character.toString(l) + Character.toString(k)
					+ Character.toString(j);
			System.out.println("erstelle Knoten:" + nodeS + " " + (nodeSet.size() + 1));
			nodeSet.add(nodeS);
		}

		it = nodeSet.iterator();
		while (it.hasNext()) {
			String s1 = (String) it.next();
			graph.addNode(s1);
			listeKnoten.add(s1);
		}
	}

	void connectNodes() {
		String source = null;
		String target = null;
		Boolean first = true;
		it = nodeSet.iterator();
		while (it.hasNext()) {
			if (first) {
				target = (String) it.next();
				first = false;
			} else {
				source = target;
				target = (String) it.next();
				graph.addEdge(source + target, source, target);
				System.out.println("connected" + source + target);
				edgeSet.add(source + target);
			}

		}
	}

	public Graph generate() {
		createNodes();
		if (connected) {
			connectNodes();
		}
		System.out.println(edgeSet.size());
		while (edgeSet.size() < anzahlKanten) {
			String src = listeKnoten.get(r.nextInt(anzahlKnoten));
			String trg = listeKnoten.get(r.nextInt(anzahlKnoten));
			if ((!edgeSet.contains(src + trg) && !edgeSet.contains(trg + src))) {
				graph.addEdge(src + trg, src, trg);
				edgeSet.add(src + trg);
				System.out.println("adding edge" + edgeSet.size() + "" + anzahlKanten);
			}
		}
		for (Edge e : graph.getEachEdge()) {
			int d1 = r.nextInt(alphabetLength2);
			e.addAttribute("weight", d1);
			graphicController.labelEdge(e);
		}
		graph.setStrict(isStrict);
		graph.addAttribute("hasWeight", true);
		return graph;

	}

	public static Graph generateEuler(int nodesAmount, String eulerGraphName) {
		// Graph erstellen
		Graph eulerGraph = new MultiGraph(eulerGraphName);

		Integer nodeName = new Integer(0);
		eulerGraph.addNode(nodeName.toString());
		nodeName += 1;

		// Knoten erstellen
		for (int n = 1; n < nodesAmount; n++) {
			// Knoten hinzufügen
			eulerGraph.addNode(nodeName.toString());

			// Knoten an den Graphen anbinden um sicherzustellen, dass der Graph
			// zusammenhängend ist.
			List<Node> nodeList = new ArrayList<>(eulerGraph.getNodeSet());
			int randNodeIndex = (int) (Math.random() * nodeList.size());
			Node randomNode = nodeList.get(randNodeIndex);
			// Keine Kante zu sich selbst
			if (randomNode.equals(eulerGraph.getNode(nodeName))) {
				while (randomNode.equals(eulerGraph.getNode(nodeName))) {
					randNodeIndex = (int) (Math.random() * nodeList.size() - 1);
					randomNode = nodeList.get(randNodeIndex);
				}
			}
			eulerGraph.addEdge(nodeName.toString() + randomNode.toString(), eulerGraph.getNode(nodeName), randomNode);

			// Den Namen für den nächsten Knoten "hochzählen"
			nodeName += 1;
		}
		List<Edge> edgeList = new ArrayList<>(eulerGraph.getEdgeSet());
		System.out.println(edgeList.toString());

		// so lange Kanten hinzufügen, bis alle Knotengrade gerade sind
		List<Node> oddNodes = getOddNodes(eulerGraph);
		while (oddNodes.size() != 0) {
			System.out.println(oddNodes.toString());
			Node s = oddNodes.get(0);
			int randomIndex = 0;
			while (randomIndex == 0) {
				randomIndex = (int) (Math.random() * oddNodes.size() - 1);
				if (oddNodes.size() == 2) {
					randomIndex = 1;
				}
			}
			Node t = oddNodes.get(randomIndex);
			Edge edge = s.getEdgeBetween(t);
			if (edge == null) {
				eulerGraph.addEdge(s.toString() + t.toString(), s, t);
			}
			oddNodes = getOddNodes(eulerGraph);
			System.out.println("Anzahl ungerader Knotengrade: " + oddNodes.size());
		}

		return eulerGraph;
	}

	/**
	 * 
	 * @param graph
	 * @return True wenn genau 0 oder 2 ungerade Knotengrade in dem Graphen
	 *         existieren.
	 */
	private static boolean isEulerDegree(Graph graph) {
		int oddNumberCounter = 0;
		for (Node node : graph.getNodeSet()) {
			if (oddNumberCounter > 2) {
				return false;
			} else {
				if (node.getDegree() % 2 != 0) {
					oddNumberCounter += 1;
				}
			}
		}
		if (oddNumberCounter == 1) {
			return false;
		} else {
			return true;
		}
	}

	private static List<Node> getOddNodes(Graph graph) {
		List<Node> oddNodes = new ArrayList<>();
		for (Node node : graph.getNodeSet()) {
			System.out.print(node.toString() + ": ");
			System.out.println(node.getDegree());
			if (node.getDegree() % 2 != 0) {
				oddNodes.add(node);
			}
		}
		return oddNodes;
	}

}
