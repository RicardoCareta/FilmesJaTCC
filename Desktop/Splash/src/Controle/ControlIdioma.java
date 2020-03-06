package Controle;

import java.util.ArrayList;
import java.util.List;

import Constantes.ConsAtivo;
import DAO.DAOIdioma;
import Modelo.ModelIdioma;

public class ControlIdioma {

	private DAOIdioma daoIdioma;
	
	private List<ModelIdioma> listaIdioma;
	
	public ControlIdioma(){
		daoIdioma = new DAOIdioma();
		listaIdioma = daoIdioma.Selecionar();
	}
	
	public List<ModelIdioma> Selecionar(){
		List<ModelIdioma> retorno = new ArrayList<ModelIdioma>();
		for (ModelIdioma modelIdioma : listaIdioma) {
			if(modelIdioma.getAtivo() == ConsAtivo.ATIVO){
				retorno.add(modelIdioma);
			}
		}
		
		return retorno;
	}
	
	public int getID(String nome){
		for (ModelIdioma modelIdioma : listaIdioma) {
			if(modelIdioma.getNome().equals(nome)){
				return modelIdioma.getID();
			}
		}
		
		return 0;
	}
	
	public String getNome(int ID){
		for (ModelIdioma modelIdioma : listaIdioma) {
			if(modelIdioma.getID() == ID){
				return modelIdioma.getNome();
			}
		}
		
		return "";
	}
	
}
