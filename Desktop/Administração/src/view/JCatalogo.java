package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.sun.imageio.plugins.common.ImageUtil;

import Enum.ConsColunaCatalogo;
import Enum.EnumAL;
import Enum.EnumAcesso;
import Enum.EnumTexto;
import control.ControlAL;
import control.ControlAcesso;
import control.ControlCatalogo;
import control.ControlCatalogoEnvolvidos;
import control.ControlCategoria;
import control.ControlClassificacao;
import control.ControlElenco;
import control.ControlGenero;
import control.ControlGeneroCatalogo;
import control.ControlIdioma;
import control.ControlPais;
import control.ControlPaisCatalogo;
import control.ControlSinopseCatalogo;
import control.ControlTipoEnvolvido;
import control.ControlTituloCatalogo;
import control.ControlTituloIdioma;
import util.ImgUtils;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Frame;

@SuppressWarnings("serial")
public class JCatalogo extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtAno;
	private JTextField txtCaminhoVideo;
	private JTextField txtCaminhoImagem;
	private JTextField txtNEp;
	private JTextField txtNTemp;

	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel defaultCbmGenero;
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel defaultCbmTipo;
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel defaultCbmPais;
	// O de baixo é para pais de origem o de cima é para o regiao
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel defaultCbmPaises;
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel defaultCbmCategoria;
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel defaultCbmPaisClass;
	@SuppressWarnings({ "rawtypes" })
	private DefaultComboBoxModel defaultCbmCatalogo;

	@SuppressWarnings("rawtypes")
	private JComboBox cbmCategoria;
	@SuppressWarnings("rawtypes")
	private JComboBox cbmGenero;
	@SuppressWarnings("rawtypes")
	private JComboBox cbmTipo;
	// Esse primeiro de baixo é para regiao
	@SuppressWarnings("rawtypes")
	private JComboBox cbmPaises;
	@SuppressWarnings("rawtypes")
	private JComboBox cbmRegiao;
	// Esse aq em baixo é para pais de origem
	@SuppressWarnings("rawtypes")
	private JComboBox cbmPais;

	// private String Envolvidos = "Envolvidos";
	// private String Legendas = "Legendas";
	// private String Audios = "Audios";
	// private String Generos = "Gêneros";
	// private String Paises = "Paises";
	// private String Regiao = "Região";

	private DefaultTableModel tableModelGenero;
	private DefaultTableModel tableModelPesquisaElenco;
	private DefaultTableModel tableModelElenco;
	private DefaultTableModel tableModelAcesso;
	private DefaultTableModel tableModelAudios;
	private DefaultTableModel tableModelLegendas;
	private DefaultTableModel tableModelPais;
	private DefaultTableModel tableModelTitulos;
	private DefaultTableModel tableModelSinopse;
	private DefaultTableModel tableModelClassificacao;
	

	private ControlGenero controlGenero;
	private ControlElenco controlElenco;
	private ControlTipoEnvolvido controlTipo;
	private ControlPais controlPais;
	private ControlCategoria controlCategoria;
	private ControlCatalogo controlCatalogo;
	private ControlPaisCatalogo controlPC;
	private ControlGeneroCatalogo controlGC;
	private ControlTituloCatalogo controlTC;
	private ControlTituloIdioma controlTI;
	private ControlIdioma controlIdioma;
	private ControlSinopseCatalogo controlSC;
	private ControlClassificacao controlClass;
	private ControlCatalogoEnvolvidos controlCE;
	private ControlAL controlAL;
	private ControlAcesso controlAcesso;

	private JCheckBox chckbxProibido;

	private ButtonGroup bGAL;
	private ButtonGroup bgFS;

	private JLabel lblNDeE;
	private JLabel lblNDaTemporada;

	private JCatalogo catalogo = this;

	private int CountLegenda = 0;

	private int ID = 0;

	/**
	 * Launch the application.
	 */
	static JCatalogo frame;
	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					//UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new JCatalogo();
					frame.setTitle("Adicionar Catalogo");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JCatalogo() {
		setExtendedState(Frame.MAXIMIZED_BOTH);

		ImgUtils img = new ImgUtils();
		
		controlElenco = new ControlElenco();
		controlTipo = new ControlTipoEnvolvido();
		controlPais = new ControlPais();
		controlCategoria = new ControlCategoria();
		controlCatalogo = new ControlCatalogo();
		controlAL = new ControlAL();
		tableModelAudios = new DefaultTableModel(new Object[][] {},
				new String[] { EnumTexto.Idioma.get(), EnumTexto.Caminho.get() });
		tableModelLegendas = new DefaultTableModel(new Object[][] {},
				new String[] { EnumTexto.Idioma.get(), EnumTexto.Caminho.get() });
		tableModelTitulos = new DefaultTableModel(new Object[][] {},
				new String[] { EnumTexto.Idioma.get(), EnumTexto.Titulo.get() });
		tableModelSinopse = new DefaultTableModel(new Object[][] {},
				new String[] { EnumTexto.Idioma.get(), EnumTexto.Sinopse.get() });
		tableModelClassificacao = new DefaultTableModel(new Object[][] {}, new String[] { "Idade", "Pais" });
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1378, 744);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				super.mouseReleased(arg0);
				contentPane.requestFocusInWindow();
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setLocationRelativeTo(null);

		JLabel lblNome = new JLabel("T\u00EDtulo:");
		lblNome.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNome.setBounds(85, 56, 52, 20);
		contentPane.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(128, 53, 412, 28);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblAno = new JLabel("Ano:");
		lblAno.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblAno.setBounds(95, 96, lblAno.getPreferredSize().width, lblAno.getPreferredSize().height);
		contentPane.add(lblAno);

		txtAno = new JTextField();
		txtAno.addKeyListener(keyJustNumber);
		txtAno.setColumns(10);
		txtAno.setBounds(128, 93, 120, 28);
		contentPane.add(txtAno);

		JLabel lblCaminhoVideo = new JLabel("Caminho Video:");
		lblCaminhoVideo.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblCaminhoVideo.setBounds(21, 137, lblCaminhoVideo.getPreferredSize().width,
				lblCaminhoVideo.getPreferredSize().height);
		contentPane.add(lblCaminhoVideo);

		txtCaminhoVideo = new JTextField();
		txtCaminhoVideo.setColumns(10);
		txtCaminhoVideo.setBounds(128, 134, 412, 28);
		contentPane.add(txtCaminhoVideo);

		JLabel lblCaminhoImagem = new JLabel("Caminho Imagem:");
		lblCaminhoImagem.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblCaminhoImagem.setBounds(4, 323, lblCaminhoImagem.getPreferredSize().width,
				lblCaminhoImagem.getPreferredSize().height);
		contentPane.add(lblCaminhoImagem);

		txtCaminhoImagem = new JTextField();
		txtCaminhoImagem.setColumns(10);
		txtCaminhoImagem.setBounds(128, 320, 307, 28);
		contentPane.add(txtCaminhoImagem);

		JButton btnPesquisarImagem = new JButton("...");
		btnPesquisarImagem.setBounds(447, 320, 46, 28);
		btnPesquisarImagem.addActionListener(clickPesquisarFoto);
		contentPane.add(btnPesquisarImagem);

		txtNEp = new JTextField();
		txtNEp.setBounds(128, 174, 67, 28);
		txtNEp.addKeyListener(keyJustNumber);
		contentPane.add(txtNEp);
		txtNEp.setColumns(10);

		lblNDeE = new JLabel("N\u00BA do Episodio:");
		lblNDeE.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNDeE.setBounds(25, 177, lblNDeE.getPreferredSize().width, lblNDeE.getPreferredSize().height);
		contentPane.add(lblNDeE);

		txtNTemp = new JTextField();
		txtNTemp.setColumns(10);
		txtNTemp.addKeyListener(keyJustNumber);
		txtNTemp.setBounds(368, 174, 67, 28);
		contentPane.add(txtNTemp);

		lblNDaTemporada = new JLabel("N\u00BA da Temporada:");
		lblNDaTemporada.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNDaTemporada.setBounds(246, 177, lblNDaTemporada.getPreferredSize().width,
				lblNDaTemporada.getPreferredSize().height);
		contentPane.add(lblNDaTemporada);

		cbmCategoria = new JComboBox();
		cbmCategoria.setBounds(342, 94, 198, 28);
		contentPane.add(cbmCategoria);

		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblCategoria.setBounds(274, 96, lblCategoria.getPreferredSize().width, lblCategoria.getPreferredSize().height);
		contentPane.add(lblCategoria);

		tableModelGenero = new DefaultTableModel(new Object[][] {}, new String[] { "Gêneros" });

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Elenco", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(59, 59, 59)));
		panel.setBounds(771, 53, 564, 252);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollElenco = new JScrollPane();
		scrollElenco.setBounds(6, 104, 552, 142);
		panel.add(scrollElenco);

		tblElenco = new JTable();
		tblElenco.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = tblElenco.rowAtPoint(arg0.getPoint());
				tableModelElenco.removeRow(row);
				tblElenco.repaint();
			}
		});
		tableModelElenco = new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "Função" });
		tblElenco.setModel(tableModelElenco);
		tblElenco.getColumnModel().getColumn(0).setPreferredWidth(164);
		tblElenco.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollElenco.setViewportView(tblElenco);

		tblPesquisaElenco = new JTable(new DefaultTableModel(new Object[][] {}, new String[] { "" }));
		
		txtPesquisaElenco = new JTextField(10);
		txtPesquisaElenco.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tblPesquisaElenco.getRowCount() != 0) {
					txtPesquisaElenco.setText(tblPesquisaElenco.getValueAt(0, 0).toString());
				}
				clickAddElenco.actionPerformed(null);
				tblPesquisaElenco.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "" }));
			}
		});
		txtPesquisaElenco.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				tblPesquisaElenco.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "" }));
			}
		});
		txtPesquisaElenco.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				txtPesquisaElenco.setText(txtPesquisaElenco.getText().toUpperCase());

				tableModelPesquisaElenco = new DefaultTableModel(new Object[][] {}, new String[] { "" });
				tblPesquisaElenco.setModel(tableModelPesquisaElenco);

				if (txtPesquisaElenco.getText().trim().equals("")) {
					tblPesquisaElenco.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "" }));
				}

				tblPesquisaElenco.setTableHeader(null);

				for (String s : controlElenco.getListLimit(txtPesquisaElenco.getText().toUpperCase())) {
					tableModelPesquisaElenco.addRow(new Object[] { s });
				}
				txtPesquisaElenco.requestFocus();
				if (tblPesquisaElenco.getRowCount() != 0) {
					tblPesquisaElenco.setRowSelectionInterval(0, 0);
				}

			}
		});
		txtPesquisaElenco.setBounds(6, 30, 309, 28);

		panel.add(txtPesquisaElenco);
		txtPesquisaElenco.setColumns(10);
		tableModelPesquisaElenco = new DefaultTableModel(new Object[][] {}, new String[] { " " });

		JButton btnAddElenco = new JButton("+");
		btnAddElenco.setBounds(489, 30, 44, 28);
		btnAddElenco.addActionListener(clickAddElenco);
		panel.add(btnAddElenco);

		cbmTipo = new JComboBox();
		cbmTipo.setBounds(327, 31, 150, 26);
		panel.add(cbmTipo);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBorder(null);
		scrollPane_3.setBounds(6, 54, 309, 59);
		scrollPane_3.setViewportView(tblPesquisaElenco);
		panel.add(scrollPane_3);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(171, 173, 179)), "Acesso", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(1193, 300, 38, 28);
		//contentPane.add(panel_1);

		cbmPaises = new JComboBox();
		cbmRegiao = new JComboBox();
		cbmRegiao.setModel(
				new DefaultComboBoxModel(new String[] { EnumTexto.Todas.get(), "1", "2", "3", "4", "5", "6" }));
		cbmRegiao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaisCombo();
			}
		});
		cbmRegiao.setSelectedIndex(0);
		cbmRegiao.setBounds(83, 18, 187, 28);
		panel_1.add(cbmRegiao);

		JButton btnAddAcesso = new JButton("+");
		btnAddAcesso.setBounds(282, 35, 44, 28);
		btnAddAcesso.addActionListener(clickAddAcesso);
		panel_1.add(btnAddAcesso);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 104, 320, 124);
		panel_1.add(scrollPane);

		tblAcesso = new JTable();
		tableModelAcesso = new DefaultTableModel(new Object[][] {}, new String[] { "Pais", "Região", "Proibido" }) {
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};
		tblAcesso.setModel(tableModelAcesso);
		tblAcesso.getColumnModel().getColumn(0).setPreferredWidth(160);

		scrollPane.setViewportView(tblAcesso);

		cbmPaises.setBounds(83, 47, 187, 28);
		panel_1.add(cbmPaises);

		JLabel lblRegio = new JLabel("Regi\u00E3o:");
		lblRegio.setBounds(16, 24, 55, 16);
		panel_1.add(lblRegio);

		JLabel lblPaises = new JLabel("Paises:");
		lblPaises.setBounds(16, 53, 55, 16);
		panel_1.add(lblPaises);

		chckbxProibido = new JCheckBox("Proibido");
		chckbxProibido.setBounds(6, 81, 74, 18);
		panel_1.add(chckbxProibido);

		bGAL = new ButtonGroup();

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Op\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(1130, 522, 198, 97);
		contentPane.add(panel_3);

		rdbtnLegendas = new JRadioButton("Legendas");
		rdbtnLegendas.setBounds(85, 34, 96, 18);
		panel_3.add(rdbtnLegendas);
		bGAL.add(rdbtnLegendas);

		rdbtnAudio = new JRadioButton("Audios");
		rdbtnAudio.setBounds(6, 34, 78, 18);
		panel_3.add(rdbtnAudio);
		bGAL.add(rdbtnAudio);

		btnConfigurar = new JButton("Configurar");
		btnConfigurar.setBounds(42, 63, 113, 28);
		panel_3.add(btnConfigurar);

		bgFS = new ButtonGroup();

		rdbtnFilme = new JRadioButton("Filme");
		rdbtnFilme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNEp.setEnabled(false);
				txtNTemp.setEnabled(false);
				lblNDaTemporada.setEnabled(false);
				lblNDeE.setEnabled(false);
				cbmCatalogo.setEnabled(false);
			}
		});
		rdbtnFilme.setFont(new Font("SansSerif", Font.PLAIN, 15));
		rdbtnFilme.setBounds(127, 21, rdbtnFilme.getPreferredSize().width, rdbtnFilme.getPreferredSize().height);
		contentPane.add(rdbtnFilme);

		rdbtnSerie = new JRadioButton("S\u00E9rie");
		rdbtnSerie.setFont(new Font("SansSerif", Font.PLAIN, 15));
		rdbtnSerie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtNEp.setEnabled(true);
				txtNTemp.setEnabled(true);
				lblNDaTemporada.setEnabled(true);
				lblNDeE.setEnabled(true);
				cbmCatalogo.setEnabled(true);
			}
		});
		rdbtnSerie.setSelected(true);
		rdbtnSerie.setBounds(209, 21, rdbtnSerie.getPreferredSize().width, rdbtnSerie.getPreferredSize().height);
		contentPane.add(rdbtnSerie);
		btnConfigurar.addActionListener(clickConfigurar);

		bgFS.add(rdbtnFilme);
		bgFS.add(rdbtnSerie);

		JButton btnAjudaOpcoes = new JButton("?");
		btnAjudaOpcoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MessagemBoxCustom.ShowMensagem(
						"<html><center>Filme: Qualquer coisa que não tenha episódios/temporadas<br>Série: Qualquer coisa que tenha episódios/temporadas</center>",
						"Aviso!!!");
			}
		});
		btnAjudaOpcoes.setBounds(277, 18, 46, 28);
		contentPane.add(btnAjudaOpcoes);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Pa\u00EDs de Origem", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(771, 326, 276, 193);
		contentPane.add(panel_2);

		cbmPais = new JComboBox();
		cbmPais.setBounds(6, 20, 187, 28);
		panel_2.add(cbmPais);

		JButton btnAddPais = new JButton("+");
		btnAddPais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (cbmPais.getSelectedIndex() == 0) {
					return;
				}

				for (int i = 0; i < tableModelPais.getRowCount(); i++) {
					if (cbmPais.getSelectedItem().toString().equals(tableModelPais.getValueAt(i, 0))) {
						return;
					}
				}

				tableModelPais.addRow(new Object[] { cbmPais.getSelectedItem().toString() });

			}
		});
		btnAddPais.setBounds(205, 20, 44, 28);
		panel_2.add(btnAddPais);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 53, 264, 134);
		panel_2.add(scrollPane_1);

		tableModelPais = new DefaultTableModel(new Object[][] {}, new String[] { "Pais:" });

		tblPais = new JTable();
		tblPais.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = tblPais.rowAtPoint(arg0.getPoint());
				tableModelPais.removeRow(row);
				tblPais.repaint();
			}
		});
		tblPais.setModel(tableModelPais);
		scrollPane_1.setViewportView(tblPais);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(1182, 661, 103, 36);
		btnCadastrar.addActionListener(clickCadastrar);
		contentPane.add(btnCadastrar);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Sinopse", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(128, 205, 307, 106);
		contentPane.add(panel_4);
		
				txtSinopse = new JTextArea();
				txtSinopse.setBounds(6, 16, 295, 84);
				panel_4.add(txtSinopse);
				txtSinopse.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						if (txtSinopse.getText().equals("Digite aqui a sinopse, bem resumida.\r\nPor favor!!")) {
							txtSinopse.setText("");
							txtSinopse.setForeground(Color.BLACK);
							txtSinopse.setFont(new Font("SansSerif", Font.PLAIN, 12));
						}
					}

					@Override
					public void focusLost(FocusEvent arg0) {
						if (txtSinopse.getText().trim().length() == 0) {
							txtSinopse.setFont(new Font("SansSerif", Font.ITALIC, 12));
							txtSinopse.setText("Digite aqui a sinopse, bem resumida.\r\nPor favor!!");
							txtSinopse.setForeground(Color.GRAY);
						}
					}
				});
		ConfigTxtSinopse();

		JButton btnAbrirTitulos = new JButton("+");
		btnAbrirTitulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JConfigurarTitulos.main(null, tableModelTitulos, catalogo);
			}
		});
		btnAbrirTitulos.setBounds(552, 53, 46, 28);
		contentPane.add(btnAbrirTitulos);

		JButton btnAddSinopse = new JButton("+");
		btnAddSinopse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JConfigurarSinopse.main(null, tableModelSinopse, catalogo);
			}
		});
		btnAddSinopse.setBounds(447, 283, 46, 28);
		contentPane.add(btnAddSinopse);

		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Classifica\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_5.setBounds(771, 519, 347, 179);
		contentPane.add(panel_5);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(6, 74, 337, 95);
		panel_5.add(scrollPane_2);

		tblClass = new JTable();
		tblClass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = tblClass.rowAtPoint(arg0.getPoint());
				tableModelClassificacao.removeRow(row);
				tblClass.repaint();
			}
		});
		tblClass.setModel(tableModelClassificacao);
		scrollPane_2.setViewportView(tblClass);

		cbmPaisClass = new JComboBox();
		cbmPaisClass.setBounds(40, 26, 134, 26);
		panel_5.add(cbmPaisClass);

		lblPais = new JLabel("Pais:");
		lblPais.setBounds(6, 31, lblPais.getPreferredSize().width, lblPais.getPreferredSize().height);
		panel_5.add(lblPais);

		lblIdade = new JLabel("Idade:");
		lblIdade.setBounds(184, 31, lblIdade.getPreferredSize().width, lblIdade.getPreferredSize().height);
		panel_5.add(lblIdade);

		JButton btnAddClass = new JButton("+");
		btnAddClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cbmPaisClass.getSelectedIndex() == 0) {
					return;
				}

				for (int i = 0; i < tableModelClassificacao.getRowCount(); i++) {
					if (tableModelClassificacao.getValueAt(i, 1).toString().equals(
							cbmPaisClass.getSelectedItem().toString()) || txtIdade.getText().trim().equals("")) {
						return;
					}
				}

				if (txtIdade.getText().equals("0")) {
					tableModelClassificacao.addRow(new Object[] { "LIVRE", cbmPaisClass.getSelectedItem().toString() });
				} else {
					tableModelClassificacao
							.addRow(new Object[] { txtIdade.getText(), cbmPaisClass.getSelectedItem().toString() });
				}
			}
		});
		btnAddClass.setBounds(297, 25, 44, 28);
		panel_5.add(btnAddClass);

		txtIdade = new JTextField();
		txtIdade.setBounds(223, 25, 62, 28);
		txtIdade.addKeyListener(keyJustNumber);
		panel_5.add(txtIdade);
		txtIdade.setColumns(10);

		// TODO:ImagemVisu
		btnVisu = new JButton("Visualiza\u00E7\u00E3o");
		btnVisu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImageIcon img = null;
				if (txtCaminhoImagem.getText().trim().equals("") && cbmCatalogo.getSelectedIndex() == 0) {
					MessagemBoxCustom.ShowMensagem("Amigo, não tem imagem para mostrar");
					return;
				}

				if (txtCaminhoImagem.getText().equals(controlCatalogo.getModel(ID, ConsColunaCatalogo.IMAGEM))) {
					img = controlCatalogo.getImage(ID);
				} else {
					img = new ImageIcon(txtCaminhoImagem.getText());
				}

				JViewImagem.main(img);
			}
		});
		btnVisu.setBounds(451, 208, 100, 28);
		//contentPane.add(btnVisu);

		cbmCatalogo = new JComboBox();
		cbmCatalogo.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbmCatalogo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				itemChangedCatalogo(arg0);
			}
		});
		cbmCatalogo.setBounds(421, 17, 119, 28);
		contentPane.add(cbmCatalogo);

		lblPertence = new JLabel("Pertence \u00E0:");
		lblPertence.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPertence.setBounds(341, 22, lblPertence.getPreferredSize().width, lblPertence.getPreferredSize().height);
		contentPane.add(lblPertence);
		
				JPanel pnlGenero = new JPanel();
				pnlGenero.setBounds(1059, 326, 276, 193);
				contentPane.add(pnlGenero);
				pnlGenero.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "G\u00EAnero", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				pnlGenero.setLayout(null);
				
						cbmGenero = new JComboBox();
						cbmGenero.setBounds(17, 21, 187, 28);
						pnlGenero.add(cbmGenero);
						
								JButton btnAddGenero = new JButton("+");
								btnAddGenero.setBounds(216, 21, 44, 28);
								btnAddGenero.addActionListener(clickAddGenero);
								pnlGenero.add(btnAddGenero);
								
										JScrollPane scrollGenero = new JScrollPane();
										scrollGenero.setBounds(6, 53, 264, 135);
										pnlGenero.add(scrollGenero);
										tblGenero = new JTable();
										tblGenero.setModel(tableModelGenero);
										tblGenero.addMouseListener(new MouseAdapter() {
											@Override
											public void mouseClicked(MouseEvent arg0) {
												int row = tblGenero.rowAtPoint(arg0.getPoint());
												tableModelGenero.removeRow(row);
												tblGenero.repaint();
											}
										});
										scrollGenero.setViewportView(tblGenero);
										
										lblFoto = new JLabel("New label");
										lblFoto.setBorder(new LineBorder(new Color(0, 0, 0)));
										lblFoto.setForeground(Color.PINK);
										lblFoto.setBackground(Color.RED);
										lblFoto.setBounds(138, 366, 295, 322);
										contentPane.add(lblFoto);
										
										txtPesquisaPertence = new JTextField();
										txtPesquisaPertence.addKeyListener(new KeyAdapter() {
											@Override
											public void keyReleased(KeyEvent arg0) {
												txtPesquisaPertence.setText(txtPesquisaPertence.getText().toUpperCase());
											//	System.out.println(txtPesquisaPertence.getText().toUpperCase());
												int index = defaultCbmCatalogo.getIndexOf(txtPesquisaPertence.getText().toUpperCase());
												System.out.println(defaultCbmCatalogo.getSize());
												for (int i = 0; i < defaultCbmCatalogo.getSize(); i++) {
													if(defaultCbmCatalogo.getElementAt(i).toString().indexOf(txtPesquisaPertence.getText()) != -1){
														cbmCatalogo.setSelectedIndex(i);
													}
												}
												//System.out.println(index);
												//cbmCatalogo.setSelectedIndex(index);
											}
										});
										txtPesquisaPertence.setBounds(523, 17, 122, 28);
										
										//contentPane.add(txtPesquisaPertence);
										txtPesquisaPertence.setColumns(10);

		LoadCombos();
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Arial", Font.PLAIN, 12));
		label.setIcon(new ImageIcon(JInserirAtores.class.getResource("/view/imagens/FundoParaTudo.jpg")));
		label.setBounds(0, 0, 1302, 723);
		
		img.OrganizarImagem(label, new ImageIcon(JInserirAtores.class.getResource("/view/imagens/FundoParaTudo.jpg")), false);
		
		//contentPane.add(label);
	}

	private void itemChangedCatalogo(ItemEvent e) {
		if(cbmCatalogo.getSelectedIndex() == -1){
			return;
		}
		txtCaminhoVideo.setEnabled(true);
		txtNEp.setEnabled(true);
		txtNTemp.setEnabled(true);
		btnConfigurar.setEnabled(true);
		
		if (cbmCatalogo.getSelectedIndex() == 0) {
			txtAno.setText("");
			txtNome.setText("");
			txtCaminhoImagem.setText("");
			txtCaminhoVideo.setText("");
			txtIdade.setText("");
			txtNEp.setText("");
			txtPesquisaElenco.setText("");
			txtPesquisaPertence.setText("");
			ConfigTxtSinopse();

			defaultCbmCategoria = new DefaultComboBoxModel<>(controlCategoria.Selecionar(1).toArray());
			defaultCbmCategoria.insertElementAt(" ", 0);
			cbmCategoria.setModel(defaultCbmCategoria);
			
			defaultCbmGenero = new DefaultComboBoxModel<>(controlGenero.Selecionar().toArray());
			defaultCbmGenero.insertElementAt(" ", 0);
			cbmGenero.setModel(defaultCbmGenero);
			
			defaultCbmPaises = new DefaultComboBoxModel<>(controlPais.SelecionarPais(0).toArray());
			defaultCbmPaises.insertElementAt(" ", 0);
			cbmPais.setModel(defaultCbmPaises);

			defaultCbmPaisClass = new DefaultComboBoxModel<>(controlPais.SelecionarPais(0).toArray());
			defaultCbmPaisClass.insertElementAt(" ", 0);
			cbmPaisClass.setModel(defaultCbmPaisClass);
			
		}
		
		if(cbmCatalogo.getSelectedIndex() == 1){
			txtCaminhoVideo.setEnabled(false);
			txtNEp.setEnabled(false);
			txtNTemp.setEnabled(false);
			btnConfigurar.setEnabled(false);
			
			txtNEp.setText("0");
			txtNTemp.setText("0");
		}
		LoadTablesID(controlCatalogo.getID(cbmCatalogo.getSelectedItem().toString()));
	}

	public void LoadTablesID(int ID) {
		controlGC = new ControlGeneroCatalogo();
		tableModelGenero = controlGC.tblGeneroCatalogo(ID);
		tblGenero.setModel(tableModelGenero);

		controlPC = new ControlPaisCatalogo();
		tableModelPais = controlPC.tblPaisCatalogo(ID);
		tblPais.setModel(tableModelPais);

		controlClass = new ControlClassificacao();
		tableModelClassificacao = controlClass.tblClassificacao(ID);
		tblClass.setModel(tableModelClassificacao);

		controlCE = new ControlCatalogoEnvolvidos();
		tableModelElenco = controlCE.tblCE(ID);
		tblElenco.setModel(tableModelElenco);

		// controlTI = new ControlTituloIdioma();
		// tableModelTitulos = controlTI.tblTitutoIdioma(ID);

		controlAcesso = new ControlAcesso();
		tableModelAcesso = controlAcesso.tblAcesso(ID);
		tblAcesso.setModel(tableModelAcesso);

		// tableModelAcesso;

		this.ID = ID;
		
		txtCaminhoImagem.setText(controlCatalogo.getModel(ID, ConsColunaCatalogo.IMAGEM));
		defaultCbmCategoria.setSelectedItem(controlCatalogo.getModel(ID, ConsColunaCatalogo.NOMECATEGORIA));
	}

	// Codigo para regiao e não para pais de origem
	@SuppressWarnings("unchecked")
	public void PaisCombo() {
		if (cbmRegiao.getSelectedIndex() == 0) {
			defaultCbmPais = new DefaultComboBoxModel<>(controlPais.SelecionarPais(0).toArray());
		} else {
			defaultCbmPais = new DefaultComboBoxModel<>(
					controlPais.SelecionarPais(Integer.valueOf(cbmRegiao.getSelectedItem().toString())).toArray());
		}
		cbmPaises.setModel(defaultCbmPais);
		defaultCbmPais.insertElementAt(EnumTexto.Todos.get(), 0);
		cbmPaises.setSelectedIndex(0);
	}

	@SuppressWarnings("unchecked")
	public void LoadCombos() {
		// Genero
		controlGenero = new ControlGenero();
		// cbmGenero.addItem(" ");
		defaultCbmGenero = new DefaultComboBoxModel<>(controlGenero.Selecionar().toArray());
		defaultCbmGenero.insertElementAt(" ", 0);
		cbmGenero.setModel(defaultCbmGenero);
		cbmGenero.setSelectedIndex(0);

		// Tipos/Cargos
		// cbmTipo.addItem(" ");
		defaultCbmTipo = new DefaultComboBoxModel<>(controlTipo.Selecionar().toArray());
		defaultCbmTipo.insertElementAt(" ", 0);
		cbmTipo.setModel(defaultCbmTipo);
		cbmTipo.setSelectedIndex(0);

		// Pais de origem
		defaultCbmPaises = new DefaultComboBoxModel<>(controlPais.SelecionarPais(0).toArray());
		defaultCbmPaises.insertElementAt(" ", 0);
		cbmPais.setModel(defaultCbmPaises);
		cbmPais.setSelectedIndex(0);

		// Pais da classificacao
		defaultCbmPaisClass = new DefaultComboBoxModel<>(controlPais.SelecionarPais(0).toArray());
		defaultCbmPaisClass.insertElementAt(" ", 0);
		cbmPaisClass.setModel(defaultCbmPaisClass);
		cbmPaisClass.setSelectedIndex(0);

		// Categoria
		defaultCbmCategoria = new DefaultComboBoxModel<>(controlCategoria.Selecionar(1).toArray());
		defaultCbmCategoria.insertElementAt(" ", 0);
		cbmCategoria.setModel(defaultCbmCategoria);
		cbmCategoria.setSelectedIndex(0);

		// Catalogo para os q forem série
		controlCatalogo = new ControlCatalogo();
		defaultCbmCatalogo = new DefaultComboBoxModel<>(controlCatalogo.SelecionarIDSerieCatalogo(0).toArray());
		defaultCbmCatalogo.insertElementAt(" ", 0);
		defaultCbmCatalogo.insertElementAt("Série Primária", 1);
		cbmCatalogo.setModel(defaultCbmCatalogo);
		cbmCatalogo.setSelectedIndex(0);
	}

	public void OrdernarTabelas(JTable tabela, int IndexOrdenar) {
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(tabela.getModel());
		tabela.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();

		int columnIndexToSort = IndexOrdenar;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));

		sorter.setSortKeys(sortKeys);
		sorter.sort();
	}

	public void OrdernarTabelas(JTable tabela) {
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(tabela.getModel());
		tabela.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();

		int columnIndexToSort = 0;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));

		sorter.setSortKeys(sortKeys);
		sorter.sort();
	}

	public void setModelAudios(DefaultTableModel tableAudios) {
		this.tableModelAudios = tableAudios;
	}

	public void setModelLegendas(DefaultTableModel tableLegendas) {
		this.tableModelLegendas = tableLegendas;
	}

	public void setModelTitulos(DefaultTableModel tableTitulos) {
		this.tableModelTitulos = tableTitulos;
	}

	public void setModelSinopse(DefaultTableModel tableSinopse) {
		this.tableModelSinopse = tableSinopse;
	}

	KeyListener keyJustNumber = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			String characteres = "0987654321";
			if (!characteres.contains(arg0.getKeyChar() + "")) {
				arg0.consume();
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}
	};

	ActionListener clickConfigurar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (rdbtnAudio.isSelected()) {
				JConfigurarAudios.main(null, tableModelAudios, catalogo);
			}

			if (rdbtnLegendas.isSelected()) {
				JConfigurarLegendas.main(null, tableModelLegendas, catalogo);
			}
		}
	};

	ActionListener clickPesquisarFoto = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			FileFilter imageFilter = new FileNameExtensionFilter("Arquivos de Imagens",
					ImageIO.getReaderFileSuffixes());
			JFileChooser file = new JFileChooser();
			file.addChoosableFileFilter(imageFilter);
			file.setAcceptAllFileFilterUsed(false);
			int retorno = file.showSaveDialog(null);
			if (retorno == JFileChooser.APPROVE_OPTION) {
				txtCaminhoImagem.setText(file.getSelectedFile().getAbsolutePath());
				
				ImageIcon in = new ImageIcon(txtCaminhoImagem.getText());
				Image dimg = in.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon imgI = new ImageIcon(dimg);
				lblFoto.setIcon(imgI);

			}
		}
	};

	ActionListener clickAddGenero = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			String genero = cbmGenero.getSelectedItem().toString();
			if (genero.trim().equals("")) {
				return;
			}
			for (int i = 0; i < tableModelGenero.getRowCount(); i++) {
				if (tableModelGenero.getValueAt(i, 0).toString().equals(genero)) {
					return;
				}
			}
			tableModelGenero.addRow(new Object[] { genero });
			OrdernarTabelas(tblGenero);
		}
	};

	ActionListener clickAddElenco = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String tipo = cbmTipo.getSelectedItem().toString();
			String nome = txtPesquisaElenco.getText();
			if (tipo.trim().equals("") || nome.trim().equals("")) {
				return;
			}
			for (int i = 0; i < tableModelElenco.getRowCount(); i++) {
				if (tableModelElenco.getValueAt(i, 1).toString().equals(tipo)
						&& tableModelElenco.getValueAt(i, 0).toString().equals(nome)) {
					return;
				}
			}

			if (!controlElenco.findElenco(txtPesquisaElenco.getText())) {
				return;
			}

			tableModelElenco.addRow(new Object[] { nome, tipo });
			OrdernarTabelas(tblElenco);
		}
	};

	ActionListener clickAddAcesso = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String pais = cbmPaises.getSelectedItem().toString();
			String regiao = cbmRegiao.getSelectedItem().toString();
			boolean proibido = chckbxProibido.isSelected();

			if (cbmRegiao.getSelectedIndex() == 0 && cbmPaises.getSelectedIndex() != 0) {
				regiao = String.valueOf(controlPais.ObterRegiao(pais));
			}

			for (int i = 0; i < tableModelAcesso.getRowCount(); i++) {

				String cPais = tableModelAcesso.getValueAt(i, 0).toString();
				String cRegiao = tableModelAcesso.getValueAt(i, 1).toString();
				boolean cProibido = Boolean.parseBoolean(tableModelAcesso.getValueAt(i, 2).toString());

				if (cbmPaises.getSelectedIndex() == 0 && cbmRegiao.getSelectedIndex() != 0) {
					if (cRegiao.equals(regiao) && cProibido == proibido) {
						tableModelAcesso.removeRow(i);
					}
				}

				if (cPais.equals(pais) && cProibido != proibido && regiao.equals(cRegiao)) {
					tableModelAcesso.removeRow(i);
				}

				if (cbmPaises.getSelectedIndex() == 0 && cbmRegiao.getSelectedIndex() == 0) {
					if (pais.equals(cPais) && cRegiao.equals(regiao) && proibido == cProibido) {
						return;
					}
				}

				if (cbmPaises.getItemAt(0).toString().equals(cPais)
						&& cbmRegiao.getItemAt(0).toString().equals(cRegiao)) {
					if (cProibido == proibido) {
						return;
					}
				}

				if (cPais.equals(pais) && (cRegiao.equals(regiao) || cbmRegiao.getItemAt(0).toString().equals(regiao))
						&& proibido == cProibido) {
					return;
				}
			}

			if (cbmRegiao.getSelectedIndex() == 0 && cbmPaises.getSelectedIndex() != 0) {
				regiao = String.valueOf(controlPais.ObterRegiao(pais));
			}

			tableModelAcesso.addRow(new Object[] { pais, regiao, proibido });
		}
	};

	ActionListener clickCadastrar = new ActionListener() {

		int countImagem = 0;
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			/*for (Component c : contentPane.getComponents()) {
				if (c instanceof JTextField) {

					if (((JTextField) c).getText().trim().equals("") && (JTextField) c != txtNEp
							&& (JTextField) c != txtNTemp && (JTextField) c != txtCaminhoImagem) {
						MessagemBoxCustom.ShowMensagem("Por favor preencha todos os campos");
						System.out.println("first " + ((JTextField)c).getName().toString());
						return;
					}
				}
			}*/

			if (rdbtnSerie.isSelected() && cbmCatalogo.getSelectedIndex() != 1) {
				if (txtNEp.getText().trim().equals("") || txtNTemp.getText().trim().equals("")) {
					MessagemBoxCustom.ShowMensagem("Por favor preencha todos os campos");
					System.out.println("2");
					return;
				}
			}

			if (tblGenero.getRowCount() == 0 || tblElenco.getRowCount() == 0
					|| tblPais.getRowCount() == 0) {
				MessagemBoxCustom.ShowMensagem("Por favor preencha todos os campos");
				System.out.println("3");
				return;
			}

			if (cbmCategoria.getSelectedIndex() == 0) {
				MessagemBoxCustom.ShowMensagem("Por favor preencha todos os campos");
				System.out.println("4");
				return;
			}
			
			if(rdbtnFilme.isSelected() && txtCaminhoImagem.getText().trim().equals("") && countImagem == 0){
				MessagemBoxCustom.ShowMensagem("<html><center>A midia será salva sem nenhuma foto<br />Clique novamente em cadastrar se tem certeza</center>");
				countImagem++;
				return;
			}

			if (tableModelAudios.getRowCount() == 0 && cbmCatalogo.getSelectedIndex() != 1) {
				MessagemBoxCustom
						.ShowMensagem("<html><center>Por favor insira no minimo um audio<br />para a midia</center>");
				return;
			}

			if (tableModelLegendas.getRowCount() == 0 && CountLegenda <= 0) {
				MessagemBoxCustom.ShowMensagem(
						"<html><center>A midia será salva sem nenhuma legenda<br />Clique novamente em cadastrar se tem certeza</center>");
				CountLegenda++;
				return;
			}

			CountLegenda = 0;

			int IDCategoria = controlCategoria.getId(cbmCategoria.getSelectedItem().toString());

			int idSerieCatalogo = 0;

			if (rdbtnFilme.isSelected()) {
				txtNEp.setText("0");
				txtNTemp.setText("0");
			}
			if (rdbtnSerie.isSelected() && cbmCatalogo.getSelectedIndex() != 1) {
				idSerieCatalogo = ID;
			}

			controlCatalogo.Inserir(txtCaminhoImagem.getText(), Integer.valueOf(txtAno.getText()), IDCategoria,
					Integer.valueOf(txtNTemp.getText()), Integer.valueOf(txtNEp.getText()), txtCaminhoVideo.getText(),
					txtSinopse.getText(), txtNome.getText(), idSerieCatalogo);

			txtNEp.setText("");
			txtNTemp.setText("");
			txtSinopse.setText("");

			controlCatalogo = new ControlCatalogo();

			int IDMidia = controlCatalogo.SelecionarLastID();

			controlPC = new ControlPaisCatalogo();
			controlGC = new ControlGeneroCatalogo();
			controlTC = new ControlTituloCatalogo();
			controlTI = new ControlTituloIdioma();
			controlIdioma = new ControlIdioma();
			controlSC = new ControlSinopseCatalogo();
			controlClass = new ControlClassificacao();
			controlCE = new ControlCatalogoEnvolvidos();
			controlAL = new ControlAL();
			controlAcesso = new ControlAcesso();

			// Pais de origem
			for (int i = 0; i < tblPais.getRowCount(); i++) {
				controlPC.Inserir(IDMidia, controlPais.ObterId(tblPais.getValueAt(i, 0).toString()));
			}

			// Generos
			for (int i = 0; i < tblGenero.getRowCount(); i++) {
				controlGC.Inserir(controlGenero.getID(tblGenero.getValueAt(i, 0).toString()), IDMidia);
			}

			// Titulos
			for (int i = 0; i < tableModelTitulos.getRowCount(); i++) {
				controlTC.Inserir(tableModelTitulos.getValueAt(i, 1).toString(), IDMidia);
				controlTI.Inserir(controlIdioma.getId(tableModelTitulos.getValueAt(i, 0).toString()),
						controlTC.getLasID());
			}

			// Sinopses
			for (int i = 0; i < tableModelSinopse.getRowCount(); i++) {
				controlSC.Inserir(IDMidia, tableModelSinopse.getValueAt(i, 1).toString(),
						controlIdioma.getId(tableModelSinopse.getValueAt(i, 0).toString()));
			}

			// Classificação
			for (int i = 0; i < tableModelClassificacao.getRowCount(); i++) {
				if(tableModelClassificacao.getValueAt(i, 0).toString() == "LIVRE"){
					controlClass.Inserir(IDMidia, controlPais.ObterId(tableModelClassificacao.getValueAt(i, 1).toString()), 0);
				}else{
				controlClass.Inserir(IDMidia, controlPais.ObterId(tableModelClassificacao.getValueAt(i, 1).toString()),
						Integer.parseInt(tableModelClassificacao.getValueAt(i, 0).toString()));
				}
			}

			/// Elencos
			for (int i = 0; i < tableModelElenco.getRowCount(); i++) {
				controlCE.Inserir(IDMidia, controlElenco.getID(tableModelElenco.getValueAt(i, 0).toString()),
						controlTipo.getID(tableModelElenco.getValueAt(i, 1).toString()));
			}

			// Audios
			for (int i = 0; i < tableModelAudios.getRowCount(); i++) {
				 controlAL.Inserir(EnumAL.AUDIO.getAL(),
						 tableModelAudios.getValueAt(i, 1).toString(), IDMidia,
						 controlIdioma.getId(tableModelAudios.getValueAt(i, 0).toString()));
			}

			// Legendas
			for (int i = 0; i < tableModelLegendas.getRowCount(); i++) {
				controlAL.Inserir(EnumAL.LEGENDA.getAL(), 
						tableModelLegendas.getValueAt(i, 1).toString(), 
						IDMidia, controlIdioma.getId(tableModelLegendas.getValueAt(i, 0).toString()));
			}
			
			// Acesso
			/*
			List<String> paises = new ArrayList<String>();
			List<String> paisesOf = new ArrayList<String>();

			for (int i = 0; i < tableModelAcesso.getRowCount(); i++) {

				boolean proibido = Boolean.parseBoolean(tableModelAcesso.getValueAt(i, 2).toString());

				if (!proibido) {
					if (tableModelAcesso.getValueAt(i, 0).toString().equals(EnumTexto.Todos.get())) {
						if (tableModelAcesso.getValueAt(i, 1).toString().equals(EnumTexto.Todas.get())) {
							paises = controlPais.SelecionarPaises();
						}
					}

					if (tableModelAcesso.getValueAt(i, 0).toString().equals(EnumTexto.Todos.get())) {
						if (!tableModelAcesso.getValueAt(i, 1).toString().equals(EnumTexto.Todas.get())) {
							paises.addAll(
									controlPais.SelecionarPaisesRegiao(tableModelAcesso.getValueAt(i, 1).toString()));
						}
					}

					if (!tableModelAcesso.getValueAt(i, 0).toString().equals(EnumTexto.Todos.get())) {
						if (!tableModelAcesso.getValueAt(i, 1).toString().equals(EnumTexto.Todas.get())) {
							paises.add(tableModelAcesso.getValueAt(i, 0).toString());
						}
					}
				}

				if (proibido) {
					if (tableModelAcesso.getValueAt(i, 0).toString().equals(EnumTexto.Todos.get())) {
						if (tableModelAcesso.getValueAt(i, 1).toString().equals(EnumTexto.Todas.get())) {
							paisesOf = controlPais.SelecionarPaises();
						}
					}

					if (tableModelAcesso.getValueAt(i, 0).toString().equals(EnumTexto.Todos.get())) {
						if (!tableModelAcesso.getValueAt(i, 1).toString().equals(EnumTexto.Todas.get())) {
							paisesOf.addAll(
									controlPais.SelecionarPaisesRegiao(tableModelAcesso.getValueAt(i, 1).toString()));
						}
					}

					if (!tableModelAcesso.getValueAt(i, 0).toString().equals(EnumTexto.Todos.get())) {
						if (!tableModelAcesso.getValueAt(i, 1).toString().equals(EnumTexto.Todas.get())) {
							paisesOf.add(tableModelAcesso.getValueAt(i, 0).toString());
						}
					}
				}
			}

			for (String pais : paisesOf) {
				controlAcesso.Inserir(IDMidia, controlPais.ObterId(pais), EnumAcesso.N.get());
			}

			for (String pais : paises) {
				controlAcesso.Inserir(IDMidia, controlPais.ObterId(pais), EnumAcesso.Y.get());
			}*/
			// Fim Acesso

			MessagemBoxCustom.ShowMensagem("Midia cadastrada com sucesso!!");

			for (Component c : contentPane.getComponents()) {
				if (c instanceof JTextField && c != txtSinopse) {
					((JTextField) c).setText("");
				}
			}
			
			for (int i = 0; i < tableModelAcesso.getRowCount(); i++) {
				tableModelAcesso.removeRow(i);
			}

			for (int j = 0; j < tableModelAudios.getRowCount(); j++) {
				tableModelAudios.removeRow(j);
			}

			for (int k = 0; k < tableModelElenco.getRowCount(); k++) {
				tableModelElenco.removeRow(k);
			}

			for (int i = 0; i < tableModelGenero.getRowCount(); i++) {
				tableModelGenero.removeRow(i);
			}

			for (int i = 0; i < tableModelLegendas.getRowCount(); i++) {
				tableModelLegendas.removeRow(i);
			}

			for (int i = 0; i < tableModelPais.getRowCount(); i++) {
				tableModelPais.removeRow(i);
			}
			
			for (int i = 0; i < tableModelClassificacao.getRowCount(); i++) {
				tableModelClassificacao.removeRow(i);
			}
			
			lblFoto.setIcon(null);

			ConfigTxtSinopse();
			txtPesquisaElenco.setText("");
			cbmCategoria.setSelectedIndex(0);
			cbmGenero.setSelectedIndex(0);
			cbmPais.setSelectedIndex(0);
			cbmPaises.setSelectedIndex(0);
			cbmRegiao.setSelectedIndex(0);
			cbmTipo.setSelectedIndex(0);
			cbmPaisClass.setSelectedItem(0);
			
			txtIdade.setText("");

			
			frame.dispose();
			JCatalogo.main(null);
		}
	};

	public void ConfigTxtSinopse() {
		txtSinopse.setForeground(Color.GRAY);
		txtSinopse.setFont(new Font("SansSerif", Font.ITALIC, 12));
		txtSinopse.setText("Digite aqui a sinopse, bem resumida.\r\nPor favor!!");
	}

	private JTable tblGenero;
	private JTextField txtPesquisaElenco;
	private JTable tblElenco;
	private JTable tblAcesso;
	private JTable tblPesquisaElenco;
	private JRadioButton rdbtnAudio;
	private JRadioButton rdbtnLegendas;
	private JRadioButton rdbtnSerie;
	private JRadioButton rdbtnFilme;
	private JTable tblPais;
	private JTextArea txtSinopse;
	private JTable tblClass;
	private JLabel lblIdade;
	private JLabel lblPais;
	@SuppressWarnings("rawtypes")
	private JComboBox cbmPaisClass;
	private JTextField txtIdade;
	@SuppressWarnings("rawtypes")
	private JComboBox cbmCatalogo;
	private JLabel lblPertence;
	private JButton btnVisu;
	private JButton btnConfigurar;
	private JLabel lblFoto;
	private JTextField txtPesquisaPertence;
}
