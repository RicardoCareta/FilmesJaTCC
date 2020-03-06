package Modelo;

public class ModelCatalogoEnvolvidos {
	private int idEnvo;
	private int idCat;
	private int ativo;
	private ModelEnvolvidos envo;
	
	public int getIdEnvo() {
		return idEnvo;
	}
	public void setIdEnvo(int idEnvo) {
		this.idEnvo = idEnvo;
	}
	public int getIdCat() {
		return idCat;
	}
	public void setIdCat(int idCat) {
		this.idCat = idCat;
	}
	public int getAtivo() {
		return ativo;
	}
	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
	public ModelEnvolvidos getEnvo(){
		return envo;
	}
	public void setEnvo(ModelEnvolvidos envo){
		this.envo = envo;
	}
}
