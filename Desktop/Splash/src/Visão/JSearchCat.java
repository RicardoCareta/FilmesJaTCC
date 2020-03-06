package Visão;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controle.ControlCatalogo;
import Controle.ControlSearch;
import Visão.Eventos.SimpleMidiaMouseEvent;
import util.ImgUtils;

@SuppressWarnings("serial")
public class JSearchCat extends JPanel {
	
	//Static Designs
	private static final int WIDTH = 1034;
	
	private static final int JUMP_LINE_PER_CAT = 10;
	private static final int SPACE_LADO_PER_CAT = 10;
	
	private static final int WIDTH_MIDIA = 130;
	private static final int HEIGTH_MIDIA = 150;
	
	private static final int START_X = 10;
	private static final int START_Y = 10;
	
	private JLabel lbl;
	
	private ControlSearch controlSearch;
	private ControlCatalogo controlCatalogo;
	
	private ImgUtils imgUtil;
	
	private SimpleMidiaMouseEvent mouse;
	
	private String s = "";
	private int IDIdioma;
	
	public JSearchCat(int idioma) {
		setBounds(0, 0, WIDTH, 500);
		setLayout(null);
		this.IDIdioma = idioma;
		controlSearch = new ControlSearch();
		controlCatalogo = new ControlCatalogo();
		setOpaque(false);
		imgUtil = new ImgUtils();
		
		mouse = new SimpleMidiaMouseEvent(this.IDIdioma);
		
	}
	
	public void Update(String s){
		this.s = s;
		Config();
	}
	
	private void Config(){
		removeAll();
		
		int countMidiaX = 0;
		
		for (Integer i : controlSearch.SelectSearch(s, IDIdioma)) {
			lbl = new JLabel();
			lbl.setName(i.toString());
			lbl.setBounds(START_X + (countMidiaX * (SPACE_LADO_PER_CAT + WIDTH_MIDIA)), START_Y, WIDTH_MIDIA, HEIGTH_MIDIA);
			ImageIcon img = controlCatalogo.getImage(i);
			imgUtil.OrganizarImagem(lbl, img, ImgUtils.LOW_RESOLUTION);
			
			lbl.addMouseListener(mouse);
			
			add(lbl);
			countMidiaX++;
		}
		
		repaint();
	}
}
