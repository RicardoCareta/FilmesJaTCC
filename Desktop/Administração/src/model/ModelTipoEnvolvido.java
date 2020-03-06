package model;

public class ModelTipoEnvolvido {
	private int ID;
	private String Tipo;
	private int Ativo;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo.toUpperCase();
	}
	public int getAtivo() {
		return Ativo;
	}
	public void setAtivo(int ativo) {
		Ativo = ativo;
	}
	public String toString(){
		return getTipo();
	}
}
