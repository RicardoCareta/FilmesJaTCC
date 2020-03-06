package Enum;

public enum EnumPreguica {
	Categoria(1), Genero(4), Elenco(3), Pais(5), Produtora(6), Cargo(7), Regiao(8), Idioma(9);
	
	private int tipo;
	
	EnumPreguica(int tipo){
		this.tipo = tipo;
	}
	
	public int get(){
		return tipo;
	}
}
