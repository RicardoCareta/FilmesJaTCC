package Controle;

import Modelo.ModelCatalogo;
import Modelo.ModelClassificacao;
import Modelo.ModelPais;

import java.util.List;

import Constantes.ConsAtivo;
import DAO.DAOClassificacao;

public class ControlClassificacao {

	private DAOClassificacao daoC;
	private ModelClassificacao c;
	private ModelCatalogo cat;
	private ModelPais p;

	private List<ModelClassificacao> listaC;
	
	public ControlClassificacao(){
		daoC = new DAOClassificacao();
		listaC = daoC.Selecionar();
	}
	
	public int getIdade(int IDCat, int IDPais){
		for (ModelClassificacao modelClassificacao : listaC) {
			if(modelClassificacao.getIDCat().getID() == IDCat && modelClassificacao.getAtivo() == ConsAtivo.ATIVO && modelClassificacao.getIDPais().getIdPais() == IDPais){
				return modelClassificacao.getIdade();
			}
		}
		
		return 0;
	}
}
