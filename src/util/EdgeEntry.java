package util;

public class EdgeEntry implements Comparable<EdgeEntry>{
	String name;
	int weight;
	String id;
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public EdgeEntry(String name, int weight, String id) {
		super();
		this.name = name;
		this.weight = weight;
		this.id = id;
	}
	public String getName() {
		System.out.println(name);
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int compareTo(EdgeEntry arg0) {
		if(this.getWeight()< arg0.getWeight()){
			return -1;
		}
		if(this.getWeight()< arg0.getWeight()){
			return 1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "EdgeEntry [name=" + name + ", weight=" + weight + "]";
	}
	
}
