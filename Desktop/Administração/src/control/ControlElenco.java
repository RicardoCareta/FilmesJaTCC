package control;

import model.ModelElenco;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import DAO.DAOElenco;
import Enum.EnumAtivo;
import Enum.EnumElenco;

public class ControlElenco {
	
	DAOElenco daoElenco;
	ModelElenco elenco;
	
	private List<ModelElenco> listaElenco;
	
	public ControlElenco(){
		UpdateList();
	}
	
	private void UpdateList(){
		daoElenco = new DAOElenco();
		listaElenco = daoElenco.Selecionar();
	}
	
	public void Inserir(String Nome){
		if(Nome.trim().equals("")){
			try {
				throw new Exception("Campo nome vazio");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		daoElenco = new DAOElenco();
		elenco = new ModelElenco();
		elenco.setNome(Nome);
		daoElenco.Inserir(elenco);
	}
	
	public void Update(int IDElenco, String nome, int Ativo){
		elenco = new ModelElenco();
		elenco.setID_Envo(IDElenco);
		elenco.setNome(nome);
		elenco.setAtivo(Ativo);
		
		try {
			daoElenco = new DAOElenco();
			daoElenco.Update(elenco);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ModelElenco> Selecionar(){
		return listaElenco;
	}
	
	public List<String> getListLimit(String texto){
		List<String> selecoes = new ArrayList<String>();
		int i = 0;
		for (ModelElenco modelElenco : listaElenco) {
			if(i >= 3){
				break;
			}
			if(modelElenco.getNome().startsWith(texto) && modelElenco.getAtivo() == EnumAtivo.ATIVO.getAtivo()){
				selecoes.add(modelElenco.getNome());
				i++;
			}
			
		}
		
		return selecoes;
	}
	
	public boolean findElenco(String texto){
		for (ModelElenco modelElenco : listaElenco) {
			if(texto.equals(modelElenco.getNome())){
				return true;
			}
		}
		
		return false;
	}
	
	public int getID(String nome){
		for (ModelElenco modelElenco : listaElenco) {
			if(modelElenco.getNome().equals(nome)){
				return modelElenco.getID_Envo();
			}
		}
		
		return 0;
	}
	
	@SuppressWarnings("serial")
	public DefaultTableModel tblElenco(){
		DefaultTableModel retorno = new DefaultTableModel(new Object[][] {}, new String[] {EnumElenco.ID.get(), EnumElenco.Nome.get(), EnumElenco.Ativo.get()}){
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};
		
		for (ModelElenco modelElenco : listaElenco) {
			if(modelElenco.getAtivo() == EnumAtivo.ATIVO.getAtivo()){
				retorno.addRow(new Object[] {modelElenco.getID_Envo(), modelElenco.getNome(), true});
			}else{
				retorno.addRow(new Object[] {modelElenco.getID_Envo(), modelElenco.getNome(), false});
			}
		}
		
		return retorno;
	}

	public int getLastID(){
		UpdateList();
		int ID = 0;
		for (ModelElenco modelElenco : listaElenco) {
			if(modelElenco.getID_Envo() >= ID){
				ID = modelElenco.getID_Envo();
			}
		}
		
		return ID;
	}
}
