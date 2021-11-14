package com.roadjava.student.view;

import com.roadjava.student.table.MainViewTable;
import com.roadjava.student.table.MainViewTableModel;
import main.java.com.roadjava.util.DimensionUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;


public class MainView extends JFrame {


    Container container=getContentPane();
    JPanel northPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addBtn=new JButton("增加");
    JButton updateBtn=new JButton("修改");
    JButton delBtn=new JButton("删除");
    JTextField searchTXt=new JTextField(15);
    JButton searchBtn=new JButton("查询");

    JPanel southPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preBtn=new JButton("上一页");
    JButton nextBtn=new JButton("下一页");

    MainViewTable mainViewTable=new MainViewTable();

    public MainView() {
        //北面布局
        layoutNorth();
        //中间布局
        layoutCenter();
        //南面布局
        layoutSouth();

        setIconImage(new ImageIcon(".\\src\\main\\res\\icon.png").getImage());
        setBounds(DimensionUtil.getBounds());
        setExtendedState(JFrame.MAXIMIZED_BOTH);//默认最大化
        setLocationRelativeTo(null);//默认居中
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    private void layoutCenter() {
        Vector<Vector<Object>>data=new Vector<>();

        Vector<Object>row1=new Vector<>();
        row1.addElement("1");
        row1.addElement("mashuai");
        row1.addElement("ms1");
        row1.addElement("陕西");
        row1.addElement("0");
        row1.addElement("0");
        row1.addElement("100");
        Vector<Object>row2=new Vector<>();
        row2.addElement("2");
        row2.addElement("mashuai");
        row2.addElement("ms1");
        row2.addElement("陕西");
        row2.addElement("0");
        row2.addElement("0");
        row2.addElement("100");
        Vector<Object>row3=new Vector<>();
        row3.addElement("3");
        row3.addElement("mashuai");
        row3.addElement("ms1");
        row3.addElement("陕西");
        row3.addElement("0");
        row3.addElement("0");
        row3.addElement("100");

        data.addElement(row1);
        data.addElement(row2);
        data.addElement(row3);
        MainViewTableModel mainViewTableModel=MainViewTableModel.assembleModel(data);
        mainViewTable.setModel(mainViewTableModel);
        mainViewTable.renderRule();
        JScrollPane jScrollPane=new JScrollPane(mainViewTable);
        container.add(jScrollPane,BorderLayout.CENTER);
    }

    private void layoutSouth() {
        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        container.add(southPanel,BorderLayout.SOUTH);
    }

    private void layoutNorth() {
        northPanel.add(addBtn);
        northPanel.add(updateBtn);
        northPanel.add(delBtn);
        northPanel.add(searchBtn);
        northPanel.add(searchTXt);
        container.add(northPanel,BorderLayout.NORTH);
    }
}

