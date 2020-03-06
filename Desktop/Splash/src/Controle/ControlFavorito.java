package Controle;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Constantes.ConsAtivo;
import Constantes.ConsMidiaIcon;
import DAO.DAOFavorito;
import Modelo.ModelCatalogo;
import Modelo.ModelConta;
import Modelo.ModelFavorito;
import Visão.JCatalogoView;
import Visão.JFavoritos;
import Visão.JFulano;
import Visão.Eventos.SimpleMidiaMouseEvent;
import util.ImgUtils;
import util.ScreenConfigs.ScreenSize;

public class ControlFavorito {
	
	private List<ModelFavorito> listaFav;
	
	private ControlCatalogo controlCatalogo;
	private ControlTituloIdioma controlTituloIdioma;
	private ControlConta controlConta;
	
	private DAOFavorito daoFavorito;
	
	private ModelFavorito fav;
	private ModelCatalogo cat;
	private ModelConta conta;
	
	private ImgUtils imgUtils;
	
	private SimpleMidiaMouseEvent mouseMidia;
	
	private int IDConta;
	
	private JFavoritos jFav;
	
	private static final int FILME = 0;
	
	private static int MAX_MIDIA_PER_LINE = ScreenSize.ConvertorSize(4);
	
//	private static final boolean HIGH_RESOLUTION = true;
	private static final boolean LOW_RESOLUTION = false;

	public ControlFavorito(){
		daoFavorito = new DAOFavorito();
		listaFav = daoFavorito.Selecionar();
	}
	
