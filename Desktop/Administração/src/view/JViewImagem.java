package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class JViewImagem extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JLabel lblImagem;

	/**
	 * Launch the application.
	 */
	public static void main(ImageIcon img) {
		try {
			JViewImagem dialog = new JViewImagem(img);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JViewImagem(ImageIcon img) {
		//setUndecorated(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblImagem = new JLabel(img);
			lblImagem.setBounds(98, 11, 230, 217);
			contentPanel.add(lblImagem);
		}

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton okButton = new JButton("OK");
			okButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					ClickFechar();
				}
			});
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
		
		OrganizarImagem(lblImagem, img);
	}

	private void ClickFechar(){
		this.dispose();
	}
	
	static void OrganizarImagem(JLabel lbl, ImageIcon img) {
		Image dimg = img.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imgI = new ImageIcon(dimg);
		lbl.setIcon(imgI);
	}
}
