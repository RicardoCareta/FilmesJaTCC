package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class classeConexaoLocal {
	private Connection cn;

	private boolean on = false;
	
	public Connection getConnection(){
		return cn;
	}
	
	public void Conectar() {
		try {
			if (!on) {
				Class.forName("org.sqlite.JDBC");
				cn = DriverManager.getConnection("jdbc:sqlite:" + classeConexaoLocal.class.getResource("ConfigsDate.db"));
				on = true;
			}else{
				Exception e = new Exception("Banco de dados já está conectado");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			}
		}else{
			Exception e = new Exception("Banco de dados está fechado");
			e.printStackTrace();
		}
	}
	
}
