package util.Biblia;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPRequestBiblia {

	private boolean block = true;
	
	private static final String URL_Biblia = "https://www.bibliaonline.com.br/acf";
	
	public String getVersiculo(String siglaLivro, int numeroLivro, String nomeLivro, int capituloLivro){
		String versiculo = "";
		String newURL = URL_Biblia + "/" + siglaLivro + "/"  + capituloLivro + "/1";
		
		int numeroVersiculo = 1;
		
		try {
			
			URL obj = new URL(newURL);
			if(block) {
				return "";
			}
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestProperty("charset", "iso-8859-1");
			//int responseCode = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String line = "";
			while((line = in.readLine()) != null){
				line = line.replace("\"", "'");
				//String teste = "p data-v='acf:" + (numeroLivro + 1) + ":1:1'";
				String filtro = "<span class='text' data-reactid='34'>";
				int indexLine = line.indexOf(filtro);
				if(indexLine != -1){
					
					String applyFiltro = line.substring(indexLine + filtro.length(), line.length());
					versiculo = applyFiltro.substring(0, applyFiltro.indexOf("</span>"));
					
					byte[] isoBytes = versiculo.getBytes("ISO-8859-1");
					versiculo = new String(isoBytes, "UTF-8");
					
					versiculo = versiculo + " - " + nomeLivro + " " + capituloLivro + ":" + numeroVersiculo;
					
				//	System.out.println(versiculo);
					
				}
				
			}
			
			in.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return versiculo;
		
		
	}
	
}
