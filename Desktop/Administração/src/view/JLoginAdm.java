package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import view.Eventos.eventoLogin;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class JLoginAdm extends JFrame {
	
	private JPanel contentPane;
	public static JTextField txtUsuario;
	
	public static JPasswordField txtSenha;
	
	private JLabel lblUsuario;
	private JLabel lblSenha;
	private JLabel lblBemVindo;
	private JLabel lblShow;
	
	public static JLabel lblEsqueci;
	
	public static JButton btnEntrar;
	public static JLoginAdm frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new JLoginAdm();
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
	public JLoginAdm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 327, 260);
		contentPane = new JPanel();
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
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblUsuario.setBounds(17, 95, 69, 14);
		contentPane.add(lblUsuario);
		
		lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(Color.BLACK);
		lblSenha.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblSenha.setBounds(30, 142, 56, 14);
		contentPane.add(lblSenha);
		
	    lblBemVindo = new JLabel("Bem vindo!");
		lblBemVindo.setFont(new Font("Arial", Font.BOLD, 27));
		lblBemVindo.setBounds(71, 29, 206, 42);
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
		
	    lblEsqueci = new JLabel("Esqueceu sua senha?");
	    lblEsqueci.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		eventoLogin.LinkEsqueci();
	    	}
	    });
		lblEsqueci.setBounds(102, 206, 142, 14);
		contentPane.add(lblEsqueci);
		
		lblShow = new JLabel("New label");
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
		lblShow.setBounds(261, 144, 31, 14);
		contentPane.add(lblShow);
		
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
		txtSenha.setBounds(96, 141, 167, 20);
		contentPane.add(txtSenha);
	}
}
