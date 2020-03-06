package view;

import java.awt.BorderLayout;
import java.text.ParseException;
import java.util.Properties;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import control.ControlCliente;
import control.ControlIdioma;
import control.ControlPais;
import util.DateLabelFormatter;
import javax.swing.JComboBox;

public class JUsuarioView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tblCliente;
	private JLabel lblEmail;
	private JLabel lblCpf;
	private JLabel lblDataDoCadastro;
	private JLabel lblTelefone;
	private JLabel lblPais;
	private JLabel lblIdioma;
	private JCheckBox chckbxDevedores;
	private JTextField txtEmail;
	private JFormattedTextField txtCPF;
	private JFormattedTextField txtTelefone;
	private JComboBox cbmIdioma;
	
	private ControlPais controlPais;
	private ControlIdioma controlIdioma;
	private ControlCliente controlCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JUsuarioView dialog = new JUsuarioView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JUsuarioView() {
		
		controlPais = new ControlPais();
		controlIdioma = new ControlIdioma();
		controlCliente = new ControlCliente();
		
		setBounds(100, 100, 1280, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 367, 1244, 283);
		contentPanel.add(scrollPane);
		
		tblCliente = new JTable();
		tblCliente.setModel(controlCliente.tblCliente());
		scrollPane.setViewportView(tblCliente);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(28, 11, lblEmail.getPreferredSize().width, lblEmail.getPreferredSize().height);
		contentPanel.add(lblEmail);
		
		lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(33, 49, lblCpf.getPreferredSize().width, lblCpf.getPreferredSize().height);
		contentPanel.add(lblCpf);
		
		lblDataDoCadastro = new JLabel("Data do Cadastro:");
		lblDataDoCadastro.setBounds(306, 11, lblDataDoCadastro.getPreferredSize().width, lblDataDoCadastro.getPreferredSize().height);
		contentPanel.add(lblDataDoCadastro);
		
		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(10, 104, lblTelefone.getPreferredSize().width, lblTelefone.getPreferredSize().height);
		contentPanel.add(lblTelefone);
		
		lblPais = new JLabel("Pais:");
		lblPais.setBounds(28, 141, lblPais.getPreferredSize().width, lblPais.getPreferredSize().height);
		contentPanel.add(lblPais);
		
		lblIdioma = new JLabel("Idioma");
		lblIdioma.setBounds(24, 183, lblIdioma.getPreferredSize().width, lblIdioma.getPreferredSize().height);
		contentPanel.add(lblIdioma);
		
		chckbxDevedores = new JCheckBox("Devedores");
		chckbxDevedores.setBounds(6, 281, 97, 23);
		contentPanel.add(chckbxDevedores);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(66, 8, 115, 20);
		contentPanel.add(txtEmail);
		txtEmail.setColumns(10);
		
		MaskFormatter msk = null;
		try {
			msk = new MaskFormatter("###.###.###-##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtCPF = new JFormattedTextField();
		msk.install(txtCPF);
		
		txtCPF.setBounds(66, 45, 115, 23);
		contentPanel.add(txtCPF);
		
		UtilDateModel model = new UtilDateModel();
		//model.setDate(20,04,2014);
		// Need this...
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		// Don't know about the formatter, but there it is...
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setLocation(405, 11);
		datePicker.setSize(200, 28);
		
		
		contentPanel.add(datePicker);
		
		MaskFormatter mskTelefone = null;
		try {
			mskTelefone = new MaskFormatter("(##)#####-####");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		txtTelefone = new JFormattedTextField();
		txtTelefone.setBounds(66, 95, 115, 23);
		mskTelefone.install(txtTelefone);
		contentPanel.add(txtTelefone);
		
		JComboBox cbmPais = new JComboBox(controlPais.SelecionarPaises(1).toArray());
		cbmPais.setBounds(66, 138, 115, 23);
		contentPanel.add(cbmPais);
		
		cbmIdioma = new JComboBox(controlIdioma.Selecionar().toArray());
		cbmIdioma.setBounds(66, 180, 115, 23);
	
		contentPanel.add(cbmIdioma);
		
	}
}