	public void Inserir(int IDCat, int IDConta){
		cat = new ModelCatalogo();
		cat.setID(IDCat);
		conta = new ModelConta();
		conta.setID(IDConta);
		fav = new ModelFavorito();
		fav.setCatalogo(cat);
		fav.setConta(conta);
		fav.setAtivo(ConsAtivo.ATIVO);
		
		try {

			daoFavorito = new DAOFavorito();
			daoFavorito.Inserir(fav);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	private void Instancias(int IDConta){
		controlCatalogo = new ControlCatalogo();
		daoFavorito = new DAOFavorito();
		
		listaFav = daoFavorito.Selecionar();
		
		imgUtils = new ImgUtils();
	
		mouseMidia = new SimpleMidiaMouseEvent(this.IDConta);
		this.IDConta = IDConta;
	}
	
	public JPanel pnlFavoritos(int IDConta, JFavoritos jFav){
		this.jFav = jFav;
		JPanel retorno = new JPanel();
		retorno.setLayout(null);
		
		Instancias(IDConta);
		
		int startX = 10;
		int startY = 10;
		
		int jumpX = ScreenSize.ConvertorSize(20);
		int jumpY = ScreenSize.ConvertorSize(20);
		
		int countFav = 0;
		int countLine = 0;
		
		for (ModelFavorito modelFavorito : listaFav) {
			if(
					modelFavorito.getAtivo() == ConsAtivo.ATIVO && modelFavorito.getCatalogo().getAtivo() == ConsAtivo.ATIVO &&
					modelFavorito.getCatalogo().getIDSerieCatalogo() == FILME && modelFavorito.getConta().getID() == IDConta
					){
				//Imagens heuheu, moio
				if(countFav == MAX_MIDIA_PER_LINE){
					countLine++;
					countFav = 0;
				}
				JLabel lblImagemMidia = new JLabel();
				lblImagemMidia.setBounds(startX + (countFav * (jumpX + ConsMidiaIcon.WIDTH)), startY + (countLine * (jumpY + ConsMidiaIcon.HEIGHT)), ConsMidiaIcon.WIDTH, ConsMidiaIcon.HEIGHT);
				lblImagemMidia.setIcon(controlCatalogo.getImage(modelFavorito.getCatalogo().getID()));
				imgUtils.OrganizarImagem(lblImagemMidia, LOW_RESOLUTION);
				lblImagemMidia.setName(String.valueOf(modelFavorito.getCatalogo().getID()));
				lblImagemMidia.addMouseListener(mouseMidia);
			//	lblImagemMidia.addMouseListener(mouseFav);
				retorno.add(lblImagemMidia);
				
				JLabel lblNomeMidia = new JLabel();
				lblNomeMidia.setBounds(startX + (countFav * (jumpX + ConsMidiaIcon.WIDTH)), (startY + (ConsMidiaIcon.HEIGHT / 2) + 10) + (countLine * (jumpY + ConsMidiaIcon.HEIGHT)), ConsMidiaIcon.WIDTH, ConsMidiaIcon.HEIGHT);
				lblNomeMidia.setFont(new Font("Tahoma", Font.PLAIN, ScreenSize.ConvertorSize(14)));
				lblNomeMidia.setForeground(Color.WHITE);
				controlTituloIdioma = new ControlTituloIdioma();
				controlConta = new ControlConta();
				
				String texto = controlTituloIdioma.getTitle(modelFavorito.getCatalogo().getID(), controlConta.getContaIdiomaID(IDConta));
				
				if(texto.equals("Sem Nome")){
					lblNomeMidia.setText(modelFavorito.getCatalogo().getNome());
				}else{
					lblNomeMidia.setText(texto);
				}
				
				retorno.add(lblNomeMidia);
				
				
				JLabel lblRemover = new JLabel("O");
				lblRemover.setFont(new Font("Tahoma", Font.PLAIN, ScreenSize.ConvertorSize(22)));
				lblRemover.setBounds(lblImagemMidia.getBounds().width - lblRemover.getPreferredSize().width, lblRemover.getPreferredSize().height / 4, lblRemover.getPreferredSize().width, lblRemover.getPreferredSize().height);
				lblRemover.setIcon(JCatalogoView.coracaoCheio);
				//lblRemover.setOpaque(true);
				lblRemover.setBackground(Color.WHITE);
				//setFont(new Font("Tahoma", Font.PLAIN, 47));
				lblRemover.setName(lblImagemMidia.getName());
				lblRemover.addMouseListener(ClickRemover);
				lblImagemMidia.add(lblRemover);
				countFav++;
			}
		}
		
		int height = startY + (countLine * ConsMidiaIcon.HEIGHT * jumpY);
		int width = startX + (MAX_MIDIA_PER_LINE * jumpX * ConsMidiaIcon.WIDTH);
		//retorno.setBounds(10, 11, width, height);
	//	retorno.setSize(width, height);
		return retorno;
	}

	public void Desativar(int IDCat, int IDConta, int Ativo){
		for (ModelFavorito modelFavorito : listaFav) {
			if(IDCat == modelFavorito.getCatalogo().getID() && IDConta == modelFavorito.getConta().getID() && modelFavorito.getAtivo() == ConsAtivo.ATIVO){
				daoFavorito = new DAOFavorito();
				daoFavorito.AtivoFav(modelFavorito.getID(), Ativo);
				break;
			}
		}
	}
	
	public boolean haveFavorite(int IDFav){
		for (ModelFavorito modelFavorito : listaFav) {
			if(modelFavorito.getID() == IDFav && modelFavorito.getAtivo() == ConsAtivo.ATIVO){
				return true;
			}
		}
		
		return false;
	}
	
	public boolean haveFavorite(int IDCat, int IDConta){
		for (ModelFavorito modelFavorito : listaFav) {
			if(modelFavorito.getCatalogo().getID() == IDCat && modelFavorito.getConta().getID() == IDConta && modelFavorito.getAtivo() == ConsAtivo.ATIVO){
				return true;
			}
		}
		return false;
	}
	
MouseListener ClickRemover = new MouseListener() {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel lbl = (JLabel)e.getComponent();
			Desativar(Integer.parseInt(lbl.getName()), IDConta, ConsAtivo.DESATIVO);
			JFulano.RemoveFav(Integer.parseInt(lbl.getName()));
			//jFav.Close();
			jFav.Update();
		}
		@Override
		public void mouseEntered(MouseEvent e){
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
	};
}