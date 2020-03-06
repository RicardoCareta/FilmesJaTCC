package view;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.ControlImagens;
import util.ImgUtils;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class JConfiguracoes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFoto1;
	private JLabel label1;
	private JLabel lblFoto_1;
	private JTextField txtFoto2;
	private JButton button_1;
	private JLabel lblFoto_2;
	private JTextField txtFoto3;
	private JButton button_2;
	private JButton button;
	private JButton btnSalvar;
	
	private ControlImagens controlImagens;
	private JLabel lblFoto;
	private JLabel lblFoto2;
	private JLabel lblFoto3;

	private List<ImageIcon> listaImagens;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			try {
				for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					if ("Windows".equals(info.getName())) {
						UIManager.setLookAndFeel(info.getClassName());
						break;
					}
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
			
			JConfiguracoes dialog = new JConfiguracoes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JConfiguracoes() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		});
		setBounds(100, 100, 592, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setModal(true);
		setLocationRelativeTo(null);
		//Instancias
		controlImagens = new ControlImagens();
		
		txtFoto1 = new JTextField();
		txtFoto1.setBounds(89, 55, 197, 28);
		contentPanel.add(txtFoto1);
		txtFoto1.setColumns(10);
		
		label1 = new JLabel("Foto 1");
		label1.setBounds(48, 62, label1.getPreferredSize().width, label1.getPreferredSize().height);
		contentPanel.add(label1);
		
		button = new JButton("...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AbrirMenu(txtFoto1, lblFoto);
			}
		});
		button.setBounds(296, 55, 37, 28);
		contentPanel.add(button);
		
		lblFoto_1 = new JLabel("Foto 2");
		lblFoto_1.setBounds(48, 177, lblFoto_1.getPreferredSize().width, lblFoto_1.getPreferredSize().height);
		contentPanel.add(lblFoto_1);
		
		txtFoto2 = new JTextField();
		txtFoto2.setColumns(10);
		txtFoto2.setBounds(89, 170, 197, 28);
		contentPanel.add(txtFoto2);
		
		button_1 = new JButton("...");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AbrirMenu(txtFoto2, lblFoto2);
			}
		});
		button_1.setBounds(296, 170, 37, 28);
		contentPanel.add(button_1);
		
		lblFoto_2 = new JLabel("Foto 3");
		lblFoto_2.setBounds(48, 299, lblFoto_2.getPreferredSize().width, lblFoto_2.getPreferredSize().height);
		contentPanel.add(lblFoto_2);
		
		txtFoto3 = new JTextField();
		txtFoto3.setColumns(10);
		txtFoto3.setBounds(89, 292, 197, 28);
		contentPanel.add(txtFoto3);
		
		button_2 = new JButton("...");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AbrirMenu(txtFoto3, lblFoto3);
			}
		});
		button_2.setBounds(290, 291, 37, 28);
		contentPanel.add(button_2);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlImagens.DeletarTipo(ControlImagens.TIPO_SLIDE);
				ImgUtils img = new ImgUtils();
				
				
				if(!txtFoto1.getText().equals("")){
					controlImagens.Inserir(txtFoto1.getText(), ControlImagens.TIPO_SLIDE);
				}
				else{
					if(listaImagens.size() >= 1){
						controlImagens.Inserir(img.ConvertImageToInputStream(listaImagens.get(0)), ControlImagens.TIPO_SLIDE);	
					}
				}
				
				if(!txtFoto2.getText().equals("")){
					controlImagens.Inserir(txtFoto2.getText(), ControlImagens.TIPO_SLIDE);
				}else{
					if(listaImagens.size() >= 2){
						controlImagens.Inserir(img.ConvertImageToInputStream(listaImagens.get(1)), ControlImagens.TIPO_SLIDE);
				
					}
				}
				
				if(!txtFoto3.getText().equals("")){
					controlImagens.Inserir(txtFoto3.getText(), ControlImagens.TIPO_SLIDE);
				}else{
					if(listaImagens.size() >= 3){
						controlImagens.Inserir(img.ConvertImageToInputStream(listaImagens.get(2)), ControlImagens.TIPO_SLIDE);
					}
				}
				
				MessagemBoxCustom.ShowMensagem("Imagens do site cadastradas com sucesso");
			}
		});
		btnSalvar.setBounds(221, 401, 89, 23);
		contentPanel.add(btnSalvar);
		
		lblFoto = new JLabel("");
		lblFoto.setBounds(337, 11, 212, 110);
		contentPanel.add(lblFoto);
		
		lblFoto2 = new JLabel("");
		lblFoto2.setBounds(337, 126, 212, 110);
		contentPanel.add(lblFoto2);
		
		lblFoto3 = new JLabel("");
		lblFoto3.setBounds(337, 251, 212, 110);
		contentPanel.add(lblFoto3);
		
		LoadFotos();
	}
	
	private void AbrirMenu(JTextField txtFoto, JLabel lblF){
		ImgUtils img = new ImgUtils();
		String caminho = img.buscaImagens();
		
		if(caminho.equals("nulo")){
			return;
		}
		
		txtFoto.setText(caminho);
		
		ImageIcon in = new ImageIcon(txtFoto.getText());
		Image dimg = in.getImage().getScaledInstance(lblF.getWidth(), lblF.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imgI = new ImageIcon(dimg);
		lblF.setIcon(imgI);
	}
	
	private void LoadFotos(){
		controlImagens = new ControlImagens();
		//controlImagens.SelecionarTipo(ControlImagens.TIPO_SLIDE);
		listaImagens = controlImagens.SelecionarTipo(ControlImagens.TIPO_SLIDE);
		ImgUtils img = new ImgUtils();
		if(listaImagens.size() >= 1){
			img.OrganizarImagem(lblFoto, listaImagens.get(0), false);
		}
		
		if(listaImagens.size() >= 2){
			img.OrganizarImagem(lblFoto2, listaImagens.get(1), false);
		}
		
		if(listaImagens.size() >= 3){
			img.OrganizarImagem(lblFoto3, listaImagens.get(2), false);
		}
		
		
		
	}
}
