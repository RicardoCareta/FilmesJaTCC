package model;

public class ModelGenero {
	private int idGen;
	private String nome;
	private int ativo;

	
	public int getIdGen() {
		return idGen;
	}
	public void setIdGen(int idGen) {
		this.idGen = idGen;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}
	public int getAtivo() {
		return ativo;
	}
	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
	public String toString(){
		return getNome();
	}
}
