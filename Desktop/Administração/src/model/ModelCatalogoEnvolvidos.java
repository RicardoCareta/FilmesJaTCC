package model;

public class ModelCatalogoEnvolvidos{
	private int ID;
	private ModelCatalogo IDCat;
	private ModelElenco IDEnvolvido;
	private ModelTipoEnvolvido IDTipoEnvolvido;
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
	public ModelElenco getIDEnvolvido() {
		return IDEnvolvido;
	}
	public void setIDEnvolvido(ModelElenco iDEnvolvido) {
		IDEnvolvido = iDEnvolvido;
	}
	public ModelTipoEnvolvido getIDTipoEnvolvido() {
		return IDTipoEnvolvido;
	}
	public void setIDTipoEnvolvido(ModelTipoEnvolvido iDTipoEnvolvido) {
		IDTipoEnvolvido = iDTipoEnvolvido;
	}
	public int getAtivo() {
		return Ativo;
	}
	public void setAtivo(int ativo) {
		Ativo = ativo;
	}
}
	