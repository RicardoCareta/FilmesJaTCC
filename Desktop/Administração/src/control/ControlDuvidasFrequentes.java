package control;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import DAO.DAODuvidasFrequentes;
import model.MDDuvidasFrequentes;

public class ControlDuvidasFrequentes {

	private DAODuvidasFrequentes daoDuvidasFrequentes;
	private MDDuvidasFrequentes mdDuvidasFrequentes;
	
	private List<MDDuvidasFrequentes> lista;
	
	public ControlDuvidasFrequentes(){
		lista = Selecionar();
	}
	
	public void Inserir(String duvida, String resposta){
		mdDuvidasFrequentes = new MDDuvidasFrequentes();
		
		mdDuvidasFrequentes.setDUVIDA(duvida);
		mdDuvidasFrequentes.setRESPOSTA(resposta);
		
		daoDuvidasFrequentes = new DAODuvidasFrequentes();
		daoDuvidasFrequentes.Inserir(mdDuvidasFrequentes);
	}
	
	public void Atualizar(int ID, String duvida, String resposta, boolean status){
		mdDuvidasFrequentes = new MDDuvidasFrequentes();
		int ativo = 0;
		if(status){
			ativo = 1;
		}
		
		mdDuvidasFrequentes.setDUVIDA(duvida);
		mdDuvidasFrequentes.setRESPOSTA(resposta);
		mdDuvidasFrequentes.setATIVO(ativo);
		mdDuvidasFrequentes.setID_DUVIDA(ID);
		
		daoDuvidasFrequentes = new DAODuvidasFrequentes();
		daoDuvidasFrequentes.Atualizar(mdDuvidasFrequentes);
	}
	
	public List<MDDuvidasFrequentes> Selecionar(){
		daoDuvidasFrequentes = new DAODuvidasFrequentes();
		return daoDuvidasFrequentes.Selecionar();
		
	}
	
	public DefaultTableModel tblDuvidas(){
		DefaultTableModel retorno = new DefaultTableModel(new Object[][] {}, new String[] {"ID", "Duvida", "Resposta", "Status"}){
			private static final long serialVersionUID = 1L;

			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 3:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};
		
		
		for (MDDuvidasFrequentes mdDuvidasFrequentes : lista) {
			boolean ativo = true;
			
			if(mdDuvidasFrequentes.getATIVO() == 0){
				ativo = false;
			}
			
			retorno.addRow(new Object[] {mdDuvidasFrequentes.getID_DUVIDA(), mdDuvidasFrequentes.getDUVIDA(), mdDuvidasFrequentes.getRESPOSTA(), ativo});
		}
		
		return retorno;
	}
	
}
