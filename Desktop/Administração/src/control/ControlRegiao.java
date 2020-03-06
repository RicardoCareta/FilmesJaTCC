package control;

import model.ModelRegiao;

import java.util.List;

import DAO.DAORegiao;

public class ControlRegiao {
	
	private ModelRegiao regiao;
	private DAORegiao daoRegiao;
	private List<ModelRegiao> listaRegiao;
	
	public ControlRegiao(){
		daoRegiao = new DAORegiao();
		listaRegiao = daoRegiao.Selecionar();
	}
	
	public void Inserir(String Nome){
		if(Nome.trim().equals("")){
			try {
				throw new Exception("Campo nome vazio");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {	
			regiao = new ModelRegiao();
			regiao.setNome(Nome);
			daoRegiao = new DAORegiao();
			daoRegiao.Inserir(regiao);
		} catch (Exception e) {
			//Validar erros depois
		}
	}
	
	public void Update(int id, String Nome, int ativo){
		if(Nome.trim().equals("")){
			try {
				throw new Exception("Campo nome vazio");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {	
			regiao = new ModelRegiao();
			
			regiao.setNome(Nome);
			regiao.setAtivo(ativo);
			regiao.setID(id);
			
			daoRegiao = new DAORegiao();
			daoRegiao.Update(regiao);
		} catch (Exception e) {
			//Validar erros depois
		}
	}
	
	public List<ModelRegiao> Selecionar(){
		return listaRegiao;
	}
}
