package Controle;

import DAO.DAOTesteConexao;

public class ControlTesteConexao {
	
	private DAOTesteConexao daoTC;
	
	public boolean teste(){
		daoTC = new DAOTesteConexao();
		return daoTC.teste();
	}
}
