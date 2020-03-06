package Controle;

import Modelo.ModelCategoria;

import java.util.ArrayList;
import java.util.List;


import Constantes.ConsAtivo;
import DAO.DAOCategoria;

public class ControlCategoria {

	//private ModelCategoria cat;
	private DAOCategoria daoCat;
	private List<ModelCategoria> listaCategoria;
	
	public ControlCategoria(){
		UpdateList();
	}
	
	private void UpdateList(){
		daoCat = new DAOCategoria();
		listaCategoria = daoCat.Selecionar();
	}
	
	public List<ModelCategoria> Selecionar(){
		List<ModelCategoria> retorno = new ArrayList<ModelCategoria>();
		for (ModelCategoria modelCategoria : listaCategoria) {
			if(modelCategoria.getAtivo() == ConsAtivo.ATIVO){
				retorno.add(modelCategoria);
			}
		}
		
		return retorno;
	}
	
	public int getID(String nome){
		for (ModelCategoria modelCategoria : listaCategoria) {
			if(modelCategoria.getAtivo() == ConsAtivo.ATIVO && modelCategoria.getNome().equals(nome)){
				return modelCategoria.getID_Cat();
			}
		}
		
		return 0;
	}
}
