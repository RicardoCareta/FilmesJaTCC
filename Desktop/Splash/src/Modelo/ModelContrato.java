package Modelo;

import java.sql.Date;

public class ModelContrato {
	private int ID;
	private Date Vencimento;
	private double Preco;
	private ModelPlanos Plano;
	private ModelCliente Cliente;
	private int Ativo;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Date getVencimento() {
		return Vencimento;
	}
	public void setVencimento(Date vencimento) {
		Vencimento = vencimento;
	}
	public double getPreco() {
		return Preco;
	}
	public void setPreco(double preco) {
		Preco = preco;
	}
	public ModelPlanos getPlano() {
		return Plano;
	}
	public void setPlano(ModelPlanos plano) {
		Plano = plano;
	}
	public ModelCliente getCliente() {
		return Cliente;
	}
	public void setCliente(ModelCliente cliente) {
		Cliente = cliente;
	}
	public int getAtivo() {
		return Ativo;
	}
	public void setAtivo(int ativo) {
		Ativo = ativo;
	}
}
