package Modelo;

public class ModelPlanos {
	private int ID;
	private double Preco;
	private int QntTelas;
	private String NomePlano;
	private int Ativo;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public double getPreco() {
		return Preco;
	}
	public void setPreco(double preco) {
		Preco = preco;
	}
	public int getQntTelas() {
		return QntTelas;
	}
	public void setQntTelas(int qntTelas) {
		QntTelas = qntTelas;
	}
	public String getNomePlano() {
		return NomePlano;
	}
	public void setNomePlano(String nomePlano) {
		NomePlano = nomePlano.toString();
	}
	public int getAtivo() {
		return Ativo;
	}
	public void setAtivo(int ativo) {
		Ativo = ativo;
	}
}
