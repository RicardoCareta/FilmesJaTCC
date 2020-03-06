package view;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import control.ControlDuvidasFrequentes;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class JDuvidasFrequentesVisual extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tblDuvidas;

	private ControlDuvidasFrequentes controlDuvidasFrequentes;
	
	private JLabel lblNewLabel;
	private JTextArea txtDuvida;
	private JTextArea txtResposta;
	private JCheckBox chkStatus;
	private JCheckBox chkModoEdicao;
	
	private TableRowSorter<TableModel> sorterName;

	private RowFilter<Object, Object> rf;
	private List<RowFilter<Object, Object>> filters;
	
	private boolean modoEdicao;
	private JButton btnAtualizar;
	private JLabel lblResposta;
	private JLabel lblDuvida;
	private JLabel lblBusca;
	private JTextField textField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDuvidasFrequentesVisual dialog = new JDuvidasFrequentesVisual();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDuvidasFrequentesVisual() {
		setBounds(100, 100, 910, 682);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		setTitle("Duvidas Frequentes");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 264, 810, 315);
		getContentPane().add(scrollPane);
		
		tblDuvidas = new JTable(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblDuvidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDuvidas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (modoEdicao) {
					int select = tblDuvidas.getSelectedRow();
					if(select > -1){
						txtDuvida.setText(tblDuvidas.getValueAt(select, 0).toString());	
						txtResposta.setText(tblDuvidas.getValueAt(select, 1).toString());
						chkStatus.setSelected((boolean)tblDuvidas.getValueAt(select, 2));
					}
				}
			}
		});
		scrollPane.setViewportView(tblDuvidas);
		
		
		controlDuvidasFrequentes = new ControlDuvidasFrequentes();
		tblDuvidas.setModel(controlDuvidasFrequentes.tblDuvidas());
		
		
		TableColumnModel tm = tblDuvidas.getColumnModel();
		tm.removeColumn(tm.getColumn(0));
		
		sorterName = new TableRowSorter<TableModel>(tblDuvidas.getModel()); 
		tblDuvidas.setRowSorter(sorterName);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 611, 489);
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(JControleAdministrador.class.getResource("/view/imagens/FundoParaTudo.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Image dimg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imgI = new ImageIcon(dimg);
		
		txtDuvida = new JTextArea();
		txtDuvida.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Filter();
			}
		});
		txtDuvida.setBounds(46, 39, 380, 119);
		txtDuvida.setLineWrap(true);

		getContentPane().add(txtDuvida);
		txtDuvida.setColumns(10);
		
		txtResposta = new JTextArea();
		txtResposta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Filter();
			}
		});
		txtResposta.setColumns(10);
		txtResposta.setBounds(476, 39, 380, 119);
		txtResposta.setLineWrap(true);
		getContentPane().add(txtResposta);
		
		chkStatus = new JCheckBox("Status");
		chkStatus.setSelected(true);
		chkStatus.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				Filter();
			}
		});
		chkStatus.setForeground(Color.BLACK);
		chkStatus.setOpaque(false);
		chkStatus.setBounds(181, 165, chkStatus.getPreferredSize().width, chkStatus.getPreferredSize().height);
		getContentPane().add(chkStatus);
		
		chkModoEdicao = new JCheckBox("Modo Edi\u00E7\u00E3o");
		chkModoEdicao.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(modoEdicao != chkModoEdicao.isSelected()){
					tblDuvidas.clearSelection();
				}
				modoEdicao = chkModoEdicao.isSelected();
				btnAtualizar.setVisible(modoEdicao);
				
			}
		});
		chkModoEdicao.setForeground(Color.BLACK);
		chkModoEdicao.setOpaque(false);
		chkModoEdicao.setBounds(46, 165, chkModoEdicao.getPreferredSize().width, chkModoEdicao.getPreferredSize().height);
		getContentPane().add(chkModoEdicao);
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int select = tblDuvidas.getSelectedRow();
				
				if(select > -1){
					int ID = Integer.parseInt(tblDuvidas.getModel().getValueAt(0, tblDuvidas.getSelectedRow()).toString());
					String duvida = txtDuvida.getText();
					String resposta = txtResposta.getText();
					boolean status = chkStatus.isSelected();
					controlDuvidasFrequentes.Atualizar(ID, duvida, resposta, status);
					
					MessagemBoxCustom.ShowMensagem("Atualizado com sucesso!");
					tblDuvidas.clearSelection();
					
				}else{
					MessagemBoxCustom.ShowMensagem("Por favor selecione um item para atualizar");
				}
				
				
				LoadTable();
			}
		});
		btnAtualizar.setVisible(false);
		btnAtualizar.setBounds(181, 209, 100, 28);
		getContentPane().add(btnAtualizar);
		
		lblDuvida = new JLabel("D\u00FAvida:");
		lblDuvida.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDuvida.setForeground(Color.BLACK);
		lblDuvida.setBounds(46, 18, lblDuvida.getPreferredSize().width, lblDuvida.getPreferredSize().height);
		getContentPane().add(lblDuvida);
		
		lblResposta = new JLabel("Resposta:");
		lblResposta.setFont(new Font("Arial", Font.PLAIN, 18));
		lblResposta.setForeground(Color.BLACK);
		lblResposta.setBounds(476, 18, lblResposta.getPreferredSize().width, lblResposta.getPreferredSize().height);
		getContentPane().add(lblResposta);
		
		JButton button = new JButton("Salvar");
		button.setFont(new Font("Arial", Font.PLAIN, 12));
		button.setActionCommand("OK");
		button.setBounds(634, 606, 100, 28);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("Sair");
		button_1.setFont(new Font("Arial", Font.PLAIN, 12));
		button_1.setActionCommand("Cancel");
		button_1.setBounds(756, 606, 100, 28);
		getContentPane().add(button_1);
		
		JButton lblInserir = new JButton("Inserir");
		lblInserir.setBounds(46, 209, 100, 28);
		getContentPane().add(lblInserir);
		
		lblBusca = new JLabel("Pesquisar D\u00FAvida: ");
		lblBusca.setFont(new Font("Arial", Font.PLAIN, 18));
		lblBusca.setBounds(46, 289, 158, 18);
		//getContentPane().add(lblBusca);
		
		textField = new JTextField();
		textField.setBounds(199, 285, 297, 28);
		//getContentPane().add(textField);
		textField.setColumns(10);
		
		lblNewLabel.setIcon(imgI);
		//getContentPane().add(lblNewLabel);
	}
	
	//TODO: Filtro tabelas
	public void Filter(){
		filters = new ArrayList<RowFilter<Object, Object>>();
		if(!modoEdicao){
			filters.add(RowFilter.regexFilter(txtDuvida.getText(), 1));	
			filters.add(RowFilter.regexFilter(txtResposta.getText(), 2));
			RowFilter<Object, Object> filterBoolean = new RowFilter<Object, Object>(){
					@Override
				public boolean include(javax.swing.RowFilter.Entry<? extends Object, ? extends Object> entry) {
					// TODO Auto-generated method stub
					for(int i = entry.getValueCount() - 1; i >=0; i--){
						if(entry.getValue(i).equals(chkStatus.isSelected())){
							return true;
						}
					}
					
					return false;
				}
					
			};
			filters.add(filterBoolean);
			
		}
		rf = RowFilter.andFilter(filters);
		
		sorterName.setRowFilter(rf);
		
	}
	
	private void LoadTable(){
		controlDuvidasFrequentes = new ControlDuvidasFrequentes();
		tblDuvidas.setModel(controlDuvidasFrequentes.tblDuvidas());
		
		
		TableColumnModel tm = tblDuvidas.getColumnModel();
		tm.removeColumn(tm.getColumn(0));
		
		sorterName = new TableRowSorter<TableModel>(tblDuvidas.getModel()); 
		tblDuvidas.setRowSorter(sorterName);
	}
}
