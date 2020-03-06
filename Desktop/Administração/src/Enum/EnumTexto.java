package Enum;

public enum EnumTexto {
	Todas("Todas"), Todos("Todos"), Idioma("Idioma"), Sinopse("Sinopse"), Caminho("Caminho"), Titulo("Titulo");
	
	private String texto;
	
	EnumTexto(String texto){
		this.texto = texto;
	}
	
	public String get(){
		return texto;
	}
}
