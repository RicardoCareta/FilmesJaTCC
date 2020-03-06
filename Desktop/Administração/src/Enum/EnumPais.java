package Enum;

public enum EnumPais {
	ID("ID"), Pais("Pais"), Regiao("Regi�o"), Ativo("Ativo");

	private String nome;
	
	EnumPais(String nome){
		this.nome = nome;
	}
	
	public String get(){
		return this.nome;
	}
}
