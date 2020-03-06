package DAO;
import java.sql.CallableStatement;
import java.sql.SQLException;
import Modelo.ModelAvaliacaoCena;
import util.classeConexao;

public class DAOAvaliacao {
	private CallableStatement cs;
	private classeConexao cn;
	
	public DAOAvaliacao(){}
	
	public void Inserir(ModelAvaliacaoCena avaliacao){
		try{
	cn = new classeConexao();
	cn.Conectar();
	cs = cn.getConnection().prepareCall("{call InserirConta(?, ?, ?, ?)}");
	
	cs.setInt(1, avaliacao.getMinuto());
	cs.setInt(2, avaliacao.getNota() );
	cs.setInt(3, avaliacao.getIdCat());
	cs.setInt(4, avaliacao.getIdConta());
	
	cs.execute();
	cn.Desconectar();
} 
	catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}

public void Atualizar (ModelAvaliacaoCena avaliacao){
	try{
		cn = new classeConexao();
		cn.Conectar();	
		cs = cn.getConnection().prepareCall("{call InserirConta(?, ?, ?, ?)}");
		
		cs.setInt(1,  avaliacao.getIdAvCena());
		cs.setInt(2, avaliacao.getMinuto());
		cs.setInt (3, avaliacao.getNota());
		cs.setInt(4, avaliacao.getIdCat());
		cs.setInt(4, avaliacao.getIdConta());
		cs.setInt(5, avaliacao.getAtivo());
		
		cs.execute();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}



	

	
}
	

