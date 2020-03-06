package Visão;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import Constantes.ConsAtivo;
import Controle.ControlConta;
import Controle.ControlIdioma;
import util.DateLabelFormatter;
import util.ImgUtils;
import util.ScreenConfigs.ScreenSize;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.sound.midi.ControllerEventListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;

@SuppressWarnings("serial")
public class JConta extends JDialog {

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
	
	private static JConta frame;
	private JButton btnExcluirConta;
	/**
	 * Launch the application.
	 */
	public static void main(int ID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new JConta(ID);
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
	public JConta(int ID) {
		setUndecorated(true);
		this.ID = ID;
		setModal(true);
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, ScreenSize.ConvertorSize(422, ScreenSize.FULANO_HEIGHT), ScreenSize.ConvertorSize(471, ScreenSize.FULANO_HEIGHT));
		//setBounds(100, 100, 422, 471);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);
		
		lblE = new JLabel("E");
		lblE.setBounds( ScreenSize.ConvertorSize(142, ScreenSize.FULANO_HEIGHT),  ScreenSize.ConvertorSize(114, ScreenSize.FULANO_HEIGHT),  ScreenSize.ConvertorSize(42, ScreenSize.FULANO_HEIGHT),  ScreenSize.ConvertorSize(28, ScreenSize.FULANO_HEIGHT));
		imgUtil = new ImgUtils();
		lblE.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ChangeFotoClick();
			}
		});
		contentPane.setLayout(null);
		
		lblNomeConta = new JTextField("Nome", JLabel.CENTER);
		lblNomeConta.setBounds( ScreenSize.ConvertorSize(131, ScreenSize.FULANO_HEIGHT),  ScreenSize.ConvertorSize(180, ScreenSize.FULANO_HEIGHT), 200, 22);
		//lblNomeConta.setForeground(Color.WHITE);
		contentPane.add(lblNomeConta);
		contentPane.add(lblE);
		
		lblImagemPerfil = new JLabel("");
		lblImagemPerfil.setBounds( ScreenSize.ConvertorSize(143, ScreenSize.FULANO_HEIGHT),  ScreenSize.ConvertorSize(11, ScreenSize.FULANO_HEIGHT),  ScreenSize.ConvertorSize(155, ScreenSize.FULANO_HEIGHT),  ScreenSize.ConvertorSize(130, ScreenSize.FULANO_HEIGHT));
		lblImagemPerfil.setIcon(new ImageIcon(JConta.class.getResource("/Vis\u00E3o/imagens/anonimoface.png")));
		contentPane.add(lblImagemPerfil);
		
		lblIdioma = new JLabel("Idioma:");
		lblIdioma.setBounds( ScreenSize.ConvertorSize(83, ScreenSize.FULANO_HEIGHT),  ScreenSize.ConvertorSize(230, ScreenSize.FULANO_HEIGHT), 36, 14);
		lblIdioma.setForeground(Color.WHITE);
		contentPane.add(lblIdioma);
		
		cbmIdioma = new JComboBox();
		cbmIdioma.setBounds( ScreenSize.ConvertorSize(129, ScreenSize.FULANO_HEIGHT),  ScreenSize.ConvertorSize(227, ScreenSize.FULANO_HEIGHT), 117, 20);
		contentPane.add(cbmIdioma);

		
		lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setBounds(ScreenSize.ConvertorSize(21, ScreenSize.FULANO_HEIGHT), ScreenSize.ConvertorSize(270, ScreenSize.FULANO_HEIGHT), 100, 14);
		lblDataDeNascimento.setForeground(Color.white);
		contentPane.add(lblDataDeNascimento);
		
		model = new SqlDateModel();
		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model, p);

		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(ScreenSize.ConvertorSize(129, ScreenSize.FULANO_HEIGHT), ScreenSize.ConvertorSize(265, ScreenSize.FULANO_HEIGHT), 195, 36);
		datePicker.getJFormattedTextField().setEditable(true);
		datePicker.setBackground(Color.BLACK);
		SpringLayout springLayout = (SpringLayout) datePicker.getLayout();
		springLayout.putConstraint(SpringLayout.SOUTH, datePicker.getJFormattedTextField(), 0, SpringLayout.SOUTH, datePicker);
		contentPane.add(datePicker);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(ScreenSize.ConvertorSize(147, ScreenSize.FULANO_HEIGHT), ScreenSize.ConvertorSize(396, ScreenSize.FULANO_HEIGHT), ScreenSize.ConvertorSize(98, ScreenSize.FULANO_HEIGHT), ScreenSize.ConvertorSize(26, ScreenSize.FULANO_HEIGHT));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//"E o meu herói surge bem na minha frente, e o que eu tenho pra dizer é o seguinte: Vou ser alguem pra te orgulhar, vc me faz não querer parar"
				ClickSalvar(arg0);
			}
		});
		contentPane.add(btnSalvar);
		
		btnExcluirConta = new JButton("Excluir Conta");
		btnExcluirConta.setBounds(ScreenSize.ConvertorSize(255, ScreenSize.FULANO_HEIGHT), ScreenSize.ConvertorSize(398, ScreenSize.FULANO_HEIGHT), ScreenSize.ConvertorSize(95, ScreenSize.FULANO_HEIGHT), ScreenSize.ConvertorSize(23, ScreenSize.FULANO_HEIGHT));
		btnExcluirConta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClickExcluir();
			}
		});
		contentPane.add(btnExcluirConta);
		
		lblVoltar = new JLabel("Voltar");
		lblVoltar.setBounds(ScreenSize.ConvertorSize(377, ScreenSize.FULANO_HEIGHT), ScreenSize.ConvertorSize(11, ScreenSize.FULANO_HEIGHT), 35, 17);
		lblVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ClickVoltar();
			}
		});
		lblVoltar.setForeground(Color.white);
		lblVoltar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		contentPane.add(lblVoltar);
		
		lblNewLabel = new JLabel("Usu\u00E1rio:");
		lblNewLabel.setBounds(ScreenSize.ConvertorSize(75, ScreenSize.FULANO_HEIGHT), ScreenSize.ConvertorSize(184, ScreenSize.FULANO_HEIGHT), 46, 14);
		lblNewLabel.setForeground(Color.white);
		contentPane.add(lblNewLabel);
		
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
	
	private void ClickVoltar(){
		this.dispose();
	}
	
	private void ClickSalvar(ActionEvent e){
		//"Ele diz pra mim que meu sonho é possivel, mas também que pagarei um preço terrivel e que eu posso ter também esse poder, mas se eu não treinar eu posso até mesmo morrer"

		if(cbmIdioma.getSelectedIndex() == 0){
			System.out.println("antes");
			MessagemBoxCustom.ShowMensagem("Selecione no minimo um idioma!", frame, false);
			System.out.println("depois");
			return;
		}
		
		imgUtil = new ImgUtils();
		
		ImageIcon imgs = new ImageIcon(imgUtil.ConvertIconToImage(lblImagemPerfil.getIcon()));
	
		Date selectedDate = (Date) datePicker.getModel().getValue();
	
		if(selectedDate.after(new java.util.Date())){
			MessagemBoxCustom.ShowMensagem("Você não pode nascer no futuro!", frame, false);
			return;
		}
		
		//System.out.println(selectedDate.getYear());
		
		controlConta = new ControlConta();
		controlConta.Update(ID, lblNomeConta.getText(), "", imgUtil.ConvertImageToInputStream(imgs), selectedDate, controlConta.getCliente(ID), controlIdioma.getID(cbmIdioma.getSelectedItem().toString()), ConsAtivo.ATIVO);
	
		JFulano.setTitleFrame("Ol\u00E1, " + lblNomeConta.getText());
		JFulano.setNewImagePerfil(imgs);
		
		MessagemBoxCustom.ShowMensagem("Atualizado com sucesso", frame, true);
		this.dispose();
		
		//gi bonitona <3
		//^ concordo 
	}
	
	int cont = 0;
	private JLabel lblVoltar;
	private JLabel lblNewLabel;
	private void ClickExcluir(){
		MessagemBoxCustom.ShowMensagem("Deseja excluir a conta", frame, MessagemBoxCustom.CLOSE, true);
		
		if(MessagemBoxCustom.returnDado == MessagemBoxCustom.CANCEL){
			MessagemBoxCustom.ShowMensagem("Operação cancelada", frame, MessagemBoxCustom.CLOSE);
			return;
		}
		
		ImageIcon imgs = new ImageIcon(imgUtil.ConvertIconToImage(lblImagemPerfil.getIcon()));
		Date selec = (Date) datePicker.getModel().getValue();
		controlConta = new ControlConta();
		controlConta.Update(ID, lblNomeConta.getText(), "", imgUtil.ConvertImageToInputStream(imgs), selec, controlConta.getCliente(ID), controlIdioma.getID(cbmIdioma.getSelectedItem().toString()), ConsAtivo.DESATIVO);
		
		MessagemBoxCustom.ShowMensagem("Conta excluida com sucesso", frame, MessagemBoxCustom.CLOSE);
		this.dispose();
		JFulano.closeDispose();
		JSelecionarConta.main(controlConta.getCliente(ID));
		cont = 0;
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
		
		lblE.setIcon(imgUtil.ResizeImageIcon(lblE, new ImageIcon(JConta.class.getResource("/Vis\u00E3o/imagens/camera1600.png")), ImgUtils.LOW_RESOLUTION));
		
		lblImagemPerfil.setIcon(imgUtil.ResizeImageIcon(lblImagemPerfil, new ImageIcon(JConta.class.getResource("/Vis\u00E3o/imagens/anonimoface.png")), ImgUtils.LOW_RESOLUTION));
		
		//imgUtil.OrganizarImagem(lblE, new ImageIcon(JConta.class.getResource("/Vis\u00E3o/imagens/edit.png")), false);
		imgUtil.OrganizarImagem(lblImagemPerfil, controlConta.getImageIcon(ID), ImgUtils.LOW_RESOLUTION);
		//imgUtil.CircleRender(imgUtil.converterImageIconSize(lblImagemPerfil.getWidth(), lblImagemPerfil.getHeight(), controlConta.getImageIcon(ID)), lblImagemPerfil);
		lblNomeConta.setText(controlConta.getNome(ID));
		
		cal = Calendar.getInstance();
		cal.setTime(controlConta.getDtNasc(ID));
		
		int year = cal.get(Calendar.YEAR);
	//	System.out.println(year);
		//year -= 1000;
		int moth = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DATE);
		model.setDate(year, moth, day);
		model.setSelected(true);
		
		String nameValue = controlIdioma.getNome(controlConta.getContaIdiomaID(ID));
		
		for (int i = 0; i < cbmIdioma.getItemCount(); i++) {
			if(nameValue.equals(cbmIdioma.getItemAt(i).toString())){
				cbmIdioma.setSelectedIndex(i);
				break;
			}
		}
	}
}
