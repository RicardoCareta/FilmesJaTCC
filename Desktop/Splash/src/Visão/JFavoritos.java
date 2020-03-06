package Visão;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controle.ControlFavorito;
import util.ScreenConfigs.ScreenSize;

import java.awt.Color;

@SuppressWarnings("serial")
public class JFavoritos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JPanel pnlFav;

	private ControlFavorito controlFavorito;
	
	private int IDConta;
	
	private static JFavoritos jFav;
	
	/**
	 * Launch the application.
	 */
	public static void main(int IDConta) {
		try {
			JFavoritos dialog = new JFavoritos(IDConta);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			jFav = dialog;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JFavoritos(int IDConta) {
		setTitle("Favoritos");
		setResizable(false);
		getContentPane().setBackground(new Color(0, 0, 0));
		setBackground(new Color(0, 0, 0));
		
		this.IDConta = IDConta;
		setBounds(100, 100, ScreenSize.ConvertorSize(680), ScreenSize.ConvertorSize(544));
		setLocationRelativeTo(null);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		pnlFav = new JPanel();
		Configs();
		//pnlFav.setLocation(10, 11);
		pnlFav.setBounds(ScreenSize.ConvertorSize(10), ScreenSize.ConvertorSize(11), ScreenSize.ConvertorSize(1000), ScreenSize.ConvertorSize(1000));
		contentPanel.add(pnlFav);
	}

	private void Configs(){
		controlFavorito = new ControlFavorito();
		
		pnlFav = controlFavorito.pnlFavoritos(IDConta, this);
		pnlFav.setBackground(Color.BLACK);
		pnlFav.repaint();
	}
	
	public void Update(){
		
		contentPanel.remove(pnlFav);
		contentPanel.repaint();
		pnlFav = new JPanel();
		
		Configs();
		pnlFav.setBounds(10, 11, ScreenSize.ConvertorSize(1000), ScreenSize.ConvertorSize(1000));
		contentPanel.add(pnlFav);
	}
	
	public void Close(){
		this.dispose();
	}
}
