package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import control.ControlDuvidasFrequentes;
import util.ScreenSize.SC;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

public class JDuvidasFrequentes extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblPergunta;
	private JLabel lblResposta;
	private JLabel lblFundo;
	private JTextArea txtReposta;
	private JButton btnSalvar;
	private JTextArea textArea;
	
	private ControlDuvidasFrequentes controlDuvidasFrequentes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDuvidasFrequentes dialog = new JDuvidasFrequentes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDuvidasFrequentes() {
		setTitle("Adicionar Duvida Frequente");
		setBounds(100, 100, 611, 489);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLocationRelativeTo(null);
		
		setModal(true);
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		

		
		lblPergunta = new JLabel("Pergunta:");
		lblPergunta.setForeground(Color.BLACK);
		lblPergunta.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPergunta.setBounds(68, 62, lblPergunta.getPreferredSize().width, lblPergunta.getPreferredSize().height);
		contentPanel.add(lblPergunta);
		
		lblResposta = new JLabel("Resposta:");
		lblResposta.setForeground(Color.BLACK);
		lblResposta.setFont(new Font("Arial", Font.PLAIN, 18));
		lblResposta.setBounds(68, 184, lblResposta.getPreferredSize().width, lblResposta.getPreferredSize().height);
		contentPanel.add(lblResposta);
		
		txtReposta = new JTextArea();
		txtReposta.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtReposta.setBounds(151, 180, 377, 200);
		txtReposta.setRows(5);
		txtReposta.setLineWrap(true);
		txtReposta.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPanel.add(txtReposta);
		txtReposta.setColumns(20);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		textArea.setRows(5);
		textArea.setLineWrap(true);
		textArea.setColumns(20);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setBounds(153, 55, 375, 59);
		contentPanel.add(textArea);
		
		
		
		lblFundo = new JLabel("");
		lblFundo.setBounds(0, 0, 611, 489);
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(JControleAdministrador.class.getResource("/view/imagens/FundoParaTudo.jpg"));
		} catch (Exception e) {
			// TODO: handle exception
		}

		Image dimg = img.getScaledInstance(lblFundo.getWidth(), lblFundo.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imgI = new ImageIcon(dimg);
		contentPanel.setLayout(null);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSalvar.setBounds(373, 412, 100, 28);
		contentPanel.add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String duvida = textArea.getText();
					String resposta = txtReposta.getText();
					ControlDuvidasFrequentes duvidas = new ControlDuvidasFrequentes();
					duvidas.Inserir(duvida, resposta);
					MessagemBoxCustom.ShowMensagem("Inserido com sucesso");
					textArea.setText("");
					txtReposta.setText("");
				} catch (Exception e) {
					MessagemBoxCustom.ShowMensagem("Ocorreu um erro ao inserir uma duvida.");
					e.printStackTrace();
				}
			}
		});
		btnSalvar.setActionCommand("OK");
		getRootPane().setDefaultButton(btnSalvar);
		
		
		JButton btnFechar = new JButton("Sair");
		btnFechar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnFechar.setBounds(484, 412, 100, 28);
		contentPanel.add(btnFechar);
		btnFechar.setActionCommand("Cancel");
		
		
		lblFundo.setIcon(imgI);
		//contentPanel.add(lblFundo);
		
	}
}
