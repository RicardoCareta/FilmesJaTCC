package DAO;

import util.classeConexao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.ModelElenco;

public class DAOElenco {

	private classeConexao cn;
	private CallableStatement cs;
	private ResultSet rs;
	private PreparedStatement ps;
	
	
	public void Inserir(ModelElenco elenco) {
		try {
			cn = new classeConexao();
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spInsertEnvolvido(?,?)}");
			cs.setString(1, elenco.getNome());
			cs.setInt(2, 1);
			cs.execute();

			cn.Desconectar();
		} catch (Exception e) {
			try {
				throw new Exception("Falha ao inserir um envolvido");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
	}
	
	public List<ModelElenco> Selecionar(){
		List<ModelElenco> listElenco = new ArrayList<ModelElenco>();
		try {
			ModelElenco elenco = new ModelElenco();

			cn = new classeConexao();
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from tblEnvolvido");
			rs = ps.executeQuery();
			while(rs.next()){
				elenco = new ModelElenco();
				elenco.setID_Envo(rs.getInt(1));
				elenco.setNome(rs.getString(2));
				elenco.setAtivo(rs.getInt(3));
				listElenco.add(elenco);
			}
			ps.close();
			rs.close();
			cn.Desconectar();
			
		} catch (Exception e) {
			// TODO: handle exception
			try {
				throw new Exception("Falha ao tentar selecionar o elenco");
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return listElenco;
	}
	
	public void Update(ModelElenco elenco){
		cn = new classeConexao();
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spUpdateEnvolvido(?,?,?)}");
			
			cs.setInt(1, elenco.getID_Envo());
			cs.setString(2, elenco.getNome());
			cs.setInt(3, elenco.getAtivo());
			
			cs.execute();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Pesquisar usando o BD, mas é muito perigoso e aumenta o fluxo de rede
	/*public List<ModelElenco> SelecionarLimit(String texto){
		
		List<ModelElenco> listElenco = new ArrayList<ModelElenco>();
		try {
			ModelElenco elenco = new ModelElenco();
			cn = new classeConexao();
			ps = cn.getConnection().prepareStatement("select * from tblEnvolvido where rownum <= 4 and Nome like '?%' and Ativo = 1");
			ps.setString(1, texto);
			rs = ps.executeQuery();
			while(rs.next()){
				elenco = new ModelElenco();
				elenco.setNome(rs.getString(1));
				elenco.setAtivo(rs.getInt(2));
				listElenco.add(elenco);
			}
		} catch (Exception e) {
			try {
				throw new Exception("Falha ao buscar: " + e.getMessage());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return listElenco;
	}*/
}
