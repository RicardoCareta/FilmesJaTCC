package Modelo;

import java.sql.Date;

public class ModelHistorico {
	private int ID;
	private int Avaliacao;
	private int Assistido;
	private int IDCat;
	private int IDConta;
	private Date Data;
	private int Tempo;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getAvaliacao() {
		return Avaliacao;
	}
	public void setAvaliacao(int avaliacao) {
		Avaliacao = avaliacao;
	}
	public int getAssistido() {
		return Assistido;
	}
	public void setAssistido(int assistido) {
		Assistido = assistido;
	}
	public int getIDCat() {
		return IDCat;
	}
	public void setIDCat(int iDCat) {
		IDCat = iDCat;
	}
	public int getIDConta() {
		return IDConta;
	}
	public void setIDConta(int iDConta) {
		IDConta = iDConta;
	}
	public Date getData(){
		return this.Data;
	}
	public void setData(Date data){
		this.Data = data;
	}
	public int getTempo(){
		return this.Tempo;
	}
	public void setTempo(int tempo){
		this.Tempo = tempo;
	}
}
