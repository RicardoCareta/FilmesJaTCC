package control;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import DAO.DAOSinopseCatalogo;
import model.ModelCatalogo;
import model.ModelIdioma;
import model.ModelSinopseCatalogo;
import Enum.ConsSinopse;
import Enum.EnumAtivo;

public class ControlSinopseCatalogo {
	private ModelSinopseCatalogo sc;
	private ModelCatalogo c;
	private ModelIdioma i;
	private DAOSinopseCatalogo daoSc;
	
	
	private List<ModelSinopseCatalogo> listaSC;
	
	public ControlSinopseCatalogo(){
		daoSc = new DAOSinopseCatalogo();
		listaSC = daoSc.Selecionar();
	}
	
	public void Inserir(int IDCat, String Sinopse, int IDIdioma){
		sc = new ModelSinopseCatalogo();
		c = new ModelCatalogo();
		i = new ModelIdioma();
		i.setID(IDIdioma);
		c.setID(IDCat);
		sc.setIDCat(c);
		sc.setSinopse(Sinopse);
		sc.setIDIdioma(i);
		sc.setAtivo(1);
		
		try {
			daoSc = new DAOSinopseCatalogo();
			daoSc.Inserir(sc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Update(int IDCat,int Ativo){
		sc = new ModelSinopseCatalogo();
		c = new ModelCatalogo();
		c.setID(IDCat);
		sc.setIDCat(c);
		sc.setAtivo(Ativo);
		try {
			daoSc = new DAOSinopseCatalogo();
			daoSc.Update(sc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DefaultTableModel tblSinopse(int ID){
		DefaultTableModel retorno = new DefaultTableModel(new Object[][] {}, new String[] {ConsSinopse.SINOPSE, ConsSinopse.IDIOMA});
		for (ModelSinopseCatalogo modelSC : listaSC) {
			if(modelSC.getAtivo() == EnumAtivo.ATIVO.getAtivo() && modelSC.getIDCat().getID() == ID){
				retorno.addRow(new Object[] {modelSC.getSinopse(), modelSC.getIDIdioma().getNome()});
			}
		}
		return retorno;
	}
}
