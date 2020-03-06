package DAO;

import util.classeConexao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.ModelTipoEnvolvido;

public class DAOTipoEnvolvido {
	
	private PreparedStatement ps;
	private ResultSet rs;
	private CallableStatement cs;
	private classeConexao cn;
	
	public void Inserir(ModelTipoEnvolvido tipo){
		try {
			cn = new classeConexao();
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spInsertTipoEnvolvido(?,?)}");
			cs.setInt(1, 1);
			cs.setString(2, tipo.getTipo());
			cs.execute();
			cs.close();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Update(ModelTipoEnvolvido tipo){
		try {
			cn = new classeConexao();
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spUpdateTipoEnvolvido(?,?,?)}");
			cs.setInt("p_idTipoEnvolvido", tipo.getID());
			cs.setString("p_Tipo", tipo.getTipo());
			cs.setInt("p_Ativo", tipo.getAtivo());
			cs.execute();
			cs.close();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ModelTipoEnvolvido> Selecionar(){
		List<ModelTipoEnvolvido> tipo = new ArrayList<ModelTipoEnvolvido>();
		ModelTipoEnvolvido t;
		try {
			cn = new classeConexao();
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from tblTipoEnvolvido");
			rs = ps.executeQuery();
			while(rs.next()){
				t = new ModelTipoEnvolvido();
				t.setTipo(rs.getString(2));
				t.setID(rs.getInt(1));
				t.setAtivo(rs.getInt(3));
				tipo.add(t);
			}
			rs.close();
			ps.close();
			cn.Desconectar();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tipo;
	}
}
