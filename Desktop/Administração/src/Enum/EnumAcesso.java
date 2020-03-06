package Enum;

public enum EnumAcesso {
	Y(1), N(0);
	
	int acesso;
	
	EnumAcesso(int acesso){
		this.acesso = acesso;
	}
	
	public int get(){
		return acesso;
	}
}
