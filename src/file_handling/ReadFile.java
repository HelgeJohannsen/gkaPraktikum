package file_handling;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;

/**
 * 
 * @author Helge Johannsen, Timo Peters
 *
 */

public class ReadFile {
	private FileReader fr1;
	private FileReader fr2;
	private Pattern patternNoWeight;
	private Pattern patternWeight;
	private boolean isDirected = false;
	private boolean weight = false;
	private Graph graph;

	/**
	 * Die Klasse wandelt mit Hilfer der Reader Klasse .graph Dateien in Graph
	 * Objekte um.
	 * 
	 * @param reader
	 *            - Ein Reader-Objekt aus dem der FileStream ausgelesen wird.
	 */
	public ReadFile(Reader reader) {
		fr1 = reader.getFr();
		fr2 = reader.getFr2();
		patternNoWeight = Pattern.compile("([a-zA-Z0-9öäüÖÄÜ]*),([a-zA-Z0-9öäüÖÄÜ]*)");
		patternWeight = Pattern.compile("([a-zA-Z0-9öäüÖÄÜ]*),([a-zA-Z0-9öäüÖÄÜ]*) *:: *([0-9]* *);");
		checkGraph();
//		System.out.println("weight = " + weight);
//		System.out.println("directed = " + isDirected);
	}

	/**
	 * Die Methode überprüft, ob der Graph aus der Datei gerichtet oder
	 * ungerichtet ist. Außerdem wird überprüft, ob die Kanten ein Gewicht haben
	 * oder nicht.
	 */
	private void checkGraph() {
		Scanner sc = new Scanner(fr1);
		while (sc.hasNext()) {
			Matcher matcherNoWeight;
			Matcher matcherWeight;
			String line = sc.nextLine();
			matcherWeight = patternWeight.matcher(line);
			matcherNoWeight = patternNoWeight.matcher(line);
			if (line.matches("(# *directed;*)")) {
				isDirected = true;
			}

			if (matcherWeight.find()) {
				weight = true;
			} else if (matcherNoWeight.find()) {
				weight = false;
			}
		}
		sc.close();
	}

	/**
	 * Es wird ein Graph aus der eingelesenen Datei erstellt und die jeweiligen
	 * Knoten und Kanten werden hinzugefügt.
	 * 
	 * @param graphName
	 * @return Den erstellten Graph als Graph-Objekt
	 * @throws IOException
	 */
	public Graph handle(String graphName) throws IOException {
		Scanner sc = new Scanner(fr2);
		Graph graph = new MultiGraph(graphName);
		graph.addAttribute("hasWeight", weight); // wird benötigt im FileWrite
		graph.setStrict(isDirected); // wird benötigt im FileWrite
		Matcher matcher;
		Pattern patternToUse;
		int weightInt = 0;

		if (weight) {
			patternToUse = patternWeight;
		} else {
			patternToUse = patternNoWeight;
		}

		while (sc.hasNext()) {
			String line = sc.nextLine();
			matcher = patternToUse.matcher(line);

			if (matcher.find()) {

				if (graph.getNode(matcher.group(1)) == null) {
					graph.addNode(matcher.group(1));
				}

				if (graph.getNode(matcher.group(2)) == null) {
					graph.addNode(matcher.group(2));
				}

				graph.addEdge(matcher.group(1) + matcher.group(2), matcher.group(1), matcher.group(2),
						graph.isStrict());

				if (weight) {
					// fügt erstmal jede Gewichtung unabhängig vom Datentyp
					// hinzu
					// if(weightDouble== null){
					// weightDouble = 1000;
					// }
					if (matcher.group(3).equals("")) {
						weightInt = 99;
					} else {
						weightInt = Integer.parseInt(matcher.group(3).trim());
					}

					graph.getEdge(matcher.group(1) + matcher.group(2)).addAttribute("weight", weightInt);
				}
			}
		}
		sc.close();
		return graph;
	}

}
