package model;

public class ModelPais {
	private int IDPais;
	private String Nome;
	private int IDRegiao;
	
	private int Ativo;
	public int getIDPais() {
		return IDPais;
	}
	public void setIDPais(int iDPais) {
		IDPais = iDPais;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome.toUpperCase();
	}
	public int getAtivo() {
		return Ativo;
	}
	public void setAtivo(int ativo) {
		Ativo = ativo;
	}
	public int getRegiao(){
		return IDRegiao;
	}
	public void setRegiao(int regiao){
		IDRegiao = regiao;
	}
	public String toString(){
		return getNome();
	}
}
