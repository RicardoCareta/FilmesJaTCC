package Visão.Eventos;

import java.io.IOException;
import javax.swing.JPasswordField;

import Constantes.ConsProp;
import Controle.ControlCliente;
import Controle.ControlContrato;
import Visão.JLogin;
import Visão.JSelecionarConta;
import Visão.MessagemBoxCustom;
import util.ConvertPassword;
import util.Props;

public class eventoLogin {

	private static String URLCadastrar = "www.superanimes.com";
	private static String URLEsqueci = "www.youtube.com";

	private static ControlCliente controlCliente;
	private static ControlContrato controlContrato;

	public static void EntrarClick() {

		if (JLogin.txtSenha.equals("")) {
			return;
		}

		else if (JLogin.txtUsuario.equals("")) {
			return;
		}

		

		controlCliente = new ControlCliente();
		controlContrato = new ControlContrato();
		
		if (controlCliente.Login(JLogin.txtUsuario.getText(), JLogin.txtSenha.getPassword())) {

			int IDCliente = controlCliente.getID(JLogin.txtUsuario.getText(), JLogin.txtSenha.getPassword());
			
			if(!controlContrato.canLogin(controlCliente.getID(JLogin.txtUsuario.getText(), JLogin.txtSenha.getPassword()))){
				MessagemBoxCustom.ShowMensagem("Por favor, pague antes de usar o serviço", "Poxa amigo, ai moio", MessagemBoxCustom.DONT_CLOSE);
				//return;
			}
			
			if (JLogin.chckbxLembrarSenha.isSelected()) {
				Props p = new Props();
				p.setProperties(ConsProp.PASSWORD, ConvertPassword.convert(JLogin.txtSenha.getPassword()));
			}
			else if(!JLogin.chckbxLembrarSenha.isSelected()){
				Props p = new Props();
				p.removeProp(ConsProp.PASSWORD);
			}
			
			/*if(!controlCliente.cabeMaisUm(IDCliente)){
				MessagemBoxCustom.ShowMensagem("Ja ta usando todas as telas =P", "Poxa amigo, aumente o pacote", MessagemBoxCustom.DONT_CLOSE);
				return;
			}*/
			
			JLogin.Visible(false);
			JSelecionarConta.main(IDCliente);
			JLogin.txtSenha.setText("");
			JLogin.txtUsuario.setText("");
		} else {
			MessagemBoxCustom.ShowMensagem("Usuario ou senha está incorreto", MessagemBoxCustom.DONT_CLOSE);
		}
	}

	public static void ExibirSenhaEntered() {
		JLogin.txtSenha.setEchoChar((char) 0);
	}

	public static void OcultarSenhaExited() {
		JPasswordField old = new JPasswordField();
		JLogin.txtSenha.setEchoChar(old.getEchoChar());
	}

	public static void LinkCadastrar() {
		OpenURL(URLCadastrar);
	}

	public static void LinkEsqueci() {
		OpenURL(URLEsqueci);
	}

	private static void OpenURL(String URL) {
		try {
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + URL);
		} catch (IOException ex) {
			System.out.print(ex.getMessage());
		}
	}
}