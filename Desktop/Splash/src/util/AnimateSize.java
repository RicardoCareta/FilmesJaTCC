package util;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class AnimateSize {
	
	public static final int WIDTH = 1;
	public static final int HEIGHT = 2;
	public static final int BOTH = 3;
	
	//private static final int DELAY = 40;
	
	private int SizePerCicle;
	private int Type;
	private int MaxSize;
	private int Delay;
	
	private Timer tmr;
	
	private Component c;
	
	public AnimateSize(Component component, int SizePerCicle, int Type, int MaxSize, int Delay){
		this.c = component;
		this.SizePerCicle = SizePerCicle;
		this.Type = Type;
		this.MaxSize = MaxSize;
		this.Delay = Delay;
	}
	
	public void Start(){
		tmr = new Timer(Delay, null);
		if(Type == WIDTH){
			if(SizePerCicle > 0){
				tmr.addActionListener(WidthUp);
			}else{
				tmr.addActionListener(WidthDown);
			}
			
		} else if(Type == HEIGHT){
			tmr.addActionListener(Height);
		} else if(Type == BOTH){
			tmr.addActionListener(Both);
		}
		
		tmr.setRepeats(true);
		tmr.setCoalesce(true);
		tmr.setInitialDelay(0);
		tmr.start();
	}
	
	ActionListener WidthUp = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			c.setSize(c.getSize().width + SizePerCicle, c.getSize().height);
			c.setLocation(c.getLocation().x - SizePerCicle, c.getLocation().y);
			if(c.getSize().width >= MaxSize){
				tmr.stop();
			}
			
		}
	};
	
	ActionListener WidthDown = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			c.setSize(c.getSize().width + SizePerCicle, c.getSize().height);
			c.setLocation(c.getLocation().x - SizePerCicle, c.getLocation().y);
			if(c.getSize().width <= MaxSize){
				tmr.stop();
			}
			
		}
	};
	
	ActionListener Height = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			c.setSize(c.getSize().width, c.getSize().height + SizePerCicle);
			if(c.getSize().height >= MaxSize){
				tmr.stop();
			}
		}
	};
	
	ActionListener Both = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			c.setSize(c.getSize().width + SizePerCicle, c.getSize().height + SizePerCicle);
			if(c.getSize().width >= MaxSize && c.getSize().height >= MaxSize){
				tmr.stop();
			}
		}
	};
}
