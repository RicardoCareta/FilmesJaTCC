package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class JMenuConfig extends JDialog {

	public JPanel contentPanel = new JPanel();
	private JButton btnDvidasFrequentes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JMenuConfig dialog = new JMenuConfig();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JMenuConfig() {
		setBounds(100, 100, 450, 365);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		//getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("Op\u00E7\u00F5es de Configurações:");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Arial", Font.BOLD, 18));
		label.setBounds(298, 6, label.getPreferredSize().width, label.getPreferredSize().height);
		contentPanel.add(label);
		
		JLabel lblSite = new JLabel("Site:");
		lblSite.setVisible(false);
		lblSite.setForeground(Color.BLACK);
		lblSite.setFont(new Font("Arial", Font.BOLD, 17));
		lblSite.setBounds(63, 74, 74, 22);
		contentPanel.add(lblSite);
		
		JButton btnImagens = new JButton("Imagens Site");
		btnImagens.setFont(new Font("Arial", Font.BOLD, 16));
		btnImagens.setBounds(227, 109, 150, 85);
		btnImagens.addActionListener(new  ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanel.setVisible(false);
				JImagemSite.main("T");
			}
		});
		contentPanel.add(btnImagens);
		
		btnDvidasFrequentes = new JButton("D\u00FAvidas Site");
		btnDvidasFrequentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPanel.setVisible(false);
				JControleAdministrador.escolha.setVisible(true);
			}
		});
		btnDvidasFrequentes.setFont(new Font("Arial", Font.BOLD, 16));
		btnDvidasFrequentes.setBounds(435, 109, 150, 85);
		contentPanel.add(btnDvidasFrequentes);
		
		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JControleAdministrador.RemoveFirstPanel();
			}
		});
		lblX.setBounds(834, 0, 24, 18);
		contentPanel.add(lblX);
		
		JButton btnVoltar = new JButton("");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanel.setVisible(false);
			}
		});
		btnVoltar.setIcon(new ImageIcon(JControleAdministrador.class.getResource("/view/imagens/proximo.png")));
		btnVoltar.setFont(new Font("Arial", Font.BOLD, 16));
		btnVoltar.setBounds(435, 288, 150, 86);
		contentPanel.add(btnVoltar);
		
		JButton btnAtalhos = new JButton("Atalhos");
		btnAtalhos.setVisible(false);
		btnAtalhos.setFont(new Font("Arial", Font.BOLD, 16));
		btnAtalhos.setBounds(227, 289, 150, 85);
		btnAtalhos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPanel.setVisible(false);
				JAtalhos.main("T");
			}
		});
		contentPanel.add(btnAtalhos);
	}
}
	
