package Modelo;

public class ModelEnvolvidos {
	private int idEnvo;
	private String nome;
	private int ativo;

	
	public int getIdEnvo() {
		return idEnvo;
	}
	public void setIdEnvo(int idEnvo) {
		this.idEnvo = idEnvo;
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
}
