package br.ufjf.coordenacao.OfertaVagas.estimate;

import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;

import br.ufjf.coordenacao.OfertaVagas.model.Class;
import br.ufjf.coordenacao.OfertaVagas.model.ClassStatus;
import br.ufjf.coordenacao.OfertaVagas.model.Curriculum;
import br.ufjf.coordenacao.OfertaVagas.model.Student;
import br.ufjf.coordenacao.OfertaVagas.model.StudentsHistory;

public class Estimator {
	
	private Curriculum curriculum;
	private StudentsHistory history;
	private EstimativesResult result;

	public Estimator(Curriculum c, StudentsHistory sh) {
			this.curriculum = c;
			this.history = sh;
	}
	
/**
 * 
 * 		//Passos:
		// para cada disciplina da grade,
		    // pegar quais os seus pr�-requisitos
		      // verificar quantos alunos j� fizeram os pr�-requisitos
		      // verificar quantos alunos est�o fazendo os prerequisitos (1 ou mais)
		      
		     // para cada aluno, verificar se ele j� cursou a disciplina (o aluno pode ter feito a disciplina mas n�o o seu pr�-requisito)
		       // descontar do numero total
		
		    // permitir uma taxa de reprova��o. Ou seja, daqueles que estao cursando, quantos podem se reprovar.

			// o aluno pode estar cursando a disciplina neste semestre...
 * 
 * 	
 */
		
	public EstimativesResult populateData() {

		// Fazendo somente com disciplinas obrigat�rias
		this.result = new EstimativesResult();
		HashMap<Integer, TreeSet<Class>> mantadories = this.curriculum.getMandatories();
		Collection<Student> students = this.history.getStudents().values();
		for (Integer semester : mantadories.keySet()) {
			for (Class _class : mantadories.get(semester)) {
				int countCanEnroll = 0;
				int countIsEnrolled = 0;
				int countCompletePrereq = 0;
				
				for(Student st : students) {
					
					// Se o aluno j� passou na disciplina, n�o entra na contagem
					if (st.getClasses(ClassStatus.APPROVED).contains(_class))
						continue;
					
					// Se o aluno est� fazendo a disciplina, n�o precisa checar pr�-requisitos
					if (st.getClasses(ClassStatus.ENROLLED).contains(_class))
						countIsEnrolled++;
					
					// Se o aluno n�o fez ou n�o est� fazendo a disciplina, talvez ele possa fazer no pr�ximo per�odo
					else {
						int qtdPrereqCompleted = 0;
						int qtdPrereqEnrolled = 0;
						for(Class pre : _class.getPrerequisite()) {
							if (st.getClasses(ClassStatus.APPROVED).contains(pre)) 
								qtdPrereqCompleted++;
							
							if (st.getClasses(ClassStatus.ENROLLED).contains(pre)) 
								qtdPrereqEnrolled++;
						}
						
						if(qtdPrereqCompleted == _class.getPrerequisite().size()) countCompletePrereq++;
						else if (qtdPrereqCompleted + qtdPrereqEnrolled == _class.getPrerequisite().size())	countCanEnroll++;
					}
					
				}
				result.addEstimative(_class, countCompletePrereq, countCanEnroll, countIsEnrolled);
			}
		}

		return this.result;	
	}
	
	
}
