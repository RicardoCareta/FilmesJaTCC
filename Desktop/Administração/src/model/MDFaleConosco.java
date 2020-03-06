package model;

import java.util.Date;

public class MDFaleConosco {
	private int IDFC;
	private String Nome;
	private String email;
	private String mensagem;
	private int status;
	private Date data;
	public int getIDFC() {
		return IDFC;
	}
	public void setIDFC(int iDFC) {
		IDFC = iDFC;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
}
