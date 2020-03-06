package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DeviationRenderer;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Week;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;
import org.jfree.ui.RectangleInsets;

public class FramePreBanca extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePreBanca frame = new FramePreBanca();
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
	public FramePreBanca() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
	//	setContentPane(contentPane);
	}
	
	 private static XYDataset createDataset() {

	        YIntervalSeries series1 = new YIntervalSeries("Inscrições");
	        YIntervalSeries series2 = new YIntervalSeries("Petshop");
	        RegularTimePeriod t = new Week();
	        double y1 = 5;
	        double y2 = 5;
	        
	        for (int i = 0; i <= 10; i++) {
	        	 y2 = (Math.random() * 10);
	        	 if(y2 < 0){
	        		 y2 = 0;
	        	 }
	        	 y1 = (Math.random() * 10);
	        	 if(i >= 9){
	        		 y2 = 0;
	        		 if(y1 < y2){
	        			 double ax = y1;
	        			 y1 = y2 + 1;
	        			 y1 = ax;
	        		 }
	        	 }
	        	 
	            double dev1 = (0.05 * i);
	            series1.add(t.getFirstMillisecond(), y1, y1 - dev1, y1 + dev1);
	           

	            double dev2 = (0.07 * i);
	            series2.add(t.getFirstMillisecond(), y2, y2 - dev2, y2 + dev2);
	           
	            
	            t = t.next();
	        }

	        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
	        dataset.addSeries(series1);
	        dataset.addSeries(series2);

	        return dataset;

	    }

	    /**
	     * Creates a chart.
	     *
	     * @param dataset  the data for the chart.
	     *
	     * @return a chart.
	     */
	    private static JFreeChart createChart(XYDataset dataset) {

	        // create the chart...
	        JFreeChart chart = ChartFactory.createTimeSeriesChart(
	            "Avaliações Escola",          // chart title
	            "Periodo",                   // x axis label
	            "Avaliações",       // y axis label
	            dataset,                  // data
	            true,                     // include legend
	            true,                     // tooltips
	            false                     // urls
	        );


	        // get a reference to the plot for further customisation...
	        XYPlot plot = (XYPlot) chart.getPlot();
	        plot.setDomainPannable(true);
	        plot.setRangePannable(false);
	        plot.setInsets(new RectangleInsets(5, 5, 5, 20));

	        DeviationRenderer renderer = new DeviationRenderer(true, false);
	        renderer.setSeriesStroke(0, new BasicStroke(3.0f, BasicStroke.CAP_ROUND,
	                BasicStroke.JOIN_ROUND));
	        renderer.setSeriesStroke(0, new BasicStroke(3.0f,
	                BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
	        renderer.setSeriesStroke(1, new BasicStroke(3.0f,
	                BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
	        renderer.setSeriesFillPaint(0, new Color(255, 200, 200));
	        renderer.setSeriesFillPaint(1, new Color(200, 200, 255));
	        plot.setRenderer(renderer);

	        // change the auto tick unit selection to integer units only...
	        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
	        yAxis.setAutoRangeIncludesZero(false);
	        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

	        return chart;

	    }

	    /**
	     * Creates a panel for the demo (used by SuperDemo.java).
	     *
	     * @return A panel.
	     */
	    public static JPanel createDemoPanel() {
	        JFreeChart chart = createChart(createDataset());
	        return new ChartPanel(chart);
	    }

	    /**
	     * Starting point for the demonstration application.
	     *
	     * @param args  ignored.
	     */

}
