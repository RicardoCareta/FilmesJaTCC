package Controle;

import java.util.List;

import Constantes.ConsAtivo;
import DAO.DAOCatalogo;
import DAO.DAOSinopseCatalogo;
import Modelo.ModelCatalogo;
import Modelo.ModelSinopseCatalogo;

public class ControlSinopseCatalogo {
	private DAOCatalogo daoCat;
	private DAOSinopseCatalogo daoSc;
	
	
	private List<ModelSinopseCatalogo> listaSC;
	private List<ModelCatalogo> listaCat;
	
	public ControlSinopseCatalogo(){
		daoSc = new DAOSinopseCatalogo();
		listaSC = daoSc.Selecionar();
		
		daoCat = new DAOCatalogo();
		listaCat = daoCat.Selecionar();
	}
	
	public String getSinopse(int IDCat, int IDIdi){
		for (ModelSinopseCatalogo modelSinopseCatalogo : listaSC) {
			if(modelSinopseCatalogo.getIDCat().getID() == IDCat && modelSinopseCatalogo.getIDIdioma().getID() == IDIdi && modelSinopseCatalogo.getAtivo() == ConsAtivo.ATIVO){
				return modelSinopseCatalogo.getSinopse();
			}
		}
		
		for (ModelCatalogo modelCatalogo : listaCat) {
			if(modelCatalogo.getID() == IDCat){
				return modelCatalogo.getSinopse();
			}
		}
		
		return "Sem sinopse";
	}
}
