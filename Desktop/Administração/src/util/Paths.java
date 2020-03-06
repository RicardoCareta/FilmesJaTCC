package util;

public class Paths {
	private String APPDATA_PATH = "";
	private String IMAGES_PATH = "";
	
	
	public Paths(){
		APPDATA_PATH = System.getenv("APPDATA");
		IMAGES_PATH = APPDATA_PATH + "\\FilmesJa\\Images\\";
		
		Atendimento = IMAGES_PATH + "Atendimento 22.png";
		Config = IMAGES_PATH + "Config 2.png";
		FundoParaTudo = IMAGES_PATH + "FundoParaTudo.jpg";
		IconeInserir = IMAGES_PATH  + "Icone Inserir 2.png";
		IconeModificar = IMAGES_PATH  + "ícone modificar 2.png";
		IconeTelaPrincipal = IMAGES_PATH + "IconePraTelaPrincipal2.png";
		ImageNull = IMAGES_PATH + "imageNull.jpg";
		Relatorio = IMAGES_PATH + "Relatório2.png";
	}
	
	public String Atendimento = "";
	public String Config = "";
	public String FundoParaTudo = "";
	public String IconeInserir = "";
	public String IconeModificar = "";
	public String IconeTelaPrincipal = "";
	public String ImageNull = "";
	public String Relatorio = "";
}
