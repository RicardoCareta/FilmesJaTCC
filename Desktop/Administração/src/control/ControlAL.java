package control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import DAO.DAOAL;
import model.ModelAL;
import Enum.EnumAL;
import Enum.EnumAtivo;
import Enum.EnumTexto;

public class ControlAL {
	
	private DAOAL daoAL;
	private ModelAL al;
	
	private List<ModelAL> listaAL;
	
	public ControlAL(){
		update();
	}
	
	public void update(){
		daoAL = new DAOAL();
		listaAL = daoAL.Selecionar();
		

		File f = new File(System.getProperty("user.dir") +"\\Audios\\");
		
		if(!f.exists()){
			f.mkdirs();
		}
		else{
			for (String s : f.list()) {
				File deletar = new File(f.getPath(), s);
				deletar.delete();
			}
		}
		
		for (ModelAL modelAL : listaAL) {
			CriarArquivo(modelAL.getArquivoByte(), String.valueOf(modelAL.getID()), modelAL.getLinkFile());
		}
	}
	
	public void Inserir(int Tipo, String LinkFile, int IDCat, int IDIdioma){
		try {
			
			if(Tipo == 0){
				try {
					throw new Exception("Campo tipo nulo");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			if(LinkFile.trim().equals("")){
				try {
					throw new Exception("Campo linkfile nulo");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			if(IDCat == 0){
				try {
					throw new Exception("Campo catalogo nulo");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			if(IDIdioma == 0){
				try {
					throw new Exception("Campo idioma nulo");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			al = new ModelAL();
			al.setTipo(Tipo);
			al.setLinkFile(LinkFile);
			al.setIDCat(IDCat);
			al.setIDIdi(IDIdioma);
			al.setAtivo(EnumAtivo.ATIVO.getAtivo());
			daoAL = new DAOAL();
			daoAL.Inserir(al);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Update(int idCat, int Ativo){
		try {
			al = new ModelAL();
			al.setIDCat(idCat);
			al.setAtivo(Ativo);
			daoAL = new DAOAL();
			daoAL.Update(al);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ModelAL> Selecionar(int IDCat){
		List<ModelAL> lista = new ArrayList<ModelAL>();
		
		for (ModelAL modelAL : listaAL) {
			if(modelAL.getIDCat() == IDCat){
				lista.add(modelAL);
			}
		}
		
		return lista;
	}
	public List<ModelAL> Selecionar(int IDCat, int Tipo){
		List<ModelAL> lista = new ArrayList<ModelAL>();
		
		for (ModelAL modelAL : listaAL) {
			if(modelAL.getIDCat() == IDCat && modelAL.getTipo() == Tipo){
				lista.add(modelAL);
				
			}
		}
		
		return lista;
	}
	
	private void CriarArquivo(byte[] array, String nome, InputStream in){
		try {
			File file = new File(System.getProperty("user.dir") +"\\Audios\\" + nome + ".mp3");
		
			
			file.createNewFile();
			
			FileOutputStream fos = new FileOutputStream(file);
			
			fos.write(array);
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public DefaultTableModel tblAudio(int IDCat){
		update();
		ControlIdioma controlIdioma = new ControlIdioma();

		
		DefaultTableModel retorno = new DefaultTableModel(new Object[][] {},
				new String[] { EnumTexto.Idioma.get(), EnumTexto.Caminho.get() });
		
		for (ModelAL modelAL : listaAL) {
			if(modelAL.getTipo() == EnumAL.AUDIO.getAL() && modelAL.getIDCat() == IDCat){
				retorno.addRow(new Object[] {controlIdioma.getNome(modelAL.getIDIdi()), System.getProperty("user.dir") +"\\Audios\\" + modelAL.getID() + ".mp3"});
			}
		}
		
		return retorno;
	}
}
