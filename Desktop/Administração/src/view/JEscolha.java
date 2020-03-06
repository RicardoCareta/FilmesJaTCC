package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JEscolha extends JPanel {
	private JLabel lblX;

	private JPanel pnl;
	
	public JEscolha() {
		setLayout(null);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Arial", Font.BOLD, 16));
		btnModificar.setBounds(310, 64, 150, 85);
		btnModificar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDuvidasFrequentesVisual.main(null);
			}
		});
		add(btnModificar);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.setFont(new Font("Arial", Font.BOLD, 16));
		btnInserir.setBounds(97, 64, 150, 85);
		btnInserir.addActionListener(new  ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDuvidasFrequentes.main(null);
			}
		});
		add(btnInserir);
		
		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pnl.setVisible(false);
				JControleAdministrador.GoBackPanel(JControleAdministrador.PanelConfig);
			}
		});
		lblX.setBounds(534, 0, 24, 18);
		add(lblX);

		pnl = this;
	}
}
