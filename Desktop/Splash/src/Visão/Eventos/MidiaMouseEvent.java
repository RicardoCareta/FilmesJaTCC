package Visão.Eventos;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import util.AnimateSlide;

public class MidiaMouseEvent implements MouseListener {
	
	private static final int widthJIconeCatalogo = 130;
	private static final int heightJIconeCatalogo = 150;
	
	private static final int widthJICatalogoBig = 230;
	private static final int heightJICatalogoBig = 250;
	
	@Override
	public void mouseClicked(MouseEvent e) {
				//OrganizarImagem(lbl, iconToImage(lbl.getIcon()));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel lbl = (JLabel)e.getComponent();
		OrganizarImagem(lbl, iconToImage(lbl.getIcon()));
		Rectangle r = new Rectangle(lbl.getBounds().x, lbl.getBounds().y, widthJICatalogoBig, heightJICatalogoBig);
		AnimateSlide a = new AnimateSlide(lbl.getBounds(), r);
		a.startLabel(lbl);

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel lbl = (JLabel)e.getComponent();
		OrganizarImagem(lbl, iconToImage(lbl.getIcon()));
		Rectangle r = new Rectangle(lbl.getBounds().x, lbl.getBounds().y, widthJIconeCatalogo, heightJIconeCatalogo);
		AnimateSlide a = new AnimateSlide(lbl.getBounds(), r);
		a.startLabel(lbl);

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	private void OrganizarImagem(JLabel lbl, Image img) {
		Image dimg = img.getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imgI = new ImageIcon(dimg);
		lbl.setIcon(imgI);
	}

	static Image iconToImage(Icon icon) {
		if (icon instanceof ImageIcon) {
			return ((ImageIcon) icon).getImage();
		} else {
			int w = icon.getIconWidth();
			int h = icon.getIconHeight();
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice gd = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gd.getDefaultConfiguration();
			BufferedImage image = gc.createCompatibleImage(w, h);
			Graphics2D g = image.createGraphics();
			icon.paintIcon(null, g, 0, 0);
			g.dispose();
			return image;
		}
	}
}
