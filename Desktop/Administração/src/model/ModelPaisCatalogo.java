package model;

public class ModelPaisCatalogo {
	
	private ModelPais idPais;
	private ModelCatalogo idCat;
	private int Ativo;
	public ModelPais getIdPais() {
		return idPais;
	}
	public void setIdPais(ModelPais idPais) {
		this.idPais = idPais;
	}
	public ModelCatalogo getIdCat() {
		return idCat;
	}
	public void setIdCat(ModelCatalogo idCat) {
		this.idCat = idCat;
	}
	public int getAtivo(){
		return Ativo;
	}
	public void setAtivo(int ativo){
		this.Ativo = ativo;
	}
}
