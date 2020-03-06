package DAO;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ModelImagens;
import util.classeConexao;

public class DAOImagens extends DAOBas{
	
	
	public void Inserir(ModelImagens imagem){
		cn = new classeConexao();
		cn.Conectar();
		try {
			cs = cn.getConnection().prepareCall("{call spInsertImagens(?,?)}");
			cs.setBlob(1, imagem.getImagem());
			cs.setInt(2, imagem.getTipo_Imagem());
			
			cs.execute();
			cs.close();
			cn.Desconectar();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void DeletarTipo(int tipo){
		cn = new classeConexao();
		cn.Conectar();
		
		try {
			cs = cn.getConnection().prepareCall("{call spDeleteImagens(?)}");
			
			cs.setInt(1, tipo);
			
			cs.execute();
			cs.close();
			cn.Desconectar();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	public List<ModelImagens> SelecionarTipo(int tipo){
		List<ModelImagens> lista = new ArrayList<ModelImagens>();
		
		cn = new classeConexao();
		cn.Conectar();
		
		try {
			ps = cn.getConnection().prepareStatement("select * from tblImagens where Tipo_Image = " + tipo);
			rs = ps.executeQuery();
			
			ModelImagens mi;
			
			while(rs.next()){
				
				mi = new ModelImagens();
				
				InputStream input = rs.getBinaryStream("Foto");
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
				   mi.setImageArray(b);                    
				}
				mi.setIDImagem(rs.getInt("ID_IMAGE"));
				mi.setTipo_Imagem(rs.getInt("TIPO_IMAGE"));
				
				lista.add(mi);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
}
