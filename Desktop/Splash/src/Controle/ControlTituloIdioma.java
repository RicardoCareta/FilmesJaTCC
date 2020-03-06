package Controle;

import Modelo.ModelCatalogo;
import Modelo.ModelTituloIdioma;

import java.util.List;

import DAO.DAOCatalogo;
import DAO.DAOTituloIdioma;

public class ControlTituloIdioma {
	private DAOTituloIdioma daoTI;
	private DAOCatalogo daoCatalogo;
	
	private List<ModelTituloIdioma> listaTi;
	private List<ModelCatalogo> listaCat;
	
	public ControlTituloIdioma(){
		daoTI = new DAOTituloIdioma();
		listaTi = daoTI.Selecionar();
		daoCatalogo = new DAOCatalogo();
		listaCat = daoCatalogo.Selecionar();
	}
	
	public String getTitle(int IDCat, int IDIdi){
		for (ModelTituloIdioma modelTituloIdioma : listaTi) {
			if(modelTituloIdioma.getIDIdi().getID() == IDIdi && modelTituloIdioma.getIDTc().getIDCat() == IDCat){
				return modelTituloIdioma.getIDTc().getTitulo();
			}
		}
		
		for (ModelCatalogo modelCatalogo : listaCat) {
			if(modelCatalogo.getID() == IDCat){
				return modelCatalogo.getNome();
			}
		}
		
		return "Sem Nome";
	}

	
	public List<ModelTituloIdioma> Selecionar(){
		return listaTi;
	}
}
