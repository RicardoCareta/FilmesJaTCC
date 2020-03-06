package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.Font;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import Enum.EnumPreguica;

import java.awt.Component;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JMenuInserir extends JDialog {

	public JPanel contentPanel = new JPanel();
	private JLabel lblX;
	private JButton btnAbrirCargo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JMenuInserir dialog = new JMenuInserir(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public JMenuInserir(boolean isInserir) {
		setBounds(100, 100, 450, 371);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPanel.setSize(450, 371);
		//
	
		//contentPanel.setBounds(0, 0, 450, 371);
		//getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		String titulo = "Op\u00E7\u00F5es de Inser\u00E7\u00E3o:";
		
		if (!isInserir) {
			titulo = "Opções de Modificação:";
		}
		
		JLabel lblOpcoes = new JLabel(titulo);
		lblOpcoes.setForeground(Color.BLACK);
		lblOpcoes.setFont(new Font("Arial", Font.BOLD, 18));
		lblOpcoes.setBounds(352, 12, lblOpcoes.getPreferredSize().width, lblOpcoes.getPreferredSize().height);
		contentPanel.add(lblOpcoes);
		
		btnAbrirCargo = new JButton("Cargo");
		btnAbrirCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanel.setVisible(false);
				
				if (isInserir) {
					JInserirAtores.main(null, EnumPreguica.Cargo.get());	
				}else{
					JEditar.main(null, EnumPreguica.Cargo.get());
				}
				
				contentPanel.setVisible(true);
			}
		});
		btnAbrirCargo.setFont(new Font("Arial", Font.BOLD, 16));
		btnAbrirCargo.setBounds(56, 97, 150, 85);
		contentPanel.add(btnAbrirCargo);
		
		JButton btnCatlogo = new JButton("Cat\u00E1logo");
		btnCatlogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanel.setVisible(false);
				if (isInserir) {
					JCatalogo.main(null);	
				}else{
					JVisualCatalogo.main(null);
				}
				contentPanel.setVisible(true);
			}
		});
		btnCatlogo.setFont(new Font("Arial", Font.BOLD, 16));
		btnCatlogo.setBounds(250, 97, 150, 85);
		contentPanel.add(btnCatlogo);
		
		JButton btnCategoria = new JButton("Categoria");
		btnCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanel.setVisible(false);
				if (isInserir) {
					JInserirAtores.main(null, EnumPreguica.Categoria.get());	
				}else{
					JEditar.main(null, EnumPreguica.Categoria.get());
				}
				contentPanel.setVisible(true);
			}
		});
		btnCategoria.setFont(new Font("Arial", Font.BOLD, 16));
		btnCategoria.setBounds(250, 269, 150, 86);
		contentPanel.add(btnCategoria);
		
		JButton btnElenco = new JButton("Elenco");
		btnElenco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanel.setVisible(false);
				if (isInserir) {
					JInserirAtores.main(null, EnumPreguica.Elenco.get());	
				}else{
					JEditar.main(null, EnumPreguica.Elenco.get());
				}
				contentPanel.setVisible(true);
			}
		});
		btnElenco.setFont(new Font("Arial", Font.BOLD, 16));
		btnElenco.setBounds(445, 97, 150, 85);
		contentPanel.add(btnElenco);
		
		JButton btnGnero = new JButton("G\u00EAnero");
		btnGnero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanel.setVisible(false);
				if (isInserir) {
					JInserirAtores.main(null, EnumPreguica.Genero.get());	
				}else{
					JEditar.main(null, EnumPreguica.Genero.get());
				}
				contentPanel.setVisible(true);
			}
		});
		btnGnero.setFont(new Font("Arial", Font.BOLD, 16));
		btnGnero.setBounds(642, 97, 150, 85);
		contentPanel.add(btnGnero);
		
		JButton btnIdioma = new JButton("Idioma");
		btnIdioma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanel.setVisible(false);
				if (isInserir) {
					JInserirAtores.main(null, EnumPreguica.Idioma.get());	
				}else{
					JEditar.main(null, EnumPreguica.Idioma.get());
				}
				contentPanel.setVisible(true);
			}
		});
		btnIdioma.setFont(new Font("Arial", Font.BOLD, 16));
		btnIdioma.setBounds(56, 270, 150, 85);
		contentPanel.add(btnIdioma);
		
		JButton btnPas = new JButton("Pa\u00EDs");
		btnPas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanel.setVisible(false);
				if (isInserir) {
					JInserirAtores.main(null, EnumPreguica.Pais.get());	
				}else{
					JEditar.main(null, EnumPreguica.Pais.get());
				}
				contentPanel.setVisible(true);
			}
		});
		btnPas.setFont(new Font("Arial", Font.BOLD, 16));
		btnPas.setBounds(445, 269, 150, 86);
		contentPanel.add(btnPas);
		
		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JControleAdministrador.RemoveFirstPanel();
			}
		});
		lblX.setBounds(822, 0, 24, 18);
		contentPanel.add(lblX);
		
		JButton btnVoltar = new JButton("");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanel.setVisible(false);
			}
		});
		btnVoltar.setIcon(new ImageIcon(JControleAdministrador.class.getResource("/view/imagens/proximo.png")));
		btnVoltar.setFont(new Font("Arial", Font.BOLD, 16));
		btnVoltar.setBounds(642, 269, 150, 86);
		contentPanel.add(btnVoltar);
		contentPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblOpcoes}));
	}
}
