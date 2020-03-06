package Enum;

public enum EnumElenco {
	
	ID("ID"), Nome("Nome"), Ativo("Ativo");
	
	
	private String valor;
	
	EnumElenco(String valor){
		this.valor = valor;
	}
	
	public String get(){
		return valor;
	}
}
