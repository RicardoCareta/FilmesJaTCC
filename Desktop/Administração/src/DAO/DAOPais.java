package DAO;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ModelPais;
import util.classeConexao;

public class DAOPais {
	private CallableStatement cs;

	private classeConexao cn;

	private PreparedStatement ps;

	private ResultSet rs;

	public void Inserir(ModelPais pais) {
		try {
			cn = new classeConexao();
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spInsertPais(?,?,?)}");
			cs.setString(1, pais.getNome());
			cs.setInt(2, pais.getRegiao());
			cs.setInt(3, 1);

			cs.execute();
			cn.Desconectar();
		} catch (Exception e) {
			try {
				throw new Exception("Falha ao inserir um pais: " + e.getMessage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public List<ModelPais> SelecionarPais() {

		List<ModelPais> lista = new ArrayList<ModelPais>();
		ModelPais pais = new ModelPais();
		cn = new classeConexao();
		cn.Conectar();

		try {

			ps = cn.getConnection().prepareStatement("select * from tblPais");

			rs = ps.executeQuery();
			while (rs.next()) {
				pais = new ModelPais();
				pais.setNome(rs.getString(2));
				pais.setIDPais(rs.getInt(1));
				pais.setAtivo(rs.getInt(3));
				pais.setRegiao(rs.getInt(4));
				lista.add(pais);
			}
			rs.close();
			ps.close();
			cn.Desconectar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}
	
	public void Update(ModelPais pais){
		cn = new classeConexao();
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spUpdatePais(?,?,?,?)}");
			cs.setInt(1, pais.getIDPais());
			cs.setString(2, pais.getNome());
			cs.setInt(3, pais.getRegiao());
			cs.setInt(4, pais.getAtivo());
			
			cs.execute();
			cs.close();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
