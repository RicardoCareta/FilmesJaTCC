package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.MDAtalhoLocal;
import util.classeConexaoLocal;

public class DAOAtalhosLocal {
	
	private Statement stmn = null;
	private classeConexaoLocal cn;
	
	public DAOAtalhosLocal(){
		cn = new classeConexaoLocal();
	}
	
	public List<MDAtalhoLocal> Selecionar(){
		List<MDAtalhoLocal> retorno = new ArrayList<MDAtalhoLocal>();
		cn.Conectar();
		try {
			stmn = cn.getConnection().createStatement();
			ResultSet rs = stmn.executeQuery("select * from Atalhos");
			while(rs.next()){
				MDAtalhoLocal mdAtalhoLocal = new MDAtalhoLocal();
				mdAtalhoLocal.setID(rs.getInt("ID_ATALHO"));
				mdAtalhoLocal.setConfig(rs.getString("CONFIG"));
				mdAtalhoLocal.setAtalho(rs.getString("ATALHO"));
				mdAtalhoLocal.setStatus(rs.getInt("STATUS"));
				retorno.add(mdAtalhoLocal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cn.Desconectar();
		return retorno;
	}
	
	public void Atualizar(MDAtalhoLocal mdAtalhoLocal){
		cn.Conectar();
		try {
			stmn = cn.getConnection().createStatement();
			String sql = "update Atalhos set ATALHO = '" + mdAtalhoLocal.getAtalho() + "', STATUS = " + mdAtalhoLocal.getStatus() +  " where ID_ATALHO = " + mdAtalhoLocal.getID();
			stmn.executeUpdate(sql);
			stmn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		cn.Desconectar();
	}
}
