package control;

import model.ModelCatalogo;
import model.ModelCatalogoEnvolvidos;
import model.ModelElenco;
import model.ModelTipoEnvolvido;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import DAO.DAOCatalogoEnvolvidos;
import Enum.ConsCE;
import Enum.EnumAtivo;

public class ControlCatalogoEnvolvidos {

	private ModelCatalogoEnvolvidos ce;
	private ModelCatalogo c;
	private ModelTipoEnvolvido te;
	private ModelElenco e;
	
	private DAOCatalogoEnvolvidos daoCE;
	
	private List<ModelCatalogoEnvolvidos> listaCE;
	
	public ControlCatalogoEnvolvidos(){
		daoCE = new DAOCatalogoEnvolvidos();
		listaCE = daoCE.Selecionar();
	}
	
	public void Inserir(int IDCat, int IDEnvolvido, int IDTipoEnvolvido){
		ce = new ModelCatalogoEnvolvidos();
		c = new ModelCatalogo();
		te = new ModelTipoEnvolvido();
		e = new ModelElenco();
		c.setID(IDCat);
		te.setID(IDTipoEnvolvido);
		e.setID_Envo(IDEnvolvido);
		ce.setIDCat(c);
		ce.setIDEnvolvido(e);
		ce.setIDTipoEnvolvido(te);
		ce.setAtivo(EnumAtivo.ATIVO.getAtivo());
		try {
			daoCE = new DAOCatalogoEnvolvidos();
			daoCE.Inserir(ce);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Update(int IDCat, int Ativo){
		ce = new ModelCatalogoEnvolvidos();
		c = new ModelCatalogo();
		c.setID(IDCat);
		ce.setIDCat(c);
		ce.setAtivo(Ativo);
		try {
			daoCE = new DAOCatalogoEnvolvidos();
			daoCE.Update(ce);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DefaultTableModel tblCE(int ID){
		DefaultTableModel retorno = new DefaultTableModel(new Object[][] {}, new String[] {ConsCE.NOME, ConsCE.FUNCAO});
		for (ModelCatalogoEnvolvidos modelCE : listaCE) {
			if(modelCE.getIDCat().getID() == ID && modelCE.getAtivo() == EnumAtivo.ATIVO.getAtivo()){
				retorno.addRow(new Object[] {modelCE.getIDEnvolvido().getNome(), modelCE.getIDTipoEnvolvido().getTipo()});
			}
		}
		
		return retorno;
	}
}
