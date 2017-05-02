package file_handling;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * 
 * @author Helge Johannsen, Timo Peters
 *
 */

public class Reader {

	private String path;

	/**
	 * Die Klasse liest eine Datei ein und erstellt einen FileStream.
	 * 
	 * @param path
	 *            - Der Pfad zu der zu lesenden Datei.
	 */
	public Reader(String path) {
		this.path = path;
	}

	public FileReader getFr() {
		try {
			FileReader fr = new FileReader(path);
			return fr;
		} catch (FileNotFoundException e) {
			System.out.println("Datei zum Einlesen nicht gefunden");
			return null;
		}
	}

	public FileReader getFr2() {
		try {
			FileReader fr = new FileReader(path);
			return fr;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

}
