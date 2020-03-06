package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.DefaultCategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import control.ControlCliente;

public class JGraficoGanhos extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel = new JPanel();
	private JPanel contentPanel_1;
	
	private JPanel chartPanelMes;
	private JPanel chartPanelAno;
	
	private ControlCliente controlCliente;
	private JLabel lblNewLabel;
	private JComboBox cbmFiltro;
	private JLabel lblTotalDeCadastrados;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JGraficoGanhos dialog = new JGraficoGanhos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JGraficoGanhos() {
		setTitle("Ganhos - Dashboard");
		controlCliente = new ControlCliente();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 700);
		setLocationRelativeTo(null);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		chartPanelMes = createDemoPanelMes();
		chartPanelMes.setBounds(0, 39, 1264, 622);
		chartPanelMes.setPreferredSize(new java.awt.Dimension(500, 270));
        
		chartPanelAno = createDemoPanelAno();
		chartPanelAno.setBounds(chartPanelMes.getBounds());
		chartPanelAno.setPreferredSize(chartPanelAno.getPreferredSize());
		
		contentPanel_1 = new JPanel();
        contentPanel_1.setLayout(null);
        contentPanel_1.add(chartPanelAno);
        
        setContentPane(contentPanel_1);
        
        lblNewLabel = new JLabel("Filtro:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(56, 13, lblNewLabel.getPreferredSize().width, lblNewLabel.getPreferredSize().height);
        contentPanel_1.add(lblNewLabel);
        
        cbmFiltro = new JComboBox();
        cbmFiltro.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(cbmFiltro.getSelectedIndex() != 0){
        			FiltroMes();
        		}else{
        			FiltroAno();
        		}
        	}
        });
        cbmFiltro.setModel(new DefaultComboBoxModel(new String[] {"Esperado", "Real"}));
        cbmFiltro.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbmFiltro.setBounds(101, 10, 107, 25);
        contentPanel_1.add(cbmFiltro);
        
        lblTotalDeCadastrados = new JLabel("Total de Cadastrados: " + controlCliente.getClientesCount());
        lblTotalDeCadastrados.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTotalDeCadastrados.setBounds(218, 13, lblTotalDeCadastrados.getPreferredSize().width, lblTotalDeCadastrados.getPreferredSize().height);
       // contentPanel_1.add(lblTotalDeCadastrados);
	}
	
	private DefaultCategoryDataset createDatasetMes() {

	       XYSeries series = new XYSeries("Ganhos Reais");
	       series.add(1.0, 2.0);
	       series.add(3.0, 10.0);

	       XYSeriesCollection dataset = new XYSeriesCollection();
	       dataset.addSeries(series);
	       
	       DefaultCategoryDataset ds = new DefaultCategoryDataset();
	       
	       List<String> valoresMeses = controlCliente.SelecionarGanhosReais();
	       
	       ds.addValue(Double.parseDouble(valoresMeses.get(0)), "teste", "Jan");
	       ds.addValue(Double.parseDouble(valoresMeses.get(1)), "teste", "Fev");
	       ds.addValue(Double.parseDouble(valoresMeses.get(2)), "teste", "Mar");
	       ds.addValue(Double.parseDouble(valoresMeses.get(3)), "teste", "Abr");
	       ds.addValue(Double.parseDouble(valoresMeses.get(4)), "teste", "Mai");
	       ds.addValue(Double.parseDouble(valoresMeses.get(5)), "teste", "Jun");
	       ds.addValue(Double.parseDouble(valoresMeses.get(6)), "teste", "Jul");
	       ds.addValue(Double.parseDouble(valoresMeses.get(7)), "teste", "Ago");
	       ds.addValue(Double.parseDouble(valoresMeses.get(8)), "teste", "Set");
	       ds.addValue(Double.parseDouble(valoresMeses.get(9)), "teste", "Out");
	       ds.addValue(Double.parseDouble(valoresMeses.get(10)), "teste", "Nov");
	       ds.addValue(Double.parseDouble(valoresMeses.get(11)), "teste", "Dez");
	       
	        return ds;
	 }
	
	private DefaultCategoryDataset createDatasetAno() {

	       XYSeries series = new XYSeries("Ganhos Esperados");
	       series.add(1.0, 2.0);
	       series.add(3.0, 10.0);

	       XYSeriesCollection dataset = new XYSeriesCollection();
	       dataset.addSeries(series);
	       
	       DefaultCategoryDataset ds = new DefaultCategoryDataset();
	       
	       List<String> valoresMeses = controlCliente.SelecionarGanhosEsperados();
	       
	       ds.addValue(Double.parseDouble(valoresMeses.get(0)), "teste", "Jan");
	       ds.addValue(Double.parseDouble(valoresMeses.get(1)), "teste", "Fev");
	       ds.addValue(Double.parseDouble(valoresMeses.get(2)), "teste", "Mar");
	       ds.addValue(Double.parseDouble(valoresMeses.get(3)), "teste", "Abr");
	       ds.addValue(Double.parseDouble(valoresMeses.get(4)), "teste", "Mai");
	       ds.addValue(Double.parseDouble(valoresMeses.get(5)), "teste", "Jun");
	       ds.addValue(Double.parseDouble(valoresMeses.get(6)), "teste", "Jul");
	       ds.addValue(Double.parseDouble(valoresMeses.get(7)), "teste", "Ago");
	       ds.addValue(Double.parseDouble(valoresMeses.get(8)), "teste", "Set");
	       ds.addValue(Double.parseDouble(valoresMeses.get(9)), "teste", "Out");
	       ds.addValue(Double.parseDouble(valoresMeses.get(10)), "teste", "Nov");
	       ds.addValue(Double.parseDouble(valoresMeses.get(11)), "teste", "Dez");
	       
	       return ds;
	 }
	 
	 private void FiltroMes(){
		contentPanel_1.remove(chartPanelAno);
		contentPanel_1.add(chartPanelMes);

		contentPanel_1.repaint();
	 }
	 
	 private void FiltroAno(){
		contentPanel_1.remove(chartPanelMes);
		contentPanel_1.add(chartPanelAno);
		
		contentPanel_1.repaint();
		
	 }

	    /**
	     * Creates a chart.
	     *
	     * @param dataset  the data for the chart.
	     *
	     * @return a chart.
	     */
	    /*private static JFreeChart createChart(XYDataset dataset) {

	        // create the chart...
	        JFreeChart chart = ChartFactory.createTimeSeriesChart(
	            "Usuário",          // chart title
	            "Periodo",                   // x axis label
	            "Quantidade",       // y axis label
	            dataset,                  // data
	            true,                     // include legend
	            true,                     // tooltips
	            false                     // urls
	        );


	        // get a reference to the plot for further customisation...
	        XYPlot plot = (XYPlot) chart.getPlot();
	        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
	        renderer.setSeriesLinesVisible(0, true);
	        renderer.setSeriesShapesVisible(0, false);
	        renderer.setSeriesLinesVisible(1, false);
	        renderer.setSeriesShapesVisible(1, true);        
	        plot.setRenderer(renderer);

	        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
	        yAxis.setAutoRangeIncludesZero(false);
	        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

	        return chart;

	    }*/

	    /**
	     * Creates a panel for the demo (used by SuperDemo.java).
	     *
	     * @return A panel.
	     */
	    public JPanel createDemoPanelMes() {
	        //JFreeChart chart = createChart(createDataset());
	        JFreeChart chart = ChartFactory.createLineChart("Ganhos Reais", "Mês", "BTC", createDatasetMes(), PlotOrientation.VERTICAL, true, true, false);
	        CategoryPlot plot = (CategoryPlot) chart.getPlot();
	        CategoryItemRenderer renderer = new DefaultCategoryItemRenderer();
	        
	        renderer.setSeriesStroke(0, new BasicStroke(3.0f, BasicStroke.CAP_ROUND,
	                BasicStroke.JOIN_ROUND));
	        renderer.setSeriesStroke(0, new BasicStroke(3.0f,
	                BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
	        renderer.setSeriesStroke(1, new BasicStroke(3.0f,
	                BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
	        
	        
	        chart.removeLegend();
	        //renderer.setSeriesFillPaint(0, new Color(255, 200, 200));
	        //renderer.setSeriesFillPaint(1, new Color(200, 200, 255));
	        plot.setRenderer(renderer);

	        // change the auto tick unit selection to integer units only...
	        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
	        yAxis.setAutoRangeIncludesZero(false);
	        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	        
	        plot.setRenderer(renderer);

	    	return new ChartPanel(chart);
	    }
	    
	    public JPanel createDemoPanelAno() {
	        //JFreeChart chart = createChart(createDataset());
	        JFreeChart chart = ChartFactory.createLineChart("Ganhos Esperados", "Mês", "BTC", createDatasetAno(), PlotOrientation.VERTICAL, true, true, false);
	        CategoryPlot plot = (CategoryPlot) chart.getPlot();
	        CategoryItemRenderer renderer = new DefaultCategoryItemRenderer();
	        
	        renderer.setSeriesStroke(0, new BasicStroke(3.0f, BasicStroke.CAP_ROUND,
	                BasicStroke.JOIN_ROUND));
	        renderer.setSeriesStroke(0, new BasicStroke(3.0f,
	                BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
	        renderer.setSeriesStroke(1, new BasicStroke(3.0f,
	                BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
	        
	        
	        chart.removeLegend();
	        //renderer.setSeriesFillPaint(0, new Color(255, 200, 200));
	        //renderer.setSeriesFillPaint(1, new Color(200, 200, 255));
	        plot.setRenderer(renderer);

	        // change the auto tick unit selection to integer units only...
	        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
	        yAxis.setAutoRangeIncludesZero(false);
	        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	        
	        plot.setRenderer(renderer);

	    	return new ChartPanel(chart);
	    }

}
