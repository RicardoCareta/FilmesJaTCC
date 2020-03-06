package Visão.Eventos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import Constantes.ConsAtivo;
import Controle.ControlFavorito;

public class SimpleFavoritoMouseEvent implements MouseListener{

	private JLabel lblRemover;
	
	private ControlFavorito controlFavorito;
	
	private int IDConta;
	
	private boolean enter = false;
	
	public SimpleFavoritoMouseEvent(int ID) {
		this.IDConta = ID;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel lbl = (JLabel)e.getComponent();
		
		lblRemover = new JLabel("X");
		lblRemover.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblRemover.setBounds(lbl.getBounds().width - lblRemover.getPreferredSize().width - 10, lblRemover.getPreferredSize().height / 4, lblRemover.getPreferredSize().width, lblRemover.getPreferredSize().height);
		lblRemover.setOpaque(true);
		lblRemover.setBackground(Color.WHITE);
		//setFont(new Font("Tahoma", Font.PLAIN, 47));
		lblRemover.setName(lbl.getName());
		lblRemover.addMouseListener(ClickRemover);
	//	lblRemover.addActionListener(new ActionListener);
		lbl.add(lblRemover);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel lbl = (JLabel)e.getComponent();
		if(!enter){
			lbl.removeAll();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	MouseListener ClickRemover = new MouseListener() {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel lbl = (JLabel)e.getComponent();
			System.out.println("Chego");
			controlFavorito = new ControlFavorito();
			controlFavorito.Desativar(Integer.parseInt(lbl.getName()), IDConta, ConsAtivo.DESATIVO);
		}
		@Override
		public void mouseEntered(MouseEvent e){
			enter = true;
		}
		@Override
		public void mouseExited(MouseEvent e) {
			enter = false;
		}
		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
	};
	
}
