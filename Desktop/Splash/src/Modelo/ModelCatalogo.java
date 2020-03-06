package Modelo;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.ImageIcon;

public class ModelCatalogo {
	private int ID;
	private int Classificacao;
	private int Ano;
	private int Episodio;
	private int Temporada;
	private String Caminho;
	private InputStream CaminhoImagem;
	private ModelCategoria Categoria;
	private int IDSerieCatalogo;
	private String Sinopse;
	private String Nome;
	private int Ativo;
	
	private ImageIcon image;
	private byte[] imageBytes;
	
	public ImageIcon getImageIcon(){
		image = new ImageIcon();
		if(getImageBytes() != null){
			image = new ImageIcon(getImageBytes());
		}
		
		return image;
	}
	
	public byte[] getImageBytes() {
		return imageBytes;
	}

	public void setImageBytes(byte[] imageBytes) {
		this.imageBytes = imageBytes;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getClassificacao() {
		return Classificacao;
	}
	public void setClassificacao(int classificacao) {
		Classificacao = classificacao;
	}
	public int getAno() {
		return Ano;
	}
	public void setAno(int ano) {
		Ano = ano;
	}
	public int getEpisodio() {
		return Episodio;
	}
	public void setEpisodio(int episodio) {
		Episodio = episodio;
	}
	public int getTemporada() {
		return Temporada;
	}
	public void setTemporada(int temporada) {
		Temporada = temporada;
	}
	public String getCaminho() {
		return Caminho;
	}
	public void setCaminho(String caminho) {
		Caminho = caminho;
	}
	public InputStream getCaminhoImagem() {
		return CaminhoImagem;
	}
	public void setCaminhoImagem(String caminhoImagem) {
		InputStream is = null;
		try {
			is = new FileInputStream(caminhoImagem);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		CaminhoImagem = is;
	}
	
	public void setCaminhoImagem(InputStream caminhoImagem) {
		CaminhoImagem = caminhoImagem;
	}
	
	public ModelCategoria getCategoria(){
		return Categoria;
	}
	public void setCategoria(ModelCategoria idCategoria){
		Categoria = idCategoria;
	}
	public int getIDSerieCatalogo(){
		return IDSerieCatalogo;
	}
	public void setIDSerieCatalogo(int idSerieCatalogo){
		IDSerieCatalogo = idSerieCatalogo;
	}
	public String getSinopse(){
		return Sinopse;
	}
	public void setSinopse(String sinopse){
		this.Sinopse = sinopse;
	}
	public String getNome(){
		return Nome;
	}
	public void setNome(String Nome){
		this.Nome = Nome;
	}
	public int getAtivo(){
		return Ativo;
	}
	public void setAtivo(int ativo){
		this.Ativo = ativo;
	}

	
}
