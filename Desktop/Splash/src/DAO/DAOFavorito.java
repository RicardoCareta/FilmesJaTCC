package DAO;

import java.util.ArrayList;
import java.util.List;

import Modelo.ModelCatalogo;
import Modelo.ModelConta;
import Modelo.ModelFavorito;

public class DAOFavorito extends DAOBas{
	
	public void AtivoFav(int IDFav, int Ativo){
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spUpdateFavorito(?,?)}");
			cs.setInt("p_IDFav", IDFav);
			cs.setInt("p_Ativo", Ativo);
			cs.execute();
			cs.close();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Inserir(ModelFavorito fav){
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spInsertFavorito(?,?,?)}");
			cs.setInt("p_IDCat", fav.getCatalogo().getID());
			cs.setInt("p_IDConta", fav.getConta().getID());
			cs.setInt("p_Ativo", fav.getAtivo());
			cs.execute();
			cs.close();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ModelFavorito> Selecionar(){
		List<ModelFavorito> retorno = new ArrayList<ModelFavorito>();
		ModelFavorito fav;
		ModelCatalogo cat;
		ModelConta conta;
		
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from Favoritos");
			rs = ps.executeQuery();
			while(rs.next()){
				cat = new ModelCatalogo();
				cat.setID(rs.getInt("IDCAT"));
				cat.setNome(rs.getString("NOME"));
				cat.setIDSerieCatalogo(rs.getInt("IDSERIECATALOGO"));
				cat.setAtivo(rs.getInt("ATIVOC"));

				conta = new ModelConta();
				conta.setID(rs.getInt("IDCONTA"));
				conta.setAtivo(rs.getInt("ATIVOCO"));
				
				fav = new ModelFavorito();
				fav.setID(rs.getInt("IDFAVORITO"));
				fav.setAtivo(rs.getInt("ATIVOF"));
				fav.setCatalogo(cat);
				fav.setConta(conta);
			
				retorno.add(fav);
			}
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
}
