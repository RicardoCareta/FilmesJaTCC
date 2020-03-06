package Visão;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Constantes.ConsAssistido;
import Controle.ControlHistorico;
import Thread.LoadPlayer;
import uk.co.caprica.vlcj.component.EmbeddedMediaListPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import util.Images;
import util.ImgUtils;
import util.ScreenConfigs.ScreenSize;
import util.Server.HTTPRequestStream;

public class JVideoPlayer {

	// constantes
	private static final String URL_KEY = "url";

	private static final int REMOVEHEIGHT = 70;

	private static final int PLAYER_WIDTH = ScreenSize.SCREEN_WIDTH;
	private static final int PLAYER_HEIGHT = ScreenSize.SCREEN_HEIGHT;
	
	private static final int FRAME_WIDTH = ScreenSize.SCREEN_WIDTH;
	private static final int FRAME_HEIGHT = ScreenSize.SCREEN_HEIGHT;
	
	private static final boolean OFFLINE = true;
	
	private static final int VOLUME_MIN = 0;
	private static final int VOLUME_MAX = 400;
	private static final int VOLUME_INIT = 200;
	
	// private obj
	private JFrame frame;
	
	private List<String> lstLegenda;

	private double m_CurrentTimer;
	private int m_MaxTimer;
	
	private JPanel pnlBackground;
	private JPanel pnlTimerBar;
	private JPanel contentPane;
	private JPanel pnlHoverDetect;

	private JLabel lblControlP;
	private JLabel lblTempo;
	private JLabel lblLegenda;
	
	private static JLabel lblSair;
	
	private JLabel lblFullScreen;

	private Timer tmr;
	
	private Timer tmrControls;

	private EmbeddedMediaListPlayerComponent mediaPlayer;

	private String pathJSON = System.getProperty("user.dir") + "\\TorrentServer\\returns.json";
	
	private int IDConta;
	private int IDCat;
	
	private int x = 0;
	
	private ControlHistorico controlHistorico;
	
	private JSlider volumeSlider;
	
	public static void Show(int IDCat, int ID) {
		new NativeDiscovery().discover();
		new JVideoPlayer(IDCat, ID);
	}

