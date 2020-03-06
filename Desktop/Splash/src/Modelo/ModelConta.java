package Modelo;

import java.io.InputStream;
import java.sql.Date;

import javax.swing.ImageIcon;

public class ModelConta {
	private int ID;
	private String Nome;
	private String Senha;
	private InputStream Foto;
	private Date dtNasc;
	private ModelCliente cliente;
	private ModelIdioma idioma;
	private int Ativo;
	private int Line;
	

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
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome.toUpperCase();
	}
	public String getSenha() {
		return Senha;
	}
	public void setSenha(String senha) {
		Senha = senha;
	}
	public InputStream getFoto() {
		return Foto;
	}
	public void setFoto(InputStream foto) {
		Foto = foto;
	}
	public Date getDtNasc() {
		return dtNasc;
	}
	public void setDtNasc(Date dtNasc) {
		this.dtNasc = dtNasc;
	}
	public ModelCliente getCliente() {
		return cliente;
	}
	public void setCliente(ModelCliente cliente) {
		this.cliente = cliente;
	}
	public ModelIdioma getIdioma() {
		return idioma;
	}
	public void setIdioma(ModelIdioma idioma) {
		this.idioma = idioma;
	}
	public int getAtivo() {
		return Ativo;
	}
	public void setAtivo(int ativo) {
		Ativo = ativo;
	}
	public int getLine(){
		return Line;
	}
	public void setLine(int line){
		this.Line = line;
	}
}
