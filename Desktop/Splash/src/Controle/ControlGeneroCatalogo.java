package Controle;

import java.util.List;

import Constantes.ConsAtivo;
import DAO.DAOGeneroCatalogo;
import Modelo.ModelGeneroCatalogo;

public class ControlGeneroCatalogo {
	
	private DAOGeneroCatalogo daoGeneroCatalogo;
	
	private List<ModelGeneroCatalogo> listaGC;
	
	public ControlGeneroCatalogo(){
		daoGeneroCatalogo = new DAOGeneroCatalogo();
		listaGC = daoGeneroCatalogo.SelecionarGC();
	}
	
	public String Generos(int IDCat){
		String retorno = "";
		for (ModelGeneroCatalogo modelGeneroCatalogo : listaGC) {
			if(modelGeneroCatalogo.getAtivo() == ConsAtivo.ATIVO && modelGeneroCatalogo.getIDCat().getID() == IDCat){
				retorno += modelGeneroCatalogo.getIDGen().getNome() + ", ";
			}
		}
		if(retorno.length() > 2){
			retorno = retorno.substring(0, retorno.length() - 2);
		}
		else if(retorno.length() == 0){
			retorno = "Não foram encontrados gêneros";
		}
		
		
		return retorno;
	}
}
