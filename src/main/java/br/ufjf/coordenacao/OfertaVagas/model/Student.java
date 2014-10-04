package br.ufjf.coordenacao.OfertaVagas.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {

	
	private HashMap<ClassStatus, HashMap<Class, ArrayList<String>>> classes = new HashMap<ClassStatus, HashMap<Class, ArrayList<String>>>();
	private String _id;
	
	public Student(String id) {
		this._id = id;
	}
	
	
	public void addClass(String _class, String semester, ClassStatus status) {
		
		// A linha abaixo � necess�ria por conta das equival�ncias de disciplinas
		Class _class2 = ClassFactory.getClass(_class);
		
		if (!this.classes.containsKey(status))
			this.classes.put(status, new HashMap<Class, ArrayList<String>>());
		
		ArrayList<String> a = this.classes.get(status).get(_class2);
		if(a == null) {
			a = new ArrayList<String>();
		}
		
		a.add(semester);
		this.classes.get(status).put(_class2, a);
	}
	
	public String getId() { return this._id; }
	
	@Override
	public String toString() {
		String output = "Student " + this._id;

		for (ClassStatus status : this.classes.keySet()) {
			output += ", "+status.name()+"=";
			for (Object string : this.classes.get(status).keySet().toArray()) 
				output += "," + string;
		}
		return output;
	}
	
	public HashMap<Class, ArrayList<String>> getClasses(ClassStatus cs) {
		if (!this.classes.containsKey(cs))
			this.classes.put(cs, new HashMap<Class, ArrayList<String>>());

		return classes.get(cs);
	}
	
}
