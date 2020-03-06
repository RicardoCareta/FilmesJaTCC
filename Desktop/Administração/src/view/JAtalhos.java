package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;

import control.ControlAtalhosLocal;
import util.ImgUtils;

public class JAtalhos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3097517466214070311L;

	private final JPanel contentPanel = new JPanel();

	private ControlAtalhosLocal atalhosLocal;
	private JTable tblAtalhos;
	private JTextField txtAtalho;
	
	private List<Integer> atalhosCode; 
	private JTextField txtConfiguracao;
	private JScrollPane scrollPane;
	private JLabel lblConfigurao;
	
	private ImgUtils img;
	private JLabel lblAtalho;
	
	/**
	 * Launch the application.
	 */
	public static void main(String args) {
		try {
			JAtalhos dialog = new JAtalhos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			if(args != null){
				dialog.addWindowListener(new WindowListener() {
					public void windowClosing(WindowEvent arg0) {
						JControleAdministrador.GoBackPanel(JControleAdministrador.PanelConfig);
					}

					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowDeactivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowDeiconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowIconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowOpened(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JAtalhos() {
		setTitle("Atalhos");
		//getContentPane().setBackground(Color.BLACK);
		atalhosLocal = new ControlAtalhosLocal();
		
		atalhosCode = new ArrayList<>();
		
		setBounds(100, 100, 600, 419);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 130, 564, 242);
		getContentPane().add(scrollPane);
		
		tblAtalhos = new JTable();
		scrollPane.setViewportView(tblAtalhos);
		tblAtalhos.setModel(atalhosLocal.tblAtalhos());
		
		lblAtalho = new JLabel("Atalho:");
		lblAtalho.setBounds(73, 40, lblAtalho.getPreferredSize().width, lblAtalho.getPreferredSize().height);
		lblAtalho.setForeground(Color.BLACK);
		getContentPane().add(lblAtalho);
		
		txtAtalho = new JTextField();
		txtAtalho.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				
				if(!txtAtalho.hasFocus()){
					return;
				}
				
				if(keyCode == 10){
					txtConfiguracao.requestFocus();
					return;
				}
				
				if(atalhosCode.indexOf(keyCode) == -1){
					atalhosCode.add(keyCode);
				}
				
				AplicarTextoTeclas();
				
			}
			@Override
			public void keyReleased(KeyEvent e) {

				if(!txtAtalho.hasFocus()){
					return;
				}
				
				for (int i = 0; i < atalhosCode.size(); i++) {
					if(atalhosCode.get(i) == e.getKeyCode()){
						atalhosCode.remove(i);	
					}
				}
				
				
				AplicarTextoTeclas();
			}
		});
		txtAtalho.setBounds(122, 39, 222, 20);
		getContentPane().add(txtAtalho);
		txtAtalho.setColumns(10);
		
		txtConfiguracao = new JTextField();
		txtConfiguracao.setBounds(122, 70, 161, 20);
		getContentPane().add(txtConfiguracao);
		txtConfiguracao.setColumns(10);
		
		lblConfigurao = new JLabel("Configura\u00E7\u00E3o:");
		lblConfigurao.setBounds(35, 71, lblConfigurao.getPreferredSize().width, lblConfigurao.getPreferredSize().height);
		lblConfigurao.setForeground(Color.BLACK);
		getContentPane().add(lblConfigurao);
		TableColumnModel tcm = tblAtalhos.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));
		
		tblAtalhos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				String atalho = tblAtalhos.getValueAt(tblAtalhos.getSelectedRow(), 1).toString();
				txtAtalho.setText(atalho);
			}
		});
		
		JLabel label = new JLabel("");
		label.setBackground(Color.BLACK);
		label.setFont(new Font("Arial", Font.PLAIN, 12));
		label.setIcon(new ImageIcon(JInserirAtores.class.getResource("/view/imagens/FundoParaTudo.jpg")));
		label.setBounds(0, 0, 1200, 704);
		
		img = new ImgUtils();
		img.OrganizarImagem(label, new ImageIcon(JInserirAtores.class.getResource("/view/imagens/FundoParaTudo.jpg")), false);
		
		//getContentPane().add(label);
		
		contentPanel.setLayout(null);
		
	}
	
	private void AplicarTextoTeclas(){
		txtAtalho.setText("");
		
		for (Integer integer : atalhosCode) {
			String tecla = KeyEvent.getKeyText(integer);
			
			tecla = ValidaoSenha(tecla);
			
			txtAtalho.setText(txtAtalho.getText() + tecla + " ");
		}
	}
	
	private String ValidaoSenha(String tecla){

		if(tecla.equals("Ctrl")){
			tecla = "control";
		}
		
		if(tecla.equals("Alt")){
			tecla = "alt";
		}
		
		return tecla;
	}
}
