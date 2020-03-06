package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MessagemBoxCustom extends JDialog {

	private final static JPanel contentPanel = new JPanel();

	private static MessagemBoxCustom dialog;
	
	private static String mensagem = "Teste";
	private static JLabel lblMensagem = new JLabel(mensagem);
	
	
	private static void setMensagem(String _mensagem) {
		lblMensagem.setText(_mensagem);
	}
	
	public static void ShowMensagem(String Mensagem) {
		try {
			setMensagem("");
			setMensagem(Mensagem);
			dialog = new MessagemBoxCustom();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			dialog.setSize(new Dimension(Mensagem.length() + 300, 200));
			dialog.setLocationRelativeTo(null);
			
			dialog.setVisible(true);
			dialog.requestFocus();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void ShowMensagem(String Mensagem, String Titulo) {
		try {
			setMensagem("");
			setMensagem(Mensagem);
			dialog = new MessagemBoxCustom();
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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//null
	}

	/**
	 * Create the dialog.
	 */
	public MessagemBoxCustom() {
//		setUndecorated(true);
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
			//	dialog.requestFocus();
			}
		});
		
		setModal(true);
		setIconImage(null);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		lblMensagem.setBounds(65, 64, lblMensagem.getPreferredSize().width, lblMensagem.getPreferredSize().height);
		contentPanel.add(lblMensagem);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dialog.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				/*JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);*/
			}
		}
	}
}
