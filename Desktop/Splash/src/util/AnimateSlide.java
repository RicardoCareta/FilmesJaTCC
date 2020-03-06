package util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimateSlide {

	public static final int RUN_TIME = 200;

	private JPanel panel;
	
	private ImgUtils util;
	private Rectangle from;
	private Rectangle to;

	private long startTime;

	public AnimateSlide(JPanel panel, Rectangle from, Rectangle to) {
		this.panel = panel;
		this.from = from;
		this.to = to;
	}
	
	public AnimateSlide(Rectangle from, Rectangle to) {
		this.from = from;
		this.to = to;
		util = new ImgUtils();
	}

	public void start() {
		Timer timer = new Timer(40, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				long duration = System.currentTimeMillis() - startTime;
				double progress = (double) duration / (double) RUN_TIME;
				if (progress > 1f) {
					progress = 1f;
					((Timer) e.getSource()).stop();
				}
				Rectangle target = calculateProgress(from, to, progress);
				panel.setBounds(target);
			}
		});
		timer.setRepeats(true);
		timer.setCoalesce(true);
		timer.setInitialDelay(0);
		startTime = System.currentTimeMillis();
		timer.start();
	}
	
	public void startLabel(JLabel lbl) {
		Timer timer = new Timer(40, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				long duration = System.currentTimeMillis() - startTime;
				double progress = (double) duration / (double) RUN_TIME;
				if (progress > 1f) {
					progress = 1f;
					((Timer) e.getSource()).stop();
				}
				Rectangle target = calculateProgress(from, to, progress);
				lbl.setBounds(target);
				lbl.setIcon(new ImageIcon((Image)util.scaleImage(lbl.getBounds().width, lbl.getBounds().height, iconToImage(lbl.getIcon()))));
				//OrganizarImagem(lbl, iconToImage(lbl.getIcon()));
			}
		});
		timer.setRepeats(true);
		timer.setCoalesce(true);
		timer.setInitialDelay(0);
		startTime = System.currentTimeMillis();
		timer.start();
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

	public static Rectangle calculateProgress(Rectangle startBounds, Rectangle targetBounds, double progress) {

		Rectangle bounds = new Rectangle();

		if (startBounds != null && targetBounds != null) {

			bounds.setLocation(calculateProgress(startBounds.getLocation(), targetBounds.getLocation(), progress));
			bounds.setSize(calculateProgress(startBounds.getSize(), targetBounds.getSize(), progress));

		}

		return bounds;

	}

	public static Point calculateProgress(Point startPoint, Point targetPoint, double progress) {

		Point point = new Point();

		if (startPoint != null && targetPoint != null) {

			point.x = calculateProgress(startPoint.x, targetPoint.x, progress);
			point.y = calculateProgress(startPoint.y, targetPoint.y, progress);

		}

		return point;

	}

	public static int calculateProgress(int startValue, int endValue, double fraction) {

		int value = 0;
		int distance = endValue - startValue;
		value = (int) Math.round((double) distance * fraction);
		value += startValue;

		return value;
	}

	public static Dimension calculateProgress(Dimension startSize, Dimension targetSize, double progress) {

		Dimension size = new Dimension();

		if (startSize != null && targetSize != null) {

			size.width = calculateProgress(startSize.width, targetSize.width, progress);
			size.height = calculateProgress(startSize.height, targetSize.height, progress);

		}

		return size;
	}
}
