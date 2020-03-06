package control;
import model.ModelGenero;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import DAO.DAOGenero;
import Enum.EnumAtivo;
import Enum.EnumGenero;

public class ControlGenero {
	
	private ModelGenero genero;
	private DAOGenero daoGenero;
	
	private List<ModelGenero> listaGenero;
	
	public ControlGenero(){
		UpdateList(false, 0);
	}
	
	public void Inserir(String nome){
		try {
			if (nome.trim().equals("")) {
				throw new Exception("Caixa de nome vazio");
			}
			genero = new ModelGenero();
			genero.setNome(nome);
			daoGenero = new DAOGenero();
			daoGenero.Inserir(genero);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Update(int ID, String nome, int Ativo){
		try {
			if (nome.trim().equals("")) {
				throw new Exception("Caixa de nome vazio");
			}
			genero = new ModelGenero();
			genero.setNome(nome);
			genero.setAtivo(Ativo);
			genero.setIdGen(ID);
			daoGenero = new DAOGenero();
			daoGenero.Update(genero);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ModelGenero> Selecionar(){
		UpdateList(true, 1);
		return listaGenero;
	}
	
	public void UpdateList(boolean filtro, int Ativo){
		daoGenero = new DAOGenero();
		List<ModelGenero> retorno = new ArrayList<ModelGenero>();
		if(filtro){
			for (ModelGenero modelGenero : daoGenero.Selecionar()){
				if(modelGenero.getAtivo() == Ativo){
					retorno.add(modelGenero);
				}
			}
			
			listaGenero = retorno;
			
		}else{
			listaGenero = daoGenero.Selecionar();
		}
	}
	
	public int getID(String texto){
		for (ModelGenero modelGenero : listaGenero) {
			if(modelGenero.getNome().equals(texto)){
				return modelGenero.getIdGen();
			}
		}
		
		return 0;
	}
	
	@SuppressWarnings("serial")
	public DefaultTableModel tblGenero(){
		DefaultTableModel retorno = new DefaultTableModel(new Object[][] {}, new String[] {EnumGenero.ID.get(), EnumGenero.Nome.get(), EnumGenero.Ativo.get()}){
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
		for (ModelGenero modelGenero : listaGenero) {
			if(modelGenero.getAtivo() == EnumAtivo.ATIVO.getAtivo()){
				retorno.addRow(new Object[] {modelGenero.getIdGen(), modelGenero.getNome(), true});
			}else{
				retorno.addRow(new Object[] {modelGenero.getIdGen(), modelGenero.getNome(), false});
			}
		}
		
		return retorno;
	}
	
	public int getLastID(){
		UpdateList(false, 0);
		int ID = 0;
		for (ModelGenero modelGenero : listaGenero) {
			if(modelGenero.getIdGen() >= ID){
				ID = modelGenero.getIdGen();
			}
		}
		
		return ID;
	}
}
