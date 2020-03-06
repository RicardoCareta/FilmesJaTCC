package util;

public class ConvertPassword {

	public static String convert(char[] a){
		String password = "";
		for (int i = 0; i < a.length; i++) {
			password += a[i];
		}
		
		return password;
	}
}
