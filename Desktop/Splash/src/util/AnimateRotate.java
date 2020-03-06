package util;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class AnimateRotate implements Runnable{

	public static final int TO_UP = 1;
	public static final int TO_DOWN = 2;
	public static final int TO_LEFT = 3;
	public static final int TO_RIGHT = 4;
	
	private static final int DELAY = 100;
	
	private JLabel lbl;
	
	private int tipo;
	
	private Thread thr;
	
	public AnimateRotate(JLabel lbl, int tipo) {
		this.lbl = lbl;
		
		this.tipo = tipo;
	
	}
	
	public void Start(){
		thr = new Thread(this);
		thr.start();
	}
	
	@Override
	public void run() {
		RotateLabel();
	}
	
	private void RotateLabel(){
		if(tipo == 0){
			
			ImgUtils imgUtils = new ImgUtils();
			
			lbl.setIcon(new ImageIcon(imgUtils.rotate(imgUtils.ConvertIconToImage(lbl.getIcon()), 90)));
			
			Image dimg = imgUtils.ConvertIconToImage(lbl.getIcon()).getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			ImageIcon imgI = new ImageIcon(dimg);
			lbl.setIcon(imgI);
			
			//imgUtils.OrganizarImagem(lbl, ImgUtils.LOW_RESOLUTION);
			
			for (double angle = 0; angle < 90; angle++) {
				try {
					Thread.sleep(DELAY);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
