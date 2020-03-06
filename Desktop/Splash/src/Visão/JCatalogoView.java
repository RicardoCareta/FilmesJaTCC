package Visão;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import Constantes.ConsAtivo;
import Constantes.ConsPanelName;
import Constantes.ConsPaths;
import Controle.ControlCatalogo;
import Controle.ControlClassificacao;
import Controle.ControlCliente;
import Controle.ControlConta;
import Controle.ControlFavorito;
import Controle.ControlHistorico;

import Modelo.ModelTop;
import Modelo.ModelTopGeneros;
import Visão.Eventos.SimpleMidiaMouseEvent;
import util.Images;

@SuppressWarnings("serial")
public class JCatalogoView extends JPanel {
	
	private List<ModelTop> listaTop;
	private List<ModelTopGeneros> listaTopGeneros;
	private List<Integer> listaCatGen;
	
	private ControlHistorico controlHistorico;
	private ControlCatalogo controlCatalogo;
	private ControlClassificacao controlClassificacao;
	private ControlConta controlConta;
	private ControlCliente controlCliente;
	private ControlFavorito controlFavorito;
	
	private JPanel jMaisAssistidos;
	
	private SimpleMidiaMouseEvent mouse;
	
	private static final int maxTopAssistidos = 6;
	private static final int maxTopGeneros = 5;
	
	private static final URL pathImagemNull = JCatalogoView.class.getResource("/Vis\u00E3o/imagens/imagemNull.jpg");
	
	private static final int widthJIconeCatalogo = 130;
	private static final int heightJIconeCatalogo = 150;
	
	private static final int jumpXIconeCatalogo = 30;
	
	private static final int jumpYGeneros = 10;
	
	private static final int spaceBorderIconeCatalogoAcesso = 100;
	
	private static final int heightSizeGenero = heightJIconeCatalogo + spaceBorderIconeCatalogoAcesso;
	
	private static final int VALUEADD = 1;
	private static final int VALUEREMOVE = 2;
	
	private Images images;
	
	private int idConta;
	
	public static ImageIcon coracaoCheio;
	public static ImageIcon coracaoVazio;
	
