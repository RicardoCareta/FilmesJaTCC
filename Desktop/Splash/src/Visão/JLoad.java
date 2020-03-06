package Visão;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import util.AnimateText;
import util.Biblia.HTTPRequestBiblia;
import util.Biblia.SiglaLivros;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

@SuppressWarnings("serial")
public class JLoad extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	
	private static JLoad jLoad;
	private static JLabel lblCarregando;
	
	private static Timer tmr;
	
	private static boolean load = false;
	//private static JLabel lblVersiculo;
	
	private static JTextArea lblVersiculo;
	
	//private static boolean startLoad = true;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JLoad frame = new JLoad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void Show(){
		JLoad frame = new JLoad();
		frame.setVisible(true);

		jLoad = frame;
		
		//RandomVersiculo();
		
		/*while(!startLoad){
			
		}*/
		
		
		tmr = new Timer(600, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AnimateText anime = new AnimateText();
				String textos[] = new String[4];
				textos[0] = "Carregando";
				textos[1] = "Carregando.";
				textos[2] = "Carregando..";
				textos[3] = "Carregando...";
				anime.Start(textos, lblCarregando);
			}
		});
		
		tmr.setRepeats(true);
		tmr.start();
		
		AnimateText anime = new AnimateText();
		String textos[] = new String[4];
		textos[0] = "Carregando";
		textos[1] = "Carregando.";
		textos[2] = "Carregando..";
		textos[3] = "Carregando...";
		anime.Start(textos, lblCarregando);
		
		
		load = true;
	}
	
	public static void RandomVersiculo(){
		Random r = new Random();
		int numero = r.nextInt(SiglaLivros.values().length);
		HTTPRequestBiblia ht = new HTTPRequestBiblia();
		
		String siglaLivro = SiglaLivros.values()[numero].nomeSigla;
		int numeroLivro = SiglaLivros.values()[numero].valorSigla;
		String nomeLivro = SiglaLivros.values()[numero].nomeLivro;
		
		int numeroCapitulo = r.nextInt(SiglaLivros.values()[numero].qntCapitulos);
		numeroCapitulo++;
		
		lblVersiculo.setText(ht.getVersiculo(siglaLivro, numeroLivro, nomeLivro, numeroCapitulo));
		
		lblVersiculo.setBounds(0, jLoad.getHeight() / 2, jLoad.getWidth(), 200);
		lblVersiculo.setWrapStyleWord(true);
		lblVersiculo.setLineWrap(true);
	//	startLoad = true;
		
	}
	
	public void Close(){
		tmr.stop();
		jLoad.dispose();
	}
	
	public static void Dispose(){
		tmr.stop();
		jLoad.dispose();
	}
	
	public boolean getLoad(){
		return load;
	}

	/**
	 * Create the frame.
	 */
	public JLoad() {
		setUndecorated(true);
		setResizable(false);	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCarregando = new JLabel("Carregando");
		lblCarregando.setForeground(new Color(255, 215, 0));
		lblCarregando.setFont(new Font("Arial", Font.BOLD, 20));
		lblCarregando.setBounds(163, 120, lblCarregando.getPreferredSize().width, lblCarregando.getPreferredSize().height);
		contentPane.add(lblCarregando);
		
		lblVersiculo = new JTextArea();
		lblVersiculo.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblVersiculo.setForeground(Color.WHITE);
		lblVersiculo.setBackground(Color.BLACK);
		lblVersiculo.setBounds(193, 202, lblVersiculo.getPreferredSize().width, lblVersiculo.getPreferredSize().height);
		lblVersiculo.setWrapStyleWord(true);
		lblVersiculo.setLineWrap(true);
		contentPane.add(lblVersiculo);
	}
}
