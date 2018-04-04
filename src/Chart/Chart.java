/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chart;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Michal
 */
public class Chart {
    private JFreeChart chart;
    private XYSeries series;
    private JPanel panel;
    
    private double maxFirst;
    private double minFirst;
    
    private String nameLabel;
    private String xLabel;
    private String yLabel;
    
    public Chart(String labelName, String labelX, String labelY) {
        this.nameLabel = labelName;
        this.xLabel = labelX;
        this.yLabel = labelY;
        
        this.series = createDataset();
        this.chart = createChart( this.series ); 
        this.panel = createPanel();
        
        this.maxFirst = 0.0;
        this.minFirst = Double.MAX_VALUE;

        
    }

    public JPanel getPanel() {
        return panel;
    }

    public JFreeChart getChart() {
        return chart;
    }
    
    public void resetDataset() {
        this.series.clear();
        this.chart.getXYPlot().setDataset(this.chart.getXYPlot().getDataset());
        this.maxFirst = 0.0;
        this.minFirst = Double.MAX_VALUE;
    }
      
    private XYSeries createDataset( ) 
   {
      XYSeries series = new XYSeries(this.yLabel); 
      return series;         
   }
    
        
    public void updateDataset(double x, double y) 
   {
        this.series.add(x, y);
        this.chart.getXYPlot().setDataset(this.chart.getXYPlot().getDataset());
   }
    
    
   private JFreeChart createChart( XYSeries dataset )
   {
        XYSeriesCollection data = new XYSeriesCollection( dataset );
        final JFreeChart chart = ChartFactory.createXYLineChart(this.nameLabel,
            this.xLabel, 
            this.yLabel, 
            data,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );

      return chart;
   }


   public JPanel createPanel( )
   {
      return new ChartPanel( this.chart ); 
   }

    public void updateRanges(double avg) {
        if (avg > this.maxFirst)
            this.maxFirst = avg;

        if (avg < this.minFirst) 
            this.minFirst = avg;
        
        if (this.minFirst == this.maxFirst) {
            maxFirst += 0.01;
            minFirst += 0.01;
        }
        
        if (this.minFirst != Double.MAX_VALUE && this.maxFirst != 0.0 && this.maxFirst != this.minFirst) {
            this.chart.getXYPlot().getRangeAxis().setRange(this.minFirst , this.maxFirst ); 
        }
        
        
    }
}

