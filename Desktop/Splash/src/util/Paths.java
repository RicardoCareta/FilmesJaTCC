package util;

import java.net.MalformedURLException;
import java.net.URL;

public class Paths {
	private String APPDATA_PATH = "";
	private String IMAGES_PATH = "";

	public Paths() {
		APPDATA_PATH = System.getenv("APPDATA");
		IMAGES_PATH = APPDATA_PATH + "\\FilmesJa\\Images\\";

		
		propsPath = APPDATA_PATH + "\\FilmesJa\\Props\\Config";
		
		Atendimento = IMAGES_PATH + "Atendimento 22.png";
		Config = IMAGES_PATH + "Config 2.png";
		FundoParaTudo = IMAGES_PATH + "FundoParaTudo.jpg";
		IconeInserir = IMAGES_PATH + "Icone Inserir 2.png";
		IconeModificar = IMAGES_PATH + "ícone modificar 2.png";
		IconeTelaPrincipal = IMAGES_PATH + "IconePraTelaPrincipal2.png";
		ImageNull = IMAGES_PATH + "imageNull.jpg";
		Relatorio = IMAGES_PATH + "Relatório2.png";
		
		//Splash
		AnonimoFace = IMAGES_PATH + "anonimoface.png";
		BotaoVoltar = IMAGES_PATH + "botao-quot-voltar_318-108687.png";
		BotaoVoltarCerto = IMAGES_PATH + "botao-quot-voltar_Certo.png";
		CadeadoAberto = IMAGES_PATH + "cadeado-aberto_318-42566.jpg";
		Camera = IMAGES_PATH + "camera1600.png";
		depreJPG = IMAGES_PATH + "depre.jpg";
		deprePNG = IMAGES_PATH + "depre.png";
		depreVermelhinhoJPG = IMAGES_PATH + "depreVermelinho.jpg";
		depreVermelhinhoPNG = IMAGES_PATH + "depreVermelinho.png";
		down = IMAGES_PATH + "down.png";
		edit = IMAGES_PATH + "edit.png";
		exit_C = IMAGES_PATH + "exit_C.png";
		exit = IMAGES_PATH + "exit.png";
		felizPNG = IMAGES_PATH + "feliz.jpg";
		felizJPG = IMAGES_PATH + "feliz.png";
		felizVerdinhoJPG = IMAGES_PATH + "felizVerdinho.jpg";
		felizVerdinhoPNG = IMAGES_PATH + "felizVerdinho.png";
		FullScreenIcon = IMAGES_PATH + "fullscreenicon.png";
		IconPlay = IMAGES_PATH + "Ícone_Play.png";
		IconPlayC = IMAGES_PATH + "Ícone_PlayC.png";
		naquelasJPG = IMAGES_PATH + "naquelas.jpg";
		naquelasPNG = IMAGES_PATH + "naquelas.png";
		naquelasAmareloJPG = IMAGES_PATH + "naquelasAmarelo.jpg";
		naquelasAmareloPNG = IMAGES_PATH + "naquelasAmarelo.png";
		pauseIcon7856 = IMAGES_PATH + "pause-icon-7856.png";
		pauseIcon = IMAGES_PATH + "pause-icon.png";
		search = IMAGES_PATH + "search.png";
		showPassword = IMAGES_PATH + "show_password-512";
		
		coracaoCheio = IMAGES_PATH + "coracaoCheio.png";
		coracaoVazio = IMAGES_PATH + "coracaoVazio.png";
	}
	
	public URL getURL(String caminho){
		try {
			return new URL("file:///" + caminho);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public String Atendimento = "";
	public String Config = "";
	public String FundoParaTudo = "";
	public String IconeInserir = "";
	public String IconeModificar = "";
	public String IconeTelaPrincipal = "";
	public String ImageNull = "";
	public String Relatorio = "";
	
	//Splash
	public String AnonimoFace = "";
	public String BotaoVoltar = "";
	public String BotaoVoltarCerto = "";
	public String CadeadoAberto = "";
	public String Camera = "";
	public String depreJPG = "";
	public String deprePNG = "";
	public String depreVermelhinhoJPG = "";
	public String depreVermelhinhoPNG = "";
	public String down = "";
	public String edit = "";
	public String exit_C = "";
	public String exit = "";
	public String felizPNG = "";
	public String felizJPG = "";
	public String felizVerdinhoJPG = "";
	public String felizVerdinhoPNG = "";
	public String FullScreenIcon = "";
	public String IconPlay = "";
	public String IconPlayC = "";
	public String naquelasJPG = "";
	public String naquelasPNG = "";
	public String naquelasAmareloJPG = "";
	public String naquelasAmareloPNG = "";
	public String pauseIcon7856 = "";
	public String pauseIcon = "";
	public String search = "";
	public String showPassword = "";
	
	public String coracaoVazio = "";
	public String coracaoCheio = "";
	
	public String propsPath = "";
}
