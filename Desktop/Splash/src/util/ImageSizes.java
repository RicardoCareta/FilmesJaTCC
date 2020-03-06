package util;

import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import Visão.JFulano;

public class ImageSizes {
	
	private static double LY;
	private static double LX;
	
	private static double sW;
	private static double sH;
	//720 479
	private static double nLY;
	private static double nLX;
	
	private static double pSW = 704;
	private static double pSH = 440;
	
	//private static Component[] listC;
	
	
	public static void ArrumarLocation(Container cont, double SW, double SH){
		for (Component c : cont.getComponents()) {
			if((c instanceof JLabel || c instanceof JButton) && c != JFulano.fundoLabel){
				
				//JFulano frame = new JFulano();
				//System.out.print("a " + LY  + "\r\n");
				LY = c.getLocation().getY();
				//System.out.print("b " + LY + "\r\n");
				LX = c.getLocation().getX();
				sW = SW;
				sH = SH;
				if(((JLabel)c).getText().equals("icon")){
					System.out.println(pSW + " " + pSH);
				}
				nLY = (LY * sH) / pSH;
				nLX = (LX * sW) / pSW;
				
				pSW = SW;
				pSH = SH;
				
				c.setLocation((int)nLX, (int)nLY);
				//c.setBounds((int)nLX + (int)nLX/3, (int)nLY, c.getBounds().width, c.getBounds().height);
				//System.out.print(c.getLocation() + "\r\n");
			}
		}
	}

	public static void OrganizarImagem(URL url, JLabel lbl){
		BufferedImage img = null;
		try {
			img = ImageIO.read(url);
		} catch (Exception e) {
			// TODO: handle exception
		}

		Image dimg = img.getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imgI = new ImageIcon(dimg);
		lbl.setIcon(imgI);
	}
}
