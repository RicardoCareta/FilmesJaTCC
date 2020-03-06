package Enum;

public enum EnumCatalogo {
	ID("ID"), Nome("Nome Padrao"), Sinopse("Sinopse"), Imagem("Imagem"), Caminho("Caminho"), Ano("Ano"), Temporada("Temporada"), Episodio("Episodio"),
	IDCategoria("IDCategoria"), Categoria("Categoria"), IDSerieCatalogo("IDSerieCatalogo"), Ativo("Ativo"), FS("Filme/Série");
	
	private String name;
	
	EnumCatalogo(String nome){
		name = nome;
	}
	
	public String get(){
		return name;
	}
}
