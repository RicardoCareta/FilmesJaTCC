package DAO;

import util.classeConexao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.ModelTituloCatalogo;

public class DAOTituloCatalogo {
	
	private classeConexao cn;
	private PreparedStatement ps;
	private CallableStatement cs;
	private ResultSet rs;
	
	public void Inserir(ModelTituloCatalogo tc){
		cn = new classeConexao();
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spInsertTituloCatalogo(?,?)}");
			cs.setString(1, tc.getTitulo());
			cs.setInt(2, tc.getIDCat());
			cs.execute();
			cs.close();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Update(ModelTituloCatalogo tc){
		cn = new classeConexao();
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spUpdateTituloCatalogo(?,?)}");
			cs.setInt("p_idCat", tc.getIDCat());
			cs.setInt("p_Ativo", tc.getAtivo());
			cs.execute();
			cs.close();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ModelTituloCatalogo> Selecionar(){
		List<ModelTituloCatalogo> retorno = new ArrayList<ModelTituloCatalogo>();
		ModelTituloCatalogo tc;
		cn = new classeConexao();
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select IDTC, IDCat, Titulo, Ativo from tblTituloCatalogo");
			rs = ps.executeQuery();
			while(rs.next()){
				tc = new ModelTituloCatalogo();
				tc.setID(rs.getInt(1));
				tc.setIDCat(rs.getInt(2));
				tc.setTitulo(rs.getString(3));
				tc.setAtivo(rs.getInt(4));
				retorno.add(tc);
			}
			cn.Desconectar();
		} catch (Exception e) {
			return retorno;
		}
		
		return retorno;
	}
}
