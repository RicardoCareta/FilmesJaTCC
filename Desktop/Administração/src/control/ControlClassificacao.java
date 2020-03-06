package control;

import model.ModelCatalogo;
import model.ModelClassificacao;
import model.ModelPais;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import DAO.DAOClassificacao;
import Enum.ConsClassificacao;
import Enum.EnumAtivo;

public class ControlClassificacao {

	private DAOClassificacao daoC;
	private ModelClassificacao c;
	private ModelCatalogo cat;
	private ModelPais p;

	private List<ModelClassificacao> listaC;
	
	public ControlClassificacao(){
		daoC = new DAOClassificacao();
		listaC = daoC.Selecionar();
	}
	
	public void Inserir(int iDCat, int iDPais, int idade){
		c = new ModelClassificacao();
		cat = new ModelCatalogo();
		p = new ModelPais();
		cat.setID(iDCat);
		p.setIDPais(iDPais);
		c.setIDCat(cat);
		c.setIDPais(p);
		c.setIdade(idade);
		c.setAtivo(1);
		try {
			daoC = new DAOClassificacao();
			daoC.Inserir(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Update(int iDCat, int Ativo){
		c = new ModelClassificacao();
		cat = new ModelCatalogo();
		cat.setID(iDCat);
		c.setIDCat(cat);
		c.setAtivo(Ativo);
		try {
			daoC = new DAOClassificacao();
			daoC.Update(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DefaultTableModel tblClassificacao(int ID){
		DefaultTableModel retorno = new DefaultTableModel(new Object[][] {}, new String[] {ConsClassificacao.IDADE, ConsClassificacao.PAIS});
		
		for (ModelClassificacao modelC : listaC) {
			if(modelC.getAtivo() == EnumAtivo.ATIVO.getAtivo() && modelC.getIDCat().getID() == ID){
				retorno.addRow(new Object[] {modelC.getIdade(), modelC.getIDPais().getNome()});
			}
		}
		
		return retorno;
	}
}
