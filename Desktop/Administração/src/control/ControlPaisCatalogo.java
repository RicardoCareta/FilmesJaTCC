package control;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import DAO.DAOPaisCatalogo;
import Enum.EnumAtivo;
import Enum.EnumPais;
import model.ModelCatalogo;
import model.ModelPais;
import model.ModelPaisCatalogo;

public class ControlPaisCatalogo {
	
	private ModelPaisCatalogo pc;
	private ModelCatalogo c;
	private ModelPais p;
	private DAOPaisCatalogo daoPC;
	
	private List<ModelPaisCatalogo> listaPC;
	
	public ControlPaisCatalogo(){
		daoPC = new DAOPaisCatalogo();
		listaPC = daoPC.Selecionar();
	}
	
	public void Inserir(int IDCat, int IDPais){
		pc = new ModelPaisCatalogo();
		c = new ModelCatalogo();
		p = new ModelPais();
		
		c.setID(IDCat);
		p.setIDPais(IDPais);
		pc.setIdCat(c);
		pc.setIdPais(p);
		pc.setAtivo(1);
		
		daoPC = new DAOPaisCatalogo();
		daoPC.Inserir(pc);
	}
	
	public void Update(int IDCat, int Ativo){
		pc = new ModelPaisCatalogo();
		c = new ModelCatalogo();
		
		c.setID(IDCat);
		pc.setIdCat(c);
		pc.setAtivo(Ativo);
		
		try {
			daoPC = new DAOPaisCatalogo();
			daoPC.Update(pc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DefaultTableModel tblPaisCatalogo(int ID){
		DefaultTableModel retorno = new DefaultTableModel(new Object[][] {}, new String[] {EnumPais.Pais.get()});
		for (ModelPaisCatalogo modelPC : listaPC) {
			if(modelPC.getAtivo() == EnumAtivo.ATIVO.getAtivo() && ID == modelPC.getIdCat().getID()){
				retorno.addRow(new Object[]{modelPC.getIdPais().getNome()});
			}
		}
		return retorno;
	}
}
