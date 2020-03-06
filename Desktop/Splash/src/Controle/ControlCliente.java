package Controle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Constantes.ConsAtivo;
import DAO.DAOCliente;
import Modelo.ModelCliente;

public class ControlCliente {

	// private ModelCliente cl;
	private DAOCliente daoCl;

	private List<ModelCliente> listaCliente;

	private ControlContrato controlContrato;
	
	public ControlCliente() {
		daoCl = new DAOCliente();
		listaCliente = daoCl.Selecionar();
	}

	public boolean cabeMaisUm(int IDCliente){
		controlContrato = new ControlContrato();
		//System.out.println(daoCl.CountLineOn(IDCliente));
		if(controlContrato.getQntTelas(IDCliente) > daoCl.CountLineOn(IDCliente)){
			
			return true;
		}else{
			return false;
		}
	}
	
	public List<ModelCliente> Selecionar() {
		List<ModelCliente> retorno = new ArrayList<ModelCliente>();
		for (ModelCliente modelCliente : listaCliente) {
			if (modelCliente.getAtivo() == ConsAtivo.ATIVO) {
				retorno.add(modelCliente);
			}
		}

		return retorno;
	}

	public boolean Login(String email, char[] senha) {
		for (ModelCliente modelCliente : listaCliente) {

			if (modelCliente.getEmail().equals(email)) {
				if (isCorrectPassword(senha, modelCliente.getSenha())) {
					return true;
				}
			}
		}

		return false;
	}

	public int getID(String email, char[] senha) {
		for (ModelCliente modelCliente : listaCliente) {
			if (modelCliente.getEmail().equals(email)) {
				if (isCorrectPassword(senha, modelCliente.getSenha())) {
					return modelCliente.getID();
				}
			}
		}

		return 0;
	}

	public boolean isCorrectPassword(char[] senha, String correctPassword) {
		char[] charPassword = new char[correctPassword.length()];
		for (int i = 0; i < correctPassword.length(); i++) {
			charPassword[i] = correctPassword.charAt(i);
		}

		if (charPassword.length != senha.length) {
			return false;
		}

		if (Arrays.equals(senha, charPassword)) {
			return true;
		}

		return false;
	}
	
	public int getIDPais(int ID){
		for (ModelCliente modelCliente : listaCliente) {
			if(modelCliente.getID() == ID && modelCliente.getAtivo() == ConsAtivo.ATIVO){
				return modelCliente.getPais().getIdPais();
			}
		}
		
		return 0;
	}    
}
