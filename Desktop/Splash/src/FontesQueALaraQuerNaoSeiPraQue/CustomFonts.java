package FontesQueALaraQuerNaoSeiPraQue;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

import java.io.IOException;
import java.io.InputStream;

public class CustomFonts {
	//private static final String ANTIGONI = CustomFonts.class.getResource("FontesQueALaraQuerNaoSeiPraQue/antigoni.tff").toString();
	
	
	public static Font getAntigoni(){
		Font font = null;
		try {
			InputStream is = CustomFonts.class.getResourceAsStream("antigoni.ttf");
			font = Font.createFont(Font.TRUETYPE_FONT, is);

			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
			
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return font;
	}
}
