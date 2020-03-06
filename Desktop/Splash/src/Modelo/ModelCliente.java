package Modelo;

public class ModelCliente {
	private int ID;
	private String Telefone;
	private String Senha;
	private String Email;
	private String CPF;
	private ModelIdioma Idioma;
	private ModelPais Pais;
	private int SenhaCatalogo;
	private int Ativo;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getTelefone() {
		return Telefone;
	}
	public void setTelefone(String telefone) {
		Telefone = telefone;
	}
	public String getSenha() {
		return Senha;
	}
	public void setSenha(String senha) {
		Senha = senha;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public ModelIdioma getIdioma() {
		return Idioma;
	}
	public void setIdioma(ModelIdioma idioma) {
		Idioma = idioma;
	}
	public ModelPais getPais() {
		return Pais;
	}
	public void setPais(ModelPais pais) {
		Pais = pais;
	}
	public int getSenhaCatalogo() {
		return SenhaCatalogo;
	}
	public void setSenhaCatalogo(int senhaCatalogo) {
		SenhaCatalogo = senhaCatalogo;
	}
	public int getAtivo() {
		return Ativo;
	}
	public void setAtivo(int ativo) {
		Ativo = ativo;
	}
}
