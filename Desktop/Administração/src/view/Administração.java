package view;

import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.jtattoo.plaf.texture.TextureLookAndFeel;

import util.ScreenSize.SC;

public class Administração {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				//System.out.println(info.getName());
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			 	Properties props = new Properties();
	            
	            props.put("logoString", ""); 
	            props.put("licenseKey", "INSERT YOUR LICENSE KEY HERE");
	            
	            props.put("selectionBackgroundColor", "255 229 0"); 
	            props.put("menuSelectionBackgroundColor", "255 229 0"); 
	            
	            props.put("controlColor", "255 0 0");
	            props.put("controlColorLight", "218 254 230");
	            props.put("controlColorDark", "180 240 197"); 

	            props.put("buttonColor", "218 230 254");
	            props.put("buttonColorLight", "255 255 255");
	            props.put("buttonColorDark", "244 242 232");

	            props.put("rolloverColor", "218 254 230"); 
	            props.put("rolloverColorLight", "218 254 230"); 
	            props.put("rolloverColorDark", "180 240 197"); 

	            props.put("windowTitleForegroundColor", "255 255 255");
	            props.put("windowTitleBackgroundColor", "0 0 0"); 
	            props.put("windowTitleColorLight", "0 0 0"); 
	            props.put("windowTitleColorDark", "0 0 0"); 
	            props.put("windowBorderColor", "0 0 0");
	            
	            // set your theme
	            TextureLookAndFeel.setCurrentTheme(props);
	            
	            
	            // select the Look and Feel
	            UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame frm = new JFrame("asas");
		frm.setUndecorated(true);
		frm.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frm.setVisible(true);
		
		SC.SCREEN_WIDTH = frm.getWidth();
		SC.SCREEN_HEIGHT = frm.getHeight();
		
		frm.setVisible(false);
		frm.dispose();
		
		JControleAdministrador.main(null);
		//JVisualCatalogo.main(null);
		//JLoginAdm.main(null);
	}

}
