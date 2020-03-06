package util;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class AnimatePosition implements Runnable{

	public static final int X = 1;
	public static final int Y = 2;
	public static final int BOTH = 3;
	
	private Thread thr;
	
	private Timer tmr;
	
	private int Type;
	private int Delay;
	private int PosPerCicle;
	private int MaxLocation;
	
	private Component c;
	
	public AnimatePosition(Component component, int Delay, int PosPerCicle, int Type, int MaxLocation) {
		this.c = component;
		this.Delay = Delay;
		this.PosPerCicle = PosPerCicle;
		this.Type = Type;
		this.MaxLocation = MaxLocation;
	}
	
	public void Start(){
		thr = new Thread(this);
		thr.start();
	}

	@Override
	public void run() {
		tmr = new Timer(Delay, null);
		if(Type == X){
			if(PosPerCicle > 0){
				tmr.addActionListener(XaDown);
			}else{
				tmr.addActionListener(XaUp);
			}
		}
		
		tmr.setRepeats(true);
		tmr.setCoalesce(true);
		tmr.setInitialDelay(0);
		tmr.start();
	}
	
	ActionListener XaUp = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			c.setLocation(c.getLocation().x + PosPerCicle, c.getLocation().y);
			if(c.getLocation().x <= MaxLocation){
				tmr.stop();
			}
		}
	};
	
	ActionListener XaDown = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			c.setLocation(c.getLocation().x + PosPerCicle, c.getLocation().y);
			if(c.getLocation().x >= MaxLocation){
				tmr.stop();
			}
		}
	};
}
