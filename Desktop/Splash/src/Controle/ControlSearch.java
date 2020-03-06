package Controle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Constantes.ConsAtivo;
import DAO.DAOCatalogo;
import DAO.DAOSearch;
import Modelo.ModelCatalogo;
import Modelo.ModelSearch;

public class ControlSearch {

	private DAOSearch daoSearch;
	private DAOCatalogo daoCatalogo;

	private List<ModelSearch> listSearch;
	private List<ModelCatalogo> listaCatalogo;

	public ControlSearch() {
		daoSearch = new DAOSearch();
		listSearch = daoSearch.Selecionar();

		daoCatalogo = new DAOCatalogo();
		listaCatalogo = daoCatalogo.Selecionar();
	}

	public List<Integer> SelectSearch(String s, int IDIdioma) {
		List<Integer> retorno = new ArrayList<Integer>();
		s = s.toUpperCase().replace(" ", "");
		for (ModelSearch modelSearch : listSearch) {
			if (modelSearch.getTituloIdioma().getIDIdi().getID() == IDIdioma) {
				if (modelSearch.getTituloCatalogo().getTitulo().toUpperCase().indexOf(s) != -1) {
					if (modelSearch.getCatalogo().getNome().toUpperCase().indexOf(s) == -1) {
						retorno.add(modelSearch.getCatalogo().getID());
					}
				}

				if (modelSearch.getTituloCatalogo().getTitulo().toUpperCase().indexOf(s) == -1) {
					if (modelSearch.getCatalogo().getNome().toUpperCase().indexOf(s) != -1) {
						retorno.add(modelSearch.getCatalogo().getID());
					}
				}
			}
			
			if(modelSearch.getCatalogoEnvolvido().getEnvo().getNome().toUpperCase().indexOf(s) != -1){
				retorno.add(modelSearch.getCatalogo().getID());
			}
		}

		List<Integer> newRetorno = new ArrayList<Integer>();

		for (ModelCatalogo modelCatalogo : listaCatalogo) {
			if (modelCatalogo.getAtivo() == ConsAtivo.ATIVO && modelCatalogo.getNome().trim().toUpperCase().indexOf(s) != -1 && modelCatalogo.getIDSerieCatalogo() == 0) {
				if (retorno.isEmpty()) {
					newRetorno.add(modelCatalogo.getID());
				} else {
					for (Integer integer : retorno) {
						// System.out.println(integer.toString());
						if (integer == modelCatalogo.getID()) {
							continue;
						}
						newRetorno.add(integer);
					}
				}
			}
		}
		Set<Integer> rh = new HashSet<>();
		retorno.addAll(newRetorno);
		rh.addAll(retorno);
		retorno.clear();
		retorno.addAll(rh);
		return retorno;
	}
}
