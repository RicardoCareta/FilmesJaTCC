package DAO;

import java.util.ArrayList;
import java.util.List;

import model.ModelAcesso;
import model.ModelCatalogo;
import model.ModelPais;

public class DAOAcesso extends DAOBas{

	public void Inserir(ModelAcesso a){
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spInsertAcesso(?,?,?,?)}");
			cs.setInt("p_IDCat", a.getIDCat().getID());
			cs.setInt("p_IDPais", a.getIDPais().getIDPais());
			cs.setInt("p_acesso", a.getAcesso());
			cs.setInt("p_ativo", a.getAtivo());
			cs.execute();
			cs.close();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Update(ModelAcesso a){
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spUpdateAcesso(?,?)}");
			cs.setInt("p_IDCat", a.getIDCat().getID());
			cs.setInt("p_ativo", a.getAtivo());
			cs.execute();
			cs.close();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ModelAcesso> Selecionar(){
		List<ModelAcesso> retorno = new ArrayList<ModelAcesso>();
		ModelAcesso a;
		ModelCatalogo c;
		ModelPais p;
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from acessopais");
			rs = ps.executeQuery();
			while(rs.next()){
				c = new ModelCatalogo();
				c.setID(rs.getInt("IDCat"));
				p = new ModelPais();
				p.setIDPais(rs.getInt("IDPais"));
				p.setNome(rs.getString("Pais"));
				p.setRegiao(rs.getInt("IDRegiao"));
				a = new ModelAcesso();
				a.setID(rs.getInt("IDAcesso"));
				a.setAcesso(rs.getInt("Acesso"));
				a.setAtivo(rs.getInt("AtivoA"));
				a.setIDCat(c);
				a.setIDPais(p);
				retorno.add(a);
			}
			
			rs.close();
			ps.close();
			cn.Desconectar();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	
}
