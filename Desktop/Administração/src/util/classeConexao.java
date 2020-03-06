package util;

import java.sql.DriverManager;
import java.sql.SQLException;
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
				Exception e = new Exception("Banco de dados já está conectado");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e){
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
