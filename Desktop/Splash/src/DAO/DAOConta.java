package DAO;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Constantes.ConsPaths;
import Modelo.ModelCliente;
import Modelo.ModelConta;
import Modelo.ModelIdioma;
import util.ImgUtils;

public class DAOConta extends DAOBas{
	
	public void ChangeLine(int IDConta, int Line){
		try {
			cn.Conectar();
			
			cs = cn.getConnection().prepareCall("{call spChangeLine(?,?)}");
			
			cs.setInt("p_IDConta", IDConta);
			cs.setInt("p_Line", Line);
			
			cs.execute();
			
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Inserir(ModelConta conta){
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spInsertConta(?,?,?,?,?,?,?,?)}");
			cs.setString("p_Nome", conta.getNome());
			cs.setString("p_Senha", conta.getNome());
			cs.setBlob("p_Foto", conta.getFoto());
			cs.setDate("p_dtNascimento", conta.getDtNasc());
			cs.setInt("p_idCliente", conta.getCliente().getID());
			cs.setInt("p_idIdioma", conta.getIdioma().getID());
			cs.setInt("p_ativo", conta.getAtivo());
			cs.setInt("p_line", conta.getLine());

			cs.execute();
			
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Update(ModelConta conta){
		try {
			
			cn.Conectar();
			
			cs = cn.getConnection().prepareCall("{call spUpdateConta(?, ?, ?, ?, ?, ?, ?, ?)}");
			
			cs.setInt("p_IDConta", conta.getID());
			cs.setString("p_Nome", conta.getNome());
			cs.setString("p_Senha", conta.getSenha());
			cs.setBlob("p_Foto", conta.getFoto());
			//cs.setDate("p_DTNascimento", conta.getDtNasc());
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			cs.setString("p_DTNascimento", df.format(conta.getDtNasc()));
			cs.setInt("p_IDCliente", conta.getCliente().getID());
			cs.setInt("p_IDIdioma", conta.getIdioma().getID());
			cs.setInt("p_Ativo", conta.getAtivo());
			
			cs.execute();
			cn.Desconectar();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ModelConta> Selecionar(){
		ModelConta conta;
		ModelCliente cl;
		ModelIdioma idi;
		List<ModelConta> retorno = new ArrayList<ModelConta>();
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from tblConta");
			rs = ps.executeQuery();
			while(rs.next()){
				conta = new ModelConta();
				conta.setID(rs.getInt("IDCONTA"));
				conta.setNome(rs.getString("NOME"));
				conta.setSenha(rs.getString("SENHA"));
				conta.setLine(rs.getInt("LINE"));
				
				InputStream input = rs.getBinaryStream("FOTO");
				if(input != null){
				   ByteArrayOutputStream output = new ByteArrayOutputStream();
				   // set read buffer size
				   byte[] rb = new byte[1024];
				   int ch = 0;
				   while ((ch = input.read(rb)) != -1){	
				       output.write(rb, 0, ch);
				   }
				   // transfer to byte buffer
				   byte[] b = output.toByteArray();
				   input.close();
				   output.close();
				   // onde o método setImagem espera um array de bytes
				   conta.setImageBytes(b);                    
				}
				
				else
				{
					//System.out.println(System.getProperty("user.dir").toString());
					ImgUtils imgUtils = new ImgUtils();
					conta.setImageBytes(imgUtils.imageToByte(ConsPaths.TESTE));
				}
				
				conta.setDtNasc(rs.getDate("DTNASCIMENTO"));
				cl = new ModelCliente();
				cl.setID(rs.getInt("IDCLIENTE"));
				idi = new ModelIdioma();
				idi.setID(rs.getInt("IDIDIOMA"));
				conta.setAtivo(rs.getInt("ATIVO"));
				conta.setCliente(cl);
				conta.setIdioma(idi);
				retorno.add(conta);
			}
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
}
