package DAO;

import java.util.ArrayList;
import java.util.List;

import control.ControlConta;
import model.ModelCliente;
import model.ModelIdioma;
import model.ModelPais;

public class DAOCliente extends DAOBas{
	
	public List<ModelCliente> Selecionar(){
		List<ModelCliente> retorno = new ArrayList<ModelCliente>();
		ModelCliente cl;
		ModelIdioma i;
		ModelPais p ;
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from tblUsuarioCliente");
			rs = ps.executeQuery();
			while(rs.next()){
				cl = new ModelCliente();
				cl.setAtivo(rs.getInt("ATIVO"));
				cl.setID(rs.getInt("IDCLIENTE"));
				cl.setEmail(rs.getString("EMAIL"));
				cl.setSenha(rs.getString("SENHA"));
				cl.setCPF(rs.getString("CPF"));
				cl.setTelefone(rs.getString("TELEFONE"));
				cl.setSenhaCatalogo(rs.getInt("SENHACATALOGO"));
				i = new ModelIdioma();
				i.setID(rs.getInt("IDIDIOMA"));
				cl.setIdioma(i);
				p = new ModelPais();
				p.setIDPais(rs.getInt("IDPAIS"));
				cl.setPais(p);
				
				retorno.add(cl);
			}
			
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	public int CountLineOn(int IDCliente){
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select count(*) as TOTAL from tblConta where IDCliente = ? and Line = ?");
			ps.setInt(1, IDCliente);
			ps.setInt(2, ControlConta.LINE_ON);
			rs = ps.executeQuery();
			rs.next();
			int retorno = rs.getInt("TOTAL");
			cn.Desconectar();
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public List<String> SelecionarQnt(){
		List<String> retorno = new ArrayList<String>();
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from usuarioscadastradosqtd");
			rs = ps.executeQuery();
			while(rs.next()){
				for(int i = 1; i < 13; i++){
					retorno.add(rs.getString(i));
				}
			}
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	
	public List<String> SelecionarQtdAno(){
		List<String> retorno = new ArrayList<String>();
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from USUARIOSCADASTRADOSQTDANO");
			rs = ps.executeQuery();
			while(rs.next()){
				for(int i = 1; i < 6; i++){
					retorno.add(rs.getString(i));
				}
			}
			
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public List<String> SelecionarGanhosReais(){
		List<String> retorno = new ArrayList<String>();
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from GANHOSVERDADEIROS");
			rs = ps.executeQuery();
			while(rs.next()){
				for(int i = 1; i < 13; i++){
					if(rs.getString(i) == null){
						retorno.add("0");
					}else{
						retorno.add(rs.getString(i));
					}
				}
			}
			
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	
	public List<String> SelecionarGanhosEsperados(){
		List<String> retorno = new ArrayList<String>();
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from GANHOSESPERADOS");
			rs = ps.executeQuery();
			while(rs.next()){
				for(int i = 1; i < 13; i++){
					if(rs.getString(i) == null){
						retorno.add("0");
					}else{
						retorno.add(rs.getString(i));
					}
				}
			}
			
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public List<String> SelecionarMaisAcessos(){
		List<String> retorno = new ArrayList<String>();
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from MAISACESSOS");
			rs = ps.executeQuery();
			while(rs.next()){
				for(int i = 1; i < 13; i++){
					if(rs.getString(i) == null){
						retorno.add("0");
					}else{
						retorno.add(rs.getString(i));
					}
				}
			}
			
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	
	
	public String countClientes(){
		String retorno = "0";
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select count(*) from tblUsuarioCliente");
			rs = ps.executeQuery();
			while(rs.next()){
				retorno = rs.getString(1);
			}
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
}
