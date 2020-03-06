package DAO;

import util.classeConexao;

public class DAOTesteConexao {

	private classeConexao cn;

	public boolean teste() {

		boolean verdadeiro = false;
		try {
			cn = new classeConexao();
			cn.Conectar();
			verdadeiro = true;
			cn.Desconectar();
		} catch (Exception e) {
			return false;

		}
		return verdadeiro;
	}
}
