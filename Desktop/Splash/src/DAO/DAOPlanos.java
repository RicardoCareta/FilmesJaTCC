package DAO;

import java.util.ArrayList;
import java.util.List;

import Modelo.ModelPlanos;

public class DAOPlanos extends DAOBas{

	public List<ModelPlanos> Selecionar(){
		List<ModelPlanos> retorno = new ArrayList<ModelPlanos>();
		ModelPlanos plano;
		
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from tblPlano");
			rs = ps.executeQuery();
			while(rs.next()){
				plano = new ModelPlanos();
				plano.setID(rs.getInt("IDPLA"));
				plano.setPreco(rs.getDouble("PRECO"));
				plano.setQntTelas(rs.getInt("QTDTELAS"));
				plano.setNomePlano(rs.getString("NOMEPLANO"));
				plano.setAtivo(rs.getInt("ATIVO"));
				
				retorno.add(plano);
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
