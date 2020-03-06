package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import control.ControlFalaConosco;
import util.ImgUtils;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import javax.swing.JList;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

import javax.swing.JFormattedTextField;

@SuppressWarnings("serial")
public class JFaleConosco extends JDialog {
	private JTable table;
	private JTextField txtPesquisa;

	private ControlFalaConosco controlFalaConosco;
	
	private ImgUtils img;
	
	private static final int DATA_FILTRO = 1;
	private static final int NOME_FILTRO = 2;
	private static final int EMAIL_FILTRO = 3;
	
	private static final String TEXTO_EMAIL = "Email";
	private static final String TEXTO_NOME = "Nome";
	private static final String TEXTO_DATA = "Data";
	
	private int tipoFiltro = 0;
	
	
	/**
	 * Launch the application.
	 */
	static JFaleConosco dialog;
	private JComboBox cbmFiltro;
	private JFormattedTextField txtPesquisaData;
	private JButton btnResponder;
	public static void main(String[] args) {
		try {
			dialog = new JFaleConosco();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JFaleConosco() {
		img = new ImgUtils();
		
		setTitle("Atendimento ao Cliente");
		setBounds(100, 100, 1200, 704);
		getContentPane().setLayout(null);
		
		setLocationRelativeTo(null);
		setModal(true);
		
		controlFalaConosco = new ControlFalaConosco();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 159, 1164, 446);
		getContentPane().add(scrollPane);
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.setModel(controlFalaConosco.tblFale());
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Filtrar por:");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 15, 80, 14);
		getContentPane().add(lblNewLabel_1);
		
		cbmFiltro = new JComboBox();
		cbmFiltro.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				SetarFiltro();
			}
		});
		cbmFiltro.setModel(new DefaultComboBoxModel(new String[] {"", TEXTO_NOME, TEXTO_EMAIL, TEXTO_DATA}));
		cbmFiltro.setFont(new Font("Arial", Font.PLAIN, 13));
		cbmFiltro.setBounds(81, 9, 153, 28);
		getContentPane().add(cbmFiltro);
		
		txtPesquisa = new JTextField();
		txtPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				txtPesquisa.setText(txtPesquisa.getText().toUpperCase());
				Filtro();
			}
		});
		txtPesquisa.setFont(new Font("Arial", Font.PLAIN, 13));
		txtPesquisa.setBounds(323, 9, 208, 28);
		getContentPane().add(txtPesquisa);
		txtPesquisa.setColumns(10);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
			}
		});
		btnVoltar.setBounds(1083, 631, 91, 28);
		getContentPane().add(btnVoltar);
		
		JLabel lblNewLabel = new JLabel("Pesquisar:");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setBounds(246, 15, 80, 14);
		getContentPane().add(lblNewLabel);
		
		txtPesquisaData = new JFormattedTextField(new SimpleDateFormat("dd/mm/yyyy"));
		txtPesquisaData.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Filtro();
			}
		});
		txtPesquisaData.setBounds(323, 9, 208, 28);
		try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.install(txtPesquisaData);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		txtPesquisaData.setVisible(false);
		getContentPane().add(txtPesquisaData);
		
		btnResponder = new JButton("Responder");
		btnResponder.setBounds(1065, 119, 91, 28);
		btnResponder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String email = table.getModel().getValueAt(row, 1).toString();
				String name = table.getModel().getValueAt(row, 0).toString();
				String mensagem = table.getModel().getValueAt(row, 3).toString();
				
				JResponder.main(email, name, mensagem);
			}
		});
		getContentPane().add(btnResponder);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Arial", Font.PLAIN, 12));
		label.setIcon(new ImageIcon(JInserirAtores.class.getResource("/view/imagens/FundoParaTudo.jpg")));
		label.setBounds(0, 0, 1200, 704);
		
		img.OrganizarImagem(label, new ImageIcon(JInserirAtores.class.getResource("/view/imagens/FundoParaTudo.jpg")), false);
		
		//getContentPane().add(label);
		
		
		
	}
	
	private void SetarFiltro(){
		tipoFiltro = 0;
		txtPesquisaData.setVisible(false);
		txtPesquisa.setVisible(true);
		if(cbmFiltro.getSelectedItem().toString().equals(TEXTO_DATA)){
			tipoFiltro = DATA_FILTRO;
			txtPesquisaData.setVisible(true);
			txtPesquisa.setVisible(false);
		}
		
		if(cbmFiltro.getSelectedItem().toString().equals(TEXTO_NOME)){
			tipoFiltro = NOME_FILTRO;
		}
		
		if(cbmFiltro.getSelectedItem().toString().equals(TEXTO_EMAIL)){
			tipoFiltro = EMAIL_FILTRO;
		}
	}
	
	private void Filtro(){
		
		if(tipoFiltro == EMAIL_FILTRO){
			table.setModel(controlFalaConosco.tblFaleEmail(txtPesquisa.getText()));
		}
		
		if(tipoFiltro == NOME_FILTRO){
			table.setModel(controlFalaConosco.tblFaleNome(txtPesquisa.getText()));
		}
		
		if(tipoFiltro == DATA_FILTRO){
			table.setModel(controlFalaConosco.tblFaleData(txtPesquisaData.getText().replace("/", "").trim()));
		}
		
		if(tipoFiltro == 0){
			table.setModel(controlFalaConosco.tblFale());
		}
	}
}
