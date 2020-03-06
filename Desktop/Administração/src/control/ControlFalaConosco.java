package control;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import DAO.DAOFaleConosco;
import model.MDFaleConosco;

public class ControlFalaConosco {

	private DAOFaleConosco daoFaleConosco;
	
	private List<MDFaleConosco> lista;
	
	public ControlFalaConosco(){
		daoFaleConosco = new DAOFaleConosco();
		lista = daoFaleConosco.Selecionar();
	}
	
	public DefaultTableModel tblFale(){
		DefaultTableModel retorno =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Nome", "Email", "Data", "Mensagem"
				}
			);
		
		for (MDFaleConosco mdFaleConosco : lista) {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			String dataFormatada = formato.format(mdFaleConosco.getData());
			retorno.addRow(new Object[] {mdFaleConosco.getNome(), mdFaleConosco.getEmail(), dataFormatada, mdFaleConosco.getMensagem()});
		}
		
		return retorno;
	}
	
	public DefaultTableModel tblFaleNome(String nome){
		DefaultTableModel retorno =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Nome", "Email", "Data", "Mensagem"
				}
			);
		
		for (MDFaleConosco mdFaleConosco : lista) {
			if(mdFaleConosco.getNome().indexOf(nome) != -1){
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				String dataFormatada = formato.format(mdFaleConosco.getData());
				retorno.addRow(new Object[] {mdFaleConosco.getNome(), mdFaleConosco.getEmail(), dataFormatada, mdFaleConosco.getMensagem()});
			}
		}
		
		return retorno;
	}
	
	public DefaultTableModel tblFaleData(String data){
		DefaultTableModel retorno =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Nome", "Email", "Data", "Mensagem"
				}
			);
		
		for (MDFaleConosco mdFaleConosco : lista) {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			String dataFormatada = formato.format(mdFaleConosco.getData());
			if(dataFormatada.replace("/", "").trim().indexOf(data) != -1){
				retorno.addRow(new Object[] {mdFaleConosco.getNome(), mdFaleConosco.getEmail(), dataFormatada, mdFaleConosco.getMensagem()});
			}
		}
		
		return retorno;
	}
	
	public DefaultTableModel tblFaleEmail(String email){
		DefaultTableModel retorno =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Nome", "Email", "Data", "Mensagem"
				}
			);
		
		for (MDFaleConosco mdFaleConosco : lista) {
			if(mdFaleConosco.getEmail().indexOf(email) != -1){
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				String dataFormatada = formato.format(mdFaleConosco.getData());
				retorno.addRow(new Object[] {mdFaleConosco.getNome(), mdFaleConosco.getEmail(),dataFormatada, mdFaleConosco.getMensagem()});
			}
		}
		
		return retorno;
	}
	
}
