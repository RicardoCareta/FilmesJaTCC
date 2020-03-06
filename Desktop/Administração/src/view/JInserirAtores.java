package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Enum.EnumAtivo;
import Enum.EnumPreguica;
import control.ControlCategoria;
import control.ControlGenero;
import control.ControlIdioma;
import control.ControlElenco;
import control.ControlPais;
import control.ControlRegiao;
import control.ControlTipoEnvolvido;

import javax.swing.SwingConstants;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class JInserirAtores extends JDialog {

	//TODO: Variaveis
	
	@SuppressWarnings("rawtypes")
	private JComboBox cbmPerso;
	
	private static final int CATEGORIA = 1;
	private static final int GENERO = 4;
	private static final int ELENCO = 3;
	private static final int PAIS = 5;
	private static final int PRODUTORA = 6;
	private static final int CARGO = 7;
	private static final int REGIAO = 8;
	private static final int IDIOMA = 9;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JLabel lblTitulo;
	private static JLabel lblNome;
	//private static JLabel lblPerso;
	
	private static String Titulo = "Titulo";
	private static String Nome = "Nome";
	
	private static int _tipo;
	private List<Integer> listLastIDS;
	private List<Integer> listLastIDSD;
	
	private static boolean visiblePerso = false;
	private static boolean desfazer = false;
	private boolean refazer = false;
	
	private DefaultTableModel modelTBLUltimoDados;
	
	
	private ControlGenero genero;
	private ControlCategoria categoria;
	private ControlElenco elenco;
	private ControlPais pais;
	private ControlTipoEnvolvido tipo;
	private ControlRegiao regiao;
	private ControlIdioma idioma;
	/**
	 * Launch the application.
	 */
	
	private static JInserirAtores dialog;
	
	public static void main(String[] args, int tipo) {
		try {
			visiblePerso = false;
			_tipo = tipo;
			if(tipo == 1){
				Titulo = "Categoria";
				Nome = "Descrição:";
				//align = 0;
			}
			
			if(tipo == 4){
				Titulo = "Gênero";
				Nome = "Nome:";
				//align = 1;
			}
			
			if(tipo == 3){
				Titulo = "Elenco";
				Nome = "Nome:";
				//align = 1;
			}
			
			if(tipo == 5){
				Titulo = "País";
				Nome = "Nome:";
				visiblePerso = true;
			}
			
 			if (tipo == 6){
				Titulo = "Produtora";
				Nome = "Nome:";
			}
 			
 			if (tipo == 7){
 				Titulo = "Cargo";
 				Nome = "Nome:";
 			}
			
 			if(tipo == 8){
 				Titulo = "Região";
 				Nome = "Nome:";
 			}
 			
 			if(tipo == 9){
 				Titulo = "Idioma";
 				Nome = "Nome:";
 			}
 			
 			dialog = new JInserirAtores();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	public void AtivarCombo(){
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JInserirAtores() {
		setTitle("Inserir " + Titulo);
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				dialog.requestFocus();
			}
		});
		
		listLastIDS = new ArrayList<Integer>();
		listLastIDSD = new ArrayList<Integer>();
		
		setModal(true);
		setBounds(100, 100, 600, 417);
		getContentPane().setLayout(null);
		
		setResizable(false);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		cbmPerso = new JComboBox();
		cbmPerso.setVisible(visiblePerso);
		cbmPerso.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
		cbmPerso.setBounds(122, 103, 140, 28);
		getContentPane().add(cbmPerso);
		
		lblTitulo = new JLabel(Titulo);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
		lblTitulo.setForeground(Color.BLACK);
		lblTitulo.setBounds(252, 0, 135, 34);
		getContentPane().add(lblTitulo);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCadastrar.setBounds(171, 144, 91, 28);
		btnCadastrar.addActionListener(clickCadastrar);
		getContentPane().add(btnCadastrar);
		
		lblNome = new JLabel("Nome: ");

		lblNome.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setForeground(Color.BLACK);
		lblNome.setBounds(70, 67, lblNome.getPreferredSize().width, lblNome.getPreferredSize().height);
		getContentPane().add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNome.setBounds(122, 60, 374, 30);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblPerso = new JLabel("Regi\u00E3o: ");
		lblPerso.setVisible(visiblePerso);
		lblPerso.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerso.setForeground(Color.BLACK);
		lblPerso.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPerso.setBounds(63, 108, lblPerso.getPreferredSize().width, lblPerso.getPreferredSize().height);
		getContentPane().add(lblPerso);
		
		JButton btnAjudaMapa = new JButton("Ajuda Mapa");
		btnAjudaMapa.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAjudaMapa.setVisible(visiblePerso);
		btnAjudaMapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				view.Eventos.eventoLogin.OpenURL("https://pt.wikipedia.org/wiki/C%C3%B3digo_de_Regi%C3%A3o_de_DVD");
			}
		});
		btnAjudaMapa.setBounds(296, 103, 100, 28);
		getContentPane().add(btnAjudaMapa);
		
		btnDesfazer = new JButton("Desfazer");
		btnDesfazer.setFont(new Font("Arial", Font.PLAIN, 12));
		btnDesfazer.setBounds(306, 144, 91, 28);
		btnDesfazer.addActionListener(clickDesfazer);
		btnDesfazer.setEnabled(desfazer);
		getContentPane().add(btnDesfazer);
		
		btnRefazer = new JButton("Refazer");
		btnRefazer.setBounds(416, 151, 81, 23);
		btnRefazer.addActionListener(clickRefazer);
		btnRefazer.setEnabled(refazer);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 180, 570, 193);
		getContentPane().add(scrollPane);
		
		tblUltimoDados = new JTable();
		if(visiblePerso){

			modelTBLUltimoDados = new DefaultTableModel(new Object[][] {}, new String[] { "ID", Nome, "Região" });
		}else{

			modelTBLUltimoDados = new DefaultTableModel(new Object[][] {}, new String[] { "ID", Nome });
		}
	
		tblUltimoDados.setModel(modelTBLUltimoDados);
		
		tblUltimoDados.removeColumn(tblUltimoDados.getColumnModel().getColumn(0));
		
		scrollPane.setViewportView(tblUltimoDados);
		//getContentPane().add(btnRefazer);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Arial", Font.PLAIN, 12));
		label.setIcon(new ImageIcon(JInserirAtores.class.getResource("/view/imagens/FundoParaTudo.jpg")));
		label.setBounds(0, 0, 559, 330);
		//getContentPane().add(label);
	}
	
	ActionListener clickCadastrar = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int lastId = 0;
			if(txtNome.getText().trim().equals("")){
				System.out.println("Vazio");
				return;
			}
			
			if(_tipo == 1){
				try {
					//Categoria
					categoria = new ControlCategoria();
					categoria.Inserir(txtNome.getText());
					listLastIDS.add(categoria.getLastID());
					lastId = listLastIDS.get(listLastIDS.size() - 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(_tipo == 4){
				//Gênero
				try {
					genero = new ControlGenero();
					genero.Inserir(txtNome.getText());
					listLastIDS.add(genero.getLastID());
					lastId = listLastIDS.get(listLastIDS.size() - 1);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			
			if(_tipo == 3){
				//Elenco
				try {
					elenco = new ControlElenco();
					elenco.Inserir(txtNome.getText());
					listLastIDS.add(elenco.getLastID());
					lastId = listLastIDS.get(listLastIDS.size() - 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(_tipo == 5){
				//Pais
				try {
					pais = new ControlPais();
					pais.Inserir(txtNome.getText(), cbmPerso.getSelectedItem().toString());
					listLastIDS.add(pais.getLastID());
					lastId = listLastIDS.get(listLastIDS.size() - 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
 			if (_tipo == 6){
 				try{
 					//produtora = new ControlProdutora();
 					//produtora.Inserir(txtNome.getText());
 				} catch (Exception e){
 					e.printStackTrace();
 				}
			}
 			
 			if(_tipo == 7){
 				try {
					tipo = new ControlTipoEnvolvido();
					tipo.Inserir(txtNome.getText());
					listLastIDS.add(tipo.getLastID());
					lastId = listLastIDS.get(listLastIDS.size() - 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
 			}
 			
 			if(_tipo == 8){
 				try {
					regiao = new ControlRegiao();
					regiao.Inserir(txtNome.getText());
				} catch (Exception e) {
					e.printStackTrace();
				}
 			}
 			
 			if(_tipo == 9){
 				try {
					idioma = new ControlIdioma();
					idioma.Inserir(txtNome.getText());
					listLastIDS.add(idioma.getLastID());
					lastId = listLastIDS.get(listLastIDS.size() - 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
 			}
 			
 			if(!desfazer){
 				desfazer = true;
 				UpdateDesfazer();
 			}
 			
 			MessagemBoxCustom.ShowMensagem("Cadastrado com sucesso");
 			if(visiblePerso){
 				modelTBLUltimoDados.insertRow(0, new Object[] {
 		 			lastId, txtNome.getText(), 	cbmPerso.getSelectedItem().toString()
 		 		});
 			}else{
 				modelTBLUltimoDados.insertRow(0, new Object[] {
 					lastId, txtNome.getText()
 				});
 			}
 			txtNome.setText("");
 			cbmPerso.setSelectedIndex(-1);
		}
	};
	
	private void UpdateDesfazer(){
		btnDesfazer.setEnabled(desfazer);
	}
	
	private void UpdateRefazer(){
		btnRefazer.setEnabled(refazer);
	}
	//A base é simples assim
	//Beleza ganha de comedia
	//Comedia ganha de gentileza
	//Gentileza perde pra beleza e para comedia
	//Logo a gentileza é a mais fraca entre as três
	
	
	private void ClickDesfazer(){

		int row = tblUltimoDados.getSelectedRow();
		if(row < 0){
			return;
		}
		
		int id = Integer.parseInt(tblUltimoDados.getModel().getValueAt(row, 0).toString());
		String nome = tblUltimoDados.getModel().getValueAt(row, 1).toString();
	
		if(_tipo == CATEGORIA){
			categoria = new ControlCategoria();
			categoria.Update(id, nome, EnumAtivo.INACESSIVEL.getAtivo());
		}
		
		if(_tipo == GENERO){
			genero = new ControlGenero();
			genero.Update(id, nome, EnumAtivo.INACESSIVEL.getAtivo());
		}
		
		if(_tipo == ELENCO){
			elenco = new ControlElenco();
			elenco.Update(id, nome, EnumAtivo.INACESSIVEL.getAtivo());
		}
		
		if(_tipo == PAIS){
			pais = new ControlPais();
			String item = tblUltimoDados.getModel().getValueAt(row, 2).toString();
			pais.Update(id, nome, item, EnumAtivo.INACESSIVEL.getAtivo());
		}
		
		if (_tipo == PRODUTORA){
		}
			
		if (_tipo == CARGO){
			tipo = new ControlTipoEnvolvido();
			tipo.Update(id, nome, EnumAtivo.INACESSIVEL.getAtivo());
		}
		
		if(_tipo == REGIAO){
			regiao = new ControlRegiao();
			regiao.Update(id, nome, EnumAtivo.INACESSIVEL.getAtivo());
		}
			
		if(_tipo == IDIOMA){
			idioma = new ControlIdioma();
			idioma.Update(id, nome, EnumAtivo.INACESSIVEL.getAtivo());
		}
		
		
		txtNome.setText(nome);
		if(visiblePerso){
			cbmPerso.setSelectedItem(tblUltimoDados.getModel().getValueAt(row, 2).toString());
		}
		((DefaultTableModel)tblUltimoDados.getModel()).removeRow(row);
	}
	
	//TODO: clickDesfazer
	ActionListener clickDesfazer = new ActionListener() {
		
		@SuppressWarnings("unused")
		@Override
		public void actionPerformed(ActionEvent e) {
			ClickDesfazer();
			if(true){
				return;
			}
			if(listLastIDS.size() == 0){
				MessagemBoxCustom.ShowMensagem("Não tem o que desfazer", "Ops..");
				return;
			}
			//X - 1
			//Z - 2
			//A - 3
			
			//id = 2, Z
			int id = listLastIDS.get(listLastIDS.size() - 1);
			
			refazer = true;
			UpdateRefazer();
			
			if(_tipo == EnumPreguica.Categoria.get()){
				txtNome.setText(categoria.getCategoriaNome(id));
				categoria.Update(id, txtNome.getText(), EnumAtivo.INACESSIVEL.getAtivo());
			}
			
			//X - 1
			listLastIDS.remove(listLastIDS.size() - 1);
			//A - 3
			//Z - 2
		//	listLastIDSD.add(id);
			
			if(listLastIDS.isEmpty()){
				desfazer = false;
				UpdateDesfazer();
			}
		}
	};
	
	//TODO:Click Refazer
	ActionListener clickRefazer = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(listLastIDSD.isEmpty()){
				MessagemBoxCustom.ShowMensagem("Desfaça algo para refaze-lo", "Ops..");
				return;
			}
						
			int listSize = listLastIDSD.size();
			
			//A - 3
			//Z - 2
			
			//ID = 3, A
			
			int id = 0;
			
			if(listSize == 1){
				txtNome.setText("");
				
				id = listLastIDSD.get(0);
				listLastIDSD.remove(0);
				listLastIDS.add(id);
				
				refazer = false;
				UpdateRefazer();
				return;
			}
			
			id = listLastIDSD.get(listSize - 1);
			
			desfazer = true;
			UpdateDesfazer();
			
			
			
			if(_tipo == EnumPreguica.Categoria.get()){
				txtNome.setText(categoria.getCategoriaNome(id));
				categoria.Update(id, txtNome.getText(), EnumAtivo.ATIVO.getAtivo());
			}
			
			listLastIDSD.remove(listSize - 1);
			listLastIDS.add(id);
		//	System.out.println(listLastIDS.get(listLastIDS.size() - 1) + " " + listLastIDS.size());
			if(listLastIDSD.isEmpty()){
				
				refazer = false;
				UpdateRefazer();
			}
		}
	};
	private static JButton btnRefazer;
	private static JButton btnDesfazer;
	private JTable tblUltimoDados;
	private JScrollPane scrollPane;
}
