package com.msskx.java.view.table;


import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class MainViewTable extends JTable {
    public MainViewTable() {
        //设置表头
        JTableHeader tableHeader=new JTableHeader();
        tableHeader.setFont(new Font("幼圆",Font.BOLD,20));
        tableHeader.setForeground(Color.BLACK);
        //设置表格体
        setFont(new Font("幼圆",Font.PLAIN,18));
        setForeground(Color.gray);
        setGridColor(Color.BLACK);
        setRowHeight(40);
        //设置多行选择
        getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }
    public void renderRule(){
        //设置表格列的渲染格式
        Vector<String>columns= MainViewTableModel.getColumns();
        MainViewCellRender render=new MainViewCellRender();
        for (int i = 0; i < columns.size(); i++) {
            TableColumn column=this.getColumn(columns.get(i));
            column.setCellRenderer(render);

            //单独渲染某一列
//            if(i==1){
//                column.setPreferredWidth(50);
//                column.setMaxWidth(50);
//                column.setResizable(false);
//            }
        }
    }
}
