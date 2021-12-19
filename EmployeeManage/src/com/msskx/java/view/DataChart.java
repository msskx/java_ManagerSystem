package com.msskx.java.view;

import com.msskx.java.service.impl.EmployeeServiceImpl;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.awt.*;

public class DataChart {
    public DataChart() {
        DefaultPieDataset dataSet = new DefaultPieDataset();
        EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
        double[] sum=employeeServiceImpl.getAllSalary();
        double development=sum[0];
        double operation=sum[1];
        double product=sum[2];
        double market=sum[3];
        double sales=sum[4];


        dataSet.setValue("研发部", development);
        dataSet.setValue("运营部", operation);
        dataSet.setValue("产品部", product);
        dataSet.setValue("市场部", market);
        dataSet.setValue("销售部", sales);

        JFreeChart chart = ChartFactory.createPieChart3D("部门工资比例表", (PieDataset)dataSet, true, true, true);

        LegendTitle legend = null;
        TextTitle txtTitle = null;
        PiePlot categoryplot = null;

        legend = chart.getLegend();
        txtTitle = chart.getTitle();
        categoryplot = (PiePlot) chart.getPlot();

        txtTitle.setFont(new Font("幼圆", Font.BOLD, 36)); // 设置标题字体
        categoryplot.setLabelFont(new Font("微软雅黑", 30, 18));// 设置图片上固定指示文字字体
        legend.setItemFont(new Font("微软雅黑", 30, 16));// 设置图例字体

        ChartFrame chartFrame = new ChartFrame("部门工资比例表", chart);
        chartFrame.pack();
        chartFrame.setIconImage(new ImageIcon(".\\src\\com\\msskx\\java\\res\\icon.png").getImage());
        chartFrame.setResizable(false);
        chartFrame.setLocationRelativeTo(null);
        chartFrame.setVisible(true);

    }
}
