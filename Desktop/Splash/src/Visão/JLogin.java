package Visão;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Constantes.ConsProp;
import Visão.Eventos.eventoLogin;
import util.Images;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class JLogin extends JFrame {
	
	private JPanel contentPane;
	public static JTextField txtUsuario;
	
	public static JPasswordField txtSenha;
	
	private JLabel lblUsuario;
	private JLabel lblSenha;
	private JLabel lblBemVindo;
	private JLabel lblCadastro;
	private JLabel lblShow;
	
	public static JLabel lblEsqueci;
	
	public static JButton btnEntrar;
	public static JLogin frame;
	
	private static JLogin jLogin;
	public static JCheckBox chckbxLembrarSenha;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new JLogin(args);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void Visible(boolean visible){
		frame.setVisible(visible);
	}

	/**
	 * Create the frame.
	 */
	public JLogin(String[] args) {
		
		Images images = new Images();
		
		jLogin = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 327, 260);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsuario = new JTextField();
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == 10){
					eventoLogin.EntrarClick();
				}
				if (arg0.getKeyCode() == 27 ) { 
					System.exit(0);
				}
			}
		});
		txtUsuario.setBounds(96, 94, 167, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		lblUsuario = new JLabel("Usu\u00E1rio:");
		lblUsuario.setForeground(Color.YELLOW);
		lblUsuario.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblUsuario.setBounds(17, 95, 69, 14);
		lblUsuario.setName("lblUsuario");
		contentPane.add(lblUsuario);
		
		lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(Color.YELLOW);
		lblSenha.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblSenha.setBounds(30, 142, 56, 14);
		contentPane.add(lblSenha);
		
	    lblBemVindo = new JLabel("Bem vindo!");
	    lblBemVindo.setForeground(Color.YELLOW);
		lblBemVindo.setFont(new Font("Arial", Font.BOLD, 27));
		lblBemVindo.setBounds(96, 24, lblBemVindo.getPreferredSize().width, lblBemVindo.getPreferredSize().height);
		contentPane.add(lblBemVindo);
		
	    btnEntrar = new JButton("Entrar");
	    btnEntrar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				eventoLogin.EntrarClick();
			}
	    });
	    //btnEntrar.addActionListener(evento);
		btnEntrar.setBounds(17, 172, 286, 23);
		contentPane.add(btnEntrar);
		
		lblCadastro = new JLabel("Cadastre-se");
		lblCadastro.setForeground(Color.YELLOW);
		lblCadastro.setBounds(234, 206, 83, 14);
		lblCadastro.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		eventoLogin.LinkCadastrar();
	    	}
	    });
		contentPane.add(lblCadastro);
		
	    lblEsqueci = new JLabel("Esqueceu sua senha?");
	    lblEsqueci.setForeground(Color.YELLOW);
	    lblEsqueci.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		eventoLogin.LinkEsqueci();
	    	}
	    });
		lblEsqueci.setBounds(17, 206, 142, 14);
		contentPane.add(lblEsqueci);
		
		lblShow = new JLabel("New label");
		//lblShow.setIcon();
		lblShow.setForeground(new Color(255, 255, 255));
		lblShow.setVisible(false);
		lblShow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				eventoLogin.ExibirSenhaEntered();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				eventoLogin.OcultarSenhaExited();
			}
		});
		lblShow.setBounds(261, 143, 31, 19);
		contentPane.add(lblShow);
		
		OrganizarImagem(lblShow,new ImageIcon(images.p.CadeadoAberto));
		
		txtSenha = new JPasswordField();
		txtSenha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				lblShow.setVisible(true);
			}
			@Override
			public void focusLost(FocusEvent e) {
				lblShow.setVisible(false);
			}
		});
		
		txtSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == 10){
					eventoLogin.EntrarClick();
				}

				if (arg0.getKeyCode() == 27 ) { 
					System.exit(0);
				}
			}
		});
		txtSenha.setBounds(96, 142, 167, 20);
		contentPane.add(txtSenha);
		
		chckbxLembrarSenha = new JCheckBox("Lembrar senha?");
		chckbxLembrarSenha.setForeground(new Color(255, 255, 255));
		chckbxLembrarSenha.setBackground(new Color(0, 0, 0));
		chckbxLembrarSenha.setBounds(17, 227, chckbxLembrarSenha.getPreferredSize().width, chckbxLembrarSenha.getPreferredSize().height);
		contentPane.add(chckbxLembrarSenha);
		
		String email = args[ConsProp.AR_EMAIL];
		String pass = args[ConsProp.AR_PASSWORD];
		
		if(email != null){
			txtUsuario.setText(args[ConsProp.AR_EMAIL]);
		}
		if(pass != null){
			txtSenha.setText(args[ConsProp.AR_PASSWORD]);
			chckbxLembrarSenha.setSelected(true);
		}
	}
	private void OrganizarImagem(JLabel lbl, ImageIcon img) {
		Image dimg = img.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imgI = new ImageIcon(dimg);
		lbl.setIcon(imgI);
	}
	public static JLogin getJLogin(){
		return jLogin;
	}
}
