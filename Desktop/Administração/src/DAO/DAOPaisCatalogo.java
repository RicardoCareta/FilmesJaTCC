package DAO;

import util.classeConexao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.ModelCatalogo;
import model.ModelPais;
import model.ModelPaisCatalogo;

public class DAOPaisCatalogo {

	private classeConexao cn;
	private CallableStatement cs;
	
	private PreparedStatement ps;
	private ResultSet rs;
	
	public void Inserir(ModelPaisCatalogo pc){
		cn = new classeConexao();
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spInsertPaisCatalogo(?,?,?)}");
			
			cs.setInt(1, pc.getIdPais().getIDPais());
			cs.setInt(2, pc.getIdCat().getID());
			cs.setInt(3, 1);
			
			cs.execute();
			cs.close();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ModelPaisCatalogo> Selecionar(){
		List<ModelPaisCatalogo> retorno = new ArrayList<ModelPaisCatalogo>();
		
		cn = new classeConexao();
		ModelCatalogo c;
		ModelPaisCatalogo pc;
		ModelPais p;
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from PaisCatalogo");
			rs = ps.executeQuery();
			while(rs.next()){
				c = new ModelCatalogo();
				c.setID(rs.getInt("IDCat"));
				p = new ModelPais();
				p.setIDPais(rs.getInt("IDPaisPC"));
				p.setNome(rs.getString("Pais"));
				pc = new ModelPaisCatalogo();
				pc.setAtivo(rs.getInt("AtivoPC"));
				pc.setIdCat(c);
				pc.setIdPais(p);
				retorno.add(pc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public void Update(ModelPaisCatalogo pc){
		cn = new classeConexao();
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spUpdatePaisCatalogo(?,?)}");
			cs.setInt("p_idCat", pc.getIdCat().getID());
			cs.setInt("p_Ativo", pc.getAtivo());
			cs.execute();
			cs.close();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
