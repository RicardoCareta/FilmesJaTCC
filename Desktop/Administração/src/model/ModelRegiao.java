package model;

public class ModelRegiao {
	private int ID;
	private String Nome;
	private int Ativo;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome.toUpperCase();
	}
	public int getAtivo() {
		return Ativo;
	}
	public void setAtivo(int ativo) {
		Ativo = ativo;
	}
	public String toString(){
		return getNome();
	}
}
