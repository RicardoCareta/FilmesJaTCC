package util;

import java.sql.DriverManager;
import java.sql.SQLException;

import Visão.MessagemBoxCustom;

import java.sql.Connection;

public class classeConexao {
	
	private Connection cn;

	private String Username = "TCC";
	private String Password = "123456";
	private String URL = "jdbc:oracle:thin:@localhost:1521:xe";

	private boolean on = false;
	
	public Connection getConnection(){
		return cn;
	}
	
	public void Conectar() {
		try {
			if (!on) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				cn = DriverManager.getConnection(URL, Username, Password);
				on = true;
			}else{
				System.out.println("Banco de dados ja está aberto");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			MessagemBoxCustom.ShowMensagem("<html><p>Erro ao conectar ao servidor</p>", true);
		} catch (SQLException e) {
			e.printStackTrace();
			MessagemBoxCustom.ShowMensagem("<html><p>Erro ao conectar ao servidor</p>", true);
		}

	}
	
	public void Desconectar(){
		if (on) {
			try {
				cn.close();
				on = false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				MessagemBoxCustom.ShowMensagem("<html><p>Falha ao desconectar do banco de dados</p>", false);
				
			}
		}else{
			System.out.println("O banco de dados já está fechado");
		}
	}

}
