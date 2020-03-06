package control;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import DAO.DAOAtalhosLocal;
import model.MDAtalhoLocal;

public class ControlAtalhosLocal {
	private List<MDAtalhoLocal> lista;
	
	private DAOAtalhosLocal daoAtalhosLocal;
	
	public ControlAtalhosLocal(){
		daoAtalhosLocal = new DAOAtalhosLocal();
		lista = Selecionar();
	}
	
	
	public List<MDAtalhoLocal> Selecionar(){
		return daoAtalhosLocal.Selecionar();
	}
	
	public void Atualizar(String atalho, int status, int ID){
		MDAtalhoLocal mdAtalhoLocal = new MDAtalhoLocal();
		mdAtalhoLocal.setAtalho(atalho);
		mdAtalhoLocal.setStatus(status);
		mdAtalhoLocal.setID(ID);
		daoAtalhosLocal.Atualizar(mdAtalhoLocal);
	}
	
	
	
	public DefaultTableModel tblAtalhos(){
		@SuppressWarnings("serial")
		DefaultTableModel retorno = new DefaultTableModel(new Object[][] {}, new String[] {
				"ID", "Configuração", "Atalho", "Ativo"
			}){
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 3:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};
		
		for (MDAtalhoLocal mdAtalhoLocal : lista) {
			boolean ativo = false;
			if(mdAtalhoLocal.getStatus() == 1){
				ativo = true;
			}
			retorno.addRow(new Object[] {mdAtalhoLocal.getID(), mdAtalhoLocal.getConfig(), mdAtalhoLocal.getAtalho(), ativo});
		}		
		return retorno;
	}
}
