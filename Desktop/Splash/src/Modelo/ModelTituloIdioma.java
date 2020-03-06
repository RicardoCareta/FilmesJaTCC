package Modelo;

public class ModelTituloIdioma {
	private ModelIdioma IDIdi;
	private ModelTituloCatalogo IDTc;
	private int Ativo;
	public ModelIdioma getIDIdi() {
		return IDIdi;
	}
	public void setIDIdi(ModelIdioma iDIdi) {
		IDIdi = iDIdi;
	}
	public ModelTituloCatalogo getIDTc() {
		return IDTc;
	}
	public void setIDTc(ModelTituloCatalogo iDTc) {
		IDTc = iDTc;
	}
	public int getAtivo() {
		return Ativo;
	}
	public void setAtivo(int ativo) {
		Ativo = ativo;
	}
}
