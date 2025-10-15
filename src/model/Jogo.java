package model;

import java.sql.Date;

public class Jogo {

	private int id;
	private String nome;
	private String genero;
	private Date dataLancamento;
	private double nota;
	
	public Jogo() {
		
	}

	public Jogo(int id, String nome, String genero, Date dataLancamento, double nota) {
		this.id = id;
		this.nome = nome;
		this.genero = genero;
		this.dataLancamento = dataLancamento;
		this.nota = nota;
	}

	public Jogo(String nome, String genero, Date dataLancamento, double nota) {
		super();
		this.nome = nome;
		this.genero = genero;
		this.dataLancamento = dataLancamento;;
		this.nota = nota;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "Jogo [id=" + id + ", nome=" + nome + ", genero=" + genero + ", dataLancamento=" + dataLancamento
				+ ", nota=" + nota + "]";
	}

}
