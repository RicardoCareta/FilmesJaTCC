package Controle;
import DAO.DAOAvaliacao;
import Modelo.ModelAvaliacaoCena;

public class ControlAvaliacao {
	private ModelAvaliacaoCena modelavaliacao;
	private DAOAvaliacao daoavaliacao;
	private ModelAvaliacaoCena avaliacao;
	
	public void Inserir ( int Minuto, int Nota, int IDCat, int IDConta){
		 avaliacao = new ModelAvaliacaoCena();
		
		 avaliacao.setMinuto (Minuto);
		 avaliacao.setNota (Nota);
		 avaliacao.setIdConta (IDConta);
		 avaliacao.setIdCat(IDCat);
		 avaliacao.setAtivo(1);
		 
		 daoavaliacao = new DAOAvaliacao();
		 
		 daoavaliacao.Inserir(avaliacao);
		 
	}

	public void Atualizar (int idAvCena, int Minuto, int Nota, int IDCat, int IDConta, int Ativo){
		avaliacao = new ModelAvaliacaoCena();
		avaliacao.setIdAvCena(idAvCena);
		avaliacao.setMinuto(Minuto);
		avaliacao.setNota (Nota);
		 avaliacao.setIdConta (IDConta);
		 avaliacao.setIdCat(IDCat);
		 avaliacao.setAtivo(Ativo);
		 
		 daoavaliacao = new DAOAvaliacao();
		 
		 daoavaliacao.Atualizar(avaliacao);
	}
}
