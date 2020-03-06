package Controle;

import java.net.URL;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import Constantes.ConsAtivo;
import DAO.DAOCatalogo;
import DAO.DAOTituloIdioma;
import Modelo.ModelCatalogo;
import Modelo.ModelTituloIdioma;
import Visão.JCatalogoView;

public class ControlCatalogo {

	private DAOCatalogo daoCatalogo;
	private DAOTituloIdioma daoTituloIdioma;
	
	private List<ModelCatalogo> listaCatalogo;
	private List<ModelTituloIdioma> listaTituloIdioma;

	public ControlCatalogo() {
		daoCatalogo = new DAOCatalogo();
		listaCatalogo = daoCatalogo.Selecionar();
		
		daoTituloIdioma = new DAOTituloIdioma();
		listaTituloIdioma = daoTituloIdioma.Selecionar();
	}
	private static final URL pathImagemNull = JCatalogoView.class.getResource("/Vis\u00E3o/imagens/imagemNull.jpg");

	private static final String ID = "";
	private static final String N_EP = "Nº Episódio";
	private static final String NO_EP = "Nome Episódio";
	private static final String TEMPORADA = "Temporada";
	
	public ImageIcon getImage(int ID) {
		for (ModelCatalogo modelCatalogo : listaCatalogo) {
			if (modelCatalogo.getID() == ID) {
				if (modelCatalogo.getImageBytes() != null) {
				//	System.out.println("not null");
					return modelCatalogo.getImageIcon();
				} else {
					//System.out.println("null");
					return new ImageIcon(pathImagemNull);
				}
			}
		}

		return new ImageIcon(pathImagemNull);
	}
	
	public boolean isSerie(int ID){
		for (ModelCatalogo modelCatalogo : listaCatalogo) {
			if(modelCatalogo.getIDSerieCatalogo() == ID && modelCatalogo.getAtivo() == ConsAtivo.ATIVO){
				return true;
			}
		}
		
		return false;
	}
	
	public String getCaminhoVideo(int ID){
		for (ModelCatalogo modelCatalogo : listaCatalogo) {
			if (modelCatalogo.getID() == ID && modelCatalogo.getAtivo() == ConsAtivo.ATIVO) {
				return modelCatalogo.getCaminho();
			}
		}
		
		return "";
	}
	
	public DefaultTableModel tblEps(int IDCat, int IdIdioma, int Temp){
		DefaultTableModel retorno = new DefaultTableModel(new Object[][]{}, new String[]{ID, N_EP, NO_EP});
		
		for (ModelCatalogo modelCatalogo : listaCatalogo) {
			if(modelCatalogo.getAtivo() == ConsAtivo.ATIVO && modelCatalogo.getIDSerieCatalogo() == IDCat && modelCatalogo.getTemporada() == Temp){
				String titulo = modelCatalogo.getNome();
				
				for (ModelTituloIdioma modelTituloIdioma : listaTituloIdioma) {
					if(modelTituloIdioma.getIDIdi().getID() == IdIdioma && modelTituloIdioma.getAtivo() == ConsAtivo.ATIVO && modelTituloIdioma.getIDTc().getIDCat() == modelCatalogo.getID()){
						titulo = modelTituloIdioma.getIDTc().getTitulo();
					}
				}
				
				retorno.addRow(new Object[]{modelCatalogo.getID(), modelCatalogo.getEpisodio(), titulo});
			}
		}
		
		return retorno;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ComboBoxModel getTemporadas(int IDCat){
		DefaultComboBoxModel retorno = new DefaultComboBoxModel();
		
		for (ModelCatalogo modelCatalogo : listaCatalogo) {
			if(modelCatalogo.getIDSerieCatalogo() == IDCat){
				if(retorno.getIndexOf(modelCatalogo.getTemporada()) == -1){

					retorno.addElement(modelCatalogo.getTemporada());
				}
			}
		}
		
		return retorno;
	}
}
