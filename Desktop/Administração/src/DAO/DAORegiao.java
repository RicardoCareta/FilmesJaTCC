package DAO;

import util.classeConexao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.ModelRegiao;

public class DAORegiao {
	
	private PreparedStatement ps;
	private CallableStatement cs;
	private classeConexao cn;
	private ResultSet rs;

	public void Inserir(ModelRegiao regiao){
		try {
			cn = new classeConexao();
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spInsertRegiao(?,?)}");
			cs.setString(1, regiao.getNome());
			cs.setInt(2, 1);
			cs.execute();
			cs.close();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Update(ModelRegiao regiao){
		try {
			cn = new classeConexao();
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call SPUPDATEREGIAO(?,?,?)}");
			
			cs.setInt(1, regiao.getID());
			cs.setString(2, regiao.getNome());
			cs.setInt(3, regiao.getAtivo());
			
			cs.execute();
			cs.close();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ModelRegiao> Selecionar(){
		List<ModelRegiao> regiao = new ArrayList<ModelRegiao>();
		ModelRegiao r;
		
		try {
			
			cn = new classeConexao();
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from tblRegiao where Ativo = 1;");
			rs = ps.executeQuery();
			while(rs.next()){
				r = new ModelRegiao();
				r.setID(rs.getInt(1));
				r.setNome(rs.getString(2));
				regiao.add(r);
			}
			cs.close();
			ps.close();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return regiao;
	}
}
