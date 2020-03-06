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
import javax.swing.RowFilter;

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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Enum.EnumAtivo;
import Enum.EnumPreguica;
import Enum.EnumTexto;

import javax.swing.event.ChangeEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ListSelectionModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class JEditar extends JDialog {

	@SuppressWarnings("rawtypes")
	private JComboBox cbmPerso;

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JLabel lblTitulo;
	private static JLabel lblNome;
	// private static JLabel lblPerso;

	private static String Titulo = "Titulo";
	private static String Nome = "Nome";

	private static int _tipo;

	private static boolean visiblePerso = false;

	private ControlGenero genero;
	private ControlCategoria categoria;
	private ControlElenco elenco;
	private ControlPais pais;
	private ControlTipoEnvolvido tipo;
	private ControlRegiao regiao;
	private ControlIdioma idioma;

	private DefaultTableModel tblCategoria;
	private DefaultTableModel tblElenco;
	private DefaultTableModel tblGenero;
	private DefaultTableModel tblPais;
	private DefaultTableModel tblCargo;
	private DefaultTableModel tblIdioma;
	
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel cbmPersoModel;

	private int selectedRow;
	
	private boolean modoEdicao;
	private boolean todos; 
	private boolean att;
	
	private TableRowSorter<TableModel> sorterName;
	
	private RowFilter<Object, Object> rf;
	private List<RowFilter<Object, Object>> filters;
	
	/**
	 * Launch the application.
	 */

	// Tipo
	/*
	 * 1 - Categoria 2 - Nulo 3 - Elenco 4 - Genero 5 - Pais 6 - Produtora (OFF)
	 * 7 - Cargo 8 - Região 9 - Idioma
	 */

	private static JEditar dialog;

	public static void main(String[] args, int tipo) {
		try {
			visiblePerso = false;
			_tipo = tipo;
			if (tipo == EnumPreguica.Categoria.get()) {
				Titulo = "Categoria";
				Nome = "Descrição:";
				// align = 0;
			}

			if (tipo == EnumPreguica.Genero.get()) {
				Titulo = "Gênero";
				Nome = "Nome:";
				// align = 1;
			}

			if (tipo == EnumPreguica.Elenco.get()) {
				Titulo = "Elenco";
				Nome = "Nome:";
				// align = 1;
			}

			if (tipo == EnumPreguica.Pais.get()) {
				Titulo = "País";
				Nome = "Nome:";
				visiblePerso = true;
			}

			if (tipo == EnumPreguica.Produtora.get()) {
				Titulo = "Produtora";
				Nome = "Nome:";
			}

			if (tipo == EnumPreguica.Cargo.get()) {
				Titulo = "Cargo";
				Nome = "Nome:";
			}

			if (tipo == EnumPreguica.Regiao.get()) {
				Titulo = "Região";
				Nome = "Nome:";
			}

			if (tipo == EnumPreguica.Idioma.get()) {
				Titulo = "Idioma";
				Nome = "Nome:";
			}

			dialog = new JEditar();
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JEditar() {
		setTitle("Modificar");
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				dialog.requestFocus();
			}
		});

		setModal(true);
		setBounds(100, 100, 611, 488);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		modoEdicao = false;
		todos = false;
		att = false;
		
		cbmPersoModel = new DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6" });
		cbmPerso = new JComboBox();
		cbmPerso.setFont(new Font("Arial", Font.PLAIN, 12));
		cbmPerso.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(!modoEdicao){
					if(_tipo == EnumPreguica.Pais.get()){
						Filter();
					}
				}
			}
		});
		cbmPerso.setVisible(visiblePerso);
		cbmPerso.setModel(cbmPersoModel);
		cbmPerso.setBounds(113, 112, 140, 28);
		getContentPane().add(cbmPerso);

		lblTitulo = new JLabel(Titulo);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblTitulo.setForeground(Color.BLACK);
		lblTitulo.setBounds(262, 10, lblTitulo.getPreferredSize().width, lblTitulo.getPreferredSize().height);
		getContentPane().add(lblTitulo);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(238, 397, 100, 28);
		btnSalvar.addActionListener(clickUpdate);
		getContentPane().add(btnSalvar);

		lblNome = new JLabel("Nome: ");

		lblNome.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setForeground(Color.BLACK);
		lblNome.setBounds(54, 62, lblNome.getPreferredSize().width, lblNome.getPreferredSize().height);
		getContentPane().add(lblNome);

		txtNome = new JTextField();
		//TODO: KeyReleased Name
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				txtNome.setText(txtNome.getText().toUpperCase());
				if(!modoEdicao){
					Filter();
				}
			}
		});
		txtNome.setFont(new Font("Arial", Font.PLAIN, 14));
		txtNome.setBounds(113, 55, 372, 28);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);

		lblPerso = new JLabel("Regi\u00E3o: ");
		lblPerso.setVisible(visiblePerso);
		lblPerso.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerso.setForeground(Color.BLACK);
		lblPerso.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPerso.setBounds(50, 115, lblPerso.getPreferredSize().width, lblPerso.getPreferredSize().height);
		getContentPane().add(lblPerso);

		btnAjudaMapa = new JButton("Ajuda Mapa");
		btnAjudaMapa.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAjudaMapa.setVisible(visiblePerso);
		btnAjudaMapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				view.Eventos.eventoLogin.OpenURL("https://pt.wikipedia.org/wiki/C%C3%B3digo_de_Regi%C3%A3o_de_DVD");
			}
		});
		btnAjudaMapa.setBounds(262, 111, 100, 28);
		getContentPane().add(btnAjudaMapa);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 172, 575, 206);
		getContentPane().add(scrollPane);

		tblGeral = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		LoadTable();
		tblGeral.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//TODO: ClickJTable
		tblGeral.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					if (modoEdicao && !att) {
						selectedRow = tblGeral.getSelectedRow();
						txtNome.setText(tblGeral.getValueAt(selectedRow, 0).toString());
						if(_tipo == EnumPreguica.Pais.get()){
							chckbxAtivo.setSelected(Boolean.parseBoolean(tblGeral.getModel().getValueAt(selectedRow, 3).toString()));
						}else{
							chckbxAtivo.setSelected(Boolean.parseBoolean(tblGeral.getModel().getValueAt(selectedRow, 2).toString()));
						}
						
						if(_tipo == EnumPreguica.Pais.get()){
							cbmPerso.setSelectedItem(tblGeral.getModel().getValueAt(selectedRow, 2).toString());
						}
					}
				}
			}
		});
		scrollPane.setViewportView(tblGeral);
		
		chckbxModoDeEdio = new JCheckBox("Modo de Edi\u00E7\u00E3o");
		//TODO: MudarEdicao
		chckbxModoDeEdio.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (chckbxModoDeEdio.isSelected()) {
					ModoEdicao();
				} else {
					ModoConsulta();
				}
				
				Filter();
			}
		});
		chckbxModoDeEdio.setFont(new Font("Arial", Font.PLAIN, 15));
		chckbxModoDeEdio.setForeground(Color.BLACK);
		chckbxModoDeEdio.setOpaque(false);
		chckbxModoDeEdio.setBackground(Color.WHITE);
		chckbxModoDeEdio.setBounds(414, 11, chckbxModoDeEdio.getPreferredSize().width,
				chckbxModoDeEdio.getPreferredSize().height);
		getContentPane().add(chckbxModoDeEdio);
		
		chckbxAtivo = new JCheckBox("Ativo");
		//TODO: MudarAtivo
		chckbxAtivo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Filter();
			}
		});
		chckbxAtivo.setFont(new Font("Arial", Font.PLAIN, 15));
		chckbxAtivo.setForeground(Color.BLACK);
		chckbxAtivo.setOpaque(false);
		chckbxAtivo.setBounds(495, 87, chckbxAtivo.getPreferredSize().width, chckbxAtivo.getPreferredSize().height);
		getContentPane().add(chckbxAtivo);
		
		chckbxTodos = new JCheckBox("Todos");
		//TODO: MudarTodos
		chckbxTodos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				todos = chckbxTodos.isSelected();
				chckbxAtivo.setEnabled(!todos);
				Filter();
			}
		});
		chckbxTodos.setOpaque(false);
		chckbxTodos.setForeground(Color.BLACK);
		chckbxTodos.setSelected(true);
		chckbxTodos.setFont(new Font("Arial", Font.PLAIN, 15));
		chckbxTodos.setBounds(495, 55, chckbxTodos.getPreferredSize().width, chckbxTodos.getPreferredSize().height);
		getContentPane().add(chckbxTodos);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(JEditar.class.getResource("/view/imagens/FundoParaTudo.jpg")));
		label.setBounds(0, 210, 595, 237);
		//getContentPane().add(label);

		ModoConsulta();
		
		Filter();
	}

	//TODO: Filtro tabelas
	public void Filter(){
		filters = new ArrayList<RowFilter<Object, Object>>();
		if(!modoEdicao){
			filters.add(RowFilter.regexFilter(txtNome.getText(), 1));
			if(!todos){
				RowFilter<Object, Object> filterBoolean = new RowFilter<Object, Object>(){

					@Override
					public boolean include(javax.swing.RowFilter.Entry<? extends Object, ? extends Object> entry) {
						// TODO Auto-generated method stub
						for(int i = entry.getValueCount() - 1; i >=0; i--){
							//System.out.println(entry.getStringValue(i));
							if(entry.getValue(i).equals(chckbxAtivo.isSelected())){
								return true;
							}
						}
						
						return false;
					}
					
				};
				filters.add(filterBoolean);
			}
			if(_tipo == EnumPreguica.Pais.get() && !cbmPerso.getSelectedItem().toString().equals(EnumTexto.Todas.get())){
				filters.add(RowFilter.regexFilter(cbmPerso.getSelectedItem().toString(), 2));
			}
			
			
		}
		rf = RowFilter.andFilter(filters);
		
		sorterName.setRowFilter(rf);
		
	}
	
	//TODO: LoadTable
	public void LoadTable() {
		MudarPais(false);

		// Categoria
		if (_tipo == EnumPreguica.Categoria.get()) {
			categoria = new ControlCategoria();
			tblCategoria = categoria.tblCategoria();
			tblGeral.setModel(tblCategoria);
		}

		// Elenco
		if (_tipo == EnumPreguica.Elenco.get()) {
			elenco = new ControlElenco();
			tblElenco = elenco.tblElenco();
			tblGeral.setModel(tblElenco);
		}

		// Genero
		if (_tipo == EnumPreguica.Genero.get()) {
			genero = new ControlGenero();
			tblGenero = genero.tblGenero();
			tblGeral.setModel(tblGenero);
		}

		// Pais
		if (_tipo == EnumPreguica.Pais.get()) {
			MudarPais(true);
			pais = new ControlPais();
			tblPais = pais.tblPais();
			tblGeral.setModel(tblPais);
		}
		
		//Cargo
		if(_tipo == EnumPreguica.Cargo.get()){
			tipo = new ControlTipoEnvolvido();
			tblCargo = tipo.tblCargo();
			tblGeral.setModel(tblCargo);
		}
		
		//Idioma
		if(_tipo == EnumPreguica.Idioma.get()){
			idioma = new ControlIdioma();
			tblIdioma = idioma.tblIdioma();
			tblGeral.setModel(tblIdioma);
		}
		
		sorterName = new TableRowSorter<TableModel>(tblGeral.getModel()); 
		tblGeral.removeColumn(tblGeral.getColumnModel().getColumn(0));
		tblGeral.setRowSorter(sorterName);
	}

	@SuppressWarnings("unchecked")
	public void ModoEdicao() {
		modoEdicao = true;
		btnSalvar.setEnabled(true);
		cbmPersoModel = new DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6" });
		cbmPerso.setModel(cbmPersoModel);
		chckbxTodos.setSelected(false);
		chckbxTodos.setEnabled(false);
	}

	@SuppressWarnings("unchecked")
	public void ModoConsulta() {
		modoEdicao = false;
		btnSalvar.setEnabled(false);
		txtNome.setText("");
		cbmPersoModel = new DefaultComboBoxModel<>(new String[] {EnumTexto.Todas.get() ,"1", "2", "3", "4", "5", "6" });
		cbmPerso.setModel(cbmPersoModel);
		cbmPerso.setSelectedIndex(0);
		chckbxTodos.setEnabled(true);
	}

	public void MudarPais(boolean mudar) {
		cbmPerso.setVisible(mudar);
		lblPerso.setVisible(mudar);
		btnAjudaMapa.setVisible(mudar);
	}

	// TODO: EventosClicks
	ActionListener clickUpdate = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (txtNome.getText().trim().equals("")) {
				MessagemBoxCustom.ShowMensagem("Caixa de texto nome vazia", "Ops..");
				return;
			}
			
			int Ativo = 0;
			if(chckbxAtivo.isSelected()){
				Ativo = EnumAtivo.ATIVO.getAtivo();
			}else{
				Ativo = EnumAtivo.DESATIVO.getAtivo();
			}

			if(_tipo == EnumPreguica.Categoria.get()){
				categoria = new ControlCategoria();
				int IDCat = Integer.parseInt(tblCategoria.getValueAt(selectedRow, 0).toString());
				String nome = txtNome.getText();
				
				categoria.Update(IDCat, nome, Ativo);
				
			}
			
			if(_tipo == EnumPreguica.Elenco.get()){
				elenco = new ControlElenco();
				int IDElenco = Integer.parseInt(tblElenco.getValueAt(selectedRow, 0).toString());
				String nome = txtNome.getText();
				
				elenco.Update(IDElenco, nome, Ativo);
			}
			
			if(_tipo == EnumPreguica.Pais.get()){
				
				int IDPais = Integer.parseInt(tblPais.getValueAt(selectedRow, 0).toString());
				String nome = txtNome.getText();
				String regiao = cbmPerso.getSelectedItem().toString();
				
				pais = new ControlPais();
				pais.Update(IDPais, nome, regiao, Ativo);
			}
			
			if(_tipo == EnumPreguica.Cargo.get()){
				
				int IDCargo  = Integer.parseInt(tblCargo.getValueAt(selectedRow, 0).toString());
				String nome = txtNome.getText();
				tipo = new ControlTipoEnvolvido();
				tipo.Update(IDCargo, nome, Ativo);
			}
			
			if(_tipo == EnumPreguica.Idioma.get()){
				int IDIdioma = Integer.parseInt(tblIdioma.getValueAt(selectedRow, 0).toString());
				String nome = txtNome.getText();
				idioma = new ControlIdioma();
				idioma.Update(IDIdioma, nome, Ativo);
			}
			
			if (_tipo == EnumPreguica.Genero.get()){
				int IDGenero = Integer.parseInt(tblGenero.getValueAt(selectedRow, 0).toString());
				String nome = txtNome.getText();
				genero = new ControlGenero();
				genero.Update(IDGenero, nome, Ativo);
			}
			
			att = true;
			LoadTable();
			att = false;
			
			MessagemBoxCustom.ShowMensagem("Atualizado com sucesso");
			txtNome.setText("");
		}
	};
	
	private JScrollPane scrollPane;
	private JTable tblGeral;
	private JButton btnSalvar;
	private JCheckBox chckbxModoDeEdio;
	private JLabel lblPerso;
	private JButton btnAjudaMapa;
	private JCheckBox chckbxAtivo;
	private JCheckBox chckbxTodos;
}
