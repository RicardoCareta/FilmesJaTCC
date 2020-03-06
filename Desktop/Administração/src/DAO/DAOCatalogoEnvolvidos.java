package DAO;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.ModelCatalogo;
import model.ModelCatalogoEnvolvidos;
import model.ModelElenco;
import model.ModelTipoEnvolvido;
import util.classeConexao;

public class DAOCatalogoEnvolvidos{

	
	private classeConexao cn;
	private PreparedStatement ps;
	private CallableStatement cs;
	private ResultSet rs;
	
	public void Inserir(ModelCatalogoEnvolvidos ce) {
		cn = new classeConexao();
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spInsertCatalogosEnvolvidos(?,?,?,?)}");
			cs.setInt(1, ce.getIDCat().getID());
			cs.setInt(2, ce.getIDEnvolvido().getID_Envo());
			cs.setInt(3, ce.getIDTipoEnvolvido().getID());
			cs.setInt(4, ce.getAtivo());
			
			cs.execute();
			cs.close();
			cn.Desconectar();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Update(ModelCatalogoEnvolvidos ce) {
		cn = new classeConexao();
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spUpdateCatalogoEnvolvidos(?,?)}");
			cs.setInt("p_idCat", ce.getIDCat().getID());
			cs.setInt("p_Ativo", ce.getAtivo());
			
			cs.execute();
			cs.close();
			cn.Desconectar();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ModelCatalogoEnvolvidos> Selecionar() {
		List<ModelCatalogoEnvolvidos> retorno = new ArrayList<ModelCatalogoEnvolvidos>();
		ModelCatalogoEnvolvidos ce;
		ModelCatalogo c;
		ModelElenco e;
		ModelTipoEnvolvido te;
		
		cn = new classeConexao();
		
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from elencocatalogo");
			rs = ps.executeQuery();
			while(rs.next()){
				ce = new ModelCatalogoEnvolvidos();
				ce.setID(rs.getInt("IDCE"));
				
				c = new ModelCatalogo();
				c.setID(rs.getInt("IDCat"));
				ce.setIDCat(c);
				
				e = new ModelElenco();
				e.setID_Envo(rs.getInt("IDEnvolvidoE"));
				e.setNome(rs.getString("NomeE"));
				ce.setIDEnvolvido(e);
				
				te = new ModelTipoEnvolvido();
				te.setID(rs.getInt("IDTIPOENVOLVIDO"));
				te.setTipo(rs.getString("Tipo"));
				ce.setIDTipoEnvolvido(te);
				ce.setAtivo(rs.getInt("AtivoCE"));
				
				retorno.add(ce);
			}
		} catch (Exception ea) {
			ea.printStackTrace();
			return retorno;
		}
		
		return retorno;
	}
	
}
