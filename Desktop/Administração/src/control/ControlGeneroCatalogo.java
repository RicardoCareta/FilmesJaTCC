package control;

import model.ModelCatalogo;
import model.ModelGenero;
import model.ModelGeneroCatalogo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import DAO.DAOGeneroCatalogo;
import Enum.EnumAtivo;
import Enum.EnumGenero;
import Enum.TiposID;

public class ControlGeneroCatalogo {
	
	private ModelGeneroCatalogo gc;
	private ModelGenero g;
	private ModelCatalogo c;
	private DAOGeneroCatalogo daoGC;
	
	private List<ModelGeneroCatalogo> listaGC;
	
	public ControlGeneroCatalogo(){
		daoGC = new DAOGeneroCatalogo();
		listaGC = daoGC.Selecionar();
	}
	
	public void Inserir(int idGen, int idCat){
		c = new ModelCatalogo();
		c.setID(idCat);
		g = new ModelGenero();
		g.setIdGen(idGen);
		gc = new ModelGeneroCatalogo(c, g, 1);
		daoGC = new DAOGeneroCatalogo();
		daoGC.Inserir(gc);
	}
	
	public void Update(int idCat, int Ativo){
		c = new ModelCatalogo();
		c.setID(idCat);
		gc = new ModelGeneroCatalogo(c, Ativo);
		daoGC = new DAOGeneroCatalogo();
		daoGC.Update(gc);
	}
	
	public List<ModelGeneroCatalogo> Selecionar(){
		List<ModelGeneroCatalogo> retorno = new ArrayList<ModelGeneroCatalogo>();
		for (ModelGeneroCatalogo modelGeneroCatalogo : listaGC) {
			if(modelGeneroCatalogo.getAtivo() == 1){
				retorno.add(modelGeneroCatalogo);
			}
		}
		
		return retorno;
	}
	
	public List<ModelGeneroCatalogo> Selecionar(int ID, int Tipo){
		if(Tipo == TiposID.IDCATALOGO){
			return SelecionarIDCat(ID);
		}
		if(Tipo == TiposID.IDGENERO){
			return SelecionarIDGenero(ID);
		}
		else{
			return new ArrayList<ModelGeneroCatalogo>();
		}
	}
	
	private List<ModelGeneroCatalogo> SelecionarIDCat(int ID){
		List<ModelGeneroCatalogo> retorno = new ArrayList<ModelGeneroCatalogo>();
		
		for (ModelGeneroCatalogo gc : listaGC) {
			if(gc.getIDCat().getID() == ID){
				retorno.add(gc);
			}
		}
		
		return retorno;
	}
	
	private List<ModelGeneroCatalogo> SelecionarIDGenero(int ID){
		List<ModelGeneroCatalogo> retorno = new ArrayList<ModelGeneroCatalogo>();
		
		for (ModelGeneroCatalogo gc : listaGC) {
			if(gc.getIDGen().getIdGen() == ID){
				retorno.add(gc);
			}
		}
		
		return retorno;
	}
	
	public DefaultTableModel tblGeneroCatalogo(int ID){
		DefaultTableModel retorno = new DefaultTableModel(new Object[][] {}, new String[] {EnumGenero.Nome.get()});
		for (ModelGeneroCatalogo modelGeneroCatalogo : listaGC) {
			if(modelGeneroCatalogo.getIDCat().getID() == ID && modelGeneroCatalogo.getAtivo() == EnumAtivo.ATIVO.getAtivo()){
				retorno.addRow(new Object[] {modelGeneroCatalogo.getIDGen().getNome()});
			}
		}
		
		return retorno;
	}
}
