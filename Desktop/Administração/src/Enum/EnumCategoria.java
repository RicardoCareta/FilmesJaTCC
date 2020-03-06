package Enum;

public enum EnumCategoria {
	Categoria("Descrição"), Ativo("Ativo"), ID("ID");
	
	private String nome;
	
	EnumCategoria(String nome){
		this.nome = nome;
	}
	
	public String get(){
		return nome;
	}
}
