package util;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Constantes.ConsMidiaIcon;
import Constantes.ConsPaths;

public class ImgUtils {
	
	public static final boolean LOW_RESOLUTION = false;
	public static final boolean HIGH_RESOLUTION = true;
	
	public BufferedImage scaleImage(int WIDTH, int HEIGHT, Image img) {
	    BufferedImage bi = null;
	    try {
	        bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	        Graphics2D g2d = (Graphics2D) bi.createGraphics();
	        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
	        g2d.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	    return bi;
	}
	
	public void OrganizarImagem(int WIDTH, int HEIGTH, JLabel lbl, boolean HighResolution){
		if(lbl == null){
			lbl = new JLabel();
			lbl.setPreferredSize(new Dimension(ConsMidiaIcon.WIDTH, ConsMidiaIcon.HEIGHT));
			lbl.setIcon(new ImageIcon(ConsPaths.PATH_NULL));
		}
		if(lbl.getIcon() == null){
			lbl.setIcon(new ImageIcon(ConsPaths.PATH_NULL));
		}
		
		if(HighResolution){
			lbl.setIcon(new ImageIcon(scaleImage(WIDTH, HEIGTH, ConvertIconToImage(lbl.getIcon()))));
		}else{
			Image dimg = ConvertIconToImage(lbl.getIcon()).getScaledInstance(WIDTH, HEIGTH, Image.SCALE_SMOOTH);
			ImageIcon imgI = new ImageIcon(dimg);
			lbl.setIcon(imgI);
		}
	}
	
	public void OrganizarImagem(JLabel lbl, ImageIcon img, boolean HighResolution) {
		if(img == null){
			img = new ImageIcon(ConsPaths.PATH_NULL);
		}
		
		if(!HighResolution){
			Image dimg = img.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon imgI = new ImageIcon(dimg);
			lbl.setIcon(imgI);
		}else{
			lbl.setIcon(new ImageIcon(scaleImage(lbl.getWidth(), lbl.getHeight(), img.getImage())));
		}
	}
	
	public void OrganizarImagem(JLabel lbl, boolean HighResolution){
		if(lbl == null){
			lbl = new JLabel();
			lbl.setPreferredSize(new Dimension(ConsMidiaIcon.WIDTH, ConsMidiaIcon.HEIGHT));
			lbl.setIcon(new ImageIcon(ConsPaths.PATH_NULL));
		}
		if(lbl.getIcon() == null){
			lbl.setIcon(new ImageIcon(ConsPaths.PATH_NULL));
		}
		
		if(HighResolution){
			lbl.setIcon(new ImageIcon(scaleImage(lbl.getWidth(), lbl.getHeight(), ConvertIconToImage(lbl.getIcon()))));
		}else{
			OrganizarImagem(lbl.getWidth(), lbl.getHeight(), lbl, false);
		}
	}
	
	public ImageIcon ResizeImageIcon(JLabel lbl, ImageIcon img, boolean HighResolution){
		if(HighResolution){
			return new ImageIcon(scaleImage(lbl.getWidth(), lbl.getHeight(), img.getImage()));
		}else{
			Image dimg = img.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon imgI = new ImageIcon(dimg);
			return imgI;
		}
	}
	
	public BufferedImage ConvertIconToImage(Icon icon){
		BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.createGraphics();
		icon.paintIcon(null, g, 0, 0);
		g.dispose();
		return bi;
	}
	
	public void CircleRender(URL Path, JLabel lbl){
		BufferedImage master = null;
		try {
			master = ImageIO.read(Path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    int diameter = Math.min(master.getWidth(), master.getHeight());
	    BufferedImage mask = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = mask.createGraphics();
	    applyQualityRenderingHints(g2d);
	    g2d.fillOval(0, 0, diameter - 1, diameter - 1);
	    g2d.dispose();

	    BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
	    g2d = masked.createGraphics();
	    applyQualityRenderingHints(g2d);
	    int x = (diameter - master.getWidth()) / 2;
	    int y = (diameter - master.getHeight()) / 2;
	    g2d.drawImage(master, x, y, null);
	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
	    g2d.drawImage(mask, 0, 0, null);
	    g2d.dispose();
	    
	    lbl.setIcon(new ImageIcon(masked));
	}
	
	public void CircleRender(ImageIcon img, JLabel lbl){
		BufferedImage master = new BufferedImage(img.getIconWidth(), img.getIconHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = master.createGraphics();
		img.paintIcon(null, g, 0, 0);
		g.dispose();

	    int diameter = Math.min(master.getWidth(), master.getHeight());
	    BufferedImage mask = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = mask.createGraphics();
	    applyQualityRenderingHints(g2d);
	    g2d.fillOval(0, 0, diameter - 1, diameter - 1);
	    g2d.dispose();

	    BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
	    g2d = masked.createGraphics();
	    applyQualityRenderingHints(g2d);
	    int x = (diameter - master.getWidth()) / 2;
	    int y = (diameter - master.getHeight()) / 2;
	    g2d.drawImage(master, x, y, null);
	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
	    g2d.drawImage(mask, 0, 0, null);
	    g2d.dispose();
	    
	    lbl.setIcon(new ImageIcon(masked));
	}
	
	public static void applyQualityRenderingHints(Graphics2D g2d) {

	    g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	    g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

	}

	public ImageIcon converterImageIconSize(int width, int height, ImageIcon img){
		Image dimg = img.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(dimg);
	}
	
	public BufferedImage rotate(BufferedImage image, double angle) {
	    double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
	    int w = image.getWidth(), h = image.getHeight();
	    int neww = (int)Math.floor(w*cos+h*sin), newh = (int) Math.floor(h * cos + w * sin);
	    GraphicsConfiguration gc = getDefaultConfiguration();
	    BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
	    Graphics2D g = result.createGraphics();
	    g.translate((neww - w) / 2, (newh - h) / 2);
	    g.rotate(angle, w / 2, h / 2);
	    g.drawRenderedImage(image, null);
	    g.dispose();
	    return result;
	}

	private static GraphicsConfiguration getDefaultConfiguration() {
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice gd = ge.getDefaultScreenDevice();
	    return gd.getDefaultConfiguration();
	}
	
	public BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
	public InputStream ConvertImageToInputStream(ImageIcon img){
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		try {
			ImageIO.write(ConvertImageToRenderedImage(img.getImage()), "png", os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(os.toByteArray());
	}
	
	public RenderedImage ConvertImageToRenderedImage(Image img){

		BufferedImage bImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);

		Graphics2D bImageGraphics = bImage.createGraphics();
		
		bImageGraphics.drawImage(img, null, null);

		RenderedImage rImage = (RenderedImage)bImage;
		
		return rImage;
	}
	
	public byte[] imageToByte(String image){
	    InputStream is = null;
	    byte[] buffer = null;
	    try {
			is = new FileInputStream(image);
			buffer = new byte[is.available()];
			is.read(buffer);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	  
	    return buffer;
	}
	
	public byte[] imageToByte(InputStream image){
	    InputStream is = image;
	    byte[] buffer = null;
	    try {
			buffer = new byte[is.available()];
			is.read(buffer);
			//is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	  
	    return buffer;
	}
	
	public BufferedImage applyAlpha(BufferedImage pb, float alpha) {
	    BufferedImage img = new BufferedImage(pb.getWidth(), pb.getHeight(), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = (Graphics2D) img.getGraphics().create();
	    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	    g2.drawImage(pb, 0, 0, null);
	    g2.dispose();
	    return img;
	}	
}
