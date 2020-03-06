package util;

import javax.swing.ImageIcon;

public class Images {
	
	public Paths p;
	
	public Images(){
		p = new Paths();
		
		Image_Atendimento = new ImageIcon(p.Atendimento);
		Image_Config = new ImageIcon(p.Config);
		Image_FundoParaTudo = new ImageIcon(p.FundoParaTudo);
		Image_IconeInserir = new ImageIcon(p.IconeInserir);
		Image_IconeModificar = new ImageIcon(p.IconeModificar);
		Image_IconeTelaPrincipal = new ImageIcon(p.IconeTelaPrincipal);
		Image_ImageNull = new ImageIcon(p.ImageNull);
		Image_Relatorio = new ImageIcon(p.Relatorio);
	}
	
	public ImageIcon Image_Atendimento = null;
	public ImageIcon Image_Config = null;
	public ImageIcon Image_FundoParaTudo = null;
	public ImageIcon Image_IconeInserir = null;
	public ImageIcon Image_IconeModificar = null;
	public ImageIcon Image_IconeTelaPrincipal = null;
	public ImageIcon Image_ImageNull = null;
	public ImageIcon Image_Relatorio = null;
	
}
