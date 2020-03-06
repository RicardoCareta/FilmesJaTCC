package Modelo;

public class ModelSinopseCatalogo {
	private int ID;
	private ModelCatalogo IDCat;
	private String Sinopse;
	private ModelIdioma IDIdioma;
	private int Ativo;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public ModelCatalogo getIDCat() {
		return IDCat;
	}
	public void setIDCat(ModelCatalogo iDCat) {
		IDCat = iDCat;
	}
	public String getSinopse() {
		return Sinopse;
	}
	public void setSinopse(String sinopse) {
		Sinopse = sinopse;
	}
	public ModelIdioma getIDIdioma() {
		return IDIdioma;
	}
	public void setIDIdioma(ModelIdioma iDIdioma) {
		IDIdioma = iDIdioma;
	}
	public int getAtivo() {
		return Ativo;
	}
	public void setAtivo(int ativo) {
		Ativo = ativo;
	}
}
