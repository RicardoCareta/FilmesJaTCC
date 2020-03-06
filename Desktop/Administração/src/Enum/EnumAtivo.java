package Enum;

public enum EnumAtivo {
	ATIVO(1), DESATIVO(0), INACESSIVEL(2);
	
	private int valor;
	
	public int getAtivo(){
		return valor;
	}
	
	EnumAtivo(int valor){
		this.valor = valor;
	}
}
