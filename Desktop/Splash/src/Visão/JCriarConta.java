package Visão;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.Calendar;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import Controle.ControlConta;
import Controle.ControlIdioma;
import util.DateLabelFormatter;
import util.Images;
import util.ImgUtils;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.Color;

@SuppressWarnings("serial")
public class JCriarConta extends JFrame {

	private JPanel contentPane;
	private JLabel lblImagemPerfil;
	private JTextField lblNomeConta;
	private JLabel lblIdioma;
	private JLabel lblDataDeNascimento;
	private SqlDateModel model;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	private Properties p;
	@SuppressWarnings("rawtypes")
	private JComboBox cbmIdioma;
	
	private ImgUtils imgUtil;
	
	private ControlConta controlConta;
	private ControlIdioma controlIdioma;
	
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel boxModelIdioma;
	
	private int ID;
	
	private static final String VAZIO = "";
	private JLabel lblE;
	private JButton btnSalvar;
	
	private Calendar cal;
	private SpringLayout springLayout;
	/**
	 * Launch the application.
	 */
	public static void main(int ID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCriarConta frame = new JCriarConta(ID);
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
	@SuppressWarnings("rawtypes")
	public JCriarConta(int ID) {
		setUndecorated(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				ClosedWindow();
			}
		});
		this.ID = ID;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 442, 534);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		setLocationRelativeTo(null);
		
		lblE = new JLabel("E");
		lblE.setBounds(143, 132, 27, 28);
		imgUtil = new ImgUtils();
		lblE.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ChangeFotoClick();
			}
		});
		contentPane.setLayout(null);
		
		lblNomeConta = new JTextField("");
		lblNomeConta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNomeConta.setBounds(118, 193, 241, 28);
		contentPane.add(lblNomeConta);
		contentPane.add(lblE);
		
		lblImagemPerfil = new JLabel("");
		lblImagemPerfil.setBounds(143, 11, 155, 149);
		contentPane.add(lblImagemPerfil);
		
		lblIdioma = new JLabel("Idioma:");
		lblIdioma.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIdioma.setForeground(Color.WHITE);
		lblIdioma.setBounds(58, 241, lblIdioma.getPreferredSize().width, lblIdioma.getPreferredSize().height);
		contentPane.add(lblIdioma);
		
		cbmIdioma = new JComboBox();
		cbmIdioma.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbmIdioma.setBounds(118, 236, 200, 28);
		contentPane.add(cbmIdioma);
		
		lblDataDeNascimento = new JLabel("<html><center>Data de <br />Nascimento:</center></html>");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDataDeNascimento.setForeground(Color.WHITE);
		lblDataDeNascimento.setBounds(27, 275, lblDataDeNascimento.getPreferredSize().width, lblDataDeNascimento.getPreferredSize().height);
		contentPane.add(lblDataDeNascimento);
		
		model = new SqlDateModel();
		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model, p);

		springLayout = new SpringLayout();
		
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBackground(Color.BLACK);
	
		springLayout.putConstraint(SpringLayout.NORTH, datePicker.getJFormattedTextField(), 0, SpringLayout.NORTH, datePicker);
		springLayout.putConstraint(SpringLayout.SOUTH, datePicker.getJFormattedTextField(), 23, SpringLayout.NORTH, datePicker);
		springLayout.putConstraint(SpringLayout.EAST, datePicker.getJFormattedTextField(), -24, SpringLayout.EAST, datePicker);
		datePicker.setBounds(118, 275, 200, 38);
		springLayout = (SpringLayout) datePicker.getLayout();
	//	springLayout.putConstraint(SpringLayout.WEST, datePicker.getJFormattedTextField(), 25, SpringLayout.WEST, datePicker);
		datePicker.getJFormattedTextField().setEditable(true);
		
		contentPane.add(datePicker);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalvar.setBounds(220, 441, 98, 28);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//"E o meu herói surge bem na minha frente, e o que eu tenho pra dizer é o seguinte: Vou ser alguem pra te orgulhar, vc me faz não querer parar"
				ClickSalvar(arg0);
			}
		});
		contentPane.add(btnSalvar);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsurio.setForeground(Color.WHITE);
		lblUsurio.setBounds(55, 197, lblUsurio.getPreferredSize().width, lblUsurio.getPreferredSize().height);
		contentPane.add(lblUsurio);
		
		JLabel lblNewLabel = new JLabel("Voltar");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ClosedWindow();
			}
		});
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(386, 11, 46, 14);
		//contentPane.add(lblNewLabel);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVoltar.setBounds(118, 441, 98, 28);
		btnVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClosedWindow();
			}
		});
		contentPane.add(btnVoltar);
		
		JLabel lblX = new JLabel("X");
		lblX.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblX.setForeground(Color.WHITE);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ClosedWindow();
			}
		});
		lblX.setBounds(421, 0, lblX.getPreferredSize().width, lblX.getPreferredSize().height);
		contentPane.add(lblX);
	
		Configs();		
	}
	
	private void ChangeFotoClick(){
		FileFilter imageFilter = new FileNameExtensionFilter("Arquivos de Imagens",
				ImageIO.getReaderFileSuffixes());
		JFileChooser file = new JFileChooser();
		file.addChoosableFileFilter(imageFilter);
		file.setAcceptAllFileFilterUsed(false);
		int retorno = file.showSaveDialog(null);
		if (retorno == JFileChooser.APPROVE_OPTION) {
			lblImagemPerfil.setIcon(new ImageIcon(file.getSelectedFile().getAbsolutePath()));
			imgUtil.OrganizarImagem(lblImagemPerfil, ImgUtils.LOW_RESOLUTION);
		}
	}
	
	private void ClickSalvar(ActionEvent e){
	
		if (lblNomeConta.getText().equals("") || cbmIdioma.getSelectedIndex() == 0 || datePicker.getJFormattedTextField().getText().equals("")){
			MessagemBoxCustom.ShowMensagem("Por favor, preencha todos os campos.", this, MessagemBoxCustom.DONT_CLOSE);
			return;
		}
		
		imgUtil = new ImgUtils();
		
		ImageIcon imgs = new ImageIcon(imgUtil.ConvertIconToImage(lblImagemPerfil.getIcon()));
	
		Date selectedDate = (Date) datePicker.getModel().getValue();
		
		if(selectedDate.after(new java.util.Date())){
			MessagemBoxCustom.ShowMensagem("Nascimento inválido.", this, MessagemBoxCustom.DONT_CLOSE);
			return;
		}
		
		//System.out.println(selectedDate.getYear());
		
		controlConta = new ControlConta();
		controlConta.Inserir(lblNomeConta.getText(), "", imgUtil.ConvertImageToInputStream(imgs), selectedDate, ID, controlIdioma.getID(cbmIdioma.getSelectedItem().toString()));
	//	controlConta.Update(ID, lblNomeConta.getText(), "", imgUtil.ConvertImageToInputStream(imgs), selectedDate, controlConta.getCliente(ID), controlIdioma.getID(cbmIdioma.getSelectedItem().toString()), ConsAtivo.ATIVO);
		MessagemBoxCustom.ShowMensagem("Conta criada com sucesso.", this, MessagemBoxCustom.CLOSE);
		this.dispose();
		
		JSelecionarConta.main(ID);
	}
	
	@SuppressWarnings("unchecked")
	private void Instancias(){
		imgUtil = new ImgUtils();
		controlConta = new ControlConta();
		controlIdioma = new ControlIdioma();
		boxModelIdioma = new DefaultComboBoxModel<>(controlIdioma.Selecionar().toArray());
		boxModelIdioma.insertElementAt(VAZIO, 0);
		cbmIdioma.setModel(boxModelIdioma);
	}
	
	private void Configs(){
		Instancias();
		
		Images images = new Images();
		
		lblE.setIcon(imgUtil.ResizeImageIcon(lblE, images.Image_Camera, ImgUtils.LOW_RESOLUTION));
		lblImagemPerfil.setIcon(imgUtil.ResizeImageIcon(lblImagemPerfil, images.Image_AnonimoFace, ImgUtils.LOW_RESOLUTION));
	}
	
	private void ClosedWindow(){
		this.dispose();
		JSelecionarConta.main(ID);
	}
}
