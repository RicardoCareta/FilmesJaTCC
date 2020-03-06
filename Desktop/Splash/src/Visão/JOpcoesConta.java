package Visão;

import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;

@SuppressWarnings("serial")
public class JOpcoesConta extends JPanel{
	//Tamanho do Painel
	private static final int WIDTH = 900;
	private static final int HEIGHT = 484;
	
	/* Fontes */
	//Nomes das Fontes

	//Padrao
	private static final String FONTE_PADRAO = "Arial";
	
	//Tamanho das Fontes
	//Padrao
	private static final int TAMANHO_PADRAO = 20;
	
	/* Declaração variaveis do design*/
	//Static booleans
	
	private static final boolean ENTRO = true;
	private static final boolean SAIU = false;
	
	//JLabel
	
	private JLabel lblFavoritos;
		//Posicoes
		private static final int X_FAVORITOS = 137;
		private static final int Y_FAVORITOS = 181;
		
	private JLabel lblConta;
		//Posicoes
		private static final int X_CONTA = 622;
		private static final int Y_CONTA = 181;
		
	private JLabel lblConfiguracoes;
		//Posicoes
		private static final int X_CONFIG = 120;
		private static final int Y_CONFIG = 398;
	
	//Fonts
	private Font fontPadrao;
	
	public JOpcoesConta(int ID){
		setLayout(null);
		setSize(WIDTH, HEIGHT);
		
		//Instanciar fontes
		//Padrao
		fontPadrao = new Font(FONTE_PADRAO, Font.PLAIN, TAMANHO_PADRAO);
		
		//Instanciar Labels, colocar imagens depois
		//Favoritos
		lblFavoritos = new JLabel("Favoritos");
		lblFavoritos.setFont(fontPadrao);
		lblFavoritos.setBounds(X_FAVORITOS, Y_FAVORITOS, lblFavoritos.getPreferredSize().width, lblFavoritos.getPreferredSize().height);
		lblFavoritos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFavoritos.main(ID);
			}
			
			@Override
			public void mouseEntered(MouseEvent e){
				ChangeColorHover(ENTRO, e);
			}
			
			//"Não importe o inimigo e nem se meu corpo, algo faz meu corpo mexer, e eu ergo a minha cabeça"
			//E q chuvinha ta caindo - 18/01/17
			
			@Override
			public void mouseExited(MouseEvent e){
				ChangeColorHover(SAIU, e);
			}
		});
		
		//Minha Conta
		lblConta = new JLabel("Minha Conta");
		lblConta.setFont(fontPadrao);
		lblConta.setBounds(X_CONTA, Y_CONTA, lblConta.getPreferredSize().width, lblConta.getPreferredSize().height);
		lblConta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JConta.main(ID);
			}
			
			@Override
			public void mouseEntered(MouseEvent e){
				ChangeColorHover(ENTRO, e);
			}
			
			@Override
			public void mouseExited(MouseEvent e){
				ChangeColorHover(SAIU, e);
			}
		});
		
		//Configuracoes
		lblConfiguracoes = new JLabel("Configurações");
		lblConfiguracoes.setFont(fontPadrao);
		lblConfiguracoes.setBounds(X_CONFIG, Y_CONFIG, lblConfiguracoes.getPreferredSize().width, lblConfiguracoes.getPreferredSize().height);
		lblConfiguracoes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e){
				ChangeColorHover(ENTRO, e);
			}
			
			@Override
			public void mouseExited(MouseEvent e){
				ChangeColorHover(SAIU, e);
			}
		});
		
		//Add os bang no JPanel
		add(lblFavoritos);
		add(lblConta);
		add(lblConfiguracoes);
		
		//Se é loco bixo, mó sono, "São milhoes de corações, que pulsam ao meu redor, mas pra mim não mais do que um simples piscar de olhos.."
		//Que musica legal a q está em aspas
		
	}
	
	private void ChangeColorHover(boolean entro, MouseEvent e) {

		JLabel lbl = (JLabel) e.getComponent();
		Font font = null;
		if (entro) {
			font = new Font(lbl.getFont().getName(), Font.BOLD, lbl.getFont().getSize());
		} else {
			font = new Font(lbl.getFont().getName(), Font.PLAIN, lbl.getFont().getSize());
		}
		((JLabel) e.getComponent()).setFont(font);
		lbl.setBounds(lbl.getBounds().x, lbl.getBounds().y, lbl.getPreferredSize().width, lbl.getPreferredSize().height);
	}
}