	public JVideoPlayer(int IDCat, int ID) {

		Images images = new Images();
		
		controlHistorico = new ControlHistorico();
		
		ImgUtils img = new ImgUtils();
		
		this.IDConta = ID;
		this.IDCat = IDCat;
		frame = new JFrame();
		frame.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setAlwaysOnTop (true);
		
		contentPane = new JPanel();
		contentPane.setLayout(null);

		mediaPlayer = new EmbeddedMediaListPlayerComponent();
		mediaPlayer.setSize(PLAYER_WIDTH, PLAYER_HEIGHT);
		mediaPlayer.setLocation(0, 0);
		mediaPlayer.getVideoSurface().addMouseListener(clickControl);
		
		pnlBackground = new JPanel();
		pnlBackground.setLayout(new BorderLayout());
		pnlBackground.setBackground(Color.DARK_GRAY);
		pnlBackground.setSize(ScreenSize.ConvertorSize(1280), ScreenSize.ConvertorSize(40));
		pnlBackground.setLocation(0, (frame.getBounds().height - pnlBackground.getSize().height) - REMOVEHEIGHT);
		pnlBackground.addMouseListener(l);

		lblControlP = new JLabel("Pause");
		lblControlP.setFont(new Font("Arial", Font.BOLD, ScreenSize.ConvertorSize(30)));
		lblControlP.setBounds((ScreenSize.FULANO_WIDTH / 2), (frame.getBounds().height - pnlBackground.getSize().height) - 27,
				ScreenSize.ConvertorSize(60), ScreenSize.ConvertorSize(60));
		//lblControlP.setIcon(new ImageIcon(JVideoPlayer.class.getResource("/Vis\u00E3o/imagens/pause-icon-7856.png")));
		lblControlP.addMouseListener(clickControl);
		//lblControlP.setBorder(new LineBorder(Color.WHITE, 3));
		
		lblTempo = new JLabel("");
		lblTempo.setFont(new Font("Arial", Font.BOLD, ScreenSize.ConvertorSize(13)));
		lblTempo.setBounds(ScreenSize.ConvertorSize(1180), (pnlBackground.getLocation().y + pnlBackground.getSize().height + lblTempo.getPreferredSize().height), lblTempo.getPreferredSize().width, lblTempo.getPreferredSize().height);
		
		lblLegenda = new JLabel("");
		lblLegenda.setFont(new Font("Arial", Font.PLAIN, 10));
		lblLegenda.setForeground(Color.YELLOW);
		lblLegenda.setBounds(frame.getBounds().width / 2, PLAYER_HEIGHT - (PLAYER_HEIGHT / 10), lblLegenda.getPreferredSize().width, lblLegenda.getPreferredSize().height);
		
		lblFullScreen = new JLabel("");
		lblFullScreen.setIcon(new ImageIcon("/Vis\u00E3o/imagens/fullscreenicon.png"));
		lblFullScreen.setBounds(frame.getBounds().width - 40, frame.getBounds().height - pnlBackground.getSize().height, 50, 50);
		lblFullScreen.setLayout(new BorderLayout());
		
		lblFullScreen.addMouseListener(clickFullScreen);
		//img.OrganizarImagem(50, 50, lblFullScreen, false);
		
		lblSair = new JLabel();
		//lblSair.setBounds(ScreenSize.ConvertorSize(PLAYER_WIDTH - ScreenSize.ConvertorSize(60)), 10, lblSair.getPreferredSize().width, lblSair.getPreferredSize().height);
		lblSair.setIcon(images.Image_BotaoVoltar);
	
		lblSair.setBounds(ScreenSize.ConvertorSize(1155), ScreenSize.ConvertorSize(10), 60, 60);
		//OrganizarImagem(lblSair, new ImageIcon(JVideoPlayer.class.getResource("/Vis\u00E3o/imagens/botao-quot-voltar_318-108687.png")));
		img.OrganizarImagem(lblSair, images.Image_BotaoVoltar,  false);
		lblSair.setLayout(new  BorderLayout());
		
		//System.out.println(lblSair.getLocation().getX());
		lblSair.addMouseListener(clickSair);
		lblSair.setForeground(Color.BLACK);
		lblSair.setFont(new Font("Arial", Font.BOLD, 50));
		
		pnlHoverDetect = new JPanel();
		pnlHoverDetect.setLayout(new BorderLayout());
		//pnlHoverDetect.setBackground(Color.BLACK);
		pnlHoverDetect.setSize(ScreenSize.ConvertorSize(1280), ScreenSize.ConvertorSize(40));
		pnlHoverDetect.setLocation(0, frame.getBounds().height - pnlBackground.getSize().height - REMOVEHEIGHT);
		pnlHoverDetect.setOpaque(true);
		// pnlHoverDetect.addMouseListener(l);

		pnlTimerBar = new JPanel();
		pnlTimerBar.setBackground(Color.YELLOW);

		pnlBackground.add(pnlTimerBar, BorderLayout.WEST);
		
		volumeSlider = new JSlider(JSlider.VERTICAL, VOLUME_MIN, VOLUME_MAX, VOLUME_INIT);
		
		volumeSlider.addChangeListener(changeVolume);
		volumeSlider.setLayout(new BorderLayout());
		volumeSlider.setLocation(ScreenSize.ConvertorSize(1200), (ScreenSize.ConvertorSize((700 / 2) - volumeSlider.getPreferredSize().height)));
		
		volumeSlider.setMajorTickSpacing(50);
		volumeSlider.setPaintTicks(true);
		
		volumeSlider.setSize(volumeSlider.getPreferredSize());
		
		volumeSlider.setBackground(Color.GRAY);

		contentPane.add(lblControlP);
		
	//	contentPane.add(pnlHoverDetect);
		contentPane.add(pnlBackground);
		contentPane.add(lblTempo);
		contentPane.add(volumeSlider);
		//contentPane.add(lblFullScreen);
		contentPane.add(lblLegenda);
		contentPane.add(lblSair);
	//	contentPane.add(pnlTimerBar, BorderLayout.WEST);
		
		contentPane.add(mediaPlayer);

		img.OrganizarImagem(lblControlP, images.Image_pauseIcon7856, true);
		img.OrganizarImagem(lblControlP, images.Image_IconPlay, true);
		img.OrganizarImagem(lblControlP, images.Image_pauseIcon7856, true);
		
		frame.setContentPane(contentPane);

		frame.requestFocus();
		frame.setVisible(true);

		
	//	String uhu = http.getPortRequest("magnet:?xt=urn:btih:4f5c2e464e5d9b21a47c537ce390f29b2726b124&dn=The.Avengers.Age.of.Ultron.%282015%29MajestykMovies.mkv&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fpublic.popcorn-tracker.org%3A6969");
		//System.out.println(uhu);
		
		//mediaPlayer.getMediaPlayer().playMedia(getTorrentFile());
		
		//mediaPlayer.getMediaPlayer().playMedia("C:\\Users\\Ricardo\\Desktop\\Filosofia.avi");
		//frame.setVisible(false);
		frame.addWindowListener(new WindowListener() {
			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				mediaPlayer.release();
				tmr.stop();
				controlHistorico.Inserir(0, ConsAssistido.ASSISTIDO, IDCat, ID, controlHistorico.getDataBD(), (int)m_CurrentTimer);
				frame.dispose();
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowOpened(WindowEvent e) {
			}
		});

		tmr = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateTimer();
			}
		});
		
		tmrControls = new Timer(10, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pnlTimerBar.setSize(x, pnlTimerBar.getSize().height);
			}
		});
		
	
		if(!OFFLINE){
			HTTPRequestStream http = new HTTPRequestStream();
			String valor = http.getPortRequest("magnet:?xt=urn:btih:08ada5a7a6183aae1e09d831df6748d566095a10&dn=Sintel&tr=udp%3A%2F%2Fexplodie.org%3A6969&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Ftracker.empire-js.us%3A1337&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Ftracker.opentrackr.org%3A1337&tr=wss%3A%2F%2Ftracker.btorrent.xyz&tr=wss%3A%2F%2Ftracker.fastcast.nz&tr=wss%3A%2F%2Ftracker.openwebtorrent.com&ws=https%3A%2F%2Fwebtorrent.io%2Ftorrents%2F&xs=https%3A%2F%2Fwebtorrent.io%2Ftorrents%2Fsintel.torrent");
			mediaPlayer.getMediaPlayer().playMedia(valor);
		}else{
			mediaPlayer.getMediaPlayer().playMedia("C:\\Users\\Ricardo\\Desktop\\Filosofia.avi");
		}
		
		///LoadPlayer lp = new LoadPlayer();
		//lp.Start(mediaPlayer, this);
		
		
		
		startTimer();
		
		//img.OrganizarImagem(lblFullScreen, false);
	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

	public void startTimer() {
		m_CurrentTimer = controlHistorico.getTime(IDCat, IDConta);
		
		m_MaxTimer = (int) (mediaPlayer.getMediaPlayer().getLength() / 1000);
		int removeTimer = (m_MaxTimer * 10) / 1000;
		mediaPlayer.getMediaPlayer().setTime(((int)m_CurrentTimer - removeTimer) * 1000);
		mediaPlayer.getMediaPlayer().setVolume(VOLUME_INIT);
		
		//mediaPlayer.getMediaPlayer().setSubTitleFile("C:\\Users\\Ricardo\\Desktop\\teste.srt");
		
		tmr.start();
		
		tmrControls.start();
	}
	
	private void UpdateTimer() {
		m_MaxTimer = (int) (mediaPlayer.getMediaPlayer().getLength() / 1000);
	
		m_CurrentTimer++;

		UpdateBarTimer();
		
		ConfigurationLegenda();
		MouseConfig();
	}
	
	DecimalFormat df = new DecimalFormat("#.00");
	private void UpdateBarTimer() {
		x = (pnlBackground.getSize().width * (int)m_CurrentTimer) / m_MaxTimer;

		double timeMinus = m_CurrentTimer / 60;
		int minuts = (int) timeMinus;
		double seconds = timeMinus - (int) minuts;
		seconds = seconds * 60;
		
		/*
		double maxtimeMinus = m_MaxTimer / 60;
		int maxminuts = (int) maxtimeMinus;
		double maxseconds = maxtimeMinus - (int) maxminuts;
		maxseconds = maxseconds * 60;
		*/
		
		
		lblTempo.setText(minuts + ":" + (int)seconds + "/" + m_MaxTimer);
		lblTempo.setSize(lblTempo.getPreferredSize());
		
		pnlTimerBar.setSize(x, pnlTimerBar.getSize().height);
	}
	
	private void Close(){
		mediaPlayer.release();
		tmr.stop();
		controlHistorico.Inserir(0, ConsAssistido.ASSISTIDO, IDCat, IDConta, controlHistorico.getDataBD(), (int)m_CurrentTimer);
		frame.dispose();
	}

	
	static void OrganizarImagem(URL pathIcone2, JLabel lbl) {
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

	private void OrganizarImagem(JLabel lbl, ImageIcon img) {
		Image dimg = img.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imgI = new ImageIcon(dimg);
		lbl.setIcon(imgI);
	}
	
	Point anterior = new Point(0, 0);
	int count = 0;
	private void MouseConfig(){
		Point atual = MouseInfo.getPointerInfo().getLocation();
		if(anterior.getX() != atual.getX() || anterior.getY() != atual.getY()){
			
			count = 0;
			pnlBackground.setVisible(true);
			pnlTimerBar.setVisible(true);
			lblTempo.setVisible(true);
			pnlHoverDetect.setVisible(true);
			
			volumeSlider.setVisible(true);
			
			lblControlP.setVisible(true);
			
			lblSair.setVisible(true);
		}
		else if (count >= 3){
			pnlBackground.setVisible(false);
			pnlTimerBar.setVisible(false);
			lblTempo.setVisible(false);
			
			volumeSlider.setVisible(false);
			
			pnlHoverDetect.setVisible(false);
			
			lblControlP.setVisible(false);
			
			lblSair.setVisible(false);
		}
		
		count++;
		anterior = MouseInfo.getPointerInfo().getLocation();
	}
	
	private Images images;
	
	private void MediaControl() {
		images = new Images();
		ImgUtils img = new ImgUtils();
		if(mediaPlayer.getMediaPlayer().isPlaying()){
			img.OrganizarImagem(lblControlP, images.Image_IconPlay, true);
			
			tmr.stop();
		}
		else
		{
			img.OrganizarImagem(lblControlP, images.Image_pauseIcon7856, true);
			tmr.start();
		}
		mediaPlayer.getMediaPlayer().pause();
	}
	
	private void ConfigurationLegenda(){
		
	}
	MouseListener clickSair = new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			Close();
		}
	};
	
	ChangeListener changeVolume = new ChangeListener() {
		
		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider)e.getSource();
			
			
			int volume = (int)source.getValue();
				
			mediaPlayer.getMediaPlayer().setVolume(volume);
			
		}
	};

	MouseListener clickFullScreen = new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("Entrou");
			mediaPlayer.getMediaPlayer().setFullScreen(true);
		}
	};
	
	MouseListener l = new MouseListener() {

		@Override
		public void mouseExited(MouseEvent e) {
			//pnlHoverDetect.setVisible(true);
			//pnlTimerBar.setVisible(false);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			//pnlHoverDetect.setVisible(false);
			//pnlTimerBar.setVisible(true);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			int x = (int) ((e.getX() * m_MaxTimer) / 1280);
			pnlTimerBar.setSize(e.getX(), pnlTimerBar.getHeight());
			m_CurrentTimer = x;
		//	System.out.println(m_MaxTimer);
			//System.out.println(m_CurrentTimer);
			mediaPlayer.getMediaPlayer().setTime(x * 1000);
		}
	};
	
	MouseListener clickControl = new MouseListener() {
		@Override
		public void mouseClicked(MouseEvent e) {
			MediaControl();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}
	};
}
