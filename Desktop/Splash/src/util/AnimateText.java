package util;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JLabel;

public class AnimateText implements Runnable{

	private static final int LABEL = 1;
	private static final int BUTTON = 2;
	
	private static final int DELAY_TIME = 100;
	
	private int tipo = 0;
	
	private Thread thr;
	
	private String[] textos;
	
	private Component c;
	
	public void Start(String[] textos, Component c){
		this.textos = textos;
		this.c = c;
		
		if(c instanceof JLabel){
			tipo = LABEL;
		}
		
		if(c instanceof JButton){
			tipo = BUTTON;
		}
		
		thr = new Thread(this);
		thr.start();
	}
	
	@Override
	public void run() {
		for (int i = 0; i < textos.length; i++) {
			if(tipo == LABEL){
				((JLabel)c).setText(textos[i]);
				((JLabel)c).setBounds(((JLabel)c).getBounds().x, ((JLabel)c).getBounds().y, ((JLabel)c).getPreferredSize().width, ((JLabel)c).getPreferredSize().height);
				try {
					Thread.sleep(DELAY_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}	
}
