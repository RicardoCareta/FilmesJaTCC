package control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import DAO.DAOPais;
import Enum.EnumAtivo;
import Enum.EnumPais;
import model.ModelGenero;
import model.ModelPais;

public class ControlPais {

	private DAOPais daoPais;
	private ModelPais pais;

	private List<ModelPais> listaPaises;

	public ControlPais() {
		updateList(false, 0);
	}
	
	private void updateList(boolean filtro, int Ativo){
		daoPais = new DAOPais();
		List<ModelPais> retorno = new ArrayList<>();
		
		if(filtro){
			for (ModelPais modelPais : daoPais.SelecionarPais()) {
				if(modelPais.getAtivo() == Ativo){
					retorno.add(modelPais);
				}
			}
			
			listaPaises = retorno;
		}else{
			listaPaises = daoPais.SelecionarPais();
		}
		
		
	}

	public void Inserir(String Nome, String Regiao) {
		if (Nome.trim().equals("")) {
			try {
				throw new Exception("Campo nome está vazio");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		daoPais = new DAOPais();
		pais = new ModelPais();
		pais.setNome(Nome);
		pais.setRegiao(Integer.valueOf(Regiao));
		daoPais.Inserir(pais);
	}
	
	public void Update(int IDPais, String Nome, int Regiao, int Ativo){
		pais = new ModelPais();
		pais.setIDPais(IDPais);
		pais.setNome(Nome);
		pais.setRegiao(Regiao);
		pais.setAtivo(Ativo);
		
		try {
			daoPais = new DAOPais();
			daoPais.Update(pais);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Update(int IDPais, String Nome, String Regiao, int Ativo){
		pais = new ModelPais();
		pais.setIDPais(IDPais);
		pais.setNome(Nome);
		pais.setRegiao(Integer.valueOf(Regiao));
		pais.setAtivo(Ativo);
		
		try {
			daoPais = new DAOPais();
			daoPais.Update(pais);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ModelPais> SelecionarPais(int Regiao) {
		if (Regiao == 0) {
			updateList(true, 1);
			return listaPaises;
		}

		List<ModelPais> listaRetorno = new ArrayList<ModelPais>();
		for (ModelPais modelPais : listaPaises) {
			if (modelPais.getRegiao() == Regiao) {
				listaRetorno.add(modelPais);
			}
		}

		return listaRetorno;
	}

	public int ObterRegiao(String Pais) {
		int Regiao = 0;
		if(Pais.trim().equals("")){
			try {
				throw new Exception("Nome do pais vazio");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		for (ModelPais modelPais : listaPaises) {
			if(modelPais.getNome().equals(Pais)){
				Regiao = modelPais.getRegiao();
			}
		}
		
		return Regiao;
	}

	public int ObterRegiao(int ID) {
		int Regiao = 0;
		if(ID == 0){
			try {
				throw new Exception("ID do pais vazio");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		for (ModelPais modelPais : listaPaises) {
			if(modelPais.getIDPais() == ID){
				Regiao = modelPais.getRegiao();
			}
		}
		
		return Regiao;
	}
	
	public int ObterId(String texto){
		for (ModelPais modelPais : listaPaises) {
			if(modelPais.getNome().equals(texto)){
				return modelPais.getIDPais();
			}
		}
		
		return 0;
	}
	
	public List<String> SelecionarPaisesRegiao(int IDRegiao){
		List<String> retorno = new ArrayList<String>();
		
		for (ModelPais modelPais : listaPaises) {
			if(modelPais.getRegiao() == IDRegiao){
				retorno.add(modelPais.getNome());
			}
		}
		
		return retorno;
	}
	
	public List<String> SelecionarPaisesRegiao(String IDRegiao){
		
		int IDRegiaoS = Integer.parseInt(IDRegiao);
		List<String> retorno = new ArrayList<String>();
		
		for (ModelPais modelPais : listaPaises) {
			if(modelPais.getRegiao() == IDRegiaoS){
				retorno.add(modelPais.getNome());
			}
		}
		
		return retorno;
	}
	
	public List<String> SelecionarPaises(){
		List<String> retorno = new ArrayList<String>();
		for (ModelPais modelPais : listaPaises) {
			retorno.add(modelPais.getNome());
		}
		
		return retorno;
	}
	
	public List<String> SelecionarPaises(int status){
		List<String> retorno = new ArrayList<String>();
		for (ModelPais modelPais : listaPaises) {
			if(modelPais.getAtivo() == status){
				retorno.add(modelPais.getNome());	
			}
		}
		
		return retorno;
	}
	
	public DefaultTableModel tblPais(){
		@SuppressWarnings("serial")
		DefaultTableModel retorno = new DefaultTableModel(new Object[][] {}, new String[] {EnumPais.ID.get(), EnumPais.Pais.get(), EnumPais.Regiao.get(), EnumPais.Ativo.get()}){
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};
		
		for (ModelPais modelPais : listaPaises) {
			if(modelPais.getAtivo() == EnumAtivo.ATIVO.getAtivo()){
				retorno.addRow(new Object[] {modelPais.getIDPais(), modelPais.getNome(), modelPais.getRegiao(), true});
			}else{
				retorno.addRow(new Object[] {modelPais.getIDPais(), modelPais.getNome(), modelPais.getRegiao(), false});
			}
			
			
		}
		
		return retorno;
	}
	
	public int getLastID(){
		updateList(false, 0);
		int ID = 0;
		for (ModelPais modelPais : listaPaises) {
			if(modelPais.getIDPais() >= ID){
				ID = modelPais.getIDPais();
			}
		}
		
		return ID;
	}
}
