package DAO;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.classeConexao;
import Modelo.ModelCategoria;

public class DAOCategoria {

	private classeConexao cn;
	private CallableStatement cs;
	
	private PreparedStatement ps;
	private ResultSet rs;
	
	public void Inserir(ModelCategoria cat){
		
		try{
		cn = new classeConexao();
		cn.Conectar();
		
		cs = cn.getConnection().prepareCall("{call spInsertCategoria(?,?)}");
		cs.setString(1, cat.getNome());
		cs.setInt(2, 1);
		
		cs.execute();
		cn.Desconectar();
		}catch (Exception e){
			try {
				throw new Exception("Falha ao tentar cadastrar uma categoria");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public List<ModelCategoria> Selecionar(){
		List<ModelCategoria> retorno = new ArrayList<ModelCategoria>();
		ModelCategoria c;
		
		cn = new classeConexao();
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from tblCategoria");
			rs = ps.executeQuery();
			while(rs.next()){
				c = new ModelCategoria();
				c.setID_Cat(rs.getInt(1));
				c.setNome(rs.getString(2));
				c.setAtivo(rs.getInt(3));
				retorno.add(c);
			}
			rs.close();
			ps.close();
			cn.Desconectar();
		} catch (Exception e) {
			return retorno;
		}
		
		return retorno;
	}
	
	public void Update(ModelCategoria cat){
		cn = new classeConexao();
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spUpdateCategoria(?,?,?)}");
		
			cs.setString(1, cat.getNome());
			cs.setInt(2, cat.getID_Cat());
			cs.setInt(3, cat.getAtivo());
			
			cs.execute();
			cs.close();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
