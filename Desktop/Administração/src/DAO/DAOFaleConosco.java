package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.DAOBas;
import model.MDFaleConosco;
import util.classeConexao;

public class DAOFaleConosco extends DAOBas{
	
	public DAOFaleConosco(){
		cn = new classeConexao();
	}
	
	public List<MDFaleConosco> Selecionar(){
		List<MDFaleConosco> lista = new ArrayList<MDFaleConosco>();
		cn.Conectar();
		
		try {
			ps = cn.getConnection().prepareStatement("select * from tblFalaConosco");
			rs = ps.executeQuery();
			
			MDFaleConosco fala;
			
			while(rs.next()){
				fala = new MDFaleConosco();
				fala.setEmail(rs.getString("EMAIL"));
				fala.setMensagem(rs.getString("MENSAGEM"));
				fala.setNome(rs.getString("NOME"));
				fala.setData(rs.getDate("DATA"));
				fala.setIDFC(rs.getInt("ID_FC"));
				fala.setStatus(rs.getInt("STATUS"));
				
				lista.add(fala);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}
	
}
