package control;

import model.ModelCategoria;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import DAO.DAOCategoria;
import Enum.EnumAtivo;
import Enum.EnumCategoria;

public class ControlCategoria {

	private ModelCategoria cat;
	private DAOCategoria daoCat;
	private List<ModelCategoria> listaCategoria;
	
	public ControlCategoria(){
		UpdateList();
	}
	
	public void Inserir(String Nome){
		
		if(Nome.trim().equals("")){
			try {
				throw new Exception("Nome vazio");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		cat = new ModelCategoria();
		daoCat = new DAOCategoria();
		
		cat.setNome(Nome);
		daoCat.Inserir(cat);
	}
	
	public void Update(int IDCat, String nome, int Ativo){
		cat = new ModelCategoria();
		cat.setID_Cat(IDCat);
		cat.setNome(nome);
		cat.setAtivo(Ativo);
		
		try {
			daoCat = new DAOCategoria();
			daoCat.Update(cat);
			UpdateList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ModelCategoria> Selecionar(int Ativo){
		
		List<ModelCategoria> retorno = new ArrayList<ModelCategoria>();
		
		for (ModelCategoria modelCategoria : listaCategoria) {
			if(modelCategoria.getAtivo() == Ativo){
				retorno.add(modelCategoria);
			}
		}
		return retorno;
	}
	
	public String getCategoriaNome(int ID){
		for (ModelCategoria modelCategoria : listaCategoria) {
			if(modelCategoria.getID_Cat() == ID){
				return modelCategoria.getNome();
			}
		}
		
		return "";
	}
	
	public int getId(String texto){
		for (ModelCategoria modelCategoria : listaCategoria) {
			if(modelCategoria.getNome().equals(texto)){
				return modelCategoria.getID_Cat();
			}
		}
		return 0;
	}
	
	public DefaultTableModel tblCategoria(){
		@SuppressWarnings("serial")
		DefaultTableModel retorno = new DefaultTableModel(new Object[][] {}, new String[] {EnumCategoria.ID.get(),EnumCategoria.Categoria.get(), EnumCategoria.Ativo.get()}){
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
		
		for (ModelCategoria modelCategoria : listaCategoria) {
			if(modelCategoria.getAtivo() == EnumAtivo.ATIVO.getAtivo()){
				retorno.addRow(new Object[] {modelCategoria.getID_Cat(),modelCategoria.getNome(), true});
			}else if(modelCategoria.getAtivo() == EnumAtivo.DESATIVO.getAtivo()){
				retorno.addRow(new Object[] {modelCategoria.getID_Cat(),modelCategoria.getNome(), false});
			}
			
		}
		
		return retorno;
	}
	
	private void UpdateList(){
		daoCat = new DAOCategoria();
		listaCategoria = daoCat.Selecionar();
	}
	
	public int getLastID(){
		UpdateList();
		int ID = 0;
		for (ModelCategoria modelCategoria : listaCategoria) {
			if(modelCategoria.getID_Cat() >= ID){
				ID = modelCategoria.getID_Cat();
			}
		}
		return ID;
	}
}
