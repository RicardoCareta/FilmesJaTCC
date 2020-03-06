package control;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import DAO.DAOImagens;
import model.ModelImagens;

public class ControlImagens {
	public static final int TIPO_SLIDE = 1;
	
	private DAOImagens daoImagens;

	private List<ModelImagens> lista;
	
	public ControlImagens(){
		lista = new ArrayList<>();
	}
	
	public void Inserir(String caminho, int tipo){
		daoImagens = new DAOImagens();
		ModelImagens imagem = new ModelImagens();
		imagem.setImagem(caminho);
		imagem.setTipo_Imagem(tipo);
		daoImagens.Inserir(imagem);
	}
	

	public void Inserir(InputStream caminho, int tipo){
		daoImagens = new DAOImagens();
		ModelImagens imagem = new ModelImagens();
		imagem.setCaminhoImagem(caminho);
		//imagem.setImageArray();
		imagem.setTipo_Imagem(tipo);
		daoImagens.Inserir(imagem);
	}
	
	public void DeletarTipo(int tipo){
		daoImagens = new DAOImagens();
		daoImagens.DeletarTipo(tipo);
	}
	
	public List<ImageIcon> SelecionarTipo(int tipo){
		List<ImageIcon> img = new ArrayList<ImageIcon>();
		
		daoImagens = new DAOImagens();
		
		lista = new ArrayList<ModelImagens>();
		lista = daoImagens.SelecionarTipo(tipo);
		
		for (ModelImagens modelImagens : lista) {
			if(modelImagens.getTipo_Imagem() == tipo){
				img.add(new ImageIcon(modelImagens.getImageArray()));
			}
		}
		
		return img;
	}
	
	@SuppressWarnings("serial")
	public DefaultTableModel ImagensSite(){
		DefaultTableModel retorno = new DefaultTableModel(new Object[][] {},
				new String[] { "Imagens" }){
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return ImageIcon.class;
				default:
					return String.class;
				}
			}
		};
		
		SelecionarTipo(1);
		
		for (ModelImagens modelImagens : lista) {
			ImageIcon img = new ImageIcon(modelImagens.getImageArray());	
			retorno.addRow(new Object[]{img});
		}
		
		return retorno;
		
	}
}
