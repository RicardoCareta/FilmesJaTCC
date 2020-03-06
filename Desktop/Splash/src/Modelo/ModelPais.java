package Modelo;

public class ModelPais {
	private int IdPais;
	private String Nome;
	private int ativo;

	
	public int getIdPais() {
		return IdPais;
	}
	public void setIdPais(int idPais) {
		IdPais = idPais;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome.toUpperCase();
	}
	public int getAtivo() {
		return ativo;
	}
	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
	
}
