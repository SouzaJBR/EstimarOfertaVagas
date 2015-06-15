package br.ufjf.coordenacao.OfertaVagas.model;

import java.util.ArrayList;

public class Class implements Comparable<Class> {

	String id;
	ArrayList<Class> prerequisite = new ArrayList<Class>();
	private int weight;
	
	public Class(String id, int weight)
	{
		this.id = id;
		this.weight = weight;
	}
	
	public Class(String id) {
		this(id, 4);
	}
	
	public void addPrerequisite(Class c) {
		this.prerequisite.add(c);
	}

	public ArrayList<Class> getPrerequisite() {
		return this.prerequisite;
	}
	
	public String getId() { return id; }
	
	public int getWeight() { return this.weight; }
	public void setWeight(int weight) { this.weight = weight; }
	
	
	@Override
	public String toString() {
		String o = id + "(" + weight + ")";
		if (!prerequisite.isEmpty()) {
			o += " -> [pre  ";
			for (Class _class : prerequisite) o += _class +",";
			o = o.substring(0,o.length()-1) + "]";
		}
		return o;
	}

	@Override
	public int compareTo(Class o) {
		return o.getId().compareTo(id);
	}
	
}
