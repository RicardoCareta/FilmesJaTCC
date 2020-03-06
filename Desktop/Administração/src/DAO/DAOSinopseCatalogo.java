package DAO;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.ModelCatalogo;
import model.ModelIdioma;
import model.ModelSinopseCatalogo;

import util.classeConexao;

public class DAOSinopseCatalogo {
	
	private classeConexao cn;
	private PreparedStatement ps;
	private CallableStatement cs;
	private ResultSet rs;
	
	public void Inserir(ModelSinopseCatalogo sp){
		cn = new classeConexao();
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spInsertSinopseCatalogo(?,?,?,?)}");
			cs.setInt(1, sp.getIDCat().getID());
			cs.setString(2, sp.getSinopse());
			cs.setInt(3, sp.getIDIdioma().getID());
			cs.setInt(4, 1);
			
			cs.execute();
			cs.close();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Update(ModelSinopseCatalogo sp){
		cn = new classeConexao();
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spUpdateSinopseCatalogo(?,?)}");
			cs.setInt("p_idCat", sp.getIDCat().getID());
			cs.setInt("p_Ativo", sp.getAtivo());
			cs.execute();
			cs.close();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ModelSinopseCatalogo> Selecionar(){
		List<ModelSinopseCatalogo> retorno = new ArrayList<ModelSinopseCatalogo>();
		ModelSinopseCatalogo sc;
		ModelCatalogo c;
		ModelIdioma i;
		cn = new classeConexao();
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from Sinopse");
			rs = ps.executeQuery();
			while(rs.next()){
				sc = new ModelSinopseCatalogo();
				sc.setID(rs.getInt("IDSinopse"));
				c = new ModelCatalogo();
				c.setID(rs.getInt("IDCat"));
				sc.setIDCat(c);
				sc.setSinopse(rs.getString("Sinopse"));
				i = new ModelIdioma();
				i.setID(rs.getInt("IdIdiomaI"));
				sc.setIDIdioma(i);
				sc.setAtivo(rs.getInt("AtivoSC"));
				retorno.add(sc);
			}
		} catch (Exception e) {
			return retorno;
		}
		return retorno;
	}
}
