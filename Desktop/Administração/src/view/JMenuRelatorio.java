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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JMenuRelatorio extends JDialog {

	public JPanel contentPanel = new JPanel();
	private JLabel label;
	private JLabel lblUsurios;
	private JButton btnQuantidadeDeCadastrados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JMenuRelatorio dialog = new JMenuRelatorio();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JMenuRelatorio() {
		setBounds(100, 100, 846, 497);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		//getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		label = new JLabel("Op\u00E7\u00F5es de Relatório:");
		label.setFont(new Font("Arial", Font.BOLD, 18));
		label.setBounds(314, 12, label.getPreferredSize().width, label.getPreferredSize().height);
		label.setForeground(Color.BLACK);
		
		contentPanel.add(label);
		
		btnQuantidadeDeCadastrados = new JButton("<html><center>Quantidade de <br>Cadastrados</center></html>");
		btnQuantidadeDeCadastrados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanel.setVisible(false);
				JGraficoUsuario.main(null);
				contentPanel.setVisible(true);
			}
		});
		btnQuantidadeDeCadastrados.setFont(new Font("Arial", Font.BOLD, 16));
		btnQuantidadeDeCadastrados.setBounds(227, 109, 150, 85);
		contentPanel.add(btnQuantidadeDeCadastrados);
		
		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JControleAdministrador.RemoveFirstPanel();
			}
		});
		lblX.setBounds(822, 0, 24, 18);
		contentPanel.add(lblX);
		
		lblUsurios = new JLabel("Usu\u00E1rios:");
		lblUsurios.setFont(new Font("Arial", Font.BOLD, 17));
		lblUsurios.setBounds(63, 74, lblUsurios.getPreferredSize().width, lblUsurios.getPreferredSize().height);
		//contentPanel.add(lblUsurios);
		
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
		
		JButton btnGanhos = new JButton("Ganhos");
		btnGanhos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JGraficoGanhos.main(null);
			}
		});
		btnGanhos.setFont(new Font("Arial", Font.BOLD, 16));
		btnGanhos.setBounds(435, 109, 150, 85);
		contentPanel.add(btnGanhos);
		
		JButton btnFilmes = new JButton("Visualiza\u00E7\u00F5es");
		btnFilmes.setFont(new Font("Arial", Font.BOLD, 16));
		btnFilmes.setBounds(227, 289, 150, 85);
		btnFilmes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JGraficoMaisAssistidos.main(null);
			}
		});
		contentPanel.add(btnFilmes);
	}
}
