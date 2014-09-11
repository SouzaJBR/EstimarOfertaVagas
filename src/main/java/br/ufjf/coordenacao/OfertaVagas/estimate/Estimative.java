package br.ufjf.coordenacao.OfertaVagas.estimate;

public class Estimative {
	
	/**
	 * Esse valor guarda quantos alunos possuem todos os pr�-requisitos para a disciplina
	 */
	private int qtdHasAllPrereq;
	private int qtdHasAllPrereqWeighted;
	
	/**
	 * Esse valor guarda quantos alunos podem, ao final do per�odo, ter todos os pr�-requisitos para a disciplina
	 * Ou seja, o aluno est� cursando ainda algum pr�-requisito
	 */
	private int qdtCanHaveAllPreq;
	private int qdtCanHaveAllPreqWeighted;

	/**
	 * C�digo da disciplina
	 */
	private String courseId;
	
	/**
	 * Esse valor guarda quantos alunos est�o matriculados atualmente nesta disciplina
	 */
	private int qtdEnrolled;
	private int qtdEnrolledWeighted;
	
	/**
	 * Aqui est� o total de vagas necess�rias
	 */
	private int qtdTotal;
	
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

	public int getQtdHasAllPrereqWeighted() {
		return qtdHasAllPrereqWeighted;
	}
	public void setQtdHasAllPrereqWeighted(int qtdHasAllPrereqWeighted) {
		this.qtdHasAllPrereqWeighted = qtdHasAllPrereqWeighted;
	}
	public int getQdtCanHaveAllPreqWeighted() {
		return qdtCanHaveAllPreqWeighted;
	}
	public void setQdtCanHaveAllPreqWeighted(int qdtCanHaveAllPreqWeighted) {
		this.qdtCanHaveAllPreqWeighted = qdtCanHaveAllPreqWeighted;
	}
	public int getQtdEnrolledWeighted() {
		return qtdEnrolledWeighted;
	}
	public void setQtdEnrolledWeighted(int qtdEnrolledWeighted) {
		this.qtdEnrolledWeighted = qtdEnrolledWeighted;
	}
	public int getQtdTotal() {
		return qtdTotal;
	}
	public void setQtdTotal(int qtdTotal) {
		this.qtdTotal = qtdTotal;
	}
	public Estimative(String c, int hasPrereq, int qdtCanHaveAllPreq, int isEnrolled) {
		this.qtdHasAllPrereq = hasPrereq;
		this.courseId = c;
		this.qtdEnrolled = isEnrolled;
		this.qdtCanHaveAllPreq = qdtCanHaveAllPreq;
	}
	
}
