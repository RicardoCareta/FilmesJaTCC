package Visão;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;

@SuppressWarnings("serial")
public class MessagemBoxCustom extends JDialog {
	public static boolean canClose = false;
	public static int returnDado;
	
	private final static JPanel contentPanel = new JPanel();

	private static MessagemBoxCustom dialog;
	
	private static String mensagem = "Teste";
	private static JLabel lblMensagem = new JLabel(mensagem);
	
	private static JFrame frm = null;
	private static JDialog dg = null;
	
	public static final boolean CLOSE = true;
	public static final boolean DONT_CLOSE = false;
	
	private static final int FRAME = 1;
	private static final int DIALOG = 2;
	
	public static final int CANCEL = 1;
	public static final int OK = 2;
	
	private static int TypeJ;
	
	private static boolean ClosePrevi;
	
	private static boolean OptionsC;
	
	private static void setMensagem(String _mensagem) {
		lblMensagem.setText(_mensagem);
	}
	
	public static void ShowMensagem(String Mensagem, boolean sairAPP) {
		try {
			setMensagem("");
			setMensagem(Mensagem);
			dialog = new MessagemBoxCustom(sairAPP);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			dialog.setSize(new Dimension(Mensagem.length() + 300, 200));
			dialog.setLocationRelativeTo(null);
			
			dialog.setVisible(true);
			dialog.requestFocus();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void ShowMensagem(String Mensagem, String Titulo, boolean sairAPP) {
		try {
			setMensagem("");
			setMensagem(Mensagem);
			dialog = new MessagemBoxCustom(sairAPP);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			dialog.setSize(new Dimension(Mensagem.length() + 300, 200));
			dialog.setLocationRelativeTo(null);
			dialog.setTitle(Titulo);
			dialog.setVisible(true);
			dialog.requestFocus();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void ShowMensagem(String Mensagem, JFrame frame, boolean FecharFrame){
		OptionsC = false;
		frm = frame;
		TypeJ = FRAME;
		ClosePrevi = FecharFrame;
		ShowMensagem(Mensagem, DONT_CLOSE);
	}
	
	public static void ShowMensagem(String Mensagem, JDialog dialog, boolean FecharDialog){
		OptionsC = false;
		dg = dialog;
		TypeJ = DIALOG;
		ClosePrevi = FecharDialog;
		ShowMensagem(Mensagem, DONT_CLOSE);
	}
	
	public static void ShowMensagem(String Mensagem, JFrame frame, boolean FecharFrame, boolean Options){
		frm = frame;
		TypeJ = FRAME;
		ClosePrevi = FecharFrame;
		OptionsC = Options;
		ShowMensagem(Mensagem, DONT_CLOSE);
	}
	
	public static void ShowMensagem(String Mensagem, JDialog dialog, boolean FecharDialog, boolean Options){
		OptionsC = false;
		dg = dialog;
		TypeJ = DIALOG;
		ClosePrevi = FecharDialog;
		OptionsC = Options;
		ShowMensagem(Mensagem, DONT_CLOSE);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//null
	}

	/**
	 * Create the dialog.
	 */
	
	private void LastConfigs(boolean sairAPP){
		if(sairAPP){
			System.exit(0);
		}else{
			if(TypeJ == FRAME){
				if(ClosePrevi){
					frm.dispose();
					canClose = true;
				}else{
					frm.setVisible(true);
				}
			}
			else if(TypeJ == DIALOG){
				if(ClosePrevi){
					dg.dispose();
				}else{
					dg.setVisible(true);
				}
			}
			dialog.dispose();
		}
	}
	
	public MessagemBoxCustom(boolean sairAPP) {
		//setUndecorated(true);
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
			//	dialog.requestFocus();
			}
		});
		returnDado = 0;
		
		setModal(true);
		setIconImage(null);
		setBounds(100, 100, 272, 156);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(null);
		//getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.setLayout(null);
		lblMensagem.setBounds(65, 64, lblMensagem.getPreferredSize().width, lblMensagem.getPreferredSize().height);
		contentPanel.add(lblMensagem);
		JButton okButton = new JButton("OK");
		JPanel buttonPane = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						returnDado = OK;
						LastConfigs(sairAPP);
					}
				});
				getRootPane().setDefaultButton(okButton);
			}
			{
				/*JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);*/
			}
		}
		
		
		
		//buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		//okButton.setActionCommand("OK");
		//buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		{
			{
				JButton btnCancel = new JButton("Cancelar");
				
				btnCancel.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						returnDado = CANCEL;
						LastConfigs(sairAPP);
					}
				});
				{
					btnCancel.setActionCommand("Cancel");
					if(OptionsC){
						buttonPane.add(btnCancel);
					}
				}
			}
		}
		//contentPanel.add(buttonPane, BorderLayout.SOUTH);
	}
}
