package control;

import model.ModelTituloCatalogo;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import DAO.DAOTituloCatalogo;
import Enum.EnumAtivo;

public class ControlTituloCatalogo {

	private ModelTituloCatalogo tc;
	private DAOTituloCatalogo daoTC;

	private List<ModelTituloCatalogo> listaTC;

	public ControlTituloCatalogo() {
		daoTC = new DAOTituloCatalogo();
		listaTC = daoTC.Selecionar();
	}

	public void Inserir(String Titulo, int IDCat) {
		tc = new ModelTituloCatalogo();
		tc.setTitulo(Titulo);
		tc.setIDCat(IDCat);
		tc.setAtivo(1);
		try {
			daoTC = new DAOTituloCatalogo();
			daoTC.Inserir(tc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Update(int IDCat, int Ativo) {
		tc = new ModelTituloCatalogo();
		tc.setIDCat(IDCat);
		tc.setAtivo(Ativo);
		try {
			daoTC = new DAOTituloCatalogo();
			daoTC.Update(tc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ModelTituloCatalogo> Selecionar(){
		
		return listaTC;
	}
	
	public void UpdateList(){
		daoTC = new DAOTituloCatalogo();
		listaTC = daoTC.Selecionar();
	}
	
	public int getLasID(){
		UpdateList();
		
		int id = 0;
		
		for (ModelTituloCatalogo modelTituloCatalogo : listaTC) {
			if(modelTituloCatalogo.getID() >= id){
				id = modelTituloCatalogo.getID();
			}
		}
		
		return id;
	}
	
	public DefaultTableModel tblTitulo(int ID){
		DefaultTableModel retorno = new DefaultTableModel(new Object[][] {}, new String[] {});
		
		for (ModelTituloCatalogo mTC : listaTC) {
			if(mTC.getIDCat() == ID && mTC.getAtivo() == EnumAtivo.ATIVO.getAtivo()){
				
			}
		}
		
		return retorno;
	}
}
