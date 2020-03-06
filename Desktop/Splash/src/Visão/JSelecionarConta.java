package Visão;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.jna.platform.unix.X11.Screen;

import Controle.ControlConta;
import Thread.LoadFulano;
import Thread.LoadScreen;
import util.ImageSizes;
import util.Biblia.HTTPRequestBiblia;
import util.Biblia.SiglaLivros;
import util.ScreenConfigs.ScreenSize;

import java.awt.event.WindowAdapter;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class JSelecionarConta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JPanel pnlMain;
	public static boolean startLoad;

	/**
	 * Launch the application.
	 */
	
	private URL pathFundo = JSelecionarConta.class.getResource("/Vis\u00E3o/imagens/FundoParaTudo.jpg");
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 448;
	private static final int BORDER_DEPTH = 5;
	
	private static final int MAXCONTA = 4;
	
	private JLabel lblFundo;
	
	private List<Component> listaContas;
	
	private ControlConta controlConta;
	
	private int ID;
	
	private JSelecionarConta jConta;
	
	public static void main(int ID) {
		try {
			JSelecionarConta dialog = new JSelecionarConta(ID);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	
	public static JPanel getMainPainel(){
		return pnlMain;
	}
	/**
	 * Create the dialog.
	 */
	public JSelecionarConta(int ID) {
		
		jConta = this;
		setModal(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {	
				JLogin.Visible(true);
			}
		});
		
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				ImageSizes.ArrumarLocation(contentPanel, contentPanel.getWidth(), contentPanel.getHeight());
			}
		});
		
		this.ID = ID;
		setResizable(false);
		setBounds(100, 100, WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		LoadContas();
		
		
		
		
		JLabel lblNewLabel = new JLabel("Equipe que tira 10 (AGLRS)");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(429, 304, lblNewLabel.getPreferredSize().width, lblNewLabel.getPreferredSize().height);
		contentPanel.add(lblNewLabel);
		
		txtVersiculoRandom = new JTextArea();
		txtVersiculoRandom.setForeground(Color.WHITE);
		txtVersiculoRandom.setText("teste");
		txtVersiculoRandom.setOpaque(false);
		txtVersiculoRandom.setBackground(Color.BLACK);
		txtVersiculoRandom.setEditable(false);
		txtVersiculoRandom.setBounds(425, 330, 349, 79);
		txtVersiculoRandom.setWrapStyleWord(true);
		txtVersiculoRandom.setLineWrap(true);
		contentPanel.add(txtVersiculoRandom);
		
		lblAdd = new JLabel("+");
		lblAdd.setForeground(Color.WHITE);
		lblAdd.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblAdd.setBounds(748, 297, 26, 32);
		lblAdd.addMouseListener(clickAdd);
		contentPanel.add(lblAdd);
		
		
		lblFundo = new JLabel("New label");
		lblFundo.setBounds(0, 0, WIDTH, HEIGHT);
		//OrganizarImagem(pathFundo, fundoLabel);
		lblFundo.setIcon(new ImageIcon(JSelecionarConta.class.getResource("/Vis\u00E3o/imagens/FundoParaTudo.jpg")));
		contentPanel.add(lblFundo);
		
		OrganizarImagem(pathFundo, lblFundo);
		
		JLabel lblTeste = new JLabel("Teste[");
		lblTeste.setForeground(Color.WHITE);
		lblTeste.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblTeste.setBounds(185, 199, 139, 57);
		contentPanel.add(lblTeste);
		
		pnlMain = contentPanel;	
		
		RandomVersiculo();
	}
	
	//CustomStaticVoids
	private void OrganizarImagem(URL pathIcone2, JLabel lbl) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(pathIcone2);
		} catch (Exception e) {
			// TODO: handle exception
		}

		Image dimg = img.getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imgI = new ImageIcon(dimg);
		lbl.setIcon(imgI);
		
	}
	
	private  void OrganizarImagem(JLabel lbl, ImageIcon img) {
		Image dimg = img.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imgI = new ImageIcon(dimg);
		lbl.setIcon(imgI);
	}
	
	private void LoadContas(){
		listaContas = new ArrayList<>();
		
		int count = 0;
		int posX = 60;
		int posY = 60;
		int width = 120;
		int height = 120;
		controlConta = new ControlConta();
		for (Integer i : controlConta.getID(ID)) {
			
			JLabel lblFundo = new JLabel();
			
			lblFundo.setName(String.valueOf(i));
			lblFundo.setBounds(posX + ((width + posX) * count), posY - count * 5, width, height);	
			
			OrganizarImagem(lblFundo, controlConta.getImageIcon(i));
			lblFundo.addMouseListener(clickConta);
			contentPanel.add(lblFundo);
			
			JLabel lblNome = new JLabel(controlConta.getNome(i));
			lblNome.setForeground(Color.WHITE);
			lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNome.setName(String.valueOf(i));
			lblNome.setBounds(posX + ((width + posX) * count) + 30, posY + 125, lblNome.getPreferredSize().width, lblNome.getPreferredSize().height);
			//lblNome.addMouseListener(clickConta);
			contentPanel.add(lblNome);
			
			count++;
			
		}
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
		
		txtVersiculoRandom.setText(ht.getVersiculo(siglaLivro, numeroLivro, nomeLivro, numeroCapitulo));
		
		startLoad = true;
		
	}
	
	//TODO: CLICK_CONTA
	MouseListener clickConta = new MouseListener() {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			jConta.dispose();
			
			int IDConta = Integer.parseInt(e.getComponent().getName());
			
			controlConta.ChangeLine(IDConta, ControlConta.LINE_ON);
			
			LoadScreen ls = new LoadScreen();
			ls.Start();
			
			LoadFulano lf = new LoadFulano();
			lf.Start(IDConta);
			//t.start();
		//	JFulano.main(Integer.parseInt(e.getComponent().getName()));
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			JLabel lbl = (JLabel)e.getComponent();
			lbl.setBorder(BorderFactory.createLineBorder(Color.WHITE, BORDER_DEPTH));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JLabel lbl = (JLabel)e.getComponent();
			lbl.setBorder(null);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
	
	MouseListener clickAdd = new MouseListener() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			
			if(controlConta.getContaCount(ID) >= MAXCONTA){
				MessagemBoxCustom.ShowMensagem("Opa, já está sendo usado todas as contas", MessagemBoxCustom.DONT_CLOSE);
				return;
			}
			
			JCriarConta.main(ID);
			jConta.dispose();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	private JLabel lblAdd;
	private static JTextArea txtVersiculoRandom;
}
