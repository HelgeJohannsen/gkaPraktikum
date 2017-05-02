package file_handling;

import java.io.IOException;

import org.graphstream.graph.Graph;

public class FileControl {
	// Graph graph;

	public Graph readFile(String src, String graphName) {
		Reader reader = new Reader(src + graphName + ".graph");

		Graph graph = null;

		ReadFile fh = new ReadFile(reader);

		try {
			graph = fh.handle(graphName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Graph konnte nicht eingelesen werden. Fehler im FileHandler");
			e.printStackTrace();
		}
		return graph;
	}

	// public void writeFile(String path) {
	// try {
	// WriteFile.write(graph, path);
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// System.out.println("Graph Datei konnte nicht erstellt werden. Fehler im
	// WriteFile");
	// e.printStackTrace();
	// }
	// }

	public void writeFile(String path, Graph graph) {
		try {
			WriteFile.write(graph, path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Graph Datei konnte nicht erstellt werden. Fehler im WriteFile");
			e.printStackTrace();
		}
	}

//	public Graph getGraph() {
//		return graph;
//	}
//
//	public void setGraph(Graph graph) {
//		this.graph = graph;
//	}

	public Graph genGraph(int anzahlKnoten, int anzahlKanten, String graphName, Boolean connected, Boolean isStrict) {
		GraphGenerator gra = new GraphGenerator(anzahlKnoten, anzahlKanten, graphName, connected, isStrict);
		return gra.generate();

	}
}
