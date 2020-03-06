package view.Eventos;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyJustNumber implements KeyListener{

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		String characteres = "0987654321";
		if (!characteres.contains(e.getKeyChar() + "")) {
			e.consume();
		}
	}
}
