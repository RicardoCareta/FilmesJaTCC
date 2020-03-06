package model;

public class ModelGeneroCatalogo {
	private ModelCatalogo Catalogo;
	private ModelGenero Genero;
	private int Ativo;
	
	public ModelGeneroCatalogo(ModelCatalogo iDCat, ModelGenero iDGen, int ativo){
		Catalogo = iDCat;
		Genero = iDGen;
		Ativo = ativo;
	}
	
	public ModelGeneroCatalogo(ModelCatalogo iDCat, int ativo){
		Catalogo = iDCat;
		Ativo = ativo;
	}
	
	public ModelCatalogo getIDCat() {
		return Catalogo;
	}
	public void setIDCat(ModelCatalogo iDCat) {
		Catalogo = iDCat;
	}
	public ModelGenero getIDGen() {
		return Genero;
	}
	public void setIDGen(ModelGenero iDGen) {
		Genero = iDGen;
	}
	public int getAtivo() {
		return Ativo;
	}
	public void setAtivo(int ativo) {
		Ativo = ativo;
	}
}
