package com.roadjava.student.table;

import main.java.com.roadjava.student.table.MainViewCellRender;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class MainViewTable extends JTable {

    public MainViewTable() {

        //设置表头
        JTableHeader tableHeader= getTableHeader();
        tableHeader.setFont(new Font(null,Font.BOLD,16));
        tableHeader.setForeground(Color.RED);
        //设置表格体
        setFont(new Font(null, Font.PLAIN,14));
        setForeground(Color.CYAN);
        setGridColor(Color.BLACK);
        setRowHeight(60);
        //设置多行选择
        getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        //renderRule();
    }

    public void renderRule() {
        //设置表格列的渲染格式
        Vector<String> columns= MainViewTableModel.getColumns();
        MainViewCellRender render=new MainViewCellRender();
        for(int i=0;i< columns.size();i++){
            TableColumn column=this.getColumn(columns.get(i));
            column.setCellRenderer(render);
            if(i==0){
                column.setPreferredWidth(50);
                column.setMaxWidth(50);
                column.setResizable(false);
            }
        }
    }

}
