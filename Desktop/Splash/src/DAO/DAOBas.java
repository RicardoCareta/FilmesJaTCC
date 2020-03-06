package DAO;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.classeConexao;

public abstract class DAOBas {

	public classeConexao cn = new classeConexao();
	public PreparedStatement ps;
	public ResultSet rs;
	public CallableStatement cs;
	
}
