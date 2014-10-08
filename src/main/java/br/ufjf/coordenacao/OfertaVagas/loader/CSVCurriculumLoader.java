package br.ufjf.coordenacao.OfertaVagas.loader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import br.ufjf.coordenacao.OfertaVagas.model.Class;
import br.ufjf.coordenacao.OfertaVagas.model.ClassFactory;
import br.ufjf.coordenacao.OfertaVagas.model.Curriculum;

public class CSVCurriculumLoader implements ICurriculumLoader {

	private File _mandatoryFile;
	private File _electiveFile;
	private File _equivalenceFile;
	Curriculum _cur;
	
	public CSVCurriculumLoader(File mandatoryFile, File electiveFile, File equivalenceFile) {
		this._mandatoryFile = mandatoryFile;
		this._electiveFile = electiveFile;
		this._equivalenceFile = equivalenceFile;
		
		this._cur = new Curriculum();
	}
	
	public Curriculum getCurriculum() throws IOException {
		
		loadMandatoryFile();

		if(this._electiveFile != null) 		loadElectiveFile();
		
		// � necess�rio que o processamento de equival�ncias seja feito ao final da carga de eletivas + obrigat�rias
		if(this._equivalenceFile != null)	loadEquivalenceFile();

		return _cur;
	}

	private void loadMandatoryFile() throws IOException {
		Reader in = new FileReader(this._mandatoryFile);
		Iterable<CSVRecord> mandatoryRecords = CSVFormat.EXCEL.parse(in);
		
		for (CSVRecord record : mandatoryRecords) {
		    String semester = record.get(0).trim(); //Periodo
		    String _class   = record.get(1).trim(); //Disciplina
		    
		    Class c = ClassFactory.getClass(_class);
		    this._cur.addMandatoryClass(Integer.valueOf(semester), c);
		    
		    for (int i = 2; i < record.size(); i++) {
		    	String prerequisite = record.get(i).trim(); //Pr�-requisito
		    	Class pre = ClassFactory.getClass(prerequisite);
		    	c.addPrerequisite(pre);		    	
		    }

		}
		in.close();
	}

	private void loadElectiveFile() throws IOException {
		Reader in = new FileReader(this._electiveFile);
		Iterable<CSVRecord> electiveRecords = CSVFormat.EXCEL.parse(in);
		
		for (CSVRecord record : electiveRecords) {
		    Class c = ClassFactory.getClass(record.get(0).trim());
		    this._cur.addElectiveClass(c);
		    
		    for (int i = 1; i < record.size(); i++) {
		    	String prerequisite = record.get(i).trim(); //Pr�-requisito
		    	Class pre = ClassFactory.getClass(prerequisite);
		    	c.addPrerequisite(pre);		    	
		    }
		}
		in.close();

	}
	
	private void loadEquivalenceFile() throws IOException {
		Reader in = new FileReader(this._equivalenceFile);
		Iterable<CSVRecord> eqRecords = CSVFormat.EXCEL.parse(in);
		
		for (CSVRecord record : eqRecords) {
			String idDaGrade = record.get(0).trim();
		    String idNaoDaGrade = record.get(1).trim();

		    Class c = null;
		    if(!ClassFactory.contains(idDaGrade) && ClassFactory.contains(idNaoDaGrade)) {
		    	String aux = idNaoDaGrade;
		    	idNaoDaGrade = idDaGrade;
		    	idDaGrade = aux;
		    }
		    
		    //TODO h� um erro abaixo. Se j� tem 2 disciplinas, tem que ver qual � a obrigat�ria/eletiva, caso contr�rio se apareceu 2x a equival�ncia, ent�o d� erro. Ex: MAT114->MAT157 e MAT114->MAT156
		    else if(ClassFactory.contains(idDaGrade) && ClassFactory.contains(idNaoDaGrade))
			    throw new IOException("Equival�ncia de duas disciplinas j� existentes na grade: " + idDaGrade + " <-> " + idNaoDaGrade);

			else if(!ClassFactory.contains(idDaGrade) && !ClassFactory.contains(idNaoDaGrade))
				System.out.println("Equival�ncia de duas disciplinas n�o existentes na grade: " + idDaGrade + " <-> " + idNaoDaGrade);
			   
		    c = ClassFactory.getClass(idDaGrade);
		    ClassFactory.addClass(idNaoDaGrade, c);
		}		    

		in.close();
	}
	
}
