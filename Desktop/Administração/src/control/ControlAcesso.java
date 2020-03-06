package control;

import model.ModelAcesso;
import model.ModelCatalogo;
import model.ModelPais;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import DAO.DAOAcesso;
import Enum.EnumAcesso;
import Enum.EnumAtivo;

public class ControlAcesso {
	
	private ModelAcesso a;
	private ModelCatalogo c;
	private ModelPais p;
	private DAOAcesso daoA;
	
	private List<ModelAcesso> listaAcesso;
	
	private static final String PAIS = "Pais";
	private static final String ACESSO = "Acesso";
	private static final String REGIAO = "Região";
	
	private static final int ACESSOCOLUMN = 2;
	
	public ControlAcesso(){
		daoA = new DAOAcesso();
		listaAcesso = daoA.Selecionar();
	}
	
	public void Inserir(int IDCat, int IDPais, int Acesso){
		a = new ModelAcesso();
		c = new ModelCatalogo();
		p = new ModelPais();

		c.setID(IDCat);
		p.setIDPais(IDPais);
		a.setIDCat(c);
		a.setIDPais(p);
		a.setAcesso(Acesso);
		a.setAtivo(EnumAtivo.ATIVO.getAtivo());
		
		try {
			daoA = new DAOAcesso();
			daoA.Inserir(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Update(int IDCat, int Ativo){
		a = new ModelAcesso();
		c = new ModelCatalogo();

		c.setID(IDCat);
		a.setIDCat(c);
		a.setAtivo(Ativo);
		
		try {
			daoA = new DAOAcesso();
			daoA.Update(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DefaultTableModel tblAcesso(int ID){
		@SuppressWarnings("serial")
		DefaultTableModel retorno = new DefaultTableModel(new Object[][] {}, new String[]{PAIS, REGIAO, ACESSO}){
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case ACESSOCOLUMN :
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};
		for (ModelAcesso modelAcesso : listaAcesso) {
			if(modelAcesso.getIDCat().getID() == ID && modelAcesso.getAtivo() == EnumAtivo.ATIVO.getAtivo()){
				
				boolean acesso = false;
				if(modelAcesso.getAcesso() == EnumAcesso.Y.get()){
					acesso = true;
				}
				retorno.addRow(new Object[]{modelAcesso.getIDPais().getNome(), modelAcesso.getIDPais().getRegiao(), acesso});
			}
		}
		
		return retorno;
	}
}
