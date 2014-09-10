package br.ufjf.coordenacao.OfertaVagas.estimate;

public class Estimative {
	
	/**
	 * Esse valor guarda quantos alunos possuem todos os pr�-requisitos para a disciplina
	 */
	private int qtdHasAllPrereq;
	
	/**
	 * Esse valor guarda quantos alunos podem, ao final do per�odo, ter todos os pr�-requisitos para a disciplina
	 * Ou seja, o aluno est� cursando ainda algum pr�-requisito
	 */
	private int qdtCanHaveAllPreq;

	/**
	 * C�digo da disciplina
	 */
	private String courseId;
	
	/**
	 * Esse valor guarda quantos alunos est�o matriculados atualmente nesta disciplina
	 */
	private int qtdEnrolled;
	

	
	
	public String getCourseId() {
		return courseId;
	}
	public int getQtdEnrolled() {
		return qtdEnrolled;
	}
	public int getQtdHasAllPrereq() {
		return qtdHasAllPrereq;
	}
	public int getQdtCanHaveAllPreq() {
		return qdtCanHaveAllPreq;
	}

	public Estimative(String c, int hasPrereq, int qdtCanHaveAllPreq, int isEnrolled) {
		this.qtdHasAllPrereq = hasPrereq;
		this.courseId = c;
		this.qtdEnrolled = isEnrolled;
		this.qdtCanHaveAllPreq = qdtCanHaveAllPreq;
	}
	
}
