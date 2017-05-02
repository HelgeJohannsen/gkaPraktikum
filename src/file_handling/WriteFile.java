package file_handling;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

/*
 * Die write Methode bekommt einen Graphen übergeben und erstellt daraus eine .graph Datei
 * um dies tun zu können muss auf die isDirected und hasWeight Attribute des Graphen zugeggriffen werden
 * 
 */
public class WriteFile {
	public static void write(Graph graph, String src) throws IOException {
		FileWriter fw = new FileWriter(src + graph.toString() + ".graph");
		BufferedWriter bw = new BufferedWriter(fw);

		if (graph.isStrict()) {
			bw.write("#directed\n");
		}
		
		if ((boolean) graph.getAttribute("hasWeight")) {
			for (Edge edge : graph.getEachEdge()) {
				bw.write(edge.getSourceNode().toString() + "," + edge.getTargetNode().toString() + " :: "
						+ edge.getAttribute("weight") + ";\n");
			}
			
		} else {
			for (Edge edge : graph.getEachEdge()) {
				bw.write(edge.getSourceNode().toString() + "," + edge.getTargetNode().toString() + ";\n");
			}
		}
		bw.close();
	}
}