	public JCatalogoView(int IDConta) {
		setOpaque(false);
		
		setLayout(null);
		this.idConta = IDConta;
		Instancias();

		jMaisAssistidos = new JPanel();
		jMaisAssistidos.setOpaque(false);
		jMaisAssistidos.setBorder(new TitledBorder(UIManager.getBorder("Button.border"), "MAIS ASSISTIDOS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 0)));
		jMaisAssistidos.setBounds(0, 0, 1034, heightJIconeCatalogo + spaceBorderIconeCatalogoAcesso);
		jMaisAssistidos.setLayout(null);
		//jMaisAssistidos.setName(String.valueOf(ConsPanelName.TOP));
		
		Componentes();
		
		add(jMaisAssistidos);
	}
	
	private void Componentes(){
		
		mouse = new SimpleMidiaMouseEvent(this.idConta);
		
		int startPosYGenero = heightJIconeCatalogo + spaceBorderIconeCatalogoAcesso + 10;
		int startPosXGenero = 0;
		
		int startPosXAssistidos = 20;
		int startPosYAssistidos = 35;
		
		int countAssistidos = 0;
		int countGeneros = 0;
		int countMG = 0;
		
		jMaisAssistidos.removeAll();
		
		int maxHeightPanel = 0;
		
		for (ModelTop modelTop : listaTop) {
			
			if(countAssistidos == maxTopAssistidos){
				break;
			}
			
			//Descomente para limitar idade
			/*
			int idade = controlConta.getIdade(idConta);
			int IDPais = controlCliente.getIDPais(controlConta.getCliente(idConta));
			if(idade < controlClassificacao.getIdade(modelTop.getIDCat(), IDPais)){
				continue;
			}*/
			boolean have = false;
			for (Component c : jMaisAssistidos.getComponents()) {
				if(Integer.parseInt(c.getName()) == modelTop.getIDCat()){
					have = true;
				}
			}
			if(!have){

				JLabel iconeTop = new JLabel(controlCatalogo.getImage(modelTop.getIDCat()));
				iconeTop.setBounds(startPosXAssistidos + (countAssistidos * (jumpXIconeCatalogo + widthJIconeCatalogo)), startPosYAssistidos, widthJIconeCatalogo, heightJIconeCatalogo);
				OrganizarImagem(iconeTop, controlCatalogo.getImage(modelTop.getIDCat()));
				iconeTop.setName(String.valueOf(modelTop.getIDCat()));
				iconeTop.addMouseListener(mouse);
				jMaisAssistidos.add(iconeTop);
				
				controlFavorito = new ControlFavorito();
				JLabel lblRemover = new JLabel("O");
				
				lblRemover.setFont(new Font("Tahoma", Font.PLAIN, 22));
				lblRemover.setBounds(iconeTop.getBounds().width - lblRemover.getPreferredSize().width, lblRemover.getPreferredSize().height / 4, lblRemover.getPreferredSize().width, lblRemover.getPreferredSize().height);
				
				if(controlFavorito.haveFavorite(modelTop.getIDCat(), idConta)){
					//lblRemover = new JLabel("X");
					lblRemover.setName(String.valueOf(VALUEREMOVE));
					OrganizarImagemStatic(images.p.coracaoCheio, lblRemover);
				}else{
					//lblRemover = new JLabel("+");
					lblRemover.setName(String.valueOf(VALUEADD));
					OrganizarImagemStatic(images.p.coracaoVazio, lblRemover);
				}
				 
				
				//lblRemover.setOpaque(true);
				//lblRemover.setBackground(Color.WHITE);
				//setFont(new Font("Tahoma", Font.PLAIN, 47));
				
				lblRemover.addMouseListener(clickRemover);
				
				iconeTop.add(lblRemover);
				
				countAssistidos++;
			}
		}
		
		for (ModelTopGeneros modelTopGeneros : listaTopGeneros) {
			if(countGeneros == maxTopGeneros){
				break;
			}
			
			JPanel jGenero = new JPanel();
			jGenero.setBorder(new TitledBorder(UIManager.getBorder("Button.border"), modelTopGeneros.getGenero().getNome(), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 0)));
			jGenero.setBounds(startPosXGenero, startPosYGenero + (countGeneros * (jumpYGeneros + heightSizeGenero)), 1034, heightSizeGenero);
			jGenero.setLayout(null);
			jGenero.setOpaque(false);
			listaCatGen = new ArrayList<Integer>();
			listaCatGen = controlHistorico.getTopTG(modelTopGeneros.getGenero().getIdGen());
			countMG = 0;
			for (Integer integer : listaCatGen) {
				if(countMG == maxTopAssistidos){
					break;
				}
				

				//Descomente para limitar idade
				/*int idade = controlConta.getIdade(idConta);
				int IDPais = controlCliente.getIDPais(controlConta.getCliente(idConta));
				if(idade < controlClassificacao.getIdade(integer, IDPais)){
					continue;
				}*/
				JLabel lblImagem = new JLabel();
				lblImagem.setBounds(startPosXAssistidos + (countMG * (jumpXIconeCatalogo + widthJIconeCatalogo)), startPosYAssistidos, widthJIconeCatalogo, heightJIconeCatalogo);
				OrganizarImagem(lblImagem, controlCatalogo.getImage(integer));
				
				lblImagem.addMouseListener(mouse);
				lblImagem.setName(String.valueOf(integer));
				
				controlFavorito = new ControlFavorito();
				JLabel lblRemover = new JLabel("O"); 
				
				lblRemover.setFont(new Font("Tahoma", Font.PLAIN, 22));
				lblRemover.setBounds(lblImagem.getBounds().width - lblRemover.getPreferredSize().width, lblRemover.getPreferredSize().height / 4, lblRemover.getPreferredSize().width, lblRemover.getPreferredSize().height);
				
				if(controlFavorito.haveFavorite(integer, idConta)){
					//lblRemover = new JLabel("X");
					lblRemover.setName(String.valueOf(VALUEREMOVE));
					OrganizarImagemStatic(images.p.coracaoCheio, lblRemover);
				}else{
					//lblRemover = new JLabel("+");
					lblRemover.setName(String.valueOf(VALUEADD));
					OrganizarImagemStatic(images.p.coracaoVazio, lblRemover);
				}
				//lblRemover.setOpaque(true);
				//lblRemover.setBackground(Color.WHITE);
				//setFont(new Font("Tahoma", Font.PLAIN, 47));
				lblRemover.addMouseListener(clickRemover);
				
				
				lblImagem.add(lblRemover);
				
				jGenero.add(lblImagem);
				countMG ++;
			}
			add(jGenero);
			countGeneros++;
		}
		maxHeightPanel = heightJIconeCatalogo + spaceBorderIconeCatalogoAcesso + (heightSizeGenero * countGeneros) + (jumpYGeneros * countGeneros);
		setPreferredSize(new Dimension(1034, maxHeightPanel));
		
		jMaisAssistidos.repaint();
		
		JLabel lblRemover = new JLabel("O");
		
		lblRemover.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblRemover.setSize(lblRemover.getPreferredSize());
		coracaoCheio = OrganizarImagemImage(images.p.coracaoCheio, lblRemover);
		coracaoVazio = OrganizarImagemImage(images.p.coracaoVazio, lblRemover);
	}
	
	private void Instancias(){
		controlHistorico = new ControlHistorico();
		controlCatalogo = new ControlCatalogo();
		controlClassificacao = new ControlClassificacao();
		controlConta = new ControlConta();
		controlCliente = new ControlCliente();
		
		images = new Images();
		
		listaTop = controlHistorico.getTopMidia();
		listaTopGeneros = controlHistorico.getTopGender();
		
		
	}

	
	private  void OrganizarImagem(JLabel lbl, ImageIcon img) {
		if(img == null){
			img = new ImageIcon(pathImagemNull);
		}
		Image dimg = img.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imgI = new ImageIcon(dimg);
		lbl.setIcon(imgI);
	}
	
	//Eventos
	
	MouseListener clickRemover = new MouseListener() {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel lbl = (JLabel)e.getComponent();
			controlFavorito = new ControlFavorito();
			
			int v = Integer.parseInt(lbl.getName());
			
			if(v == VALUEADD){
				controlFavorito.Inserir(Integer.valueOf(lbl.getParent().getName()), idConta);
				//lbl.setText("X");
				
				lbl.setBounds(lbl.getBounds().x, lbl.getBounds().y, lbl.getPreferredSize().width, lbl.getPreferredSize().height);
				//OrganizarImagemStatic(images.p.coracaoCheio, lbl);
				lbl.setIcon(coracaoCheio);
				lbl.setName(String.valueOf(VALUEREMOVE));
				
				JFulano.AddFav(Integer.valueOf(lbl.getParent().getName()));
			}
			
			else if(v == VALUEREMOVE){
				controlFavorito.Desativar(Integer.valueOf(lbl.getParent().getName()), idConta, ConsAtivo.DESATIVO);
				//lbl.setText("+");
				lbl.setBounds(lbl.getBounds().x, lbl.getBounds().y, lbl.getPreferredSize().width, lbl.getPreferredSize().height);

				//OrganizarImagemStatic(images.p.coracaoVazio, lbl);
				lbl.setIcon(coracaoVazio);
				lbl.setName(String.valueOf(VALUEADD));
				
				JFulano.RemoveFav(Integer.valueOf(lbl.getParent().getName()));
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
		}
		
		@Override
		public void mouseExited(MouseEvent e) {	
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		
	};
	
	static void OrganizarImagemStatic(String url, JLabel lbl) {
		URL pathIcone2 = null;
		try {
			pathIcone2 = new URL("file:///" + url);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
	
	static ImageIcon OrganizarImagemImage(String url, JLabel lbl) {
		URL pathIcone2 = null;
		try {
			pathIcone2 = new URL("file:///" + url);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedImage img = null;
		try {
			img = ImageIO.read(pathIcone2);
		} catch (Exception e) {
			// TODO: handle exception
		}

		Image dimg = img.getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imgI = new ImageIcon(dimg);
		return imgI;

	}
}
