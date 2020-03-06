package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import DAO.DAOCatalogo;
import Enum.ConsColunaCatalogo;
import Enum.EnumAtivo;
import Enum.EnumCatalogo;
import model.ModelCatalogo;
import model.ModelCategoria;

public class ControlCatalogo {

	private ModelCatalogo catalogo;
	private DAOCatalogo daoCatalogo;

	private List<ModelCatalogo> listaCatalogo;
	
	private static final String TEXTOIMAGEM = "Ta no banco de dados";

	public ControlCatalogo() {
		daoCatalogo = new DAOCatalogo();
		listaCatalogo = daoCatalogo.Selecionar();
	}

	
	public void Inserir(String caminhoImagem, int Ano, int IDCategoria, int Temporada, int Episodio,
			int IDSerieCatalogo, String Caminho, String Nome) {

		catalogo = new ModelCatalogo();
		catalogo.setCaminhoImagem(caminhoImagem);
		catalogo.setAno(Ano);
		ModelCategoria ca = new ModelCategoria();
		ca.setID_Cat(IDCategoria);
		catalogo.setCategoria(ca);
		catalogo.setTemporada(Temporada);
		catalogo.setEpisodio(Episodio);
		catalogo.setIDSerieCatalogo(IDSerieCatalogo);
		catalogo.setCaminho(Caminho);
		catalogo.setNome(Nome);

		try {
			daoCatalogo = new DAOCatalogo();
			daoCatalogo.Inserir(catalogo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		new ControlCatalogo();

	}

	public void Inserir(String caminhoImagem, int Ano, int IDCategoria, int Temporada, int Episodio, String Caminho,
			String Sinopse, String Nome, int IDSerieCatalogo) {

		catalogo = new ModelCatalogo();
		/*if(caminhoImagem.trim().equals("") || caminhoImagem.equals(TEXTOIMAGEM)){
			caminhoImagem = ControlCatalogo.class.getResource("/view/imagens/imagemNull.jpg").toString().replace("file:/", "");
		}*/
		catalogo.setCaminhoImagem(caminhoImagem);
		catalogo.setAno(Ano);
		ModelCategoria ca = new ModelCategoria();
		ca.setID_Cat(IDCategoria);
		catalogo.setCategoria(ca);
		catalogo.setIDSerieCatalogo(IDSerieCatalogo);
		catalogo.setTemporada(Temporada);
		catalogo.setEpisodio(Episodio);
		catalogo.setCaminho(Caminho);
		catalogo.setSinopse(Sinopse);
		catalogo.setNome(Nome);

		try {
			daoCatalogo = new DAOCatalogo();
			daoCatalogo.Inserir(catalogo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ControlCatalogo();
	}
	
	public void Update(int ID,String caminhoImagem, int Ano, int IDCategoria, int Temporada, int Episodio, String Caminho,
			String Sinopse, String Nome, int Ativo) {

		catalogo = new ModelCatalogo();
		catalogo.setCaminhoImagem(caminhoImagem);
		catalogo.setAno(Ano);
		ModelCategoria ca = new ModelCategoria();
		ca.setID_Cat(IDCategoria);
		catalogo.setCategoria(ca);
		catalogo.setTemporada(Temporada);
		catalogo.setEpisodio(Episodio);
		catalogo.setCaminho(Caminho);
		catalogo.setSinopse(Sinopse);
		catalogo.setNome(Nome);
		catalogo.setID(ID);
		catalogo.setAtivo(Ativo);

		try {
			daoCatalogo = new DAOCatalogo();
			daoCatalogo.Update(catalogo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ControlCatalogo();
	}

	public List<ModelCatalogo> Selecionar() {
		List<ModelCatalogo> retorno = new ArrayList<ModelCatalogo>();
		for (ModelCatalogo modelCatalogo : retorno) {
			if (modelCatalogo.getAtivo() == EnumAtivo.ATIVO.getAtivo()) {
				retorno.add(modelCatalogo);
			}
		}
		return listaCatalogo;
	}
	public String getNameSerie(String nome){
		for (ModelCatalogo modelCatalogo : listaCatalogo) {
			if(modelCatalogo.getNome().indexOf(nome) != -1 && modelCatalogo.getAtivo() == EnumAtivo.ATIVO.getAtivo()){
				return modelCatalogo.getNome();
			}
		}
		
		return "";
	}
	
	public int SelecionarLastID() {
		int id = 0;
		for (ModelCatalogo modelCatalogo : listaCatalogo) {
			if (modelCatalogo.getID() >= id && modelCatalogo.getAtivo() == EnumAtivo.ATIVO.getAtivo()) {
				id = modelCatalogo.getID();
			}
		}

		return id;
	}
	

	@SuppressWarnings("serial")
	public DefaultTableModel tblCatalogo() throws IOException {
		DefaultTableModel retorno = new DefaultTableModel(new Object[][] {},
				new String[] { EnumCatalogo.ID.get(), EnumCatalogo.Nome.get(), EnumCatalogo.Sinopse.get(),
						EnumCatalogo.Caminho.get(), EnumCatalogo.Ano.get(),
						EnumCatalogo.Temporada.get(), EnumCatalogo.Episodio.get(), EnumCatalogo.IDCategoria.get(),
						EnumCatalogo.Categoria.get(), EnumCatalogo.IDSerieCatalogo.get(), EnumCatalogo.FS.get(), EnumCatalogo.Ativo.get() }) {

			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 11:
					return Boolean.class;
				case 3:
					// ImageIcon i = new ImageIcon();
					// i.getImage()
					return ImageIcon.class;
				default:
					return String.class;
				}
			}

		};

		for (ModelCatalogo modelCatalogo : listaCatalogo) {

			boolean ativo = false;
			String FS = "";

			if (modelCatalogo.getAtivo() == EnumAtivo.ATIVO.getAtivo()) {
				ativo = true;
			} else if (modelCatalogo.getAtivo() == EnumAtivo.DESATIVO.getAtivo()) {
				ativo = false;
			}
			
			if(modelCatalogo.getTemporada() == 0){
				FS = "Filme";
			}else{
				FS = "Série";
			}

			
			retorno.addRow(new Object[] { modelCatalogo.getID(), modelCatalogo.getNome(), modelCatalogo.getSinopse(), modelCatalogo.getCaminho(), modelCatalogo.getAno(), modelCatalogo.getTemporada(),
					modelCatalogo.getEpisodio(), modelCatalogo.getCategoria().getID_Cat(),
					modelCatalogo.getCategoria().getNome(), modelCatalogo.getIDSerieCatalogo(), FS, ativo });

		}
		return retorno;
	}
	
	public String getModel(int ID, int Coluna){
		for (ModelCatalogo modelCatalogo : listaCatalogo) {
			if(modelCatalogo.getID() == ID){
				switch (Coluna) {
				case ConsColunaCatalogo.NOME:
					return modelCatalogo.getNome();
				case ConsColunaCatalogo.SINOPSE:
					return modelCatalogo.getSinopse();
				case ConsColunaCatalogo.ANO:
					return String.valueOf(modelCatalogo.getAno());
				case ConsColunaCatalogo.CAMINHO:
					return modelCatalogo.getCaminho();
				case ConsColunaCatalogo.IMAGEM:
					return TEXTOIMAGEM;
				case ConsColunaCatalogo.IDSERIECATALOGO:
					return String.valueOf(modelCatalogo.getIDSerieCatalogo());
				case ConsColunaCatalogo.NOMECATEGORIA:
					return String.valueOf(modelCatalogo.getCategoria().getNome());
				default:
					return "";
				}
			}
		}
		
		return "";
	}
	
	public ImageIcon getImage(int ID){
		for (ModelCatalogo modelCatalogo : listaCatalogo) {
			if(modelCatalogo.getID() == ID){
				return modelCatalogo.getImageIcon();
			}
		}
		
		return null;
	}
	
	public boolean getAtivo(int ID){
		for (ModelCatalogo modelCatalogo : listaCatalogo) {
			if(modelCatalogo.getID() == ID){
				if(modelCatalogo.getAtivo() == EnumAtivo.ATIVO.getAtivo()){
					return true;
				}else{
					return false;
				}
			}
		}
		return false;
	}
	
	public List<ModelCatalogo> SelecionarIDSerieCatalogo(int ID){
		List<ModelCatalogo> retorno = new ArrayList<ModelCatalogo>();
		for (ModelCatalogo modelCatalogo : listaCatalogo) {
			if(modelCatalogo.getIDSerieCatalogo() == ID){
				retorno.add(modelCatalogo);
			}
		}
		
		return retorno;
	}
	
	public int getID(String GlobalName){
		for (ModelCatalogo modelCatalogo : listaCatalogo) {
			if(modelCatalogo.getNome().equals(GlobalName)){
				return modelCatalogo.getID();
			}
		}

		return 0;
	}

	
}
