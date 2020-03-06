package control;

import model.ModelIdioma;
import model.ModelTituloCatalogo;
import model.ModelTituloIdioma;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import DAO.DAOTituloIdioma;
import Enum.EnumAtivo;
import Enum.EnumTexto;

public class ControlTituloIdioma {
	private ModelTituloIdioma ti;
	private ModelTituloCatalogo tc;
	private ModelIdioma i;
	
	private DAOTituloIdioma daoTI;
	
	private List<ModelTituloIdioma> listaTi;
	
	public ControlTituloIdioma(){
		daoTI = new DAOTituloIdioma();
		listaTi = daoTI.Selecionar();
	}
	
	public void Inserir(int IDIdi, int IDTc){
		ti = new ModelTituloIdioma();
		tc = new ModelTituloCatalogo();
		i = new ModelIdioma();
		i.setID(IDIdi);

		tc.setID(IDTc);
		
		ti.setIDIdi(i);
		
		ti.setIDTc(tc);
		try {
			daoTI = new DAOTituloIdioma();
			daoTI.Inserir(ti);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ModelTituloIdioma> Selecionar(){
		return listaTi;
	}
	
	public DefaultTableModel tblTitutoIdioma(int ID){
		DefaultTableModel retorno = new DefaultTableModel(new Object[][] {}, new String[] {EnumTexto.Titulo.get(), EnumTexto.Idioma.get()});
		
		for (ModelTituloIdioma mTI : listaTi) {
			if(mTI.getIDTc().getIDCat() == ID && mTI.getAtivo() == EnumAtivo.ATIVO.getAtivo()){
				retorno.addRow(new Object[] {mTI.getIDTc().getTitulo(), mTI.getIDIdi().getNome()});
			}
		}
		
		return retorno;
	}
}
