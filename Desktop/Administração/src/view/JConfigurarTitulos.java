package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

@SuppressWarnings("serial")
public class JConfigurarTitulos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField txtTitulo;
	
	//private DefaultTableModel tableAudios;
	
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel cbmModel;
	
	private ControlIdioma controlIdioma;
	private JCatalogo jcatalogo;
	private JEditarCatalogo jeditarCatalogo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, DefaultTableModel modelTitulos, JCatalogo catalogo) {
		try {
			JConfigurarTitulos dialog = new JConfigurarTitulos(modelTitulos, catalogo);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args, DefaultTableModel modelTitulos, JEditarCatalogo catalogo) {
		try {
			JConfigurarTitulos dialog = new JConfigurarTitulos(modelTitulos, catalogo);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JConfigurarTitulos(DefaultTableModel modelTitulos, JCatalogo cat) {
		
		jcatalogo = cat;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				jcatalogo.setModelTitulos(modelTitulos);
			}
		});
		setModal(true);
		//setLocationRelativeTo(null);
		setTitle("Configurar Titulos");
		//tableAudios = new DefaultTableModel();
		//tableAudios = modelAudios;
		
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
		table.setModel(modelTitulos);
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtTitulo.getText().trim().equals("")){
					return;
				}
				
				if(cbmIdioma.getSelectedIndex() == 0){
					return;
				}
				
				for (int i = 0; i < modelTitulos.getRowCount(); i++) {
					if(modelTitulos.getValueAt(i, 0).equals(cbmIdioma.getSelectedItem().toString())){
						return;
					}
				}
				
				modelTitulos.addRow(new Object[] {cbmIdioma.getSelectedItem().toString(), txtTitulo.getText()});
			}
		});
		button.setBounds(224, 67, 52, 23);
		contentPanel.add(button);
		
		JLabel lblCaminho = new JLabel("Titulo:");
		lblCaminho.setBounds(10, 32, lblCaminho.getPreferredSize().width, lblCaminho.getPreferredSize().height);
		contentPanel.add(lblCaminho);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(75, 26, 349, 28);
		contentPanel.add(txtTitulo);
		txtTitulo.setColumns(10);
		
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
	public JConfigurarTitulos(DefaultTableModel modelTitulos, JEditarCatalogo cat) {
		
		jeditarCatalogo = cat;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				jeditarCatalogo.setModelTitulos(modelTitulos);
			}
		});
		setModal(true);
		//setLocationRelativeTo(null);
		setTitle("Configurar Titulos");
		//tableAudios = new DefaultTableModel();
		//tableAudios = modelAudios;
		
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
		table.setModel(modelTitulos);
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtTitulo.getText().trim().equals("")){
					return;
				}
				
				if(cbmIdioma.getSelectedIndex() == 0){
					return;
				}
				
				for (int i = 0; i < modelTitulos.getRowCount(); i++) {
					if(modelTitulos.getValueAt(i, 0).equals(cbmIdioma.getSelectedItem().toString())){
						return;
					}
				}
				
				modelTitulos.addRow(new Object[] {cbmIdioma.getSelectedItem().toString(), txtTitulo.getText()});
			}
		});
		button.setBounds(224, 67, 52, 23);
		contentPanel.add(button);
		
		JLabel lblCaminho = new JLabel("Titulo:");
		lblCaminho.setBounds(10, 32, lblCaminho.getPreferredSize().width, lblCaminho.getPreferredSize().height);
		contentPanel.add(lblCaminho);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(75, 26, 349, 28);
		contentPanel.add(txtTitulo);
		txtTitulo.setColumns(10);
		
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
