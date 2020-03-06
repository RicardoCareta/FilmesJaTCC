package DAO;

import util.classeConexao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.ModelCatalogo;
import model.ModelClassificacao;
import model.ModelPais;

public class DAOClassificacao {
	
	private classeConexao cn;
	private PreparedStatement ps;
	private CallableStatement cs;
	private ResultSet rs;
	
	public void Inserir(ModelClassificacao clas){
		cn = new classeConexao();
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spInsertClassificacao(?,?,?,?)}");
			cs.setInt(1, clas.getIDCat().getID());
			cs.setInt(2, clas.getIDPais().getIDPais());
			cs.setInt(3, clas.getIdade());
			cs.setInt(4, 1);
			cs.execute();
			cs.close();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Update(ModelClassificacao clas){
		cn = new classeConexao();
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spUpdateClassificacao(?,?)}");
			cs.setInt("p_idCat", clas.getIDCat().getID());
			cs.setInt("p_Ativo", 1);
			cs.execute();
			cs.close();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ModelClassificacao> Selecionar(){
		List<ModelClassificacao> retorno = new ArrayList<ModelClassificacao>();
		ModelClassificacao c;
		ModelCatalogo cat;
		ModelPais p;
		
		cn = new classeConexao();
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from Classificacao");
			rs = ps.executeQuery();
			while(rs.next()){
				c = new ModelClassificacao();
				c.setID(rs.getInt("IDClassificacao"));
				cat = new ModelCatalogo();
				cat.setID(rs.getInt("IDCat"));
				c.setIDCat(cat);
				p = new ModelPais();
				p.setIDPais(rs.getInt("IDPaisP"));
				p.setNome(rs.getString("Pais"));
				c.setIDPais(p);
				c.setIdade(rs.getInt("Idade"));
				c.setAtivo(rs.getInt("AtivoCC"));
				retorno.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return retorno;
		}
		
		return retorno;
	}
}
