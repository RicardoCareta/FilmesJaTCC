package DAO;

import java.sql.CallableStatement;

import model.ModelProdutora;
import util.classeConexao;

public class DAOProdutora {

	private CallableStatement cs;
	private classeConexao cn;
	
	public void Inserir(ModelProdutora prod){
		try {
			cn = new classeConexao();
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spInsertProdutora(?,?}");
			cs.setString(1, prod.getNome());
			cs.setInt(2, 1);
			cs.execute();
			cn.Desconectar(); 
			
		} catch (Exception e) {
			try {
				throw new Exception("Falha ao inserir produto: " + e.getMessage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			 }
		}
	}
}
