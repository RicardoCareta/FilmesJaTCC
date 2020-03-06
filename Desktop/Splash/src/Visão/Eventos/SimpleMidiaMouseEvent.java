package Visão.Eventos;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import Constantes.ConsPreMidia;
import Constantes.ConsProp;
import Controle.ControlIdioma;
import Visão.JPreMidia;
import util.Props;

public class SimpleMidiaMouseEvent implements MouseListener{

	private Border simpleLine;

	private ControlIdioma controlIdioma;
	
	private Props prop;

	private int IDConta;
	
	public SimpleMidiaMouseEvent(int IDConta){
		simpleLine = BorderFactory.createLineBorder(Color.white, 3);
		prop = new Props();
		controlIdioma = new ControlIdioma();
		this.IDConta = IDConta;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//Abrir perfil da midia, vo ter q programar, aff, mó role fazer isso
		//E to fazendo agr
		int idIdi = controlIdioma.getID(prop.getPropValue(ConsProp.IDIOMA));
		int[] args = new int[10];
		args[ConsPreMidia.IDCAT] = Integer.valueOf(e.getComponent().getName());
		args[ConsPreMidia.IDIDI] = idIdi;
		args[ConsPreMidia.IDCONTA] = IDConta;
		JPreMidia.main(args);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel lbl = (JLabel)e.getComponent();
		lbl.setBorder(simpleLine);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel lbl = (JLabel)e.getComponent();
		lbl.setBorder(null);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}