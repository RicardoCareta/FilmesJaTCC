package view;

import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import control.ControlImagens;
import util.ImgUtils;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class JImagemSite extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnSelecionarIMG1;
	private JTextField txtCaminhoImagem1;
	private JLabel lblFoto1;
	private JTextField txtCaminhoImagem2;
	private JTextField txtCaminhoImagem3;
	private JButton btnSelecionarIMG2;
	private JButton btnSelecionarIMG3;
	private JLabel lblFoto2;
	private JLabel lblFoto3;
	
	private ControlImagens controlImagens;
	private List<ImageIcon> imgs;
	
	private ImgUtils imgUtil;
	private JButton btnSalvar;

	private static JImagemSite dialog;
	
	/**
	 * Launch the application.
	 */
	public static void main(String args) {
		try {
			dialog = new JImagemSite();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
			if(args != null){
				dialog.addWindowListener(new WindowListener() {
					
					@Override
					public void windowOpened(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowIconified(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowDeiconified(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowDeactivated(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowClosing(WindowEvent arg0) {
						JControleAdministrador.GoBackPanel(JControleAdministrador.PanelConfig);
					}
					
					@Override
					public void windowClosed(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowActivated(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				});
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JImagemSite() {
		
		controlImagens = new ControlImagens();
		
		imgUtil = new ImgUtils();
		
		imgs = controlImagens.SelecionarTipo(ControlImagens.TIPO_SLIDE);
		
		setTitle("Imagens Site");
		setBounds(100, 100, 611, 609);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		btnSelecionarIMG1 = new JButton("...");
		btnSelecionarIMG1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SelecionarImg(txtCaminhoImagem1, lblFoto1);
			}
		});
		btnSelecionarIMG1.setBounds(398, 12, 41, 24);
		getContentPane().add(btnSelecionarIMG1);
		
		JLabel lblCaminho = new JLabel("Caminho:");
		lblCaminho.setBounds(12, 15, 56, 18);
		getContentPane().add(lblCaminho);
		
		txtCaminhoImagem1 = new JTextField();
		txtCaminhoImagem1.setBounds(71, 13, 322, 22);
		getContentPane().add(txtCaminhoImagem1);
		txtCaminhoImagem1.setColumns(10);
		
		lblFoto1 = new JLabel("");
		lblFoto1.setBounds(71, 45, 322, 140);
		getContentPane().add(lblFoto1);
		
		imgUtil.OrganizarImagem(lblFoto1, imgs.get(0), ImgUtils.LOW_RESOLUTION);
		imgUtil.OrganizarImagem(lblFoto2, imgs.get(1), ImgUtils.LOW_RESOLUTION);
		imgUtil.OrganizarImagem(lblFoto3, imgs.get(2), ImgUtils.LOW_RESOLUTION);
		
		txtCaminhoImagem2 = new JTextField();
		txtCaminhoImagem2.setColumns(10);
		txtCaminhoImagem2.setBounds(71, 197, 322, 22);
		getContentPane().add(txtCaminhoImagem2);
		
		lblFoto2 = new JLabel("");
		lblFoto2.setBounds(71, 231, 322, 140);
		getContentPane().add(lblFoto2);
		
		btnSelecionarIMG2 = new JButton("...");
		btnSelecionarIMG2.setBounds(398, 196, 41, 24);
		btnSelecionarIMG2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SelecionarImg(txtCaminhoImagem2, lblFoto2);
			}
		});
		getContentPane().add(btnSelecionarIMG2);
		
		JLabel label_1 = new JLabel("Caminho:");
		label_1.setBounds(12, 199, 56, 18);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Caminho:");
		label_2.setBounds(12, 385, 56, 18);
		getContentPane().add(label_2);
		
		txtCaminhoImagem3 = new JTextField();
		txtCaminhoImagem3.setColumns(10);
		txtCaminhoImagem3.setBounds(71, 383, 322, 22);
		getContentPane().add(txtCaminhoImagem3);
		
		lblFoto3 = new JLabel("");
		lblFoto3.setBounds(71, 417, 322, 145);
		getContentPane().add(lblFoto3);
		
		btnSelecionarIMG3 = new JButton("...");
		btnSelecionarIMG3.setBounds(398, 382, 41, 24);
		btnSelecionarIMG3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SelecionarImg(txtCaminhoImagem3, lblFoto3);
			}
		});
		getContentPane().add(btnSelecionarIMG3);
		contentPanel.setLayout(null);
		
		imgUtil.OrganizarImagem(lblFoto1, imgs.get(0), ImgUtils.LOW_RESOLUTION);
		imgUtil.OrganizarImagem(lblFoto2, imgs.get(1), ImgUtils.LOW_RESOLUTION);
		imgUtil.OrganizarImagem(lblFoto3, imgs.get(2), ImgUtils.LOW_RESOLUTION);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlImagens.DeletarTipo(ControlImagens.TIPO_SLIDE);
				
				ImageIcon img1 = new ImageIcon(imgUtil.ConvertIconToImage(lblFoto1.getIcon()));
				ImageIcon img2 = new ImageIcon(imgUtil.ConvertIconToImage(lblFoto2.getIcon()));
				ImageIcon img3 = new ImageIcon(imgUtil.ConvertIconToImage(lblFoto3.getIcon()));
				
				controlImagens.Inserir(imgUtil.ConvertImageToInputStream(img3), ControlImagens.TIPO_SLIDE);
				controlImagens.Inserir(imgUtil.ConvertImageToInputStream(img1), ControlImagens.TIPO_SLIDE);
				controlImagens.Inserir(imgUtil.ConvertImageToInputStream(img2), ControlImagens.TIPO_SLIDE);
				
				MessagemBoxCustom.ShowMensagem("Imagens salvas com sucesso");
				
				dialog.dispose();
			}
		});
		btnSalvar.setBounds(484, 534, 100, 28);
		getContentPane().add(btnSalvar);
	}
	
	public void SelecionarImg(JTextField txtCaminho, JLabel lblFoto){
		FileFilter imageFilter = new FileNameExtensionFilter("Arquivos de Imagens",
				ImageIO.getReaderFileSuffixes());
		JFileChooser file = new JFileChooser();
		file.addChoosableFileFilter(imageFilter);
		file.setAcceptAllFileFilterUsed(false);
		int retorno = file.showSaveDialog(null);
		if (retorno == JFileChooser.APPROVE_OPTION) {
			txtCaminho.setText(file.getSelectedFile().getAbsolutePath());
			
			ImageIcon in = new ImageIcon(txtCaminho.getText());
			Image dimg = in.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon imgI = new ImageIcon(dimg);
			lblFoto.setIcon(imgI);

		}
	}
}
