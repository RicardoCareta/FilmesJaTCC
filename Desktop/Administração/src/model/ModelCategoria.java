package model;

public class ModelCategoria {
	private int ID_Cat;
	private String Nome;
	private int Ativo;
	public int getID_Cat() {
		return ID_Cat;
	}
	public void setID_Cat(int iD_Cat) {
		ID_Cat = iD_Cat;
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
