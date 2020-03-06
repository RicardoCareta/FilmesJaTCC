package Modelo;

public class ModelClassificacao {
	private int ID;
	private ModelCatalogo IDCat;
	private ModelPais IDPais;
	private int Idade;
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
	public int getIdade() {
		return Idade;
	}
	public void setIdade(int idade) {
		Idade = idade;
	}
	public int getAtivo() {
		return Ativo;
	}
	public void setAtivo(int ativo) {
		Ativo = ativo;
	}
}
