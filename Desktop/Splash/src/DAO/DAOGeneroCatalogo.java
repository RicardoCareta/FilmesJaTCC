package DAO;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Modelo.ModelCatalogo;
import Modelo.ModelGenero;
import Modelo.ModelGeneroCatalogo;
import util.classeConexao;

public class DAOGeneroCatalogo {
	
	private PreparedStatement ps;
	private CallableStatement cs;
	private classeConexao cn;
	private ResultSet rs;
	
	public void Inserir(ModelGeneroCatalogo gc){
		cn = new classeConexao();
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spInsertGeneroCatalogo(?,?,?)}");
			cs.setInt(1, gc.getIDGen().getIdGen());
			cs.setInt(2, gc.getIDCat().getID());
			cs.setInt(3, 1);
			
			cs.execute();
			cs.close();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Update(ModelGeneroCatalogo gc){
		cn = new classeConexao();
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spUpdateGeneroCatalogo(?,?)}");
			cs.setInt("p_idCat", gc.getIDCat().getID());
			cs.setInt("p_Ativo", gc.getAtivo());
			
			cs.execute();
			cs.close();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ModelGeneroCatalogo> Selecionar(){
		List<ModelGeneroCatalogo> retorno = new ArrayList<ModelGeneroCatalogo>();
		cn = new classeConexao();
		ModelGeneroCatalogo gc;
		ModelGenero g;
		ModelCatalogo c;
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from generoCatalogo");
			rs = ps.executeQuery();
			while(rs.next()){
				
				g = new ModelGenero();
				g.setIdGen(rs.getInt("IDGenero"));
				g.setNome(rs.getString("Genero"));
				g.setAtivo(rs.getInt("AtivoG"));
				
				c = new ModelCatalogo();
				c.setID(rs.getInt("IDCat"));
				
				gc = new ModelGeneroCatalogo(c, g, rs.getInt("AtivoGC"));
				retorno.add(gc);
			}
		} catch (Exception e) {
			return retorno;
		}
		
		return retorno;
	}
	
	public List<ModelGeneroCatalogo> SelecionarGC(){
		List<ModelGeneroCatalogo> retorno = new ArrayList<ModelGeneroCatalogo>();
		cn = new classeConexao();
		ModelGeneroCatalogo gc = null;
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from generoCatalogo");
			rs = ps.executeQuery();
			while(rs.next()){
				ModelCatalogo iDCat = new ModelCatalogo();
				iDCat.setID(rs.getInt("IDCAT"));
				iDCat.setAtivo(rs.getInt("ATIVOC"));
				ModelGenero iDGen = new ModelGenero();
				iDGen.setIdGen(rs.getInt("IDGENERO"));
				iDGen.setNome(rs.getString("GENERO"));
				iDGen.setAtivo(rs.getInt("ATIVOG"));
				int ativo = rs.getInt("ATIVOGC");
				gc = new ModelGeneroCatalogo(iDCat, iDGen, ativo);
				retorno.add(gc);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return retorno;
		}
		
		return retorno;
	}
}
