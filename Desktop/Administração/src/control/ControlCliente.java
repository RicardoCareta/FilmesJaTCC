package control;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import DAO.DAOCliente;
import model.ModelCliente;

public class ControlCliente {

	private DAOCliente daoCliente;
	
	private List<ModelCliente> listaCliente;
	
	public ControlCliente(){
		daoCliente = new DAOCliente();
		listaCliente = daoCliente.Selecionar();
	}
	
	public List<String> UsuarioQtd(){
		return daoCliente.SelecionarQnt();
	}
	
	public List<String> UsuarioQtdAno(){
		return daoCliente.SelecionarQtdAno();
	}
	
	public List<String> SelecionarGanhosReais(){
		return daoCliente.SelecionarGanhosReais();
	}
	
	public List<String> SelecionarGanhosEsperados(){
		return daoCliente.SelecionarGanhosEsperados();
	}
	
	public List<String> SelecionarMaisAcessos(){
		return daoCliente.SelecionarMaisAcessos();
	}
	
	public DefaultTableModel tblCliente(){
		DefaultTableModel retorno = new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Email", "Telefone", "CPF", "Pais", "Idioma", "Data do Cadastro", "Ativo"}){
			private static final long serialVersionUID = 1L;

			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 7:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};
		
		for (ModelCliente modelCliente : listaCliente) {
			boolean ativo = false;
			if(modelCliente.getAtivo() == 1){
				ativo = true;
			}
			retorno.addRow(new Object[]{modelCliente.getID(), modelCliente.getEmail(), modelCliente.getTelefone(), modelCliente.getCPF(), modelCliente.getPais().getNome(), modelCliente.getIdioma().getNome(), "08/02/2015", ativo});
		}
		
		return retorno;
	}
	
	public String getClientesCount(){
		return daoCliente.countClientes();
	}
	
}
