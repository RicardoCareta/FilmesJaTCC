package DAO;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Modelo.ModelCliente;
import Modelo.ModelPlanos;
import Modelo.ModelContrato;

public class DAOContrato extends DAOBas{

	public List<ModelContrato> Selecionar(){
		List<ModelContrato> retorno = new ArrayList<ModelContrato>();
		
		ModelContrato contrato;
		ModelCliente cliente;
		ModelPlanos plano;
		
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from Contrato order by VENCIMENTO desc");
			rs = ps.executeQuery();
			while(rs.next()){
				cliente = new ModelCliente();
				cliente.setID(rs.getInt("IDCLIENTE"));
				cliente.setAtivo(rs.getInt("AtivoC"));
				
				plano = new ModelPlanos();
				plano.setID(rs.getInt("IDPlano"));
				plano.setPreco(rs.getDouble("PrecoP"));
				plano.setQntTelas(rs.getInt("QTDTelas"));
				plano.setNomePlano(rs.getString("NomePlano"));
				plano.setAtivo(rs.getInt("AtivoP"));
				
				contrato = new ModelContrato();
				contrato.setID(rs.getInt("IDContrato"));
				contrato.setVencimento(rs.getDate("VENCIMENTO"));
				contrato.setPreco(rs.getDouble("PrecoCon"));
				contrato.setCliente(cliente);
				contrato.setPlano(plano);
				contrato.setAtivo(rs.getInt("AtivoCon"));
				
				retorno.add(contrato);
			}
			
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
	
}
