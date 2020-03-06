package model;

public class ModelTituloCatalogo {
	private int ID;
	private String Titulo;
	private int IDCat;
	private int Ativo;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getTitulo() {
		return Titulo;
	}
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	public int getIDCat() {
		return IDCat;
	}
	public void setIDCat(int iDCat) {
		IDCat = iDCat;
	}
	public int getAtivo(){
		return Ativo;
	}
	public void setAtivo(int ativo){
		this.Ativo = ativo;
	}
}
