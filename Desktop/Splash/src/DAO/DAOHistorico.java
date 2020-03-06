package DAO;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Constantes.ConsAssistido;
import Modelo.ModelHistorico;
import util.classeConexao;
public class DAOHistorico {
	private CallableStatement cs;
	private classeConexao cn;
	
	private ResultSet rs;
	private PreparedStatement ps;
	
	public void Inserir (ModelHistorico historico){
		try {
			cn = new classeConexao();
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spInsertHIstorico(?, ?, ?, ?, ?, ?)}");
			cs.setInt(1, historico.getAvaliacao());
			cs.setInt(2, historico.getAssistido());
			cs.setInt(3,historico.getIDCat());
			cs.setInt(4, historico.getIDConta());
			cs.setDate(5, historico.getData());
			cs.setInt(6, historico.getTempo());
			
			cs.execute();
			cn.Desconectar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void UpdateNota(int Avaliacao, int ID){
		cn = new classeConexao();
		try{
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("update tblHistorico set AVALIACAO = " + Avaliacao + ", ASSISTIDO = " + ConsAssistido.DESASSISTIDO + " where IDHIST = " + ID);
			ps.execute();
			cn.Desconectar();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<ModelHistorico> Selecionar(){
		List<ModelHistorico> retorno = new ArrayList<ModelHistorico>();
		ModelHistorico h;
		cn = new classeConexao();
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from tblHistorico");
			rs = ps.executeQuery();
			while(rs.next()){
				h = new ModelHistorico();
				h.setID(rs.getInt("IDHIST"));
				h.setAvaliacao(rs.getInt("AVALIACAO"));
				h.setAssistido(rs.getInt("ASSISTIDO"));
				h.setIDCat(rs.getInt("IDCAT"));
				h.setIDConta(rs.getInt("IDCONTA"));
				h.setTempo(rs.getInt("TEMPO"));
				retorno.add(h);
			}
			rs.close();
			ps.close();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	public Date DataBD(){
		String data = "";
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from NOW");
			rs = ps.executeQuery();
			while(rs.next()){
				data = rs.getString("NOW");
			}
			
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date parsed = null;
		try {
			parsed = format.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return new Date(parsed.getTime());
	}
	
	public void ChangeAssistido(int ID, int Assistido){
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spChangeAssistido(? ,?)}");
			cs.setInt("p_ID", ID);
			cs.setInt("p_Assistido", Assistido);
			
			cs.execute();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

				
	
		

		