package Enum;

public enum EnumAL {
	AUDIO (1), LEGENDA(2);
	
	private int valor;
	
	EnumAL(int AL){
		this.valor = AL;
	}
	
	public int getAL(){
		return valor;
	}
}
