package Controle;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.ImageIcon;

import Constantes.ConsAtivo;
import DAO.DAOConta;
import Modelo.ModelCliente;
import Modelo.ModelConta;
import Modelo.ModelIdioma;

public class ControlConta {

	public static final int LINE_ON = 1;
	public static final int LINE_OFF = 0;
	
	private DAOConta daoConta;
	
	private List<ModelConta> listaConta;
	
	private ModelConta conta;
	private ModelCliente cliente;
	private ModelIdioma idioma;
	
	public ControlConta(){
		daoConta = new DAOConta();
		listaConta= daoConta.Selecionar();
	}
	
	public void Inserir(String Nome, String Senha, InputStream Foto, Date DataNascimento, int IDCliente, int IDIdioma){
		cliente = new ModelCliente();
		cliente.setID(IDCliente);
		
		idioma = new ModelIdioma();
		idioma.setID(IDIdioma);
		
		conta = new ModelConta();
		conta.setCliente(cliente);
		conta.setIdioma(idioma);
		conta.setFoto(Foto);
		conta.setDtNasc(DataNascimento);
		conta.setSenha(Senha);
		conta.setNome(Nome);
		conta.setAtivo(ConsAtivo.ATIVO);
		conta.setLine(LINE_OFF);
		
		daoConta.Inserir(conta);
	}
	
	public void Update(int ID, String Nome, String Senha, InputStream Foto, Date DataNascimento, int IDCliente, int IDIdioma, int Ativo){
	
		cliente = new ModelCliente();
		cliente.setID(IDCliente);
		
		//"No meu sonho eu nunca deixei de acreditar, com minha persistência, sei que posso alcançar. De passo em passo, com treino e esforço, sei que esse dia vai chegar!"
		//"Não importam o que digam, não vou desistir. Meu forte coração me ensinou a persistir. De passo em passo, com suor e dor, sei que um dia vou conseguir"
		
		idioma = new ModelIdioma();
		idioma.setID(IDIdioma);
		
		conta = new ModelConta();
		
		conta.setID(ID);
		conta.setNome(Nome);
		conta.setSenha(Senha);
		conta.setFoto(Foto);
		conta.setDtNasc(DataNascimento);
		conta.setIdioma(idioma);
		conta.setCliente(cliente);
		conta.setAtivo(Ativo);
		
		daoConta.Update(conta);
	}
	
	public void ChangeLine(int IDConta, int Line){
		for (ModelConta modelConta : listaConta) {
			if(modelConta.getID() == IDConta){
				daoConta.ChangeLine(IDConta, Line);
			}
		}
	}
	
	public List<ModelConta> Selecionar(){
		List<ModelConta> retorno = new ArrayList<ModelConta>();
		
		for (ModelConta modelConta : listaConta) {
			if(modelConta.getAtivo() == ConsAtivo.ATIVO){
				retorno.add(modelConta);
			}
		}
		
		return retorno;
	}
	
	public List<Integer> getID(int IDCliente){
		List<Integer> retorno = new ArrayList<Integer>();
		for (ModelConta modelConta : listaConta) {
			if(modelConta.getAtivo() == ConsAtivo.ATIVO && modelConta.getCliente().getID() == IDCliente){
				retorno.add(modelConta.getID());
			}
		}
		
		return retorno;
	}
	
	public ImageIcon getImageIcon(int ID){
		for (ModelConta modelConta : listaConta) {
			if(modelConta.getID() == ID && modelConta.getAtivo() == ConsAtivo.ATIVO){
				return modelConta.getImageIcon();
			}
		}
		
		//Aqui pode voltar uma imagem padrão ou algo assim, ainda não decidi
		return null;
	}
	
	public String getNome(int ID){
		for (ModelConta modelConta : listaConta) {
			if(modelConta.getID() == ID && modelConta.getAtivo() == ConsAtivo.ATIVO){
				return modelConta.getNome();
			}
		}
		
		return "Sem nome";
	}
	
	public Date getDtNasc(int ID){
		for (ModelConta modelConta : listaConta) {
			if(modelConta.getID() == ID && modelConta.getAtivo() == ConsAtivo.ATIVO){
				return modelConta.getDtNasc();
			}
		}
		
		return null;
	}
	
	public int getContaIdiomaID(int ID){
		for (ModelConta modelConta : listaConta) {
			if(modelConta.getID() == ID && modelConta.getAtivo() == ConsAtivo.ATIVO){
				return modelConta.getIdioma().getID();
			}
		}
		
		return 0;
	}
	
	public int getIdade(int ID){
		for (ModelConta modelConta : listaConta) {
			if(modelConta.getID() == ID && modelConta.getAtivo() == ConsAtivo.ATIVO){
				Calendar cal = Calendar.getInstance();
				cal.setTime(modelConta.getDtNasc());
				
				Calendar as = new GregorianCalendar();
				
				int idade = as.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
				
				if(as.get(Calendar.DAY_OF_YEAR) - cal.get(Calendar.DAY_OF_YEAR) >= 0){
					idade++;
				}
				
				return idade;
			}
		}
		
		return 0;
	}
	
	public int getCliente(int IDConta){
		for (ModelConta modelConta : listaConta) {
			//System.out.println(IDConta);
			if(modelConta.getID() == IDConta && modelConta.getAtivo() == ConsAtivo.ATIVO){
				return modelConta.getCliente().getID();
			}
		}
		
		return 0;
	}
	
	public int getContaCount(int IDCliente){
		int count = 0;
		for (ModelConta modelConta : listaConta) {
			if(modelConta.getCliente().getID() == IDCliente && modelConta.getAtivo() == ConsAtivo.ATIVO){
				count++;
			}
		}
		
		return count;
	}
}
