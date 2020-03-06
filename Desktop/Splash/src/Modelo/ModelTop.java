package Modelo;

public class ModelTop {
	private int IDCat;
	private int ViewCount;
	public int getIDCat() {
		return IDCat;
	}
	public void setIDCat(int iDCat) {
		IDCat = iDCat;
	}
	public int getViewCount() {
		return ViewCount;
	}
	public void setViewCount(int viewCount) {
		ViewCount = viewCount;
	}
	public String toString(){
		return String.valueOf(getIDCat());
	}
}
