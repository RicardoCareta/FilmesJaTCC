package util;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class LinkLabel extends JLabel{
	
	private static final String CSS_COLOR_CLICK = "#440f56";
	
	private String text;
	
	public LinkLabel(String text){
		super(text);
		
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		enableEvents(MouseEvent.MOUSE_EVENT_MASK);
	}
	
	public void setText(String text){
		super.setText("<html><font color=\"#0000CF\"><u>"+text+"</u></font></html>");
		this.text = text;
	}
	
	public void setClickedLink(String text){
		//#440f56
		super.setText("<html><font color=\"" + CSS_COLOR_CLICK + "\"><u>"+text+"</u></font></html>");
		this.text = text;
	}
	
	public String getNormalText(){
		return text;
	}
	
	protected void processMouseEvent(MouseEvent e) {
		super.processMouseEvent(e);
		if(e.getID() == MouseEvent.MOUSE_CLICKED){
			fireActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, getNormalText()));
			setClickedLink(text);
		}
	}
	
	public void addActionListener(ActionListener listener){
		listenerList.add(ActionListener.class, listener);
	}
	
	public void removeActionListener(ActionListener listener){
		listenerList.remove(ActionListener.class, listener);
	}
	
	protected void fireActionPerformed(ActionEvent e){
		Object[] listeners = listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i++) {
			if(listeners[i] == ActionListener.class){
				ActionListener listener = (ActionListener)listeners[i+1];
				listener.actionPerformed(e);
			}
		}
	}
}
