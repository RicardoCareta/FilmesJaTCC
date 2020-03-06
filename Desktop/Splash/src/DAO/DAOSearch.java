package DAO;

import java.util.ArrayList;
import java.util.List;

import Modelo.ModelCatalogo;
import Modelo.ModelCatalogoEnvolvidos;
import Modelo.ModelEnvolvidos;
import Modelo.ModelIdioma;
import Modelo.ModelSearch;
import Modelo.ModelTituloCatalogo;
import Modelo.ModelTituloIdioma;

public class DAOSearch extends DAOBas{
	
	public List<ModelSearch> Selecionar(){
		List<ModelSearch> retorno = new ArrayList<ModelSearch>();
		
		ModelSearch se;
		ModelCatalogo cat;
		ModelTituloCatalogo tc;
		ModelTituloIdioma ti;
		ModelIdioma idioma;
		ModelEnvolvidos envo;
		ModelCatalogoEnvolvidos ce;
		
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from Search");
			rs = ps.executeQuery();
			while(rs.next()){
				
				envo = new ModelEnvolvidos();
				envo.setIdEnvo(rs.getInt("IDEnvolvido"));
				envo.setNome(rs.getString("NOMEE"));
				envo.setAtivo(rs.getInt("ATIVOE"));
				
				cat = new ModelCatalogo();
				cat.setID(rs.getInt("IDCat"));
				cat.setNome(rs.getString("Nome"));
				
				tc = new ModelTituloCatalogo();
				tc.setID(rs.getInt("IDTC"));
				tc.setTitulo(rs.getString("TITULO"));
				tc.setAtivo(rs.getInt("ATIVOTC"));
				
				
				idioma = new ModelIdioma();
				idioma.setID(rs.getInt("IDIDIOMA"));
				idioma.setNome(rs.getString("IDIOMA"));
				
				ti = new ModelTituloIdioma();
				ti.setAtivo(rs.getInt("ATIVOTI"));
				ti.setIDIdi(idioma);
				ti.setIDTc(tc);
				
				ce = new  ModelCatalogoEnvolvidos();
				ce.setIdCat(rs.getInt("IDCat"));
				ce.setEnvo(envo);

				se = new ModelSearch();
				se.setCatalogo(cat);
				se.setTituloCatalogo(tc);
				se.setTituloIdioma(ti);
				se.setCatalogoEnvolvido(ce);
				
				retorno.add(se);
				
			}
			
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
}
