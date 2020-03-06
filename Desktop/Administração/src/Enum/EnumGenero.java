package Enum;

public enum EnumGenero {
	ID("ID"), Nome("Nome"), Ativo("Ativo");
	
	private String nome;
	
	EnumGenero(String nome){
		this.nome = nome;
	}
	
	public String get(){
		return this.nome;
	}
}
