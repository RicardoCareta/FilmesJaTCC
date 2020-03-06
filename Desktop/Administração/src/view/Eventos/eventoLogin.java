package view.Eventos;

import java.io.IOException;
import javax.swing.JPasswordField;

import view.JControleAdministrador;
import view.JLoginAdm;

public class eventoLogin {
	
	private static String URLCadastrar = "www.superanimes.com";
	private static String URLEsqueci = "www.youtube.com";

	public static void EntrarClick(){
		
		if(JLoginAdm.txtSenha.equals("")){
			return;
		}
		
		else if(JLoginAdm.txtUsuario.equals("")){
			return;
		}
		
		JLoginAdm.Visible(false);
		
		JControleAdministrador.main(null);
	}
	
	public static void ExibirSenhaEntered(){
		JLoginAdm.txtSenha.setEchoChar((char)0);
	}

	public static void OcultarSenhaExited(){
		JPasswordField old = new JPasswordField();
		JLoginAdm.txtSenha.setEchoChar(old.getEchoChar());
	}
	
	public static void LinkCadastrar(){
		OpenURL(URLCadastrar);
	}
	
	public static void LinkEsqueci(){
		OpenURL(URLEsqueci);
	}
	
	public static void OpenURL(String URL){
		try {
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + URL);
			} catch (IOException ex) {
				System.out.print(ex.getMessage());
			}
	}
}