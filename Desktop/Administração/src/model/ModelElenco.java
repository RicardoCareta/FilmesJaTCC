package model;

public class ModelElenco {
	private int ID_Envo;
	private String Nome;
	private int Ativo;
	public int getID_Envo() {
		return ID_Envo;
	}
	public void setID_Envo(int iD_Envo) {
		ID_Envo = iD_Envo;
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
}
