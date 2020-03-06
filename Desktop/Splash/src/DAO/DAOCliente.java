package DAO;

import java.util.ArrayList;
import java.util.List;

import Controle.ControlConta;
import Modelo.ModelCliente;
import Modelo.ModelIdioma;
import Modelo.ModelPais;

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
				p.setIdPais(rs.getInt("IDPAIS"));
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
}
