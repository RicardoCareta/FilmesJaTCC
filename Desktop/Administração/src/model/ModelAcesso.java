package model;

public class ModelAcesso {
	private int ID;
	private ModelCatalogo IDCat;
	private ModelPais IDPais;
	private int Acesso;
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
	public ModelPais getIDPais() {
		return IDPais;
	}
	public void setIDPais(ModelPais iDPais) {
		IDPais = iDPais;
	}
	public int getAcesso() {
		return Acesso;
	}
	public void setAcesso(int acesso) {
		Acesso = acesso;
	}
	public int getAtivo() {
		return Ativo;
	}
	public void setAtivo(int ativo) {
		Ativo = ativo;
	}
}
