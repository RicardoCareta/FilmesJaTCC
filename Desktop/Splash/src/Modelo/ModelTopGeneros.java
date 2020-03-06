package Modelo;

public class ModelTopGeneros {
	private int IDCat;
	private ModelGenero Genero;
	private int countView;
	public int getIDCat() {
		return IDCat;
	}
	public void setIDCat(int iDCat) {
		IDCat = iDCat;
	}
	public ModelGenero getGenero() {
		return Genero;
	}
	public void setGenero(ModelGenero genero) {
		Genero = genero;
	}
	public int getCountView() {
		return countView;
	}
	public void setCountView(int countView) {
		this.countView = countView;
	}
}
