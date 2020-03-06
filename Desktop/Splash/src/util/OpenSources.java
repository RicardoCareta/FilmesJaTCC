package util;

import java.io.IOException;

public class OpenSources {

	public static void OpenURL(String URL){
		try {
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + URL);
		} catch (IOException ex) {
			System.out.print(ex.getMessage());
		}
	}
	
}
