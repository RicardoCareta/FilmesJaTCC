package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import control.ControlIdioma;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class JConfigurarLegendas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField txtCaminho;
	

	
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel cbmModel;
	
	private ControlIdioma controlIdioma;
	private JCatalogo jcatalogo;
	private JEditarCatalogo jeditarCatalogo;
	
	private static JConfigurarLegendas dialog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, DefaultTableModel modelLegendas, JCatalogo catalogo) {
		try {
			dialog = new JConfigurarLegendas(modelLegendas, catalogo);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
			//dialog.setModal(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args, DefaultTableModel modelLegendas, JEditarCatalogo catalogo) {
		try {
			dialog = new JConfigurarLegendas(modelLegendas, catalogo);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
			//dialog.setModal(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JConfigurarLegendas(DefaultTableModel modelLegendas, JCatalogo cat) {
	
		setModal(true);
		jcatalogo = cat;
		
		//setLocationRelativeTo(null);
		setTitle("Configurar Legendas");
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				jcatalogo.setModelLegendas(modelLegendas);
			}
		});
		
		//tableLegendas = new DefaultTableModel();
		//tableLegendas = modelLegendas;
		
		controlIdioma = new ControlIdioma();
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JComboBox cbmIdioma = new JComboBox();
		cbmIdioma.setBounds(75, 67, 137, 23);
		cbmModel = new DefaultComboBoxModel<>(controlIdioma.Selecionar().toArray());
		cbmModel.insertElementAt(" ", 0);
		cbmIdioma.setModel(cbmModel);
		cbmIdioma.setSelectedIndex(0);
		contentPanel.add(cbmIdioma);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 102, 414, 115);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2 && !e.isConsumed()){
					int row = table.rowAtPoint(e.getPoint());
					modelLegendas.removeRow(row);
					table.repaint();
					
					e.consume();
					
				}
			}
		});
		table.setModel(modelLegendas);
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtCaminho.getText().trim().equals("")){
					return;
				}
				
				if(cbmIdioma.getSelectedIndex() == 0){
					return;
				}
				
				for (int i = 0; i < modelLegendas.getRowCount(); i++) {
					if(modelLegendas.getValueAt(i, 1).toString().equals(txtCaminho.getText()) && modelLegendas.getValueAt(i, 0).toString().equals(cbmIdioma.getSelectedItem().toString())){
						return;
					}
				}
				
				modelLegendas.addRow(new Object[] {cbmIdioma.getSelectedItem().toString(), txtCaminho.getText()});
				
				txtCaminho.setText("");
				cbmIdioma.setSelectedIndex(-1);
			}
		});
		button.setBounds(224, 67, 52, 23);
		contentPanel.add(button);
		
		JLabel lblCaminho = new JLabel("Caminho:");
		lblCaminho.setBounds(10, 32, lblCaminho.getPreferredSize().width, lblCaminho.getPreferredSize().height);
		contentPanel.add(lblCaminho);
		
		txtCaminho = new JTextField();
		txtCaminho.setBounds(75, 26, 300, 28);
		contentPanel.add(txtCaminho);
		txtCaminho.setColumns(10);
		
		JButton btnAdd = new JButton("...");
		btnAdd.setBounds(383, 26, 50, 28);
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FileFilter imageFilter = new FileNameExtensionFilter("Arquivos de Legendas",
						"srt");
				JFileChooser file = new JFileChooser();
				file.addChoosableFileFilter(imageFilter);
				file.setAcceptAllFileFilterUsed(false);
				int retorno = file.showSaveDialog(null);
				if (retorno == JFileChooser.APPROVE_OPTION) {
					txtCaminho.setText(file.getSelectedFile().getAbsolutePath());
				}
			}
		});
		
		contentPanel.add(btnAdd);
		
		
		JLabel lblIdioma = new JLabel("Idioma:");
		lblIdioma.setBounds(20, 70, lblIdioma.getPreferredSize().width, lblIdioma.getPreferredSize().height);
		contentPanel.add(lblIdioma);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Sair");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JConfigurarLegendas(){
		
	}
	
	public JConfigurarLegendas(DefaultTableModel modelLegendas, JEditarCatalogo cat) {
	
		setModal(true);
		jeditarCatalogo = cat;
		
		//setLocationRelativeTo(null);
		setTitle("Configurar Legendas");
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				jeditarCatalogo.setModelLegendas(modelLegendas);
			}
		});
		
		//tableLegendas = new DefaultTableModel();
		//tableLegendas = modelLegendas;
		
		controlIdioma = new ControlIdioma();
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JComboBox cbmIdioma = new JComboBox();
		cbmIdioma.setBounds(75, 67, 137, 23);
		cbmModel = new DefaultComboBoxModel<>(controlIdioma.Selecionar().toArray());
		cbmModel.insertElementAt(" ", 0);
		cbmIdioma.setModel(cbmModel);
		cbmIdioma.setSelectedIndex(0);
		contentPanel.add(cbmIdioma);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 102, 414, 115);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				if(e.getClickCount() == 2 && !e.isConsumed()){
					int row = table.rowAtPoint(e.getPoint());
					modelLegendas.removeRow(row);
					table.repaint();
					
					e.consume();
					
				}
			}
		});
		table.setModel(modelLegendas);
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtCaminho.getText().trim().equals("")){
					return;
				}
				
				if(cbmIdioma.getSelectedIndex() == 0){
					return;
				}
				
				for (int i = 0; i < modelLegendas.getRowCount(); i++) {
					if(modelLegendas.getValueAt(i, 1).toString().equals(txtCaminho.getText()) && modelLegendas.getValueAt(i, 0).toString().equals(cbmIdioma.getSelectedItem().toString())){
						return;
					}
				}
				
				modelLegendas.addRow(new Object[] {cbmIdioma.getSelectedItem().toString(), txtCaminho.getText()});
				
				txtCaminho.setText("");
				cbmIdioma.setSelectedIndex(-1);
			}
		});
		button.setBounds(224, 67, 52, 23);
		contentPanel.add(button);
		
		JLabel lblCaminho = new JLabel("Caminho:");
		lblCaminho.setBounds(10, 32, lblCaminho.getPreferredSize().width, lblCaminho.getPreferredSize().height);
		contentPanel.add(lblCaminho);
		
		txtCaminho = new JTextField();
		txtCaminho.setBounds(75, 26, 300, 28);
		contentPanel.add(txtCaminho);
		txtCaminho.setColumns(10);
		
		JButton btnAdd = new JButton("...");
		btnAdd.setBounds(383, 26, 50, 28);
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FileFilter imageFilter = new FileNameExtensionFilter("Arquivos de Legendas",
						"srt");
				JFileChooser file = new JFileChooser();
				file.addChoosableFileFilter(imageFilter);
				file.setAcceptAllFileFilterUsed(false);
				int retorno = file.showSaveDialog(null);
				if (retorno == JFileChooser.APPROVE_OPTION) {
					txtCaminho.setText(file.getSelectedFile().getAbsolutePath());
				}
			}
		});
		
		contentPanel.add(btnAdd);
		
		JLabel lblIdioma = new JLabel("Idioma:");
		lblIdioma.setBounds(20, 70, lblIdioma.getPreferredSize().width, lblIdioma.getPreferredSize().height);
		contentPanel.add(lblIdioma);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Sair");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
