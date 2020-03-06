package DAO;

import util.classeConexao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.ModelAL;

public class DAOAL {

	private classeConexao cn;
	private PreparedStatement ps;
	private CallableStatement cs;
	private ResultSet rs;
	
	public void Inserir(ModelAL al){
		try {
			cn = new classeConexao();
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spInsertAudioLegenda(?,?,?,?,?)}");
			cs.setInt(1, al.getTipo());
			cs.setBlob(2, al.getLinkFile());
			
			cs.setInt(3, al.getIDCat());
			cs.setInt(4, al.getIDIdi());
			cs.setInt(5, 1);
			
			cs.execute();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Update(ModelAL al){
		try {
			cn = new classeConexao();
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spUpdateAudioLegenda(?,?)}");
			cs.setInt("p_idCat", al.getIDCat());
			cs.setInt("p_Ativo", al.getAtivo());
			cs.execute();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ModelAL> Selecionar(){
		List<ModelAL> retorno = new ArrayList<ModelAL>();
		ModelAL al;
		
		try {
			cn = new classeConexao();
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from tblAudioLegenda where Ativo = 1");
			rs = ps.executeQuery();
			while(rs.next()){
				al = new ModelAL();
				al.setID(rs.getInt(1));
				al.setTipo(rs.getInt(2));
			//	al.setLinkFile(rs.getString(3));
				InputStream input = rs.getBinaryStream("LINKFILE");
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
				   al.setArquivoByte(b);                    
				}
				
				al.setIDCat(rs.getInt("IDCAT"));
				al.setIDIdi(rs.getInt("IDIDI"));
				retorno.add(al);
			}
			cn.Desconectar();
		} catch (Exception e) {
			return retorno;
		}
		
		return retorno;
	}
	
}
