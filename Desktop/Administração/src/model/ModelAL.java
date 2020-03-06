package model;

import java.io.FileInputStream;
import java.io.InputStream;

public class ModelAL {
	private int ID;
	private InputStream LinkFile;
	private int Tipo;
	private int IDIdi;
	private int IDCat;
	private int Ativo;
	
	private byte[] arquivoByte;
	
	public byte[] getArquivoByte(){
		return arquivoByte;
	}
	
	public void setArquivoByte(byte[] arquivoByte){
		this.arquivoByte = arquivoByte;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public InputStream getLinkFile() {
		return LinkFile;
	}
	public void setLinkFile(InputStream linkFile) {
		LinkFile = linkFile;
	}
	public void setLinkFile(String linkFile) {
		InputStream is = null;
		try {
			is = new FileInputStream(linkFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LinkFile = is;
	}
	public int getTipo() {
		return Tipo;
	}
	public void setTipo(int tipo) {
		Tipo = tipo;
	}
	public int getIDIdi() {
		return IDIdi;
	}
	public void setIDIdi(int iDIdi) {
		IDIdi = iDIdi;
	}
	public int getIDCat() {
		return IDCat;
	}
	public void setIDCat(int iDCat) {
		IDCat = iDCat;
	}
	public int getAtivo(){
		return Ativo;
	}
	public void setAtivo(int ativo){
		Ativo = ativo;
	}
}
