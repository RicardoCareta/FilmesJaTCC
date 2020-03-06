package DAO;

import util.classeConexao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Modelo.ModelIdioma;
import Modelo.ModelTituloCatalogo;
import Modelo.ModelTituloIdioma;

public class DAOTituloIdioma {
	
	private classeConexao cn;
	private CallableStatement cs;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public void Inserir(ModelTituloIdioma ti){
		cn = new classeConexao();
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spInsertTituloIdioma(?,?,?)}");
			cs.setInt(1, ti.getIDIdi().getID());
			cs.setInt(2, ti.getIDTc().getID());
			cs.setInt(3, 1);
			cs.execute();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ModelTituloIdioma> Selecionar(){
		List<ModelTituloIdioma> retorno = new ArrayList<ModelTituloIdioma>();
		ModelTituloIdioma ti;
		ModelTituloCatalogo tc;
		ModelIdioma i;
		
		try {
			cn = new classeConexao();
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from TITULOCATALOGOIDIOMA");
			rs = ps.executeQuery();
			while(rs.next()){
				tc = new ModelTituloCatalogo();
				tc.setIDCat(rs.getInt("IDCat"));
				tc.setID(rs.getInt("IDTC"));
				tc.setTitulo(rs.getString("Titulo"));
				tc.setAtivo(rs.getInt("AtivoTC"));
				ti = new ModelTituloIdioma();
				i = new ModelIdioma();
				i.setID(rs.getInt("IDIdioma"));
				i.setNome(rs.getString("Idioma"));
				ti.setIDIdi(i);
				ti.setIDTc(tc);
				ti.setAtivo(1);
				retorno.add(ti);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return retorno;
		}
		
		return retorno;
	}
}
