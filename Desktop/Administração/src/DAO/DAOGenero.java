package DAO;

import util.classeConexao;

import java.sql.CallableStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.ModelGenero;


public class DAOGenero {
	private classeConexao cn;
	private CallableStatement cs;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public void Inserir(ModelGenero genero){
		try {
			cn = new classeConexao();
			
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spInsertGenero(?,?)}");
			cs.setString(1, genero.getNome());
			cs.setInt(2, 1);
			
			cs.execute();
			cn.Desconectar();
		} catch (Exception e) {
			try {
				throw new Exception("Falha ao tentar cadastrar um gênero");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void Update(ModelGenero genero){
		try {
			cn = new classeConexao();
			
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call SPUPDATEGENERO(?,?,?)}");
			
			cs.setInt(1, genero.getIdGen());
			cs.setString(2, genero.getNome());
			cs.setInt(3, genero.getAtivo());
			
			cs.execute();
			cn.Desconectar();
		} catch (Exception e) {
			try {
				throw new Exception("Falha ao tentar cadastrar um gênero");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	
	public List<ModelGenero> Selecionar(){
		List<ModelGenero> generos = new ArrayList<>();
		ModelGenero g = new ModelGenero();
		try {
			rs = null;
			cn = new classeConexao();
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from tblGenero order by Genero");
			rs = ps.executeQuery();
			while(rs.next()){
				g = new ModelGenero();
				g.setIdGen(rs.getInt(1));
				g.setNome(rs.getString(2));
				g.setAtivo(rs.getInt(3));
				generos.add(g);
			}
			ps.close();
			rs.close();
			cn.Desconectar();
		} catch (Exception e) {
			// TODO: handle exception
			try {
				throw new Exception("Falha: " + e.getMessage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return generos;
	}
}
