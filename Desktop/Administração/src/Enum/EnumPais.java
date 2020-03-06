package Enum;

public enum EnumPais {
	ID("ID"), Pais("Pais"), Regiao("Região"), Ativo("Ativo");

	private String nome;
	
	EnumPais(String nome){
		this.nome = nome;
	}
	
	public String get(){
		return this.nome;
	}
}
