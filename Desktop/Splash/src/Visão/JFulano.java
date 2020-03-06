package Visão;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
//import util.ImageSizes;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Constantes.ConsPaths;
import Controle.ControlCategoria;

//import com.sun.xml.internal.ws.api.Component;

//import Controle.ControlCliente;
import Controle.ControlConta;
import Controle.ControlHistorico;
//import Thread.LoadChangePerfil;
import Thread.LoadFulano;
import Thread.LoadScreen;
import util.AnimatePosition;
import util.AnimateSize;
import util.Images;
import util.ImgUtils;
import util.PlaceholderTextField;
import util.ScreenConfigs.ScreenSize;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class JFulano extends JFrame {

	private Images images = new Images();
	
	private static final int WIDTHMAIN = 1250;
	private static final int HEIGHTMAIN = 700;

	private static final boolean ENTER = true;
	private static final boolean EXIT = false;

	private static final int CATALOGO = 1;
	private static final int CONTA = 2;

	private static JPanel pnlMain;
	private static JPanel pnlOpen;
	private static JPanel pnlMenuProfile;

	private static JFulano frame;

	public static JLabel fundoLabel;

	private static JLabel logoFilmesJa;
	private static JLabel lblSair;

	private static URL pathIcone;
	private static URL pathFundo;
	private static URL pathSearch;

	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	// private static double width = screenSize.getWidth();
	private static double height = screenSize.getHeight();

	private static boolean V = true;

	private int ID;
	private int currentMenu;
	
	private static int IDConta;

	private ControlConta controlConta;
	private ControlCategoria controlCategoria;
	// private ControlCatalogo controlCatalogo;
	private ControlHistorico historico;

	// private List<java.awt.Component> listaComponent;
	private JLabel lblFavoritos;
	private JLabel lblConfiguracoes;
	private JLabel lblConta;
	private static JLabel lblPerfilImagem;
	private JLabel lblMudar;
	private JLabel lblFinanceiro;

	private ImgUtils imgUtil;

	private boolean showContas = false;
	private boolean showFindBar = false;
	
	private static boolean loadComplete = false;
	private static boolean LoadChangeComplete = false;

	/**
	 * Launch the application.
	 */
	public static void main(int ID) {

		frame = new JFulano(ID);
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		// fundoLabel.setSize((int)width, (int)height);

		frame.setLocationRelativeTo(null);
		V = true;

		// frame.setVisible(true);
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

	private void AnimationMenuBar() {
		if (pnlConta.isVisible()) {
			pnlConta.setVisible(false);
			ValidacaoMenus(true);
		}

		Rectangle fromMenu = new Rectangle(-pnlMenuProfile.getWidth(), 0, pnlMenuProfile.getWidth(), (int) height);
		Rectangle toMenu = new Rectangle(0, 0, pnlMenuProfile.getWidth(), (int) height);

		Rectangle fromOpen = new Rectangle(0, 0, pnlOpen.getWidth(), pnlOpen.getHeight());
		Rectangle toOpen = new Rectangle(pnlMenuProfile.getWidth(), 0, pnlOpen.getWidth(), pnlOpen.getHeight());

		if (V == true) {
			util.AnimateSlide a = new util.AnimateSlide(pnlMenuProfile, fromMenu, toMenu);
			a.start();

			a = new util.AnimateSlide(pnlOpen, fromOpen, toOpen);
			a.start();
			V = false;

		} else {
			util.AnimateSlide a = new util.AnimateSlide(pnlMenuProfile, toMenu, fromMenu);
			a.start();

			a = new util.AnimateSlide(pnlOpen, toOpen, fromOpen);
			a.start();
			V = true;

		}
	}

	/**
	 * Create the frame.
	 */
	
	public JFulano(int ID) {
		System.out.println(images.p.IconeTelaPrincipal);
		pathIcone = images.p.getURL(images.p.IconeTelaPrincipal);
		pathFundo = images.p.getURL(images.p.FundoParaTudo);
		pathSearch = images.p.getURL(images.p.search);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				Exit();
			}
		});
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				// ImageSizes.ArrumarLocation(pnlMain, pnlMain.getWidth(),
				// pnlMain.getHeight());
				// ControlComponentsLocation();
			}
		});

		this.ID = ID;
		this.IDConta = ID;
		imgUtil = new ImgUtils();
		controlConta = new ControlConta();
	//	controlCatalogo = new ControlCatalogo();
		historico = new ControlHistorico();
		historico.getTopMidia();
		setTitle("Ol\u00E1, " + controlConta.getNome(ID));
		//setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	
		setName("JFulano");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
		//
		setBounds(100, 100, WIDTHMAIN, HEIGHTMAIN);
		pnlMain = new JPanel();
		pnlMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlMain);
		pnlMain.setLayout(null);

		logoFilmesJa = new JLabel("icon");
		logoFilmesJa.setName("lblLogo");
		logoFilmesJa.setBounds(ScreenSize.ConvertorSize(495, HEIGHTMAIN), ScreenSize.ConvertorSize(11, HEIGHTMAIN), ScreenSize.ConvertorSize(350, HEIGHTMAIN), ScreenSize.ConvertorSize(180, HEIGHTMAIN));
		OrganizarImagem(pathIcone, logoFilmesJa);

		pnlMain.add(logoFilmesJa);

		lblSair = new JLabel("Sair");
		lblSair.setIcon(new ImageIcon(JFulano.class.getResource("/Vis\u00E3o/imagens/exit.png")));
		lblSair.setName("lblSair");
		lblSair.setForeground(Color.WHITE);
		lblSair.setFont(new Font("Tahoma", Font.PLAIN, 47));
		lblSair.setToolTipText("Sair");
		lblSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Exit();
			}
		});
		lblSair.setBounds(ScreenSize.ConvertorSize(1150, HEIGHTMAIN), ScreenSize.ConvertorSize(11, HEIGHTMAIN), ScreenSize.ConvertorSize(64, HEIGHTMAIN), ScreenSize.ConvertorSize(46, HEIGHTMAIN));
		pnlMain.add(lblSair);

		// TODO: MenuProfile
		pnlMenuProfile = new JPanel();
		pnlMenuProfile.setForeground(Color.WHITE);
		pnlMenuProfile.setBackground(Color.WHITE);
		pnlMenuProfile.setBounds(-200, 0, 200, 437);
		pnlMain.add(pnlMenuProfile);
		pnlMenuProfile.setLayout(null);

		JLabel lblFilmesj = new JLabel("FilmesJ\u00E1");
		lblFilmesj.setFont(new Font("Arial", Font.ITALIC, 44));
		lblFilmesj.setBounds(10, 11, 180, 39);
		pnlMenuProfile.add(lblFilmesj);

		lblPerfilImagem = new JLabel("");
		lblPerfilImagem.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblPerfilImagem.setBounds(45, 61, 155, 109);
		pnlMenuProfile.add(lblPerfilImagem);

		lblFavoritos = new JLabel("Favoritos");
		lblFavoritos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFavoritos.main(ID);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				ChangeColorHover(ENTER, e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				ChangeColorHover(EXIT, e);
			}
		});
		lblFavoritos.setBounds(48, 179, 90, 28);

		pnlMenuProfile.add(lblFavoritos);
		lblFavoritos.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblFavoritos.setForeground(Color.BLACK);

		lblConta = new JLabel("Minha Conta");
		lblConta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JConta.main(ID);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				ChangeColorHover(ENTER, e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				ChangeColorHover(EXIT, e);
			}
		});
		lblConta.setBounds(33, 229, 127, 28);
		pnlMenuProfile.add(lblConta);
		lblConta.setForeground(Color.BLACK);
		lblConta.setFont(new Font("Tahoma", Font.PLAIN, 23));

		lblConfiguracoes = new JLabel("Configura\u00E7\u00F5es");
		lblConfiguracoes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ChangeColorHover(ENTER, e);
			}
			@Override
			public void mouseExited(MouseEvent e){
				ChangeColorHover(EXIT, e);
			}
		});
		lblConfiguracoes.setBounds(18, 288, 142, 28);
		//pnlMenuProfile.add(lblConfiguracoes);
		lblConfiguracoes.setForeground(Color.BLACK);
		lblConfiguracoes.setFont(new Font("Tahoma", Font.PLAIN, 23));

		lblFinanceiro = new JLabel("Financeiro");
		lblFinanceiro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFinanceiro.main(controlConta.getCliente(ID));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				ChangeColorHover(ENTER, e);
			}
			@Override
			public void mouseExited(MouseEvent e){
				ChangeColorHover(EXIT, e);
			}
			
		});
		lblFinanceiro.setForeground(Color.BLACK);
		lblFinanceiro.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblFinanceiro.setBounds(45, 278, lblFinanceiro.getPreferredSize().width, lblFinanceiro.getPreferredSize().height);
		
		
		
		pnlMenuProfile.add(lblFinanceiro);
		
		lblChange = new JLabel("");
		lblChange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				showContas = !showContas;
				ValidacaoMenus(!showContas);
				pnlConta.setVisible(showContas);
				// AnimateRotate rotate = new AnimateRotate(lblChange,
				// AnimateRotate.TO_UP);
				// rotate.Start();
			}
		});
		lblChange.setBounds(159, 137, 31, 18);
		lblChange.setIcon(
				imgUtil.ResizeImageIcon(lblChange, images.Image_down, ImgUtils.LOW_RESOLUTION));
		pnlMenuProfile.add(lblChange);

		pnlOpen = new JPanel();
		pnlOpen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AnimationMenuBar();
			}
		});
		pnlOpen.setBackground(Color.BLACK);
		pnlOpen.setBounds(0, 0, 58, 47);
		pnlMain.add(pnlOpen);
		pnlOpen.setLayout(null);

		JLabel traco1 = new JLabel("_____");
		traco1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AnimationMenuBar();
			}
		});
		traco1.setForeground(Color.WHITE);
		traco1.setBounds(10, 0, 48, 14);
		pnlOpen.add(traco1);

		JLabel traco2 = new JLabel("_____");
		traco2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AnimationMenuBar();
			}
		});
		traco2.setForeground(Color.WHITE);
		traco2.setBounds(10, 11, 48, 14);
		pnlOpen.add(traco2);

		JLabel traco3 = new JLabel("_____");
		traco3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AnimationMenuBar();
			}
		});
		traco3.setForeground(Color.WHITE);
		traco3.setBounds(10, 25, 48, 14);
		pnlOpen.add(traco3);

		LoadPerfil();

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(ScreenSize.ConvertorSize(200, HEIGHTMAIN), ScreenSize.ConvertorSize(176, HEIGHTMAIN), ScreenSize.ConvertorSize(1034, HEIGHTMAIN), ScreenSize.ConvertorSize(484, HEIGHTMAIN));

		pnlCatalogo = new JCatalogoView(ID);
		
		pnlSearch = new JSearchCat(controlConta.getContaIdiomaID(ID));
		
		pnlConta = new JSelecionarContaPainel(ID, this);
		pnlConta.setBounds(200, 176, 800, 300);
		pnlConta.setVisible(false);
		pnlConta.setLayout(null);
		pnlMain.add(pnlConta);

		pnlCatalogo.setLayout(null);

		scrollPane.setViewportView(pnlCatalogo);
		scrollPane.getViewport().setOpaque(false);
	    scrollPane.setBorder(null);
	    scrollPane.setOpaque(false);
		
		//"Vou me clonar nas sombras e vou conquistar o meu lugar"
		pnlContaOpcoes = new JOpcoesConta(ID);
		pnlContaOpcoes.setLocation(pnlConta.getLocation());
		pnlContaOpcoes.setVisible(false);
	
		pnlMain.add(scrollPane);
		pnlMain.add(pnlContaOpcoes);
		
		
		lblMudar = new JLabel("Mudar");
		lblMudar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChangeCurrentMenu();
			}
		});
		lblMudar.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMudar.setBounds(1160, 139, lblMudar.getPreferredSize().width, lblMudar.getPreferredSize().height);
		lblMudar.setForeground(Color.WHITE);
	//	pnlMain.add(lblMudar);
		
		lblBusca = new JLabel("Buscar");
		lblBusca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AnimateBusca();
			}
		});
		lblBusca.setFont(new Font("Arial", Font.PLAIN, 16));
		lblBusca.setBounds(ScreenSize.ConvertorSize(1142, HEIGHTMAIN), ScreenSize.ConvertorSize(136, HEIGHTMAIN), lblBusca.getPreferredSize().width, lblBusca.getPreferredSize().height);
		lblBusca.setForeground(Color.WHITE);
		
		lblIconBusca = new JLabel();
		lblIconBusca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AnimateBusca();
			}
		});
		
		
		lblIconBusca.setBounds(ScreenSize.ConvertorSize(1092, HEIGHTMAIN), ScreenSize.ConvertorSize(126, HEIGHTMAIN), ScreenSize.ConvertorSize(40, HEIGHTMAIN), ScreenSize.ConvertorSize(40, HEIGHTMAIN));
		ImageIcon imgI = new ImageIcon(pathSearch);
		imgUtil.OrganizarImagem(lblIconBusca, imgI, ImgUtils.LOW_RESOLUTION);
		pnlMain.add(lblIconBusca);
		
		pnlBarFind = new JPanel();
		pnlBarFind.setSize(0, ScreenSize.ConvertorSize(30, HEIGHTMAIN));
		pnlBarFind.setLocation(ScreenSize.ConvertorSize(1190, HEIGHTMAIN), ScreenSize.ConvertorSize(137, HEIGHTMAIN));
		pnlBarFind.setLayout(null);
		//pnlBarFind.setBorder(BorderFactory.createLineBorder(Color.WHITE, ScreenSize.ConvertorSize(0, HEIGHTMAIN)));
		//pnlBarFind.setBackground(Color.DARK_GRAY);
		pnlBarFind.setOpaque(false);
		//pnlBarFind.setVisible(false);
		
		txtBusca = new PlaceholderTextField();
		txtBusca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Search();
			}
		});
		txtBusca.setLocation(0, 0);
		txtBusca.setPlaceholder("Filmes, séries, atores, diretores");
		txtBusca.setSize(ScreenSize.ConvertorSize(250, HEIGHTMAIN), ScreenSize.ConvertorSize(30, HEIGHTMAIN));
		txtBusca.setForeground(Color.WHITE);
		txtBusca.setBackground(Color.DARK_GRAY);
		txtBusca.setCaretColor(Color.WHITE);
		txtBusca.setEnabled(false);

		pnlBarFind.add(txtBusca);
		
		pnlMain.add(pnlBarFind);
		
		pnlMain.add(lblBusca);
		
		fundoLabel = new JLabel("");
		fundoLabel.setBounds(0, 0, ScreenSize.SCREEN_WIDTH, ScreenSize.SCREEN_HEIGHT);
		// OrganizarImagem(pathFundo, fundoLabel);
		fundoLabel.setIcon(new ImageIcon(pathFundo));

		pnlMain.add(fundoLabel);

		OrganizarImagem(fundoLabel, new ImageIcon(pathFundo));

		
		OrganizarImagem(lblSair, images.Image_exit);
		// SalvarTudoNaListaPraMudarOLocal();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		currentMenu = CATALOGO;
		loadComplete = true;
	}

	private void LoadPerfil() {
		imgUtil.CircleRender(converterImageIconSize(lblPerfilImagem.getWidth(), lblPerfilImagem.getHeight(),
				controlConta.getImageIcon(ID)), lblPerfilImagem);
	}

	private void ChangeColorHover(boolean entro, MouseEvent e) {

		JLabel lbl = (JLabel) e.getComponent();
		Font font = null;
		if (entro) {
			font = new Font(lbl.getFont().getName(), Font.BOLD, lbl.getFont().getSize());
		} else {
			font = new Font(lbl.getFont().getName(), Font.PLAIN, lbl.getFont().getSize());
		}
		((JLabel) e.getComponent()).setFont(font);
		lbl.setBounds(lbl.getBounds().x, lbl.getBounds().y, lbl.getPreferredSize().width,
				lbl.getPreferredSize().height);
	}

	private void ChangeCurrentMenu() {
		if (currentMenu == CATALOGO) {
			scrollPane.setVisible(false);
			pnlContaOpcoes.setVisible(true);
			currentMenu = CONTA;
		} else {
			scrollPane.setVisible(true);
			pnlContaOpcoes.setVisible(false);
			currentMenu = CATALOGO;
		}
	}
	
	private void ValidacaoMenus(boolean umValor){
		if(currentMenu == CATALOGO){
			scrollPane.setVisible(umValor);
		}else{
			pnlContaOpcoes.setVisible(umValor);
		}
	}
	
	private void Exit(){
		controlConta.ChangeLine(ID, ControlConta.LINE_OFF);
		//JLogin.Visible(true);
		//frame.dispose();
		System.exit(0);
	}
	
	//TODO : AMIMATEBusca
	private void AnimateBusca(){
		if(!showFindBar){
			AnimatePosition p = new AnimatePosition(lblIconBusca, 13, -10, AnimatePosition.X, ScreenSize.ConvertorSize(894, HEIGHTMAIN));
			
			p.Start();
			
			AnimateSize anime = new AnimateSize(pnlBarFind, 10, AnimateSize.WIDTH, ScreenSize.ConvertorSize(250, HEIGHTMAIN), 10);
			anime.Start();
			
			showFindBar = true;
			txtBusca.setEnabled(true);
		}else{
			AnimatePosition p = new AnimatePosition(lblIconBusca, 10, 10, AnimatePosition.X, ScreenSize.ConvertorSize(1092, HEIGHTMAIN));
			
			p.Start();
			
			AnimateSize anime = new AnimateSize(pnlBarFind, -10, AnimateSize.WIDTH, 0, 10);
			anime.Start();
			
			showFindBar = false;
			txtBusca.setEnabled(false);
		}
	}
	
	private void Search(){
		if(txtBusca.getText().length() != 0){
			pnlSearch.Update(txtBusca.getText());
			scrollPane.setViewportView(pnlSearch);
		}else{
			scrollPane.setViewportView(pnlCatalogo);
		}
	}

	public static void Visible(boolean b) {
		frame.setUndecorated(true);
		frame.setVisible(b);
		frame.setResizable(false);
	}
	
	public static int getIDConta(){
		return IDConta;
	}

	public ImageIcon converterImageIconSize(int width, int height, ImageIcon img) {
		Image dimg = img.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(dimg);
	}

	public void ClickChangePerfil(int IDConta) {
		LoadScreen ls = new LoadScreen();
		ls.Start();

		// Visible(false);

		// LoadChangePerfil lcp = new LoadChangePerfil();
		// lcp.Start(frame, IDConta);
		frame.dispose();
		LoadFulano lf = new LoadFulano();
		lf.Start(IDConta);
	}

	public void ChangePerfil(int IDConta) {
		this.ID = IDConta;

		/*
		 * LoadPerfil(); pnlConta.setVisible(false);
		 * 
		 * pnlMain.remove(scrollPane); scrollPane = new JScrollPane();
		 * scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.
		 * HORIZONTAL_SCROLLBAR_NEVER); scrollPane.setBounds(200, 176, 1034,
		 * 484);
		 * 
		 * pnlCatalogo = new JCatalogoView(ID);
		 * 
		 * for (Component c : pnlCatalogo.getComponents()) {
		 * System.out.println(c.getName()); } pnlCatalogo.setLayout(null);
		 * pnlCatalogo.setVisible(true);
		 * 
		 * scrollPane.setViewportView(pnlCatalogo); scrollPane.setVisible(true);
		 * scrollPane.repaint();
		 * 
		 * pnlMain.add(scrollPane); pnlMain.revalidate(); pnlMain.repaint();
		 * this.repaint();
		 */
		this.dispose();
		JFulano.main(ID);
		JFulano.Visible(true);
		// JFulano.V = true;
		LoadChangeComplete = true;
	}

	public static void RemoveFav(int IDCat) {
		for (Component c : pnlCatalogo.getComponents()) {
			if (c instanceof JPanel) {
				for (Component ca : ((JPanel) c).getComponents()) {
					if (Integer.parseInt(ca.getName()) == IDCat && ca instanceof JLabel) {
						for (Component cac : ((JLabel) ca).getComponents()) {
							if (cac instanceof JLabel) {
								JLabel lbl = (JLabel) cac;
								//lbl.setText("+");
								lbl.setIcon(JCatalogoView.coracaoVazio);
								//lbl.setBounds(lbl.getBounds().x, lbl.getBounds().y, lbl.getPreferredSize().width,
									//	lbl.getPreferredSize().height);
							}
						}
					}
				}
			}
		}
	}
	
	public static void AddFav(int IDCat) {
		for (Component c : pnlCatalogo.getComponents()) {
			if (c instanceof JPanel) {
				for (Component ca : ((JPanel) c).getComponents()) {
					if (Integer.parseInt(ca.getName()) == IDCat && ca instanceof JLabel) {
						for (Component cac : ((JLabel) ca).getComponents()) {
							if (cac instanceof JLabel) {
								JLabel lbl = (JLabel) cac;
								//lbl.setText("X");
								lbl.setIcon(JCatalogoView.coracaoCheio);
								//lbl.setBounds(lbl.getBounds().x, lbl.getBounds().y, lbl.getPreferredSize().width,
									//	lbl.getPreferredSize().height);
							}
						}
					}
				}
			}
		}
	}

	public static boolean getLoadChange() {
		return LoadChangeComplete;
	}

	public static boolean getLoad() {
		return loadComplete;
	}
	
	public static void closeDispose(){
		frame.dispose();
	}

	MouseListener clickCategoria = new MouseListener() {

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
			e.getComponent().setForeground(Color.BLACK);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			e.getComponent().setForeground(Color.RED);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			JFulano.Visible(false);
			JBuscaCatalogo.main(controlCategoria.getID(e.getComponent().getName()), ID);
		}
	};
	
	public static void setTitleFrame(String nome){
		frame.setTitle(nome);
	}
	
	public static void setNewImagePerfil(ImageIcon img){
		lblPerfilImagem.setIcon(img);
		frame.imgUtil.CircleRender(img, lblPerfilImagem);
	}
	
	private void Reload(){
		
	}
	
	private static JPanel pnlCatalogo;
	private JPanel pnlConta;
	private JPanel pnlContaOpcoes;
	private static JScrollPane scrollPane;
	private JLabel lblChange;
	private JLabel lblBusca;
	private JLabel lblIconBusca;
	
	private JPanel pnlBarFind;
	private PlaceholderTextField txtBusca;
	private JSearchCat pnlSearch;
	private JLabel lblIconBuscaBar;
}
