package view;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Enum.EnumCatalogo;
import Enum.EnumFSOP;
import control.ControlCatalogo;
import control.ControlCategoria;

import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Frame;

@SuppressWarnings("serial")
public class JVisualCatalogo extends JFrame {

	private JPanel contentPane;
	private JTable tblCatalogo;

	private DefaultTableModel tmCatalogo;
	private ControlCatalogo ctrlCatalogo;
	private JTextField txtNome;
	private JTextField txtAno;
	
	private List<RowFilter<Object, Object>> filtro;
	private RowFilter<Object, Object> rf;
	private TableRowSorter<TableModel> sorter;
	private JLabel lblNomePadro;
	private JLabel lblAno;
	@SuppressWarnings("rawtypes")
	private JComboBox cbmCategoria;
	private JLabel lblCategoria;

	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel modelCbmCategoria;
	
	private ControlCategoria controlCategoria;
	@SuppressWarnings("rawtypes")
	private JComboBox cbmFS;
	private JLabel lblFilmesrie;
	private JButton btnEditar;
	
	private JVisualCatalogo vc = this;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JVisualCatalogo frame = new JVisualCatalogo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JVisualCatalogo() throws IOException {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		controlCategoria = new ControlCategoria();
		
		setTitle("Cat\u00E1logo - An\u00E1lise");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1377, 745);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 187, 1350, 484);
		contentPane.add(scrollPane);
		
		tblCatalogo = new JTable(){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		RefreshTable();
		
		sorter = new TableRowSorter<TableModel>(tblCatalogo.getModel());
		tblCatalogo.setRowSorter(sorter);
		
		scrollPane.setViewportView(tblCatalogo);
		
		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				txtNome.setText(txtNome.getText().toUpperCase());
				Filter();
			}
		});
		txtNome.setBounds(128, 28, 137, 28);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtAno = new JTextField();
		txtAno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Filter();
			}
			@Override
			public void keyTyped(KeyEvent e) {
				String characteres = "0987654321";
				if (!characteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtAno.setBounds(128, 68, 137, 28);
		contentPane.add(txtAno);
		txtAno.setColumns(10);
		
		lblAno = new JLabel("Ano:");
		lblAno.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblAno.setBounds(95, 71, lblAno.getPreferredSize().width, lblAno.getPreferredSize().height);
		contentPane.add(lblAno);
		
		lblNomePadro = new JLabel("Nome Padr\u00E3o:");
		lblNomePadro.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNomePadro.setBounds(30, 31, lblNomePadro.getPreferredSize().width, lblNomePadro.getPreferredSize().height);
		contentPane.add(lblNomePadro);
		
		cbmCategoria = new JComboBox();
		cbmCategoria.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Filter();
			}
		});
		cbmCategoria.setBounds(372, 28, 122, 28);
		contentPane.add(cbmCategoria);
		
		lblCategoria = new JLabel("Categoria:");
		lblCategoria.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblCategoria.setBounds(293, 31, lblCategoria.getPreferredSize().width, lblCategoria.getPreferredSize().height);
		contentPane.add(lblCategoria);
		
		cbmFS = new JComboBox(EnumFSOP.values());
		cbmFS.insertItemAt(" ", 0);
		cbmFS.setSelectedIndex(0);
		cbmFS.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Filter();
			}
		});
		cbmFS.setBounds(372, 68, 122, 28);

		contentPane.add(cbmFS);
		
		lblFilmesrie = new JLabel("Filme/S\u00E9rie:");
		lblFilmesrie.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblFilmesrie.setBounds(282, 71, lblFilmesrie.getPreferredSize().width, lblFilmesrie.getPreferredSize().height);
		contentPane.add(lblFilmesrie);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = 0;
				int ID = 0;
				row = tblCatalogo.getSelectedRow();
				for (int i = 0; i < tblCatalogo.getModel().getRowCount(); i++) {
					if(tblCatalogo.getModel().getValueAt(i, 1).equals(tblCatalogo.getValueAt(row, 0))){
						ID = Integer.parseInt(tblCatalogo.getModel().getValueAt(i, 0).toString());
					}
				}
				JEditarCatalogo.main(ID, vc);
				RefreshTable();
			}
		});
		btnEditar.setBounds(537, 49, 91, 28);
		contentPane.add(btnEditar);
		
		tblCatalogo.setRowSelectionInterval(0, 0);
		
		LoadCombo();
	}
	
	//TODO: Filtro
	public void Filter(){
		filtro = new ArrayList<RowFilter<Object, Object>>();
		filtro.add(RowFilter.regexFilter("(?i)" + txtNome.getText(), 1));
		filtro.add(RowFilter.regexFilter(txtAno.getText(), 4));
		if(!cbmCategoria.getSelectedItem().toString().trim().equals("")){
			filtro.add(RowFilter.regexFilter(cbmCategoria.getSelectedItem().toString(), 8));
		}
		if(!cbmFS.getSelectedItem().toString().trim().equals("")){
			filtro.add(RowFilter.regexFilter(cbmFS.getSelectedItem().toString(), 10));
		}
		rf = RowFilter.andFilter(filtro);
		sorter.setRowFilter(rf);
		tblCatalogo.setRowSelectionInterval(0, 0);
	}
	
	@SuppressWarnings("unchecked")
	public void LoadCombo(){
		
		modelCbmCategoria = new DefaultComboBoxModel<>(controlCategoria.Selecionar(1).toArray());
		modelCbmCategoria.insertElementAt(" ", 0);
		cbmCategoria.setModel(modelCbmCategoria);
		cbmCategoria.setSelectedIndex(0);
	}
	
	public static void Table(JVisualCatalogo vc){
		vc.RefreshTable();
	}
	
	public void RefreshTable(){
		ctrlCatalogo = new ControlCatalogo();
		try {
			tmCatalogo = ctrlCatalogo.tblCatalogo();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tblCatalogo.setModel(tmCatalogo);	
		tblCatalogo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		for (int i = 0; i < tblCatalogo.getColumnModel().getColumnCount(); i++) {
			if(tblCatalogo.getColumnName(i).equals(EnumCatalogo.ID.get()) ||
				tblCatalogo.getColumnName(i).equals(EnumCatalogo.IDCategoria.get()) ||
				tblCatalogo.getColumnName(i).equals(EnumCatalogo.IDSerieCatalogo.get()) ||
				tblCatalogo.getColumnName(i).equals(EnumCatalogo.Imagem.get()) || 
				tblCatalogo.getColumnName(i).equals(EnumCatalogo.Temporada.get())
				){
				tblCatalogo.removeColumn(tblCatalogo.getColumnModel().getColumn(i));
			}
		}
		
		for (int i = 0; i < tblCatalogo.getColumnModel().getColumnCount(); i++) {
			if(tblCatalogo.getColumnName(i).equals(EnumCatalogo.Episodio.get()) ||
					tblCatalogo.getColumnName(i).equals(EnumCatalogo.Caminho.get())){
				tblCatalogo.removeColumn(tblCatalogo.getColumnModel().getColumn(i));
			}	
		}
	}
}
