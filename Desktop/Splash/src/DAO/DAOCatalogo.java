package DAO;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Modelo.ModelCatalogo;
import Modelo.ModelCategoria;
import util.classeConexao;

public class DAOCatalogo {
	
	private PreparedStatement ps;
	private CallableStatement cs;
	private classeConexao cn;
	private ResultSet rs;
	
	public void Inserir(ModelCatalogo catalogo){
		cn = new classeConexao();
		
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spInsertCatalogo(?,?,?,?,?,?,?,?,?,?)}");
			cs.setBlob(1, catalogo.getCaminhoImagem());
			cs.setInt(2, catalogo.getAno());
			cs.setInt(3, catalogo.getCategoria().getID_Cat());
			cs.setInt(4, catalogo.getTemporada());
			cs.setInt(5, catalogo.getEpisodio());
			cs.setInt(6, 1);
			cs.setInt(7, catalogo.getIDSerieCatalogo());
			cs.setString(8, catalogo.getCaminho());
			cs.setString(9, catalogo.getSinopse());
			cs.setString(10, catalogo.getNome());
			
			cs.execute();
			
			cs.close();
			cn.Desconectar();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ModelCatalogo> Selecionar(){
		List<ModelCatalogo> retorno = new ArrayList<ModelCatalogo>();
		ModelCatalogo c;
		ModelCategoria ca;
		
		cn = new classeConexao();
		try {
			cn.Conectar();
			ps = cn.getConnection().prepareStatement("select * from catalogocategoria");
			rs = ps.executeQuery();
			while(rs.next()){
				c = new ModelCatalogo();
				c.setID(rs.getInt("IDCAT"));
				c.setCaminhoImagem(rs.getBinaryStream("IMAGEM"));
				c.setAno(rs.getInt("ANO"));
				
				ca = new ModelCategoria();
				
				InputStream input = rs.getBinaryStream("IMAGEM");
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
				   c.setImageBytes(b);                    
				}
				
				ca.setID_Cat(rs.getInt("IDCATEGORIA"));
				ca.setNome(rs.getString("CATEGORIA"));
				c.setCategoria(ca);
				c.setSinopse(rs.getString("SINOPSE"));
				c.setTemporada(rs.getInt("TEMPORADA"));
				c.setEpisodio(rs.getInt("EPISÓDIO"));
				c.setAtivo(rs.getInt("ATIVO"));
				c.setIDSerieCatalogo(rs.getInt("IDSERIECATALOGO"));
				c.setNome(rs.getString("NOME"));
				c.setCaminho(rs.getString("CAMINHO"));
				retorno.add(c);
			}
			
			rs.close();
			ps.close();
			cn.Desconectar();
		} catch (Exception e) {
			return retorno;
		}
		
		return retorno;
	}
	
	public void Update(ModelCatalogo c){
		cn = new classeConexao();
		try {
			cn.Conectar();
			cs = cn.getConnection().prepareCall("{call spUpdateCatalogo(?,?,?,?,?,?,?,?,?,?,?)}");
			cs.setInt("p_idCat", c.getID());
			cs.setBlob("p_Imagem", c.getCaminhoImagem());
			cs.setInt("p_Ano", c.getAno());
			cs.setString("p_Nome", c.getNome());
			cs.setInt("p_idCategoria", c.getCategoria().getID_Cat());
			cs.setInt("p_Temporada", c.getTemporada());
			cs.setInt("p_Episódio", c.getEpisodio());
			cs.setInt("p_Ativo", c.getAtivo());
			cs.setInt("p_IDSerieCatalogo", c.getIDSerieCatalogo());
			cs.setString("p_Caminho", c.getCaminho());
			cs.setString("p_Sinopse", c.getSinopse());
			
			cs.execute();
			cs.close();
			cn.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
