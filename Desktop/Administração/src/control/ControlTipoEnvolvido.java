package control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import DAO.DAOTipoEnvolvido;
import Enum.ConsCargo;
import Enum.EnumAtivo;
import model.ModelTipoEnvolvido;

public class ControlTipoEnvolvido {
	
	private DAOTipoEnvolvido daoTipo;
	private ModelTipoEnvolvido tipo;
	
	private List<ModelTipoEnvolvido> listaEnvolvido;
	
	public void Inserir(String texto){
		
		if(texto.trim().equals("")){
			try {
				throw new Exception("Campo nome vazio");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		tipo = new ModelTipoEnvolvido();
		tipo.setTipo(texto);
		try {
			daoTipo = new DAOTipoEnvolvido();
			daoTipo.Inserir(tipo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Update(int ID, String texto, int Ativo){
		
		if(texto.trim().equals("")){
			try {
				throw new Exception("Campo nome vazio");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		tipo = new ModelTipoEnvolvido();
		tipo.setID(ID);
		tipo.setTipo(texto);
		tipo.setAtivo(Ativo);
		try {
			daoTipo = new DAOTipoEnvolvido();
			daoTipo.Update(tipo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		UpdateList();
	}

	public ControlTipoEnvolvido(){
		UpdateList();
	}
	
	private void UpdateList(){
		daoTipo = new DAOTipoEnvolvido();
		listaEnvolvido = daoTipo.Selecionar();
	}
	
	public List<ModelTipoEnvolvido> Selecionar(){
		List<ModelTipoEnvolvido> retorno = new ArrayList<ModelTipoEnvolvido>();
		for (ModelTipoEnvolvido modelTipoEnvolvido : listaEnvolvido) {
			if(modelTipoEnvolvido.getAtivo() == EnumAtivo.ATIVO.getAtivo()){
				retorno.add(modelTipoEnvolvido);
			}
		}
		return retorno;
	}
	
	public int getID(String tipo){
		for (ModelTipoEnvolvido modelTipoEnvolvido : listaEnvolvido) {
			if(tipo.equals(modelTipoEnvolvido.getTipo())){
				return modelTipoEnvolvido.getID();
			}
		}
		
		return 0;
	}
	
	public int getLastID(){
		UpdateList();
		int ID = 0;
		for (ModelTipoEnvolvido modelTipoEnvolvido : listaEnvolvido) {
			if(modelTipoEnvolvido.getID() >= ID){
				ID = modelTipoEnvolvido.getID();
			}
		}
		
		return ID;
	}
	
	public DefaultTableModel tblCargo(){
		@SuppressWarnings("serial")
		DefaultTableModel retorno = new DefaultTableModel(new Object[][] {}, new String[]{ConsCargo.ID, ConsCargo.TIPO, ConsCargo.Ativo}){
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 2:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};
		for (ModelTipoEnvolvido modelTE : listaEnvolvido) {
			boolean ativo = false;
			if(modelTE.getAtivo() == EnumAtivo.ATIVO.getAtivo()){
				ativo = true;
			}
			retorno.addRow(new Object[]{modelTE.getID(), modelTE.getTipo(), ativo});
		}
		
		return retorno;
	}
}
