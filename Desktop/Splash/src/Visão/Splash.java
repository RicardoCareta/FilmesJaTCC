package Visão;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import com.sun.jna.platform.win32.WinDef.SCODEbyReference;

import Constantes.ConsProp;
import Controle.ControlTesteConexao;
import util.Images;
import util.Props;
import util.ScreenConfigs.ScreenSize;

@SuppressWarnings("serial")
public class Splash extends JFrame {

	private JPanel contentPane;
	private static JProgressBar progressBar = new JProgressBar();
	private static JLabel logoFilmesJa;

	private static Splash sp;

	private final JLabel lblNewLabel = new JLabel("");

	private static String[] args;
	
	private static ControlTesteConexao tc;
	
	public static void main(String[] args) {
		sp = new Splash();
		sp.setLocationRelativeTo(null);
		sp.setVisible(true);
		carregar();
	}

	public Splash() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		Images images = new Images();
		
		setUndecorated(true);
		lblNewLabel.setBounds(0, 0, 718, 254);
		lblNewLabel.setIcon(images.Image_FundoParaTudo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 269);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		progressBar.setBounds(0, 252, 683, 17);
		progressBar.setStringPainted(true);

		logoFilmesJa = new JLabel("New label");
		logoFilmesJa.setBounds(160, 35, 350, 180);
		
		try {
			
			OrganizarImagem(new URL("file:///" + images.p.IconeTelaPrincipal), logoFilmesJa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// logoFilmesJa.setIcon(arg0);
		contentPane.add(logoFilmesJa);

		// JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(Color.GREEN);
		contentPane.setLayout(null);
		contentPane.add(logoFilmesJa);
		contentPane.add(lblNewLabel);
		contentPane.add(progressBar);
	}

	public static void carregar() {
		for (int i = 0; i < 101; i++) {
			try {

				Thread.sleep(30);

				progressBar.setValue(i);
				
				if(i == 30){
					tc = new ControlTesteConexao();
					tc.teste();
				}
				
				if(i == 35){
					CarregarPropriedades();
				}
				
				if(i == 50){
					CarregarScreenSize();
				}
				
				if (i == 100) {
					JLogin.main(args);
					sp.setVisible(false);
				}

			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(null, "Não foi possível carregar" + e.getMessage());
				e.printStackTrace();
			}
		}

	}

	static void CarregarPropriedades(){
		Props p = new Props();
		args = new String[10];
		args[ConsProp.AR_EMAIL] = p.getPropValue(ConsProp.EMAIL);
		args[ConsProp.AR_PASSWORD] = p.getPropValue(ConsProp.PASSWORD);
	}
	
	static void CarregarScreenSize(){
		ScreenSize.LoadSize();
	}
	
	static void OrganizarImagem(URL url, JLabel lbl) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(url);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Image dimg = img.getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imgI = new ImageIcon(dimg);
		lbl.setIcon(imgI);
	}
}