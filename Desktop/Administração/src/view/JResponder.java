package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.border.LineBorder;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import util.ImgUtils;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JResponder extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtEmail;
	private JLabel lblNome;
	private JLabel lblEmail;
	private JLabel lblMensagem;
	private JTextArea txtMensagem;
	private JButton btnSair;
	private JButton btnEnviar;

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String email, String nome, String Mensagem) {
		try {
			JResponder dialog = new JResponder(email, nome, Mensagem);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JResponder(String email, String nome, String Mensagem) {
		setTitle("Responder Email");
		
		ImgUtils img = new ImgUtils();
		setBounds(100, 100, 899, 651);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblNome = new JLabel("Nome:");
		lblNome.setForeground(Color.BLACK);
		lblNome.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNome.setBounds(201, 81, lblNome.getPreferredSize().width, lblNome.getPreferredSize().height);
		contentPanel.add(lblNome);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 15));
		lblEmail.setBounds(203, 129, lblEmail.getPreferredSize().width, lblEmail.getPreferredSize().height);
		contentPanel.add(lblEmail);
		
		lblMensagem = new JLabel("Mensagem:");
		lblMensagem.setForeground(Color.BLACK);
		lblMensagem.setFont(new Font("Arial", Font.PLAIN, 15));
		lblMensagem.setBounds(168, 211, lblMensagem.getPreferredSize().width, lblMensagem.getPreferredSize().height);
		contentPanel.add(lblMensagem);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNome.setBounds(249, 76, 414, 28);
		txtNome.setText(nome);
		txtNome.setEditable(false);
		txtNome.setBackground(Color.LIGHT_GRAY);
		contentPanel.add(txtNome);
		txtNome.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 15));
		txtEmail.setColumns(10);
		txtEmail.setBounds(249, 124, 414, 28);
		txtEmail.setText(email);
		txtEmail.setEditable(false);
		txtEmail.setBackground(Color.LIGHT_GRAY);
		contentPanel.add(txtEmail);
		
		txtMensagem = new JTextArea();
		txtMensagem.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtMensagem.setBounds(249, 209, 414, 227);
		txtMensagem.setWrapStyleWord(true);
		txtMensagem.setLineWrap(true);
		
		txtMensagem.setText("Escreva aqui \n -----------------------------------------------------------------------------------------------------\n"+Mensagem);
		
		contentPanel.add(txtMensagem);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnEnviar.setBounds(491, 558, 89, 28);
		btnEnviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Enviar();
			}
		});
		contentPanel.add(btnEnviar);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sair();
			}
		});
		btnSair.setFont(new Font("Arial", Font.PLAIN, 15));
		btnSair.setBounds(373, 558, 89, 28);
		contentPanel.add(btnSair);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Arial", Font.PLAIN, 12));
		label.setIcon(new ImageIcon(JInserirAtores.class.getResource("/view/imagens/FundoParaTudo.jpg")));
		label.setBounds(0, 0, 1200, 704);
		
		img.OrganizarImagem(label, new ImageIcon(JInserirAtores.class.getResource("/view/imagens/FundoParaTudo.jpg")), false);
		
		//contentPanel.add(label);
		
		setModal(true);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		
		txtMensagem.requestFocus();
	}
	
	private void Sair(){
		this.dispose();
	}
	
	private void Enviar(){
		SimpleEmail email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		try {
			email.addTo(txtEmail.getText());
			email.setFrom("ricardoluis199@gmail.com");
			email.setAuthentication("ricardoluis199@gmail.com", "careta10");
			email.setStartTLSEnabled(true);
			email.setSmtpPort(587);
			
			email.setSubject("FilmesJá - Resposta");
			email.setMsg(txtMensagem.getText());
			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
