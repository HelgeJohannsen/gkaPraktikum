package util;

import org.graphstream.graph.Node;

public class DijkstraEntry  implements Comparable<DijkstraEntry> {
	private String name;
	private String prevNode = null;
	private int distance = Integer.MAX_VALUE;
	private boolean ok= false;
	
	public DijkstraEntry(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrevNode() {
		return prevNode;
	}

	public void setPrevNode(String prevNode) {
		this.prevNode = prevNode;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public String toString() {
		return "DijkstraEntry [name=" + name + ", prevNode=" + prevNode + ", distance=" + distance + ", ok=" + ok + "]";
	}

	@Override
	public int compareTo(DijkstraEntry arg0) {
		int retValue = 0;
		if(this.getDistance() < arg0.getDistance()){
			retValue = -1;
		};
		return retValue;
	}
	
	
}
