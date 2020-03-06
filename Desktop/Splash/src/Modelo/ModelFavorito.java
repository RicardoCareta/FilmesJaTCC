package Modelo;

public class ModelFavorito {
	private int ID;
	private ModelCatalogo catalogo;
	private ModelConta conta;
	private int Ativo;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public ModelCatalogo getCatalogo() {
		return catalogo;
	}
	public void setCatalogo(ModelCatalogo catalogo) {
		this.catalogo = catalogo;
	}
	public ModelConta getConta() {
		return conta;
	}
	public void setConta(ModelConta conta) {
		this.conta = conta;
	}
	public int getAtivo() {
		return Ativo;
	}
	public void setAtivo(int ativo) {
		Ativo = ativo;
	}
}
