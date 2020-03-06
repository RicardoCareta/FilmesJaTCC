package control;

import model.ModelIdioma;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import DAO.DAOIdioma;
import Enum.EnumAtivo;

public class ControlIdioma {

	private ModelIdioma idioma;
	private DAOIdioma daoIdioma;

	private List<ModelIdioma> listaIdioma;

	private static final String ID = "ID";
	private static final String IDIOMA = "Idioma";
	private static final String ATIVO = "Ativo";

	public ControlIdioma() {
		UpdateList();
	}

	private void UpdateList() {
		daoIdioma = new DAOIdioma();
		listaIdioma = daoIdioma.Selecionar();
	}

	public List<ModelIdioma> Selecionar() {
		List<ModelIdioma> retorno = new ArrayList<ModelIdioma>();
		for (ModelIdioma modelIdioma : listaIdioma) {
			if (modelIdioma.getAtivo() == EnumAtivo.ATIVO.getAtivo()) {
			
				retorno.add(modelIdioma);
			}
		}
		return retorno;
	}

	public void Inserir(String texto) {

		if (texto.trim().equals("")) {
			try {
				throw new Exception("Campo vazio");
			} catch (Exception e) {

			}
		}
		try {
			daoIdioma = new DAOIdioma();
			idioma = new ModelIdioma();
			idioma.setNome(texto);
			idioma.setAtivo(1);
			daoIdioma.Inserir(idioma);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Update(int ID,String texto, int Ativo) {

		if (texto.trim().equals("")) {
			try {
				throw new Exception("Campo vazio");
			} catch (Exception e) {

			}
		}
		try {
			daoIdioma = new DAOIdioma();
			idioma = new ModelIdioma();
			idioma.setAtivo(Ativo);
			idioma.setNome(texto);
			idioma.setID(ID);
			daoIdioma.Update(idioma);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getId(String Idioma) {
		for (ModelIdioma modelIdioma : listaIdioma) {
			if (Idioma.equals(modelIdioma.getNome())) {
				return modelIdioma.getID();
			}
		}

		return 0;
	}
	
	public String getNome(int id){
		UpdateList();
		for (ModelIdioma modelIdioma : listaIdioma) {
			System.out.println(id);
			if(modelIdioma.getID() == id){
				return modelIdioma.getNome();
			}
		}
		
		return "";
	}

	public int getLastID() {
		int id = 0;
		UpdateList();
		for (ModelIdioma modelIdioma : listaIdioma) {
			if (modelIdioma.getID() >= id) {
				id = modelIdioma.getID();
			}
		}

		return id;
	}

	public DefaultTableModel tblIdioma() {
		@SuppressWarnings("serial")
		DefaultTableModel retorno = new DefaultTableModel(new Object[][] {}, new String[] { ID, IDIOMA, ATIVO }) {
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 2:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};

		for (ModelIdioma modelIdioma : listaIdioma) {
			boolean ativo = false;
			if (modelIdioma.getAtivo() == EnumAtivo.ATIVO.getAtivo()) {
				ativo = true;
			}

			retorno.addRow(new Object[] { modelIdioma.getID(), modelIdioma.getNome(), ativo });
		}

		return retorno;
	}
}
