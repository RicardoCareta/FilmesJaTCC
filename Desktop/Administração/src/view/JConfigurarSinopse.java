package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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
import java.awt.event.WindowAdapter;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class JConfigurarSinopse extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	
	//private DefaultTableModel tableAudios;
	
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel cbmModel;
	
	private ControlIdioma controlIdioma;
	private JCatalogo jcatalogo;
	private JEditarCatalogo jeditarCatalogo;
	private JTextArea txtSinopse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, DefaultTableModel modelSinopse, JCatalogo catalogo) {
		try {
			JConfigurarSinopse dialog = new JConfigurarSinopse(modelSinopse, catalogo);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args, DefaultTableModel modelSinopse, JEditarCatalogo catalogo) {
		try {
			JConfigurarSinopse dialog = new JConfigurarSinopse(modelSinopse, catalogo);
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
	public JConfigurarSinopse(DefaultTableModel modelSinopse, JCatalogo cat) {
		
		jcatalogo = cat;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				jcatalogo.setModelSinopse(modelSinopse);
			}
		});
		setModal(true);
		//setLocationRelativeTo(null);
		setTitle("Configurar Sinopse");
		//tableAudios = new DefaultTableModel();
		//tableAudios = modelAudios;
		
		controlIdioma = new ControlIdioma();
		
		setBounds(100, 100, 519, 354);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JComboBox cbmIdioma = new JComboBox();
		cbmIdioma.setBounds(63, 115, 137, 23);
		cbmModel = new DefaultComboBoxModel<>(controlIdioma.Selecionar().toArray());
		cbmModel.insertElementAt(" ", 0);
		cbmIdioma.setModel(cbmModel);
		cbmIdioma.setSelectedIndex(0);
		contentPanel.add(cbmIdioma);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 162, 481, 115);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setModel(modelSinopse);
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtSinopse.getText().trim().equals("")){
					return;
				}
				
				if(cbmIdioma.getSelectedIndex() == 0){
					return;
				}
				
				for (int i = 0; i < modelSinopse.getRowCount(); i++) {
					if(modelSinopse.getValueAt(i, 0).equals(cbmIdioma.getSelectedItem().toString())){
						return;
					}
				}
				
				modelSinopse.addRow(new Object[] {cbmIdioma.getSelectedItem().toString(), txtSinopse.getText()});
				txtSinopse.setText("");
			}
		});
		button.setBounds(212, 115, 52, 23);
		contentPanel.add(button);
		
		JLabel lblCaminho = new JLabel("Sinopse:");
		lblCaminho.setBounds(10, 32, lblCaminho.getPreferredSize().width, lblCaminho.getPreferredSize().height);
		contentPanel.add(lblCaminho);
		
		JLabel lblIdioma = new JLabel("Idioma:");
		lblIdioma.setBounds(10, 118, lblIdioma.getPreferredSize().width, lblIdioma.getPreferredSize().height);
		contentPanel.add(lblIdioma);
		
		txtSinopse = new JTextArea();
		txtSinopse.setBounds(64, 13, 412, 89);
		txtSinopse.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtSinopse.getText().equals("Digite aqui a sinopse, bem resumida.\r\nPor favor!!")){
					txtSinopse.setText("");
					txtSinopse.setForeground(Color.BLACK);
					txtSinopse.setFont(new Font("SansSerif", Font.PLAIN, 12));
				}
			}
			
			@Override
			public void focusLost(FocusEvent arg0){
				if(txtSinopse.getText().trim().length() == 0){
					txtSinopse.setFont(new Font("SansSerif", Font.ITALIC, 12));
					txtSinopse.setText("Digite aqui a sinopse, bem resumida.\r\nPor favor!!");
					txtSinopse.setForeground(Color.GRAY);
				}
			}
		});
		contentPanel.add(txtSinopse);
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
	public JConfigurarSinopse(DefaultTableModel modelSinopse, JEditarCatalogo cat) {
		
		jeditarCatalogo = cat;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				jeditarCatalogo.setModelSinopse(modelSinopse);
			}
		});
		setModal(true);
		//setLocationRelativeTo(null);
		setTitle("Configurar Sinopse");
		//tableAudios = new DefaultTableModel();
		//tableAudios = modelAudios;
		
		controlIdioma = new ControlIdioma();
		
		setBounds(100, 100, 519, 354);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JComboBox cbmIdioma = new JComboBox();
		cbmIdioma.setBounds(63, 115, 137, 23);
		cbmModel = new DefaultComboBoxModel<>(controlIdioma.Selecionar().toArray());
		cbmModel.insertElementAt(" ", 0);
		cbmIdioma.setModel(cbmModel);
		cbmIdioma.setSelectedIndex(0);
		contentPanel.add(cbmIdioma);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 162, 481, 115);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setModel(modelSinopse);
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtSinopse.getText().trim().equals("")){
					return;
				}
				
				if(cbmIdioma.getSelectedIndex() == 0){
					return;
				}
				
				for (int i = 0; i < modelSinopse.getRowCount(); i++) {
					if(modelSinopse.getValueAt(i, 0).equals(cbmIdioma.getSelectedItem().toString())){
						return;
					}
				}
				
				modelSinopse.addRow(new Object[] {cbmIdioma.getSelectedItem().toString(), txtSinopse.getText()});
				txtSinopse.setText("");
			}
		});
		button.setBounds(212, 115, 52, 23);
		contentPanel.add(button);
		
		JLabel lblCaminho = new JLabel("Sinopse:");
		lblCaminho.setBounds(10, 32, lblCaminho.getPreferredSize().width, lblCaminho.getPreferredSize().height);
		contentPanel.add(lblCaminho);
		
		JLabel lblIdioma = new JLabel("Idioma:");
		lblIdioma.setBounds(10, 118, lblIdioma.getPreferredSize().width, lblIdioma.getPreferredSize().height);
		contentPanel.add(lblIdioma);
		
		txtSinopse = new JTextArea();
		txtSinopse.setBounds(64, 13, 412, 89);
		txtSinopse.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtSinopse.getText().equals("Digite aqui a sinopse, bem resumida.\r\nPor favor!!")){
					txtSinopse.setText("");
					txtSinopse.setForeground(Color.BLACK);
					txtSinopse.setFont(new Font("SansSerif", Font.PLAIN, 12));
				}
			}
			
			@Override
			public void focusLost(FocusEvent arg0){
				if(txtSinopse.getText().trim().length() == 0){
					txtSinopse.setFont(new Font("SansSerif", Font.ITALIC, 12));
					txtSinopse.setText("Digite aqui a sinopse, bem resumida.\r\nPor favor!!");
					txtSinopse.setForeground(Color.GRAY);
				}
			}
		});
		contentPanel.add(txtSinopse);
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
