package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import Enum.ConsMenu;
import Enum.EnumPreguica;
import control.ControlAtalhosLocal;
import model.MDAtalhoLocal;
import util.Images;
import util.ImgUtils;
import util.ScreenSize.SC;

@SuppressWarnings("serial")
public class JControleAdministrador extends JFrame {

	private JPanel contentPane;
	private static URL pathIcone = JControleAdministrador.class.getResource("/view/imagens/IconePraTelaPrincipal2.png");
	private static URL pathLogoNovo = JControleAdministrador.class.getResource("/view/imagens/logoNovo.png");

	
	public final static String PanelInserir = "CardInserir";
	public final static String PanelConfig = "CardConfig";
	public final static String PanelRelatorio = "CardRelatorio";
	public final static String PanelEditar = "CardEditar";
	
	public static JEscolha escolha;
	
	private static JLabel lblNewLabel;
	private static JLabel lblLogo;

	public static void main(String[] args) {
		try {
			JControleAdministrador controle = new JControleAdministrador();
			controle.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			controle.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the frame.
	 */
	public JControleAdministrador() {
		
		Images images = new Images();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1378, 755);
		setTitle("Administrador");
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.BLACK);
		setJMenuBar(menuBar);
		
		mnInserir = new JMenu("Inserir");
		mnInserir.setBackground(Color.BLACK);
		mnInserir.setForeground(Color.WHITE);
		menuBar.add(mnInserir);
		
		mntmInserirCategoria = new JMenuItem("Categoria");
		mntmInserirCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JInserirAtores.main(null, EnumPreguica.Categoria.get());
			}
		});
		
		mntmInserirCargo = new JMenuItem("Cargo");
		mntmInserirCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JInserirAtores.main(null, EnumPreguica.Cargo.get());
			}
		});
		mnInserir.add(mntmInserirCargo);
		
		mntmInserirCatalogo = new JMenuItem("Catalogo");
		mntmInserirCatalogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JCatalogo.main(null);
			}
		});
		mnInserir.add(mntmInserirCatalogo);
		mnInserir.add(mntmInserirCategoria);
		
		mntmConta_5 = new JMenuItem("Conta");
		//mnNewMenu.add(mntmConta_5);
		
		mntmInserirElenco = new JMenuItem("Elenco");
		mntmInserirElenco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JInserirAtores.main(null, EnumPreguica.Elenco.get());
			}
		});
		mnInserir.add(mntmInserirElenco);
		
		mntmInserirGenero = new JMenuItem("G\u00EAnero ");
		mntmInserirGenero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JInserirAtores.main(null, EnumPreguica.Genero.get());
			}
		});
		mnInserir.add(mntmInserirGenero);
		
		mntmInserirPais = new JMenuItem("Pa\u00EDs");
		mntmInserirPais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JInserirAtores.main(null, EnumPreguica.Pais.get());
			}
		});
		
		mntmInserirIdioma = new JMenuItem("Idioma");
		mntmInserirIdioma.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JInserirAtores.main(null, EnumPreguica.Idioma.get());
				
			}
		});
		mnInserir.add(mntmInserirIdioma);
		mnInserir.add(mntmInserirPais);
		
		mntmInserirRegiao = new JMenuItem("Regi\u00E3o");
		mntmInserirRegiao.setVisible(false);
		mntmInserirRegiao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInserirAtores.main(null, EnumPreguica.Regiao.get());
			}
		});
		mnInserir.add(mntmInserirRegiao);
		
		/*mntmProdutora_1 = new JMenuItem("Produtora");
		mntmProdutora_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JInserirAtores.main(null, 6);
			}
		});
		mnNewMenu.add(mntmProdutora_1);*/
		
		mnNewMenu_1 = new JMenu("Modificar");
		mnNewMenu_1.setForeground(Color.WHITE);
		menuBar.add(mnNewMenu_1);
		
		mntmModificarElenco = new JMenuItem("Elenco");
		mntmModificarElenco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JEditar.main(null, EnumPreguica.Elenco.get());
			}
		});
		
		mntmModificarCargo = new JMenuItem("Cargo");
		mntmModificarCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JEditar.main(null, EnumPreguica.Cargo.get());
			}
		});
		mnNewMenu_1.add(mntmModificarCargo);
		
		mntmModificarCategoria = new JMenuItem("Categoria");
		mntmModificarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JEditar.main(null, EnumPreguica.Categoria.get());
			}
		});
		
		mntmModificarCatalogo = new JMenuItem("Catalogo");
		mntmModificarCatalogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JVisualCatalogo.main(null);
			}
		});
		mnNewMenu_1.add(mntmModificarCatalogo);
		mnNewMenu_1.add(mntmModificarCategoria);
		
		mntmConta = new JMenuItem("Conta");
		//mnNewMenu_1.add(mntmConta);
		mnNewMenu_1.add(mntmModificarElenco);
		
		mntmModificarPais = new JMenuItem("Pa\u00EDs");
		mntmModificarPais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JEditar.main(null, EnumPreguica.Pais.get());
			}
		});
		
		mntmModificarGenero = new JMenuItem("G\u00EAnero");
		mntmModificarGenero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JEditar.main(null, EnumPreguica.Genero.get());
			}
		});
		mnNewMenu_1.add(mntmModificarGenero);
		
		mntmModificarIdioma = new JMenuItem("Idioma");
		mntmModificarIdioma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JEditar.main(null, EnumPreguica.Idioma.get());
			}
		});
		mnNewMenu_1.add(mntmModificarIdioma);
		mnNewMenu_1.add(mntmModificarPais);
		
		mntmModificarRegiao = new JMenuItem("Regi\u00E3o");
		mntmModificarRegiao.setVisible(false);
		mntmModificarRegiao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JEditar.main(null, EnumPreguica.Regiao.get());
			}
		});
		mnNewMenu_1.add(mntmModificarRegiao);
		
		//mntmProdutora = new JMenuItem("Produtora");
		//mnNewMenu_1.add(mntmProdutora);
		
		mnControle = new JMenu("Relat\u00F3rio");
		mnControle.setForeground(Color.WHITE);
		menuBar.add(mnControle);
		
		mntmCategoria_1 = new JMenuItem("Categoria");
		mntmCategoria_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//JRelatorio relatorio = new JRelatorio();
				//JRelatorio.main(null);
				FramePreBanca.main(null);
			}
		});
		mnControle.add(mntmCategoria_1);
		
		mnUsuarios = new JMenu("Usu\u00E1rios");
		mnControle.add(mnUsuarios);
		
		mntmQtdCadastrados = new JMenuItem("Quantidade de Cadastrados");
		mntmQtdCadastrados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JGraficoUsuario.main(null);
			}
		});
		mnUsuarios.add(mntmQtdCadastrados);
		
		mntmConta_2 = new JMenuItem("Conta");
		//mnControle.add(mntmConta_2);
		
		mntmFilme_3 = new JMenuItem("Catalogo");
		//mnControle.add(mntmFilme_3);
		
		mntmGnero_1 = new JMenuItem("G\u00EAnero");
		//mnControle.add(mntmGnero_1);
		
		mnConfiguraes = new JMenu("Configura\u00E7\u00F5es");
		mnConfiguraes.setForeground(Color.WHITE);
		mnConfiguraes.setBackground(Color.PINK);
		menuBar.add(mnConfiguraes);
		
		mntmLocal = new JMenu("Local");
	//	mnConfiguraes.add(mntmLocal);
		
		mntmAtalhos = new JMenuItem("Atalhos");
		mntmAtalhos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JAtalhos.main(null);
			}
		});
		mntmLocal.add(mntmAtalhos);
		
		mntmSite = new JMenu("Site");
		mnConfiguraes.add(mntmSite);
		
		mntmImagensSite = new JMenuItem("Imagens Site");
		mntmImagensSite.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JImagemSite.main(null);
				
			}
		});
		mntmSite.add(mntmImagensSite);
		
		mntmDuvidasFrequentes = new JMenu("Duvidas Frequentes");
		mntmSite.add(mntmDuvidasFrequentes);
		
		mntmAdicionar = new JMenuItem("Adicionar");
		mntmAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JDuvidasFrequentes.main(null);
			}
		});
		mntmDuvidasFrequentes.add(mntmAdicionar);
		
		mntmModificar = new JMenuItem("Modificar");
		mntmModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JDuvidasFrequentesVisual.main(null);
			}
		});
		mntmDuvidasFrequentes.add(mntmModificar);
		mntmImagensSite.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		mntmAtendimento = new JMenu("Atendimento ao Cliente");
		mntmAtendimento.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent arg0) {
			}
			public void menuDeselected(MenuEvent arg0) {
			}
			public void menuSelected(MenuEvent arg0) {
				JFaleConosco.main(null);
			}
		});
		mntmAtendimento.setForeground(Color.WHITE);
		mntmAtendimento.setBackground(Color.BLACK);
		mntmAtendimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFaleConosco.main(null);
			}
		});
		menuBar.add(mntmAtendimento);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblinserir = new JLabel("Inserir");
		lblinserir.setForeground(Color.YELLOW);
		lblinserir.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblinserir.setBounds(66, 96, 42, 16);
		contentPane.add(lblinserir);
		
		JLabel lblModificar = new JLabel("Modificar");
		lblModificar.setForeground(Color.YELLOW);
		lblModificar.setBounds(55, 228, 55, 16);
		contentPane.add(lblModificar);
		
		JLabel lblRelatório = new JLabel("Relat\u00F3rio");
		lblRelatório.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				escolha.setVisible(false);
				CardLayout cl = (CardLayout)(pnlFirst.getLayout());
				cl.show(pnlFirst, PanelRelatorio);
			}
		});
		lblRelatório.setForeground(Color.YELLOW);
		lblRelatório.setBounds(53, 361, 55, 16);
		contentPane.add(lblRelatório);
		
		JLabel lblConfig = new JLabel("Configura\u00E7\u00E3o");
		lblConfig.setForeground(Color.YELLOW);
		lblConfig.setBounds(45, 499, 89, 16);
		contentPane.add(lblConfig);
		
		JLabel lblAtend = new JLabel("Atendimento");
		lblAtend.setForeground(Color.YELLOW);
		lblAtend.setBounds(45, 630, 78, 16);
		contentPane.add(lblAtend);

		btnInserir = new JLabel("New button");
		btnInserir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				escolha.setVisible(false);
				CardLayout cl = (CardLayout)(pnlFirst.getLayout());
				cl.show(pnlFirst, PanelInserir);
			}
		});
		btnInserir.setIcon(images.Image_IconeInserir);
		btnInserir.setBounds(35, 20, 98, 80);
		contentPane.add(btnInserir);
		
		btnRelatorio = new JLabel("New button");
		btnRelatorio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				escolha.setVisible(false);
				CardLayout cl = (CardLayout)(pnlFirst.getLayout());
				cl.show(pnlFirst, PanelRelatorio);
			}
		});
		btnRelatorio.setIcon(images.Image_Relatorio);
		btnRelatorio.setBounds(35, 283, 98, 80);
		contentPane.add(btnRelatorio);
		
		btnModificar = new JLabel("New button");
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				escolha.setVisible(false);
				CardLayout cl = (CardLayout)(pnlFirst.getLayout());
				cl.show(pnlFirst, PanelEditar);
			}
		});
		btnModificar.setIcon(images.Image_IconeModificar);
		btnModificar.setBounds(35, 152, 98, 80);
		contentPane.add(btnModificar);
		
		btnConfig = new JLabel("New button");
		btnConfig.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				escolha.setVisible(false);
				CardLayout cl = (CardLayout)(pnlFirst.getLayout());
				cl.show(pnlFirst, PanelConfig);
			}
		});
		btnConfig.setIcon(images.Image_Config);
		btnConfig.setBounds(35, 424, 98, 80);
		contentPane.add(btnConfig);
		
		btnAtend = new JLabel("New button");
		btnAtend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFaleConosco.main(null);
			}
		});
		btnAtend.setIcon(images.Image_Atendimento);
		btnAtend.setBounds(35, 555, 98, 80);
		contentPane.add(btnAtend);
		
		
		lblNewLabel = new JLabel("Configura\u00E7\u00E3o");
		lblNewLabel.setBounds(0, 0, SC.SCREEN_WIDTH, SC.SCREEN_HEIGHT);
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(images.p.FundoParaTudo));
		} catch (Exception e) {
			// TODO: handle exception
		}

		Image dimg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imgI = new ImageIcon(dimg);
		contentPane.setLayout(null);
		
		lblLogo = new JLabel("");
		lblLogo.setBounds(372, 123, 308, 272);
		OrganizarImagem(pathIcone, lblLogo);
		//contentPane.add(lblLogo);
		
		
		
		pnlFirst = new JPanel();
		pnlFirst.setBounds(310, 110, 846, 497);
		pnlFirst.setLayout(new CardLayout(0, 0));
		
		
		JPanel semNada = new JPanel();
		semNada.setOpaque(false);
		
		pnlFirst.add(semNada, "0");
		JMenuInserir m = new JMenuInserir(true);
		JMenuInserir mEdit = new JMenuInserir(false);
		pnlFirst.add(m.contentPanel, PanelInserir);
		pnlFirst.add(mEdit.contentPanel, PanelEditar);

		JMenuConfig config = new JMenuConfig();
		pnlFirst.add(config.contentPanel, PanelConfig);
		
		JMenuRelatorio rl = new JMenuRelatorio();
		pnlFirst.add(rl.contentPanel, PanelRelatorio);
		
		pnlFirst.setOpaque(false);
		
		escolha = new JEscolha();
		escolha.setVisible(false);
		escolha.setBounds(SC.SCREEN_WIDTH / 2 - (543 / 2), SC.SCREEN_HEIGHT / 2 - 203, 543, 203);
		contentPane.add(escolha);
		
		contentPane.add(pnlFirst);
		
		JLabel lblNovoLogo = new JLabel("");
		lblNovoLogo.setBounds(385, 137, 679, 342);
		
		
		contentPane.add(lblNovoLogo);
		OrganizarImagem(pathLogoNovo, lblNovoLogo);
		lblNewLabel.setIcon(imgI);
		contentPane.add(lblNewLabel);
		
		
		ConfigurarAtalhos();
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
	
	public static void RemoveFirstPanel(){
		escolha.setVisible(false);
		
		CardLayout cl = (CardLayout)(pnlFirst.getLayout());
		cl.show(pnlFirst, "0");
	}
	
	public static void GoBackPanel(String panel){
		escolha.setVisible(false);
		
		CardLayout cl = (CardLayout)(pnlFirst.getLayout());
		cl.show(pnlFirst, panel);
		
	}
	
	private JMenu mnInserir;
	private JMenu mnNewMenu_1;
	private JMenu mnNewMenu_2;
	private JMenu mnFaleConosco;
	
	private JMenuItem mntmInserirCatalogo;
	private JMenuItem mntmConta;
	private JMenuItem mntmModificarCatalogo;
	private JMenuItem mntmConta_1;
	private JMenuItem mntmFilme_2;
	private JMenu mnControle;
	private JMenuItem mntmConta_2;
	private JMenuItem mntmFilme_3;
	private JMenu mnConfiguraes;
	private JMenuItem mntmInserirGenero;
	private JMenuItem mntmInserirElenco;
	private JMenuItem mntmInserirPais;
	private JMenuItem mntmModificarElenco;
	private JMenuItem mntmProdutora;
	private JMenuItem mntmModificarPais;
	private JMenuItem mntmModificarCategoria;
	private JMenuItem mntmProdutora_1;
	private JMenuItem mntmConta_5;
	private JMenuItem mntmGnero_1;
	private JMenuItem mntmCategoria_1;
	private JMenuItem mntmInserirRegiao;
	private JMenuItem mntmImagensSite;
	private JMenu mntmAtendimento;
	private JLabel btnRelatorio;
	private JLabel btnModificar;
	private JLabel btnConfig;
	private JLabel btnAtend;
	private JLabel btnInserir;
	private JMenu mntmSite;
	private JMenu mntmDuvidasFrequentes;
	private JMenuItem mntmAdicionar;
	private JMenuItem mntmModificar;
	private JMenu mntmLocal;
	private JMenuItem mntmAtalhos;
	private JMenuItem mntmInserirCategoria;
	private JMenuItem mntmInserirCargo;
	private JMenuItem mntmInserirIdioma;
	private JMenuItem mntmModificarCargo;
	private JMenuItem mntmModificarGenero;
	private JMenuItem mntmModificarIdioma;
	private JMenuItem mntmModificarRegiao;
	

	private void ConfigurarAtalhos(){
		ControlAtalhosLocal controlAtalhosLocal = new ControlAtalhosLocal();
		for (MDAtalhoLocal mdAtalhoLocal: controlAtalhosLocal.Selecionar()) {
			
			Action a = null;
			a = InserirCategoria;
			
			
			switch (mdAtalhoLocal.getConfig()) {
			case ConsMenu.InserirCategoria:
				SetAction(InserirCategoria, mdAtalhoLocal.getAtalho(), mdAtalhoLocal.getID());
				break;

			case ConsMenu.InserirGenero:
				SetAction(InserirGenero, mdAtalhoLocal.getAtalho(), mdAtalhoLocal.getID());
				break;
			
			case ConsMenu.InserirCargo:
				SetAction(InserirCargo, mdAtalhoLocal.getAtalho(), mdAtalhoLocal.getID());
				break;
				
			case ConsMenu.InserirCatalogo:
				SetAction(InserirCatalogo, mdAtalhoLocal.getAtalho(), mdAtalhoLocal.getID());
				break;
				
			case ConsMenu.InserirElenco:
				SetAction(InserirElenco, mdAtalhoLocal.getAtalho(), mdAtalhoLocal.getID());
				break;
				
			case ConsMenu.InserirPais:
				SetAction(InserirPais, mdAtalhoLocal.getAtalho(), mdAtalhoLocal.getID());
				break;
				
			case ConsMenu.InserirRegiao:
				SetAction(InserirRegiao, mdAtalhoLocal.getAtalho(), mdAtalhoLocal.getID());
				break;
			
			case ConsMenu.InserirIdioma:
				SetAction(InserirIdioma, mdAtalhoLocal.getAtalho(), mdAtalhoLocal.getID());
				break;
			
			case ConsMenu.ModificarCargo:
				SetAction(ModificarCargo, mdAtalhoLocal.getAtalho(), mdAtalhoLocal.getID());
				break;
				
			case ConsMenu.ModificarCatalogo:
				SetAction(ModificarCatalogo, mdAtalhoLocal.getAtalho(), mdAtalhoLocal.getID());
				break;
			
			
			case ConsMenu.ModificarCategoria:
				SetAction(ModificarCategoria, mdAtalhoLocal.getAtalho(), mdAtalhoLocal.getID());
				break;
			
			case ConsMenu.ModificarElenco:
				SetAction(ModificarElenco, mdAtalhoLocal.getAtalho(), mdAtalhoLocal.getID());
				break;
				
			case ConsMenu.ModificarGenero:
				SetAction(ModificarGenero, mdAtalhoLocal.getAtalho(), mdAtalhoLocal.getID());
				break;
				
			case ConsMenu.ModificarIdioma:
				SetAction(ModificarIdioma, mdAtalhoLocal.getAtalho(), mdAtalhoLocal.getID());
				break;
			
			case ConsMenu.ModificarPais:
				SetAction(ModificarPais, mdAtalhoLocal.getAtalho(), mdAtalhoLocal.getID());
				break;
				
			case ConsMenu.ModificarRegiao:
				SetAction(ModificarRegiao, mdAtalhoLocal.getAtalho(), mdAtalhoLocal.getID());
				break;
				
			case ConsMenu.ConfigurarLocal:
				SetAction(ConfigurarLocal, mdAtalhoLocal.getAtalho(), mdAtalhoLocal.getID());
				break;
				
			case ConsMenu.ConfigurarSite:
				SetAction(ConfigurarSite, mdAtalhoLocal.getAtalho(), mdAtalhoLocal.getID());
				break;
				
			case ConsMenu.ConfigurarAtalhos:
				SetAction(ConfigurarAtalhos, mdAtalhoLocal.getAtalho(), mdAtalhoLocal.getID());
				break;
				
			case ConsMenu.ConfigurarImagens:
				SetAction(ConfigurarImagens, mdAtalhoLocal.getAtalho(), mdAtalhoLocal.getID());
				break;
				
			case ConsMenu.ConfigurarDuvidas:
				SetAction(ConfigurarDuvidas, mdAtalhoLocal.getAtalho(), mdAtalhoLocal.getID());
				break;
			
			case ConsMenu.ConfigurarAdicionar:
				SetAction(ConfigurarAtalhos, mdAtalhoLocal.getAtalho(), mdAtalhoLocal.getID());
				break;
				
			case ConsMenu.ConfigurarModificar:
				SetAction(ConfigurarModificar, mdAtalhoLocal.getAtalho(), mdAtalhoLocal.getID());
				break;
			
			case ConsMenu.Atendimento:
				SetAction(Atendimento, mdAtalhoLocal.getAtalho(), mdAtalhoLocal.getID());
				break;
				
			default:
				a = null;
				break;
			}
			
		}
		
		
	}
	
	private void SetAction(Action action, String key, int id){
		contentPane.getInputMap().put(KeyStroke.getKeyStroke(key), id);
		contentPane.getActionMap().put(id, action);
	}
	
	//Actions usados para os atalhos
	
	private Action InserirCategoria = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			mntmInserirCategoria.doClick();
		}
	};
	
	private Action InserirGenero = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mntmInserirGenero.doClick();
		}
	};
	
	private Action InserirCargo = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mntmInserirCargo.doClick();
		}
	};
	
    private Action InserirCatalogo = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mntmInserirCatalogo.doClick();
		}
	};
	
    private Action InserirElenco = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mntmInserirElenco.doClick();
		}
	};
	
    private Action InserirPais = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mntmInserirPais.doClick();
		}
	};
	
    private Action InserirRegiao = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mntmInserirRegiao.doClick();
		}
	};
	
    private Action InserirIdioma = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mntmInserirIdioma.doClick();
		}
	};
	
	
     private Action ModificarCargo = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mntmModificarCargo.doClick();
		}
	};
	

	private Action ModificarCatalogo = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mntmModificarCatalogo.doClick();
		}
	};
	

	private Action ModificarCategoria = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mntmModificarCategoria.doClick();
		}
	};
	

	private Action ModificarElenco = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mntmModificarElenco.doClick();
		}
	};

	private Action ModificarGenero = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mntmModificarGenero.doClick();
		}
	};
	

	private Action ModificarIdioma = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mntmModificarIdioma.doClick();
		}
	};
	

	private Action ModificarPais = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mntmModificarPais.doClick();
		}
	};

	private Action ModificarRegiao = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mntmModificarRegiao.doClick();
		}
	};
	

	private Action ConfigurarLocal = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mntmLocal.doClick();
		}
	};

	private Action ConfigurarSite = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mntmSite.doClick();
		}
	};
	

	private Action ConfigurarAtalhos = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mntmAtalhos.doClick();
		}
	};

	private Action ConfigurarImagens = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mntmImagensSite.doClick();
		}
	};
	

	private Action ConfigurarDuvidas = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mntmDuvidasFrequentes.doClick();
		}
	};

	private Action ConfigurarAdicionar = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mntmAdicionar.doClick();
		}
	};
	

	private Action ConfigurarModificar = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mntmModificar.doClick();
		}
	};
	

	private Action Atendimento = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mntmAtendimento.doClick();
		}
	};
	private static JPanel pnlFirst;
	private JMenu mnUsuarios;
	private JMenuItem mntmQtdCadastrados;
}
