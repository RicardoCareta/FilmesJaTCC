package model;

import java.io.FileInputStream;
import java.io.InputStream;

public class ModelImagens {

	private int IDImagem;
	private InputStream Imagem;
	private int Tipo_Imagem;
	
	private byte[] ImagemByte;
	
	public byte[] getImageArray(){
		return ImagemByte;
	}
	
	public void setImageArray(byte[] imageArray){
		this.ImagemByte = imageArray;
	}
	
	public int getIDImagem() {
		return IDImagem;
	}
	public void setIDImagem(int iDImagem) {
		IDImagem = iDImagem;
	}
	public InputStream getImagem() {
		return Imagem;
	}
	public void setImagem(String imagem) {
		InputStream is = null;
		try {
			is = new FileInputStream(imagem);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Imagem = is;
	}
	
	public void setCaminhoImagem(InputStream caminhoImagem) {
		Imagem = caminhoImagem;
	}
	
	public int getTipo_Imagem() {
		return Tipo_Imagem;
	}
	public void setTipo_Imagem(int tipo_Imagem) {
		Tipo_Imagem = tipo_Imagem;
	}
	
}
