package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Props {
	
	private static String PATH_PROPS = "./Props/Config";
	
	private Properties props;
	private FileOutputStream fo;
	private FileInputStream fi;
	
	public Props(){
		Paths p = new Paths();
		PATH_PROPS = p.propsPath;
	}
	
	public Properties getPros() {
		
		try {
			fi = new FileInputStream(PATH_PROPS);
		} catch (FileNotFoundException e) {
			//Escrever um arquivo de configuração, vou pensar ainda
			try {
				fo = new FileOutputStream(PATH_PROPS);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		props = new Properties();
		try {
			props.load(fi);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
	
	public void setProperties(String PropName, String PropValue){
		props = getPros();
		try {
			fo = new FileOutputStream(PATH_PROPS);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		props.setProperty(PropName, PropValue);
		try {
			props.store(fo, null);
			fo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getPropValue(String propName){
		props = getPros();
		return props.getProperty(propName);
	}
	
	public void removeProp(String propName){
		props = getPros();
		props.remove(propName);
		try {
			fo = new FileOutputStream(PATH_PROPS);
			props.store(fo, null);
			fo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void teste(){
		setProperties("p.pass", "#");
	}
}
