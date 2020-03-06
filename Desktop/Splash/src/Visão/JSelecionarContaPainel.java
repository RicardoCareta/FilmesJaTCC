package Visão;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controle.ControlConta;
import util.Images;

@SuppressWarnings("serial")
public class JSelecionarContaPainel extends JPanel{
	
	private Images images = new Images();
	private URL pathFundo;
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 448;
	private static final int BORDER_DEPTH = 5;
	
	private JLabel lblFundo;
	
	private ControlConta controlConta;
	
	private JFulano fulano;
	
	private int ID;
	//private int idConta;
	
	private JPanel pnlEffect;
	
	public JSelecionarContaPainel(int ID, JFulano fulano){
		pathFundo = images.p.getURL(images.p.FundoParaTudo);
		this.ID = ID;
		this.fulano = fulano;
		setBounds(100, 100, WIDTH, HEIGHT);
		setLayout(null);
		
		setOpaque(false);
		
		pnlEffect = new JPanel();
		pnlEffect.setBounds(new Rectangle(0, 0, WIDTH, HEIGHT));
		//pnlEffect.setBackground(Color.black);
		
		//add(pnlEffect);
		
		LoadContas();
		//setBackground(new Color(255, 0, 0, 10));
		
		lblFundo = new JLabel("New label");
		lblFundo.setBounds(0, 0, WIDTH, HEIGHT);
		//OrganizarImagem(pathFundo, fundoLabel);
		lblFundo.setIcon(images.Image_FundoParaTudo);
		//add(lblFundo);		
	
//		AnimateOpacity op = new AnimateOpacity(pnlEffect, 0, 0, 0, AnimateOpacity.NOT_HIDE);
		//op.start();
		
		OrganizarImagem(pathFundo, lblFundo);
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
		
		int count = 0;
		int posX = 60;
		int posY = 60;
		int width = 120;
		int height = 120;
		controlConta = new ControlConta();
		int IDConta = controlConta.getCliente(ID);
		for (Integer i : controlConta.getID(IDConta)) {
			
			JLabel lblFundo = new JLabel();
			
			lblFundo.setName(String.valueOf(i));
			lblFundo.setBounds(posX + ((width + posX) * count), posY - count * 5, width, height);	
			
			OrganizarImagem(lblFundo, controlConta.getImageIcon(i));
			lblFundo.addMouseListener(clickConta);
			add(lblFundo);
			
			JLabel lblNome = new JLabel(controlConta.getNome(i));
			lblNome.setForeground(Color.WHITE);
			lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNome.setName(String.valueOf(i));
			lblNome.setBounds(posX + ((width + posX) * count) + 30, posY + 125, lblNome.getPreferredSize().width, lblNome.getPreferredSize().height);
			lblNome.addMouseListener(clickConta);
			add(lblNome);
			
			count++;
		}
	}
	
	MouseListener clickConta = new MouseListener() {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel lbl = (JLabel)e.getComponent();
			fulano.ClickChangePerfil(Integer.parseInt(lbl.getName()));
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			JLabel lbl = (JLabel)e.getComponent();
			if(lbl.getText().equals("")){
				lbl.setBorder(BorderFactory.createLineBorder(Color.WHITE, BORDER_DEPTH));
			}
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
}
