package Visão;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controle.ControlConta;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class JBuscaCatalogo extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	private static final int WIDTH = 1250;
	private static final int HEIGHT = 700;
	
	private static final URL pathFundo = JBuscaCatalogo.class.getResource("/Vis\u00E3o/imagens/FundoParaTudo.jpg");
	
	private JLabel lblFundo;
	
	private ControlConta controlConta;
	
	public static void main(int IDCategoria, int IDConta) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JBuscaCatalogo frame = new JBuscaCatalogo(IDConta);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JBuscaCatalogo(int IDConta) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				JFulano.Visible(true);
			}
		});
		
		controlConta = new ControlConta();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, WIDTH, HEIGHT);
		setTitle("Olá, " + controlConta.getNome(IDConta));
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setOpaque(false);
		lblFundo = new JLabel();
		lblFundo.setBounds(0, 0, WIDTH, HEIGHT);
		contentPane.add(lblFundo);
		
		OrganizarImagem(lblFundo, new ImageIcon(pathFundo));
	}
	
	private  void OrganizarImagem(JLabel lbl, ImageIcon img) {
		Image dimg = img.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imgI = new ImageIcon(dimg);
		lbl.setIcon(imgI);
	}

}
