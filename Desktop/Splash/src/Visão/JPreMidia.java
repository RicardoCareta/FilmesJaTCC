package Visão;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Constantes.ConsPreMidia;
import Controle.ControlCatalogo;
import Controle.ControlFavorito;
import Controle.ControlGeneroCatalogo;
import Controle.ControlHistorico;
import Controle.ControlSinopseCatalogo;
import Controle.ControlTituloIdioma;
import util.CreateJSON;
import util.ImgUtils;

import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class JPreMidia extends JDialog {

	//Se a história fosse ser contada, ficaria longa.
	//'Por mais que seja impressionante, eu que terei que fazer isso.."
	private static JPreMidia dialog;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblVoltar;
	private JLabel lblSinopse;
	private JLabel lblTitulo;

	private ControlSinopseCatalogo controlSinopse;
	private ControlTituloIdioma controlTI;
	private ControlCatalogo controlCatalogo;
	private ControlGeneroCatalogo controlGC;
	private ControlHistorico controlHistorico;
	private ControlFavorito controlFavorito;
	
	private ImgUtils imgUtils;
	
	private int IDCat;
	private int IDIdi;
	private int IDConta;
	private JTable tblEps;
	
	private DefaultTableModel tblModelEps;
	
	private JScrollPane scrollPane;
	private JLabel lblTemporada;
	private JComboBox<?> cbmTemp;
	private JLabel lblGneros;
	private JLabel lblGenero;
	private JLabel lblFundo;
	private JButton btnPlay;
	private JLabel lblCarinha1;
	private JLabel lblCarinha2;
	private JLabel lblCarinha3;
	private JLabel lblControlFavorito;
	
	private static final String PATH_FELIZ = "/Vis\u00E3o/imagens/feliz.png";
	private static final String N_PATH_FELIZ = "/Vis\u00E3o/imagens/felizVerdinho.png";
	
	private static final String PATH_NAQUELAS = "/Vis\u00E3o/imagens/naquelas.png";
	private static final String N_PATH_NAQUELAS = "/Vis\u00E3o/imagens/naquelasAmarelo.png";
	
	private static final String PATH_DEPRE = "/Vis\u00E3o/imagens/depre.png";
	private static final String N_PATH_DEPRE = "/Vis\u00E3o/imagens/depreVermelinho.png";
	
	private boolean savedNota = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(int[] args) {
		try {
			dialog = new JPreMidia(args);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JPreMidia(int[] args) {
	//	setUndecorated(true);
		setBounds(100, 100, 450, 530);
		setLocationRelativeTo(null);
		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		setModal(true);
		
		IDIdi = args[ConsPreMidia.IDIDI];
		IDCat = args[ConsPreMidia.IDCAT];
		IDConta = args[ConsPreMidia.IDCONTA];
		
		lblVoltar = new JLabel("Voltar");
		lblVoltar.setForeground(Color.WHITE);
		lblVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dialog.dispose();
			}
		});
		lblVoltar.setBounds(12, 463, lblVoltar.getPreferredSize().width, lblVoltar.getPreferredSize().height);
		contentPanel.add(lblVoltar);
		
		lblSinopse = new JLabel("Sinopse");
		lblSinopse.setForeground(Color.WHITE);
		lblSinopse.setBounds(166, 139, lblSinopse.getPreferredSize().width, lblSinopse.getPreferredSize().height);
		contentPanel.add(lblSinopse);
		
		lblTitulo = new JLabel("titulo");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(166, 11, lblTitulo.getPreferredSize().width, lblTitulo.getPreferredSize().height);
		contentPanel.add(lblTitulo);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 238, 414, 187);
		contentPanel.add(scrollPane);
		
		tblEps = new JTable(){
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int column){
				return false;
			};
		};
		tblEps.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblEps);
		
		lblTemporada = new JLabel("N\u00BA Temporada:");
		lblTemporada.setForeground(Color.WHITE);
		lblTemporada.setBounds(10, 213, lblTemporada.getPreferredSize().width, lblTemporada.getPreferredSize().height);
		lblTemporada.setVisible(false);
		contentPanel.add(lblTemporada);
		
		cbmTemp = new JComboBox<Object>();
		cbmTemp.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ConfigTable();
			}
		});
		cbmTemp.setBounds(103, 211, 59, 20);
		cbmTemp.setVisible(false);
		contentPanel.add(cbmTemp);
		
		lblGneros = new JLabel("G\u00EAnero(s):");
		lblGneros.setForeground(Color.WHITE);
		lblGneros.setBounds(10, 176, lblGneros.getPreferredSize().width, lblGneros.getPreferredSize().height);
		contentPanel.add(lblGneros);
		
		lblGenero = new JLabel("lblGeneros");
		lblGenero.setForeground(Color.WHITE);
		lblGenero.setBounds(81, 176, lblGenero.getPreferredSize().width, lblGenero.getPreferredSize().height);
		contentPanel.add(lblGenero);
		
		btnPlay = new JButton("PLAY");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateJSON cj = new CreateJSON();
				int valorId = 0;
				if(!controlCatalogo.isSerie(IDCat)){
					valorId = IDCat;
				}
				cj.create(controlCatalogo.getCaminhoVideo(valorId));
				JVideoPlayer.Show(valorId, JFulano.getIDConta());
				dialog.dispose();
			}
		});
		imgUtils = new ImgUtils();
		btnPlay.setBounds(166, 491, 98, 26);
		contentPanel.add(btnPlay);
		
		lblCarinha1 = new JLabel("1");
		lblCarinha1.setIcon(new ImageIcon(JPreMidia.class.getResource(PATH_DEPRE)));

		lblCarinha1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AvaliarCatalogo(1, lblCarinha1);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				ResetAll();
				lblCarinha1.setIcon(new ImageIcon(JPreMidia.class.getResource(N_PATH_DEPRE)));
				imgUtils.OrganizarImagem(lblCarinha1, new ImageIcon(JPreMidia.class.getResource(N_PATH_DEPRE)), false);
				
				savedNota = false;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!savedNota){
					lblCarinha1.setIcon(new ImageIcon(JPreMidia.class.getResource(PATH_DEPRE)));
					imgUtils.OrganizarImagem(lblCarinha1, new ImageIcon(JPreMidia.class.getResource(PATH_DEPRE)), false);
				}
			}
		});
		lblCarinha1.setForeground(Color.WHITE);
		lblCarinha1.setBounds(94, 436, 68, 43);
		//imgUtils.OrganizarImagem(lblCarinha1, ImgUtils.LOW_RESOLUTION);
		imgUtils.OrganizarImagem(lblCarinha1, new ImageIcon(JPreMidia.class.getResource(PATH_DEPRE)), false);
		contentPanel.add(lblCarinha1);
		
		lblCarinha2 = new JLabel("2");
		lblCarinha2.setIcon(new ImageIcon(JPreMidia.class.getResource(PATH_NAQUELAS)));
		lblCarinha2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//"Como eu era ingênuo, sonhador demais. Queria um mudo perfeito, queria achar a paz"
				AvaliarCatalogo(2, lblCarinha2);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				ResetAll();
				lblCarinha2.setIcon(new ImageIcon(JPreMidia.class.getResource(N_PATH_NAQUELAS)));
				imgUtils.OrganizarImagem(lblCarinha2, new ImageIcon(JPreMidia.class.getResource(N_PATH_NAQUELAS)), false);
				
				savedNota = false;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!savedNota){
					lblCarinha2.setIcon(new ImageIcon(JPreMidia.class.getResource(PATH_NAQUELAS)));
					imgUtils.OrganizarImagem(lblCarinha2, new ImageIcon(JPreMidia.class.getResource(PATH_NAQUELAS)), false);
				}
			}
		});
		lblCarinha2.setForeground(Color.WHITE);
		lblCarinha2.setBounds(172, 436, 68, 43);
		imgUtils.OrganizarImagem(lblCarinha2, new ImageIcon(JPreMidia.class.getResource(PATH_NAQUELAS)), false);
		contentPanel.add(lblCarinha2);
		
		lblCarinha3 = new JLabel("3");
		lblCarinha3.setIcon(new ImageIcon(JPreMidia.class.getResource("/Vis\u00E3o/imagens/feliz.png")));
		lblCarinha3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Achava que era capaz, de acabar com guerras e trazer a paz, mas...
				AvaliarCatalogo(3, lblCarinha3);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				ResetAll();
				lblCarinha3.setIcon(new ImageIcon(JPreMidia.class.getResource(N_PATH_FELIZ)));
				imgUtils.OrganizarImagem(lblCarinha3, new ImageIcon(JPreMidia.class.getResource(N_PATH_FELIZ)), false);
				
				savedNota = false;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				//A minha persistencia vai me levar ao lugar almejado
				//A desistencia não existe em meu vocabulario
				//Como ser heroi sem individualidade
				//Na ausencia de poder, preencho com força de vontade
				if(!savedNota){
					lblCarinha3.setIcon(new ImageIcon(JPreMidia.class.getResource(PATH_FELIZ)));
					imgUtils.OrganizarImagem(lblCarinha3, new ImageIcon(JPreMidia.class.getResource(PATH_FELIZ)), false);
				}
			}
		});
		lblCarinha3.setForeground(Color.WHITE);
		lblCarinha3.setBounds(252, 436, 68, 43);
		imgUtils.OrganizarImagem(lblCarinha3, new ImageIcon(JPreMidia.class.getResource(PATH_FELIZ)), false);
		contentPanel.add(lblCarinha3);
		
		lblControlFavorito = new JLabel("Favoritos");
		lblControlFavorito.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ConfigFavorito();
			}
		});
		lblControlFavorito.setForeground(Color.WHITE);
		lblControlFavorito.setBounds(341, 467, 55, 16);
		contentPanel.add(lblControlFavorito);
		
		lblFundo = new JLabel("");
		lblFundo.setBounds(0, 0, this.getWidth(), this.getHeight());
		
		contentPanel.add(lblFundo);
	
		Configs();
	}
	
	private void ConfigFavorito(){
		if(lblControlFavorito.getText().indexOf("Remover") != -1){
		//	controlFavorito.Desativar(IDCat, IDConta, );
		}
	}
	
	private void ResetAll(){
		lblCarinha3.setIcon(new ImageIcon(JPreMidia.class.getResource(PATH_FELIZ)));
		imgUtils.OrganizarImagem(lblCarinha3, new ImageIcon(JPreMidia.class.getResource(PATH_FELIZ)), false);
		
		lblCarinha2.setIcon(new ImageIcon(JPreMidia.class.getResource(PATH_NAQUELAS)));
		imgUtils.OrganizarImagem(lblCarinha2, new ImageIcon(JPreMidia.class.getResource(PATH_NAQUELAS)), false);
		
		lblCarinha1.setIcon(new ImageIcon(JPreMidia.class.getResource(PATH_DEPRE)));
		imgUtils.OrganizarImagem(lblCarinha1, new ImageIcon(JPreMidia.class.getResource(PATH_DEPRE)), false);
	}
	
	@SuppressWarnings("unchecked")
	private void Configs(){
		controlTI = new ControlTituloIdioma();
		lblTitulo.setText(controlTI.getTitle(IDCat, IDIdi));
		controlSinopse = new ControlSinopseCatalogo();
		lblSinopse.setText(controlSinopse.getSinopse(IDCat, IDIdi));
		
		controlCatalogo = new ControlCatalogo();
		
		if(controlCatalogo.isSerie(IDCat)){
			cbmTemp.setModel(controlCatalogo.getTemporadas(IDCat));
			cbmTemp.setVisible(true);
			lblTemporada.setVisible(true);
			
			ConfigTable();
			
		}else{
			scrollPane.setVisible(false);
		}
		
		lblTitulo.setBounds(lblTitulo.getBounds().x, lblTitulo.getBounds().y, lblTitulo.getPreferredSize().width, lblTitulo.getPreferredSize().height);
		lblSinopse.setBounds(lblSinopse.getBounds().x, lblSinopse.getBounds().y, lblSinopse.getPreferredSize().width, lblSinopse.getPreferredSize().height);
		
		controlGC = new ControlGeneroCatalogo();
		lblGenero.setText(controlGC.Generos(IDCat));
		lblGenero.setSize(lblGenero.getPreferredSize().width, lblGenero.getPreferredSize().height);
		
		imgUtils.OrganizarImagem(lblFundo, new ImageIcon(imgUtils.applyAlpha(imgUtils.toBufferedImage(controlCatalogo.getImage(IDCat).getImage()), 0.2f)), ImgUtils.HIGH_RESOLUTION);
	
		controlHistorico = new ControlHistorico();
		int avaliacao = controlHistorico.getAvaliacao(IDConta, IDCat);
		
		if(avaliacao == 1){
			lblCarinha1.setIcon(new ImageIcon(JPreMidia.class.getResource(N_PATH_DEPRE)));
			imgUtils.OrganizarImagem(lblCarinha1, new ImageIcon(JPreMidia.class.getResource(N_PATH_DEPRE)), false);
		}
		
		if(avaliacao == 2){
			lblCarinha2.setIcon(new ImageIcon(JPreMidia.class.getResource(N_PATH_NAQUELAS)));
			imgUtils.OrganizarImagem(lblCarinha2, new ImageIcon(JPreMidia.class.getResource(N_PATH_NAQUELAS)), false);
		}
		
		if(avaliacao == 3){
			lblCarinha3.setIcon(new ImageIcon(JPreMidia.class.getResource(N_PATH_FELIZ)));
			imgUtils.OrganizarImagem(lblCarinha3, new ImageIcon(JPreMidia.class.getResource(N_PATH_FELIZ)), false);
		}
		
		controlFavorito = new ControlFavorito();
		if(controlFavorito.haveFavorite(IDCat, IDConta)){
			lblControlFavorito.setText("Remover Favorito");
		}else{
			lblControlFavorito.setText("Adicionar Favorito");
		}
		
		lblControlFavorito.setBounds(341, 467, lblControlFavorito.getPreferredSize().width, lblControlFavorito.getPreferredSize().height);
	}
	
	private void ConfigTable(){
		tblEps.removeAll();
		tblModelEps = new DefaultTableModel();
		tblModelEps = controlCatalogo.tblEps(IDCat, IDIdi, Integer.parseInt(cbmTemp.getSelectedItem().toString()));
		tblEps.setModel(tblModelEps);
		
		tblEps.getColumnModel().removeColumn(tblEps.getColumnModel().getColumn(0));;
		//tblEps.getColumnModel().removeColumn(tblEps.getColumnModel().getColumn(0));
	}
	
	private void AvaliarCatalogo(int Carinha, JLabel lbl){
		controlHistorico = new ControlHistorico();
		controlHistorico.AtualizarAvaliacao(IDConta, Carinha, IDCat);
		
		MessagemBoxCustom.ShowMensagem("Acho que deu certo, já que não deu erro", MessagemBoxCustom.DONT_CLOSE);
	
		/*lblCarinha1.setForeground(Color.white);
		lblCarinha2.setForeground(Color.WHITE);
		lblCarinha3.setForeground(Color.white);*/
		//System.out.println(Carinha);
		if(Carinha == 1){
			lblCarinha1.setIcon(new ImageIcon(JPreMidia.class.getResource(N_PATH_DEPRE)));
			imgUtils.OrganizarImagem(lblCarinha1, new ImageIcon(JPreMidia.class.getResource(N_PATH_DEPRE)), false);
		}
		
		if(Carinha == 2){
			lblCarinha2.setIcon(new ImageIcon(JPreMidia.class.getResource(N_PATH_NAQUELAS)));
			imgUtils.OrganizarImagem(lblCarinha2, new ImageIcon(JPreMidia.class.getResource(N_PATH_NAQUELAS)), false);
		}
		
		if(Carinha == 3){
			lblCarinha3.setIcon(new ImageIcon(JPreMidia.class.getResource(N_PATH_FELIZ)));
			imgUtils.OrganizarImagem(lblCarinha3, new ImageIcon(JPreMidia.class.getResource(N_PATH_FELIZ)), false);
		}
		
		savedNota = true;
	
		//lbl.setForeground(Color.YELLOW);
	}
}
