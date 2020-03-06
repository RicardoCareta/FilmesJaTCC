package DAO;

import util.classeConexao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Modelo.ModelIdioma;

public class DAOIdioma {

	private classeConexao cn;
	private CallableStatement cs;
	private ResultSet rs;
	private PreparedStatement ps;

	public void Inserir(ModelIdioma idioma) {
		cn = new classeConexao();
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spInsertIdioma(?,?)}");
			cs.setString(1, idioma.getNome());
			cs.setInt(2, 1);
			
			cs.execute();
			cn.Desconectar();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Update(ModelIdioma idioma) {
		cn = new classeConexao();
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spUpdateIdioma(?,?,?)}");
			cs.setInt("p_idIdioma", idioma.getID());
			cs.setString("p_idioma", idioma.getNome());
			cs.setInt("p_Ativo", idioma.getAtivo());
			
			cs.execute();
			cs.close();
			cn.Desconectar();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List<ModelIdioma> Selecionar(){
		List<ModelIdioma> retorno = new ArrayList<ModelIdioma>();
		ModelIdioma i;
		try {
			cn = new classeConexao();
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from tblIdioma");
			rs = ps.executeQuery();
			while(rs.next()){
				i = new ModelIdioma();
				i.setNome(rs.getString(2));
				i.setID(rs.getInt(1));
				i.setAtivo(rs.getInt(3));
				retorno.add(i);
			}
		} catch (Exception e) {
			return retorno;
		}
		
		return retorno;
	}
}
