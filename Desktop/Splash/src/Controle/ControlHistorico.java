package Controle;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Constantes.ConsAssistido;
import Constantes.ConsAtivo;
import DAO.DAOGeneroCatalogo;
import DAO.DAOHistorico;
import Modelo.ModelGeneroCatalogo;
import Modelo.ModelHistorico;
import Modelo.ModelTop;
import Modelo.ModelTopGeneros;

public class ControlHistorico {
	
	private DAOHistorico daoHistorico;
	private DAOGeneroCatalogo daoGeneroCatalogo;
	
	private ModelHistorico historico;
	private ModelTop top;
	private ModelTopGeneros topGeneros;
	
	private List<ModelHistorico> listaHistorico;
	private List<ModelTop> listaTop;
	private List<ModelTopGeneros> listaTopGeneros;
	private List<ModelGeneroCatalogo> listaGeneroCatalogo;
	
	public ControlHistorico(){
		daoHistorico = new DAOHistorico();
		listaHistorico = daoHistorico.Selecionar();
		
		daoGeneroCatalogo = new DAOGeneroCatalogo();
		listaGeneroCatalogo = daoGeneroCatalogo.SelecionarGC();
	}
	
	public int getTime(int IDCat, int IDConta){
		
		for (ModelHistorico modelHistorico : listaHistorico) {
			if(modelHistorico.getIDCat() == IDCat && modelHistorico.getIDConta() == IDConta && modelHistorico.getAssistido() == ConsAssistido.ASSISTIDO){
				return modelHistorico.getTempo();
			}
		}
		
		return 1;
	}
	
	public void ChangeAssistido(int ID, int assistido){
		daoHistorico.ChangeAssistido(ID, assistido);
	}
	public void Inserir ( int Avaliacao, int Assistido, int Id_Cat, int Id_Conta, Date Data, int Tempo){

		historico = new ModelHistorico();
		
		for (ModelHistorico modelHistorico : listaHistorico) {
			if(modelHistorico.getIDCat() == Id_Cat && modelHistorico.getIDConta() == Id_Conta &&  modelHistorico.getAssistido() == ConsAssistido.ASSISTIDO){
				ChangeAssistido(modelHistorico.getID(), ConsAssistido.IGNORADO);
			}
		}
		
		historico.setAvaliacao(Avaliacao);
		historico.setAssistido(Assistido);
		historico.setIDCat(Id_Cat);
		historico.setIDConta(Id_Conta);
		historico.setData(Data);
		historico.setTempo(Tempo);
		
		daoHistorico = new DAOHistorico();
		
		daoHistorico.Inserir(historico);
	}
	
	public List<ModelHistorico> SelecionarAssistido(){
		List<ModelHistorico> retorno = new ArrayList<ModelHistorico>();
		for (ModelHistorico modelHistorico : listaHistorico) {
			if(modelHistorico.getAssistido() == ConsAssistido.ASSISTIDO){
				retorno.add(modelHistorico);
			}
				
		}
		return retorno;
	}
	
	public List<ModelTop> getTopMidia(){
		listaTop = new ArrayList<ModelTop>();
		for (ModelHistorico modelHistorico : listaHistorico) {
			boolean recive = false;
			for (ModelTop modelTop : listaTop) {
				if(modelTop.getIDCat() == modelHistorico.getIDCat() && modelHistorico.getAssistido() == ConsAssistido.ASSISTIDO){
					modelTop.setViewCount(modelTop.getViewCount() + 1);
					recive = true;
					break;
				}
			}
			if(!recive){
				top = new ModelTop();
				top.setIDCat(modelHistorico.getIDCat());
				top.setViewCount(1);
				listaTop.add(top);
			}
		}
		
		Collections.sort(listaTop, new Comparator<ModelTop>() {

			@Override
			public int compare(ModelTop o1, ModelTop o2) {
				return o2.getViewCount() - o1.getViewCount();
			}
			
		});
		
		return listaTop;
	}
	
	public List<ModelTopGeneros> getTopGender(){
		listaTopGeneros = new ArrayList<ModelTopGeneros>();
		
		for (ModelHistorico mH : listaHistorico) {
			for (ModelGeneroCatalogo mGC : listaGeneroCatalogo) {
				if( 
					mH.getIDCat() == mGC.getIDCat().getID() && 
				    mH.getAssistido() == ConsAssistido.ASSISTIDO && 
					mGC.getAtivo() == ConsAtivo.ATIVO && 
					mGC.getIDCat().getAtivo() == ConsAtivo.ATIVO && 
					mGC.getIDGen().getAtivo() == ConsAtivo.ATIVO
					){
					
					boolean add = true;
					for (ModelTopGeneros modelTopGeneros : listaTopGeneros) {
						if(modelTopGeneros.getGenero().getIdGen() == mGC.getIDGen().getIdGen()){
							modelTopGeneros.setCountView(modelTopGeneros.getCountView() + 1);
							add = false;
						}
					}
					if(add){
						topGeneros = new ModelTopGeneros();
						topGeneros.setIDCat(mGC.getIDCat().getID());
						//System.out.println(mGC.getIDCat().getID());
						topGeneros.setGenero(mGC.getIDGen());
						topGeneros.setCountView(1);
						listaTopGeneros.add(topGeneros);
					}
				}
			}
		}
		
		Collections.sort(listaTopGeneros, new Comparator<ModelTopGeneros>() {

			@Override
			public int compare(ModelTopGeneros o1, ModelTopGeneros o2) {
				return o2.getCountView() - o1.getCountView();
			}
		});	
		
		return listaTopGeneros;
	}

	public List<Integer> getTopTG(int IDGen){
		List<Integer> retorno = new ArrayList<Integer>();
		
		for (ModelTop modelTop : getTopMidia()) {
			for (ModelGeneroCatalogo modelGeneroCatalogo : listaGeneroCatalogo) {
				if(modelGeneroCatalogo.getIDGen().getIdGen() == IDGen){
					if(modelTop.getIDCat() == modelGeneroCatalogo.getIDCat().getID()){
						boolean ff = true;
						for (Integer integer : retorno) {
							if(integer == modelTop.getIDCat()){
								ff = false;
							}
						}
						if(ff){
							retorno.add(modelTop.getIDCat());
						}
					}
				}
			}
		}
		
		return retorno;
	}
	
	public Date getDataBD(){
		return daoHistorico.DataBD();
	}
	
	public void AtualizarAvaliacao(int IDConta, int Avaliacao, int IDCat){
		boolean find = false;
		int id = 0;
		
		daoHistorico = new DAOHistorico();
		
		for (ModelHistorico modelHistorico : listaHistorico) {
			if(modelHistorico.getIDConta() == IDConta && modelHistorico.getAssistido() == ConsAssistido.DESASSISTIDO && modelHistorico.getIDCat() == IDCat){
				find = true;
				id = modelHistorico.getID();
			}
		}
		
		if(!find){
			Inserir(Avaliacao, ConsAssistido.DESASSISTIDO, IDCat, IDConta, null, 0);
		}
		else{
			daoHistorico.UpdateNota(Avaliacao, id);
		}
	}
	
	public int getAvaliacao(int IDConta, int IDCat){
		for (ModelHistorico modelHistorico : listaHistorico) {
			if(modelHistorico.getIDCat() == IDCat && modelHistorico.getAssistido() == ConsAssistido.DESASSISTIDO && modelHistorico.getIDConta() == IDConta){
				return modelHistorico.getAvaliacao();
			}
		}
		
		return 0;
	}
}
