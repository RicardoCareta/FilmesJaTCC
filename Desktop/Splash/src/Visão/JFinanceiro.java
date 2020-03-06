package Visão;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import Constantes.ConsLinks;
import Controle.ControlContrato;
import util.LinkLabel;
import util.OpenSources;
import util.ScreenConfigs.ScreenSize;

@SuppressWarnings("serial")
public class JFinanceiro extends JDialog {

	//Static JFrame
	//Tamanho
	private static final int WIDTH = 747;
	private static final int HEIGHT = 416;
	
	private JPanel contentPane;

	private int IDCliente;
	
	private JTable tblContrato;
	
	private JLabel lblParaRealizarPagamentos;
	private JLabel lblFinanceiro;
	
	private LinkLabel lblCliqueAqui;
	private JScrollPane scrollPane;
	
	private ControlContrato contrato;
	private JLabel lblVoltar;
	
	/**
	 * Launch the application.
	 */
	public static void main(int IDCliente) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFinanceiro frame = new JFinanceiro(IDCliente);
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
	public JFinanceiro(int IDClient) {
		this.IDCliente = IDClient;
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, ScreenSize.ConvertorSize(WIDTH, ScreenSize.FULANO_HEIGHT), ScreenSize.ConvertorSize(HEIGHT, ScreenSize.FULANO_HEIGHT));
		setUndecorated(true);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(ScreenSize.ConvertorSize(5, ScreenSize.FULANO_HEIGHT), ScreenSize.ConvertorSize(5, ScreenSize.FULANO_HEIGHT), ScreenSize.ConvertorSize(5, ScreenSize.FULANO_HEIGHT), ScreenSize.ConvertorSize(5, ScreenSize.FULANO_HEIGHT)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		contentPane.setBackground(Color.black);
		
		lblFinanceiro = new JLabel("Financeiro");
		lblFinanceiro.setForeground(Color.white);
		lblFinanceiro.setFont(new Font("SansSerif", Font.PLAIN, 18));
		
		int widthFinanceiro =lblFinanceiro.getPreferredSize().width;
		int heigthFinanceiro = lblFinanceiro.getPreferredSize().height;
		
		
		lblFinanceiro.setBounds(ScreenSize.ConvertorSize((WIDTH / 2 ) - widthFinanceiro / 2), 0, widthFinanceiro, heigthFinanceiro);
		
		contentPane.add(lblFinanceiro);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(ScreenSize.ConvertorSize(12, ScreenSize.FULANO_HEIGHT), ScreenSize.ConvertorSize(64, ScreenSize.FULANO_HEIGHT), ScreenSize.ConvertorSize(722, ScreenSize.FULANO_HEIGHT), ScreenSize.ConvertorSize(350, ScreenSize.FULANO_HEIGHT));
		contentPane.add(scrollPane);
		
		tblContrato = new JTable(){
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int column){
				return false;
			};
		};
		tblContrato.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		LoadDados();
		scrollPane.setViewportView(tblContrato);
		
		lblParaRealizarPagamentos = new JLabel("Para realizar pagamentos acesse o site");
		//System.out.println(lblParaRealizarPagamentos.getFont().getSize());
		lblParaRealizarPagamentos.setForeground(Color.white);
		lblParaRealizarPagamentos.setFont(new Font(lblParaRealizarPagamentos.getFont().getName(), lblParaRealizarPagamentos.getFont().getStyle(), ScreenSize.ConvertorSize(lblParaRealizarPagamentos.getFont().getSize())));
		lblParaRealizarPagamentos.setBounds(ScreenSize.ConvertorSize(425, ScreenSize.FULANO_HEIGHT), ScreenSize.ConvertorSize(40, ScreenSize.FULANO_HEIGHT), lblParaRealizarPagamentos.getPreferredSize().width, lblParaRealizarPagamentos.getPreferredSize().height);
		
		contentPane.add(lblParaRealizarPagamentos);
		
		lblCliqueAqui = new LinkLabel("clique aqui");
		lblCliqueAqui.setFont(new Font(lblCliqueAqui.getFont().getFontName(), lblCliqueAqui.getFont().getStyle(), ScreenSize.ConvertorSize(lblCliqueAqui.getFont().getSize(), ScreenSize.FULANO_HEIGHT)));
		lblCliqueAqui.setBounds(ScreenSize.ConvertorSize(654, ScreenSize.FULANO_HEIGHT), ScreenSize.ConvertorSize(40, ScreenSize.FULANO_HEIGHT), lblCliqueAqui.getPreferredSize().width, lblCliqueAqui.getPreferredSize().height);
		lblCliqueAqui.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				OpenSources.OpenURL(ConsLinks.PAGAMENTOS);
			}
		});
		
		contentPane.add(lblCliqueAqui);
		
		
		lblVoltar = new JLabel("X");
		lblVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ClickSair();
			}
		});
		lblVoltar.setForeground(Color.white);
		lblVoltar.setFont(new Font("SansSerif", Font.PLAIN, 14));
		System.out.println(this.getSize().width);
		lblVoltar.setBounds(ScreenSize.ConvertorSize(WIDTH, ScreenSize.FULANO_HEIGHT) - lblVoltar.getPreferredSize().width - 5, ScreenSize.ConvertorSize(0, ScreenSize.FULANO_HEIGHT), lblVoltar.getPreferredSize().width, lblVoltar.getPreferredSize().height);
		contentPane.add(lblVoltar);
	}
	
	private void LoadDados(){
		contrato = new ControlContrato();
		
		tblContrato.setModel(contrato.getTableModel(IDCliente));
		
		for (int i = 0; i < tblContrato.getColumnCount(); i++) {
			tblContrato.getColumnModel().getColumn(i).setCellRenderer(new CustomFont());
		}
		
		for (int row = 0; row < tblContrato.getRowCount(); row++) {
			int rowHeight = tblContrato.getRowHeight();
			
			for (int column = 0; column < tblContrato.getColumnCount(); column++) {
				Component comp = tblContrato.prepareRenderer(tblContrato.getCellRenderer(row, column), row, column);
				rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
			}
			
			tblContrato.setRowHeight(row, rowHeight);
		}
	}
	
	private void ClickSair(){
		this.dispose();
	}
}

class CustomFont extends DefaultTableCellRenderer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
		Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		cellComponent.setFont(new Font(cellComponent.getFont().getFontName(), Font.PLAIN, ScreenSize.ConvertorSize(cellComponent.getFont().getSize())));
		
		return cellComponent;
		
	}
	
}
