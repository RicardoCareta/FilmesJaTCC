package util.ScreenConfigs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class ScreenSize {
	public static int SCREEN_WIDTH = 0;
	public static int SCREEN_HEIGHT = 0;
	
	public static final int FULANO_WIDTH = 1250;
	public static final int FULANO_HEIGHT = 700;
	
	public static final int WIDTH = 1;
	public static final int HEIGHT = 2;
	
	public static int GAMBIARRADATELA = 0;
	
	public static void LoadSize(){
		JFrame frame = new JFrame("");
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		SCREEN_WIDTH = frame.getWidth();
		SCREEN_HEIGHT = frame.getHeight();
		frame.setVisible(false);
		frame.dispose();
	}
	
	public static void ApplySize(Rectangle bounds, Dimension frameSizeB, Component control){
		//pX - frameSizeB.Width
		//x  - SCREEN_WIDTH
		int x = ConvertorSize(control.getX(), frameSizeB);
		int y = ConvertorSize(control.getY(), frameSizeB);
		int width = ConvertorSize(control.getWidth(), frameSizeB);
		int height = ConvertorSize(control.getHeight(), frameSizeB);
		
		Rectangle r = new Rectangle(x, y, width, height);
		control.setBounds(r);
	}
	
	public static int ConvertorSize(int Size){
		return ((Size * SCREEN_HEIGHT) / FULANO_HEIGHT);
	}
	
	public static int ConvertorSize(int Size, Dimension frameSizeB){
		return ((Size * SCREEN_HEIGHT) / frameSizeB.height);
	}
	
	public static int ConvertorSize(int Size, int frameSizeB){
		return ((Size * SCREEN_HEIGHT) / frameSizeB);
	}
	
	public static int ConvertorSize(int Size, int frameSizeB, int Tipo){
		if(Tipo == WIDTH){
			return ((Size * SCREEN_WIDTH) / frameSizeB);
		}
		
		if(Tipo == HEIGHT){
			return ((Size * SCREEN_HEIGHT) / frameSizeB);
		}
		return 0;
	}
}
