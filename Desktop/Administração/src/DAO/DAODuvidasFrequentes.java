package DAO;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.MDDuvidasFrequentes;
import util.classeConexao;

public class DAODuvidasFrequentes {
	
	private PreparedStatement ps;
	private CallableStatement cs;
	private classeConexao cn;
	private ResultSet rs;
	
	public DAODuvidasFrequentes(){
		
	}
	
	
	public void Inserir(MDDuvidasFrequentes df){
		cn = new classeConexao();
		
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spInsertDuvidasFrequentes(?,?)}");
			
			cs.setString(1, df.getDUVIDA());
			cs.setString(2, df.getRESPOSTA());
			
			cs.execute();
			
			cs.close();
			cn.Desconectar();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Atualizar(MDDuvidasFrequentes df){
		cn = new classeConexao();
		
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spUpdateDuvidasFrequentes(?,?,?,?)}");
			
			cs.setInt(1, df.getID_DUVIDA());
			cs.setString(2, df.getDUVIDA());
			cs.setString(3, df.getRESPOSTA());
			cs.setInt(4, df.getATIVO());
			
			cs.execute();
			
			cs.close();
			
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MDDuvidasFrequentes> Selecionar(){
		List<MDDuvidasFrequentes> retorno = new ArrayList<>();
		
		MDDuvidasFrequentes duvidasFrequentes;
		
		cn = new classeConexao();
		cn.Conectar();
		
		try {

			ps = cn.getConnection().prepareStatement("select * from tblDuvidasFrequentes");

			rs = ps.executeQuery();
			while (rs.next()) {
				duvidasFrequentes = new MDDuvidasFrequentes();
				duvidasFrequentes.setID_DUVIDA(rs.getInt(1));
				duvidasFrequentes.setDUVIDA(rs.getString(2));
				duvidasFrequentes.setRESPOSTA(rs.getString(3));
				duvidasFrequentes.setATIVO(rs.getInt(4));
				
				retorno.add(duvidasFrequentes);
			}
			rs.close();
			ps.close();
			cn.Desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;
	}
}
