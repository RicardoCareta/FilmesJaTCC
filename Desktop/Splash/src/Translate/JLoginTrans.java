package Translate;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

public class JLoginTrans {
	
	
	private JLabel lblUsuario;
	private JLabel lblSenha;
	private JLabel lblBemVindo;
	private JLabel lblCadastro;
	private JLabel lblEsqueci;
	
	public JLoginTrans(){
		lblUsuario = new JLabel();
		lblUsuario.setName("lblUsuario");
		
		lblSenha = new JLabel();
		lblSenha.setText("lblSenha");
		
		lblBemVindo = new JLabel();
		lblBemVindo.setName("lblBemVindo");
		
		lblCadastro = new JLabel();
		lblCadastro.setName("lblCadastro");
	
		lblEsqueci = new JLabel();
		lblEsqueci.setName("lblEsqueci");
	}
	
	public List<Component> getPortugues(){
		List<Component> retorno = new ArrayList<Component>();
		
		lblUsuario.setText("Usuário:");
		retorno.add(lblUsuario);
		lblSenha.setText("Senha:");
		retorno.add(lblSenha);
		lblBemVindo.setText("Bem vindo!");
		retorno.add(lblBemVindo);
		lblCadastro.setText("Cadastre-se");
		retorno.add(lblCadastro);
		lblEsqueci.setText("Esqueceu sua senha?");
		retorno.add(lblEsqueci);
		
		return retorno;
	}
	
	public List<Component> getIngles(){
		List<Component> retorno = new ArrayList<Component>();
		
		lblUsuario.setText("User:");
		retorno.add(lblUsuario);
		lblSenha.setText("Password:");
		retorno.add(lblSenha);
		lblBemVindo.setText("Welcome!");
		retorno.add(lblBemVindo);
		lblCadastro.setText("Register");
		retorno.add(lblCadastro);
		lblEsqueci.setText("Forgot your password?");
		retorno.add(lblEsqueci);
		
		return retorno;
	}
}
