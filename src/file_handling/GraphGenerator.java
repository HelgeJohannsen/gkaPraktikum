package file_handling;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
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

}
