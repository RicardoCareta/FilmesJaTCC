package Modelo;

public class ModelSearch {
	private ModelCatalogo catalogo;
	private ModelTituloCatalogo tituloCatalogo;
	private ModelTituloIdioma tituloIdioma;
	private ModelCatalogoEnvolvidos catalogoEnvolvido;
	
	public ModelCatalogo getCatalogo() {
		return catalogo;
	}
	public void setCatalogo(ModelCatalogo catalogo) {
		this.catalogo = catalogo;
	}
	public ModelTituloCatalogo getTituloCatalogo() {
		return tituloCatalogo;
	}
	public void setTituloCatalogo(ModelTituloCatalogo tituloCatalogo) {
		this.tituloCatalogo = tituloCatalogo;
	}
	public ModelTituloIdioma getTituloIdioma() {
		return tituloIdioma;
	}
	public void setTituloIdioma(ModelTituloIdioma tituloIdioma) {
		this.tituloIdioma = tituloIdioma;
	}
	public ModelCatalogoEnvolvidos getCatalogoEnvolvido(){
		return catalogoEnvolvido;
	}
	public void setCatalogoEnvolvido(ModelCatalogoEnvolvidos catalogoEnvolvido){
		this.catalogoEnvolvido = catalogoEnvolvido;
	}
}
