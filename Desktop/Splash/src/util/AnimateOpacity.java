package util;

import java.awt.Color;
import javax.swing.JPanel;

public class AnimateOpacity implements Runnable {

	public static final boolean HIDE = true;
	public static final boolean NOT_HIDE = false;

	private static final int DELAY = 150;

	private JPanel pnl;

	private Thread trh;

	private boolean hide;

	private int R, G, B;

	public AnimateOpacity(JPanel painel) {
		this.pnl = painel;
	}

	public AnimateOpacity(JPanel painel, int R, int G, int B, boolean hide) {
		this.pnl = painel;
		this.R = R;
		this.G = G;
		this.B = B;
		this.hide = hide;
	}

	public AnimateOpacity(JPanel painel, int startOpacity) {
		this.pnl = painel;
	}

	public void start() {
		trh = new Thread(this);
		trh.start();
	}

	@Override
	public void run() {
		if (hide == HIDE) {
			for (int i = 0; i < 255; i++) {
				pnl.setBackground(new Color(R, G, B, i));
				pnl.repaint();
				try {
					Thread.sleep(DELAY);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} 
		
		else{
			//pnl.setBackground(new Color(R, G, B, 10));
			//pnl.repaint();
			
			float alpha = 1;
			while (true) {
				alpha += 0.05f;
				if(alpha >= 1.0f){
					System.out.println("AE");
					break;
				}
				else{
					System.out.println("AB");
					pnl.setBackground(new Color(0, 0, 0, alpha));
					pnl.repaint();
					try {
						Thread.sleep(DELAY);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
